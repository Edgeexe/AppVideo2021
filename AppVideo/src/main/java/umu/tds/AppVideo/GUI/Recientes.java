package umu.tds.AppVideo.GUI;

import java.awt.Color;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dominio.Video;
import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.Controlador.Controlador;

import java.awt.Font;
import java.util.List;

public class Recientes extends JPanel {
	
	private JTable tablaVideosVertical2;
	private JTable tablaVideosVertical;
	private TablaVideos tm = new TablaVideos(1);
	private TablaVideos tm2 = new TablaVideos(1);
	
	public Recientes() throws DAOException{
		setBackground(Color.BLACK);
		setBounds(0, 0, 1073, 624);
		setLayout(null);
		
		//Tabla de los videos mas reproducidos
		tablaVideosVertical = new JTable();
		tablaVideosVertical.setBounds(1, 26, 450, 0);
		tablaVideosVertical.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		tablaVideosVertical.setModel(tm2);
		tablaVideosVertical.setRowHeight(175); 
		tablaVideosVertical.getTableHeader().setUI(null);  
		TableColumnModel colModel=tablaVideosVertical.getColumnModel();
		for(int i=0; i<1; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		//scrollPane con la tabal de los videos premium
		JScrollPane scrollPane_premium = new JScrollPane(tablaVideosVertical);
		scrollPane_premium.setBounds(611, 71, 452, 542);
		add(scrollPane_premium);
		
		//Tabla con los videos reproducidos mas recientemente por los usuarios
		tablaVideosVertical2 = new JTable();
		tablaVideosVertical2.setBounds(1, 26, 450, 0);
		tablaVideosVertical2.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		tablaVideosVertical2.setModel(tm);
		tablaVideosVertical2.setRowHeight(175); 
		tablaVideosVertical2.getTableHeader().setUI(null);  
		TableColumnModel colModel2=tablaVideosVertical2.getColumnModel();
		for(int i=0; i<1; i++)
		{
			TableColumn col=colModel2.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		//scrollpane con la tabla de los videos recientes
		JScrollPane scrollPane_recientes = new JScrollPane(tablaVideosVertical2);
		scrollPane_recientes.setBounds(10, 71, 452, 542);
		add(scrollPane_recientes);
		
		JLabel recientes = new JLabel("Videos Recientes");
		recientes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		recientes.setForeground(Color.WHITE);
		recientes.setBackground(Color.WHITE);
		recientes.setBounds(149, 36, 152, 14);
		add(recientes);
		
		JLabel top = new JLabel("Videos Top");
		top.setForeground(Color.WHITE);
		top.setFont(new Font("Tahoma", Font.PLAIN, 17));
		top.setBackground(Color.WHITE);
		top.setBounds(789, 36, 152, 24);
		add(top);
		
		mostrarListas();
				
	}
	
	//Funcion para mostrar los videos mas reproducidos y los videos recientes del usuario
	public void mostrarListas() throws DAOException {
		List<Video> lista=Controlador.getUnicaInstancia().getRecientesUsuarioActual();
		int filasRecientes=tm.getRowCount();
		int filasPremium=tm2.getRowCount();
		for (int i = filasRecientes-1; i >= 0; i--)
			tm.removeRow(i);
		for (int i = filasPremium-1; i >= 0; i--)
			tm2.removeRow(i);
		for (Video video : lista)tm.addRow(new LineaVideos(video));
		tm.fireTableDataChanged();
		tm2.fireTableDataChanged();
		//Mostrar y buscar los videos mas reproducidos si el usuario es premium
		if(Controlador.getUnicaInstancia().usuarioActualEsPremium()) {
			lista=Controlador.getUnicaInstancia().recogerVideosMasVistos();
			for (Video video : lista)tm2.addRow(new LineaVideos(video));
			tm2.fireTableDataChanged();
		}
		validate();	
	}
}
