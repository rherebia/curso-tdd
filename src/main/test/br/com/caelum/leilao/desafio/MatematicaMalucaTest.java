package br.com.caelum.leilao.desafio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaMalucaTest {

	@Test
	public void contaMalucaComNumeroMaiorQueTrinta() {
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int contaMaluca = matematicaMaluca.contaMaluca(35);
		
		assertEquals(140, contaMaluca);
	}
	
	@Test
	public void contaMalucaComNumeroMaiorQueDezEMenorQueTrinta() {
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int contaMaluca = matematicaMaluca.contaMaluca(25);
		
		assertEquals(75, contaMaluca);
	}
	
	@Test
	public void contaMalucaComNumeroMenorQueOnze() {
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int contaMaluca = matematicaMaluca.contaMaluca(7);
		
		assertEquals(14, contaMaluca);
	}
}
