package com.idat.springboot.sistematienda.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utiles {

	public static String obtenerFechaYHoraActual() {
        String formato = "dd-MM-yyyy HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }
}
