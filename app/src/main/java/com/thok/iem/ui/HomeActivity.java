package com.thok.iem.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.thok.iem.R;

import com.thok.iem.ui.fragmentviewmodel.EquipmentInfoFragment;
import com.thok.iem.ui.fragmentviewmodel.HomePageFragment;
import com.thok.iem.ui.fragmentviewmodel.MyFragment;

import java.util.Date;

public class HomeActivity extends BaseActivity implements MyFragment.OnFragmentInteractionListener,EquipmentInfoFragment.OnListFragmentInteractionListener {

    private TextView mHomeTitle;
    private long onBackPressedTime = 0;
    //private MyReceiver receiver;
    //public Toolbar mToolbar;
    Fragment homeFragment,equipentInfoFragment,myFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(homeFragment == null){
                        homeFragment = HomePageFragment.newInstance();
                    }
                    switchFragment(homeFragment);
                    return true;
                case R.id.navigation_information:
                    printLog("ItemSelected","navigation_information");
                    //startActivity(new Intent(HomeActivity.this,EquipmentInfoActivity.class));
                    if(equipentInfoFragment == null){
                        equipentInfoFragment = EquipmentInfoFragment.newInstance(1);
                    }
                     switchFragment(equipentInfoFragment);
                    return true;
                case R.id.navigation_my:
                    if(myFragment == null){
                        myFragment = MyFragment.newInstance("","");
                    }
                    switchFragment(myFragment);
                    return true;
            }
            return false;
        }
    };
    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       /* mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mToolbar.setTitle("");
        // 设置toolbar为Actionbar对象
        setSupportActionBar(mToolbar);
        //mActionBar = getSupportActionBar();
      //  mActionBar.setTitle("");*/
        mHomeTitle = (TextView) findViewById(R.id.home_title);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        homeFragment = HomePageFragment.newInstance();
        myFragment = MyFragment.newInstance("","");
        equipentInfoFragment = EquipmentInfoFragment.newInstance(1);
        if (savedInstanceState == null) {
            printLog("home_frame_layout","home_frame_layout");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.home_frame_layout, homeFragment).commit();
            currentFragment = homeFragment;
        }
       // receiver = new MyReceiver();
        String scannerInputPlugin = "com.symbol.datawedge.api.ACTION_SCANNERINPUTPLUGIN";
        String extraData = "com.symbol.datawedge.api.EXTRA_PARAMETER";
        Intent i = new Intent();
        i.setAction(scannerInputPlugin);
        // add additional info
        i.putExtra(extraData, "ENABLE_PLUGIN");
        // send the intent to DataWedge
        sendBroadcast(i);
    }

    @Override
    protected void onResume() {

        super.onResume();
     }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(INTENT_FILTER_ACTION);
        registerReceiver(myBroadcastReceiver,filter);
        super.onStart();
    }
    @Override
    protected void onRestart() {
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(INTENT_FILTER_ACTION);
        registerReceiver(myBroadcastReceiver,filter);
        super.onRestart();
    }
     @Override
    protected void onStop() {
        unregisterReceiver(myBroadcastReceiver);
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        long difference = new Date().getTime() - onBackPressedTime;
        if(difference<2000){
            setResult(FINISH_APP);
            finish();
        }else{
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
            onBackPressedTime += difference;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        printLog("iem_homeActivity","toString = "+event.toString()+"_getCharacters = "+event.getCharacters());
        return super.onKeyDown(keyCode, event);
    }

    public void hideAllFragement(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(equipentInfoFragment != null){
            transaction.hide(equipentInfoFragment);
        }
        if(myFragment != null){
            transaction.hide(myFragment);
        }

        transaction.commit();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        printLog("iem_homeActivity","intent="+intent.toString()+"_="+intent.getStringExtra("com.symbol.datawedge.data_string"));
        super.onNewIntent(intent);
    }

    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            printLog("switchFragment",targetFragment.getClass().getName()+"isAdded");
            transaction
                    .hide(currentFragment)
                    .add(R.id.home_frame_layout, targetFragment)
                    .commit();
        }else if(currentFragment != targetFragment){
            printLog("switchFragment",targetFragment.getClass().getName()+"show");
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();

        }
        currentFragment = targetFragment;
    }

        @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction() {

    }
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle b = intent.getExtras();

            if(INTENT_FILTER_ACTION.equals(action)) {
                //  Received a barcode scan
                try {
                   // displayScanResult(intent, "via Broadcast");
                    printLog("iem_homeActivity","decodedData =" + intent.getStringExtra("com.symbol.datawedge.data_string"));
                    ((EquipmentInfoFragment)equipentInfoFragment).updataUi(intent.getStringExtra("com.symbol.datawedge.data_string"));
                } catch (Exception e) {
                    //  Catch if the UI does not exist when we receive the broadcast... this is not designed to be a production app
                }
            }
        }
    };
}
