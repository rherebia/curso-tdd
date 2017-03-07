package br.com.caelum.leilao.desafio;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveIdentificarAnoBissextoMultiploDe400() {
		assertTrue(new AnoBissexto().ehBissexto(400));
		assertTrue(new AnoBissexto().ehBissexto(800));
		assertTrue(new AnoBissexto().ehBissexto(1200));
		assertTrue(new AnoBissexto().ehBissexto(1600));
		assertTrue(new AnoBissexto().ehBissexto(2000));
	}
	
	@Test
	public void deveIdentificarAnoBissextoMultiploDe100() {
		assertTrue(new AnoBissexto().ehBissexto(1800));
		assertTrue(new AnoBissexto().ehBissexto(1900));
		assertTrue(new AnoBissexto().ehBissexto(2100));
	}
	
	@Test
	public void deveIdentificarAnoBissextoMultiploDe4() {
		assertTrue(new AnoBissexto().ehBissexto(2008));
		assertTrue(new AnoBissexto().ehBissexto(2012));
		assertTrue(new AnoBissexto().ehBissexto(2016));
	}
	
	@Test
	public void naoDeveIdentificarAnoBissexto() {
		assertFalse(new AnoBissexto().ehBissexto(2005));
		assertFalse(new AnoBissexto().ehBissexto(2006));
		assertFalse(new AnoBissexto().ehBissexto(2007));
	}
}
