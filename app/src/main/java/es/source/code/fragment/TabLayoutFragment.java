package es.source.code.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.activity.buy1;
import es.source.code.adapter.MyAdapter;
import es.source.code.adapter.TabAdapter;
import es.source.code.model.Food;
import es.source.code.model.Student;

import static es.source.code.fragment.HomeFragment.alllist;

/**
 * Created by apple on 2017/10/15.
 */

public class TabLayoutFragment extends Fragment {
    public static String TABLAYOUT_FRAGMENT = "tab_fragment";
    private TextView txt;
    private int type;

    private MyAdapter adapter;
    private ListView listView1;
    public static TabLayoutFragment newInstance(int type) {

        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_detail, container, false);


        initView(view);
        return view;
    }


    protected void initView(View view) {
        //txt = (TextView) view.findViewById(R.id.tab_txt);
        ImageView image_photo = (ImageView) view.findViewById(R.id.imageView1);
        TextView tv_name1 = (TextView) view.findViewById(R.id.info);
        TextView tv_age1 = (TextView) view.findViewById(R.id.detail1);
        Button btn1 = (Button) view.findViewById(R.id.check);

            image_photo.setImageResource(alllist.get(type-1).getPhoto());
            tv_name1.setText(alllist.get(type-1).getName());
            tv_age1.setText(String.valueOf(alllist.get(type-1).getPrice()));
            if (alllist.get(type-1).getState()!=true){
                btn1.setText("点餐");
            }else {
                btn1.setText("退订");
            }




    }
}
