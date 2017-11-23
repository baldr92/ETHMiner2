package com.example.fire.ethminer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;

public class AccountActivity extends Activity {

    SharedPreferences sharedPreferences;
    final String SAVED_WALLET = "saved_wallet";

    @BindView(R.id.address_eth)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        save_eth_wallet();
        load_eth_wallet();
    }

    void save_eth_wallet() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_WALLET, editText.getText().toString());
        editor.commit();
        Toast.makeText(this, "Кошелек сохранен", Toast.LENGTH_SHORT).show();
    }

    void load_eth_wallet() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String save_wallet = sharedPreferences.getString(SAVED_WALLET, "");
        editText.setText(save_wallet);
    }

}
