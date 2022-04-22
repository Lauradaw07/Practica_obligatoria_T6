package modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin implements Serializable {
    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    private String clave;
    private String email;

    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Constructor
    public Admin(String nombre, String apellido, String clave, String email) {
        this.id = (((int) (Math.random() * 4000000)) + 3000001);
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.email = email;
    }



}
