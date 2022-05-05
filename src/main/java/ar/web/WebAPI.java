package ar.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import ar.model.Curso;
import ar.model.Estudiante;
import ar.model.EstudianteException;

import ar.servicios.Cursos;
import ar.servicios.Estudiantes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebAPI {

 private Estudiantes estudiante;
 private Cursos cursos;
 private int webPort;

 public WebAPI(Cursos cursos, Estudiantes estudiante, int webPort) {
  this.estudiante = estudiante;
  this.cursos = cursos;
  this.webPort = webPort;
 }

 public void start() {
  Javalin app = Javalin.create(config -> {
   config.enableCorsForAllOrigins();
  }).start(this.webPort);
  app.get("/estudiantes", traerEstudiantes());
  app.get("/cursos", traerCursos());
  app.post("/estudiantes", crearEstudiante());
  app.post("/inscripcion", inscribir());

  app.exception(EstudianteException.class, (e, ctx) -> {
   e.printStackTrace();
   ctx.json(Map.of("result", "error", "errors", e.toMap()));
   // log error in a stream...
  });  
  
  app.exception(Exception.class, (e, ctx) -> {
   e.printStackTrace();
   ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió.: " + e.getMessage()));
   // log error in a stream...
  });
 }

 private Handler traerCursos() {
  return ctx -> {
   String nombre = ctx.queryParam("nombre");
   var cursos = this.cursos.cursos(nombre);
   var list = new ArrayList<Map<String, Object>>();
   for(Curso c:cursos) {
    list.add(c.toMap());
   }
   ctx.json(Map.of("result", "success", "cursos", list));
  };
 }

 private Handler crearEstudiante() {
  return ctx -> {
   EstudianteDto dto = ctx.bodyAsClass(EstudianteDto.class);
   this.estudiante.crearEstudiante(dto.getNombre(), dto.getApellido(),
     dto.getCursos());
   ctx.json(Map.of("result", "success"));
  };
 }

 private Handler traerEstudiantes() {
  return ctx -> {
   String apellido = ctx.queryParam("apellido");
   List<Estudiante> estudiantes = this.estudiante.estudiantes(apellido);

   var list = new ArrayList<Map<String, Object>>();

   for (Estudiante e : estudiantes) {
    list.add(e.toMap());
   }

   ctx.json(Map.of("result", "success", "estudiantes", list));
 
  };
 }

 /*private Handler inscribir(){
  return ctx ->{
   String apellido = ctx.queryParam("apellido");
   String nombreCurso = ctx.queryParam("curso");
   System. out. println("Apellido: "+apellido+" --nombre Curso: " + nombreCurso);

   this.estudiante.inscribir( apellido, nombreCurso);
   ctx.json(Map.of("result", "success"));
  };
 }*/
 private Handler inscribir(){
  return ctx ->{
   EstudianteDto dto = ctx.bodyAsClass(EstudianteDto.class);
   System. out. println("Apellido: "+dto.getApellido()+" --nombre Curso: " + dto.getCursos()[0]);

   this.estudiante.inscribir(dto.getApellido(), dto.getCursos()[0]);
   ctx.json(Map.of("result", "success"));
  };
 }

}
