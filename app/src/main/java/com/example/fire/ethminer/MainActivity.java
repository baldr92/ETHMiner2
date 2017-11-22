package com.example.fire.ethminer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import com.example.fire.ethminer.models.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String BASE_URL = "https://min-api.cryptocompare.com";
    ImageButton imageButton;
    boolean flag = true;
    GraphView graphView;

    private RetrofitInterfaceGraph service;
    Toast toast;

    @BindView(R.id.new_data)
    TextView textData;
    @BindView(R.id.balance)
    TextView textView;

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

        callRetrofit2();
        setGraph();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent balanceIntent = new Intent();
                balanceIntent.setClass(MainActivity.this, BalanceActivity.class);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_view:
                Intent intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                return true;
            case R.id.get_promo:
                Intent intent1 = new Intent(this, SendPromoActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   /*
    public class DrawerItemClickListener implements ListView.OnItemClickListener {
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
    */


    public void callRetrofit2() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(RetrofitInterfaceGraph.class);
        Call<Request> call = service.getPosts("ETH", "USD", 30);
        call.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Request request = response.body();
                Date date = new Date();
                date.setTime((long) request.getData().get(1).getTime()*1000);
                String s = "  ";
                textData.setText(date + s);
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
            toast.show();
            }
        });
    }

    public void setGraph() {
        graphView = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
             /* new DataPoint(0, 1),
                new DataPoint(1,3),
                new DataPoint(2, 3)
                */
        });
        graphView.addSeries(series);
    }

}