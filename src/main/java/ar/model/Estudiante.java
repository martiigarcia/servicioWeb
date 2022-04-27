package ar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estudiante {

 private String nombre;
 private String apellido;
 private List<Curso> cursos;



 public Estudiante(String nombre, String apellido) {


  var check = new NotNullNotEmpty("nombre", nombre, "apellido", apellido);
  check.throwOnError();

  this.nombre = nombre;
  this.apellido = apellido;
  this.cursos = new ArrayList<>();
 }



 public void addCurso(String curso) {
  var check = new NotNullNotEmpty("curso", curso);
  check.throwOnError();
  this.cursos.add(new Curso(curso));
 }

 public void addCursos(String[] nombresCursos) {

  String[] cursos = nombresCursos;
  
  var cur = List.of(cursos).stream().map((n) -> {
   return new Curso(n);
  }).collect(Collectors.toList());
  
  this.cursos.addAll(cur);
 }
 
 public String nombre() {
  return nombre + " " + apellido;
 }

 @Override
 public String toString() {
  return "estudiante [nombre=" + nombre + ", apellido=" + apellido
    + ", cursos=" + cursos + "]";
 }

 public Map<String, Object> toMap() {
  var map = new HashMap<String, Object>(
    Map.of("nombre", nombre, "apellido", apellido));


  if (this.cursos != null && this.cursos.size() > 0) {
   map.put("cursos", cursos.stream().map((e) -> e.toMap())
     .collect(Collectors.toList()));
  }
  return map;
 }

 public boolean containsApellido(String apellido) {
  return this.apellido.contains(apellido);
 }
}
