package com.example.fire.ethminer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;

public class SendPromoActivity extends AppCompatActivity {

    @BindView(R.id.quantity_number)
    TextView promo_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_promo);
        int z = new Random(System.currentTimeMillis()).nextInt(10000000 - 1) + 1;   //генерируем случайный код для отправки пользователю
        String s = "" + z;
        promo_text.setText(s);
    }

    public void sendPromoOnClick(View view){
        String send_promo_text = promo_text.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, send_promo_text);
        startActivity(intent);
    }
}