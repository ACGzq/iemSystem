package com.thok.iem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.thok.iem.httpUtil.ErrCode;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.FilterHistory;
import com.thok.iem.model.LoginRequest;
import com.thok.iem.model.LoginResponse;
import com.thok.iem.model.UserBean;
import com.thok.iem.ui.BaseActivity;
import com.thok.iem.ui.ForgetPassWordActivity;
import com.thok.iem.ui.HomeActivity;
import com.thok.iem.utils.DataBaseHelp;
import com.thok.iem.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static com.thok.iem.ui.BaseActivity.LAST_LOGIN_USER_ID;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mUserAccount;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public static final String  KEY_FIRSTSTART = "isFirstStart_test1";
    private static String ACTION_DATAWEDGE_FROM_6_2 ="com.symbol.datawedge.api.ACTION";
    private static String EXTRA_CREATE_PROFILE ="com.symbol.datawedge.api.CREATE_PROFILE";

    private static String EXTRA_SET_CONFIG ="com.symbol.datawedge.api.SET_CONFIG";

    private static String EXTRA_PROFILE_NAME ="InventoryDEMO";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        mUserAccount = findViewById(R.id.user_name);
        populateAutoComplete();
        if(SharedPreferencesUtil.getInstance(getApplicationContext()).getBoolean(KEY_FIRSTSTART,true)){

        }
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });


        Button mSignInButton = findViewById(R.id.email_sign_in_button);
        mSignInButton.setOnClickListener(view -> attemptLogin());
        findViewById(R.id.forget).setOnClickListener((View view)-> startActivity(new Intent(LoginActivity.this,ForgetPassWordActivity.class)));
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        String userID = SharedPreferencesUtil.getInstance(LoginActivity.this).getString(LAST_LOGIN_USER_ID);
        if(!TextUtils.isEmpty(userID)){
            DataBaseHelp dbHelp = new DataBaseHelp(LoginActivity.this,UserBean.class);
            SQLiteDatabase readable = dbHelp.getReadableDatabase();
            Cursor cursor = readable.rawQuery("select * from UserBean where id=?",new String[]{userID});
            if(cursor.moveToFirst()){
                mUserAccount.setText(cursor.getString(cursor.getColumnIndex("userName")));
                mPasswordView.setText(cursor.getString(cursor.getColumnIndex("password")));
            }
        }
    }
    @Override
    protected void onStart() {
        SharedPreferencesUtil.getInstance(getApplicationContext()).setBoolean(KEY_FIRSTSTART,false);
        super.onStart();
    }
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mUserAccount, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, v -> requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS));
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == BaseActivity.FINISH_APP){
            finish();
        };
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mUserAccount.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String account = mUserAccount.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.thok_error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(account)) {
            mUserAccount.setError(getString(R.string.error_field_required));
            focusView = mUserAccount;
            cancel = true;
        } else if (hasIllegalCharacter(account)) {
            mUserAccount.setError(getString(R.string.thok_error_illegal_character));
            focusView = mUserAccount;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUserName(account);
            loginRequest.setPassWord(password);
            OkGo.<LoginResponse>post(RequestURLs.getUrlLogin())
                    .tag(this)
                    .upJson(new Gson().toJson(loginRequest))
            .execute(new OkGoJsonCallback<LoginResponse>() {
                @Override
                public void onErrorMessage(String str, int code) {
                    onPostExecute(false,str);
                }

                @Override
                public void onSuccess(Response<LoginResponse> response) {
                    UserBean userBean = response.body().getData();
                    userBean.setPassword(password);
                    ThokApplication.requestToken = userBean.getToken();
                    ThokApplication.userName = userBean.getRealName();
                    ThokApplication.realName = userBean.getRealName();
                    DataBaseHelp dbHelp = new DataBaseHelp(LoginActivity.this,UserBean.class);
                    SQLiteDatabase readable = dbHelp.getReadableDatabase();
                    Cursor cursor = readable.rawQuery("select * from UserBean where id=?",new String[]{userBean.getId()});
                    SQLiteDatabase dataBase = dbHelp.getWritableDatabase();
                    if(cursor.getCount()<1){
                        ContentValues values = new ContentValues();
                        values.put("id", userBean.getId());
                        values.put("token", userBean.getToken());
                        values.put("userName", userBean.getUserName());
                        values.put("realName", userBean.getRealName());
                        values.put("createTime", userBean.getCreateTime());
                        values.put("password", userBean.getPassword());
                        values.put("userRole", userBean.getUserRole());
                        values.put("remark", userBean.getRemark());
                        dataBase.insert(UserBean.class.getSimpleName(), null, values);
                    }else{
                        //dataBase.update();
                    }
                    SharedPreferencesUtil.getInstance(LoginActivity.this).setString(LAST_LOGIN_USER_ID,userBean.getId());
                    onPostExecute(true,"");
                }
            });
        }
    }
    protected void onPostExecute(Boolean success,String msg) {
        showProgress(false);
        if (success) {
            startActivityForResult(new Intent(LoginActivity.this,HomeActivity.class),0);
            //finish();
        } else {
            mPasswordView.setError(msg);
            mPasswordView.requestFocus();
        }
    }

    private boolean hasIllegalCharacter(String str) {
        //TODO: Replace this with your own logic
        return str.contains("*");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mUserAccount.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

}

