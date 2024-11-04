package com.venancio.dam.tema2Acceso.ejemplos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ejemplo9 {

	public static void main(String[] args) {

		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
			 CallableStatement llamadaProcedimiento = conexion.prepareCall("{CALL test_student_enrollment(?, ?, ?) }");) {

			int idEstudiante = 1;
			int idAsignatura = 3;
			int idCurso = 2;

			
			llamadaProcedimiento.setInt(1, idEstudiante);
			llamadaProcedimiento.setInt(2, idAsignatura);
			llamadaProcedimiento.setInt(3, idCurso);
			
			//El alumno con id 1 se matricula en la asignatura con id 3 en el curso con id 2
			
			llamadaProcedimiento.executeUpdate();
			System.out.println("Procedimiento ejecutado correctamente");
			
		} catch (SQLException e) {
			System.err.println("Se ha obtenido el siguiente error de la BBDD: \n " + e.getMessage());
		}

	}

}
