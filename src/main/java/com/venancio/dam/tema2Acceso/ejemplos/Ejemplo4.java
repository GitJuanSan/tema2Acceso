package com.venancio.dam.tema2Acceso.ejemplos;

import java.util.List;

public class Ejemplo4 {

	public static void main(String[] args) {
		List<Ejemplo4Asignatura> asignaturas = Ejemplo4AsignaturaDAO.leerTodos();

		System.out.println("Las asignaturas almacenadas en la base de datos son:");
		for (Ejemplo4Asignatura asignatura : asignaturas)
			System.out.println(asignatura);
		
		
		
	}
}
