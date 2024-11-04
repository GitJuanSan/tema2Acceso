package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplo6 {
	public static void main(String[] args) {
		try (Connection conexion =
				DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root","");) {

			DatabaseMetaData dbmd = conexion.getMetaData();

			ResultSet columnas = dbmd.getColumns(null, null, "asignatura", null);

			while (columnas.next()) {
				System.out.println("\n");
				System.out.println(columnas.getString("COLUMN_NAME") + "\n" + 
									columnas.getString("TYPE_NAME") + "\n" + 
									columnas.getString("COLUMN_SIZE") + "\n" +
									columnas.getString("IS_NULLABLE") + "\n" + 
									columnas.getString("IS_AUTOINCREMENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
