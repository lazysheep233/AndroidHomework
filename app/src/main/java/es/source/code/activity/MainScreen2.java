package es.source.code.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import es.source.code.model.User;


public class MainScreen2 extends Activity implements AdapterView.OnItemClickListener {
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    private int[] icon = {R.drawable.buy, R.drawable.order,
            R.drawable.login, R.drawable.help,};
    private String[] iconName = {"点餐", "菜篮", "登录", "帮助"};
    public  User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        gview = (GridView) findViewById(R.id.gview);

        data_list = new ArrayList<Map<String, Object>>();

        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};

        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();


        String  from1 = bundle.getString("from");
        if (from1!=null) {
            switch (from1) {
                case "fromentry":
                    //来自入口Activity
                    getDataWithoutLogin();

                    Toast.makeText(MainScreen2.this, "来自入口", Toast.LENGTH_SHORT).show();
                    break;

                case "LoginSuccess":
                    //来自LoginOrRegister且登录成功
                    user =(User)bundle.getSerializable("usr");
                    Toast.makeText(MainScreen2.this, from1+user.getName(), Toast.LENGTH_SHORT).show();
                    getData();
                    break;

                case "Return":
                    //用户点击返回取消登录
                    Toast.makeText(MainScreen2.this, from1, Toast.LENGTH_SHORT).show();
                    break;
                case "fromtest":
                    Toast.makeText(MainScreen2.this, from1, Toast.LENGTH_SHORT).show();
                    break;
                case "RegisterSuccess":
                    user =(User)bundle.getSerializable("usr");
                    Toast.makeText(MainScreen2.this, "欢迎："+user.getName()+"成为新用户", Toast.LENGTH_SHORT).show();
                    getData();
                    break;

            }
        }

        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);

        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(this);

    }

    public List<Map<String, Object>> getData() {

        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

    public List<Map<String, Object>> getDataWithoutLogin() {

        for (int i = 2; i < 4; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (data_list.size()==2){
            switch (position){
                case 0:
                    Toast.makeText(MainScreen2.this, "登录", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent();
                    intent3.setClass(MainScreen2.this, LoginOrRegister.class);
                    startActivity(intent3);
                    break;
                case 1:
                    Toast.makeText(MainScreen2.this, "帮助", Toast.LENGTH_SHORT).show();
                    Intent intent5 = new Intent();
                    intent5.setClass(MainScreen2.this, buy2.class);
                    startActivity(intent5);
                    break;
            }

        }else if (data_list.size()==4){
            switch (position) {
                case 0:
                    Toast.makeText(MainScreen2.this, "点餐", Toast.LENGTH_SHORT).show();
                    Intent intentnew = new Intent(MainScreen2.this,buy.class);
                    Bundle bundlenew = new Bundle();
                    bundlenew.putSerializable("usr",user);
                    bundlenew.putString("from","Mainscreen");
                    intentnew.putExtras(bundlenew);
                    startActivity(intentnew);
                    break;
                case 1:
                    Toast.makeText(MainScreen2.this, "菜篮", Toast.LENGTH_SHORT).show();
                    Intent intent4 = new Intent(MainScreen2.this,FoodOrderView.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("usr",user);
                    bundle1.putString("from","Mainscreen");
                    intent4.putExtras(bundle1);
                    startActivity(intent4);
                    break;
                case 2:
                    Toast.makeText(MainScreen2.this, "登录", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent();
                    intent3.setClass(MainScreen2.this, LoginOrRegister.class);
                    startActivity(intent3);
                    break;
                case 3:
                    Toast.makeText(MainScreen2.this, "帮助", Toast.LENGTH_SHORT).show();
                    Intent intent5 = new Intent();
                    intent5.setClass(MainScreen2.this, buy.class);
                    startActivity(intent5);
                    break;
            }
        }


    }
}