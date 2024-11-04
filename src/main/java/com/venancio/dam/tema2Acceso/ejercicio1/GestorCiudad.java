package com.venancio.dam.tema2Acceso.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestorCiudad {

	private static Connection crearConexion() throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
		return conexion;
	}

	public static List<Ciudad> getCiudadesOrdenadasPorID() {
		
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try (Connection conexion = crearConexion();
				Statement consulta = conexion.createStatement();
				ResultSet resultConsulta = consulta.executeQuery("SELECT * FROM city")) {

			while (resultConsulta.next()) {
				ciudades.add(new Ciudad(resultConsulta.getInt("ID"), resultConsulta.getString("Name"),
						resultConsulta.getString("CountryCode"), resultConsulta.getString("District"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ciudades;
	}

	public static List<Ciudad> getCiudadesOrdenadasPorHabitantes() {
		
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try (Connection conexion = crearConexion();
				Statement consulta = conexion.createStatement();
				ResultSet resultConsulta = consulta.executeQuery("SELECT * FROM city order by Population asc")) {

			while (resultConsulta.next()) {
				ciudades.add(new Ciudad(resultConsulta.getInt("ID"), resultConsulta.getString("Name"),
						resultConsulta.getString("CountryCode"), resultConsulta.getString("District"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudades;
	}
	
	public static Ciudad getCiudadPorId(int idCiudad) {
		
		ResultSet resultConsulta = null;
		Ciudad ciudad = null;
		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion.prepareStatement("SELECT * FROM city where ID = ?")) {

			consultaPreparada.setInt(1, idCiudad);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				ciudad = new Ciudad(resultConsulta.getInt("ID"), resultConsulta.getString("Name"),
						resultConsulta.getString("CountryCode"), resultConsulta.getString("District"),
						resultConsulta.getInt("Population"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ciudad;
	}

	public static List<Ciudad> getCiudadesPorNombre(String nombreCiudad) {
		
		ResultSet resultConsulta = null;
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion.prepareStatement("SELECT * FROM city where Name = ?")) {

			consultaPreparada.setString(1, nombreCiudad);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				ciudades.add(new Ciudad(resultConsulta.getInt("ID"), resultConsulta.getString("Name"),
						resultConsulta.getString("CountryCode"), resultConsulta.getString("District"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudades;
	}

	public static List<Ciudad> getListaCiudadesCodigoPais(String codPais) {
		ResultSet resultConsulta = null;
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
						.prepareStatement("SELECT * FROM city where CountryCode = ?")) {

			consultaPreparada.setString(1, codPais);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				ciudades.add(new Ciudad(resultConsulta.getInt("ID"), resultConsulta.getString("Name"),
						resultConsulta.getString("CountryCode"), resultConsulta.getString("District"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudades;
	}

	public static int getNumeroCiudades() {
		int numCiudades = 0;
		try (Connection conexion = crearConexion();
				Statement consulta = conexion.createStatement();
				ResultSet resultConsulta = consulta.executeQuery("SELECT count(*) FROM city")) {

			while (resultConsulta.next()) {
				numCiudades = resultConsulta.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numCiudades;
	}
	
	public static int getNumeroCiudadesConCodigoPais(String codPais) {
		int numCiudades = 0;
		ResultSet resultConsulta = null;

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion.prepareStatement(
						"SELECT count(*) FROM city where CountryCode = ?")) {

			consultaPreparada.setString(1, codPais);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				numCiudades = resultConsulta.getInt("count(*)");
				
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return numCiudades;
	}

	public static List<Ciudad> getListaCiudadesNombrePais(String nombrePais) {
		ResultSet resultConsulta = null;
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		
		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion.prepareStatement(
						"SELECT * FROM city where CountryCode = (select Code FROM country where Name = ?)")) {

			consultaPreparada.setString(1, nombrePais);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				ciudades.add(new Ciudad(resultConsulta.getInt("ID"), resultConsulta.getString("Name"),
						resultConsulta.getString("CountryCode"), resultConsulta.getString("District"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudades;
	}

	public static boolean agregarCiudad(Ciudad nuevaCiudad) {
		
		if (!GestorPais.isPaisPorCodigoDelPais(nuevaCiudad.getCodigoPais())) {
			System.out.println("[ERROR] NO EXISTE");
			return false;
		}

		if (GestorPais.getHabitantesPorCodigo(nuevaCiudad.getCodigoPais()) == 0) {
			System.out.println("PAIS SIN HABITANTES");
			return false;
		}

		GestorPais.updateHabitantesPais(nuevaCiudad.getCodigoPais(), nuevaCiudad.getPoblacion());

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
						.prepareStatement("insert into city (Name,CountryCode,District,Population) values (?,?,?,?)")) {

			consultaPreparada.setString(1, nuevaCiudad.getNombre());
			consultaPreparada.setString(2, nuevaCiudad.getCodigoPais());
			consultaPreparada.setString(3, nuevaCiudad.getDistrito());
			consultaPreparada.setInt(4, nuevaCiudad.getPoblacion());

			consultaPreparada.executeUpdate();

		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}