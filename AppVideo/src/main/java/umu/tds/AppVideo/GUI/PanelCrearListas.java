package umu.tds.AppVideo.GUI;

import java.util.List;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;

import dominio.ListaVideos;
import dominio.Video;
import tds.video.VideoWeb;
import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.Controlador.Controlador;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;

public class PanelCrearListas extends JPanel {
	
	private JTable tablaVideosCrearLista;
	private JTable tablaVideosVertical;
	
	private static Controlador appVideo = Controlador.getUnicaInstancia();
	private JTextField lista;
	private JTextField nom_video;
	private ListaVideos playlist;
	
	public PanelCrearListas() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		setBounds(0, 0, 1073, 624);
		
		//Componente para reproducir los videos
		VideoWeb vWeb = VentanaPrincipal.getVideoWeb();
		
		//Indica la lista que se esta creando o modificando
		JLabel listaActual = new JLabel();
		listaActual.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel nombre_lista = new JLabel("Nombre Lista");
		nombre_lista.setForeground(Color.WHITE);
		nombre_lista.setBounds(77, 11, 86, 14);
		add(nombre_lista);
		
		//Campo para introducir el nombre de la lista
		lista = new JTextField();
		lista.setBounds(21, 36, 179, 20);
		add(lista);
		lista.setColumns(10);
		
		//Tabla para buscar los videos
		tablaVideosCrearLista = new JTable();
		tablaVideosCrearLista.setBounds(1, 26, 450, 0);
		tablaVideosCrearLista.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		//Modelo de tabla con los videos
		TablaVideos tm2 = new TablaVideos(6);
				
		tablaVideosCrearLista.setModel(tm2);
		tablaVideosCrearLista.setRowHeight(175); 
		tablaVideosCrearLista.getTableHeader().setUI(null);  
		TableColumnModel colModel = tablaVideosCrearLista.getColumnModel();
		for(int i=0; i<6; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideosCrearLista.setShowGrid(false);
		
		//ScrollPane de la tabla de videos
		JScrollPane scrollPane_1 = new JScrollPane(tablaVideosCrearLista);
		scrollPane_1.setBounds(272, 67, 770, 437);
		add(scrollPane_1);
		
		//Tabla de videos para mostar los videos de la lista
		tablaVideosVertical = new JTable();
		tablaVideosVertical.setBounds(1, 26, 450, 0);
		tablaVideosVertical.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		//Modelo de tabla con los videos
		TablaVideos tm1 = new TablaVideos(1);
		
		tablaVideosVertical.setModel(tm1);
		tablaVideosVertical.setRowHeight(175); 
		tablaVideosVertical.getTableHeader().setUI(null);  
		TableColumnModel colModel2=tablaVideosVertical.getColumnModel();
		for(int i=0; i<1; i++)
		{
			TableColumn col=colModel2.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideosVertical.setShowGrid(false);
		
		//ScrollPane de la tabla de videos
		JScrollPane scrollPane = new JScrollPane(tablaVideosVertical);
		scrollPane.setBounds(21, 100, 179, 426);
		add(scrollPane);
		
		//Botón para buscar la lista
		JButton buscarLista = new JButton("Buscar");
		buscarLista.setBounds(20, 67, 89, 23);
		add(buscarLista);
		buscarLista.addActionListener(ev->{
				ListaVideos pl=appVideo.buscarPlaylist(lista.getText());
				if(pl!=null) {
					int decision=JOptionPane.showConfirmDialog(tablaVideosCrearLista,"¿Le gustría editar la playlist existente?","La Playlist existe",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(decision == JOptionPane.YES_OPTION) {
						playlist=pl;
						lista.setText("");
						listaActual.setText("Modificando: "+playlist.getNombre());
						for(int i=0;i<tablaVideosVertical.getRowCount();i++) tm1.removeRow(i);
						for (Video video : playlist.getListaVideos())tm1.addRow(new LineaVideos(video));
						tm1.fireTableDataChanged();
					}
				}
				else {
					int decision=JOptionPane.showConfirmDialog(tablaVideosCrearLista,"¿Le gustría crear la playlist?","La Playlist no existe",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(decision == JOptionPane.YES_OPTION) {
						playlist=appVideo.crearPlaylist(lista.getText());
						lista.setText("");
						listaActual.setText("Modificando: "+playlist.getNombre());
					}
						
				}
				validate();	
		});
		
		//Botón para eliminar la lista
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(ev-> {
				int output = JOptionPane.showConfirmDialog(tablaVideosVertical, "Borrar Playlist","¿Quieres borrar la playlist?", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if (output == JOptionPane.YES_OPTION) {
					appVideo.eliminarPlaylist(playlist);
					int filas = tablaVideosVertical.getRowCount();
					for (int i = 0; i< filas; i++) tm1.removeRow(i);
					tm1.fireTableDataChanged();
					lista.setText("");
					listaActual.setText("");
					repaint();
					revalidate();
					validate();
				}
			
		});
		
		eliminar.setBounds(111, 67, 89, 23);
		add(eliminar);
		
		//Botón para añadir video a la lista
		JButton anadirBoton = new JButton("Añadir");
		anadirBoton.setBounds(10, 546, 89, 23);
		add(anadirBoton);
		
		anadirBoton.addActionListener(ev-> {
				Video video=(Video) tablaVideosCrearLista.getValueAt(tablaVideosCrearLista.getSelectedRow(),tablaVideosCrearLista.getSelectedColumn());
				appVideo.anadirVideoPlaylist(playlist,video);
				tm1.addRow(new LineaVideos(video));
				tm1.fireTableDataChanged();
				validate();	
			
		});
		
		//Botón para quitar video a la lista
		JButton quitarBoton = new JButton("Quitar");
		quitarBoton.setBounds(111, 546, 89, 23);
		add(quitarBoton);
		
		quitarBoton.addActionListener(ev->{
				Video video=(Video) tablaVideosVertical.getValueAt(tablaVideosVertical.getSelectedRow(),tablaVideosVertical.getSelectedColumn());
				playlist.removeVideo(video);
				tm1.removeRow(tablaVideosVertical.getSelectedRow());
				tm1.fireTableDataChanged();
				validate();					
					
		});
		
		//Botón para confirmar los cambios en la lista
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(63, 591, 89, 23);
		add(botonAceptar);
		
		botonAceptar.addActionListener(ev-> {		
				ListaVideos pl_antigua=appVideo.buscarPlaylist(lista.getText());
				if(pl_antigua==null) {
					appVideo.anadirPlaylist(playlist);
				}
				else {
					appVideo.actualizarPlaylist(pl_antigua,playlist);
				}
				int filas = tablaVideosVertical.getRowCount();
				for (int i = filas-1; i >= 0; i--)
					tm1.removeRow(i);
				tm1.fireTableDataChanged();
				repaint();
				revalidate();
				validate();
					
		});
		
		
		JLabel tituloVideo = new JLabel("Título Vídeo");
		tituloVideo.setForeground(Color.WHITE);
		tituloVideo.setBounds(476, 11, 120, 14);
		add(tituloVideo);
		
		nom_video = new JTextField();
		nom_video.setBounds(272, 36, 535, 20);
		add(nom_video);
		nom_video.setColumns(10);
		
		//Botón para buscar los videos para añadir a la lista
		JButton buscarVideo = new JButton("Buscar");
		buscarVideo.setBounds(817, 35, 89, 23);
		add(buscarVideo);
		buscarVideo.addActionListener(ev->{
				String texto = nom_video.getText();
					List<Video> videoBuscado;
					try {
						videoBuscado = appVideo.getVideos(texto);
						int filas = tablaVideosCrearLista.getRowCount();
						for (int i = filas-1; i >= 0; i--)
							tm2.removeRow(i);
						if (videoBuscado != null)
							tm2.rellenarTabla(videoBuscado, vWeb);
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
				tm2.fireTableDataChanged();
				validate();	
		});
		
		//Botón para resetear la busqueda
		JButton reset2 = new JButton("Reset");
		reset2.setBounds(916, 35, 89, 23);
		add(reset2);
		
		
		listaActual.setForeground(Color.WHITE);
		listaActual.setFont(new Font("Dialog", Font.BOLD, 14));
		listaActual.setBounds(541, 515, 292, 51);
		add(listaActual);
		
		reset2.addActionListener(ev -> {
			int filas =tablaVideosCrearLista.getRowCount();
			for (int i = filas-1; i >= 0; i--)
				tm2.removeRow(i);
			tm2.fireTableDataChanged();
			nom_video.setText("");
			validate();
		});
	}
}
