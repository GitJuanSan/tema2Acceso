package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Ejemplo10 {

	public static void main(String[] args) {
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
			 CallableStatement llamadaFuncion = conexion.prepareCall("{ ? = CALL test_professor_assignment(?, ?) }");) {

			int idProfesor = 3;
			int idAsignatura = 1;
			
			llamadaFuncion.registerOutParameter(1, Types.VARCHAR);
			llamadaFuncion.setInt(2, idProfesor);//ID_PROFESOR
			llamadaFuncion.setInt(3, idAsignatura);//ID_ASIGNATURA
						
			llamadaFuncion.executeUpdate();
			
			boolean resultado = llamadaFuncion.getBoolean(1);
			
			if(resultado)
				System.out.println("El profesor con id " + idProfesor + " imparte la asignatura con id " + idAsignatura);
			else
				System.out.println("El profesor con id " + idProfesor + " NO imparte la asignatura con id " + idAsignatura);
			
		} catch (SQLException e) {
			System.err.println("Se ha obtenido el siguiente error de la BBDD: \n " + e.getMessage());
		}
	}
}
