package dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.AppVideo.Persistencia.FactoriaDAO;

public class CatalogoEtiquetas {
	private static CatalogoEtiquetas unicaInstancia;
	private FactoriaDAO factoria;

	private HashMap<String, Etiqueta> asistentesPorNombre;

	public static CatalogoEtiquetas getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoEtiquetas();
		return unicaInstancia;
	}

	public CatalogoEtiquetas (){
		asistentesPorNombre = new HashMap<String,  Etiqueta>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List< Etiqueta> lista = factoria.getEtiquetaDAO().recuperarTodasEtiquetas();
			for (Etiqueta l : lista) {
				asistentesPorNombre.put(l.getEtiqueta(), l);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Etiqueta> getEtiquetas() throws DAOException {
		return new LinkedList<Etiqueta>(asistentesPorNombre.values());
	}
	
	public Etiqueta getEtiqueta(Etiqueta l) {
		return asistentesPorNombre.get(l.getEtiqueta());
	}
	
	public void addEtiqueta(Etiqueta lista) {
		asistentesPorNombre.put(lista.getEtiqueta(), lista);
	}
	
	public void removeEtiqueta (Etiqueta lista) {
		asistentesPorNombre.remove(lista.getEtiqueta(), lista);
	}
}
