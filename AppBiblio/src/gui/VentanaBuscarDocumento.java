package gui;

import app.Documento;
import app.ConexionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class VentanaBuscarDocumento extends JDialog {
	private JTextField txtTitulo;
	private JTextField txtTipoDocumento;
	private JButton btnVolver;
	private JButton btnBuscar;

	private String titulo;
	private String tipoDocumento;

	public VentanaBuscarDocumento() {
		setTitle("Buscar Documento");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo = new JTextField(20);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTipoDocumento = new JLabel("Tipo de Documento");
		lblTipoDocumento.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoDocumento = new JTextField(20);
		txtTipoDocumento.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnVolver = new JButton("Volver");
		btnBuscar = new JButton("Buscar");
		
        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblTipoDocumento);
        panel.add(txtTipoDocumento);
        panel.add(btnVolver);
        panel.add(btnBuscar);
		
        getContentPane().add(panel);
        
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	MenuSocio menu = new MenuSocio();
				menu.setVisible(true);
            	setVisible(false);
            }
        });

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				titulo = txtTitulo.getText();
				tipoDocumento = txtTipoDocumento.getText();
				
				consultarDocumentos(titulo, tipoDocumento);
				dispose();
			}
		});
		
		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	public String getTitulo() {
		return titulo;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void consultarDocumentos(String titulo, String tipo) {
		ArrayList<Documento> documentos = consultarDocumentosDB(titulo, tipo);

		JFrame ventanaResultados = new JFrame("Resultados de la consulta");

		// Crear un modelo de tabla para los documentos
		DefaultTableModel modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Titulo");
		modeloTabla.addColumn("Autor");

		// Llenar el modelo de tabla con los datos de los documentos
		for (Documento documento : documentos) {
			Object[] fila = new Object[3];
			fila[0] = documento.getId();
			fila[1] = documento.getNombre();
			fila[2] = documento.getAutor();

			modeloTabla.addRow(fila);
		}

		JTable tablaDocumentos = new JTable(modeloTabla);

		// Agregar la tabla a un JScrollPane para permitir el desplazamiento si hay
		// muchos resultados
		JScrollPane scrollPane = new JScrollPane(tablaDocumentos);

		ventanaResultados.getContentPane().add(scrollPane);

		// Configurar propiedades de la ventana de resultados
		ventanaResultados.setSize(500, 400);
		ventanaResultados.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		ventanaResultados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo la ventana de resultados
		ventanaResultados.setVisible(true);
	}

	public ArrayList<Documento> consultarDocumentosDB(String titulo, String tipo) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {
			
			String query = "SELECT * FROM documentos WHERE nombre_documento LIKE ? AND tipo_documento = ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + titulo + "%");
			statement.setString(2, tipo);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int idDocumento = resultSet.getInt("id_documento");
				String typeDocumento = resultSet.getString("tipo_documento");
				String nombreDocumento = resultSet.getString("nombre_documento");
				String descripcionDocumento = resultSet.getString("descripcion");
				String autorDocumento = resultSet.getString("autor");
				String editorialProductoraDocumento = resultSet.getString("editorial_productora");
				int numPaginasDuracionDocumento = resultSet.getInt("npaginas_duracion");
				String formatoDocumento = resultSet.getString("formato ");
				Date fechaPublicacionDocumento = resultSet.getDate("fecha_publicacion");

				Documento documento = new Documento(idDocumento, typeDocumento, nombreDocumento, descripcionDocumento,
						autorDocumento, editorialProductoraDocumento, numPaginasDuracionDocumento, formatoDocumento,
						fechaPublicacionDocumento);
				documentos.add(documento);
			}

			resultSet.close();
			statement.close();
			conn.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQLException: " + ex.getMessage());
			System.exit(0);
		}

		return documentos;
	}
	
}
