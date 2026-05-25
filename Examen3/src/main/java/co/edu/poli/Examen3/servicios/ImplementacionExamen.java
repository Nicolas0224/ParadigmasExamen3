package co.edu.poli.Examen3.servicios;

import co.edu.poli.Examen3.modelo.Examen;
import java.io.*;

public class ImplementacionExamen implements IOperacionExamen {

    private Examen[] listaExamenes;
    private int cantidad;

    public ImplementacionExamen() {
        listaExamenes = new Examen[2];
        cantidad = 0;
    }

    public void setListaExamenes(Examen[] listaExamenes) {
        this.listaExamenes = listaExamenes;
        this.cantidad = 0;
        for (int i = 0; i < listaExamenes.length; i++) {
            if (listaExamenes[i] != null) cantidad++;
        }
    }

    @Override
    public String crear(Examen examen) throws Exception {
        if (examen == null)
            throw new Exception("El examen no puede ser nulo");
        if (examen.getCodigoIdentificacion() == null || examen.getCodigoIdentificacion().isEmpty())
            throw new Exception("El examen debe tener un codigo");
        if (examen.getNombrePaciente() == null || examen.getNombrePaciente().isEmpty())
            throw new Exception("Debe ingresar el nombre del paciente");

        for (int i = 0; i < listaExamenes.length; i++)
            if (listaExamenes[i] != null &&
                listaExamenes[i].getCodigoIdentificacion().equals(examen.getCodigoIdentificacion()))
                throw new Exception("Ya existe un examen con el codigo " + examen.getCodigoIdentificacion());

        for (int i = 0; i < listaExamenes.length; i++) {
            if (listaExamenes[i] == null) {
                listaExamenes[i] = examen;
                cantidad++;
                return "Examen registrado correctamente en la posicion " + i;
            }
        }

        // Arreglo lleno — duplicar
        Examen[] arregloAmpliado = new Examen[listaExamenes.length * 2];
        for (int i = 0; i < listaExamenes.length; i++)
            arregloAmpliado[i] = listaExamenes[i];
        listaExamenes = arregloAmpliado;

        for (int i = 0; i < listaExamenes.length; i++) {
            if (listaExamenes[i] == null) {
                listaExamenes[i] = examen;
                cantidad++;
                return "Arreglo duplicado. Examen registrado en la posicion " + i;
            }
        }
        throw new Exception("Error al registrar el examen");
    }

    @Override
    public Examen[] consultarTodos() {
        return listaExamenes;
    }

    @Override
    public String serializar(String nombreArchivo) throws Exception {
        try {
            FileOutputStream flujoSalida      = new FileOutputStream(nombreArchivo);
            ObjectOutputStream escritorObjetos = new ObjectOutputStream(flujoSalida);
            escritorObjetos.writeObject(listaExamenes);
            escritorObjetos.close();
            flujoSalida.close();
            return "Examenes guardados en " + nombreArchivo;
        } catch (IOException errorArchivo) {
            throw new Exception("Error al guardar " + errorArchivo.getMessage());
        }
    }

    @Override
    public Examen[] deserializar(String nombreArchivo) throws Exception {
        try {
            FileInputStream flujoEntrada      = new FileInputStream(nombreArchivo);
            ObjectInputStream lectorObjetos   = new ObjectInputStream(flujoEntrada);
            Examen[] examenesLeidos           = (Examen[]) lectorObjetos.readObject();
            lectorObjetos.close();
            flujoEntrada.close();
            return examenesLeidos;
        } catch (IOException errorArchivo) {
            throw new Exception("Archivo no encontrado " + errorArchivo.getMessage());
        } catch (ClassNotFoundException errorClase) {
            throw new Exception("Error al leer los datos " + errorClase.getMessage());
        }
    }
}


