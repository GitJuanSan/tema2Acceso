package com.venancio.dam.tema2Acceso.ejercicio7;

public class Ejercicio7Asignatura {
	private int id;
	private String nombre;
	private int creditos;
	private String tipo;
	private int curso;
	private int cuatrimestre;
	private int idGrado;

	public Ejercicio7Asignatura(int id, String nombre, int creditos, String tipo, int curso, int cuatrimestre,
			int idGrado) {
		this.id = id;
		this.nombre = nombre;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		this.cuatrimestre = cuatrimestre;
		this.idGrado = idGrado;
	}

	@Override
	public String toString() {

		return "Asignatura{" + "id=" + id + ", nombre='" + nombre + '\'' + ", creditos=" + creditos + ", tipo='" + tipo
				+ '\'' + ", curso=" + curso + ", cuatrimestre=" + cuatrimestre + ", idGrado=" + idGrado + '}';
	}
	
	//Getters y Setters

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCreditos() {
		return creditos;
	}

	public String getTipo() {
		return tipo;
	}

	public int getCurso() {
		return curso;
	}

	public int getCuatrimestre() {
		return cuatrimestre;
	}

	public int getIdGrado() {
		return idGrado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

}
