package umu.tds.AppVideo.GUI;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import dominio.Video;
import tds.video.VideoWeb;


public class VideoLabelLista extends DefaultListCellRenderer{

		private static final long serialVersionUID = 1L;

		@Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	    	
	        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //este no es nuestro JLabel
	        if (value!=null) {
	        	Video video = (Video) value;
	        	VideoWeb vWeb = VentanaPrincipal.getVideoWeb();
		        label.setIcon(vWeb.getThumb(video.getUrl())); //asi que hay que hacer un setIcon y setText
		        label.setText(video.getTitulo()); 
		        label.setHorizontalTextPosition(JLabel.CENTER);
				label.setVerticalTextPosition(JLabel.BOTTOM);
				label.setHorizontalAlignment(SwingConstants.CENTER);
		        label.setVerticalAlignment(SwingConstants.CENTER);
	        }
	        return label;
	    }
}

