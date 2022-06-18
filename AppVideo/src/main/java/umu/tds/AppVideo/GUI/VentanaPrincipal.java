package umu.tds.AppVideo.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.EventObject;
import java.util.LinkedList;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.itextpdf.text.DocumentException;

import java.awt.Insets;

import dominio.*;

import java.awt.*;
import javax.swing.JToggleButton;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import tds.video.VideoWeb;
import umu.tds.AppVideo.Persistencia.DAOException;
import umu.tds.Controlador.Controlador;



public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private int posX=0;
	private int	posY=0;

	private static VideoWeb vWeb = new VideoWeb();

	private static PanelMisLIstas misListas;
	private static PanelExplorar panelExplorar;
	private static PanelCrearListas panelNuevaLista;
	private static Recientes recientes;
	

	public VentanaPrincipal() throws DAOException {
		
		//array para añadir a las opciones de seleccionar un filtro
		String[] filtros= {"No Filtro","Filtro Adulto","Filtro Mis Listas"};
		
		setUndecorated(true);
		
		//Creación panel recientes
		recientes=new Recientes();
		
		//Creación panel mis listas
		misListas=new PanelMisLIstas();
		
		//Creación panel explorar
		panelExplorar=new PanelExplorar();
		
		//Creación panel crear listas
		panelNuevaLista=new PanelCrearListas();
		
		
		
		setBounds(new Rectangle(100, 100, 1280, 720));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//Creación panel para cambiar entre los paneles creados
		JPanel panelLayout = new JPanel();
		panelLayout.setBounds(207, 96, 1073, 624);
		getContentPane().add(panelLayout);
		panelLayout.setLayout(new CardLayout(0, 0));
		
		panelLayout.add(recientes, "recientes");
		
		panelLayout.add(misListas,"mis_listas");

		panelLayout.add(panelExplorar,"explorar");
		
		panelLayout.add(panelNuevaLista, "crear_listas");
		
		//Panel cabecera de la Ventana principal
		JPanel panelCabecera = new JPanel();
		panelCabecera.setOpaque(false);
		panelCabecera.setBorder(null);
		panelCabecera.setBounds(0, 0, 1280, 95);
		getContentPane().add(panelCabecera);
		panelCabecera.setLayout(null);
		
		//JLabel con El icono de la App
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/LogoNoText.png")));
		logo.setBounds(45, 5, 100, 95);
		panelCabecera.add(logo);
		
		//Jlabel con el nombre del usuario que ha entrado a la App
		JLabel textoUsuario = new JLabel(Controlador.getUnicaInstancia().getUsuarioActual().getUsuario());
		textoUsuario.setFont(new Font("Lato Black", Font.BOLD, 15));
		textoUsuario.setForeground(Color.WHITE);
		textoUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/UserIcon.png")));
		int width = textoUsuario.getText().length()*15;
		textoUsuario.setBounds(277, 33, 241, 29);
		panelCabecera.add(textoUsuario);
		
		RoundedPanel panel = new RoundedPanel(45, new Color(41,41,41));
		panel.setBounds(275, 28, 253, 39);
		panel.setOpaque(false);
		panelCabecera.add(panel);
		
		//Botón Premium para seleccionar los filtros
		JToggleButton botonPremium = new JToggleButton("");
		botonPremium.setContentAreaFilled(false);
		botonPremium.setBorderPainted(false);
		botonPremium.setSelectedIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumStatus.png")));
		botonPremium.setRolloverSelectedIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumStatus.png")));
		botonPremium.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumButton.png")));
		botonPremium.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/PremiumButton.png")));
		botonPremium.setBorder(null);
		botonPremium.setBounds(226, 28, 39, 39);
		panelCabecera.add(botonPremium);
		
		//Texto que indica el filtro actual
		JLabel filtroActual = new JLabel("Filtro Actual: "+Controlador.getUnicaInstancia().getFiltroActual());
		filtroActual.setFont(new Font("Dialog", Font.BOLD, 15));
		filtroActual.setForeground(Color.WHITE);
		filtroActual.setBounds(542, 36, 272, 23);
		panelCabecera.add(filtroActual);
		
		//Botón para generar el pdf si el usuario es premium junto a su evento
		JButton generarPDF = new JButton("Generar PDF");
		generarPDF.setFont(new Font("Dialog", Font.BOLD, 14));
		generarPDF.setBounds(940, 33, 151, 28);
		panelCabecera.add(generarPDF);
		
		generarPDF.addActionListener(ev->{
			if(Controlador.getUnicaInstancia().usuarioActualEsPremium())
				try {
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fc.showOpenDialog(fc);
					File directorio=fc.getSelectedFile();
					Controlador.getUnicaInstancia().generarPDf(directorio.toString());
					JOptionPane.showMessageDialog(generarPDF, "El PDF ha sido creado correctamente en "+directorio+"\\listasVideos.pdf","Éxito" ,JOptionPane.INFORMATION_MESSAGE,null);
				} catch (FileNotFoundException e1) {
					 JOptionPane.showMessageDialog(generarPDF,"No se ha encontrado el archivo", "File not Found", JOptionPane.ERROR_MESSAGE,null);
					e1.printStackTrace();
				} catch (DocumentException e1) {
					 JOptionPane.showMessageDialog(generarPDF,"No se ha podido crear el archivo PDF", "Error", JOptionPane.ERROR_MESSAGE,null);
					e1.printStackTrace();
				}
			else JOptionPane.showMessageDialog(generarPDF,"Debe ser Usuario Premium para esta funcionalidad", "Error selección de filtro", JOptionPane.ERROR_MESSAGE,null);
			
		});
		
		//evento del boton premium 
		botonPremium.addActionListener(ev-> {
			if(Controlador.getUnicaInstancia().usuarioActualEsPremium()) {
				String opcion=(String) JOptionPane.showInputDialog(botonPremium, "Seleccione el filtro que quiere usar", "Selección de filtro", JOptionPane.QUESTION_MESSAGE,null,filtros,filtros[0]);
				Controlador.getUnicaInstancia().cambiarFiltro(opcion);
				 filtroActual.setText("Filtro Actual: "+Controlador.getUnicaInstancia().getFiltroActual());
			}
			else JOptionPane.showMessageDialog(botonPremium,"Debe ser Usuario Premium para esta funcionalidad", "Error selección de filtro", JOptionPane.ERROR_MESSAGE,null);
				botonPremium.setSelected(false);
		});
		
		//Creación de panel con los botones de la Ventana Principal
		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		panelBotones.setBorder(null);
		panelBotones.setBounds(0, 106, 206, 614);
		getContentPane().add(panelBotones);
		panelBotones.setLayout(null);
		
		//Botón explorar con su evento
		JButton botonExplorar = new JButton("        Explorar");
		botonExplorar.setMargin(new Insets(2, 25, 2, 14));
		botonExplorar.setRequestFocusEnabled(false);
		botonExplorar.setHorizontalAlignment(SwingConstants.LEFT);
		botonExplorar.setContentAreaFilled(false);
		botonExplorar.setBorderPainted(false);
		botonExplorar.setOpaque(false);
		botonExplorar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/HomeIcon.png")));
		botonExplorar.setFont(new Font("Lato Black", Font.BOLD, 15));
		botonExplorar.setForeground(Color.WHITE);
		botonExplorar.setBounds(0, 139, 206, 46);
		panelBotones.add(botonExplorar);
		
		botonExplorar.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	botonExplorar.setContentAreaFilled(true);
		    	botonExplorar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	botonExplorar.setContentAreaFilled(false);		    
		    	}
		    
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panelLayout.getLayout());
					cl.show(panelLayout, "explorar");
				}
			}
		});
		
		//Botón mis listas con su evento
		JButton botonMisListas = new JButton("        Mis Listas");
		botonMisListas.setMargin(new Insets(2, 25, 2, 14));
		botonMisListas.setOpaque(false);
		botonMisListas.setRequestFocusEnabled(false);
		botonMisListas.setHorizontalAlignment(SwingConstants.LEFT);
		botonMisListas.setContentAreaFilled(false);
		botonMisListas.setBorderPainted(false);
		botonMisListas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/LibraryIcon.png")));
		botonMisListas.setForeground(Color.WHITE);
		botonMisListas.setFont(new Font("Lato Black", Font.BOLD, 15));
		botonMisListas.setBounds(0, 185, 206, 46);
		panelBotones.add(botonMisListas);
		
		botonMisListas.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent evt) {
		    	botonMisListas.setContentAreaFilled(true);
		    	botonMisListas.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	botonMisListas.setContentAreaFilled(false);		    
		    }
		    
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panelLayout.getLayout());
					misListas.ActualizarListas();
					cl.show(panelLayout, "mis_listas");
				}
			}
		});
		
		//Botón recientes con su evento
		JButton botonRecientes = new JButton("        Recientes");
		botonRecientes.setMargin(new Insets(2, 25, 2, 14));
		botonRecientes.setOpaque(false);
		botonRecientes.setRequestFocusEnabled(false);
		botonRecientes.setHorizontalAlignment(SwingConstants.LEFT);
		botonRecientes.setContentAreaFilled(false);
		botonRecientes.setBorderPainted(false);
		botonRecientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/RecentsIcon.png")));
		botonRecientes.setForeground(Color.WHITE);
		botonRecientes.setFont(new Font("Lato Black", Font.BOLD, 15));
		botonRecientes.setBounds(0, 230, 206, 46);
		panelBotones.add(botonRecientes);
		
		botonRecientes.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	botonRecientes.setContentAreaFilled(true);
		    	botonRecientes.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	botonRecientes.setContentAreaFilled(false);		    
		    }
		    
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panelLayout.getLayout());
					try {
						recientes.mostrarListas();
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cl.show(panelLayout, "recientes");
				}
			}
		});
		
		//Botón nueva listas con su evento
		JButton botonNuevaLista = new JButton("        Nueva Lista");
		botonNuevaLista.setMargin(new Insets(2, 25, 2, 14));
		botonNuevaLista.setOpaque(false);
		botonNuevaLista.setRequestFocusEnabled(false);
		botonNuevaLista.setHorizontalAlignment(SwingConstants.LEFT);
		botonNuevaLista.setContentAreaFilled(false);
		botonNuevaLista.setBorderPainted(false);
		botonNuevaLista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/NewListIcon.png")));
		botonNuevaLista.setForeground(Color.WHITE);
		botonNuevaLista.setFont(new Font("Lato Black", Font.BOLD, 15));
		botonNuevaLista.setBounds(0, 275, 206, 46);
		panelBotones.add(botonNuevaLista);
		
		botonNuevaLista.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	botonNuevaLista.setContentAreaFilled(true);
		    	botonNuevaLista.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	botonNuevaLista.setContentAreaFilled(false);		    
		    }
		    
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					CardLayout cl=(CardLayout)(panelLayout.getLayout());
					cl.show(panelLayout, "crear_listas");
				}
			}
		});
		
		//Botón cerrar sesión con su evento
		JButton botonLogout = new JButton("CERRAR SESION");
		botonLogout.setBorder(null);
		botonLogout.setBorderPainted(false);
		botonLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		botonLogout.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/Logout.png")));
		botonLogout.setRolloverEnabled(true);
		botonLogout.setRolloverIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/Resources/LogoutHover.png")));
		botonLogout.setFont(new Font("Lato Black", Font.BOLD, 15));
		botonLogout.setForeground(Color.BLACK);
		botonLogout.setContentAreaFilled(false);
		botonLogout.setFocusPainted(false);
		botonLogout.setBounds(19, 548, 169, 58);
		panelBotones.add(botonLogout);
		botonLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.mostarVentana();
				dispose();
			}
		});
			
		
		Luz luz = new Luz();
		luz.color = Color.YELLOW;
		luz.setBounds(53, 385, 99, 102);
		panelBotones.add(luz);
		
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject evt) {
				if(luz.isEncendido()) {
					JFileChooser selectorArchivos = new JFileChooser();
					selectorArchivos.showOpenDialog(selectorArchivos);
					File archivo_xml = selectorArchivos.getSelectedFile(); // obtiene el archivo seleccionado
					Controlador.getUnicaInstancia().cargarVideos(archivo_xml);
					panelExplorar.ActualizarEtiquetas();
				}
					
			}
		});
		
		//Botón cerrar con su evento para cerra
		JButton botonCerrar = new JButton("");
		botonCerrar.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/AppVideo/Resources/Close button.png")));
		botonCerrar.setBackground(Color.BLACK);
		botonCerrar.setForeground(Color.WHITE);
		botonCerrar.setFont(new Font("Dialog", Font.BOLD, 30));
		botonCerrar.setBorder(null);
		botonCerrar.setBounds(1240, 0, 40, 26);
		botonCerrar.setFocusable(false);
		getContentPane().add(botonCerrar);
		
		botonCerrar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evento) {
				if(evento.getButton()==MouseEvent.BUTTON1)
					System.exit(0);
			}
			public void mouseEntered(MouseEvent evt) {
				botonCerrar.setContentAreaFilled(true);
				botonCerrar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
				botonCerrar.setContentAreaFilled(false);		    
		    }
		});
		
		//Botón para minimizar la ventana principal
		JButton botonMinimizar = new JButton("");
		botonMinimizar.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/AppVideo/Resources/Minimize.png")));
		botonMinimizar.setForeground(Color.WHITE);
		botonMinimizar.setFont(new Font("Dialog", Font.BOLD, 30));
		botonMinimizar.setFocusable(false);
		botonMinimizar.setBorder(null);
		botonMinimizar.setBackground(Color.BLACK);
		botonMinimizar.setBounds(1200, 0, 40, 26);
		getContentPane().add(botonMinimizar);
		
		botonMinimizar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evento) {
				if(evento.getButton()==MouseEvent.BUTTON1)
					setState(JFrame.ICONIFIED);
			}
			
			public void mouseEntered(MouseEvent evt) {
				botonMinimizar.setContentAreaFilled(true);
				botonMinimizar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
				botonMinimizar.setContentAreaFilled(false);		    
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




