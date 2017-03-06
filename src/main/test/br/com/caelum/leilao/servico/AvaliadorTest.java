package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	@Test
    public void deveEntenderLancesEmOrdemCrescente() {
        // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,500.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double maiorEsperado = 500;
        double menorEsperado = 300;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(400, leiloeiro.getValorMedio(), 0.00001);
    }

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("joao");
		Leilao leilao = new Leilao("playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 1000));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaiores() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(maria, 200));
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(maria, 400));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		assertEquals(3, tresMaiores.size());
		
		assertEquals(400, tresMaiores.get(0).getValor(), 0.000001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.000001);
		assertEquals(200, tresMaiores.get(2).getValor(), 0.000001);
	}
	
	@Test
	public void deveEntenderValoresAleatorios() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 450));
		leilao.propoe(new Lance(joao, 120));
		leilao.propoe(new Lance(maria, 700));
		leilao.propoe(new Lance(joao, 630));
		leilao.propoe(new Lance(maria, 230));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(700, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderValoresDecrescentes() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 100));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(100, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void verificaTresMaioresEmCincoLances() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 100));
		leilao.propoe(new Lance(maria, 50));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);

		assertEquals(3, leiloeiro.getTresMaiores().size());
		assertEquals(400, leiloeiro.getTresMaiores().get(0).getValor(), 0.00001);
		assertEquals(300, leiloeiro.getTresMaiores().get(1).getValor(), 0.00001);
		assertEquals(200, leiloeiro.getTresMaiores().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void verificaTresMaioresEmDoisLances() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);

		assertEquals(2, leiloeiro.getTresMaiores().size());
		assertEquals(400, leiloeiro.getTresMaiores().get(0).getValor(), 0.00001);
		assertEquals(300, leiloeiro.getTresMaiores().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void verificaTresMaioresSemLances() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("playstation 3 novo");
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);

		assertEquals(0, leiloeiro.getTresMaiores().size());
	}
}
