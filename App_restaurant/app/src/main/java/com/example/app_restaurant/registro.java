package com.example.app_restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {
    private EditText cedula,nombres,apellidos,direccion,telefono,correo;
    private Button registrarse,calcelar;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cedula=(EditText)findViewById(R.id.txt_codigo_producto);
        nombres=(EditText)findViewById(R.id.txt_nombre_producto);
        apellidos=(EditText)findViewById(R.id.txt_precio_producto);
        direccion=(EditText)findViewById(R.id.txt_stock_producto);
        telefono=(EditText)findViewById(R.id.txt_telefono);
        correo=(EditText)findViewById(R.id.txt_correo);

        registrarse=(Button)findViewById(R.id.btn_registrarse);
        calcelar=(Button)findViewById(R.id.btn_cancelar_registro);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServico("http://192.168.0.105/restaurant/cli_registrar.php");
            }
        });
        calcelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancelar();
            }
        });

    }
    public void Cancelar(){
        Intent canc=new Intent(this,MainActivity.class);
        startActivity(canc);
        cedula.setText("");
        nombres.setText("");
        apellidos.setText("");
        direccion.setText("");
        telefono.setText("");
        correo.setText("");

    }
    private void ejecutarServico(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("cedula",cedula.getText().toString());
                parametros.put("nombres",nombres.getText().toString());
                parametros.put("apellidos",apellidos.getText().toString());
                parametros.put("direccion",direccion.getText().toString());
                parametros.put("telefono",telefono.getText().toString());
                parametros.put("correo",correo.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void buscar(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        cedula.setText(jsonObject.getString("cedula"));
                        nombres.setText(jsonObject.getString("nombres"));
                        apellidos.setText(jsonObject.getString("apellidos"));
                        direccion.setText(jsonObject.getString("direccion"));
                        telefono.setText(jsonObject.getString("telefono"));
                        correo.setText(jsonObject.getString("correo"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No se logro encontrar el registro",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void actualizar(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("cedula",cedula.getText().toString());
                parametros.put("nombres",nombres.getText().toString());
                parametros.put("apellidos",apellidos.getText().toString());
                parametros.put("direccio",direccion.getText().toString());
                parametros.put("telefono",telefono.getText().toString());
                parametros.put("correo",correo.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void borrar(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}