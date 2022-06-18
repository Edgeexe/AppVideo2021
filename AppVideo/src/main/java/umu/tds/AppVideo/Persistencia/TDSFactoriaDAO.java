package umu.tds.AppVideo.Persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}
	
	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorVideoDAO getVideoDAO() {
		return  AdaptadorVideoTDS.getInstance();
	}

	@Override
	public IAdaptadorListaVideosDAO getListaVideosDAO() {
		return AdaptadorListaVideosTDS.getInstance();
	}

	@Override
	public IAdaptadorEtiquetaDAO getEtiquetaDAO() {
		return AdaptadorEtiquetaTDS.getInstance();
	}

}