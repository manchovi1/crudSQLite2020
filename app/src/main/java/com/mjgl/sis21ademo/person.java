package com.mjgl.sis21ademo;
import java.util.ArrayList;
import java.util.List;

public class person {
        String name;
        String age;
        int photoId;

        person(String name, String age, int photoId) {
            this.name = name;
            this.age = age;
            this.photoId = photoId;
        }


    //private List<person> persons;
    // This method creates an ArrayList that has three Person objects
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images.
    /*private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new person("Manuel Gamez", "35 years old", R.drawable.computadora2));
        persons.add(new person("Daniel Gamez", "4 years old", R.drawable.computadora3));
        persons.add(new person("Alejandra Gamez", "8 years old", R.drawable.computadora1));
    }*/

}
