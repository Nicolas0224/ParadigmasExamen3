package co.edu.poli.Examen3.modelo;

public class ExamenOrina extends Examen {

    private String nivelGlucosa;
    private double valorPh;

    public ExamenOrina(String codigoIdentificacion, String nombrePaciente,
                       String fechaRealizacion, double costoExamen,
                       String nivelGlucosa, double valorPh) {
        super(codigoIdentificacion, nombrePaciente, fechaRealizacion, costoExamen);
        this.nivelGlucosa = nivelGlucosa;
        this.valorPh      = valorPh;
    }

    public String getNivelGlucosa()  { return nivelGlucosa; }
    public void setNivelGlucosa(String nivelGlucosa) { this.nivelGlucosa = nivelGlucosa; }

    public double getValorPh()       { return valorPh; }
    public void setValorPh(double valorPh) { this.valorPh = valorPh; }

    @Override
    public String obtenerResumen() {
        return super.toString() +
               "Glucosa: " + nivelGlucosa +
               "\npH: " + valorPh +
               "\n__________________________";
    }

    @Override
    public String toString() {
        return obtenerResumen();
    }
}
