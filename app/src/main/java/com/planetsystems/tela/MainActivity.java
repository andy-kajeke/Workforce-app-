package com.planetsystems.tela;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.planetsystems.tela.DataStorage.DatabaseHelper;
import com.planetsystems.tela.Models.SystemUsers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CardView show;
    ArrayList<SystemUsers> markList;
    TaskAdapter adapter;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATABASE_ID = "database_id";
    public static final String COLUMN_EMPLOYEE_NUMBER = "employeeNumber";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_SCHOOL_ID = "school_id";
    public static final String COLUMN_SCHOOL_NAME = "schoolName";
    public static final String COLUMN_DEVICE_ID = "device_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        show = (CardView) findViewById(R.id.cardview3);

        markList = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = dbHelper.school_Name();

        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                Toast.makeText(getApplicationContext(), "SchoolName:" + cursor.getString(cursor.getColumnIndex(SystemUsers.COLUMN_SCHOOL_NAME)), Toast.LENGTH_LONG).show();
            }
        }

        adapter= new TaskAdapter(getApplicationContext(),R.layout.school_name_view,markList);

        listView.setAdapter(adapter);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHelper.school_Name();
                while (cursor.moveToNext()){

                    System.out.println("=========================");
                    System.out.println(cursor.getString(cursor.getColumnIndex(SystemUsers.COLUMN_SCHOOL_NAME)));
                    System.out.println("=========================");
                }
            }
        });

        //readFromLocalStorage();
        new Fetch_API_JSONAsyncTask().execute("http://tela.planetsystems.co:8080/weca/webapi/tasks/siteemployees/019");

    }

    class Fetch_API_JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(String... urls) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("id","cmd"));
                URI uri = new URI(urls[0] + "?" + URLEncodedUtils.format(params, "utf-8"));
                HttpGet httppost = new HttpGet(uri);
                HttpClient httpclient = new DefaultHttpClient();


                HttpResponse response = httpclient.execute(httppost);
                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONArray jarray = new JSONArray(data);


                    for(int i = 0; i < jarray.length(); i++) {

                        JSONObject object = jarray.getJSONObject(i);
                        SystemUsers mark_List = new SystemUsers();

                        mark_List.setDatabase_id(object.getString("database_id"));
                        mark_List.setEmployeeNumber(object.getString("employeeNumber"));
                        mark_List.setFirstName(object.getString("firstName"));
                        mark_List.setLastName(object.getString("lastName"));
                        mark_List.setRole(object.getString("role"));
                        mark_List.setSchool_id(object.getString("school_id"));
                        mark_List.setSchoolName(object.getString("schoolName"));
                        mark_List.setDevice_id(object.getString("device_id"));

                        ContentValues cv = new ContentValues();

                        cv.put(COLUMN_DATABASE_ID, mark_List.getId());
                        cv.put(COLUMN_EMPLOYEE_NUMBER, mark_List.getEmployeeNumber());
                        cv.put(COLUMN_FIRST_NAME, mark_List.getFirstName());
                        cv.put(COLUMN_LAST_NAME, mark_List.getLastName());
                        cv.put(COLUMN_ROLE, mark_List.getRole());
                        cv.put(COLUMN_SCHOOL_ID, mark_List.getSchool_id());
                        cv.put(COLUMN_SCHOOL_NAME, mark_List.getSchoolName());
                        cv.put(COLUMN_DEVICE_ID, mark_List.getDevice_id());
//
                        db.insert(SystemUsers.TABLE_USERS, null, cv);
//                        db.close();
                    }

                    return true;
                }else {

                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (URISyntaxException es) {
                es.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result) {

            if(result == false){
                Toast.makeText(getApplicationContext(), "Unable to fetch tasks List  List from server", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(), "inserted successfully", Toast.LENGTH_LONG).show();
            }
        }
    }

}
