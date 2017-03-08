package br.com.caelum.leilao.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import br.com.caelum.leilao.dominio.Leilao;

public class TemUmLanceMatcher extends BaseMatcher<Leilao> {
	
	private Leilao leilao;
	
	public TemUmLanceMatcher(Leilao leilao) {
		this.leilao = leilao;
	}

	@Override
	public boolean matches(Object obj) {
		if (obj instanceof Leilao) {
			if (leilao.equals(obj)) {
				if (!leilao.getLances().isEmpty()) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("possui ao menos um lance");
	}

}
