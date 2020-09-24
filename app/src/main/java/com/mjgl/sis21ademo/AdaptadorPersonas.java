package com.mjgl.sis21ademo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorPersonas extends RecyclerView.Adapter<AdaptadorPersonas.PersonasViewHolder> {
    //Propiedad de la clase AdaptadorPersonas.
    List<person> persons;

    //Añadimos un contructor a partir de las propiedades definidas en esta clase (AdaptorPersonas).
    public AdaptadorPersonas(List<person> persons) {
        this.persons = persons;
    }

    /***********************************************************************************************/
    /*ReccylerView.Adapter tiene tres métodos abstractos a los que debemos aplicar el modificador override.
    /***********************************************************************************************/

    /*
    El siguiente método es llamado cuando el ViewHolder necesita ser inicializado.
    Especificamos el layout que cada elemento de RecyclerView debería usar.
    Ésto se hace al inflar el layout usando LayoutInflater, pasando el resultado
    al constructor del ViewHolder
    */
    @NonNull
    @Override
    public AdaptadorPersonas.PersonasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_recyclerview1,viewGroup, false);
        PersonasViewHolder pvh = new PersonasViewHolder(view);
        return pvh;
    }

    /*
    En el mètodo onBindViewHolder se especifica el contenido de cada elemento del RecyclerView.
    Éste método es muy similar al método getView de un adaptador de ListView.
    En nuestro ejemplo, aquí es donde se tiene que establecer los valores de los campos de nombre, edad y foto del CardView.
     */
    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonas.PersonasViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).name);
        holder.personAge.setText(persons.get(position).age);
        holder.personPhoto.setImageResource(persons.get(position).photoId);
    }


    /*
    Éste mètodo regresara el número de elementos presentes en los datos. Como nuestro datos están en forma de una List,
    sólo necesitamos llamar al método size en el objeto List:
     */
    @Override
    public int getItemCount() {
        return persons.size();
    }

    //Dentro de esta clase se define el mapeo o referencia de cada control
    //en la vista (Layout o xml) Personalizado. Este sin clase Java asociada a el.
    public class PersonasViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        //Dentro del constructor de nuestro ViewHolder, inicializa las views (vistas)
        //que pertenezcan a los elementos de nuestro RecyclerView.
        public PersonasViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }


    /*
    Finalmente, necesitamos hacer el override en el método onAttachedToRecyclerView.
    Por ahora, simplemente podemos utilizar la implementación de la superclase de éste método
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
