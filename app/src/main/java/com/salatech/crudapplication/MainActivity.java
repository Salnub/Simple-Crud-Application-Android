package com.salatech.crudapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnUpdate, btnDelete, btnView;

    EditText txtName, txtContact, txtDob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName  = findViewById(R.id.txtName);
        txtContact = findViewById(R.id.txtContact);
        txtDob = findViewById(R.id.txtDob);

        btnInsert =  findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);

        DBHelper DB = new DBHelper(this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String contact = txtContact.getText().toString();
                String dob = txtDob.getText().toString();

                Boolean checkDBop = DB.insertUserData(name,contact,dob);
                if (checkDBop == true){
                    Toast.makeText(MainActivity.this,"New Record Inserted Successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Unable to insert Record",Toast.LENGTH_LONG).show();
                }

            }
        });
        //Update OnClick Listener
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String contact = txtContact.getText().toString();
                String dob = txtDob.getText().toString();

                Boolean checkupdate = DB.updateUserData(name,contact,dob);
                if (checkupdate == true){
                    Toast.makeText(MainActivity.this,"Record Updated Successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Unable to Update Record",Toast.LENGTH_LONG).show();
                }

            }
        });
        //Delete OnClick Listener
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();


                Boolean checkdelete = DB.deleteUserData(name);
                if (checkdelete == true){
                    Toast.makeText(MainActivity.this,"Record Deleted Successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Unable to Delete Record",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor records = DB.viewUserData();
                if (records.getCount()==0){
                    Toast.makeText(MainActivity.this,"No DATA Found",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer allRecords = new StringBuffer();
                while (records.moveToNext()){
                    allRecords.append("SN"+records.getString(0)+"\n");
                    allRecords.append("Name:"+records.getString(1)+"\n");
                    allRecords.append("Contact:"+records.getString(2)+"\n");
                    allRecords.append("Date of  Birth:"+records.getString(3)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("View All Records");
                builder.setMessage(allRecords.toString());
                builder.show();
            }
        });

    }
}