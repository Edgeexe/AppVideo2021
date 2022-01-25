package umu.tds.AppVideo.Persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.*;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorVideoTDS implements IAdaptadorVideoDAO{
	private static AdaptadorVideoTDS instancia;
	private static ServicioPersistencia servPersistencia;
	AdaptadorVideoTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static AdaptadorVideoTDS getInstance() {
		if (instancia == null) {
			return new AdaptadorVideoTDS();
		}
		return instancia;
	}

	public void registrarVideo(Video video) {		
		if (estaRegistrado(video.getCodigo()))
			return;
		Entidad eVideo = new Entidad();
		eVideo.setNombre("video");
		eVideo.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("titulo", String.valueOf(video.getTitulo())),
						new Propiedad("numRepro", String.valueOf(video.getNumRepro())),
						new Propiedad("url", String.valueOf(video.getUrl())),
						new Propiedad("etiquetas", video.etiquetaToString()))));
		eVideo = servPersistencia.registrarEntidad(eVideo);
		video.setCodigo(eVideo.getId());
	}

	private boolean estaRegistrado(int id) {
		try {
			servPersistencia.recuperarEntidad(id);
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	

	public void borrarVideo(Video video) {
		Entidad eVideo;
		eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		servPersistencia.borrarEntidad(eVideo);

	}

	public void modificarVideo(Video video) {
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getCodigo());

		for (Propiedad prop : eVideo.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(video.getCodigo()));
			} else if (prop.getNombre().equals("titulo")) {
				prop.setValor(String.valueOf(video.getTitulo()));
			} else if (prop.getNombre().equals("url")) {
				prop.setValor(String.valueOf(video.getUrl()));
			} else if (prop.getNombre().equals("numRepro")) {
				prop.setValor(String.valueOf(video.getNumRepro()));
			} else if (prop.getNombre().equals("etiquetas")) {
				prop.setValor(video.etiquetaToString());
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Video recuperarVideo(int codigo) {
		Entidad eVideo = servPersistencia.recuperarEntidad(codigo);

		
		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, "titulo");
		String url = servPersistencia.recuperarPropiedadEntidad(eVideo, "url");
		int numRepro = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eVideo, "numRepro"));
		Video video = new Video(titulo, url);
		video.setNumRepro(numRepro);
		video.setCodigo(codigo);
		String etiquetas = servPersistencia.recuperarPropiedadEntidad(eVideo, "etiquetas");
			String[] e = etiquetas.split(" ");
		for (String eti : e)
			video.anadirEtiqueta(new Etiqueta(eti));

		return video;
	}

	public List<Video> recuperarTodosVideo() {
		List<Video> videos = new LinkedList<Video>();
		List<Entidad> eVideos = servPersistencia.recuperarEntidades("video");

		for (Entidad eVideo : eVideos) {
			videos.add(recuperarVideo(eVideo.getId()));
		}
		return videos;
	}

}