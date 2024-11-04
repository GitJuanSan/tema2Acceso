package ejemploAbstractFactoryDAO.factorias.concretas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ejemploAbstractFactoryDAO.DAOS.AsignaturaDAO;
import ejemploAbstractFactoryDAO.DAOS.ProfesorDAO;
import ejemploAbstractFactoryDAO.DAOS.MySQL.MySQLAsignaturaDAO;
import ejemploAbstractFactoryDAO.DAOS.MySQL.MySQLProfesorDAO;
import ejemploAbstractFactoryDAO.factorias.DAOFactory;

public class MySQLDAOFactory extends DAOFactory {

	public static final String DBURL = "jdbc:mysql://localhost:3306/universidad";

	public static Connection creaConexion() throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
		return conexion;
	}
	
	@Override
	public ProfesorDAO getProfesorDAO() {
		return new MySQLProfesorDAO();
	}

	@Override
	public AsignaturaDAO getAsignaturaDAO() {
		return new MySQLAsignaturaDAO();
	}

}
