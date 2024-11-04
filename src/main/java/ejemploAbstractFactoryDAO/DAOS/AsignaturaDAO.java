package ejemploAbstractFactoryDAO.DAOS;

import java.util.List;

import ejemploAbstractFactoryDAO.POJOS.Asignatura;

public interface AsignaturaDAO {

	public Asignatura leer(int id);

	public boolean escribir(Asignatura asignatura);

	public boolean actualizar(Asignatura asignatura);

	public boolean eliminar(int idAsignaturaEliminar);

	public List<Asignatura> leerTodos();

}
