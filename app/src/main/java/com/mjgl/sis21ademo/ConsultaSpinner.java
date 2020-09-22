package com.mjgl.sis21ademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.util.ArrayList;

public class ConsultaSpinner extends AppCompatActivity{
    private Spinner sp_options;
    private TextView tv_cod, tv_descripcion, tv_precio;

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    modal_Toast_Custom modal = new modal_Toast_Custom();

    private FloatingActionMenu fabmenu;
    private FloatingActionButton item1, item2, item3;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_close)
                    .setTitle("Warning")
                    .setMessage("¿Realmente desea salir?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*Intent intent = new Intent(DashboardLuces.this, luces_control_sms.class);
                            startActivity(intent);*/
                            //MainActivity.this.finishAffinity();
                            finish();
                        }
                    })
                    .show();
            // Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
            return true;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);

        sp_options = (Spinner)findViewById(R.id.sp_options);
        tv_cod = (TextView)findViewById(R.id.tv_cod);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_precio = (TextView)findViewById(R.id.tv_precio);


        fabmenu = findViewById(R.id.menu_fab);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);

        fabmenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if(opened){
                    Toast.makeText(ConsultaSpinner.this, "Abierto", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ConsultaSpinner.this, "Cerrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fabmenu.isOpened()){
                    fabmenu.close(true);
                }
            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();
                Intent intent = new Intent(ConsultaSpinner.this, MainActivity.class);
                startActivity(intent);*/
                modal.dialogConfirmCustom2(ConsultaSpinner.this);
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modal.dialogAbout(ConsultaSpinner.this);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConsultaSpinner.this, "Aplicaciòn cerrada.", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });


        /*op1.setOnClickListener(onButtonClick());
        op2.setOnClickListener(onButtonClick());
        op3.setOnClickListener(onButtonClick());*/

        /*op1.setOnClickListener(this);
        op2.setOnClickListener(this);
        op3.setOnClickListener(this);*/



        //conexion.obtenerListaArticulos();
        conexion.consultaListaArticulos();

        //ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaArticulos);
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, conexion.obtenerListaArticulos());
        sp_options.setAdapter(adaptador);

        sp_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position!=0){
                    tv_cod.setText("Código: "+conexion.consultaListaArticulos().get(position-1).getCodigo());
                    tv_descripcion.setText("Descripción: "+conexion.consultaListaArticulos().get(position-1).getDescripcion());
                    tv_precio.setText("Precio: "+String.valueOf(conexion.consultaListaArticulos().get(position-1).getPrecio()));
                }else{
                    tv_cod.setText("Código: ");
                    tv_descripcion.setText("Descripción: ");
                    tv_precio.setText("Precio: ");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    /*private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == op1){

                } else if(v == op2){

                } else if(v == op3){

                }
                fabmenu.close(true);
            }
        };
    }*/

}
