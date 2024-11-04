package com.venancio.dam.tema2Acceso.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestorPais {

	private static Connection crearConexion() throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");

		return conexion;
	}

	public static List<Pais> getPaisesOrdenadosPorCodigo() {
		
		List<Pais> paises = new ArrayList<Pais>();
		try (Connection conexion = crearConexion();
				Statement consulta = conexion.createStatement();
				ResultSet resultConsulta = consulta
						.executeQuery("SELECT Code,Name,Continent,Region,Population FROM country")) {

			while (resultConsulta.next()) {
				paises.add(new Pais(resultConsulta.getString("Code"), resultConsulta.getString("Name"),
						resultConsulta.getString("Continent"), resultConsulta.getString("Region"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paises;
	}

	public static List<Pais> getPaisesOrdenadosPorHabitantes() {
		List<Pais> paises = new ArrayList<Pais>();
		try (Connection conexion = crearConexion();
				Statement consulta = conexion.createStatement();
				ResultSet resultConsulta = consulta.executeQuery(
						"SELECT Code,Name,Continent,Region,Population FROM country order by Population asc")) {

			while (resultConsulta.next()) {
				paises.add(new Pais(resultConsulta.getString("Code"), resultConsulta.getString("Name"),
						resultConsulta.getString("Continent"), resultConsulta.getString("Region"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paises;
	}
	
	public static List<Pais> getPaisesConMayorPoblacionQue(int poblacionMinima){
		
		ResultSet resultConsulta = null;
		List<Pais> paises = new ArrayList<Pais>();
		
		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
			.prepareStatement("SELECT Code,Name,Continent,Region,Population FROM country where Population > ?")) {

			consultaPreparada.setInt(1, poblacionMinima);
						
			resultConsulta = consultaPreparada.executeQuery();
			
			while (resultConsulta.next()) {
				paises.add(new Pais(resultConsulta.getString("Code"), resultConsulta.getString("Name"),
						resultConsulta.getString("Continent"), resultConsulta.getString("Region"),
						resultConsulta.getInt("Population")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paises;
	}

	public static Pais getPaisPorCodigo(String codPais) {
		
		ResultSet resultConsulta = null;
		Pais pais = null;
		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion.prepareStatement(
						"SELECT Code,Name,Continent,Region,Population FROM country where Code = ?")) {

			consultaPreparada.setString(1, codPais);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				pais = new Pais(resultConsulta.getString("Code"), resultConsulta.getString("Name"),
						resultConsulta.getString("Continent"), resultConsulta.getString("Region"),
						resultConsulta.getInt("Population"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pais;	
	}
	

	public static Pais getPaisPorNombre(String nombrePais) {
		
		ResultSet resultConsulta = null;
		Pais pais = null;
		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion.prepareStatement(
						"SELECT Code,Name,Continent,Region,Population FROM country where Name = ?")) {

			consultaPreparada.setString(1, nombrePais);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				pais = new Pais(resultConsulta.getString("Code"), resultConsulta.getString("Name"),
						resultConsulta.getString("Continent"), resultConsulta.getString("Region"),
						resultConsulta.getInt("Population"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pais;	
	}
	
	public static int getNumeroPaises() {
		int numPaises = 0;
		
		try (Connection conexion = crearConexion();
				Statement consulta = conexion.createStatement();
				ResultSet resultConsulta = consulta.executeQuery("SELECT count(*) FROM country")) {

			while (resultConsulta.next()) {
				numPaises = resultConsulta.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numPaises;
	}

	public static boolean agregarPais(Pais nuevaPais){

		try (Connection conexion = crearConexion();
			PreparedStatement consultaPreparada = conexion.prepareStatement("insert into country (Code,Name,Continent,Region,Population) values (?,?,?,?,?)")) {

			consultaPreparada.setString(1, nuevaPais.getCodigo());
			consultaPreparada.setString(2, nuevaPais.getNombre());
			consultaPreparada.setString(3, nuevaPais.getContinente());
			consultaPreparada.setString(4, nuevaPais.getRegion());
			consultaPreparada.setInt(5, nuevaPais.getPoblacion());

			consultaPreparada.executeUpdate();

		} catch (SQLException e) {
			return false; 
		}
		return true;
	}


	public static boolean isPaisPorCodigoDelPais(String codPais) {
		ResultSet resultConsulta = null;

		try (Connection conexion = crearConexion();
			PreparedStatement consultaPreparada = conexion.prepareStatement("SELECT * FROM country where Code = ?")) {

			consultaPreparada.setString(1, codPais);
			resultConsulta = consultaPreparada.executeQuery();

			if (resultConsulta.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean updateHabitantesPais(String codPais, int poblacionIncrementar) {

		try (Connection conexion = crearConexion();
			PreparedStatement consultaPreparada = conexion.prepareStatement("update country set Population = (Population + ?) where Code = ?")) {

			consultaPreparada.setInt(1, poblacionIncrementar);
			consultaPreparada.setString(2, codPais);

			consultaPreparada.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static int getHabitantesPorCodigo(String codPais) {
		
		int habitantes = 0;
		ResultSet resultConsulta = null;

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
						.prepareStatement("SELECT Population FROM country where Code = ?")) {

			consultaPreparada.setString(1, codPais);
			resultConsulta = consultaPreparada.executeQuery();

			while (resultConsulta.next()) {
				habitantes = resultConsulta.getInt("Population");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return habitantes;
	}
}
