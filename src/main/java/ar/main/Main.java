package ar.main;

import ar.jdbc.JdbcCursos;
import ar.jdbc.JdbcEstudiantes;
import ar.memoria.MemoriaCursos;
import ar.memoria.MemoriaEstudiantes;
import ar.web.WebAPI;

public class Main {

 public static void main(String[] args) {
  WebAPI servicio = new WebAPI(new MemoriaCursos(), new MemoriaEstudiantes(),
    1234);
  servicio.start();
 }
}
