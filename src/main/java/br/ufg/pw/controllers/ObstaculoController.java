package br.ufg.pw.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufg.pw.entidades.Obstaculo;
import br.ufg.pw.services.ObstaculoServices;

@ManagedBean (name="obstaculo")
@RequestScoped
public class ObstaculoController {

	private ObstaculoServices obsServ = new ObstaculoServices();
	public void setObsServ(ObstaculoServices obsServ) {
		this.obsServ = obsServ;
	}
	
	private Obstaculo obs;
	public Obstaculo getObs() {
		return obs;
	}
	public void setObs(Obstaculo obs) {
		this.obs = obs;
	}
	
	private List<Obstaculo> listaObs;
	public List<Obstaculo> getListaObs() {
		if(listaObs == null) {
			listaObs = obsServ.getAllObstaculos();
		}
		return listaObs;
	}
	
}
