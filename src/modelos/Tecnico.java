package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Tecnico implements Serializable {

    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    private String clave;
    private String email;
    private ArrayList<Incidencia> incidencias;

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

    public ArrayList<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(ArrayList<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    //Constructor
    public Tecnico(){}

    public Tecnico(String nombre, String apellido, String clave, String email) {
        this.id = (((int) (Math.random() * 3000000)) + 2000001);
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.email = email;
        this.incidencias = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "\n╔═════════════════════════════════════════════════════════════════════╗" + "\n" +
                " ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡   TECNICO   ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡  " + "\n" +
                "    Técnico con id: " + id + "\n" +
                "    Nombre: " + nombre + "\n" +
                "    Apellido: " + apellido + "\n" +
                "    Email: " + email + "\n" +
                "╚═════════════════════════════════════════════════════════════════════╝";
    }

    //Métodos
    //Buscar incidencia por id
    public Incidencia buscaIncidenciaPorID (int id) {
        for (Incidencia incidencia : incidencias) {
            if (incidencia.getId() == id) {
                return incidencia;
            }
        }
        return null;
    }

    //Buscar incidencia por término
    public ArrayList<Incidencia> buscaIncidenciaPorTermino(String descripcion) {
        ArrayList<Incidencia> incidenciasEncontradas = new ArrayList<>();
        for (Incidencia incidencia : incidencias) {
            if (incidencia.getDescripcion().contains(descripcion)) {
                incidenciasEncontradas.add(incidencia);
            }
        }
        return incidenciasEncontradas;
    }

    //Contar incidencias abiertas
    public int buscaIncidenciasAbiertas() {
        int contador = 0;
        for (Incidencia incidencia : incidencias) {
            if (!incidencia.isEstaResuelta()) {
                contador++;
            }
        }
        return contador;
    }

    //Contar incidencias cerradas
    public int buscaIncidenciasCerradas() {
        int contador = 0;
        for (Incidencia incidencia : incidencias) {
            if (incidencia.isEstaResuelta()) {
                contador++;
            }
        }
        return contador;
    }

    //Calcular prioridad media de las incidencias
    public float prioridadMediaTecnico() {
        int sumaPrioridad = 0, numeroIncidencias = 0, media = 0;

        for (Incidencia incidencia : incidencias) {
            sumaPrioridad += incidencia.getPrioridad();
            numeroIncidencias++;
        }

        if (sumaPrioridad > 0 && numeroIncidencias > 0) {
            media = sumaPrioridad / numeroIncidencias;
        }

        return media;
    }

    //Cerrar incidencia
    public boolean cierraIncidencia(int id, String solucion) {
        for (Incidencia incidencia: incidencias) {
            if (incidencia.getId() == id) {
                incidencia.setSolucion(solucion);
                incidencia.setFechaFin(new Date());
                incidencia.setEstaResuelta(true);
                return true;
            }
        }
        return false;
    }

    //Asignar incidencia
    public void asignaIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
    }
}
