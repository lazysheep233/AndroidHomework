package es.source.code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.source.code.activity.R;
import es.source.code.activity.toastControl;
import es.source.code.model.Food;
import es.source.code.model.Student;

/**
 * Created by apple on 2017/10/16.
 */

public class willOrderAdapter extends BaseAdapter{
    private List<Food> stuList;
    private LayoutInflater inflater;
    private Boolean a;
    private Context mContext;
    toastControl control;

    public void serControl(toastControl control){
        this.control =control;
    }

    public willOrderAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }



    public willOrderAdapter(List<Food> stuList, Context context) {
        this.mContext = context;
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
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



        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item2,null);
        }

        //加载布局为一个视图
        View view = inflater.inflate(R.layout.list_item2, null);
        final Food student = getItem(position);
        //在view视图中查找id为image_photo的控件
        ImageView image_photo = (ImageView) view.findViewById(R.id.image_photo);
        TextView tv_name = (TextView) view.findViewById(R.id.name);
        TextView tv_age = (TextView) view.findViewById(R.id.age);

        final Button btn = (Button) view.findViewById(R.id.button5);
        image_photo.setImageResource(student.getPhoto());
        tv_name.setText(student.getName());
        tv_age.setText(String.valueOf(student.getPrice()));

        //btn.setText(stuList.get(position).getName());

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                //control.btnClick(stuList.get(position).getName());
                student.setState(false);
                MyAdapter.orderlist.remove(position);

                //control.btnClick(stuList.get(position).getName());
                //Toast.makeText(mContext, "退订 " + stuList.get(position).getName(), Toast.LENGTH_SHORT).show();

            }

        });

        return view;
    }
}
