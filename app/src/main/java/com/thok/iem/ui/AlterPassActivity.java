package com.thok.iem.ui;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.thok.iem.LoginActivity;
import com.thok.iem.R;
import com.thok.iem.ThokApplication;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.BaseResponse;
import com.thok.iem.model.UpdataPWRequest;
import com.thok.iem.model.UserBean;
import com.thok.iem.utils.DataBaseHelp;

public class AlterPassActivity extends BaseActivity {
    private String identificationPass;
    private String newPassWord;
    private EditText originPassEdit,newPassEdit,identificationPassEdit;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_pass);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View view)->this.finish());
        originPassEdit = findViewById(R.id.origin_pass);
        newPassEdit = findViewById(R.id.new_pass);
        newPassEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<5){
                    newPassEdit.setError("密码太短");
                }
            }
        });
        newPassEdit.setOnFocusChangeListener((v, hasFocus) -> {if(!hasFocus)checkPass();});
        identificationPassEdit = findViewById(R.id.identification_new_pass);
        identificationPassEdit.setOnFocusChangeListener((v, hasFocus) -> { if(!hasFocus)checkPass();});
       findViewById(R.id.submit_change).setOnClickListener((view) -> changePass());
       uid = getIntent().getStringExtra(LAST_LOGIN_USER_ID);

    }
    private boolean checkPass(){
        identificationPass = identificationPassEdit.getText().toString();
        newPassWord = newPassEdit.getText().toString();
        if(!TextUtils.isEmpty(newPassWord))
            if(newPassWord.equals(originPassEdit.getText().toString())){
                newPassEdit.setError("新密码不能和旧密码相同");
            }else if(!TextUtils.isEmpty(identificationPass)&&!newPassWord.equals(identificationPass)){
                identificationPassEdit.setError("两次密码输入不一致");
            }else{
                return true;
            }
        return false;
    }
    private void changePass(){
        UpdataPWRequest updataPWRequest = new UpdataPWRequest();
        updataPWRequest.setId(uid);
        updataPWRequest.setToken(ThokApplication.requestToken);
        updataPWRequest.setNewPassword(newPassWord);
        updataPWRequest.setOldPassword(originPassEdit.getText().toString());
        if(checkPass())
            OkGo.<BaseResponse>post(RequestURLs.URL_UPDATA_PW)
                .tag(this)
            .upJson(updataPWRequest.toJsonString())
                    .execute(new OkGoJsonCallback<BaseResponse>() {
                        @Override
                        public void onErrorMessage(String str, int code) {
                            Toast.makeText(AlterPassActivity.this,str,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(Response<BaseResponse> response) {
                            Toast.makeText(AlterPassActivity.this,"changePass",Toast.LENGTH_SHORT).show();
                            DataBaseHelp dbHelp = new DataBaseHelp(AlterPassActivity.this,UserBean.class);
                            SQLiteDatabase dataBase = dbHelp.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("password", newPassWord);
                            dataBase.update(UserBean.class.getSimpleName(),values,"id=?",new String[]{uid});
                            finish();
                        }
                    });

    }
}
