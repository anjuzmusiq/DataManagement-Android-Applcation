package com.example.datamanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,contact,dob;
    Button insert,update,delete,view;
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        dob=findViewById(R.id.dob);

        insert=findViewById(R.id.btninsert);
        delete=findViewById(R.id.btndelete);
        update=findViewById(R.id.btnupdate);
        view=findViewById(R.id.btnview);
        db=new DbHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String c=contact.getText().toString();
                String d=dob.getText().toString();
                Boolean checkinsertdata=db.insertdetail(n,c,d);
                if(checkinsertdata==true) {
                    Toast.makeText(MainActivity.this, "Data inserted succesfully!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data insertion Failed!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String c=contact.getText().toString();
                String d=dob.getText().toString();
                Boolean checkupdatedata = db.updatedetail(n,c,d);
                if(checkupdatedata==true) {
                    Toast.makeText(MainActivity.this, "Data updated succesfully!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data updation Failed!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                Boolean checkdeletedata = db.deletedetail(n);
                if(checkdeletedata==true) {
                    Toast.makeText(MainActivity.this, "Data delete succesfully!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data deletion Failed!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getdetail();
                if(res.getCount()==0) {
                    Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    StringBuffer buffer=new StringBuffer();
                    while (res.moveToNext())
                    {
                        buffer.append("Name:"+res.getString(0)+"\n");
                        buffer.append("Contact:"+res.getString(1)+"\n");
                        buffer.append("Date of birth:"+res.getString(2)+"\n");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User details");
                    builder.setMessage(buffer.toString());
                    builder.show();

                }
            }
        });
    }
}