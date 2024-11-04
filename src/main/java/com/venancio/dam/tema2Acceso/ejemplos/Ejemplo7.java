package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplo7 {
	public static void main(String[] args) {
	    try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");) {

	        DatabaseMetaData dbmd = conexion.getMetaData();

	        ResultSet clavesAjenas = dbmd.getImportedKeys(null, null, "asignatura");

	        while (clavesAjenas.next()) {
	            String fkColumnName = clavesAjenas.getString("FKCOLUMN_NAME");
	            String pkTableName = clavesAjenas.getString("PKTABLE_NAME");
	            String pkColumnName = clavesAjenas.getString("PKCOLUMN_NAME");
	            String fkName = clavesAjenas.getString("FK_NAME");

	            System.out.println("Clave ajena: " + fkName);
	            System.out.println("Columna de clave ajena: " + fkColumnName);
	            System.out.println("Tabla de clave primaria referenciada: " + pkTableName);
	            System.out.println("Columna de clave primaria referenciada: " + pkColumnName);
	            System.out.println("-------------------------------");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
