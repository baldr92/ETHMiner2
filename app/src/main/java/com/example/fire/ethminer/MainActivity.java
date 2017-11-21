package com.example.fire.ethminer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    boolean flag = true;
    GraphView graphView;
    private String titles[];
    private ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //устанавливаем две разные картинки для двух разгых действий
                if (flag) {
                    imageButton.setImageResource(R.drawable.unnamed1);
                    //код включения
                }
                else {
                    imageButton.setImageResource(R.drawable.ethereum1);
                    //код отключения майнера
                }
            }
        });

        graphView = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
             new DataPoint(0, 1),
             new DataPoint(1,3),
             new DataPoint(2, 3)
        });
        graphView.addSeries(series);

        TextView textView = (TextView) findViewById(R.id.balance);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent balanceIntent = new Intent();
                balanceIntent.setClass(MainActivity.this, BalanceActivity.class);
            }
        });

        titles = getResources().getStringArray(R.array.titles);
        drawerList = (ListView) findViewById(R.id.drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickLisener());

    }

    public class DrawerItemClickLisener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        switch (position) {
            case 1:
                Intent accountIntent = new Intent(this, AccountActivity.class);
                startActivity(accountIntent);
                break;
            case 2:
                Intent sendPromoIntent = new Intent(this, SendPromoActivity.class);
                startActivity(sendPromoIntent);
                break;
        }
    }
}
