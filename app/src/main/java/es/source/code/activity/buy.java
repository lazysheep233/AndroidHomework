package es.source.code.activity;

/**
 * Created by apple on 2017/10/14.
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.adapter.SectionsPagerAdapter;
import es.source.code.fragment.CatagoryFragment;
import es.source.code.fragment.DiscoveryFragment;
import es.source.code.fragment.HomeFragment;
import es.source.code.fragment.UserCentralFragment;
import es.source.code.fragment.HomeFragment;
import es.source.code.model.User;

public class buy extends AppCompatActivity implements BottomNavigationBar
        .OnTabSelectedListener, ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragments;
    public User user;


    private ListView lv;
    private List<Map<String, Object>> data;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy);

        initView();
        data = getData();
        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();
        user =(User)bundle.getSerializable("usr");
        Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();
        /*
        lv = (ListView)findViewById(R.id.listView_fun);
        data = getData();
        listAdapter adapter = new listAdapter(this);
        listView1.setAdapter(adapter);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alreadyorder:
                Toast.makeText(this, "已点菜品", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(buy.this,FoodOrderView.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("usr",user);
                bundle1.putInt("id",0);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.checkorder:
                Toast.makeText(this, "查看订单", Toast.LENGTH_SHORT).show();
                Intent intentnew = new Intent(buy.this,FoodOrderView.class);
                Bundle bundlenew = new Bundle();
                bundlenew.putSerializable("usr",user);
                bundlenew.putInt("id",1);
                intentnew.putExtras(bundlenew);
                startActivity(intentnew);
                //Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                downFragment oneFragment=new downFragment();
//
//                ft.replace(R.id.order_down,oneFragment);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("usr",user);
//                oneFragment.setArguments(bundle);
//                ft.commit();

                break;
            case R.id.help:
                Toast.makeText(this, "呼叫服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;

        }
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }




    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<String, Object>();
            map.put("img", R.drawable.ic_launcher);
            map.put("title", "233");
            map.put("info", "233333...");
            list.add(map);
        }
        return list;
    }

    static class ViewHolder
    {
        public ImageView img;
        public TextView title;
        public TextView info;
    }



    private void initView() {
        initBottomNavigationBar();
        initViewPager();
    }

    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar1);
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.cold, R.string.home)
                        .setInactiveIconResource(R.drawable.cold)
                        .setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.hot, R
                        .string.category)
                        .setInactiveIconResource(R.drawable.hot)
                        .setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.chicken, R
                        .string.discover)
                        .setInactiveIconResource(R.drawable.chicken)
                        .setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.wine, R.string.mine)
                        .setInactiveIconResource(R.drawable.wine)
                        .setActiveColorResource(R.color.orange))
                .initialise();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        //ListView listview1 = new ListView(this);

        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new CatagoryFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new UserCentralFragment());

        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
