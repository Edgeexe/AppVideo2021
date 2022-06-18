package umu.tds.AppVideo.GUI;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

import dominio.Etiqueta;
import dominio.ListaVideos;
import dominio.Video;
import tds.video.VideoWeb;
import umu.tds.Controlador.Controlador;


public class PanelMisLIstas extends JPanel {
	
	private JTable tablaVideosVertical2;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JTextField etiquetaNueva;
	private VideoWeb vWeb=VentanaPrincipal.getVideoWeb();
	private Video videoActual;
	JLabel numVideos = new JLabel();
	
	public PanelMisLIstas() {
		setBackground(Color.BLACK);
		setBounds(0,0,1073,624);
		setLayout(null);
		
		//Panel para cambiar la lista de videos y el reproductor
		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(0, 0, 1073, 624);
		add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		
		//Panel donde se muestran la lista de los usuarios
		JPanel misListas=new JPanel();
		misListas.setBackground(Color.BLACK);
		misListas.setBounds(0, 0, 1073, 624);
		
		cardPanel.add(misListas,"misListas");
		
		//Tabla que muestra los videos de la lista seleccionada
		tablaVideosVertical2 = new JTable();
		tablaVideosVertical2.setBounds(1, 26, 450, 0);
		tablaVideosVertical2.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		TablaVideos tm3 = new TablaVideos(1);
		
		tablaVideosVertical2.setModel(tm3);
		tablaVideosVertical2.setRowHeight(175); 
		tablaVideosVertical2.getTableHeader().setUI(null);  
		TableColumnModel colModel=tablaVideosVertical2.getColumnModel();
		for(int i=0; i<1; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideosVertical2.setShowGrid(false);
		misListas.setLayout(null);
		
		//Etiqueta muestra el numero de videos de una lista
		numVideos.setHorizontalAlignment(SwingConstants.CENTER);
		numVideos.setBounds(450, 554, 220, 31);
		numVideos.setBackground(Color.WHITE);
		numVideos.setForeground(Color.WHITE);
		misListas.add(numVideos);
		
		//ScrolLPane con la tabala de los videos de la lista
		JScrollPane scrollPaneVert = new JScrollPane(tablaVideosVertical2);
		scrollPaneVert.setBounds(326, 38, 452, 505);
		misListas.add(scrollPaneVert);
		comboBox.setBounds(112, 38, 182, 22);
		misListas.add(comboBox);
		
		//Evento de la comboBox para actualizar las listas del usuario
		comboBox.addItemListener(ie-> {
				String nombre_playlist= (String) comboBox.getSelectedItem();
				ListaVideos lista=Controlador.getUnicaInstancia().getListaVideo(nombre_playlist);
				int filas=tablaVideosVertical2.getRowCount();
				int num;
				for(int i=0;i<filas;i++) {
					tm3.removeRow(0);
				}
				if(lista==null || lista.getListaVideos()==null) {
					num=0;
					return;
				}
				else num=lista.getListaVideos().size();
				numVideos.setText("Num de videos: "+num);
				for (Video video : lista.getListaVideos())tm3.addRow(new LineaVideos(video));
				tm3.fireTableDataChanged();
				validate();	
			
		});
		
		JLabel seleccionarLista = new JLabel("Selecciona la lista:");
		seleccionarLista.setBounds(10, 42, 92, 14);
		seleccionarLista.setBackground(Color.WHITE);
		seleccionarLista.setForeground(Color.WHITE);
		misListas.add(seleccionarLista);
		
		//Panel de reproduccion de un video
		JPanel repro=new JPanel();
		repro.setBounds(0, 0, 1073, 624);
		repro.setBackground(Color.BLACK);
		cardPanel.add(repro,"repro");
		repro.setLayout(null);
		
		//Etiqueta donde se muestra el nombre del video reproduciendose
		JLabel nombreVideo = new JLabel("");
		nombreVideo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nombreVideo.setHorizontalAlignment(SwingConstants.CENTER);
		nombreVideo.setForeground(Color.WHITE);
		nombreVideo.setBounds(288, 80, 360, 44);
		repro.add(nombreVideo);
		
		//Etiqueta donde se muestra el numero de reproduciones del video reproduciendose
		JLabel numRepro = new JLabel("");
		numRepro.setHorizontalAlignment(SwingConstants.CENTER);
		numRepro.setForeground(Color.WHITE);
		numRepro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numRepro.setBounds(288, 395, 360, 44);
		repro.add(numRepro);
		
		//Texto donde se pone el nombre de la nueva etiqueta a añadir
		etiquetaNueva = new JTextField();
		etiquetaNueva.setBounds(288, 471, 266, 20);
		repro.add(etiquetaNueva);
		etiquetaNueva.setColumns(10);
		
		//Boton con evento para crear la etiqueta 
		JButton nuevaEtiqueta = new JButton("Añadir Etiqueta");
		nuevaEtiqueta.setBounds(564, 470, 150, 23);
		repro.add(nuevaEtiqueta);
		
		JLabel etiquetasVideo = new JLabel("Etiquetas");
		etiquetasVideo.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetasVideo.setForeground(Color.WHITE);
		etiquetasVideo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetasVideo.setBounds(66, 80, 105, 14);
		repro.add(etiquetasVideo);
		
		//Lista con las etiquetas del video que se esta reproduciendo
		JList<String> listaEtiquetasVideo = new JList<String>();
		DefaultListModel<String> modelo3 =new DefaultListModel<String>();
		listaEtiquetasVideo.setModel(modelo3);
		listaEtiquetasVideo.setBounds(24, 105, 201, 412);
		repro.add(listaEtiquetasVideo);
		
		JButton salirRepro = new JButton("Salir Reproductor");
		salirRepro.setBounds(24, 552, 201, 23);
		repro.add(salirRepro);
		
		salirRepro.addActionListener(ev->{
			modelo3.removeAllElements();
			vWeb.cancel();
			repro.repaint();
			repro.revalidate();
			CardLayout cl=(CardLayout)(cardPanel.getLayout());
			cl.show(cardPanel,"misListas");
			validate();
		});
		
		
		
		JButton reproducir = new JButton("Reproducir");
		reproducir.setBounds(896, 235, 85, 23);
		misListas.add(reproducir);
		
		reproducir.addActionListener(ac->{
			
			Video video=(Video) tablaVideosVertical2.getValueAt(tablaVideosVertical2.getSelectedRow(),tablaVideosVertical2.getSelectedColumn());
			CardLayout cl=(CardLayout)(cardPanel.getLayout());
			nombreVideo.setText(Controlador.getUnicaInstancia().getNombreVideo(video));
			numRepro.setText("Num Reproducciones: "+Controlador.getUnicaInstancia().getNumRepro(video));
			HashSet<Etiqueta> etiquetas=Controlador.getUnicaInstancia().getEtiquetasVideo(video);
			for (Etiqueta etiqueta : etiquetas) {
				modelo3.addElement(etiqueta.getEtiqueta());
			}
			vWeb.setBounds(288, 135, 360, 220);
			repro.add(vWeb);
			repro.repaint();
			repro.revalidate();
			cl.show(cardPanel, "repro");
			vWeb.playVideo(Controlador.getUnicaInstancia().getUrlVideo(video));
			videoActual=video;
			Controlador.getUnicaInstancia().sumarReproduccion(video);
			validate();
		});
		
		//Evento para añadir la nueva etiqueta a un video que se esta reproduciendo
		nuevaEtiqueta.addActionListener(ev->{
			if(!etiquetaNueva.getText().equals("")) {
				modelo3.addElement(etiquetaNueva.getText());
				Controlador.getUnicaInstancia().nuevaEtiqueta(videoActual,etiquetaNueva.getText());
				etiquetaNueva.setText("");
			}
				
			});
		
		ActualizarListas();
	}
	
		//Funcion para actualizar el comboBox de las listas de un usuario
		void ActualizarListas() {
		this.comboBox.removeAllItems();
		LinkedList<ListaVideos> listas =(LinkedList<ListaVideos>) Controlador.getUnicaInstancia().getListasVideosUsuario();
		numVideos.setText("");
		for (ListaVideos lista:listas) {
			comboBox.addItem(lista.getNombre());
		}
	}
}
