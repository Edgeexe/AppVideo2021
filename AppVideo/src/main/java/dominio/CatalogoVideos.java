package dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.AppVideo.Persistencia.FactoriaDAO;

public class CatalogoVideos {
	
	private static CatalogoVideos unicaInstancia;
	private FactoriaDAO factoria;

	private HashMap<String, Video> asistentesPorUrl;
	private HashMap<String, Video> asistentesPorNombre;

	public static CatalogoVideos getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoVideos();
		return unicaInstancia;
	}

	private CatalogoVideos(){
		asistentesPorUrl = new HashMap<String, Video>();
		asistentesPorNombre = new HashMap<String, Video>();
		
		try {
			factoria = FactoriaDAO.getInstancia();		
			List<Video> listaVideos = factoria.getVideoDAO().recuperarTodosVideo();
			for (Video video : listaVideos) {
				asistentesPorUrl.put(video.getUrl(), video);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Video> getVideos() throws DAOException {
		return new LinkedList<Video>(asistentesPorNombre.values());
	}
	
	
	public Video getVideo(String Url) {
		return asistentesPorUrl.get(Url);
	}
	
	public void addVideo(Video video) {
		asistentesPorUrl.put(video.getUrl(), video);
	}
	
	public void removeVideo(Video video) {
		asistentesPorUrl.remove(video.getUrl());
	}

	public LinkedList<Video> getVideosdeCodigo(List<String> codigos) {
		// TODO Auto-generated method stub
		return null;
	}

}
