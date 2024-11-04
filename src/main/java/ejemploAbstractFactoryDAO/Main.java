package ejemploAbstractFactoryDAO;

import java.util.Scanner;

import ejemploAbstractFactoryDAO.DAOS.AsignaturaDAO;
import ejemploAbstractFactoryDAO.POJOS.Asignatura;
import ejemploAbstractFactoryDAO.enums.FuentesDeDatos;
import ejemploAbstractFactoryDAO.enums.TipoAsignatura;
import ejemploAbstractFactoryDAO.enums.TiposDatos;
import ejemploAbstractFactoryDAO.factorias.DAOFactory;

public class Main {

	public static void main(String[] args) {

		int opcion;
		FuentesDeDatos fuenteDatos;
		TiposDatos tipoDatos;

		Scanner teclado = new Scanner(System.in);

		do {
			mostrarMenuFuenteDatos();
			opcion = teclado.nextInt();
			teclado.nextLine();

			if (opcion == 0)
				System.exit(0);

			fuenteDatos = FuentesDeDatos.fromValor(opcion);
			if (fuenteDatos == null)
				System.out.println("La fuente de datos seleccionada no existe!!\n");

		} while (fuenteDatos == null);

		do {
			mostrarMenuTipoDato();
			opcion = teclado.nextInt();
			teclado.nextLine();

			if (opcion == 0)
				System.exit(0);

			tipoDatos = TiposDatos.fromValor(opcion);
			if (tipoDatos == null)
				System.out.println("El tipo de dato seleccionado no existe!!\n");

		} while (tipoDatos == null);

		System.out.println("Se va a acceder a " + tipoDatos + " en " + fuenteDatos);

		DAOFactory factoriaDAO = DAOFactory.getDAOFactory(fuenteDatos);

		if (tipoDatos.equals(TiposDatos.ASIGNATURAS)) {

			AsignaturaDAO asignaturaDAO = factoriaDAO.getAsignaturaDAO();

			do {
				mostrarMenuAsignaturas();
				opcion = teclado.nextInt();
				teclado.nextLine();

				switch (opcion) {
				case 1:
					consultarAsignatura(asignaturaDAO, teclado);
					break;
				case 2:
					insertarAsignatura(asignaturaDAO, teclado);
					break;
				case 3:
					modificarAsignatura(asignaturaDAO, teclado);
					break;
				case 4:
					borrarAsignatura(asignaturaDAO, teclado);
					break;
				case 5:
					mostrarAsignaturas(asignaturaDAO);
					break;
				case 0:
					teclado.close();
					System.out.println("Adiós!!");
					break;
				default:
					System.out.println("Opción desconocida.");
					break;
				}

			} while (opcion != 0);

		} else if (tipoDatos.equals(TiposDatos.PROFESORES)) {

		}

	}

	public static void modificarAsignatura(AsignaturaDAO asignaturaDAO, Scanner teclado) {

		System.out.println("Introduzca el id de la asignatura a modificar");
		int id = teclado.nextInt();
		teclado.nextLine();

		Asignatura asignaturaModificar = asignaturaDAO.leer(id);

		if (asignaturaModificar == null) {
			System.out.println("No existe ninguna asignatura con el id " + id);
			return;
		}

		boolean seModificaAlguno = false;
		String respuesta;

		System.out.println("¿Quiere modificar el número de créditos?");
		System.out.println("S/N");
		respuesta = teclado.nextLine().toUpperCase();

		if (respuesta.equals("S")) {
			float nuevosCreditos = leerCreditosUsuario(teclado);
			if (nuevosCreditos != asignaturaModificar.getCreditos()) {
				seModificaAlguno = true;
				asignaturaModificar.setCreditos(nuevosCreditos);
			}
		}

		System.out.println("¿Quiere modificar el tipo de asignatura?");
		System.out.println("S/N");
		respuesta = teclado.nextLine().toUpperCase();

		if (respuesta.equals("S")) {
			TipoAsignatura tipoAsignatura = leerTipoAsignatura(teclado);
			if (!tipoAsignatura.toString().equals(asignaturaModificar.getNombre())) {
				seModificaAlguno = true;
				asignaturaModificar.setTipo(tipoAsignatura.toString());
			}
		}

		System.out.println("¿Quiere modificar el curso al que pertenece la asignatura?");
		System.out.println("S/N");
		respuesta = teclado.nextLine().toUpperCase();

		if (respuesta.equals("S")) {
			int curso = leerCursoUsuario(teclado);
			if (curso != asignaturaModificar.getCurso()) {
				seModificaAlguno = true;
				asignaturaModificar.setCurso(curso);
			}
		}

		System.out.println("¿Quiere modificar el cuatrimestre al que pertenece la asignatura?");
		System.out.println("S/N");
		respuesta = teclado.nextLine().toUpperCase();

		if (respuesta.equals("S")) {
			int cuatrimestre = leerCuatrimestreUsuario(teclado);

			if (cuatrimestre != asignaturaModificar.getCuatrimestre()) {
				seModificaAlguno = true;
				asignaturaModificar.setCuatrimestre(cuatrimestre);
			}
		}

		if (seModificaAlguno) {
			System.out.println("\nSe va a modificar la asignatura con los siguientes valores:\n" + asignaturaModificar);
			System.out.println();
			if (asignaturaDAO.actualizar(asignaturaModificar))
				System.out.println("Asignatura modificada correctamente");
			else
				System.err.println("Error al modificar la asignatura");
			System.out.println();
		} else {
			System.out.println("No se ha modificado ningún valor");
		}

	}

	public static void insertarAsignatura(AsignaturaDAO asignaturaDAO, Scanner teclado) {
		System.out.println("Se le va a solicitar la información de la nueva asignatura: ");
		teclado.nextLine();

		System.out.println("Introduzca el nombre de la asignatura");
		String nombre = teclado.nextLine();

		float creditos = leerCreditosUsuario(teclado);

		TipoAsignatura tipoAsignatura = leerTipoAsignatura(teclado);

		int curso = leerCursoUsuario(teclado);

		int cuatrimestre = leerCuatrimestreUsuario(teclado);

		// El valor de idGrado lo mockearemos para no aumentar la dificultad del
		// ejercicio
		int idGrado = 4;

		Asignatura asignatura = new Asignatura(nombre, creditos, tipoAsignatura.toString(), curso, cuatrimestre,
				idGrado);

		if (asignaturaDAO.escribir(asignatura))
			System.out.println("Asignatura añadida");
		else
			System.err.println("Error al añadir la asignatura: " + asignatura);

	}

	public static TipoAsignatura leerTipoAsignatura(Scanner teclado) {
		TipoAsignatura tipoAsignatura = null;
		String tipoAsignaturaIntroducido;

		do {
			System.out.println("Introduzca el tipo de asignatura (ha de ser alguno de los siguientes): ");
			mostrarTipoAsignaturas();
			tipoAsignaturaIntroducido = teclado.nextLine();

			try {
				tipoAsignatura = TipoAsignatura.valueOf(tipoAsignaturaIntroducido.toLowerCase());
			} catch (IllegalArgumentException e) {
				System.out.println("No ha introducido ninguno de los tipos de asignatura indicados");

			}

		} while (tipoAsignatura == null);
		return tipoAsignatura;
	}

	public static float leerCreditosUsuario(Scanner teclado) {
		float creditos;

		do {
			System.out.println("Introduzca los créditos de la asignatura (valor entre 3 y 12)");
			creditos = teclado.nextFloat();
			teclado.nextLine();

			if (creditos < 3 || creditos > 12)
				System.out.println("Valor fuera de los límites establecidos");
		} while (creditos < 3 || creditos > 12);

		return creditos;
	}

	public static int leerCuatrimestreUsuario(Scanner teclado) {
		int cuatrimestre;

		do {
			System.out.println("Introduzca el cuatrimestre (número del 1 o 2)");
			cuatrimestre = teclado.nextInt();
			teclado.nextLine();

			if (cuatrimestre != 1 && cuatrimestre != 2)
				System.out.println("Valor fuera de los límites establecidos");
		} while (cuatrimestre != 1 && cuatrimestre != 2);

		return cuatrimestre;
	}

	public static int leerCursoUsuario(Scanner teclado) {
		int curso;

		do {
			System.out.println("Introduzca el curso (número del 1 al 4)");
			curso = teclado.nextInt();
			teclado.nextLine();

			if (curso < 1 || curso > 4)
				System.out.println("Valor fuera de los límites establecidos");
		} while (curso < 1 || curso > 4);

		return curso;
	}

	public static void mostrarTipoAsignaturas() {
		for (TipoAsignatura tipoAsignatura : TipoAsignatura.values()) {
			System.out.println("\t" + tipoAsignatura);
		}
	}

	public static void consultarAsignatura(AsignaturaDAO asignaturaDAO, Scanner teclado) {

		System.out.println("\nIntroduzca el id de la asignatura de la que quiere consultar la información: ");
		int id = teclado.nextInt();
		teclado.nextLine();

		System.out.println();

		Asignatura asignatura = asignaturaDAO.leer(id);

		if (asignatura == null)
			System.out.println("No se ha encontrado ninguna asignatura con el id:  " + id);
		else
			System.out.println("La información de la asignatura es:\n" + asignatura);
		System.out.println();
	}

	public static void mostrarAsignaturas(AsignaturaDAO asignaturaDAO) {
		System.out.println();
		System.out.println("Las asignaturas son: ");
		for (Asignatura asignatura : asignaturaDAO.leerTodos())
			System.out.println(asignatura);
		System.out.println();

	}

	public static void borrarAsignatura(AsignaturaDAO asignaturaDAO, Scanner escaner) {
		int id;

		System.out.println("Introduzca el id de la asignatura a borrar");
		id = escaner.nextInt();
		escaner.nextLine();
		System.out.println();

		Asignatura asignaturaEliminar = asignaturaDAO.leer(id);

		if (asignaturaEliminar == null) {
			System.out.println("No existe ninguna asignatura con el id " + id);
			return;
		}

		if (asignaturaDAO.eliminar(id))
			System.out.println("Asignatura eliminada");
		else
			System.err.println("Error eliminando la asignatura");
		System.out.println();
	}

	public static void mostrarMenuFuenteDatos() {
		System.out.println("Elige la fuente de datos sobre la que quieres operar");
		System.out.println("\t0) Salir");

		for (FuentesDeDatos fuente : FuentesDeDatos.values()) {
			System.out.println("\t" + fuente.getValor() + ") " + fuente.toString());
		}
	}

	public static void mostrarMenuTipoDato() {
		System.out.println("Elige el tipo de dato sobre el que quieres operar");
		System.out.println("\t0) Salir");
		for (TiposDatos fuente : TiposDatos.values()) {
			System.out.println("\t" + fuente.getValor() + ") " + fuente.toString());
		}
	}

	public static void mostrarMenuAsignaturas() {
		System.out.println("1) Consultar Asignatura\n" + "2) Añadir Asignatura\n" + "3) Modificar Asignatura\n"
				+ "4) Borrar Asignatura\n" + "5) Listar Asignaturas\n" + "0) Salir");
	}

}
