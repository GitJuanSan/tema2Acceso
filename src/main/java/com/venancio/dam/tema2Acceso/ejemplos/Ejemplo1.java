package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo1 {

	public static void main(String[] args) {
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
				Statement consulta = conexion.createStatement();
				ResultSet resultadoConsulta = consulta.executeQuery("SELECT * FROM ASIGNATURA")) {

			while (resultadoConsulta.next())
				System.out.println(resultadoConsulta.getString("nombre") + " - " + resultadoConsulta.getInt("cuatrimestre"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
