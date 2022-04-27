package ar.jdbc;

import java.util.List;

import ar.model.Curso;

import ar.servicios.Cursos;


public class JdbcCursos implements Cursos {

 @Override
 public List<Curso> cursos(String nombre) {
  return List.of(new Curso("React"), new Curso("Angular"),
          new Curso("Seminario"), new Curso("Ingenieria"),
          new Curso("Bases de datos"), new Curso("IPV6"),
          new Curso("Programacion concurrente"), new Curso("Programacion orientada a objetos"));
 }

}
