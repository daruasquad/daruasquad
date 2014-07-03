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
import br.ufg.pw.services.ObstaculoServices;


@ManagedBean (name="local")
@RequestScoped
public class LocalController {
	
	/* Instanciação da propriedade de serviços solicitados por essa página */
	@ManagedProperty(value="#{fakeLocalServices}")
	private FakeLocalServices fakeLocalServices;
	
	@ManagedProperty(value="#{obsService}")
	public ObstaculoServices obsService = new ObstaculoServices();
	
	/** Serviço relacionado aos locais registrados na aplicação */
	private LocalServices localService = new LocalServices();
	
	/** Propriedades do mapa apresentado ao usuário */
	//@ManagedProperty(value="#{map}")
	private MapController map;
	
	/** Propriedade aluno para opearções da controller*/
	private Local localForm = new Local();
	
	
	
	/** Busca textual realizada no front-end */
	private String busca;
	
	/**Obstáculos selecionados no formulário de busca */
	private String[] obsForm = {};
	
	


	public Double defaultLat 	= -16.671128;
	public Double defaultLon 	= -49.282724;
	public Integer defaultZoom  =  11;

	private String mapCenterLat = defaultLat.toString();
	private String mapCenterLon = defaultLon.toString();
	private String mapZoom = defaultZoom.toString();
	
	private Map<String, String> parameters =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	
	public FakeLocalServices getFakeLocalServices() {
		return fakeLocalServices;
	}

	public void setFakeLocalServices(FakeLocalServices fakeLocalServices) {
		this.fakeLocalServices = fakeLocalServices;
	}

//	public void setMap() {
//		map = new MapController();
//	}
//	
//	public MapController getMap() {
//		return map;
//	}
	
	public Local getLocalForm() {
		return localForm;
	}
	public void setLocalForm(Local local) {
		this.localForm = local;
	}

	
	//Para fazer "cache" e ganhar desempenho.
	private List<Local> listLocal;
	
	public List<Local> getlistLocal() {
		if (listLocal == null) {
			listLocal = pesquisar();
		}
		return listLocal;
	}
	

	
	/** Realiza uma pesquisa utilizando os dados passados no campo de busca e o centro e zoom no mapa do usuário */
	
	public List<Local> pesquisar() {
		Double lat = mapCenterLat == "" ? defaultLat :  Double.parseDouble(mapCenterLat);
		Double lon = mapCenterLon == "" ? defaultLon :  Double.parseDouble(mapCenterLon);
		Integer zoom = mapZoom == "" ? defaultZoom : Integer.parseInt(mapZoom);

		if (busca == null) {
			busca = "";
		}


		Local coordLocal = new Local();
		coordLocal.setLatitude(lat);
		coordLocal.setLongitude(lon);
		try {
			//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}
		catch (Exception e){
			//do nothing
		}
		return  localService.pesquisar(busca, coordLocal, zoom);
		
		
	}
	
	
	public String pesquisarForm() {
		pesquisar();
		return "";
	}
	
	/** 
	 * Pesquisa por um local específico, utilizando seu id
	 * @param Integer id
	 * @return ArrayList<Local> lista de locais
	 * */
	public List<Local> pesquisarId() {
		Integer id = Integer.parseInt(parameters.get("id"));
		if (id == null) {
			FacesContext.getCurrentInstance().getExternalContext();
		}
		localForm.setId(id);
		
		ArrayList<Local> list = new ArrayList<Local>();
		list.add(localService.pesquisar(id));
		return list;
	}
	
	

	/** Realiza a inserção de um local
	 * @param Local montado com os dados definidos pelo usuário no formulário
	 *  */
	public String inserir() {
		
		List<Obstaculo> obsList = new ArrayList<Obstaculo>();
		
		for (int i = 0; i < obsForm.length; i++) {
			Obstaculo obs = new Obstaculo();
			obs.setNome(obsForm[i]);
			obsList.add(obs);
		}
		
		localForm.setObstaculos(obsList);
		
		localForm.setUsuarioInsersor("user1");
		localService.inserir(localForm);
		return "";
	}

	public String excluir() {
		localService.excluir(localForm);
		return "index.xhtml?faces-redirect=true";
	}
	
	/** Seta a string para a busca textual 
	 * @param String s 	String sendo buscada
	 * @return void
	 * */
	public void setBusca(String s){
		busca = s;
	}

	/**
	 * Retorna a string buscada, para manter na tela do usuário o que ele buscou anteriormente
	 * @return String 	A String da ultima busca
	 */
	public String getBusca() {
		return busca;
	}
	
	
	
	public String getMapZoom() {
		return mapZoom;
	}

	public void setMapZoom(String mapZoom) {
		this.mapZoom = mapZoom;
	}

	/*Formulario de busca*/
	public String getMapCenterLat() {
		return mapCenterLat;
	}
	

	public void setMapCenterLat(String mapCenterLat) {
		this.mapCenterLat = mapCenterLat;
	}

	public String getMapCenterLon() {
		return mapCenterLon;
	}

	public void setMapCenterLon(String mapCenterLon) {
		this.mapCenterLon = mapCenterLon;
	}

	
	public ObstaculoServices getObsService() {
		return obsService;
	}

	public void setObsService(ObstaculoServices obsService) {
		this.obsService = new ObstaculoServices();
	}
	
	public String[] getObsForm() {
		return obsForm;
	}

	public void setObsForm(String[] obsForm) {
		this.obsForm = obsForm;
	}

}
