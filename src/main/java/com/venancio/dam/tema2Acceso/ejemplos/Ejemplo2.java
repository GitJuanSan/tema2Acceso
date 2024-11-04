package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejemplo2 {
	public static void main(String[] args) {
		ResultSet resultadoConsulta = null;
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
				Statement consulta = conexion.createStatement();
				Scanner teclado = new Scanner(System.in)) {

			System.out.println("Introduce el nombre de la asignatura:");
			String nombreAsignatura = teclado.nextLine();

			// Código vulnerable
			String sql = "SELECT * FROM ASIGNATURA WHERE nombre = '" + nombreAsignatura + "'";

			System.out.println(sql);
			resultadoConsulta = consulta.executeQuery(sql);

			while (resultadoConsulta.next())
				System.out.println(
						resultadoConsulta.getString("nombre") + " - " + resultadoConsulta.getInt("cuatrimestre"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Como no podemos añadir el result set en el try with resources, aquí si es
			// necesario cerrarlo manualmente
			if (resultadoConsulta != null) {
				try {
					resultadoConsulta.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
