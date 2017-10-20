package es.source.code.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.source.code.model.User;

import static es.source.code.activity.FoodOrderView.user;
import static es.source.code.adapter.MyAdapter.orderlist;

/**
 * Created by apple on 2017/10/16.
 */

public class downFragment extends Fragment {
    private  User user1 = new User();
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_down, container, false);
        final Button btn = (Button) view.findViewById(R.id.button6);
        final TextView totalPrice = (TextView) view.findViewById(R.id.price);
        final TextView totalnum = (TextView) view.findViewById(R.id.num);
        //totalPrice.setText("100");



        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn.setText("已结账");
                int totalprice=0;
                int num =0;
                num=orderlist.size();
                for (int i=0;i<orderlist.size();i++){

                    totalprice = totalprice+orderlist.get(i).getPrice();
                }

                totalPrice.setText("订单价格："+String.valueOf(totalprice));
                totalnum.setText("订单数量："+String.valueOf(num));
                if (user.getOldUser()==true){
                    Toast.makeText(getContext(), "您好，老顾客:" + user.getName()+"本次你可享受 7 折优惠" , Toast.LENGTH_SHORT).show();
                }

                // TODO Auto-generated method stub
                //control.btnClick(stuList.get(position).getName());



            }

        });
        return view;


    }
}
