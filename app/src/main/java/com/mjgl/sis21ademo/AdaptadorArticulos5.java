package com.mjgl.sis21ademo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorArticulos5 extends RecyclerView.Adapter<AdaptadorArticulos5.Articulo5ViewHolder> {

    //Propiedad de la clase AdaptadorArticulos.
    private Context mCtx;
    private List<Dto> articulosList;

    public AdaptadorArticulos5(Context mCtx, List<Dto> articulosList) {
        this.mCtx = mCtx;
        this.articulosList = articulosList;
    }

    /***********************************************************************************************/
    /*ReccylerView.Adapter tiene tres métodos abstractos a los que debemos aplicar el
    modificador override.
    /***********************************************************************************************/
    /*
    El siguiente método es llamado cuando el ViewHolder necesita ser inicializado.
    Especificamos el layout que cada elemento de RecyclerView debería usar.
    Ésto se hace al inflar el layout usando LayoutInflater, pasando el resultado
    al constructor del ViewHolder
    */
    @NonNull
    @Override
    public Articulo5ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.diseno_recyclerview, null);
        return new Articulo5ViewHolder(view);
    }


    /*
        En el mètodo onBindViewHolder se especifica el contenido de cada elemento del
        RecyclerView.
        Éste método es muy similar al método getView de un adaptador de ListView.
        En nuestro ejemplo, aquí es donde se tiene que establecer los valores de los campos de
        codigo, descripcion y precio del CardView.
    */
    @Override
    public void onBindViewHolder(@NonNull Articulo5ViewHolder holder, int position) {
        //Toast.makeText(mCtx, "Se encontraron: "+getItemCount()+ " Registros.",
        Dto dto = articulosList.get(position);
        holder.textViewCodigo1.setText(String.valueOf(dto.getCodigo()));
        holder.textViewDescripcion1.setText(dto.getDescripcion());
        holder.textViewPrecio1.setText(String.valueOf(dto.getPrecio()));
        holder.textViewOtro.setText(String.valueOf("Registro #:" + (position+1)) + "/" + getItemCount());
    }



    /*
    Éste mètodo regresara el número de elementos presentes en los datos. Como nuestro
    datos están en forma de una List,
    sólo necesitamos llamar al método size en el objeto List:
    */
    @Override
    public int getItemCount() {
        return articulosList.size();
    }


    //Dentro de esta clase se define el mapeo o referencia de cada control
    //en la vista (Layout o xml) Personalizado. Este es el diseño personalizado para pintar
    //cada registro en el recyclerview.
    public class Articulo5ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCodigo1, textViewDescripcion1, textViewPrecio1, textViewOtro;

        public Articulo5ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCodigo1 = itemView.findViewById(R.id.textViewCodigo1);
            textViewDescripcion1 = itemView.findViewById(R.id.textViewDescripcion1);
            textViewPrecio1= itemView.findViewById(R.id.textViewPrecio1);
            textViewOtro = itemView.findViewById(R.id.textViewOtro);
            //imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
