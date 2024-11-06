package com.venancio.dam.tema2Acceso.ejercicio5;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.h2.command.ddl.CreateRole;

import com.mysql.cj.xdevapi.Statement;

public class Ejercicio5 {
	
	public static void main(String[] args) {
		int opcion=-1;
		Scanner sc = new Scanner(System.in);
		
		do {
			menu();
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch(opcion) {
				case 0:
					System.out.println("¡¡Hasta luego!!");
					break;
				case 1:
					if(!crearTablaEmpleado()) {
						System.out.println("Tabla empleado creada con éxito");
					}else {
						System.out.println("[ERROR] Fallo al crear la tabla empleado");
					}
					break;
				case 2:
					if(mostrarTablas()) {
						System.out.println();
					}else {
						System.out.println("[ERROR] Fallo al mostrar las tablas");
					}
					break;
				case 3:
					if(mostrarColumnas()) {
						System.out.println();
					}else {
						System.out.println("[ERROR] Fallo al mostrar las columnas");
					}
					break;
				default:
					System.out.println("[ERROR] Opción no válida");
					break;
			
			}
			
			
		}while(opcion!=0);
		
	}
	
	public static void menu() {
		System.out.println("""
				---MENU---
				0) Salir
				1)  Crear la tabla empleado
				2)  Mostrar las tablas de la base de datos empleados
				3)  Mostrar las columnas de las tablas de la base de datos empleados

				Introduzca la opcion:
					""");
	}
	
	public static boolean crearTablaEmpleado() {
		try (Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadoBBDD", "root", "");
				PreparedStatement consulta=conexion.prepareStatement("CREATE TABLE empleado (NIF VARCHAR(9) PRIMARY KEY,Nombre VARCHAR(50) NOT NULL,Apellidos VARCHAR(50) NOT NULL,Salario DECIMAL(10, 2))");
				Scanner scn = new Scanner(System.in)){
				return consulta.execute();
				
				
		}catch(SQLException e) {
			e.getMessage();
			return false;
		}
	}
	
	public static boolean mostrarTablas() {
		try (Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/empleadoBBDD", "root", "");){
			DatabaseMetaData dbmd = conexion.getMetaData();
			
			ResultSet tablas = dbmd.getTables("empleadobbdd", null, null, new String[] { "TABLE" });

            
            while (tablas.next()) {
                String nombreTabla = tablas.getString("TABLE_NAME");
                System.out.println(nombreTabla);
            } 
			
			return true;	
				
		}catch(SQLException e) {
			e.getMessage();
			return false;
		}
	}
	
	public static boolean mostrarColumnas() {
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empleado", "root", "")) {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet tablas = dbmd.getTables("empleadobbdd", null, null, new String[] { "TABLE" });

            System.out.println("Tablas y sus columnas en la base de datos 'empleado':");
            while (tablas.next()) {
                String nombreTabla = tablas.getString("TABLE_NAME");
                System.out.println("\nTabla: " + nombreTabla);

                ResultSet columnas = dbmd.getColumns(null, null, nombreTabla, null);

                while (columnas.next()) {
                    String nombreColumna = columnas.getString("COLUMN_NAME");
                    String tipoColumna = columnas.getString("TYPE_NAME");
                    int tamanoColumna = columnas.getInt("COLUMN_SIZE");

                   
                    System.out.println("Columna: " + nombreColumna + ", Tipo: " + tipoColumna + ", Tamaño: " + tamanoColumna);
                }

            }
            
            return true;

        } catch (SQLException e) {
        	//e.printStackTrace();)
            return false;
        }
	}	
}
