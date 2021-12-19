package umu.tds.AppVideo;

public class Usuario {
	
	private String nombre;
	private String email;
	private boolean esPremium;
	
	public Usuario(String nombre, String apellidos, String email, boolean esPremium) {
		super();
		this.nombre = nombre+" "+apellidos;
		this.email = email;
		this.esPremium = esPremium;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

}
