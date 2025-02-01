package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


public class AlgoritmoGoloso {

	private Grafo grafo;
	private Comparator<Integer> comparador;
	private HashSet<Integer> alcanzados;

	public AlgoritmoGoloso(Grafo grafo, Comparator<Integer> comparador) {
		this.grafo = grafo;
		this.comparador = comparador; 
		this.alcanzados = new HashSet<Integer>();
	}

	public ArrayList<Integer> resolver() {
		if (grafo.tamano() == 0) {
			throw new IllegalArgumentException("El grafo esta vacio");
		}
		ArrayList<Integer> solucion = new ArrayList<Integer>();

		for (Integer vertice: verticesOrdenados()) {
			if ((alcanzados.size() != grafo.tamano() && !todosRepetidos(alcanzados, grafo.vecinos(vertice))) 
					|| grafo.vecinos(vertice).size() == 0){
				agregarVertices(alcanzados, vertice);
				solucion.add(vertice);
			}
		}
		return solucion;
	}

	private ArrayList<Integer> verticesOrdenados() {
		ArrayList<Integer> ret = new ArrayList<Integer>(grafo.tamano());
		for(int i = 0; i < grafo.tamano(); i++) {
			ret.add(i);
		}
		Collections.sort(ret, comparador);
		return ret;
	}
	
	private boolean todosRepetidos(HashSet<Integer> alcanzados, Set<Integer> vecinos) {
		boolean repiteTodos = true;
		for (Integer number: vecinos) {
			repiteTodos = repiteTodos && alcanzados.contains(number);
		}
		
		return repiteTodos;
	}

	private void agregarVertices(HashSet<Integer> alcanzados, int vertice) {
		alcanzados.add(vertice);
		for (Integer v: grafo.vecinos(vertice)) {
			if(!alcanzados.contains(v))
				alcanzados.add(v);
		}
	}
}
