package com.example.lifecycleov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity implements View.OnClickListener{

    TextView username, name, address, number, city, phone, email;
    Button btn_logout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        btn_logout = findViewById(R.id.btn_logout);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        number = findViewById(R.id.number);
        city = findViewById(R.id.city);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        btn_logout.setOnClickListener(this);

        updateTextView();
        Log.d("TAG", "onCreate: N" + name.getText().toString() + username.getText().toString());

//        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        //name.getText().toString();
//        Log.d("TAG", "onCreate: G" + name + sharedPreferences);
//        Log.d("TAG", "onCreate: S" + name + sharedPreferences);
//        String name = sharedPreferences.getString("name", "no");
//        name.setText(name);
//        Log.d("TAG", "onCreate: N" +name+ name + sharedPreferences);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sharedPreferences.getAll().forEach((key, value)->{
                Log.d("TAG", "updateTextView: Save" +key + value);
            });
        }

    }

    public void updateTextView(){
        sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            sharedPreferences.getAll().forEach((key, value)->{
//                Log.d("TAG", "updateTextView: " +key + value);
//            });
//        }
        String names = sharedPreferences.getString("name", "no");
        String addresses = sharedPreferences.getString("address", "no");
        String numbers = sharedPreferences.getString("number", "no");
        String citys = sharedPreferences.getString("city", "no");
        String phones = sharedPreferences.getString("phone", "no");
        String emails = sharedPreferences.getString("email", "no");
        String usernames = sharedPreferences.getString("username", "no");

        name.setText(names);
        address.setText(addresses);
        number.setText(numbers);
        city.setText(citys);
        phone.setText(phones);
        email.setText(emails);
        username.setText(usernames);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reg:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(this,"reg", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.save:
                intent = new Intent(this, SaveActivity.class);
                startActivity(intent);
                Toast.makeText(this,"save", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "Du är utloggad", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}