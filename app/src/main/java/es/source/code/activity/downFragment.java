package es.source.code.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import es.source.code.model.User;

import static es.source.code.activity.FoodOrderView.user;
import static es.source.code.adapter.MyAdapter.orderlist;

/**
 * Created by apple on 2017/10/16.
 */

public class downFragment extends Fragment {
    private User user1 = new User();
    ProgressBar orderProgress;
    Button btn;
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_down, container, false);
        btn = (Button) view.findViewById(R.id.button6);
        final TextView totalPrice = (TextView) view.findViewById(R.id.price);
        final TextView totalnum = (TextView) view.findViewById(R.id.num);
        orderProgress = (ProgressBar) view.findViewById(R.id.progressBarOrder);
        tv = (TextView) view.findViewById(R.id.progress);
        //totalPrice.setText("100");


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                orderTask newAsyncTask = new orderTask();
                newAsyncTask.execute(100);
//                btn.setEnabled(false);
//                btn.setText("已结账");

                int totalprice = 0;
                int num = 0;
                num = orderlist.size();
                for (int i = 0; i < orderlist.size(); i++) {

                    totalprice = totalprice + orderlist.get(i).getPrice();
                }

                totalPrice.setText("订单价格：" + String.valueOf(totalprice));
                totalnum.setText("订单数量：" + String.valueOf(num));
                if (user.getOldUser() == true) {
                    Toast.makeText(getContext(), "您好，老顾客:" + user.getName() + "本次你可享受 7 折优惠"+"订单总价："+String.valueOf(totalprice*0.7)+"获得积分"+String.valueOf(totalprice), Toast.LENGTH_SHORT).show();
                }

                // TODO Auto-generated method stub
                //control.btnClick(stuList.get(position).getName());


            }

        });
        return view;


    }

    class orderTask extends AsyncTask<Integer, Integer, String> {


        @Override
        protected void onPreExecute() {
            //第一个执行方法
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            //第二个执行方法,onPreExecute()执行完后执行
            for (int i = 0; i <= 100; i++) {
                orderProgress.setProgress(i);
                publishProgress(i);
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "执行完毕";
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            //这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
            //但是这里取到的是一个数组,所以要用progesss[0]来取值
            //第n个参数就用progress[n]来取值
            tv.setText("结账进度："+progress[0] + "%");
            super.onProgressUpdate(progress);
        }

        @Override
        protected void onPostExecute(String result) {
            //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
            //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"
            //setTitle(result);
            btn.setEnabled(false);
            btn.setText("已结账");
            super.onPostExecute(result);
        }
    }
}
