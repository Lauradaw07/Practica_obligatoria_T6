package modelos;

import java.util.Calendar;
import java.util.Date;

public class Incidencia {
    //Atributos
    private int id;
    private String descripcion;
    private String solucion;
    private int prioridad;
    private boolean estaResuelta;
    private Date fechaInicio;
    private Date fechaFin;
    private int idUsuario;
    private boolean estaAsignada;

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

    public boolean isEstaAsignada() {
        return estaAsignada;
    }

    public void setEstaAsignada(boolean estaAsignada) {
        this.estaAsignada = estaAsignada;
    }

    @Override
    public String toString() {
        return "\n╔═════════════════════════════════════════════════════════════════════╗" + "\n" +
                " ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡   INCIDENCIA   ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡  " + "\n" +
                "    Incidencia con id: " + id + "\n" +
                "    Comentarios del usuario: " + descripcion + "\n" +
                "    Prioridad: " + prioridad + "\n" +
                "    Fecha de creación: " + fechaInicio + "\n" +
                "    Solución del técnico: " + ((solucion == null) ? "No existe solución aún" : solucion) + "\n" +
                "    Fecha en la que se cerró: " +((estaResuelta) ? fechaFin : "Aún no se ha cerrado") + "\n" +
                "    Dias desde que se abrió: " + diasAbierta() + "\n" +
                "╚═════════════════════════════════════════════════════════════════════╝";
    }

    //Constuctor
    public Incidencia(){}
    public Incidencia(String descripcion, int prioridad, int idUsuario) {
        this.id = (((int) (Math.random() * 3000000)) + 2000001);
        this.descripcion = descripcion;
        this.solucion = null;
        this.prioridad = prioridad;
        this.estaResuelta = false;
        this.fechaInicio = new Date(2022, Calendar.APRIL,1);
        this.fechaFin = null;
        this.idUsuario = idUsuario;
        this.estaAsignada = false;
    }

    //Métodos
    public int diasAbierta() {
        if (fechaFin != null) {
            return (int) ((int) fechaFin.getTime()- fechaInicio.getTime());
        }
        return 0;
    }
}
