package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Grafo;


import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class ViewConjuntos {

	private static JFrame frame;
	private static Grafo grafoIngresado;
	private static ArrayList<Integer> conjuntoDominante;

	public static void iniciar(Grafo grafo, ArrayList<Integer> conjunto) {
		grafoIngresado = grafo;
		conjuntoDominante = conjunto;
		new ViewConjuntos();
		ViewConjuntos.frame.setVisible(true);
	}

	public ViewConjuntos() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 192));
		frame.setBounds(0, 0, 428, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JButton btnvolver = new JButton("Volver");
		btnvolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnvolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.main(null);;
				frame.dispose();
			}
		});
		btnvolver.setBounds(160, 627, 89, 23);
		frame.getContentPane().add(btnvolver);

		JPanel grafo = new JPanel();
		grafo.setBounds(22, 24, 364, 294);
		frame.getContentPane().add(grafo);
		dibujarGrafo(grafo);

		JPanel conjunto = new JPanel();
		conjunto.setBounds(22, 343, 364, 257);
		frame.getContentPane().add(conjunto);
		dibujarConjuntoDominante(conjunto);

	}

	private void dibujarGrafo(JPanel panel) {
		mxGraph graph = new mxGraph();
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		panel.setLayout(new BorderLayout());
		panel.add(graphComponent, BorderLayout.CENTER);
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();

		Object[] vertices= agregarVertices(graph, parent, false);
		agregarAristas(graph, parent, vertices);


		graph.getModel().endUpdate();
	}

	private void dibujarConjuntoDominante(JPanel panel) {
		mxGraph graph = new mxGraph();
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		panel.setLayout(new BorderLayout());
		panel.add(graphComponent, BorderLayout.CENTER);
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();

		agregarVertices(graph, parent, true);

		graph.getModel().endUpdate();
	}

	private Object[] agregarVertices(mxGraph graph, Object parent, boolean conjunto) {
		int x;
		int y;
		if(!conjunto) { //dibuja grafo
			Object[] vertices = new Object[grafoIngresado.tamano()];
			for (int i = 0; i < grafoIngresado.tamano(); i++) {
				x = generarXrandom();
				y = generarYrandom();
				vertices[i] = graph.insertVertex(parent, null, i, x, y, 80, 30);
			}
			return vertices;

		}else{ //dibuja conjunto
			Object[] vertices = new Object[conjuntoDominante.size()];
			for (int i = 0; i < conjuntoDominante.size(); i++) {
				x = generarXrandom();
				y = generarYrandom();
				vertices[i] = graph.insertVertex(parent, null, conjuntoDominante.get(i) , x, y, 80, 30);
			}
			return vertices;
		}
	}

	private int generarXrandom() {
		Random random = new Random();
		return random.nextInt(300);

	}

	private int generarYrandom() {
		Random random = new Random();
		return random.nextInt(300);
	}
	
	private void agregarAristas(mxGraph graph, Object parent, Object[] vertices) {
		for (int i = 0; i < vertices.length; i++) {
			for (int vecino: grafoIngresado.vecinos(i)) {
				graph.insertEdge(parent, null, null, vertices[i], vertices[vecino]);
			}
		}
	}

}
