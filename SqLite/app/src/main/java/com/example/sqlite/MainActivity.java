package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText cedula, nombre, telefono;
    Button registrar, consultar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cedula = (EditText) findViewById(R.id.edt_cedula);
        nombre = (EditText) findViewById(R.id.edt_nombre);
        telefono = (EditText) findViewById(R.id.edt_telefono);
        registrar = (Button) findViewById(R.id.btn_registrar);
        consultar = (Button) findViewById(R.id.btn_consultar);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdminBD Admin= new AdminBD (MainActivity.this);
                SQLiteDatabase Base = Admin.getWritableDatabase();

                String id = cedula.getText().toString();
                String name = nombre.getText().toString();
                String phone = telefono.getText().toString();

                if (!id.isEmpty() && !name.isEmpty() && !phone.isEmpty() ){

                    ContentValues regis = new ContentValues();
                    regis.put("cedula", id);
                    regis.put("nombre", name);
                    regis.put("telefono", phone);

                    Base.insert("usuario", null, regis);

                    Base.close();

                    Toast.makeText(getApplicationContext(), "Datos almacenados", Toast.LENGTH_SHORT).show();
                     limpiar();
                }
                else {

                    Toast.makeText(getApplicationContext(), "por favor ingrese correctamente todos los datos", Toast.LENGTH_SHORT).show();

                }

            }
        });


        }

    private void limpiar(){
        cedula.setText(" ");
        nombre.setText(" ");
        telefono.setText(" ");



        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminBD Admin = new AdminBD (MainActivity.this);
                SQLiteDatabase BasedeDatos = Admin.getWritableDatabase();
                String ide = cedula.getText().toString();
                if (!ide.isEmpty()){

                    //variable de tipo cursos para que cargue la informacón que se va a mostrar
                    Cursor fila = BasedeDatos.rawQuery("SELECT nombre, telefono FROM usuario WHERE cedula=" +ide,null);
                    //moveToFirst es para que mueva los registros en orden
                     if (fila.moveToFirst()){


                         nombre.setText(fila.getString(0));
                         telefono.setText(fila.getString(1));

                     }
                     else {

                         Toast.makeText(MainActivity.this, "no se encontro el usuario ingresado", Toast.LENGTH_SHORT).show();

                     }

                }
            }
        });

    }
}