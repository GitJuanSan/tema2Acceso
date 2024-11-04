package ejemploAbstractFactoryDAO.factorias;

import ejemploAbstractFactoryDAO.DAOS.AsignaturaDAO;
import ejemploAbstractFactoryDAO.DAOS.ProfesorDAO;
import ejemploAbstractFactoryDAO.enums.FuentesDeDatos;
import ejemploAbstractFactoryDAO.factorias.concretas.MySQLDAOFactory;
import ejemploAbstractFactoryDAO.factorias.concretas.XMLDAOFactory;

public abstract class DAOFactory {
	// Debe haber un método para cada DAO.
	// Las factorías concretas deben implementar este método.
	
	public abstract ProfesorDAO getProfesorDAO();
	public abstract AsignaturaDAO getAsignaturaDAO();

	public final static DAOFactory getDAOFactory(FuentesDeDatos fuenteDeDatos) {
		switch (fuenteDeDatos) {
		case MYSQL:
			return new MySQLDAOFactory();
		case XML:
			return new XMLDAOFactory();
		default:
			return null;
		}
	}

}
