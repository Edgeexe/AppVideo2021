package umu.tds.AppVideo.Persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		Entidad eVideo=null;
		try {
			eVideo=servPersistencia.recuperarEntidad(video.getCodigo());			
		} catch (NullPointerException e) {}
		if(eVideo !=null) return;
		eVideo=new Entidad();
		eVideo.setNombre("video");
		eVideo.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						  new Propiedad("url",String.valueOf(video.getUrl())),
						  new Propiedad("titulo",String.valueOf(video.getTitulo())),
						  new Propiedad("numRepro",String.valueOf(video.getNumRepro())),
						  new Propiedad("etiquetas",String.valueOf(video.etiquetaToString()))
						)
					)
				);
		eVideo=servPersistencia.registrarEntidad(eVideo);
		video.setCodigo(eVideo.getId());
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
		Video video = null;
		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, "titulo");
		String url = servPersistencia.recuperarPropiedadEntidad(eVideo, "url");
		String numRepro = servPersistencia.recuperarPropiedadEntidad(eVideo, "numRepro");
		String etiquetas =  servPersistencia.recuperarPropiedadEntidad(eVideo, "etiquetas");;
		String[] e = etiquetas.split(" ");
		video=new Video(url,titulo,Arrays.asList(e));
		video.setNumRepro(Integer.parseInt(numRepro));
		video.setCodigo(eVideo.getId());
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