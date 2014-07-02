package br.ufg.pw.controllers;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.ufg.pw.controllers.fakeServices.FakeLocalServices;
import br.ufg.pw.entidades.Local;
import br.ufg.pw.entidades.Obstaculo;
import br.ufg.pw.services.LocalServices;


@ManagedBean (name="local")
@RequestScoped
public class LocalController {
	
	/* Instanciação da propriedade de serviços solicitados por essa página */
	@ManagedProperty(value="#{fakeLocalServices}")
	private FakeLocalServices fakeLocalServices;
	
	public FakeLocalServices getFakeLocalServices() {
		return fakeLocalServices;
	}
	public void setFakeLocalServices(FakeLocalServices fakeLocalServices) {
		this.fakeLocalServices = fakeLocalServices;
	}


	/* Propriedade aluno com seus métodos Getter e Setter */
	private Local local;
	
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}

	
	//Para fazer "cache" e ganhar desempenho.
	private List<Local> listLocal;
//	private Map<String, String> parameters =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	
	
	public List<Local> getlistLocal() {
		if (listLocal == null) {
			listLocal = pesquisar();
		}
		return listLocal;
	}
	
	
	public List<Local> pesquisar() {
//		LocalServices localService = new LocalServices();
		
//		String busca = parameters.get("busca");
		//Double lat = Double.parseDouble(parameters.get("lat"));
		//Double lon = Double.parseDouble(parameters.get("lon"));
		
//		System.out.println(busca);
//		listLocal = localService.pesquisar(busca/*, lat, lon*/);

		return fakeLocalServices.genFakeResultMult() ;
	}
	
//	public List<Local> pesquisarId(Integer id) {
//		LocalServices localService = new LocalServices();		
//		String busca = parameters.get("id");
//		//listLocal = localService.pesquisarId(busca/*, lat, lon*/);
//		fakeLocalServices.genFakeResultId();
//		return listLocal;
//	}
	
}
