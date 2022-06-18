package umu.tds.AppVideo.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dominio.Etiqueta;
import dominio.Video;
import tds.video.VideoWeb;
import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.Controlador.Controlador;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class PanelExplorar extends JPanel {
	
	private JTextField escribirBusqueda;
	private JTable tablaVideosExplorar;
	private static Controlador appVideo = Controlador.getUnicaInstancia();
	private VideoWeb vWeb=VentanaPrincipal.getVideoWeb();
	private JTextField etiquetaNueva;
	private Video videoActual;
	
	//Modelos para los JLists de las etiquetas
	private DefaultListModel<String> modelo1 =new DefaultListModel<String>();
	private DefaultListModel<String> modelo2 =new DefaultListModel<String>();
	
	public PanelExplorar(){
		setBounds(0, 0, 1073, 624);
		setLayout(null);
		
		//Panel donde se cambia la tabal de videos y el panel reproductor
		JPanel panelCentral = new JPanel();
		panelCentral.setBounds(0, 34, 811, 590);
		panelCentral.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCentral.setBackground(Color.BLACK);
		add(panelCentral);
		
		//Creacion de la tabla de explorar
		tablaVideosExplorar = new JTable();
		tablaVideosExplorar.setBounds(1, 26, 770, 437);
		tablaVideosExplorar.setDefaultRenderer(Object.class, new VideoLabelTabla());
		
		TablaVideos tm = new TablaVideos(6);
				
		tablaVideosExplorar.setModel(tm);
		tablaVideosExplorar.setRowHeight(175); 
		tablaVideosExplorar.getTableHeader().setUI(null);  
		TableColumnModel colModel=tablaVideosExplorar.getColumnModel();
		for(int i=0; i<6; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		panelCentral.setLayout(new CardLayout(0, 0));
		

		//Panel donde se muestra el resultado
		JPanel panel_resultado = new JPanel();
		panel_resultado.setBackground(Color.BLACK);
		panelCentral.add(panel_resultado, "result");
		panel_resultado.setLayout(new BorderLayout(0, 0));
		
		//Panel donde se reproducen los videos
		JPanel repro=new JPanel();
		repro.setBackground(Color.BLACK);
		panelCentral.add(repro,"repro");
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
		nuevaEtiqueta.setBounds(564, 470, 155, 23);
		repro.add(nuevaEtiqueta);
		
		JLabel etiquetasVideo = new JLabel("Etiquetas");
		etiquetasVideo.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetasVideo.setForeground(Color.WHITE);
		etiquetasVideo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetasVideo.setBounds(61, 80, 105, 14);
		repro.add(etiquetasVideo);
		
		//Lista con las etiquetas del video que se esta reproduciendo
		JList<String> listaEtiquetasVideo = new JList<String>();
		DefaultListModel<String> modelo3 =new DefaultListModel<String>();
		listaEtiquetasVideo.setModel(modelo3);
		listaEtiquetasVideo.setBounds(24, 105, 201, 412);
		repro.add(listaEtiquetasVideo);
		
		//Boton para salir del reproductor y volver al buscador de videos
		JButton salirRepro = new JButton("Salir Reproductor");
		salirRepro.setBounds(24, 552, 201, 23);
		repro.add(salirRepro);
		
		salirRepro.addActionListener(ev->{
			modelo3.removeAllElements();
			vWeb.cancel();
			repro.repaint();
			repro.revalidate();
			CardLayout cl=(CardLayout)(panelCentral.getLayout());
			cl.show(panelCentral,"result");
			validate();
		});
		
		
		tablaVideosExplorar.setShowGrid(false);
		JScrollPane js=new JScrollPane(tablaVideosExplorar);
		js.setBackground(Color.GRAY);
		panel_resultado.add(js);
		
		vWeb.setBounds(400, 254, 360, 220);
		
		//Panel superior donde se encuentran el texto donde se introduce la subcadena del nombre del video y los botones para buscar o resetar la busqueda
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBounds(0, 0, 1073, 34);
		panelBusqueda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelBusqueda.setBackground(Color.BLACK);
		add(panelBusqueda);
		panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.X_AXIS));
		
		JLabel lbl_busqueda = new JLabel("Búsqueda: ");
		lbl_busqueda.setForeground(Color.WHITE);
		lbl_busqueda.setBackground(Color.WHITE);
		panelBusqueda.add(lbl_busqueda);
		panelBusqueda.add(Box.createRigidArea(new Dimension(20,30)));
		
		//Campo de texto para introducir la subcaden del titulo del video
		escribirBusqueda = new JTextField();
		panelBusqueda.add(escribirBusqueda);
		escribirBusqueda.setColumns(10);
		
		//Boton para buscar los videos 
		JButton boton_buscar = new JButton("Buscar");
		panelBusqueda.add(boton_buscar);
		boton_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = escribirBusqueda.getText();
					List<Video> videoBuscado;
					try {
						String [] etiquetas=new String[modelo2.size()];
						for (int i = 0; i < etiquetas.length; i++) {
							etiquetas[i]=modelo2.get(i);
						}
						videoBuscado = appVideo.getVideos(texto,etiquetas);
						int filas = tablaVideosExplorar.getRowCount();
						for (int i = filas-1; i >= 0; i--)
							tm.removeRow(i);
						if (videoBuscado != null)
							tm.rellenarTabla(videoBuscado, vWeb);
					} catch (DAOException e1) {
						e1.printStackTrace();
					}			
				tm.fireTableDataChanged();
				validate();	
			}
		});
		
		panelBusqueda.add(Box.createRigidArea(new Dimension(30,30)));
		
		//Boton para resetear los criterios de busqueda
		JButton btnReset = new JButton("Reset");
		panelBusqueda.add(btnReset);
		
		//Panel este donde se encuentran las etiquetas a buscar
		JPanel panelEste = new JPanel();
		panelEste.setBounds(811, 34, 262, 590);
		panelEste.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelEste.setBackground(Color.BLACK);
		add(panelEste);

		//Scroll con el JList de las etiquetas disponibles
		JScrollPane scrollLista1=new JScrollPane();
		scrollLista1.setBounds(2, 36, 258, 235);
		modelo1.addElement("Música");
		modelo1.addElement("Serie");
		modelo1.addElement("Infantil");
		modelo1.addElement("Adulto");
		panelEste.setLayout(null);
		
		JLabel etiquetasDisponible = new JLabel("Etiquetas Disponibles");
		etiquetasDisponible.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetasDisponible.setBounds(33, 11, 189, 14);
		etiquetasDisponible.setForeground(Color.WHITE);
		etiquetasDisponible.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelEste.add(etiquetasDisponible);
		panelEste.add(scrollLista1);
		
		//JList de las etiquetas disponibles
		JList<String> etiquetasDisponibles = new JList<String>();
		scrollLista1.setViewportView(etiquetasDisponibles);
		etiquetasDisponibles.setModel(modelo1);
		
		//Evento que al hacer doble click sobre un elemento pasa a la JList de etiquetas seleccionadas
		etiquetasDisponibles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					String etiqueta=etiquetasDisponibles.getSelectedValue();
					modelo2.addElement(etiqueta);
				}
			}
		});
		
		JLabel lblEtiquetasElegidas = new JLabel("Etiquetas Elegidas");
		lblEtiquetasElegidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtiquetasElegidas.setBounds(33, 328, 189, 14);
		lblEtiquetasElegidas.setForeground(Color.WHITE);
		lblEtiquetasElegidas.setBackground(Color.WHITE);
		lblEtiquetasElegidas.setAlignmentX(0.5f);
		panelEste.add(lblEtiquetasElegidas);
		
		//JList con las etiquetas seleccionadas
		JList<String> etiquetasSeleccionadas = new JList<String>();
		etiquetasSeleccionadas.setModel(modelo2);
		JScrollPane scrollLista2=new JScrollPane(etiquetasSeleccionadas);
		scrollLista2.setBounds(2, 353, 258, 211);
		panelEste.add(scrollLista2);
		
		//Boton para eliminar las etiquetas seleccionadas
		JButton limpiarEtiquetas = new JButton("Limpiar Etiquetas");
		limpiarEtiquetas.setBounds(57, 564, 165, 23);
		limpiarEtiquetas.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelEste.add(limpiarEtiquetas);
		limpiarEtiquetas.addActionListener(ev ->{
			if(!modelo2.isEmpty())
				modelo2.removeAllElements();
		});
		
		btnReset.addActionListener(ev -> {
			int filas = tablaVideosExplorar.getRowCount();
			for (int i = filas-1; i >= 0; i--)
				tm.removeRow(i);
			tm.fireTableDataChanged();
			if(!modelo2.isEmpty())
				modelo2.removeAllElements();
			panelBusqueda.repaint();
			panelBusqueda.revalidate();
			validate();
		});
		
		//Evento de la tabla que al hacer doble click sobre un video se reproduce
		tablaVideosExplorar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					Video video=(Video) tablaVideosExplorar.getValueAt(tablaVideosExplorar.getSelectedRow(),tablaVideosExplorar.getSelectedColumn());
					CardLayout cl=(CardLayout)(panelCentral.getLayout());
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
					cl.show(panelCentral, "repro");
					vWeb.playVideo(Controlador.getUnicaInstancia().getUrlVideo(video));
					videoActual=video;
					Controlador.getUnicaInstancia().sumarReproduccion(video);
					validate();
				}
			}
		});

		//Evento para añadir la nueva etiqueta a un video que se esta reproduciendo
		nuevaEtiqueta.addActionListener(ev->{
			if(!this.etiquetaNueva.getText().equals("")) {
				modelo3.addElement(this.etiquetaNueva.getText());
				Controlador.getUnicaInstancia().nuevaEtiqueta(videoActual,this.etiquetaNueva.getText());
				this.etiquetaNueva.setText("");
				ActualizarEtiquetas();
			}
				
			});
		
		ActualizarEtiquetas();
	}
	
	//Funcion para actualizar la JList de las etiquetas disponibles
	public void ActualizarEtiquetas() {
		modelo1.removeAllElements();
		List<Etiqueta> e=Controlador.getUnicaInstancia().getEtiquetasDisponibles();
		for (Etiqueta etiqueta : e) {
			if(!modelo1.contains(etiqueta.getEtiqueta())) modelo1.addElement(etiqueta.getEtiqueta());
		}
		
	}
}
