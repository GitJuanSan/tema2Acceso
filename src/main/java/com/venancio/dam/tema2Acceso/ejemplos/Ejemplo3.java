package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejemplo3 {
	public static void main(String[] args) {
		ResultSet resultadoConsulta = null;
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
				PreparedStatement consultaPreparada = conexion.prepareStatement("SELECT * FROM ASIGNATURA WHERE nombre = ?");
				Scanner teclado = new Scanner(System.in)) {

			System.out.println("Introduce el nombre de la asignatura:");
			String nombreAsignatura = teclado.nextLine();

			consultaPreparada.setString(1, nombreAsignatura);
			System.out.println(consultaPreparada);
			
			resultadoConsulta = consultaPreparada.executeQuery();

			while (resultadoConsulta.next())
				System.out.println(resultadoConsulta.getString("nombre") + " - " + resultadoConsulta.getInt("cuatrimestre"));

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
