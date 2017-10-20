package es.source.code.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.adapter.MyAdapter;
import es.source.code.model.Food;
import es.source.code.model.Student;
//import es.source.code.adapter.listAdapter;

/**
 * Created by apple on 2017/10/14.
 */

public class HomeFragment extends Fragment {

    public static List<Food> alllist =new ArrayList<>();
    private MyAdapter adapter;
    private ListView listView1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //setContentView(R.layout.buy);
        //listView = (ListView)findViewById(R.id.listView_fun);


        View rootView = inflater.inflate(R.layout.fragment_tablayout, container, false);

        listView1= rootView.findViewById(R.id.ltvw);

        Food food0=new Food();
        Food food1=new Food();
        Food food2=new Food();
        food0.setPrice(38);
        food0.setName("小鸡炖蘑菇");
        food0.setPhoto(R.drawable.xiaoji);
        alllist.add(food0);
        food1.setPrice(58);
        food1.setName("烧鸡");
        food1.setPhoto(R.drawable.shaoji);
        alllist.add(food1);
        food2.setPrice(58);
        food2.setName("汽锅鸡");
        food2.setPhoto(R.drawable.qiguo);
        alllist.add(food2);


        for(int i=3;i<13;i++){
            Food food=new Food();
            food.setPrice(18+i);
            food.setName("炸鸡"+i);
            food.setPhoto(R.drawable.food);
            alllist.add(food);
        }

        adapter=new MyAdapter(alllist,getActivity());
        listView1.setAdapter(new MyAdapter(alllist,getActivity()));
        return rootView;
    }
}
