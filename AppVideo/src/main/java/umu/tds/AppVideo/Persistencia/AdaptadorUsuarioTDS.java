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
import dominio.ListaVideos;
import dominio.Usuario;
import dominio.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{
	private static ServicioPersistencia servPersistencia=FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	private SimpleDateFormat dateFormat; 

	private static AdaptadorUsuarioTDS unicaInstancia;

	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String RECIENTES = "recientes";
	private static final String MIS_LISTAS = "mis_listas";
	private static final String PREMIUM = "premium";

	private Usuario entidadToUsuario(Entidad eUsuario) {
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium= servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		List<Video> recientes=new LinkedList<Video>();
		List<ListaVideos> listas=new LinkedList<ListaVideos>();
		recientes=obtenerRecientesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario,RECIENTES));
		listas=obtenerListasDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario,MIS_LISTAS));

		Date fecha=new Date();
		try {
			fecha = dateFormat.parse(fechaNacimiento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Usuario usuario = new Usuario(nombre, apellidos,fecha,email, login, password);
		usuario.setCodigo(eUsuario.getId());
		usuario.setRecientes(recientes);
		usuario.setListasVideos(listas);
		usuario.setPremium(Boolean.valueOf(premium));

		return usuario;
	}


	public void create(Usuario usuario) {
		Entidad eUser=servPersistencia.recuperarEntidad(usuario.getCodigo());
		if (eUser!=null)
			return;
		 eUser = new Entidad();
		 AdaptadorListaVideosTDS adaptadorLista=AdaptadorListaVideosTDS.getInstance();
		 AdaptadorVideoTDS adaptadorVideo=AdaptadorVideoTDS.getInstance();
		 
		 for(Video reciente: usuario.getRecientes()) {
			 adaptadorVideo.registrarVideo(reciente);
		 }
		 
		 for(ListaVideos lista: usuario.getListasVideos()) {
			 adaptadorLista.registrarListaVideos(lista);
		 }
		 
		eUser.setNombre(USUARIO);
		eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(PREMIUM, String.valueOf(usuario.isPremium())), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getUsuario()), new Propiedad(PASSWORD, usuario.getContrasena()),
				new Propiedad(FECHA_NACIMIENTO, dateFormat.format(usuario.getFecha())),new Propiedad(RECIENTES,obtenerCodigosRecientes(usuario.getRecientes())),
				new Propiedad(MIS_LISTAS,obtenerCodigosListas(usuario.getListasVideos())))));
		eUser = servPersistencia.registrarEntidad(eUser);
		usuario.setCodigo(eUser.getId());
	}

	private String obtenerCodigosListas(List<ListaVideos> listasVideos) {
		String lineas = "";
		 for (ListaVideos listaVideos : listasVideos) {
		 lineas += listaVideos.getCodigo() + " ";
		 }
		 return lineas.trim();
	}


	private String obtenerCodigosRecientes(List<Video> recientes) {
		String lineas = "";
		 for (Video video : recientes) {
		 lineas += video.getCodigo() + " ";
		 }
		 return lineas.trim();
	}
	
	private List<Video> obtenerRecientesDesdeCodigos(String lineas) {
		 List<Video> lineasVenta = new LinkedList<Video>();
		 StringTokenizer strTok = new StringTokenizer(lineas, " ");
		 AdaptadorVideoTDS adaptadorLV = AdaptadorVideoTDS.getInstance();
		 while (strTok.hasMoreTokens()) {
		 lineasVenta.add(adaptadorLV.recuperarVideo(
		Integer.valueOf((String) strTok.nextElement())));
		 }
		 return lineasVenta;
		 }

private List<ListaVideos> obtenerListasDesdeCodigos(String lineas) {
	 List<ListaVideos> lineasVenta = new LinkedList<ListaVideos>();
	 StringTokenizer strTok = new StringTokenizer(lineas, " ");
	 AdaptadorListaVideosTDS adaptadorLV =
	AdaptadorListaVideosTDS.getInstance();
	 while (strTok.hasMoreTokens()) {
	 lineasVenta.add(adaptadorLV.recuperarListaVideos(
	Integer.valueOf((String) strTok.nextElement())));
	 }
	 return lineasVenta;
	 }


	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getContrasena());
				servPersistencia.modificarPropiedad(prop);
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
				servPersistencia.modificarPropiedad(prop);
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
				servPersistencia.modificarPropiedad(prop);
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
				servPersistencia.modificarPropiedad(prop);
			} else if (prop.getNombre().equals(LOGIN)) {
				prop.setValor(usuario.getUsuario());
				servPersistencia.modificarPropiedad(prop);
			} else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(String.valueOf(usuario.isPremium()));
				servPersistencia.modificarPropiedad(prop);
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFecha()));
				servPersistencia.modificarPropiedad(prop);
			} else if(prop.getNombre().equals(MIS_LISTAS)){
				String lineas=obtenerCodigosListas(usuario.getListasVideos());
				prop.setValor(lineas);
				servPersistencia.modificarPropiedad(prop);
			} else if(prop.getNombre().equals(RECIENTES)){
				String lineas=obtenerCodigosRecientes(usuario.getRecientes());
				prop.setValor(lineas);
				servPersistencia.modificarPropiedad(prop);
			}
		}
	}

	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}
	
}