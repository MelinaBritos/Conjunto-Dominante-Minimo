package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Test;
import model.AlgoritmoGoloso;
import model.Grafo;

public class AlgoritmoGolosoTest {

	@Test(expected = IllegalArgumentException.class)
	public void grafoVacioTest() {
		Grafo ejemplo = new Grafo(0);
		AlgoritmoGoloso solver = new AlgoritmoGoloso(ejemplo, new Comparator<Integer>() {
			@Override
			public int compare(Integer uno, Integer otro) {
				return ejemplo().vecinos(otro).size() - ejemplo().vecinos(uno).size();
			}
		});	
		solver.resolver();
	}
	
	@Test
	public void verticeAisladoTest() {
		AlgoritmoGoloso solver = new AlgoritmoGoloso(ejemplo(), new Comparator<Integer>() {
			@Override
			public int compare(Integer uno, Integer otro) {
				return ejemplo().vecinos(otro).size() - ejemplo().vecinos(uno).size();
			}
		});
		ArrayList<Integer> solucion = solver.resolver();
		assertTrue(solucion.contains(0));
	}

	@Test
	public void resolverTest() {
		AlgoritmoGoloso solver = new AlgoritmoGoloso(ejemplo(), new Comparator<Integer>() {
			@Override
			public int compare(Integer uno, Integer otro) {
				return ejemplo().vecinos(otro).size() - ejemplo().vecinos(uno).size();
			}
		});

		ArrayList<Integer> solucion = solver.resolver();
		int[] esperado = {0, 2, 4};
		Assert.igualesArrayList(esperado, solucion);
	}

	@Test
	public void todosAisladosTest() {
		Grafo ejemplo = new Grafo(7);
		AlgoritmoGoloso solver = new AlgoritmoGoloso(ejemplo, new Comparator<Integer>() {
			@Override
			public int compare(Integer uno, Integer otro) {
				return ejemplo().vecinos(otro).size() - ejemplo().vecinos(uno).size();
			}
		});
		ArrayList<Integer> solucion = solver.resolver();
		assertTrue(solucion.size() == 7);
	}

	private Grafo ejemplo() {
		Grafo ret = new Grafo(7);
		ret.agregarArista(4, 6);
		ret.agregarArista(4, 3);
		ret.agregarArista(4, 5);
		ret.agregarArista(3, 2);
		ret.agregarArista(2, 5);
		ret.agregarArista(1, 5);
		ret.agregarArista(1, 2);
		return ret;
	}

}
