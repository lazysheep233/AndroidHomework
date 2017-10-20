package es.source.code.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.adapter.MyAdapter;
import es.source.code.adapter.OrderAdapter;
import es.source.code.model.Student;

/**
 * Created by apple on 2017/10/14.
 */

public class DiscoveryFragment extends Fragment{
    private OrderAdapter adapter;
    private ListView listView1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //setContentView(R.layout.buy);
        //listView = (ListView)findViewById(R.id.listView_fun);


        View rootView = inflater.inflate(R.layout.fragment_category_new, container, false);

        listView1= rootView.findViewById(R.id.ltvw2);
        //transaction.replace
        adapter=new OrderAdapter(getActivity());
        listView1.setAdapter(new OrderAdapter(MyAdapter.orderlist,getActivity()));
        return rootView;
    }
}
