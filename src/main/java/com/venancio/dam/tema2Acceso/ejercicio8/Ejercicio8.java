package com.venancio.dam.tema2Acceso.ejercicio8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio8 {
	
	public static void main(String[] args) {
		
		crearProcedimientoDePrueba();
		
		crearFuncionContarEmpleados();
	}
	
	public static boolean crearProcedimientoDePrueba() {

		// El procedimiento aumenta el salario del empleado un tanto por ciento
		try (Connection conexion = creaConexion();
				PreparedStatement consulta1 = conexion.prepareStatement("DROP PROCEDURE IF EXISTS ActualizarSalario; ");

				PreparedStatement consulta2 = conexion.prepareStatement(
						"CREATE PROCEDURE ActualizarSalario ( IN p_NIF VARCHAR(9), IN p_porcentaje DECIMAL(5,2) ) BEGIN UPDATE empleado SET Salario = Salario * (1 + p_porcentaje / 100) WHERE NIF = p_NIF; END")) {
			consulta1.executeUpdate();
			consulta2.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al crear el procedimiento");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean crearFuncionContarEmpleados() {
		try (Connection conexion = creaConexion();
				PreparedStatement consulta1 = conexion.prepareStatement("DROP FUNCTION IF EXISTS ContarEmpleados; ");
				PreparedStatement consulta2 = conexion.prepareStatement("CREATE FUNCTION ContarEmpleados() "
						+ "RETURNS INT DETERMINISTIC " + "BEGIN " + "   DECLARE totalEmpleados INT; "
						+ "   SELECT COUNT(*) INTO totalEmpleados FROM empleado; " + "   RETURN totalEmpleados; "
						+ "END;")) {
			consulta1.executeUpdate();
			consulta2.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error al crear la función ContarEmpleados");
			e.printStackTrace();
		}
		return false;
	}
	
	private static Connection creaConexion() throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadobbdd", "root", "");
		//Connection conexion = DriverManager.getConnection("jdbc:h2:" + Path.of("BBDDH2").toAbsolutePath().toString(), "root", "secret");
		return conexion;
	}
}
