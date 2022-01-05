package umu.tds.AppVideo;

public interface Filtro {
	
	public boolean esVideoOK(Video v, Usuario usuario);
	public int getCodigo();
	public void setCodigo(int codigo);
	public String getNombre();
}
