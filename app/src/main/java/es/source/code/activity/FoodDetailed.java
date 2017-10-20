package es.source.code.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.adapter.MyAdapter;
import es.source.code.adapter.TabAdapter;
import es.source.code.fragment.TabLayoutFragment;
import es.source.code.model.Student;
import es.source.code.model.User;

import static es.source.code.fragment.HomeFragment.alllist;

public class FoodDetailed extends AppCompatActivity{

    private static int itempage;
    private TabLayout tab;
    private ViewPager viewpager;
    private TabAdapter adapter;
    private MyAdapter adapter1;
    private ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy1);
        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();

        itempage = bundle.getInt("id");
        initviews();
    }



    private void initviews() {
        tab = (TabLayout) findViewById(R.id.tab1);
        viewpager = (ViewPager) findViewById(R.id.viewpager1);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < alllist.size(); i++) {
            //Toast.makeText(FoodDetailed.this, MyAdapter.orderlist.size() , Toast.LENGTH_SHORT).show();
            fragments.add(TabLayoutFragment.newInstance(i+1 ));
        }
        adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        //给ViewPager设置适配器
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(itempage);
        //将TabLayout和ViewPager关联起来。

        tab.setupWithViewPager(viewpager);
        //设置从listview跳转过来时position为ViewPager的当前位置，通过Intent传递
        //设置可以滑动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);


    }
}


