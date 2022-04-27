package dominio;

import java.util.*;

public class ListaVideos {
	
	private int codigo;
	private String nombre;
	private List<Video> videos;
	
	
	public ListaVideos(String nombre,Video ...videos) {
		this.codigo=0;
		this.nombre = nombre;
		if(videos.length==0) this.videos=new LinkedList<Video>();
		else {
			this.videos=Arrays.asList(videos);
		}
	}
	

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Video> getListaVideos() {
		return videos;
	}
	
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public int getCodigo() {
		return this.codigo;
	}



	public void addVideo(Video video) {
			for (Video vi : videos) {
				if(vi.getUrl().equals(video.getUrl()))
					return;
			}
			videos.add(video);
		}
	
	public void removeVideo(Video video) {
		this.videos.remove(video);
	}
	
	public String videosToString() {		// método para la construcción de propiedades del método DAO
		String videos = "";
		for(Video video : this.videos) {
			videos += video.getCodigo() + " ";
		}
		return videos;
	}
	
	

}
