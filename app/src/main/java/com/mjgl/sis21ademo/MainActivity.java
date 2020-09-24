package com.mjgl.sis21ademo;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_codigo, et_descripcion, et_precio;
    private Button btn_guardar, btn_consultar1, btn_consultar2, btn_eliminar, btn_actualizar;
    private TextView tv_resultado;

    boolean inputEt=false;
    boolean inputEd=false;
    boolean input1=false;
    int resultadoInsert=0;

    Modal ventanas = new Modal();
    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();
    AlertDialog.Builder dialogo;

    private FABToolbarLayout morph;

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
                            //finish();
                            finishAffinity();
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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor1));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("CRUD SQLite-2019");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));
        toolbar.setTitle("Prof. Gámez");
        setSupportActionBar(toolbar);

        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmacion();
            }
        });

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();
                ventanas.Search(MainActivity.this);
            }
        });*/


        FloatingActionButton fab = findViewById(R.id.fab);
        morph = findViewById(R.id.fabtoolbar);

        View uno, dos, tres, cuatro, cinco, seis;

        uno = findViewById(R.id.uno);
        dos = findViewById(R.id.dos);
        cuatro = findViewById(R.id.cuatro);
        tres = findViewById(R.id.tres);
        cinco = findViewById(R.id.cinco);
        seis = findViewById(R.id.seis);

        fab.setOnClickListener(this);
        uno.setOnClickListener(this);
        dos.setOnClickListener(this);
        tres.setOnClickListener(this);
        cuatro.setOnClickListener(this);
        cinco.setOnClickListener(this);
        seis.setOnClickListener(this);



        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_precio = (EditText) findViewById(R.id.et_precio);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        //btn_consultar1 = (Button) findViewById(R.id.btn_consultar1);
        //btn_consultar2 = (Button) findViewById(R.id.btn_consultar2);
        //btn_eliminar = (Button) findViewById(R.id.btn_eliminar);
        //btn_actualizar = (Button) findViewById(R.id.btn_actualizar);
       // tv_resultado = (TextView) findViewById(R.id.tv_resultado);

        String senal = "";
        String codigo = "";
        String descripcion = "";
        String precio = "";

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                codigo = bundle.getString("codigo");
                senal = bundle.getString("senal");
                descripcion = bundle.getString("descripcion");
                precio = bundle.getString("precio");
                if (senal.equals("1")) {
                    et_codigo.setText(codigo);
                    et_descripcion.setText(descripcion);
                    et_precio.setText(precio);
                    //finish();
                }
            }
        }catch (Exception e){

    }
}


    private void confirmacion(){
        String mensaje = "¿Realmente desea salir?";
        dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setIcon(R.drawable.ic_close);
        dialogo.setTitle("Warning");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                /*Intent intent = new Intent(DashboardLuces.this, luces_control_sms.class);
                startActivity(intent);*/
                //DashboardLuces.this.finishAffinity();
                MainActivity.this.finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                //Toast.makeText(getApplicationContext(), "Operación Cancelada.", Toast.LENGTH_LONG).show();
            }
        });
        dialogo.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_limpiar) {
            et_codigo.setText(null);
            et_descripcion.setText(null);
            et_precio.setText(null);
            return true;
        }else if(id == R.id.action_listaArticulos){
            Intent spinnerActivity = new Intent(MainActivity.this, ConsultaSpinner.class);
            startActivity(spinnerActivity);
            return true;
        }else if(id == R.id.action_listaArticulos1){
            Intent listViewActivity = new Intent(MainActivity.this, ListViewArticulos.class);
            startActivity(listViewActivity);
            return true;
        }else if(id == R.id.action_listAllArticulos){
            Intent listViewActivity = new Intent(MainActivity.this, consulta_recyclerView.class);
            startActivity(listViewActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void alta(View v) {
        if(et_codigo.getText().toString().length()==0){
            et_codigo.setError("Campo obligatorio");
            inputEt = false;
        }else {
            inputEt=true;
        }
        if(et_descripcion.getText().toString().length()==0){
            et_descripcion.setError("Campo obligatorio");
            inputEd = false;
        }else {
            inputEd=true;
        }
        if(et_precio.getText().toString().length()==0){
            et_precio.setError("Campo obligatorio");
            input1 = false;
        }else {
            input1=true;
        }
        if (inputEt && inputEd && input1){

            try {
                datos.setCodigo(Integer.parseInt(et_codigo.getText().toString()));
                datos.setDescripcion(et_descripcion.getText().toString());
                datos.setPrecio(Double.parseDouble(et_precio.getText().toString()));
                //if(conexion.insertardatos(datos)){
                //if(conexion.InsertRegister(datos)){
                if(conexion.InserTradicional(datos)){
                    Toast.makeText(this, "Registro agregado satisfactoriamente!", Toast.LENGTH_SHORT).show();
                    limpiarDatos();
                }else{
                    Toast.makeText(getApplicationContext(), "Error. Ya existe un registro\n" +
                            " Código: "+et_codigo.getText().toString(),Toast.LENGTH_LONG).show();
                    limpiarDatos();
                }

            }catch (Exception e){
                Toast.makeText(this, "ERROR. Ya existe.", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void mensaje (String mensaje){
        Toast.makeText(this, ""+mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiarDatos(){
        et_codigo.setText(null);
        et_descripcion.setText(null);
        et_precio.setText(null);
        et_codigo.requestFocus();
    }


    public void consultaporcodigo() {
        if(et_codigo.getText().toString().length()==0){
            et_codigo.setError("Campo obligatorio");
            inputEt = false;
        }else {
            inputEt=true;
        }
        if(inputEt){
            String codigo = et_codigo.getText().toString();
            datos.setCodigo(Integer.parseInt(codigo));
            //if(conexion.consultaCodigo(datos)){
            if(conexion.consultaArticulos(datos)){
                et_descripcion.setText(datos.getDescripcion());
                et_precio.setText(""+datos.getPrecio());
                //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }

        }else{
            Toast.makeText(this, "Ingrese el código del articulo a buscar.", Toast.LENGTH_SHORT).show();
        }
    }


    public void consultapordescripcion() {
        if(et_descripcion.getText().toString().length()==0){
            et_descripcion.setError("Campo obligatorio");
            inputEd = false;
        }else {
            inputEd=true;
        }
        if(inputEd){

            String descripcion = et_descripcion.getText().toString();
            datos.setDescripcion(descripcion);
            if(conexion.consultarDescripcion(datos)){
                et_codigo.setText(""+datos.getCodigo());
                et_descripcion.setText(datos.getDescripcion());
                et_precio.setText(""+datos.getPrecio());
                //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No existe un artículo con dicha descripción", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }


        }else{
            Toast.makeText(this, "Ingrese la descripción del articulo a buscar.", Toast.LENGTH_SHORT).show();
        }

    }

    public void bajaporcodigo() {

        if(et_codigo.getText().toString().length()==0){
            et_codigo.setError("campo obligatorio");
            inputEt = false;
        }else {
            inputEt=true;
        }

        if(inputEt){
            String cod = et_codigo.getText().toString();
            datos.setCodigo(Integer.parseInt(cod));
            if(conexion.bajaCodigo(MainActivity.this,datos)){
                //Toast.makeText(this, "Registro eliminado satisfactoriamente.", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }else{
                Toast.makeText(this, "No existe un artículo con dicho código.", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        }
    }


    public void modificacion() {

        if(et_codigo.getText().toString().length()==0){
            et_codigo.setError("campo obligatorio");
            inputEt = false;
        }else {
            inputEt=true;
        }

        if(inputEt) {

            String cod = et_codigo.getText().toString();
            String descripcion = et_descripcion.getText().toString();
            double precio = Double.parseDouble(et_precio.getText().toString());

            datos.setCodigo(Integer.parseInt(cod));
            datos.setDescripcion(descripcion);
            datos.setPrecio(precio);
            if(conexion.modificar(datos)){
                Toast.makeText(this, "Registro Modificado Correctamente.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada.", Toast.LENGTH_SHORT).show();
            }

        }


    }


    public void showToast(int opcion, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText(message);
        if(opcion == 1){
            toastImage.setImageResource(R.drawable.ic_save);
        }else if(opcion == 2){
            toastImage.setImageResource(R.drawable.ic_search);
        }else if(opcion == 3){
            toastImage.setImageResource(R.drawable.ic_search1);
        }else if(opcion == 4){
            toastImage.setImageResource(R.drawable.ic_delete);
        }else if(opcion == 5){
            toastImage.setImageResource(R.drawable.ic_edit);
        }



        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            morph.show();
        }//else if(v.getId() == R.id.uno){

        //}

        //morph.hide();

        switch (v.getId()){

            case R.id.uno:
                showToast(1, "Acciones para guardar en BD");
                //morph.hide();
                alta(null);
                break;

            case R.id.dos:
                showToast(2, "Busqueda por còdigo");
                ventanas.Search(MainActivity.this);
                //morph.hide();
                break;

            case R.id.tres:
                showToast(3, "Acciones para buscar por descripciòn en BD");
                //morph.hide();
                consultapordescripcion();
                break;

            case R.id.cuatro:
                showToast(4, "Acciones para elimininar de BD");
                bajaporcodigo();
                //morph.hide();
                break;

            case R.id.cinco:
                morph.hide();
                break;

            case R.id.seis:
                showToast(5, "Acciones para modificar de BD");
                modificacion();
                //morph.hide();
                break;

            default:
                morph.hide();
                break;
        }
    }
}
