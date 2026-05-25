package co.edu.poli.Examen3.modelo;

import java.io.Serializable;

public abstract class Examen implements Serializable {

    private String codigoIdentificacion;
    private String nombrePaciente;
    private String fechaRealizacion;
    private double costoExamen;

    public Examen(String codigoIdentificacion, String nombrePaciente,
                  String fechaRealizacion, double costoExamen) {
        this.codigoIdentificacion    = codigoIdentificacion;
        this.nombrePaciente  = nombrePaciente;
        this.fechaRealizacion = fechaRealizacion;
        this.costoExamen     = costoExamen;
    }

    public String getCodigoIdentificacion()     { return codigoIdentificacion; }
    public void setCodigoIdentificacion(String codigoExamen) { this.codigoIdentificacion = codigoExamen; }

    public String getNombrePaciente()   { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public String getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(String fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }

    public double getCostoExamen()      { return costoExamen; }
    public void setCostoExamen(double costoExamen) { this.costoExamen = costoExamen; }

   
    public abstract String obtenerResumen();

    @Override
    public String toString() {
        return "Codigo: " + codigoIdentificacion +
               "\nPaciente: " + nombrePaciente +
               "\nFecha: " + fechaRealizacion +
               "\nCosto: " + costoExamen +
               "\n";
    }
}