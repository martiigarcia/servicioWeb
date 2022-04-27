package ar.memoria;

import ar.model.Curso;
import ar.servicios.Cursos;

import java.util.List;
import java.util.stream.Collectors;


public class MemoriaCursos implements Cursos {

 @Override
 public List<Curso> cursos(String nombre) {
     Curso c1=(new Curso("React"));
     Curso c2=(new Curso("Angular"));
     Curso c3=(new Curso("Seminario"));
     Curso c4=(new Curso("Ingenieria"));
     Curso c5=(new Curso("Bases de datos"));
     Curso c6=(new Curso("IPV6"));
     Curso c7=(new Curso("Programacion concurrente"));
     Curso c8=(new Curso("Programacion orientada a objetos"));
  var lista= List.of(c1, c2,c3,c4,c5,c6,c7,c8);

  if(nombre == null || nombre.isEmpty())
      return lista;

  return lista.stream().filter((e) -> {return e.containsNombre(nombre);}).collect(Collectors.toList());

 }

}
