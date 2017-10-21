package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import es.source.code.model.User;

public class MainScreen extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView textView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_buy:
                    mTextMessage.setText("buy");
                    return true;
                case R.id.navigation_bucket:
                    mTextMessage.setText("bucket");
                    return true;
                case R.id.navigation_login:
                    mTextMessage.setText("login");

                    return true;
                case R.id.navigation_help:
                    mTextMessage.setText("help");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SharedPreferences preferences=getSharedPreferences("userrecord", Context.MODE_PRIVATE);
        Boolean state =preferences.getBoolean("loginState",false);

        /*
        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();
        User newUser = (User)bundle.getSerializable("usr");
        String  from = bundle.getString("from");
        if (from!=null){
            switch(from){
                case "fromentry":
                    //来自入口Activity
                    mTextMessage.setText(from);
//                navigation.findViewById(R.id.navigation_bucket).setVisibility(View.GONE);
//                navigation.findViewById(R.id.navigation_buy).setVisibility(View.INVISIBLE);
                    break;

                case "LoginSuccess":
                    //来自LoginOrRegister且登录成功
                    mTextMessage.setText(from);
                    navigation.findViewById(R.id.navigation_bucket).setVisibility(View.VISIBLE);
                    navigation.findViewById(R.id.navigation_buy).setVisibility(View.VISIBLE);
                    break;

                case "Return":
                    //用户点击返回取消登录
                    mTextMessage.setText(from);
                    navigation.findViewById(R.id.navigation_bucket).setVisibility(View.INVISIBLE);
                    navigation.findViewById(R.id.navigation_buy).setVisibility(View.INVISIBLE);
                    break;
                case "fromtest":
                    mTextMessage.setText(from);
                    navigation.findViewById(R.id.navigation_bucket).setVisibility(View.INVISIBLE);
                    navigation.findViewById(R.id.navigation_buy).setVisibility(View.INVISIBLE);
                    break;
                case "RegisterSuccess":
                    mTextMessage.setText(from);
                    navigation.findViewById(R.id.navigation_bucket).setVisibility(View.INVISIBLE);
                    navigation.findViewById(R.id.navigation_buy).setVisibility(View.INVISIBLE);
                    break;

            }
        }*/

        /*
        Intent  intentobj = getIntent();
        User newUser1 = (User) intentobj.getSerializableExtra("usr");
        if (newUser1!=null){
            boolean userState = newUser1.getOldUser();
            if (userState){

            }else {

            }
        }
        */
            //mTextMessage.setText(newUser1.getName());
        /*
        User newUser = (User) getIntent().getSerializableExtra("usr");
        if (!(newUser.getName().equals(null))) {
            mTextMessage.setText(newUser.getName());
        }
        */




//        String nameString = intent1.getStringExtra("from");
//
//        String check = "fromentry";
//
//        if (nameString.equals(check)) {
//            //// TODO: 2017/9/25
//            mTextMessage.setText(nameString);
//        } else {
//            //mTextMessage.setText(nameString);
//            navigation.findViewById(R.id.navigation_bucket).setVisibility(View.GONE);
//            navigation.findViewById(R.id.navigation_buy).setVisibility(View.INVISIBLE);
//        }






    }


}
