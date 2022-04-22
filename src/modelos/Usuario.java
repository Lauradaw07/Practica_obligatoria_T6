package modelos;

import java.util.ArrayList;

public class Usuario {
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
    public Usuario(){}

    public Usuario(String nombre, String apellido, String clave, String email) {
        this.id = (((int) (Math.random() * 2000000)) + 1000000);
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.email = email;
        this.incidencias = new ArrayList<>();
    }

    //TODO PONER BONICO
    @Override
    public String toString() {
        return "\n╔═════════════════════════════════════════════════════════════════════╗" + "\n" +
                " ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡   USUARIO   ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡  " + "\n" +
                "    Usuario con id: " + id + "\n" +
                "    Nombre: " + nombre + "\n" +
                "    Apellido: " + apellido + "\n" +
                "    Email: " + email + "\n" +
                "╚═════════════════════════════════════════════════════════════════════╝";
    }

    //Métodos
    //Insertar incidencia
    public void insertaIncidencia(Incidencia incidencia){
        incidencias.add(incidencia);
    }

    public boolean borraIncidencia(int numero) {
        for (int i = 0; i < incidencias.size(); i++) {
            if (i == (numero - 1)) {
                incidencias.remove(i);
                return true;
            }
        }
        return false;
    }

    public Incidencia buscaIncidenciaPorID (int id) {
        for (Incidencia incidencia : incidencias) {
            if (incidencia.getId() == id) {
                return incidencia;
            }
        }
        return null;
    }

    public ArrayList<Incidencia> buscaIncidenciaPorDescripcion(String descripcion) {
        ArrayList<Incidencia> incidenciasEncontradas = new ArrayList<>();
        for (Incidencia incidencia : incidencias) {
            if (incidencia.getDescripcion().contains(descripcion)) {
                incidenciasEncontradas.add(incidencia);
            }
        }
        return incidenciasEncontradas;
    }

    //Contar incidencias asignadas
    public int buscaIncidenciasAsignadas() {
        int contador = 0;
        for (Incidencia incidencia : incidencias) {
            if (incidencia.isEstaAsignada()) {
                contador++;
            }
        }
        return contador;
    }

    //Contar incidencias sin asignar
    public int buscaIncidenciasSinAsignar() {
        int contador = 0;
        for (Incidencia incidencia : incidencias) {
            if (!incidencia.isEstaAsignada()) {
                contador++;
            }
        }
        return contador;
    }

    public float prioridadMediaUsuario() {
        int sumaPrioridad = 0, numeroIncidencias = 0, media = 0;

        for (Incidencia incidencia : incidencias) {
            sumaPrioridad += incidencia.getPrioridad();
            numeroIncidencias++;
        }
        media = sumaPrioridad / numeroIncidencias;
        return media;
    }
}
