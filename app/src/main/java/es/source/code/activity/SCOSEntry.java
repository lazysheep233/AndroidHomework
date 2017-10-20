package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SCOSEntry extends AppCompatActivity {

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    //private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);


        //mTextMessage = (TextView) findViewById(R.id.textView);
        Intent intent1 = getIntent();
        String  request = intent1.getStringExtra("request");

        if (!(request == null || request == "")){
            if (request.equals("testscos")){
                requestFromTest();
                Toast.makeText(SCOSEntry.this, "来自外部应用请求来自"+request, Toast.LENGTH_SHORT).show();
                //mTextMessage.setText(request);
            }
        }

//        if (from != null && from != "" && from.equals("testscos")) {
//            Intent intentTest = new Intent();
//            intentTest.setClass(SCOSEntry.this, MainScreen2.class);
//            startActivity(intentTest);
//        }

        /*
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SCOSEntry.this, MainScreen2.class);
                intent.putExtra("from","fromentry");
                //intent.putExtra("message", "fromentry");
                startActivity(intent);
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.xml.r2l, R.xml.l2r);

                //http://blog.csdn.net/jdsjlzx/article/details/43835341
            }
        });*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        //参考自 http://blog.csdn.net/qiantujava/article/details/9903891
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 25) {
                //向上滑，暂时用不到;
            } else if(y2 - y1 > 25) {
                //向下滑;
            } else if(x1 - x2 > 25) {
                //向左滑，设置Intent实现跳转和值的传递
                Toast.makeText(SCOSEntry.this, "向左滑", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                //intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                Intent intent = new Intent();
                intent.setClass(SCOSEntry.this, MainScreen2.class);
                intent.putExtra("from","fromentry");
                startActivity(intent);
                //为了符合左滑的效果，设置切换动画，新活动从右边进入，旧活动左边退出
                overridePendingTransition(R.xml.r2l, R.xml.l2r);
            } else if(x2 - x1 > 25) {
                //向右滑;
            }
        }
        return super.onTouchEvent(event);
    }

    public void requestFromTest(){
        Intent intentTest = new Intent();
        intentTest.setClass(SCOSEntry.this, MainScreen.class);
        intentTest.putExtra("from","fromtest");
        startActivity(intentTest);
    }

}
