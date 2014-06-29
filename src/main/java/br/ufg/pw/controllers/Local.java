package br.ufg.pw.controllers;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.ufg.pw.entidades.Local;
import br.ufg.pw.services.LocalServices;


@ManagedBean
@RequestScoped
public class Local {
	
	private List<Local> searchResult = new ArrayList<Local> () ;
	private Map<String, String> parameters =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	
	public List<Local> getSearchResult() {
		return searchResult;
	}
	
	public List<Local> pesquisar() {
		LocalServices localService = new LocalServices();
		
		String busca = parameters.get("busca");
		//Double lat = Double.parseDouble(parameters.get("lat"));
		//Double lon = Double.parseDouble(parameters.get("lon"));
		
		System.out.println(busca);
		searchResult = localService.pesquisar(busca/*, lat, lon*/);
		genFakeResultMult() ;
		return searchResult;
	}
	
	public List<Local> pesquisarId(Integer id) {
		LocalServices localService = new LocalServices();		
		String busca = parameters.get("id");
		///searchResult = localService.pesquisarId(busca/*, lat, lon*/);
		genFakeResultId();
		return searchResult;
	}
	
	
	
	private void genFakeResultId() {
		searchResult = new ArrayList<Local> ();
		Local l1 = new Local();
		l1.setNome("Local 1");
		l1.setLatitude(-16.671128);
		l1.setLongitude(-49.282724);
		searchResult.add(l1);
	}
	
	private void genFakeResultMult() {
		searchResult = new ArrayList<Local> ();
				
		Local l1 = new Local();
		Local l2 = new Local();
		Local l3 = new Local();
		Local l4 = new Local();
		
		l1.setNome("Local 1");
		l2.setNome("Local 2");
		l3.setNome("Local 3 tem um nome até grande demais para ser o nome de algum lugar real");
		l4.setNome("Local 4");
		
		
		l1.setLatitude(-16.671128);
		l2.setLatitude(-16.669813);
		l3.setLatitude(-16.679186);
		l4.setLatitude(-16.699000);
		
		l1.setLongitude(-49.282724);
		l2.setLongitude(-49.253627);
		l3.setLongitude(-49.224960);
		l4.setLongitude(-49.244443);
		
		
		searchResult.add(l1);
		searchResult.add(l2);
		searchResult.add(l3);
		searchResult.add(l4);
		
	}
	
}
