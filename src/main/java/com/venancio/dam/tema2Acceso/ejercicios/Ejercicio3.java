package com.venancio.dam.tema2Acceso.ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;

public class Ejercicio3 {
	
	public static void main(String[] args) {
		int opcion;
		Scanner sc = new Scanner(System.in);
		do {
			
		}while(opcion!=0);
		
	}
	
	public void menu() {
		System.out.println("----MENU----");
		System.out.println("0) Salir");
		System.out.println("1) Crear la tabla empleado");
		System.out.println("2) Mostrar las tablas de la base de datos empleados");
		System.out.println("3) Mostrar las columnas de las tablas de la base de datos empleados");
	}
	
	public boolean crearTablaEmpleado() {
		try (Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/empleado", "root", "");
				PreparedStatement consulta=conexion.prepareStatement("CREATE TABLE empleado (NIF VARCHAR(9) PRIMARY KEY,Nombre VARCHAR(50) NOT NULL,Apellidos VARCHAR(50) NOT NULL,Salario DECIMAL(10, 2))");
				Scanner scn = new Scanner(System.in)){
				return !consulta.execute();
				
				
			}catch(SQLException e) {
				e.getMessage();
				return false;
			}
	}
	
	
}
