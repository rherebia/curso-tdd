package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void criaAvaliador() {
		leiloeiro = new Avaliador();
		joao = new Usuario("Joao");
		jose = new Usuario("José");
		maria = new Usuario("Maria");
	}

	@Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
        		.lance(maria,500.0)
        		.lance(joao,300.0)
        		.lance(jose,400.0)
        		.constroi();

        // executando a acao
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
		Leilao leilao = new CriadorDeLeilao()
				.para("playstation 3 novo")
				.lance(joao, 1000)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaiores() {
		Leilao leilao = new CriadorDeLeilao()
				.para("playstation 3 novo")
				.lance(joao, 100)
				.lance(maria, 200)
				.lance(joao, 300)
				.lance(maria, 400)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		assertEquals(3, tresMaiores.size());
		
		assertEquals(400, tresMaiores.get(0).getValor(), 0.000001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.000001);
		assertEquals(200, tresMaiores.get(2).getValor(), 0.000001);
	}
	
	@Test
	public void deveEntenderValoresAleatorios() {
		Leilao leilao = new CriadorDeLeilao()
				.para("playstation 3 novo")
				.lance(joao, 200)
				.lance(maria, 450)
				.lance(joao, 120)
				.lance(maria, 700)
				.lance(joao, 630)
				.lance(maria, 230)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(700, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderValoresDecrescentes() {
		Leilao leilao = new CriadorDeLeilao()
				.para("playstation 3 novo")
				.lance(joao, 400)
				.lance(maria, 300)
				.lance(joao, 200)
				.lance(maria, 100)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(100, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void verificaTresMaioresEmCincoLances() {
		Leilao leilao = new CriadorDeLeilao()
				.para("playstation 3 novo")
				.lance(joao, 400)
				.lance(maria, 300)
				.lance(joao, 200)
				.lance(maria, 100)
				.lance(maria, 50)
				.constroi();
		
		leiloeiro.avalia(leilao);

		assertEquals(3, leiloeiro.getTresMaiores().size());
		assertEquals(400, leiloeiro.getTresMaiores().get(0).getValor(), 0.00001);
		assertEquals(300, leiloeiro.getTresMaiores().get(1).getValor(), 0.00001);
		assertEquals(200, leiloeiro.getTresMaiores().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void verificaTresMaioresEmDoisLances() {
		Leilao leilao = new CriadorDeLeilao()
				.para("playstation 3 novo")
				.lance(joao, 400)
				.lance(maria, 300)
				.constroi();
		
		leiloeiro.avalia(leilao);

		assertEquals(2, leiloeiro.getTresMaiores().size());
		assertEquals(400, leiloeiro.getTresMaiores().get(0).getValor(), 0.00001);
		assertEquals(300, leiloeiro.getTresMaiores().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void verificaTresMaioresSemLances() {
		Leilao leilao = new Leilao("playstation 3 novo");
		
		leiloeiro.avalia(leilao);

		assertEquals(0, leiloeiro.getTresMaiores().size());
	}
}
