package modelos;

import java.util.Date;

public class IncidenciaDataClass {

    //Atributos
    private int id;
    private String descripcion;
    private String solucion;
    private int prioridad;
    private boolean estaResuelta;
    private Date fechaInicio;
    private Date fechaFin;
    private int idUsuario;
    private int dias;
    private String nombreUsuario;
    private String emailUsuario;
    private int idTecnico;
    private String nombreTecnico;

    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isEstaResuelta() {
        return estaResuelta;
    }

    public void setEstaResuelta(boolean estaResuelta) {
        this.estaResuelta = estaResuelta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    //Constructor
    public IncidenciaDataClass(int id, String descripcion, String solucion, int prioridad, boolean estaResuelta, Date fechaInicio, Date fechaFin, int dias, int idUsuario, String nombreUsuario, String emailUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.prioridad = prioridad;
        this.estaResuelta = estaResuelta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.dias = dias;
    };

    public IncidenciaDataClass(int id, String descripcion, String solucion, int prioridad, boolean estaResuelta, Date fechaInicio, Date fechaFin,int dias, int idUsuario, String nombreUsuario, String emailUsuario, int idTecnico, String nombreTecnico) {
        this.id = id;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.prioridad = prioridad;
        this.estaResuelta = estaResuelta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idUsuario = idUsuario;
        this.dias = dias;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.idTecnico = idTecnico;
        this.nombreTecnico = nombreTecnico;
    }

    @Override
    public String toString() {
        return "\n╔═════════════════════════════════════════════════════════════════════╗" + "\n" +
                " ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡   INCIDENCIA   ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡  " + "\n" +
                "    Incidencia con id: " + id + "\n" +
                "    Comentarios del usuario: " + descripcion + "\n" +
                "    Prioridad: " + prioridad + "\n" +
                "    Solución: " + ((solucion == null) ? "No existe solución aún" : solucion) + "\n" +
                "    Resuelta: " + ((estaResuelta) ? "Resuelta" : "Sin resolver") + "\n" +
                "    Fecha de creación: " + fechaInicio + "\n" +
                "    Fecha en la que se cerró: " +((estaResuelta) ? fechaFin : "Aún no se ha cerrado") + "\n" +
                "    Dias desde que se abrió: " + dias + "\n" +
                "    Nombre del usuario que la creó: " + ((nombreUsuario == null) ? "No hay dato usuario" : nombreUsuario) + "\n" +
                "    Correo del usuario que la creó: " + ((emailUsuario == null) ? "No hay dato usuario" : emailUsuario) + "\n" +
                "    Nombre del tecnico asignado: " + ((nombreTecnico == null) ? "No hay dato tecnico" : nombreTecnico) + "\n" +
                "╚═════════════════════════════════════════════════════════════════════╝";

    }
}
