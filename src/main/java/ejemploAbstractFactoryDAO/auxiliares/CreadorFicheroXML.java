package ejemploAbstractFactoryDAO.auxiliares;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import ejemploAbstractFactoryDAO.DAOS.MySQL.MySQLAsignaturaDAO;
import ejemploAbstractFactoryDAO.POJOS.Asignatura;

/**
 * Crea el fichero XML a partir de la base de datos.
 */
public class CreadorFicheroXML {

	public static void crearXMLAlumnos() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = factoria.newDocumentBuilder();
			DOMImplementation implementacion = builder.getDOMImplementation();
			Document documento = implementacion.createDocument(null, "asignaturas", null);
			documento.setXmlVersion("1.0");

			for (Asignatura asignatura : new MySQLAsignaturaDAO().leerTodos()) {
				Element asignaturaXML = documento.createElement("asignatura");
				documento.getDocumentElement().appendChild(asignaturaXML);

				Element idXML = documento.createElement("id");
				Text idTexto = documento.createTextNode(Integer.toString(asignatura.getId()));
				idXML.appendChild(idTexto);
				asignaturaXML.appendChild(idXML);
				
				Element nombreXML = documento.createElement("nombre");
				Text nombreTexto = documento.createTextNode(asignatura.getNombre());
				nombreXML.appendChild(nombreTexto);
				asignaturaXML.appendChild(nombreXML);
				
				Element creditosXML = documento.createElement("creditos");
				Text creditosTexto = documento.createTextNode(Float.toString(asignatura.getCreditos()));
				creditosXML.appendChild(creditosTexto);
				asignaturaXML.appendChild(creditosXML);
				
				Element tipoXML = documento.createElement("tipo");
				Text tipoTexto = documento.createTextNode(asignatura.getTipo());
				tipoXML.appendChild(tipoTexto);
				asignaturaXML.appendChild(tipoXML);
				
				Element cursoXML = documento.createElement("curso");
				Text cursoTexto = documento.createTextNode(Integer.toString(asignatura.getCurso()));
				cursoXML.appendChild(cursoTexto);
				asignaturaXML.appendChild(cursoXML);
				
				Element cuatrimestreXML = documento.createElement("cuatrimestre");
				Text cuatrimestreTexto = documento.createTextNode(Integer.toString(asignatura.getCuatrimestre()));
				cuatrimestreXML.appendChild(cuatrimestreTexto);
				asignaturaXML.appendChild(cuatrimestreXML);
				
				Element idGradoXML = documento.createElement("idGrado");
				Text idGradoTexto = documento.createTextNode(Integer.toString(asignatura.getIdGrado()));
				idGradoXML.appendChild(idGradoTexto);
				asignaturaXML.appendChild(idGradoXML);
			}
			
			File fichero = new File("Ficheros/asignaturas.xml");
			Source origen = new DOMSource(documento);
			Result resultado = new StreamResult(fichero);
			
			Transformer transofrmador = TransformerFactory.newInstance().newTransformer();
			
			transofrmador.transform(origen, resultado);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
