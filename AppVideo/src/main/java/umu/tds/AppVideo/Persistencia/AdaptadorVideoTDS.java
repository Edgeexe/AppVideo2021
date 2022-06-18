package umu.tds.AppVideo.Persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
		Entidad eVideo=null;
		try {
			eVideo=servPersistencia.recuperarEntidad(video.getCodigo());			
		} catch (NullPointerException e) {}
		if(eVideo !=null) return;
		eVideo=new Entidad();
		
		for (Etiqueta e : video.getEtiquetas()) {
			AdaptadorEtiquetaTDS adaptadorEtiqueta=AdaptadorEtiquetaTDS.getInstance();
			adaptadorEtiqueta.registrarEtiqueta(e);
		}
		eVideo.setNombre("video");
		eVideo.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						  new Propiedad("url",String.valueOf(video.getUrl())),
						  new Propiedad("titulo",String.valueOf(video.getTitulo())),
						  new Propiedad("numRepro",String.valueOf(video.getNumRepro())),
						  new Propiedad("etiquetas",obtenerCodigosEtiquetas(video.getEtiquetas()))
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
				String lineas=obtenerCodigosEtiquetas(video.getEtiquetas());
				prop.setValor(lineas);
				servPersistencia.modificarPropiedad(prop);
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
		List<Etiqueta> etiquetas =  obtenerEtiquetasDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eVideo, "etiquetas"));
		video=new Video(url,titulo,etiquetas);
		video.setNumRepro(Integer.parseInt(numRepro));
		video.setCodigo(eVideo.getId());
		return video;
	}

	private List<Etiqueta> obtenerEtiquetasDesdeCodigos(String lineas) {
		List<Etiqueta> lineasVenta = new LinkedList<Etiqueta>();
		 StringTokenizer strTok = new StringTokenizer(lineas, " ");
		 AdaptadorEtiquetaTDS adaptadorE =
		AdaptadorEtiquetaTDS.getInstance();
		 while (strTok.hasMoreTokens()) {
		 lineasVenta.add(adaptadorE.recuperarEtiqueta(
		Integer.valueOf((String) strTok.nextElement())));
		 }
		 return lineasVenta;
	}

	public List<Video> recuperarTodosVideo() {
		List<Video> videos = new LinkedList<Video>();
		List<Entidad> eVideos = servPersistencia.recuperarEntidades("video");

		for (Entidad eVideo : eVideos) {
			videos.add(recuperarVideo(eVideo.getId()));
		}
		return videos;
	}


	private String obtenerCodigosEtiquetas(HashSet<Etiqueta> hashSet) {
		String lineas = "";
		 for (Etiqueta e : hashSet) {
		 lineas += e.getCodigo() + " ";
		 }
		 return lineas.trim();
	}

}