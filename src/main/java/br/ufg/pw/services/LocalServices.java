package br.ufg.pw.services;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufg.pw.entidades.Local;

@ManagedBean
@SessionScoped
public class LocalServices {
	
	public void inserir(Local local) {
		//implemantar inserção
	}
	
	public List<Local> pesquisar(String busca) {
		//implementar Pesquisa
		return null;
	}
}
