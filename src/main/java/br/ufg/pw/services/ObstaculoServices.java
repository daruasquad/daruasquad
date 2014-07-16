package br.ufg.pw.services;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ufg.pw.dao.ObstaculoDao;
import br.ufg.pw.entidades.Obstaculo;

/** Classe de teste feita para simular ações da classe LocalServices.java
 * @author brunokarpo
 * */

@ManagedBean
@ApplicationScoped
public class ObstaculoServices {	
	
	private ObstaculoDao obsDao = new ObstaculoDao();
	private List<String> obsDefault = this.getAllObstaculos();
	
	
	public List<String> getObsDefault() {
		return obsDefault;
	}

	public List<String> getAllObstaculos() {	 
		return obsDao.buscar();
	}
	

}
