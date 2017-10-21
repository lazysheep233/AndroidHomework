package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SCOSHelper extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    private int[] icon = {R.drawable.buy, R.drawable.order,
            R.drawable.login, R.drawable.help,R.drawable.help};
    private String[] iconName = {"用户协议", "关于系统", "电话人工帮助", "短信帮助","邮件帮助"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper);

        gview = (GridView) findViewById(R.id.helpgview);

        data_list = new ArrayList<Map<String, Object>>();

        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};

        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);

        getData();
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

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (data_list.size()==5){
            switch (position) {
                case 0:
                    Toast.makeText(SCOSHelper.this, "1", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(SCOSHelper.this, "2", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(SCOSHelper.this, "3", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(SCOSHelper.this, "4", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(SCOSHelper.this, "5", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    }
}
