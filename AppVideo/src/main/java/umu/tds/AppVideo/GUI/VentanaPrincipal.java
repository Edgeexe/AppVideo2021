package umu.tds.AppVideo.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dominio.CatalogoVideos;
import dominio.ListaVideos;
import dominio.Video;

import javax.swing.JTextPane;

import javax.swing.BoxLayout;
import java.awt.*;
import javax.swing.JToggleButton;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import tds.video.VideoWeb;
import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.Controlador.Controlador;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private int posX=0;
	private int	posY=0;
	private JTextField escribir_busqueda;
	private JTable tablaVideos_explorar;
	private JTable tablaVideos_crear_lista;
	private JTable tablaVideos_vertical1;
	private JTable tablaVideos_vertical2;
	private static VideoWeb vWeb = new VideoWeb();
	private static Controlador appVideo = Controlador.getUnicaInstancia();
	private JTextField lista;
	private JTextField nom_video;
	private ListaVideos playlist;
	

	public VentanaPrincipal() throws DAOException {
		
		setBounds(new Rectangle(100, 100, 1280, 720));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel_layout = new JPanel();
		panel_layout.setBounds(207, 96, 1073, 624);
		getContentPane().add(panel_layout);
		panel_layout.setLayout(new CardLayout(0, 0));
		
		

		JPanel panel_Explorar = new JPanel();
		panel_Explorar.setBounds(207, 96, 1073, 624);
		panel_Explorar.setLayout(new BorderLayout(0, 0));
		panel_layout.add(panel_Explorar,"explorar");
		
		
		JPanel panel_resultado = new JPanel();
		panel_resultado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_resultado.setBackground(Color.LIGHT_GRAY);
		panel_Explorar.add(panel_resultado, BorderLayout.CENTER);
		

		tablaVideos_explorar = new JTable();
		tablaVideos_explorar.setBounds(1, 26, 450, 0);
		tablaVideos_explorar.setDefaultRenderer(Object.class, new VideoLabelTabla());
			
		LineaVideos gVideos = new LineaVideos();
		LinkedList<LineaVideos> listaCVideos = new LinkedList<LineaVideos>();
		ArrayList<Video> videosAux  = (ArrayList<Video>) appVideo.getVideos();	
		listaCVideos.add(gVideos);
		TablaVideos tm = new TablaVideos(6);
		
		//tm.rellenarTabla(videosAux, vWeb);
		
		tablaVideos_explorar.setModel(tm);
		tablaVideos_explorar.setRowHeight(175); 
		tablaVideos_explorar.getTableHeader().setUI(null);  
		TableColumnModel colModel=tablaVideos_explorar.getColumnModel();
		for(int i=0; i<6; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		panel_resultado.setLayout(null);
		
		tablaVideos_explorar.setShowGrid(false);
		JScrollPane js=new JScrollPane(tablaVideos_explorar);
		js.setBounds(0, 0, 979, 590);
		js.setBackground(Color.GRAY);
		panel_resultado.add(js);
		
		JPanel panel_este = new JPanel();
		panel_este.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_este.setBackground(Color.LIGHT_GRAY);
		panel_Explorar.add(panel_este, BorderLayout.EAST);
		panel_este.setLayout(new BoxLayout(panel_este, BoxLayout.Y_AXIS));
		
		JList<String> list = new JList<String>();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = -7605725966827348811L;
			String[] values = new String[] {"Agfsgsbs", "Bsfsaavv", "vzvzbbsC", "Dcdssca", "EcsCCvf", "cscscjxF", "aXXACdfG"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		panel_este.add(list);
		panel_este.add(Box.createRigidArea(new Dimension(90,90)));
		
		JTextPane textPane = new JTextPane();
		panel_este.add(textPane);
		
		JPanel panel_busqueda = new JPanel();
		panel_busqueda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_busqueda.setBackground(Color.LIGHT_GRAY);
		panel_Explorar.add(panel_busqueda, BorderLayout.NORTH);
		panel_busqueda.setLayout(new BoxLayout(panel_busqueda, BoxLayout.X_AXIS));
		
		JLabel lbl_busqueda = new JLabel("Búsqueda: ");
		panel_busqueda.add(lbl_busqueda);
		panel_busqueda.add(Box.createRigidArea(new Dimension(20,30)));
		
		escribir_busqueda = new JTextField();
		panel_busqueda.add(escribir_busqueda);
		escribir_busqueda.setColumns(10);
		
		JButton boton_buscar = new JButton("Buscar");
		panel_busqueda.add(boton_buscar);
		boton_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = escribir_busqueda.getText();
				if (!texto.equals("")) {
					List<Video> videoBuscado;
					try {
						videoBuscado = appVideo.getVideos(texto);
						int filas = tablaVideos_explorar.getRowCount();
						for (int i = filas-1; i >= 0; i--)
							tm.removeRow(i);
						if (videoBuscado != null)
							tm.rellenarTabla(videoBuscado, vWeb);
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
					
				}
				else {
					int filas = tablaVideos_explorar.getRowCount();
					for (int i = filas-1; i >= 0; i--)
						tm.removeRow(i);
					List<Video> todosVideos;
					try {
						todosVideos = appVideo.getVideos();
						tm.rellenarTabla(todosVideos, vWeb);
					} catch (DAOException e1) {
						e1.printStackTrace();
					}		
				}
				tm.fireTableDataChanged();
				validate();	
			}
		});
		
		panel_busqueda.add(Box.createRigidArea(new Dimension(30,30)));
		
		JButton btn_Reset = new JButton("Reset");
		panel_busqueda.add(btn_Reset);
		
		btn_Reset.addActionListener(ev -> {
			int filas = tablaVideos_explorar.getRowCount();
			for (int i = filas-1; i >= 0; i--)
				tm.removeRow(i);
			tm.fireTableDataChanged();
			panel_busqueda.repaint();
			panel_busqueda.revalidate();
			validate();
		});
		
		JPanel panel_nuevaLista = new JPanel();
		panel_layout.add(panel_nuevaLista, "crear_listas");
		panel_nuevaLista.setLayout(null);
		
		JLabel nombre_lista = new JLabel("Nombre Lista");
		nombre_lista.setBounds(66, 11, 86, 14);
		panel_nuevaLista.add(nombre_lista);
		
		lista = new JTextField();
		lista.setBounds(10, 36, 179, 20);
		panel_nuevaLista.add(lista);
		lista.setColumns(10);
		
		tablaVideos_crear_lista = new JTable();
		tablaVideos_crear_lista.setBounds(1, 26, 450, 0);
		tablaVideos_crear_lista.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		TablaVideos tm2 = new TablaVideos(6);
				
		tablaVideos_crear_lista.setModel(tm2);
		tablaVideos_crear_lista.setRowHeight(175); 
		tablaVideos_crear_lista.getTableHeader().setUI(null);  
		colModel=tablaVideos_crear_lista.getColumnModel();
		for(int i=0; i<6; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideos_crear_lista.setShowGrid(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(tablaVideos_crear_lista);
		scrollPane_1.setBounds(272, 67, 770, 437);
		panel_nuevaLista.add(scrollPane_1);
		
		tablaVideos_vertical1 = new JTable();
		tablaVideos_vertical1.setBounds(1, 26, 450, 0);
		tablaVideos_vertical1.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		TablaVideos tm1 = new TablaVideos(1);
		
		//tm1.rellenarTabla(videosAux, vWeb);
		
		tablaVideos_vertical1.setModel(tm1);
		tablaVideos_vertical1.setRowHeight(175); 
		tablaVideos_vertical1.getTableHeader().setUI(null);  
		TableColumnModel colModel2=tablaVideos_vertical1.getColumnModel();
		for(int i=0; i<1; i++)
		{
			TableColumn col=colModel2.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideos_vertical1.setShowGrid(false);
		
		JScrollPane scrollPane = new JScrollPane(tablaVideos_vertical1);
		scrollPane.setBounds(21, 100, 179, 426);
		panel_nuevaLista.add(scrollPane);
		
		JButton buscar_lista = new JButton("Buscar");
		buscar_lista.setBounds(10, 67, 89, 23);
		panel_nuevaLista.add(buscar_lista);
		buscar_lista.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ListaVideos pl=appVideo.buscarPlaylist(lista.getText());
				if(pl!=null) {
					int decision=JOptionPane.showConfirmDialog(tablaVideos_crear_lista,"¿Le gustría editar la playlist existente?","La Playlist existe",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(decision == JOptionPane.YES_OPTION) {
						playlist=pl;
						for(int i=0;i<tablaVideos_vertical1.getRowCount();i++) tm1.removeRow(i);
						for (Video video : playlist.getListaVideos())tm1.addRow(new LineaVideos(video));
						tm1.fireTableDataChanged();
						validate();	
					}
				}
				else {
					int decision=JOptionPane.showConfirmDialog(tablaVideos_crear_lista,"¿Le gustría crear la playlist?","La Playlist no existe",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(decision == JOptionPane.YES_OPTION)
						playlist=new ListaVideos(lista.getText());
				}
			}
			
		});
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int output = JOptionPane.showConfirmDialog(tablaVideos_vertical1, "Borrar Playlist","¿Quieres borrar la playlist?", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if (output == JOptionPane.YES_OPTION) {
					appVideo.eliminarPlaylist(playlist);
				}
			}
		});
		eliminar.setBounds(100, 67, 89, 23);
		panel_nuevaLista.add(eliminar);
				
		JButton anadir_boton = new JButton("Añadir");
		anadir_boton.setBounds(10, 546, 89, 23);
		panel_nuevaLista.add(anadir_boton);
		
		anadir_boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Video video=(Video) tablaVideos_crear_lista.getValueAt(tablaVideos_crear_lista.getSelectedRow(),tablaVideos_crear_lista.getSelectedColumn());
				playlist.addVideo(CatalogoVideos.getUnicaInstancia().getVideo(video.getUrl()));
				for(int i=0;i<tablaVideos_vertical1.getRowCount();i++) tm1.removeRow(i);
				tm1.rellenarTabla(playlist.getListaVideos(), vWeb);
				tm1.fireTableDataChanged();
				validate();	
			}
		});
		
		JButton quitar_boton = new JButton("Quitar");
		quitar_boton.setBounds(111, 546, 89, 23);
		panel_nuevaLista.add(quitar_boton);
		
		quitar_boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Video video=(Video) tablaVideos_vertical1.getValueAt(tablaVideos_vertical1.getSelectedRow(),tablaVideos_vertical1.getSelectedColumn());
				playlist.removeVideo(video);
				for(int i=0;i<tablaVideos_vertical1.getRowCount();i++) tm1.removeRow(i);
				for (Video vi : playlist.getListaVideos())tm1.addRow(new LineaVideos(vi));
				tm1.fireTableDataChanged();
				validate();					
			}		
		});
		
		JButton boton_aceptar = new JButton("Aceptar");
		boton_aceptar.setBounds(63, 591, 89, 23);
		panel_nuevaLista.add(boton_aceptar);
		
		boton_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				ListaVideos pl_antigua=appVideo.buscarPlaylist(lista.getText());
				if(pl_antigua==null) {
					appVideo.anadirPlaylist(playlist);
				}
				else {
					appVideo.actualizarPlaylist(pl_antigua,playlist);
				}
				int filas = tablaVideos_vertical1.getRowCount();
				for (int i = filas-1; i >= 0; i--)
					tm1.removeRow(i);
				tm1.fireTableDataChanged();
				panel_nuevaLista.repaint();
				panel_nuevaLista.revalidate();
				validate();
			}		
		});
		
		
		
		
		JLabel lblNewLabel = new JLabel("Título Vídeo");
		lblNewLabel.setBounds(476, 11, 120, 14);
		panel_nuevaLista.add(lblNewLabel);
		
		nom_video = new JTextField();
		nom_video.setBounds(272, 36, 535, 20);
		panel_nuevaLista.add(nom_video);
		nom_video.setColumns(10);
		
		JButton buscar_2 = new JButton("Buscar");
		buscar_2.setBounds(817, 35, 89, 23);
		panel_nuevaLista.add(buscar_2);
		buscar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = nom_video.getText();
				if (!texto.equals("")) {
					List<Video> videoBuscado;
					try {
						videoBuscado = appVideo.getVideos(texto);
						int filas = tablaVideos_crear_lista.getRowCount();
						for (int i = filas-1; i >= 0; i--)
							tm2.removeRow(i);
						if (videoBuscado != null)
							tm2.rellenarTabla(videoBuscado, vWeb);
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
					
				}
				else {
					int filas = tablaVideos_crear_lista.getRowCount();
					for (int i = filas-1; i >= 0; i--)
						tm2.removeRow(i);
					List<Video> todosVideos;
					try {
						todosVideos = appVideo.getVideos();
						tm2.rellenarTabla(todosVideos, vWeb);
					} catch (DAOException e1) {
						e1.printStackTrace();
					}		
				}
				tm2.fireTableDataChanged();
				validate();	
			}
		});
		
		JButton reset2 = new JButton("Reset");
		reset2.setBounds(916, 35, 89, 23);
		panel_nuevaLista.add(reset2);
		
		JPanel panel_mis_listas = new JPanel();
		panel_layout.add(panel_mis_listas, "mis_listas");
		panel_mis_listas.setLayout(null);
		
		JLabel titulo_cancion = new JLabel("");
		titulo_cancion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		titulo_cancion.setBounds(349, 147, 360, 14);
		panel_mis_listas.add(titulo_cancion);
		vWeb.setBounds(349, 172, 360, 220);
		panel_mis_listas.add(vWeb);
		
		tablaVideos_vertical2 = new JTable();
		tablaVideos_vertical2.setBounds(1, 26, 450, 0);
		tablaVideos_vertical2.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		TablaVideos tm3 = new TablaVideos(1);
		
		//tm1.rellenarTabla(videosAux, vWeb);
		
		tablaVideos_vertical2.setModel(tm3);
		tablaVideos_vertical2.setRowHeight(175); 
		tablaVideos_vertical2.getTableHeader().setUI(null);  
		colModel2=tablaVideos_vertical2.getColumnModel();
		for(int i=0; i<1; i++)
		{
			TableColumn col=colModel2.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideos_vertical2.setShowGrid(false);
		
		JLabel num_videos = new JLabel();
		num_videos.setBounds(20, 460, 157, 14);
		panel_mis_listas.add(num_videos);
		
		JScrollPane scrollPane_vert = new JScrollPane(tablaVideos_vertical2);
		scrollPane_vert.setBounds(10, 120, 167, 329);
		panel_mis_listas.add(scrollPane_vert);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 32, 167, 22);
		panel_mis_listas.add(comboBox);
		comboBox.addItemListener((ItemListener) new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String nombre_playlist= (String) comboBox.getSelectedItem();
				ListaVideos lista=Controlador.getUnicaInstancia().getListaVideo(nombre_playlist);
				int num;
				if(lista==null || lista.getListaVideos()==null) {
					num=0;
					return;
				}
				else num=lista.getListaVideos().size();
				num_videos.setText("Num de videos: "+num);
				for(int i=0;i<tablaVideos_vertical2.getRowCount();i++) tm3.removeRow(i);
				for (Video video : lista.getListaVideos())tm3.addRow(new LineaVideos(video));
				tm3.fireTableDataChanged();
				validate();	
			}
			
		});
		
		JLabel seleccionarLista = new JLabel("Selecciona la lista:");
		seleccionarLista.setBounds(10, 11, 107, 14);
		panel_mis_listas.add(seleccionarLista);
		
		
		JButton reproducir = new JButton("Reproducir");
		reproducir.setBounds(30, 65, 129, 23);
		panel_mis_listas.add(reproducir);
		
		reproducir.addActionListener(ac->{
			Video video=(Video) tablaVideos_vertical2.getValueAt(tablaVideos_vertical2.getSelectedRow(),tablaVideos_vertical2.getSelectedColumn());
			titulo_cancion.setText(video.getTitulo());
			vWeb.playVideo(video.getUrl());
			validate();
		});
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setBounds(43, 494, 89, 23);
		panel_mis_listas.add(cancelar);
		cancelar.addActionListener(ev -> {
			int filas = tablaVideos_vertical2.getRowCount();
			for (int i = filas-1; i >= 0; i--) tm3.removeRow(i);
			titulo_cancion.setText("");
			vWeb.cancel();
			tm3.fireTableDataChanged();
			panel_mis_listas.repaint();
			panel_mis_listas.revalidate();
			validate();
		});
			
		JPanel panel_cabecera = new JPanel();
		panel_cabecera.setOpaque(false);
		panel_cabecera.setBorder(null);
		panel_cabecera.setBounds(0, 0, 1280, 95);
		getContentPane().add(panel_cabecera);
		panel_cabecera.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/LogoNoText.png")));
		logo.setBounds(45, 5, 100, 95);
		panel_cabecera.add(logo);
		
		JLabel textoUsuario = new JLabel(Controlador.getUnicaInstancia().getUsuarioActual().getUsuario());
		textoUsuario.setFont(new Font("Lato Black", Font.BOLD, 15));
		textoUsuario.setForeground(Color.WHITE);
		textoUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/UserIcon.png")));
		int width = textoUsuario.getText().length()*15;
		textoUsuario.setBounds(277, 33, 241, 29);
		panel_cabecera.add(textoUsuario);
		
		RoundedPanel panel = new RoundedPanel(45, new Color(41,41,41));
		panel.setBounds(275, 28, 253, 39);
		panel.setOpaque(false);
		panel_cabecera.add(panel);
		
		JToggleButton boton_Premium = new JToggleButton("");
		boton_Premium.setSelectedIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumStatus.png")));
		boton_Premium.setContentAreaFilled(false);
		boton_Premium.setBorderPainted(false);
		boton_Premium.setRolloverSelectedIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumStatus.png")));
		boton_Premium.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumButton.png")));
		boton_Premium.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumButton.png")));
		boton_Premium.setBorder(null);
		boton_Premium.setBounds(226, 28, 39, 39);
		panel_cabecera.add(boton_Premium);
		
		boton_Premium.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				//TODO: Hacer el boton no clickeable cuando el usuario ya sea premium
			}
		});
		
		JPanel panel_botones = new JPanel();
		panel_botones.setOpaque(false);
		panel_botones.setBorder(null);
		panel_botones.setBounds(0, 106, 206, 614);
		getContentPane().add(panel_botones);
		panel_botones.setLayout(null);
		
		JButton boton_Explorar = new JButton("        Explorar");
		boton_Explorar.setMargin(new Insets(2, 25, 2, 14));
		boton_Explorar.setRequestFocusEnabled(false);
		boton_Explorar.setHorizontalAlignment(SwingConstants.LEFT);
		boton_Explorar.setContentAreaFilled(false);
		boton_Explorar.setBorderPainted(false);
		boton_Explorar.setOpaque(false);
		boton_Explorar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/HomeIcon.png")));
		boton_Explorar.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_Explorar.setForeground(Color.WHITE);
		boton_Explorar.setBounds(0, 139, 206, 46);
		panel_botones.add(boton_Explorar);
		
		boton_Explorar.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_Explorar.setContentAreaFilled(true);
		    	boton_Explorar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_Explorar.setContentAreaFilled(false);		    
		    	}
		    
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panel_layout.getLayout());
					cl.show(panel_layout, "explorar");
				}
			}
		});
		
		JButton boton_mis_listas = new JButton("        Mis Listas");
		boton_mis_listas.setMargin(new Insets(2, 25, 2, 14));
		boton_mis_listas.setOpaque(false);
		boton_mis_listas.setRequestFocusEnabled(false);
		boton_mis_listas.setHorizontalAlignment(SwingConstants.LEFT);
		boton_mis_listas.setContentAreaFilled(false);
		boton_mis_listas.setBorderPainted(false);
		boton_mis_listas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/LibraryIcon.png")));
		boton_mis_listas.setForeground(Color.WHITE);
		boton_mis_listas.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_mis_listas.setBounds(0, 185, 206, 46);
		panel_botones.add(boton_mis_listas);
		
		boton_mis_listas.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_mis_listas.setContentAreaFilled(true);
		    	boton_mis_listas.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_mis_listas.setContentAreaFilled(false);		    
		    }
		    
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panel_layout.getLayout());
					comboBox.removeAllItems();
					for (ListaVideos lista : Controlador.getUnicaInstancia().getListasVideosUsuario()) {
						comboBox.addItem(lista.getNombre());
					}
					cl.show(panel_layout, "mis_listas");
				}
			}
		});
		
		JButton boton_Recientes = new JButton("        Recientes");
		boton_Recientes.setMargin(new Insets(2, 25, 2, 14));
		boton_Recientes.setOpaque(false);
		boton_Recientes.setRequestFocusEnabled(false);
		boton_Recientes.setHorizontalAlignment(SwingConstants.LEFT);
		boton_Recientes.setContentAreaFilled(false);
		boton_Recientes.setBorderPainted(false);
		boton_Recientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/RecentsIcon.png")));
		boton_Recientes.setForeground(Color.WHITE);
		boton_Recientes.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_Recientes.setBounds(0, 230, 206, 46);
		panel_botones.add(boton_Recientes);
		
		boton_Recientes.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_Recientes.setContentAreaFilled(true);
		    	boton_Recientes.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_Recientes.setContentAreaFilled(false);		    
		    }
		    
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panel_layout.getLayout());
					cl.show(panel_layout, "recientes");
				}
			}
		});
		
		JButton boton_Nueva_Lista = new JButton("        Nueva Lista");
		boton_Nueva_Lista.setMargin(new Insets(2, 25, 2, 14));
		boton_Nueva_Lista.setOpaque(false);
		boton_Nueva_Lista.setRequestFocusEnabled(false);
		boton_Nueva_Lista.setHorizontalAlignment(SwingConstants.LEFT);
		boton_Nueva_Lista.setContentAreaFilled(false);
		boton_Nueva_Lista.setBorderPainted(false);
		boton_Nueva_Lista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/NewListIcon.png")));
		boton_Nueva_Lista.setForeground(Color.WHITE);
		boton_Nueva_Lista.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_Nueva_Lista.setBounds(0, 275, 206, 46);
		panel_botones.add(boton_Nueva_Lista);
		
		boton_Nueva_Lista.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_Nueva_Lista.setContentAreaFilled(true);
		    	boton_Nueva_Lista.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_Nueva_Lista.setContentAreaFilled(false);		    
		    }
		    
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panel_layout.getLayout());
					cl.show(panel_layout, "crear_listas");
				}
			}
		});
		
		JButton boton_Logout = new JButton("CERRAR SESION");
		boton_Logout.setBorder(null);
		boton_Logout.setBorderPainted(false);
		boton_Logout.setHorizontalTextPosition(SwingConstants.CENTER);
		boton_Logout.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/Logout.png")));
		boton_Logout.setRolloverEnabled(true);
		boton_Logout.setRolloverIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/LogoutHover.png")));
		boton_Logout.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_Logout.setForeground(Color.BLACK);
		boton_Logout.setContentAreaFilled(false);
		boton_Logout.setFocusPainted(false);
		boton_Logout.setBounds(19, 548, 169, 58);
		panel_botones.add(boton_Logout);
		boton_Logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.mostarVentana();
				dispose();
			}
		});
			
		
		Luz luz = new Luz();
		luz.color = Color.YELLOW;
		luz.setBounds(53, 385, 99, 102);
		panel_botones.add(luz);
		
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject evt) {
				if(luz.isEncendido()) {
					JFileChooser selectorArchivos = new JFileChooser();
					selectorArchivos.showOpenDialog(selectorArchivos);
					File archivo_xml = selectorArchivos.getSelectedFile(); // obtiene el archivo seleccionado
					Controlador.getUnicaInstancia().cargarVideos(archivo_xml);
				}
					
			}
		});
				
		JButton boton_Cerrar = new JButton("");
		boton_Cerrar.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/AppVideo/Resources/Close button.png")));
		boton_Cerrar.setBackground(Color.BLACK);
		boton_Cerrar.setForeground(Color.WHITE);
		boton_Cerrar.setFont(new Font("Dialog", Font.BOLD, 30));
		boton_Cerrar.setBorder(null);
		boton_Cerrar.setBounds(1240, 0, 40, 26);
		boton_Cerrar.setFocusable(false);
		getContentPane().add(boton_Cerrar);
		
		boton_Cerrar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evento) {
				if(evento.getButton()==MouseEvent.BUTTON1)
					System.exit(0);
			}
			public void mouseEntered(MouseEvent evt) {
				boton_Cerrar.setContentAreaFilled(true);
				boton_Cerrar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
				boton_Cerrar.setContentAreaFilled(false);		    
		    }
		});
		
		
		
		JButton boton_Minimizar = new JButton("");
		boton_Minimizar.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/AppVideo/Resources/Minimize.png")));
		boton_Minimizar.setForeground(Color.WHITE);
		boton_Minimizar.setFont(new Font("Dialog", Font.BOLD, 30));
		boton_Minimizar.setFocusable(false);
		boton_Minimizar.setBorder(null);
		boton_Minimizar.setBackground(Color.BLACK);
		boton_Minimizar.setBounds(1200, 0, 40, 26);
		getContentPane().add(boton_Minimizar);
		
		boton_Minimizar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evento) {
				if(evento.getButton()==MouseEvent.BUTTON1)
					setState(JFrame.ICONIFIED);
			}
			
			public void mouseEntered(MouseEvent evt) {
				boton_Minimizar.setContentAreaFilled(true);
				boton_Minimizar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
				boton_Minimizar.setContentAreaFilled(false);		    
		    }
		});
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/AppVideo/Resources/AppBG.png")));
		fondo.setBounds(0, 0, 1280, 720);
		getContentPane().add(fondo);
		

		
		//
		//Para arrastrar la ventana
		//
		
		this.addMouseListener(new MouseAdapter()			
				{
				   public void mousePressed(MouseEvent e)
				   {
				      posX = e.getX();
				      posY = e.getY();
				   }
				});
				
				this.addMouseMotionListener(new MouseAdapter()
				{
				     public void mouseDragged(MouseEvent evt)
				     {	
						setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);				
				     }
				});

	}
	//TODO: Metodo de reproduccion de videos
	
	public static VideoWeb getVideoWeb() {
		return vWeb;
	}
	
	public void mostarVentana() {
		this.setVisible(true);
	}
}


class LineaVideos {
	
	private Video v1, v2, v3, v4, v5, v6;
	private LinkedList<Video> videos = new LinkedList<>();
	
	public LineaVideos() {
		Video v1 = null, v2 = null, v3 = null, v4 = null, v5 = null, v6 = null;
		videos.add(v1);
		videos.add(v2);
		videos.add(v3);
		videos.add(v4);
		videos.add(v5);
		videos.add(v6);
	}
	
	public LineaVideos(Video...videos ) {
		this();
		if (videos.length <= 6) {
			for (int i = 0; i < videos.length; i++) {
				
				switch (i) {
				case 0:
					this.v1 = videos[0];
					break;
				case 1:
					this.v2 = videos[1];
					break;
				case 2:
					this.v3 = videos[2];
					break;
				case 3:
					this.v4 = videos[3];
					break;
				case 4:
					this.v5 = videos[4];
					break;
				case 5:
					this.v6 = videos[5];
					break;
				default:
					break;
				}
			}
		}
	}
	
	public Video getVideo1() {
		return v1;
	}

	public Video getVideo2() {
		return v2;
	}

	public Video getVideo3() {
		return v3;
	}
	
	public Video getVideo4() {
		return v4;
	}
	
	public Video getVideo5() {
		return v5;
	}
	
	public Video getVideo6() {
		return v6;
	}
}


class RoundedPanel extends JPanel

{

	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedPanel(LayoutManager layout, int radius) {
        super(layout);
        cornerRadius = radius;
    }

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    public RoundedPanel(int radius) {
        super();
        cornerRadius = radius;
    }

    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());

        }
        
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border

    }
  }




