package umu.tds.AppVideo;

import java.util.Objects;

public class Etiqueta {

		private String etiqueta;
		private int codigo = 0;
		
		public Etiqueta(String etiqueta) {
			this.etiqueta = etiqueta;
		}

		public int getCodigo() {
			return codigo;
		}
	
		public String getEtiqueta() {
			return etiqueta;
		}
		
		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}


		public void setEtiqueta(String etiqueta) {
			this.etiqueta = etiqueta;
		}

		@Override
		public int hashCode() {
			return Objects.hash(etiqueta);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;
			Etiqueta other = (Etiqueta) obj;
			return Objects.equals(etiqueta, other.etiqueta);
		}
		
}
