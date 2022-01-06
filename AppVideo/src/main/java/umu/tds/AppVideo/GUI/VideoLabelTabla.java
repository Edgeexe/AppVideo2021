package umu.tds.AppVideo.GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import dominio.Video;
import tds.video.VideoWeb;

public class VideoLabelTabla extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable jtable, Object obj, boolean bln, boolean bln1, int i, int i1) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, obj, bln, bln1, i, i1);
        if(obj!=null) {
            VideoWeb vWeb = VentanaPrincipal.getVideoWeb();
            Video video = (Video) obj;
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setIcon(vWeb.getThumb(video.getUrl()));
            label.setText(video.getTitulo());
        } 
        return label;
	}
}
