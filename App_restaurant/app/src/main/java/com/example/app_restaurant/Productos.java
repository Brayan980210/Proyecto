package com.example.app_restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class Productos extends AppCompatActivity {
    private EditText codigo,producto,precio,stock;
    private Button Guardar,Buscar,Actualizar,Borrar;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        codigo=(EditText) findViewById(R.id.txt_codigo_producto);
        producto=findViewById(R.id.txt_nombre_producto);
        precio=findViewById(R.id.txt_precio_producto);
        stock=findViewById(R.id.txt_stock_producto);
        Guardar=(Button)findViewById(R.id.btn_guardarProducto);
        Buscar=(Button)findViewById(R.id.btn_buscarProducto);
        Actualizar=(Button)findViewById(R.id.btn_actualizarProducto);
        Borrar=(Button)findViewById(R.id.btn_borrarProducto);

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G_producto("http://192.168.0.105/restaurant/pro_registrar.php");
            }
        });
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar("http://192.168.0.105/restaurant/pro_buscar.php?codigo="+codigo.getText().toString());
            }
        });

        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               actualizar("http://192.168.0.105/restaurant/pro_actualizar.php?codigo="+codigo.getText().toString());
            }
        });

        Borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar("http://192.168.0.105/restaurant/pro_borrar.php?codigo="+codigo.getText().toString());
            }
        });

    }
    private void G_producto(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registro con exito", Toast.LENGTH_SHORT).show();
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
                parametros.put("codigo",codigo.getText().toString());
                parametros.put("producto",producto.getText().toString());
                parametros.put("precio",precio.getText().toString());
                parametros.put("stock",stock.getText().toString());
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
                        codigo.setText(jsonObject.getString("codigo"));
                        producto.setText(jsonObject.getString("producto"));
                        precio.setText(jsonObject.getString("precio"));
                        stock.setText(jsonObject.getString("stock"));
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
                parametros.put("codigo",codigo.getText().toString());
                parametros.put("producto",producto.getText().toString());
                parametros.put("precio",precio.getText().toString());
                parametros.put("stock",stock.getText().toString());
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