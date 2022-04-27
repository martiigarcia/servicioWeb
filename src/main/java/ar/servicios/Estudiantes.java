package ar.servicios;

import java.util.List;

import ar.model.Estudiante;

public interface Estudiantes {

 List<Estudiante> estudiantes(String apellido);
  
 void crearEstudiante(String nombre, String apellido,  String[] cursos);

}
