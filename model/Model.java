package model;

import java.util.ArrayList;
import java.util.Comparator;

public class Model {
	
	private static Grafo grafo;
	
	public static void crearGrafo(int n) {
		grafo = new Grafo(n);
	}
	
	public static void agregarArista(int i, int j) {
		grafo.agregarArista(i, j);
	}
	
	public static boolean existeArista(int i, int j) {
		return grafo.existeArista(i, j);
	}
	
	public static ArrayList<Integer> calcularConjunto(){
		AlgoritmoGoloso solver = new AlgoritmoGoloso(grafo, new Comparator<Integer>() {
			@Override
			public int compare(Integer uno, Integer otro) {
				return grafo.vecinos(otro).size() - grafo.vecinos(uno).size();
			}
		});
		
		return solver.resolver();
	}
	
	public static Grafo devolverGrafo() {
		return grafo;
	}
}
