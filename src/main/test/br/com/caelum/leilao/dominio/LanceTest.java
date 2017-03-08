package br.com.caelum.leilao.dominio;

import org.junit.Test;

public class LanceTest {

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitarLanceMenorDoQueZero() {
		Usuario usuario = new Usuario("Renato");
		new Lance(usuario, -10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitarLanceIgualAZero() {
		Usuario usuario = new Usuario("Renato");
		new Lance(usuario, 0);
	}
}
