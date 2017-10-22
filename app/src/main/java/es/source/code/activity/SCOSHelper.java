package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SCOSHelper extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    public  Handler handler = new Handler() {

        // 该方法运行在主线程中
        // 接收到handler发送的消息
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if (msg.what == 1) {
                Toast.makeText(SCOSHelper.this, "邮件发送成功", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private int[] icon = {R.drawable.protocol,
            R.drawable.about,
            R.drawable.manservice,
            R.drawable.smsservice,
            R.drawable.malservice};
    private String[] iconName = {"用户协议", "关于系统", "电话人工帮助", "短信帮助","邮件帮助"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper);

        gview = (GridView) findViewById(R.id.helpgview);

        data_list = new ArrayList<Map<String, Object>>();

        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};

        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);

        getData();
        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(this);
    }

    public List<Map<String, Object>> getData() {

        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (data_list.size()==5){
            switch (position) {
                case 0:
                    Toast.makeText(SCOSHelper.this, "1", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(SCOSHelper.this, "2", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(SCOSHelper.this, "3", Toast.LENGTH_SHORT).show();
                    Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 5554));
                    startActivity(intentPhone);
                    break;
                case 3:
                    Toast.makeText(SCOSHelper.this, "4", Toast.LENGTH_SHORT).show();
                    SmsManager smsManager = SmsManager.getDefault();
                    String content = "test SCOS helper";
                    smsManager.sendTextMessage("5554", null, content, null, null);
                    Toast.makeText(SCOSHelper.this, "短信发送成功", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    MailSender1 threadnew = new MailSender1(SCOSHelper.this);
                    Thread t1 = new Thread(threadnew);
                    t1.start();


                    break;
            }
        }


    }

    public class MailSender1 implements Runnable {

        private Context mcontext;
        //private Handler handler = new Handler();

        public MailSender1(Context context){
            this.mcontext = context;
        }

        public void run(){
            Uri uri = Uri.parse("mailto:rukaxj@163.com");
            String[] email = {"383007265@qq.com"};
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
            intent.putExtra(Intent.EXTRA_SUBJECT, "SCOS helper"); // 主题
            intent.putExtra(Intent.EXTRA_TEXT, "安卓作业写完了吗作业功能自动发送不要回复靴靴"); // 正文
            mcontext.startActivity(Intent.createChooser(intent, "请选择邮件类应用"));

            handler.sendEmptyMessage(1);

        }
    }


}
