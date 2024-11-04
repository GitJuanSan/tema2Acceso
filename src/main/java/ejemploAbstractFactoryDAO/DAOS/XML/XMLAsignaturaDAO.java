package ejemploAbstractFactoryDAO.DAOS.XML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import ejemploAbstractFactoryDAO.DAOS.AsignaturaDAO;
import ejemploAbstractFactoryDAO.POJOS.Asignatura;

public class XMLAsignaturaDAO implements AsignaturaDAO {

	public static final File fichero = new File("Ficheros//asignaturas.xml");

	public static Document creaDocumentAsignatura() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		Document documento = builder.parse(fichero);
		return documento;
	}

	@Override
	public Asignatura leer(int id) {
		for (Asignatura asignatura : leerTodos())
			if (asignatura.getId() == id)
				return asignatura;
		return null;
	}

	@Override
	public boolean escribir(Asignatura asignatura) {
		try {
			Document documento = creaDocumentAsignatura();
			crearElementoDeAsignatura(documento, asignatura);
			escribirFichero(documento);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Error leyendo el fichero XML.");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean escribirFichero(Document documento) {

		Source origen = new DOMSource(documento);
		Result resultado = new StreamResult(fichero);

		Transformer transofrmador;
		try {
			transofrmador = TransformerFactory.newInstance().newTransformer();
			transofrmador.transform(origen, resultado);

		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			System.err.println("Error escribiendo el fichero");
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public Element crearElementoDeAsignatura(Document documento, Asignatura asignatura) {

		Element asignaturaXML = documento.createElement("asignatura");
		documento.getDocumentElement().appendChild(asignaturaXML);

		Element idXML = documento.createElement("id");
		Text idTexto = documento.createTextNode(Integer.toString(calcularUltimoId() + 1));
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

		return asignaturaXML;
	}

	public int calcularUltimoId() {
		List<Asignatura> asignaturas = leerTodos();
		return asignaturas.get(asignaturas.size() - 1).getId();
	}

	@Override
	public boolean actualizar(Asignatura asignatura) {

		Document documento;

		try {
			documento = creaDocumentAsignatura();

			Element elementActualizar = buscarAsignaturaElement(documento, asignatura.getId());

			if (elementActualizar == null) {
				System.err.println("La asignatura a modificar no existe en el archivo XML");
				return false;
			}

			// Actualizar los valores de la asignatura
			elementActualizar.getElementsByTagName("nombre").item(0).setTextContent(asignatura.getNombre());
			elementActualizar.getElementsByTagName("creditos").item(0)
					.setTextContent(Float.toString(asignatura.getCreditos()));
			elementActualizar.getElementsByTagName("tipo").item(0).setTextContent(asignatura.getTipo());
			elementActualizar.getElementsByTagName("curso").item(0)
					.setTextContent(Integer.toString(asignatura.getCurso()));
			elementActualizar.getElementsByTagName("cuatrimestre").item(0)
					.setTextContent(Integer.toString(asignatura.getCuatrimestre()));
			elementActualizar.getElementsByTagName("idGrado").item(0)
					.setTextContent(Integer.toString(asignatura.getIdGrado()));

			return escribirFichero(documento);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean eliminar(int idAsignaturaEliminar) {

		Document documento;

		try {
			documento = creaDocumentAsignatura();
			Element elementActualizar = buscarAsignaturaElement(documento, idAsignaturaEliminar);
			elementActualizar.getParentNode().removeChild(elementActualizar);

			return escribirFichero(documento);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	private Element buscarAsignaturaElement(Document documento, int idBuscado) {
		Element elementBuscado = null;

		NodeList asignaturasNodos = documento.getElementsByTagName("asignatura");

		for (int i = 0; i < asignaturasNodos.getLength(); i++) {
			Element asignaturaElemento = (Element) asignaturasNodos.item(i);

			int id = Integer.parseInt(asignaturaElemento.getElementsByTagName("id").item(0).getTextContent());

			if (idBuscado == id)
				return asignaturaElemento;

		}

		return elementBuscado;
	}

	@Override
	public List<Asignatura> leerTodos() {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();

		Document documento = null;
		try {
			documento = creaDocumentAsignatura();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Error leyendo el fichero XML.");
			e.printStackTrace();
		}

		NodeList asignaturasNodos = documento.getElementsByTagName("asignatura");
		for (int i = 0; i < asignaturasNodos.getLength(); i++) {
			Element asignaturaElemento = (Element) asignaturasNodos.item(i);

			int id = Integer.parseInt(asignaturaElemento.getElementsByTagName("id").item(0).getTextContent());
			String nombre = asignaturaElemento.getElementsByTagName("nombre").item(0).getTextContent();
			float creditos = Float
					.parseFloat(asignaturaElemento.getElementsByTagName("creditos").item(0).getTextContent());
			String tipoAsignatura = asignaturaElemento.getElementsByTagName("tipo").item(0).getTextContent();
			int curso = Integer.parseInt(asignaturaElemento.getElementsByTagName("curso").item(0).getTextContent());
			int cuatrimestre = Integer
					.parseInt(asignaturaElemento.getElementsByTagName("cuatrimestre").item(0).getTextContent());
			int idGrado = Integer.parseInt(asignaturaElemento.getElementsByTagName("idGrado").item(0).getTextContent());

			Asignatura asignatura = new Asignatura(id, nombre, creditos, tipoAsignatura, curso, cuatrimestre, idGrado);
			asignaturas.add(asignatura);
		}

		return asignaturas;

	}

}
