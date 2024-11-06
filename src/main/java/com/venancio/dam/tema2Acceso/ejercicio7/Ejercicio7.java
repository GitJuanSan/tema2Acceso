package com.venancio.dam.tema2Acceso.ejercicio7;

import java.util.List;
import java.util.Scanner;

public class Ejercicio7 {

	public static void main(String[] args) {
		
		
		int opcion=-1;
		
		Scanner sc= new Scanner(System.in);
		
		do {
			Ejercicio7Asignatura asignatura=null;
			int id=0;
			
			menu();
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch(opcion) {
				case 0:
					System.out.println("¡¡Hasta luego!!");
					break;
				case 1:
					leerAsignaturas();
					break;
				case 2:
					id=pedirId(sc);		
					asignatura=Ejercicio7AsignaturaDAO.leer(id);		
					System.out.println(asignatura);
					break;
				case 3:
					id=pedirId(sc);
					Ejercicio7AsignaturaDAO.eliminar(id);
					break;
				case 4:
					
					modificarAsignatura(sc);
					break;
				case 5:
					Ejercicio7AsignaturaDAO.escribir(añadirAsignatura(sc));
					break;	
				default:
					System.out.println("[ERROR] Opción no válida");
					break;
			}
			
			
		}while(opcion!=0);
		
		
	}
	
	public static void menu() {
		System.out.println("""
				---MENU---
				0)  Salir
				1)  Mostrar todas las asignaturas
				2)  Buscar asignatura por id
				3)  Eliminar asignatura
				4)  Modificar asignatura
				5)  Añadir asignatura

				Introduzca la opcion:
					""");
	}
	
	public static int pedirId(Scanner sc) {
		List<Ejercicio7Asignatura> asignaturas = Ejercicio7AsignaturaDAO.leerTodos();
		
		System.out.println("Introduzca el id: ");
		int id=sc.nextInt();
		sc.nextLine();
		
		return id;

	}
	
	public static void leerAsignaturas() {
		List<Ejercicio7Asignatura> lista = Ejercicio7AsignaturaDAO.leerTodos();
		System.out.println("Las asignaturas almacenadas en la base de datos son:");
		for (Ejercicio7Asignatura asignatura : lista)
			System.out.println(asignatura);
			
	}
	
	public static Ejercicio7Asignatura añadirAsignatura(Scanner sc) {
		
		String nombre="", tipo="";
		int creditos=0, curso=0, cuatrimestre=0, idGrado=0, id=0;
		
		do {
			
			System.out.println("Introduzca el nombre de la asignatura: ");
			nombre=sc.nextLine();
			
			System.out.println("Introduzca el numero de creditos: ");
			creditos=sc.nextInt();
			sc.nextLine();
			
			System.out.println("Introduzca el tipo de la asignatura: ");
			tipo=sc.nextLine();
			
			System.out.println("Introduzca el curso: ");
			curso=sc.nextInt();
			sc.nextLine();
			
			System.out.println("Introduzca el cuatrimestre: ");
			cuatrimestre=sc.nextInt();
			sc.nextLine();
			
			System.out.println("Introduzca el id del Grado: ");
			idGrado=sc.nextInt();
			sc.nextLine();
			
		}while(id<0||nombre==null||tipo==null||creditos<=0||curso<0||cuatrimestre<0||idGrado<0);
		
		Ejercicio7Asignatura nuevo = new Ejercicio7Asignatura(nombre, creditos, tipo, curso, cuatrimestre, idGrado);
		
		return nuevo;
	}
	
	public static void modificarAsignatura(Scanner sc) {
		
		String nombre="", tipo="";
		int creditos=0, curso=0, cuatrimestre=0, idGrado=0;
		
		int id=pedirId(sc);		

		
		do {
			
			System.out.println("Introduzca el nombre de la asignatura: ");
			nombre=sc.nextLine();
			
			System.out.println("Introduzca el numero de creditos: ");
			creditos=sc.nextInt();
			sc.nextLine();
			
			System.out.println("Introduzca el tipo de la asignatura: ");
			tipo=sc.nextLine();
			
			System.out.println("Introduzca el curso: ");
			curso=sc.nextInt();
			sc.nextLine();
			
			System.out.println("Introduzca el cuatrimestre: ");
			cuatrimestre=sc.nextInt();
			sc.nextLine();
			
			System.out.println("Introduzca el id del Grado: ");
			idGrado=sc.nextInt();
			sc.nextLine();
			
		}while(nombre==null||tipo==null||creditos<=0||curso<0||cuatrimestre<0||idGrado<0);
		
		
		
		Ejercicio7AsignaturaDAO.actualizar(new Ejercicio7Asignatura(id, nombre, creditos, tipo, curso, cuatrimestre, idGrado));
	}
	
}
