package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public View() {
		initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 192));
		frame.setBounds(0, 0, 428, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblTitulo = new JLabel("Conjunto Dominante Minimo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(26, 11, 340, 34);
		frame.getContentPane().add(lblTitulo);

		JLabel lblcantVertices = new JLabel("Cantidad de vertices:");
		lblcantVertices.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblcantVertices.setBounds(26, 129, 143, 23);
		frame.getContentPane().add(lblcantVertices);

		JTextField cantVertices = new JTextField();
		cantVertices.setBounds(195, 131, 145, 20);
		frame.getContentPane().add(cantVertices);
		cantVertices.setColumns(10);

		JLabel aristas = new JLabel("Aristas entre:");
		aristas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		aristas.setBounds(26, 318, 143, 23);
		frame.getContentPane().add(aristas);

		JComboBox verticeArista = new JComboBox();
		verticeArista.setBounds(201, 319, 38, 22);
		frame.getContentPane().add(verticeArista);	

		JComboBox verticeArista1 = new JComboBox();
		verticeArista1.setBounds(302, 319, 38, 22);
		frame.getContentPane().add(verticeArista1);

		JButton btnCrearGrafo = new JButton("Crear Grafo");
		btnCrearGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cantV = Integer.parseInt(cantVertices.getText());
					if(cantV == 0 || cantV == 1) {
						JOptionPane.showMessageDialog(frame, "Ingrese una cantidad de vertices adecuada" );
					}else {
						verticeArista.setModel(new DefaultComboBoxModel(crearArreglo(cantV)));
						verticeArista1.setModel(new DefaultComboBoxModel(crearArreglo(cantV)));
						cantVertices.setEditable(false);
						btnCrearGrafo.setEnabled(false);
						Model.crearGrafo(cantV);
					}	            
				} catch (NumberFormatException m) {
					JOptionPane.showMessageDialog(frame, "Ingrese una cantidad de vertices adecuada" );
				}
			}
		});
		btnCrearGrafo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrearGrafo.setBounds(217, 203, 123, 23);
		frame.getContentPane().add(btnCrearGrafo);

		JButton btnAgregarArista = new JButton("Agregar Arista");
		btnAgregarArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cantVertices.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Ingrese una cantidad de vertices adecuada" );
				}else if(verticeArista.getSelectedItem() == null || verticeArista1.getSelectedItem() == null){
					JOptionPane.showMessageDialog(frame, "ingrese dos vertices validos" );
				}else if (verticeArista.getSelectedItem().equals(verticeArista1.getSelectedItem()) ){
					JOptionPane.showMessageDialog(frame, "No se permiten loops" );
				}else if (Model.existeArista(Integer.parseInt((String)verticeArista.getSelectedItem()), Integer.parseInt((String)verticeArista1.getSelectedItem()))){
					JOptionPane.showMessageDialog(frame, "La arista ya existe" );;			
				}else{
					Model.agregarArista(Integer.parseInt((String)verticeArista.getSelectedItem()), Integer.parseInt((String)verticeArista1.getSelectedItem()));
				} 
			}
		});
		btnAgregarArista.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAgregarArista.setBounds(217, 402, 123, 23);
		frame.getContentPane().add(btnAgregarArista);

		JButton calcularConjunto = new JButton("Calcular Conjunto");
		calcularConjunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cantVertices.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Debe crear el grafo para calcular el conjunto" );
				}else {
					ViewConjuntos.iniciar(Model.devolverGrafo(), Model.calcularConjunto());
					frame.dispose();
				}
			}
		});
		calcularConjunto.setFont(new Font("Tahoma", Font.BOLD, 11));
		calcularConjunto.setBounds(141, 525, 137, 23);
		frame.getContentPane().add(calcularConjunto);
	}

	private String[] crearArreglo(int n) {
		String[] ret = new String[n];
		for (int i = 0; i < n; i++) {
			ret[i] = Integer.toString(i);
		}

		return ret;
	}
}
