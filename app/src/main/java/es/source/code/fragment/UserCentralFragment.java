package es.source.code.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.adapter.MyAdapter;
import es.source.code.adapter.willOrderAdapter;
import es.source.code.model.Student;

/**
 * Created by apple on 2017/10/14.
 */

public class UserCentralFragment extends Fragment {
    private willOrderAdapter adapter;
    private ListView listView1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //setContentView(R.layout.buy);
        //listView = (ListView)findViewById(R.id.listView_fun);


        View rootView = inflater.inflate(R.layout.will_order, container, false);

        listView1= rootView.findViewById(R.id.ltvw2);
        List<Student> stuList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Student stu=new Student();
            stu.setAge(10+i);
            stu.setName("未下单菜"+i);
            stu.setPhoto(R.mipmap.ic_launcher);
            stuList.add(stu);
        }

        adapter=new willOrderAdapter(getActivity());
        listView1.setAdapter(new willOrderAdapter(MyAdapter.orderlist,getActivity()));
        return rootView;
    }
}
