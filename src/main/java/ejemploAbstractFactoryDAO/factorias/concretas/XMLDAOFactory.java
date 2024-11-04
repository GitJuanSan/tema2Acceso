package ejemploAbstractFactoryDAO.factorias.concretas;

import ejemploAbstractFactoryDAO.DAOS.AsignaturaDAO;
import ejemploAbstractFactoryDAO.DAOS.ProfesorDAO;
import ejemploAbstractFactoryDAO.DAOS.XML.XMLAsignaturaDAO;
import ejemploAbstractFactoryDAO.DAOS.XML.XMLProfesorDAO;
import ejemploAbstractFactoryDAO.factorias.DAOFactory;

public class XMLDAOFactory extends DAOFactory{
	
	@Override
	public ProfesorDAO getProfesorDAO() {
		return new XMLProfesorDAO();
	}

	@Override
	public AsignaturaDAO getAsignaturaDAO() {
		return new XMLAsignaturaDAO();
	}

}
