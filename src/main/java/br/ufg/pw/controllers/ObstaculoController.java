package br.ufg.pw.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufg.pw.services.ObstaculoServices;

@ManagedBean (name="obstaculoBean")
@RequestScoped
public class ObstaculoController {

	private ObstaculoServices obsServ = new ObstaculoServices();
	public void setObsServ(ObstaculoServices obsServ) {
		this.obsServ = obsServ;
	}

	private String obs = null;
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	private List<String> listaObs;
	public List<String> getListaObs() {
		if(listaObs == null) {
			listaObs = obsServ.getAllObstaculos();
		}
		return listaObs;
	}

}
