package com.thok.iem.ui.fragmentviewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.thok.iem.R;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.UserBean;
import com.thok.iem.ui.AlterPassActivity;
import com.thok.iem.ui.BaseActivity;
import com.thok.iem.ui.HomeActivity;
import com.thok.iem.utils.DataBaseHelp;
import com.thok.iem.utils.SharedPreferencesUtil;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Activity masterActivity;
    private OnFragmentInteractionListener mListener;
    private TextView userAccountText;
    private TextView userNameText;
    private TextView roleLabelText;
    private TextView createTimeText;
    private String userID;
    private String passWord;
    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        printLog("MyFragment","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        view.findViewById(R.id.logout_button).setOnClickListener(this);
        view.findViewById(R.id.alter_password).setOnClickListener(this);
        userAccountText = view.findViewById(R.id.user_account_label);
        userNameText = view.findViewById(R.id.user_name_label);
        roleLabelText = view.findViewById(R.id.role_label);
        createTimeText = view.findViewById(R.id.create_time_label);
        userID = SharedPreferencesUtil.getInstance(getActivity()).getString(BaseActivity.LAST_LOGIN_USER_ID);
        DataBaseHelp dataBaseHelp = new DataBaseHelp(getActivity(),UserBean.class);
        Cursor cursor = dataBaseHelp.getReadableDatabase().rawQuery("select * from UserBean where id=?",new String[]{userID});
        printLog(getBaseTag(),"getCount = "+cursor.getCount());
        if(cursor.moveToFirst()){
            userAccountText.setText(cursor.getString(cursor.getColumnIndex("userName")));
            userNameText.setText(cursor.getString(cursor.getColumnIndex("realName")));
            passWord = cursor.getString(cursor.getColumnIndex("password"));
            createTimeText.setText(cursor.getString(cursor.getColumnIndex("createTime")).split("\\.")[0]);
            roleLabelText.setText(cursor.getString(cursor.getColumnIndex("remark")));

        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        masterActivity = (Activity) context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout_button:
                masterActivity.finish();
                break;
            case R.id.alter_password:
                Intent intent = new Intent(getActivity(),AlterPassActivity.class);
                intent.putExtra("password",passWord);
                intent.putExtra(BaseActivity.LAST_LOGIN_USER_ID,userID);
                startActivity(intent);
                break;
            default:
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
