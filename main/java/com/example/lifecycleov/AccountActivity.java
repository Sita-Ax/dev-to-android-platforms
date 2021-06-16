package com.example.lifecycleov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_acc;
    private EditText username, password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        bindID();
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        btn_acc = findViewById(R.id.btn_acc);
        btn_acc.setOnClickListener(this);
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

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void onClick(View view){
        if(isEmpty(username)){
            username.setError("Skriv ditt användare namn");
        }
        if(isEmpty(password)){
            password.setError("Lösenord?");
        }
        if(username.getText().toString().equals("root")&& password.getText().toString().equals("login")) {
            Intent intent = new Intent(this, MainActivity.class);
            sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            bindID();
            editor.putString("username", username.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.commit();
            editor.apply();
            startActivity(intent);
            editor.clear();
            finish();
        }else {
            Toast toast = Toast.makeText(this, "Det fungera inte", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        }
    }

    private void bindID() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_acc = findViewById(R.id.btn_acc);
        username.getId();
        Log.d("TAG", "bindID: " + username.getId());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("username", username.getText().toString());
        outState.putString("password", password.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        username.setText(savedInstanceState.getString("username"));
        password.setText(savedInstanceState.getString("password"));
    }
}