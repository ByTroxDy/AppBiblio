package socio;

import app.Documento;
import db.DocumentoMaxDB;
import db.UsuarioMaxDB;
import gestor.MenuGestor;
import gestor.VentanaModificarDocumento;
import admin.MenuAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaConsultarDocumento extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JComboBox<Object> cmbTipo;
	private JButton btnVolver, btnBuscar, btnVolverBuscar, btnPedirReserva, btnBajaDoc, btnModDoc;

	private int filaSeleccionada, isbn;
	private String titulo, tipo, replicas;
	static String usuario, grupo;

	public VentanaConsultarDocumento() {
		setTitle("Consultar Document");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Títol");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo = new JTextField(20);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);

	 	JLabel lblTipo = new JLabel("Tipus");
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        String[] tiposDocumento = {"Tots", "Llibre", "Pel·lícula", "Documental", "Música"};
        cmbTipo = new JComboBox<>(tiposDocumento);

		btnVolver = new JButton("Enrere");
		btnBuscar = new JButton("Cerca");

		panel.add(lblTitulo);
		panel.add(txtTitulo);
		panel.add(lblTipo);
		panel.add(cmbTipo);
		panel.add(btnVolver);
		panel.add(btnBuscar);

		getContentPane().add(panel);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				if (usuario == null) {
					VentanaInicioSesion app = new VentanaInicioSesion();
					app.setVisible(true);
				} else if (grupo.equals("socio")) {
					MenuSocio menu = new MenuSocio();
					menu.setVisible(true);
				} else if (grupo.equals("gestor")) {
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
				} else if (grupo.equals("admin")) {
					MenuAdmin menu = new MenuAdmin();
					menu.setVisible(true);
				}
				dispose();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				titulo = txtTitulo.getText();
				tipo = (String) cmbTipo.getSelectedItem();

				consultarDocumentos(titulo, tipo);
				dispose();
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	public String getTitulo() {
		return titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void consultarDocumentos(String titulo, String tipo) {
		ArrayList<Documento> documentos;
		DocumentoMaxDB docDB = new DocumentoMaxDB();
		UsuarioMaxDB usuDB = new UsuarioMaxDB();
		
		if (!titulo.isEmpty() && tipo.equals("Tots")) {
	        documentos = docDB.consultarDocumentosPorNombre(titulo);
	    } else if (titulo.isEmpty() && !tipo.equals("Tots")) {
	        documentos = docDB.consultarDocumentosPorTipo(tipo);
	    } else if (!titulo.isEmpty() && !tipo.equals("Tots")) {
	        documentos = docDB.consultarDocumentosPorNombreYTipo(titulo, tipo);
	    } else {
	        documentos = docDB.consultarTodosDocumentos();
	    }

		if (documentos.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"No s'han trobat documents que coincideixin amb els criteris de cerca.", "Avís",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			JFrame ventanaResultados = new JFrame("Resultats de la consulta");

			// Crear un modelo de tabla para los documentos
			DefaultTableModel modeloTabla = new DefaultTableModel();

			modeloTabla.addColumn("ISBN");
			modeloTabla.addColumn("Títol");
			modeloTabla.addColumn("Tipus");
			modeloTabla.addColumn("Estat");

			// Llenar el modelo de tabla con los datos de los documentos
			for (Documento documento : documentos) {
				Object[] fila = new Object[4];
				fila[0] = documento.getISBN();
				fila[1] = documento.getTitulo();
				fila[2] = documento.getTipo();
				fila[3] = documento.getReplicas();

				if (documento.getReplicas() != 0) {
					fila[3] = "Lliure";
				} else {
					fila[3] = "Reservat";
				}

				modeloTabla.addRow(fila);
			}

			JTable tablaDocumentos = new JTable(modeloTabla);
			tablaDocumentos.setDefaultEditor(Object.class, null); // Desactivar la edición de las celdas

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			tablaDocumentos.setDefaultRenderer(Object.class, centerRenderer); // Centrar el texto en las celdas

			tablaDocumentos.getTableHeader().setEnabled(false); // Desactivar arrastrar y soltar
			tablaDocumentos.getTableHeader().setResizingAllowed(false); // Desactivar la modificación de las columnas

			JScrollPane scrollPane = new JScrollPane(tablaDocumentos);

			btnVolverBuscar = new JButton("Tornar a Cerca");
			btnPedirReserva = new JButton("Demanar Reserva");
			btnBajaDoc = new JButton("Donar de baixa");
			btnModDoc = new JButton("Modificar Doc");

			// Configurar el panel de botones
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(btnVolverBuscar);
			panelBotones.add(btnPedirReserva);
			panelBotones.add(btnBajaDoc);
			panelBotones.add(btnModDoc);

			// Configurar el panel principal
			JPanel panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new BorderLayout());
			panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
			panelPrincipal.add(scrollPane, BorderLayout.CENTER);
			panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

			ventanaResultados.getContentPane().add(panelPrincipal);
			
			if (usuario == null) {
				btnPedirReserva.setVisible(false);
			}

			if (grupo == null) {
				btnBajaDoc.setVisible(false);
			} else if (grupo.equals("socio")) {
				btnBajaDoc.setVisible(false);
			}

			btnVolverBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					VentanaConsultarDocumento ventana = new VentanaConsultarDocumento();
					ventana.setVisible(true);
					ventanaResultados.dispose();
				}
			});

			btnPedirReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					filaSeleccionada = tablaDocumentos.getSelectedRow();
					if (filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(ventanaResultados, "Seleccioneu un document de la taula.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						isbn = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
						replicas = (String) tablaDocumentos.getValueAt(filaSeleccionada, 3);
						if (usuDB.validarSancion(usuario)) {
							JOptionPane.showMessageDialog(ventanaResultados,
									"Aquestes banejades no podràs sol·licitar cap document.", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else if (replicas == "Lliure") {
							docDB.prestamoDocumento(usuario, isbn);
						} else {
							if (docDB.reservarDocumento(usuario, isbn)) {
								JOptionPane.showMessageDialog(ventanaResultados, "Reserva feta amb èxit.",
										"Reserva", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(ventanaResultados, "Ja heu reservat aquest document.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			});

			btnBajaDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					filaSeleccionada = tablaDocumentos.getSelectedRow();
					if (filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(ventanaResultados, "Seleccioneu un document de la taula.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						isbn = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
						docDB.bajaDocumento(isbn);
						JOptionPane.showMessageDialog(ventanaResultados,
								"S'ha donat de baixa correctament a l'isbn: " + isbn, "Baixa document",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			
			btnModDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					filaSeleccionada = tablaDocumentos.getSelectedRow();
					if (filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(ventanaResultados, "Seleccioneu un document de la taula.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						isbn = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
						VentanaModificarDocumento.isbn = isbn;
						VentanaModificarDocumento ventana = new VentanaModificarDocumento();
						ventana.setVisible(true);
						ventanaResultados.dispose();
					}
				}
			});
			
			ventanaResultados.pack();
			ventanaResultados.setVisible(true);
			ventanaResultados.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		}
	}
}
