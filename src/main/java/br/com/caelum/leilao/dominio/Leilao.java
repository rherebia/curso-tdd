package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if (lances.isEmpty() || podeDarLances(lance.getUsuario())) {
			lances.add(lance);
		}
	}
	
	private boolean podeDarLances(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario) && qtdeDeLancesDo(usuario) < 5;
	}

	private int qtdeDeLancesDo(Usuario usuario) {
		int total = 0;
		
		for(Lance lance : lances) {
			if (lance.getUsuario().equals(usuario)) {
				total++;
			}
		}
		
		return total;
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		Lance ultimoLance = ultimoLance(usuario);
		
		if (ultimoLance != null) {
			propoe(new Lance(usuario, ultimoLance.getValor() * 2));
		}
	}

	private Lance ultimoLance(Usuario usuario) {
		for(int i = lances.size() - 1; i >= 0; i--) {
			if (lances.get(i).getUsuario().equals(usuario)) {
				return lances.get(i);
			}
		}
		
		return null;
	}
}
