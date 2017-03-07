package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		assertEquals(0,leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.000001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		assertEquals(0,leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.000001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.000001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		
		Usuario usuario = new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(usuario, 2000));
		leilao.propoe(new Lance(usuario, 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.000001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQueCincoLancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15");

		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steve, 2000));
		leilao.propoe(new Lance(bill, 3000));
		
		leilao.propoe(new Lance(steve, 4000));
		leilao.propoe(new Lance(bill, 5000));
		
		leilao.propoe(new Lance(steve, 6000));
		leilao.propoe(new Lance(bill, 7000));
		
		leilao.propoe(new Lance(steve, 8000));
		leilao.propoe(new Lance(bill, 9000));
		
		leilao.propoe(new Lance(steve, 10000));
		leilao.propoe(new Lance(bill, 11000));
		
		leilao.propoe(new Lance(bill, 12000));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
	}
	
	@Test
	public void deveAceitarDobrarUltimoLanceDoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15");

		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steve, 2000));
		leilao.propoe(new Lance(bill, 3000));
		
		leilao.dobraLance(steve);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDobrarUltimoLanceDoUsuarioSeNaoExistir() {
		Leilao leilao = new Leilao("MacBook Pro 15");

		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(bill, 3000));
		
		leilao.dobraLance(steve);
		
		assertEquals(1, leilao.getLances().size());
	}
}
