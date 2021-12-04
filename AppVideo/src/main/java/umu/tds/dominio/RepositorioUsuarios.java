package umu.tds.dominio;

import java.util.HashMap;

public class RepositorioUsuarios {
	
	private static RepositorioUsuarios unicaInstancia;
	//Se necesita persistencia para DAO
	
	private HashMap<String, Usuario> asistentesPorLogin;
	
	/*private RepositorioUsuarios(){
	 * asistentesPorLogin = new HashMap<String, Usuario>();
	 * Para el resto se necesita persistencia
	 * }
	 */
	public void addUsuario(Usuario usuario) {
		asistentesPorLogin.put(usuario.getNombre(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		asistentesPorLogin.remove(usuario.getNombre());
	}
}
