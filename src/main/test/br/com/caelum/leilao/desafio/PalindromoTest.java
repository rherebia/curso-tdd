package br.com.caelum.leilao.desafio;

import org.junit.Assert;
import org.junit.Test;

public class PalindromoTest {

	@Test
	public void verificaSeIdentificaPalindromos() {
		
		Palindromo palindromo = new Palindromo();
		
		Assert.assertTrue(palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos"));
		Assert.assertTrue(palindromo.ehPalindromo("Anotaram a data da maratona"));
	}
	
	@Test
    public void deveIdentificarSeNaoEhPalindromo() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
            "E preciso amar as pessoas como se nao houvesse amanha");
        Assert.assertFalse(resultado);
    }
}
