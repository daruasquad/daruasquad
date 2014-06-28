package br.ufg.pw.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ufg.pw.entidades.Local;

@ManagedBean
@ApplicationScoped
public class LocalDao {
	
	public void inserir(Local local) {
		//Implementar a inserção no Banco de Dados
	}
	
	public List<Local> buscar(String busca) {
		//Implementar busca no Banco de Dados
		return null;
	}
}
