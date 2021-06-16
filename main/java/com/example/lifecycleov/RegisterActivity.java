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
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText username, name, address, number, city, phone, email;
    Button btn_reg;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.editTextTextPersonName);
        address = findViewById(R.id.editTextTextPostalAddress);
        number = findViewById(R.id.editTextNumber);
        city = findViewById(R.id.editTextTextPersonCity);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextTextEmailAddress);
        username = findViewById(R.id.username);
        btn_reg = findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(this);
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
                return  super.onOptionsItemSelected(item);
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void CheckData(){
        if(isEmpty(name)){
            Toast toast = Toast.makeText(this, "Fyll i ditt fullständiga namn", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(isEmpty(address)){
           address.setError("Fyll i din adress");
        }
        if(isEmpty(number)){
            number.setError("Fyll i postnummer");
        }
        if(isEmpty(city)){
            city.setError("Fyll i stad");
        }
        if(isEmpty(phone)){
            phone.setError("Fyll i telefonummer");
        }
        if(isEmpty(username)){
            username.setError("Fyll i ditt användare namn");
        }
        if(isEmpty(email) == false){
            email.setError("Fyll i rätt epost");
        }
    }

    public void onClick(View view) {
//        Log.d("TAG", "onClick: reg" + name.getText().toString());
        CheckData();
        Intent intent = new Intent(this, SaveActivity.class);
        sharedPreferences = this.getSharedPreferences("name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString("name", name.getText().toString());
       editor.putString("address", address.getText().toString());
       editor.putString("number", number.getText().toString());
       editor.putString("city", city.getText().toString());
       editor.putString("phone", phone.getText().toString());
       editor.putString("email", email.getText().toString());
       editor.putString("username", username.getText().toString());
       editor.apply();
       startActivity(intent);
       finish();
//       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            sharedPreferences.getAll().forEach((key, value)->{
//                Log.d("TAG", "updateTextView: Reg " +key + value + sharedPreferences);
//            });
//        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save stuff
        outState.putString("name", name.getText().toString());
        outState.putString("phone", phone.getText().toString());
        outState.putString("address", address.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Get what is saved
        name.setText(savedInstanceState.getString("name"));
        phone.setText(savedInstanceState.getString("phone"));
        address.setText(savedInstanceState.getString("address"));
    }

}