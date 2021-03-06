package com.example.waiterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waiterapp.ui.home.HomeFragment;

public class adminprueba extends AppCompatActivity {

    private ItemMenu Item;
    Button btnImagen, btnGuardar;
    TextView lbTitulo;
    EditText ediproducto,edidescripcion,ediprecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminprueba);

        btnImagen = findViewById(R.id.btnproducto);
        btnGuardar = findViewById(R.id.btnGuardar);
        lbTitulo= findViewById(R.id.lbTitulo);
        ediproducto = findViewById(R.id.ediproducto);
        edidescripcion = findViewById(R.id.edidescripcion);
        ediprecio = findViewById(R.id.ediprecio);

        Item = (ItemMenu) getIntent().getSerializableExtra("ObjetoE");

        btnImagen.setBackgroundResource(Item.getImgFoto());
        lbTitulo.setText(Item.getTitulo());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar(ediproducto.getText().toString(),edidescripcion.getText().toString(),ediprecio.getText().toString());
                //Intent I = new Intent(getApplicationContext(), HomeFragment.class);
                //startActivity(I);
            }
        });


    }

    private void Editar( String nombre, String descripcion, String precio){

        DbHelper helper = new DbHelper(this,"BD",null,1);
        SQLiteDatabase db= helper.getWritableDatabase();

        String SQL = "Update Menu set Nombre = '"+ nombre +"', Descripcion = '"+descripcion+"', Precio = '"+descripcion+"' Where Nombre= " + nombre;

        db.execSQL(SQL);
        db.close();

    }


    private void Guardar (String nombre, String descripcion, String precio){

        DbHelper2 helper = new DbHelper2(this,"BD2",null,1);
        SQLiteDatabase db= helper.getWritableDatabase();

        try{

            ContentValues cv = new ContentValues();
            cv.put("Nombre", nombre);
            cv.put("Descripcion", descripcion);
            cv.put("Precio", precio);


            db.insert("Pedido",null,cv);
            db.close();
            Toast.makeText(this, "Pedido Registrado Correctamente",Toast.LENGTH_LONG).show();
            Intent I = new Intent(getApplicationContext(), HomeFragment.class);
            startActivity(I);

        }catch(Exception ex)
        {

            Toast.makeText(this,"Error"+ex.getMessage(), Toast.LENGTH_LONG).show();

        }




    }



}