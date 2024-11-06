package com.venancio.dam.tema2Acceso.ejemplos;

import java.util.List;

public class Ejemplo4 {

	public static void main(String[] args) {
		List<Ejercicio7Asignatura> asignaturas = Ejemplo4AsignaturaDAO.leerTodos();

		System.out.println("Las asignaturas almacenadas en la base de datos son:");
		for (Ejercicio7Asignatura asignatura : asignaturas)
			System.out.println(asignatura);
		
		
		
	}
}
