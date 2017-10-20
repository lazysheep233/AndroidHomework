package es.source.code.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.FoodOrderView;
import es.source.code.activity.MainScreen2;
import es.source.code.activity.SCOSEntry;
import es.source.code.activity.buy1;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.activity.buy;

import es.source.code.activity.buy2;
import es.source.code.activity.toastControl;
import es.source.code.model.Food;
import es.source.code.model.Student;

import static android.R.id.list;
import static java.security.AccessController.getContext;

/**
 * Created by apple on 2017/10/15.
 */

public class MyAdapter extends BaseAdapter {
    private Context mcontext;
    private List<Food> stuList;
    private LayoutInflater inflater;
    private Boolean a;
    public static List<Food> orderlist =new ArrayList<>();
    toastControl control;

    public void serControl(toastControl control){
        this.control =control;
    }

//    public MyAdapter(Context context) {
//        this.mcontext = context;
//        this.inflater = LayoutInflater.from(context);
//    }



    public MyAdapter(List<Food> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
        this.mcontext = context;
    }

    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public Food getItem(int position) {

        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



//        if (convertView == null){
//            convertView = inflater.inflate(R.layout.list_item,null);
//        }
        convertView = inflater.inflate(R.layout.list_item, null);


        //加载布局为一个视图
        //View view = inflater.inflate(R.layout.list_item, null);
        final Food food = getItem(position);
        //在view视图中查找id为image_photo的控件
        final ImageView image_photo = (ImageView) convertView.findViewById(R.id.image_photo);
        TextView tv_name = (TextView) convertView.findViewById(R.id.name);
        TextView tv_age = (TextView) convertView.findViewById(R.id.age);

        final Button  btn = (Button) convertView.findViewById(R.id.button3);
        image_photo.setImageResource(food.getPhoto());
        tv_name.setText(food.getName());
        tv_age.setText(String.valueOf(food.getPrice()));

        //btn.setText(stuList.get(position).getName());

        image_photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext, FoodDetailed.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("id",position);
                intent.putExtras(bundle1);
                mcontext.startActivity(intent);
                btn.setText("点击图片");

            }

        });

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                btn.setText("已点");
                food.setState(true);
                orderlist.add(food);
                Toast.makeText(mcontext, "点餐成功"+food.getName() , Toast.LENGTH_SHORT).show();

            }

        });

        return convertView;
    }
}
