package umu.tds.AppVideo;

public class Video {
	
	private String url;
	private String titulo;
	private int numRepro;
	
	public Video(String url, String titulo, int numRepro) {
		super();
		this.url = url;
		this.titulo = titulo;
		this.numRepro = numRepro;
	}

	public String getUrl() {
		return url;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getNumRepro() {
		return numRepro;
	}

	public void setNumRepro(int numRepro) {
		this.numRepro = numRepro;
	}
	
	
}
