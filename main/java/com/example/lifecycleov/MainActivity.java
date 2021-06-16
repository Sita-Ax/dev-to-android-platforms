package com.example.lifecycleov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText username, password;
    Button btn_login;
    TextView account, reg;
    SharedPreferences sharedPreferences;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        account = findViewById(R.id.accout);
        reg = findViewById(R.id.reg);

        btn_login.setOnClickListener(this);
        account.setOnClickListener(this);
        reg.setOnClickListener(this);

        checkBox = (CheckBox)findViewById(R.id.checkBox);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
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
                return super.onOptionsItemSelected(item);
        }
    }

    private void bindID() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Log.d("TAG", "bindID: " +username.getId()+password.getText().toString());
    }

    public void onClick(View view) {
        Log.d("TAG", "onClick: viewOn" + username.getText().toString() + sharedPreferences);
        switch (view.getId()){
            case R.id.btn_login:
                if(username.getText().toString().equals("root")&& password.getText().toString().equals("login")) {
                    Intent intent = new Intent(this, SaveActivity.class);
                    sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    bindID();
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.putBoolean("username", true);
                    editor.remove("password");
                    editor.commit();
                    editor.apply();
                    startActivity(intent);
                    Log.d("TAG", "onClick: inmain" + username.getId() + username.getText().toString());
                    editor.clear();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Användare namn och lösenord är fel", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }
                break;
            case R.id.accout:
                startActivity(new Intent(this, AccountActivity.class));
                break;
            case R.id.reg:
                sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
                Log.d("TAG", "onCreate: SS" + sharedPreferences);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    sharedPreferences.getAll().forEach((key, value)->{
                        Log.d("TAG", "updateTextView: Save" +key +"nnnn"+ value + username.getText().toString() + username.getId());
                    });
                }
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

}
