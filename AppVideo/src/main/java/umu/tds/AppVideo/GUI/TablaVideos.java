package umu.tds.AppVideo.GUI;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Video;
import tds.video.VideoWeb;

public class TablaVideos extends AbstractTableModel{
	private static final int NUM_COLUMNAS = 6;
	private static final long serialVersionUID = 1L;
	private LinkedList<LineaVideos> videos;
	private String[] columnNames = {"Video 1","Video 2", "Video 3", "Video 4", "Video 5", "Video 6"};

	public TablaVideos(){
		this.videos = new LinkedList<LineaVideos>();
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() { 
		return videos.size(); 
	}
	
	public int getColumnCount() { 
		return NUM_COLUMNAS; 
	}
	
	public Video getValueAt(int row, int col) {
		Video video = null;
		LineaVideos linea = videos.get(row);
		switch (col) {						 
			case 0 : 
				video = linea.getVideo1(); 
				break;
			case 1 : 
				video = linea.getVideo2();
				break;
			case 2 : 
				video = linea.getVideo3();
				break;
			case 3 : 
				video = linea.getVideo4();
				break;
			case 4 : 
				video = linea.getVideo5();
				break;
			case 5 : 
				video = linea.getVideo6();
				break;
		}
		return video;
	}
	
	public void addRow(LineaVideos gVideos) {
		videos.add(gVideos);
	}
	
	//modificacion para incluir el titulo para mostrarlo en la lista
	public void rellenarTabla(List<Video> videos, VideoWeb vWeb) {
		Video aux[] = new Video[6];
		boolean escribir = false;
		if (videos.size() > 6) {
			for(int i = 0; i < videos.size(); i++) {
				Video elemento = videos.get(i);
				switch (i%6) {
					case 0:
						aux[0] = elemento;
						break;
					case 1:
						aux[1] = elemento;
						break;
					case 2:
						aux[2] = elemento;
						break;
					case 3:
						aux[3] = elemento;
					case 4:
						aux[4] = elemento;
					case 5:
						aux[5] = elemento;
						escribir = true;
						break;
					default:
						break;
				}
				if (escribir) {
					this.addRow(new LineaVideos(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5]));
					escribir = false;
				}
			}
		}
		else {
			for(int i = 0; i < videos.size(); i++) {
				Video elemento = videos.get(i);
				switch (i%6) {
					case 0:
						aux[0] =elemento;
						break;
					case 1:
						aux[1] = elemento;
						break;
					case 2:
						aux[2] = elemento;
					case 3:
						aux[3] = elemento;
					case 4:
						aux[4] = elemento;
						break;
					default:
						break;
				}
			}
		}
		if ((videos.size() % 6) != 0) {
			switch (videos.size() %6) {
			case 1:
				this.addRow(new LineaVideos(aux[0]));
				break;
			case 2:
				this.addRow(new LineaVideos(aux[0], aux[1]));
				break;
			case 3:
				this.addRow(new LineaVideos(aux[0], aux[1], aux[2], aux[3]));
				break;
			case 4:
				this.addRow(new LineaVideos(aux[0], aux[1], aux[2], aux[3], aux[4]));
				break;
			case 5:
				this.addRow(new LineaVideos(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5]));
				break;
			default:
				break;
			}
		}
	}
	
	public void removeRow(int i) {
		if (i <= videos.size())
			videos.remove(i);
	}
}
