package umu.tds.AppVideo.Persistencia;

import java.util.List;

import dominio.ListaVideos;
import dominio.Usuario;

public interface IAdaptadorUsuarioDAO {

	void create(Usuario asistente);
	boolean delete(Usuario asistente);
	void update(Usuario asistente);
	Usuario get(int id);
	List<Usuario> getAll();
	void registrarPlaylist(ListaVideos playlist);
	void borrarPlaylist(ListaVideos playlist);
	void actualizarPlaylist(ListaVideos nueva);
}
