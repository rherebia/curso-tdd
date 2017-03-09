package br.com.caelum.leilao.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class TemUmLanceMatcher extends TypeSafeMatcher<Leilao> {
	
	private Lance lance;
	
	public TemUmLanceMatcher(Lance lance) {
		this.lance = lance;
	}

	@Override
	public boolean matchesSafely(Leilao leilao) {
		return leilao.getLances().contains(lance);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("leilao com lance " + lance.getValor());
	}

}
