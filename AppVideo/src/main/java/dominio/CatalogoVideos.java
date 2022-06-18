package dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.AppVideo.Persistencia.FactoriaDAO;

public class CatalogoVideos {
	
	private static CatalogoVideos unicaInstancia;
	private FactoriaDAO factoria;

	private HashMap<String, Video> asistentesPorUrl;

	public static CatalogoVideos getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoVideos();
		return unicaInstancia;
	}

	private CatalogoVideos(){
		asistentesPorUrl = new HashMap<String, Video>();
		
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
	
	public List<Video> getVideos(Usuario u, String... etiquetas) throws DAOException {
		if(etiquetas.length==0)
			return  asistentesPorUrl.values().stream()
					.filter(vid-> u.comprobarVideo(vid))
					.collect(Collectors.toList());
		
		List<Etiqueta> etiqueta=new LinkedList<Etiqueta>();
		for (String string : etiquetas) {
			Etiqueta e =new Etiqueta(string);
			etiqueta.add(e);
		}
		
		 return asistentesPorUrl.values().stream()
		.filter(vid->vid.getEtiquetas().containsAll(etiqueta) && u.comprobarVideo(vid))
		.collect(Collectors.toList());
	}

	public List<Video> getVideos(String titulo,Usuario u ,String... etiquetas) {
		if(etiquetas.length==0)
			return asistentesPorUrl.values().stream()
					.filter(vid -> vid.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
					.filter(vid-> u.comprobarVideo(vid))
					.collect(Collectors.toList());
		
		List<Etiqueta> etiqueta=new LinkedList<Etiqueta>();
		for (String string : etiquetas) {
			Etiqueta e =new Etiqueta(string);
			etiqueta.add(e);
		}
		
		return asistentesPorUrl.values().stream()
				.filter(vid -> vid.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
				.filter(vid->vid.getEtiquetas().containsAll(etiqueta) && u.comprobarVideo(vid))
				.collect(Collectors.toList());
	}

	public List<Video> getTodosVideos() {
		// TODO Auto-generated method stub
		return  new LinkedList<Video>(asistentesPorUrl.values());
	}

}
