package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplo5 {
	public static void main(String[] args) {
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root","");) {

			DatabaseMetaData dbmd = conexion.getMetaData();

			String tipos[] = { "TABLE", "VIEW" };
			ResultSet tablas = dbmd.getTables("universidad", null, null, tipos);

			while (tablas.next()) {
				System.out.println("\n");
				System.out.println(tablas.getString("TABLE_NAME") + "\n" + tablas.getString("TABLE_TYPE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
