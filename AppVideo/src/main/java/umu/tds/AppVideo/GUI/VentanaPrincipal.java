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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
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

import dominio.Video;

import javax.swing.JTextPane;

import java.awt.FlowLayout;
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

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private int posX=0;
	private int	posY=0;
	private JTextField escribir_busqueda;
	private JTable tablaVideos;
	private static VideoWeb vWeb = new VideoWeb();
	
	public static void main(String[] args) {

        JFrame frame = new VentanaPrincipal();
        frame.setVisible(true);
}
	
	public VentanaPrincipal() {
		setBounds(new Rectangle(100, 100, 1280, 720));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel_Explorar = new JPanel();
		panel_Explorar.setBounds(207, 96, 1073, 624);
		getContentPane().add(panel_Explorar);
		panel_Explorar.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_resultado = new JPanel();
		panel_resultado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_resultado.setBackground(Color.LIGHT_GRAY);
		panel_Explorar.add(panel_resultado, BorderLayout.CENTER);
		panel_resultado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tablaVideos = new JTable();
		tablaVideos.setDefaultRenderer(getClass(), new VideoLabelTabla());
		LineaVideos linea = new LineaVideos();
		LinkedList<LineaVideos> listaLineaVideos = new LinkedList<LineaVideos>();
		
		
		listaLineaVideos.add(linea);
		TablaVideos tm = new TablaVideos();
		
		tablaVideos.setModel(tm);
		tablaVideos.setRowHeight(125); //cambio en la altura para que se vean los titulos
		tablaVideos.getTableHeader().setUI(null);  //Elimina la cabecera
		TableColumnModel colModel=tablaVideos.getColumnModel();
		for(int i=0; i<4; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideos.setShowGrid(false);
		
		panel_resultado.add(tablaVideos);
		
		JScrollPane scrollPane = new JScrollPane(tablaVideos);
		panel_resultado.add(scrollPane);
		
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
				LinkedList<Video> videos=new LinkedList<Video>();
				try {
					videos=Controlador.getUnicaInstancia().getVideos(escribir_busqueda.getText());
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(videos.size()!=0) {
					tm.rellenarTabla(videos,getVideoWeb());
				}
			}
		});
		
		panel_busqueda.add(Box.createRigidArea(new Dimension(30,30)));
		
		JButton btn_Reset = new JButton("Reset");
		panel_busqueda.add(btn_Reset);
		
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
		
		JLabel textoUsuario = new JLabel("Usuario");
		textoUsuario.setFont(new Font("Lato Black", Font.BOLD, 15));
		textoUsuario.setForeground(Color.WHITE);
		textoUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/UserIcon.png")));
		int width = textoUsuario.getText().length()*15;
		textoUsuario.setBounds(277, 33, width, 29);
		panel_cabecera.add(textoUsuario);
		
		RoundedPanel panel = new RoundedPanel(45, new Color(41,41,41));
		panel.setBounds(275, 28, width, 39);
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
					panel_Explorar.setVisible(true);
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
					panel_Explorar.setVisible(false);
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
		
		boton_Nueva_Lista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				//TODO Funcionalidad de cerrar sesion
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
}


class LineaVideos {
	
	private Video v1, v2, v3, v4;
	private LinkedList<Video> videos = new LinkedList<>();
	
	public LineaVideos() {
		Video v1 = null, v2 = null, v3 = null, v4 = null;
		videos.add(v1);
		videos.add(v2);
		videos.add(v3);
		videos.add(v4);
	}
	
	public LineaVideos(Video...videos ) {
		this();
		v1 = videos[0];
		v2 = videos[1];
		v3 = videos[2];
		v4 = videos[3];
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




