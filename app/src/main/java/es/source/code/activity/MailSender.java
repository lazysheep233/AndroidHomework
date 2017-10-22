package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by apple on 2017/10/22.
 */

public class MailSender implements Runnable {

    private Context mcontext;
    //private Handler handler = new Handler();

    public MailSender(Context context){
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


    }
}
