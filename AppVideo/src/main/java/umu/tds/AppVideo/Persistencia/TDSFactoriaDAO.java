package umu.tds.AppVideo.Persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}
	
	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return new AdaptadorUsuarioTDS();
	}

	@Override
	public IAdaptadorVideoDAO getVideoDAO() {
		return new AdaptadorVideoTDS();
	}

	@Override
	public IAdaptadorListaVideosDAO getListaVideosDAO() {
		return new AdaptadorListaVideosTDS();
	}

}