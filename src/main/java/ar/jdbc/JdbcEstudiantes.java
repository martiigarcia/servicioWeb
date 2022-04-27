package ar.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import ar.model.Estudiante;
import ar.servicios.Estudiantes;

public class JdbcEstudiantes implements Estudiantes {

 //constructor con dependencias
 
 @Override
 public List<Estudiante> estudiantes(String apellido) {
  Estudiante e1 = new Estudiante("Martina", "Garcia");
  Estudiante e2 = new Estudiante("Nat", "Ramanosh");
  Estudiante e3 = new Estudiante("Wanda", "Maxinoff");


  e1.addCurso("React");
  e1.addCurso("Angular");
  e2.addCurso("IPV6");
  e3.addCurso("Bases de datos");
  e3.addCurso("React");
  
  var estudiante = List.of(e1, e2, e3);
  
  if (apellido == null || apellido.isEmpty())
   return estudiante;
  
  return estudiante.stream().filter((e) -> {
   return e.containsApellido(apellido);
  }).collect(Collectors.toList());
 }

 @Override
 public void crearEstudiante(String nombre, String apellido,
   String[] cursos) {

  Estudiante e = new Estudiante(nombre, apellido);

  
  System.out.println(e.toString());
  System.out.println(cursos);
 }
}
