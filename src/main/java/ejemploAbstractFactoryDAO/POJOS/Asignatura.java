package ejemploAbstractFactoryDAO.POJOS;

public class Asignatura {
	private int id;
	private String nombre;
	private float creditos;
	private String tipo;
	private int curso;
	private int cuatrimestre;
	private int idGrado;
	//No se maneja el idProfesor para no aumentar la dificultad del ejercicio

	public Asignatura(int id, String nombre, float creditos, String tipo, int curso, int cuatrimestre, int idGrado) {
		this.id = id;
		this.nombre = nombre;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		this.cuatrimestre = cuatrimestre;
		this.idGrado = idGrado;
	}

	public Asignatura(String nombre, float creditos, String tipo, int curso, int cuatrimestre, int idGrado) {
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

	// Getters y Setters

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public float getCreditos() {
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

	public void setCreditos(float creditos) {
		this.creditos = creditos;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	public void setCuatrimestre(int cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}
	
	

}
