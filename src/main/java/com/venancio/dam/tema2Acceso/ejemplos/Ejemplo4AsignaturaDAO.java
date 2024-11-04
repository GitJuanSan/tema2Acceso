package com.venancio.dam.tema2Acceso.ejemplos;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ejemplo4AsignaturaDAO {

	private static Connection creaConexion() throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
		//Connection conexion = DriverManager.getConnection("jdbc:h2:" + Path.of("BBDDH2").toAbsolutePath().toString(), "root", "secret");
		return conexion;
	}

	public static Ejemplo4Asignatura leer(int id) {

		Ejemplo4Asignatura asignaturaBuscada = null;

		try (Connection conexion = creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM asignatura WHERE id = ?")) {
			consulta.setInt(1, id);
			System.out.println("LEER: " + consulta);
			ResultSet resultadoConsulta = consulta.executeQuery();

			if (resultadoConsulta.next()) {
				asignaturaBuscada = new Ejemplo4Asignatura(resultadoConsulta.getInt("id"),
						resultadoConsulta.getString("nombre"), resultadoConsulta.getInt("creditos"),
						resultadoConsulta.getString("tipo"), resultadoConsulta.getInt("curso"),
						resultadoConsulta.getInt("cuatrimestre"), resultadoConsulta.getInt("id_grado"));
			} else {
				System.err.println("No se ha encontrado en la base de datos ninguna asignatura con el id " + id);
			}

		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de recuperar la asignatura:");
			e.printStackTrace();
		}
		return asignaturaBuscada;
	}

	public static void escribir(Ejemplo4Asignatura asignatura) {
		try (Connection conexion = creaConexion();
				PreparedStatement consulta = conexion.prepareStatement(
						"INSERT INTO " + "asignatura (nombre, creditos, tipo, curso, cuatrimestre, id_grado)"
								+ " VALUES (?, ?, ?, ?, ?, ?)")) {

			consulta.setString(1, asignatura.getNombre());
			consulta.setInt(2, asignatura.getCreditos());
			consulta.setString(3, asignatura.getTipo());
			consulta.setInt(4, asignatura.getCurso());
			consulta.setInt(5, asignatura.getCuatrimestre());
			consulta.setInt(6, asignatura.getIdGrado());

			System.out.println("ESCRIBIR: " + consulta);
			consulta.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de escribir la asignatura:");
			e.printStackTrace();
		}
	}

	public static void actualizar(Ejemplo4Asignatura asignatura) {
		try (Connection conexion = creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("UPDATE asignatura SET"
						+ " nombre=?, creditos=?, tipo=?, curso=?, cuatrimestre=?, id_grado=? WHERE id=?")) {

			consulta.setString(1, asignatura.getNombre());
			consulta.setInt(2, asignatura.getCreditos());
			consulta.setString(3, asignatura.getTipo());
			consulta.setInt(4, asignatura.getCurso());
			consulta.setInt(5, asignatura.getCuatrimestre());
			consulta.setInt(6, asignatura.getIdGrado());
			consulta.setInt(7, asignatura.getId());

			System.out.println("ACTUALIZAR: " + consulta);
			consulta.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de modificar la asignatura:");
			e.printStackTrace();
		}
	}

	public static void eliminar(int id) {
		try (Connection conexion = creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("DELETE FROM asignatura WHERE id=?")) {
			consulta.setInt(1, id);
			System.out.println("ELIMINAR: " + consulta);
			consulta.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de eliminar la asignatura:");
			e.printStackTrace();
		}
	}

	public static List<Ejemplo4Asignatura> leerTodos() {
		List<Ejemplo4Asignatura> asignaturas = new ArrayList<Ejemplo4Asignatura>();

		try (Connection conexion = creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM asignatura")) {
			System.out.println("LEER TODOS: " + consulta);
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Ejemplo4Asignatura nuevaAsignatura = new Ejemplo4Asignatura(resultadoConsulta.getInt("id"),
						resultadoConsulta.getString("nombre"), resultadoConsulta.getInt("creditos"),
						resultadoConsulta.getString("tipo"), resultadoConsulta.getInt("curso"),
						resultadoConsulta.getInt("cuatrimestre"), resultadoConsulta.getInt("id_grado"));
				asignaturas.add(nuevaAsignatura);
			}
		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de leer las asignaturas:");
			e.printStackTrace();
		}

		return asignaturas;

	}

}
