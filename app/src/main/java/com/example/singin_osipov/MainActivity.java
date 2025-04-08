package com.example.singin_osipov;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singin);
    }
    public int start_x = 0;
    public String login, password;
    public void onAuthorization(View view)
    {
        TextView tv_login = findViewById(R.id.editTextTextPersonName);
        login = tv_login.getText().toString();

        TextView tv_password = findViewById(R.id.editTextTextPersonName2);
        password = tv_password.getText().toString();

        GetDataUser gdu = new GetDataUser();
        gdu.execute();
    }
    public class DataUser {
        public String id;
        public String login;
        public  String password;

        public void setId(String _id) {this.id = _id;}
        public String getID() {return this.id;}

        public void setLogin(String _login) {this.login = _login;}
        public String getLogin() {return this.login;}

        public void setPassword(String _password) {this.password = _password;}
        public String getPassword() {return this.password;}
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                start_x = (int)event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs((int)event.getX() - start_x) > 50)
                {
                    if (start_x < (int) event.getX())
                    {
                        setContentView(R.layout.singin);
                    }
                    else
                    {
                        setContentView(R.layout.regin);
                    }
                }
        }
        return false;
    }
}