package umu.tds.AppVideo.Persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.CatalogoVideos;
import dominio.Filtro;
import dominio.ListaVideos;
import dominio.Usuario;
import dominio.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{
	private static ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;
	
	private static final String USUARIO = "user";
	private static final String NOMBRE = "name";
	private static final String APELLIDOS = "last_name";
	private static final String EMAIL = "email";
	private static final String NOMBRE_USUARIO = "nombre_usuario";
	private static final String CONTRASENA = "contrasena";
	private static final String FECHA_NACIMIENTO = "fecha_nacimiento";
	private static final String VIDEOS_RECIENTES = "videos_recientes";
	private static final String LISTAS_VIDEOS = "listas_videos";
	private static final String PREMIUM = "premium";

	AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	public void registrarUsuario(Usuario usuario) {
		boolean registered = true;
		try {
			servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
			registered = false;
		}
		if (registered)
			return;
		Entidad eUser = new Entidad();
		eUser.setNombre(USUARIO);
		eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(USUARIO, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(NOMBRE_USUARIO, usuario.getUsuario()), new Propiedad(CONTRASENA, usuario.getContrasena()),
				new Propiedad(FECHA_NACIMIENTO,usuario.getFecha().toString()),
				new Propiedad(VIDEOS_RECIENTES,usuario.getRecientes().toString()),
				new Propiedad( LISTAS_VIDEOS,usuario.getListasVideos().toString()))));
		eUser = servPersistencia.registrarEntidad(eUser);
		usuario.setCodigo(eUser.getId());
	}

	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario;	
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.borrarEntidad(eUsuario);

	}

	public void modificarUsuario(Usuario usuario) {

		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(usuario.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals("apellidos")) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(dateFormat.format(usuario.getFecha()));
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals("usuario")) {
				prop.setValor(usuario.getUsuario());
			} else if (prop.getNombre().equals("contrasena")) {
				prop.setValor(usuario.getContrasena());
			} else if (prop.getNombre().equals("premium")) {
				prop.setValor(String.valueOf(usuario.isPremium()));
			} else if (prop.getNombre().equals("filtro")) {
				prop.setValor(String.valueOf(usuario.getFiltros().getCodigo())); 
			} else if (prop.getNombre().equals("listasVideos")) {
				prop.setValor(usuario.ListaVideosToString());
			} else if (prop.getNombre().equals("recientes")) {
				prop.setValor(usuario.videosRecienetsToString());
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	public Usuario recuperarUsuario(int codigo) {
		Entidad eUsuario;
		String listasVideos;
		String recientes;
		String nombre;
		String apellidos;
		String email;
		String usuario;
		String contrasena;
		String premium;

		eUsuario = servPersistencia.recuperarEntidad(codigo);

		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		Date fecha = null;
		try {
			fecha = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE_USUARIO);
		contrasena = servPersistencia.recuperarPropiedadEntidad(eUsuario, CONTRASENA);
		premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		recientes = (servPersistencia.recuperarPropiedadEntidad(eUsuario, VIDEOS_RECIENTES));
		listasVideos = (servPersistencia.recuperarPropiedadEntidad(eUsuario, LISTAS_VIDEOS));
		
		Usuario usuarioFinal = new Usuario(nombre, apellidos, fecha, email, usuario, contrasena);
		usuarioFinal.setPremium(Boolean.getBoolean(premium));
		usuarioFinal.setCodigo(codigo);
		
		if(!recientes.equals("")) {
			String[] videos = recientes.split(" ");
			List<String> codigos = new ArrayList<String>(Arrays.asList(videos));
			LinkedList<Video> recientesVideos = (LinkedList<Video>) CatalogoVideos.getUnicaInstancia().getVideosdeCodigo(codigos);
		usuarioFinal.setRecientes(recientesVideos);
		}
		return usuarioFinal;
	}

	public List<Usuario> recuperarTodosUsuarios() {

		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}
	
}