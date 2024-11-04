package com.venancio.dam.tema2Acceso.ejercicio1;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		int opcion = -1;
		Scanner sc = new Scanner(System.in);

		do {
			menu();
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				mostrarCiudadesPorId();
				break;
			case 2:
				mostrarCiudadesPorHabitantes();
				break;
			case 3:
				mostrarPaisesPorCodigo();
				break;
			case 4:
				mostrarPaisesPorHabitantes();
				break;
			case 5:
				mostrarCiudadPorId(sc);
				break;
			case 6:
				mostrarCiudadesPorNombre(sc);
				break;
			case 7:
				mostrarPaisPorCodigo(sc);
				break;
			case 8:
				mostrarPaisPorNombre(sc);
				break;
			case 9:
				mostrarCiudadesPorCodigoPais(sc);
				break;
			case 10:
				mostrarNumeroPaises();
				break;
			case 11:
				mostrarNumeroCiudades();
				break;
			case 12:
				mostrarNumeroCiudadesPorCodigoPais(sc);
				break;
			case 13:
				agregarPais(sc); 
				break;
			case 14:
				agregarCiudad(sc); 
				break;
			case 15:
				actualizarPoblacionPais(sc);
				break;
			case 16:
				mostrarPaisesPorHabitantesSelecionados(sc);
				break;
			case 0:
				sc.close();
				System.out.println("¡¡Hasta luego!!");
				break;
			default:
				System.out.println("Opción no válida.");
				break;
			}
		} while (opcion != 0);

	}

	private static void mostrarPaisesPorHabitantesSelecionados(Scanner sc) {
		
		int poblacion;
		
		System.out.println("Poblacion minima que deben tener los paises: ");
		poblacion = sc.nextInt();
		List<Pais> paises = GestorPais.getPaisesConMayorPoblacionQue(poblacion);
		if (paises.isEmpty()) {
			System.out.println("[ERROR] No se han encontrado paises con esos datos");
		}else {
			for (Pais pais : paises) {
				System.out.println(pais);
			}
		}
	}

	private static void actualizarPoblacionPais(Scanner sc) {

		String codPais;
		int poblacion;
		System.out.println("Codigo Pais: ");
		codPais = sc.nextLine();
		System.out.println("Poblacion que ha cambiado: ");
		poblacion = sc.nextInt();

		if (GestorPais.updateHabitantesPais(codPais, poblacion)) {
			System.out.println("Update hecho con éxito");
		}else {
			System.out.println("[ERROR] Update fallido");
		}
		
		
	}

	private static void agregarCiudad(Scanner sc) {
		
		String nombre, codPais, distrito;
		int poblacion;
		
		System.out.println("Introduce los datos de la ciudad. ");

		System.out.println("Nombre: ");
		nombre = sc.nextLine();
		System.out.println("Codigo Pais: ");
		codPais = sc.nextLine();
		System.out.println("Distrito: ");
		distrito = sc.nextLine();
		System.out.println("Poblacion: ");
		poblacion = sc.nextInt();
		sc.nextLine();

		if (GestorCiudad.agregarCiudad(new Ciudad(nombre, codPais, distrito, poblacion))) {
			System.out.println("Agregado con éxito");
		}else {
			System.out.println("[ERROR] Agregado fallido");
		}
	}

	private static void agregarPais(Scanner sc) {
		
		String codigo, nombre, continente, region;
		int poblacion;

		System.out.println("Introduce los datos del pais:");
		
		System.out.println("Codigo: ");
		codigo = sc.nextLine();
		System.out.println("Nombre: ");
		nombre = sc.nextLine();
		System.out.println("Continente: ");
		continente = sc.nextLine();
		System.out.println("Region: ");
		region = sc.nextLine();
		System.out.println("Poblacion: ");
		poblacion = sc.nextInt();

		if (GestorPais.agregarPais(new Pais(codigo, nombre, continente, region, poblacion))) {
			System.out.println("Agregado con éxito");
		}else {
			System.out.println("[ERROR] Agregado fallido");
		}
	}

	private static void mostrarNumeroCiudadesPorCodigoPais(Scanner sc) {
		
		System.out.println("Codigo del pais:");
		String codPais;
		codPais = sc.nextLine();
		System.out.println("Numero de ciudades con codigo "+ codPais +": "+GestorCiudad.getNumeroCiudadesConCodigoPais(codPais));
	}

	private static void mostrarNumeroCiudades() {
		
		System.out.println("Numero de ciudades: "+ GestorCiudad.getNumeroCiudades());
	}

	private static void mostrarNumeroPaises() {
		
		System.out.println("Numero de paises: "+ GestorPais.getNumeroPaises());
	}

	private static void mostrarCiudadesPorCodigoPais(Scanner sc) {

		System.out.println("Codigo del pais:");
		String codPais;
		codPais = sc.nextLine();
		
		List<Ciudad> ciudades = GestorCiudad.getListaCiudadesCodigoPais(codPais);
		
		if (!ciudades.isEmpty()) {
			for (Ciudad ciudad : ciudades) {
				System.out.println(ciudad);
			}
		} else {
			System.out.println("[ERROR] No se encuentran las ciudades");
		}
	}

	private static void mostrarCiudadPorId(Scanner sc) {
		
		System.out.println("ID de la ciudad:");
		int id;
		id = sc.nextInt();
		
		Ciudad ciudad = GestorCiudad.getCiudadPorId(id);
		
		if (ciudad != null) {
			System.out.println(ciudad);
		} else {
			System.out.println("[ERROR] No se encuentra la ciudad");
		}
	}

	private static void mostrarCiudadesPorNombre(Scanner sc) {

		System.out.println("Nombre de la ciudad:");
		String nombre;
		nombre = sc.nextLine();
		
		List<Ciudad> ciudades = GestorCiudad.getCiudadesPorNombre(nombre);
		
		if (!ciudades.isEmpty()) {
			for (Ciudad ciudad : ciudades) {
				System.out.println(ciudad);
			}
		} else {
			System.out.println("[ERROR] No se encuentran las ciudades");
		}

	}

	private static void mostrarPaisPorCodigo(Scanner sc) {
		
		System.out.println("Codigo del pais:");
		String codigo;
		codigo = sc.nextLine();
		
		Pais pais = GestorPais.getPaisPorCodigo(codigo);
		
		if (pais != null) {
			System.out.println(pais);
		} else {
			System.out.println("[ERROR] No se encuentra el pais");
		}

	}

	private static void mostrarPaisPorNombre(Scanner sc) {
		
		System.out.println("Nombre del pais:");
		String nombre;
		nombre = sc.nextLine();
		
		Pais pais = GestorPais.getPaisPorNombre(nombre);
		
		if (pais != null) {
			System.out.println(pais);
		} else {
			System.out.println("[ERROR] No se encuentra el pais");
		}

	}

	private static void mostrarPaisesPorHabitantes() {
		
		for (Pais p : GestorPais.getPaisesOrdenadosPorHabitantes()) {
			System.out.println(p);
		}
	}

	private static void mostrarPaisesPorCodigo() {
		
		for (Pais p : GestorPais.getPaisesOrdenadosPorCodigo()) {
			System.out.println(p);
		}
	}

	private static void mostrarCiudadesPorHabitantes() {

		System.out.println("\nCiudades ordenadas por población:");
		
		for (Ciudad ciudad : GestorCiudad.getCiudadesOrdenadasPorHabitantes()) {
			System.out.println(ciudad);
		}
	}

	private static void mostrarCiudadesPorId() {

		System.out.println("\nCiudades ordenadas por ID:");
		
		for (Ciudad ciudad : GestorCiudad.getCiudadesOrdenadasPorID()) {
			System.out.println(ciudad);
		}

	}

	private static void menu() {
		System.out.println("""
				---MENU---
				0) Salir
				1)  Mostrar ciudades ordenadas por id
				2)  Mostrar ciudades ordenadas por habitantes
				3)  Mostrar paises ordenados por codigo
				4)  Mostrar paises ordenados por habitantes
				5)  Devolver una ciudad a partir de su id
				6)  Devolver una o varias ciudades a partir de su nombre
				7)  Devolver un pais a partir de su id
				8)  Devolver un pais a partir de su nombre
				9)  Mostrar las ciudades de un pais
				10) Contar numero de paises
				11) Contar numero de ciudades
				12) Contar numero de ciudades de un pais concreto
				13) Agregar pais
				14) Agregar ciudad
				15) Actualizar la poblacion de un pais
				16) Buscar paises con mas habitantes que los indicados por el usuario

				Introduzca la opcion:
					""");
	}

}