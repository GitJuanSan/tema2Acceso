package com.venancio.dam.tema2Acceso.ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo8 {
	public static void main(String[] args) {

        StringBuilder contenidoScript = new StringBuilder(); 

		try (BufferedReader br = new BufferedReader(new FileReader(new File("Scripts/universidad_para_H2.sql")))) {
			String linea;
			while ((linea = br.readLine()) != null)
                contenidoScript.append(linea).append("\n");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		File fichero = new File("BBDDH2");
		String urlH2 = "jdbc:h2:" + fichero.getAbsolutePath();

		System.out.println(urlH2);
		
		try (Connection conexion = DriverManager.getConnection(urlH2, "root", "secret")) {
			Statement stmt = conexion.createStatement();
			stmt.executeUpdate(contenidoScript.toString());
			System.out.println("EXITO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
