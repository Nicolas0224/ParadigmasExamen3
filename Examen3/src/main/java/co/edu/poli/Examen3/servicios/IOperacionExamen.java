package co.edu.poli.Examen3.servicios;

import co.edu.poli.Examen3.modelo.Examen;

public interface IOperacionExamen {

    String crear(Examen examen) throws Exception;
    Examen[] consultarTodos();
    String serializar(String nombreArchivo) throws Exception;
    Examen[] deserializar(String nombreArchivo) throws Exception;
}