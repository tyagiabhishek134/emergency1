package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    Button b1,b2,b3;
    EditText e1;
    ListView listview;
    SQLiteDatabase sqlitedb;
    SQLiteOpenHelper s1;
    DatabaseHandler myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=findViewById(R.id.phone);
        b1=findViewById(R.id.add);
        b2=findViewById(R.id.delete);
        b3=findViewById(R.id.view);
        myDB=new DatabaseHandler(this);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                String sr=e1.getText().toString();
                addData(sr);
                Toast.makeText(Register.this,"Data added", Toast.LENGTH_SHORT).show();
                e1.setText("");
            }

        }

        b2.setOnClickListener(new View.OnClickListener() {

                                  @Override
                                  public void onClick(View v) {

                                      sqlitedb = myDB.getWritableDatabase();
                                      String x = e1.getText().toString();
                                      DeleteData(x);
                                      Toast.makeText(Register.this, "Data Deleted", Toast.LENGTH_SHORT).show();


                                  }
                              }




        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();

            }
        })
    }
    private void loadData(){
        ArrayList<String> theList = new ArrayList<>();
        Cursor data =myDB.getListContents();
        if(data.getCount()==0){
            Toast.makeText(Register.this, "THERE IS NO CONTENT",Toast.LENGTH_SHORT).show();
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listview.setAdapter(listAdapter);
            }
        }
    }
    private boolean DeleteData(String x){
        return sqlitedb.delete(DatabaseHandler.TABLE_NAME,DatabaseHandler.COL2 + "=?", new String[]{x})>0;

    }
    private void addData(String sr){
        boolean insertData = myDB.addDATA( newEntry );
        if(insertData==true){
            Toast.makeText(Register.this,"Data Added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Register.this,"Unsucessful",Toast.LENGTH_SHORT).show();
        }

    }


}