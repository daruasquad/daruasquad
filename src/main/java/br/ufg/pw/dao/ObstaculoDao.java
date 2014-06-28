package br.ufg.pw.dao;

import java.util.List;

import br.ufg.pw.entidades.Obstaculo;
import br.ufg.pw.entidades.Local;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ObstaculoDao {
	
	public List<Obstaculo> buscar(Local local) {
		//Implementar a busca dos obstáculos
		//Recebe como parâmetro o local que os obstáculos daquele local... (testar, pois talvez seja desnecessário)
		return null;
	}
	
}
