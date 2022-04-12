package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dominio.ListaVideos;
import dominio.Video;

class TestListaVideos {
	Video aux1;
	Video aux2;
	Video aux3;
	Video aux4;
	Video aux5;
	ListaVideos listaAux;
	List<Video> listaVideos;
	List<Video> listaVideos2;
	
	@Before
	void setUp() throws Exception {
		List<String> etiquetas=new LinkedList<String>();
		etiquetas.add("Musica");
		aux1 = new Video("https://www.youtube.com/watch?v=DmxAzRwgDkc","1",etiquetas);
		aux2 = new Video("https://www.youtube.com/watch?v=xIisDEMILao","2",etiquetas);
		aux3 = new Video("https://www.youtube.com/watch?v=IGCIX3kLzLw","3",etiquetas);
		aux4 = new Video("https://www.youtube.com/watch?v=7zJleRoHt08","4",etiquetas);
		aux5 = new Video("https://www.youtube.com/watch?v=7zJleRoHt08","5",etiquetas);
		listaAux = new ListaVideos("Lista Test");
		listaVideos = new LinkedList<Video>();
		listaVideos2 = new LinkedList<Video>();
		Collections.addAll(listaVideos, aux1, aux2, aux3, aux4);
		Collections.addAll(listaVideos2, aux1, aux2, aux3, aux4, aux5);
		listaVideos.stream().forEach(u->listaAux.addVideo(u));
	}

	@Test
	void testGetNombre() {
		assertEquals("Lista Test", listaAux.getNombre());
	}

	@Test
	void testGetListaVideos() {
		assertEquals(listaVideos, listaAux.getListaVideos());
	}

	@Test
	void testAddVideo() {
		listaAux.addVideo(aux5);
		assertEquals(listaVideos2, listaAux.getListaVideos());
	}

}
