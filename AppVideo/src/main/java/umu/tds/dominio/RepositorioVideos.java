package umu.tds.dominio;
import java.util.*;

public class RepositorioVideos {
	
	private static RepositorioVideos unicaInstancia;
	private HashMap<String,Video> videosPorReproducciones;
	
	/*private RepositorioVideos(){
	 * asistentesPorLogin = new HashMap<String, Usuario>();
	 * Para el resto se necesita persistencia
	 * }
	 */
	
	public void addVideo(Video video) {
		//Metodo para añadir video
		videosPorReproducciones.put(video.getUrl(),video);
	}
	
	public void removeVideo(Video video) {
		//Metodo para eliminar video
		videosPorReproducciones.remove(video.getUrl());
	}
	
	public Video findVideo(Video video) {
		//Metodo para encontrar un video
		return videosPorReproducciones.get(video.getUrl());
	}

}
