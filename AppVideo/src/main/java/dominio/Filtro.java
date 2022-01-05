package dominio;

public interface Filtro {
	
	 boolean esVideoOk(Video video,Usuario usuario);
	int getCodigo();
	void setCodigo(int codigo);
	String getNombre();

}
