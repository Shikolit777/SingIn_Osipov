package com.example.singin_osipov;

import static com.example.singin_osipov.Common.AlertDialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

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
    ArrayList<DataUser> dataUser = new ArrayList<>();

    public class GetDataUser extends AsyncTask<Void, Void, Void>
    {
        String body;
        @Override
        protected Void doInBackground(Void... params)
        {
            Document doc_b = null;
            try
            {
                doc_b = Jsoup.connect("https://0pp0site.000webhostapp.com/index.php?login="+login+"&password="+password).get();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (doc_b != null)
            {
                body = doc_b.text();
            }
            else
            {
                body = "Ошибка!";
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            try
            {
                if(body.length() != 0)
                {
                    JSONArray jsonArray = new JSONArray(body);

                    dataUser.clear();
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonRead = jsonArray.getJSONObject(i);

                        DataUser duUser = new DataUser();
                        duUser.setId(jsonRead.getString("id"));
                        duUser.setLogin(jsonRead.getString("login"));
                        duUser.setPassword(jsonRead.getString("password"));

                        dataUser.add(duUser);
                    }

                    if (dataUser.size() != 0)
                    {
                        Common.AlertDialog("Авторизация", "Пользователь авторизован",MainActivity.this);
                    }
                }
                else
                {
                    Common.AlertDialog("Авторизация", "Пользователя с таким логином или паролем не существует.",MainActivity.this);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

}

//@Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        switch (event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                start_x = (int)event.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                if (Math.abs((int)event.getX() - start_x) > 50)
//                {
//                    if (start_x < (int) event.getX())
//                    {
//                        setContentView(R.layout.singin);
//                    }
//                    else
//                    {
//                        setContentView(R.layout.regin);
//                    }
//                }
//        }
//        return false;
//    }