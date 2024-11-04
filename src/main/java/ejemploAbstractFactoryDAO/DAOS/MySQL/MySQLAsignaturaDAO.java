package ejemploAbstractFactoryDAO.DAOS.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ejemploAbstractFactoryDAO.DAOS.AsignaturaDAO;
import ejemploAbstractFactoryDAO.POJOS.Asignatura;
import ejemploAbstractFactoryDAO.factorias.concretas.MySQLDAOFactory;

public class MySQLAsignaturaDAO implements AsignaturaDAO {

	@Override
	public Asignatura leer(int id) {
		Asignatura asignaturaBuscada = null;

		try (Connection conexion = MySQLDAOFactory.creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM asignatura WHERE id = ?")) {
			consulta.setInt(1, id);
			ResultSet resultadoConsulta = consulta.executeQuery();

			if (resultadoConsulta.next()) {
				asignaturaBuscada = new Asignatura(resultadoConsulta.getInt("id"),
						resultadoConsulta.getString("nombre"), resultadoConsulta.getInt("creditos"),
						resultadoConsulta.getString("tipo"), resultadoConsulta.getInt("curso"),
						resultadoConsulta.getInt("cuatrimestre"), resultadoConsulta.getInt("id_grado"));
			}

		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de recuperar la asignatura:");
			e.printStackTrace();
		}
		return asignaturaBuscada;
	}

	@Override
	public boolean escribir(Asignatura asignatura) {
		try (Connection conexion = MySQLDAOFactory.creaConexion();
				PreparedStatement consulta = conexion.prepareStatement(
						"INSERT INTO " + "asignatura (nombre, creditos, tipo, curso, cuatrimestre, id_grado)"
								+ " VALUES (?, ?, ?, ?, ?, ?)")) {

			consulta.setString(1, asignatura.getNombre());
			consulta.setFloat(2, asignatura.getCreditos());
			consulta.setString(3, asignatura.getTipo());
			consulta.setInt(4, asignatura.getCurso());
			consulta.setInt(5, asignatura.getCuatrimestre());
			consulta.setInt(6, asignatura.getIdGrado());

			int filasAfectadas = consulta.executeUpdate();

			// Aquí se podría mejorar la comprobación de errores
			return filasAfectadas == 1;

		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de escribir la asignatura:");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizar(Asignatura asignatura) {
		try (Connection conexion = MySQLDAOFactory.creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("UPDATE asignatura SET"
						+ " nombre=?, creditos=?, tipo=?, curso=?, cuatrimestre=?, id_grado=? WHERE id=?")) {

			consulta.setString(1, asignatura.getNombre());
			consulta.setFloat(2, asignatura.getCreditos());
			consulta.setString(3, asignatura.getTipo());
			consulta.setInt(4, asignatura.getCurso());
			consulta.setInt(5, asignatura.getCuatrimestre());
			consulta.setInt(6, asignatura.getIdGrado());
			consulta.setInt(7, asignatura.getId());

			int filasAfectadas = consulta.executeUpdate();

			// Aquí se podría mejorar la comprobación de errores
			return filasAfectadas == 1;

		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de modificar la asignatura:");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Asignatura> leerTodos() {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();

		try (Connection conexion = MySQLDAOFactory.creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM asignatura")) {
			ResultSet resultadoConsulta = consulta.executeQuery();
			while (resultadoConsulta.next()) {
				Asignatura nuevaAsignatura = new Asignatura(resultadoConsulta.getInt("id"),
						resultadoConsulta.getString("nombre"), resultadoConsulta.getInt("creditos"),
						resultadoConsulta.getString("tipo"), resultadoConsulta.getInt("curso"),
						resultadoConsulta.getInt("cuatrimestre"), resultadoConsulta.getInt("id_grado"));
				asignaturas.add(nuevaAsignatura);
			}
		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de leer las asignaturas:");
			e.printStackTrace();
		}

		return asignaturas;
	}

	@Override
	public boolean eliminar(int idAsignaturaEliminar) {
		try (Connection conexion = MySQLDAOFactory.creaConexion();
				PreparedStatement consulta = conexion.prepareStatement("DELETE FROM asignatura WHERE id=?")) {
			consulta.setInt(1, idAsignaturaEliminar);

			int filasAfectadas = consulta.executeUpdate();

			// Aquí se podría mejorar la comprobación de errores
			return filasAfectadas == 1;

		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de eliminar la asignatura:");
			e.printStackTrace();
			return false;
		}
	}

}
