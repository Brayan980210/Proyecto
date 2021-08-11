package com.example.app_restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loby extends AppCompatActivity {
    private Button cafe,ubicacion,postres,menu,nosotros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loby);
        cafe=(Button)findViewById(R.id.btn_caffe);
        ubicacion=(Button)findViewById(R.id.btn_ubicacion);
        postres=(Button)findViewById(R.id.btn_postre);
        menu=(Button)findViewById(R.id.btn_menu);
        nosotros=(Button)findViewById(R.id.btn_nosotros);

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cofe();
            }
        });
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubi();
            }
        });
        postres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Postres.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),menus.class);
                startActivity(intent);
            }
        });
        nosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),nosotros.class);
                startActivity(intent);
            }
        });
    }
    public void cofe(){
        Intent caf=new Intent(this,Coffes.class);
        startActivity(caf);
    }
    public void ubi(){
        Intent ub=new Intent(this,ubicacion.class);
        startActivity(ub);
    }
}