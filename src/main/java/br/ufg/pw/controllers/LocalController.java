package br.ufg.pw.controllers;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.ufg.pw.entidades.Local;
import br.ufg.pw.entidades.Obstaculo;
import br.ufg.pw.services.LocalServices;


@ManagedBean (name="local")
@RequestScoped
public class LocalController {
	
	private List<Local> searchResult = new ArrayList<Local> () ;
	private Map<String, String> parameters =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	private LocalServices localService = new LocalServices();
	private Local localForm = new Local(); //utilizado para inserção
	
	// Extrair para classe de mapa

	private Double defaultLat 	= -16.671128;
	private Double defaultLon 	= -49.282724;
	private Integer defaultZoom =  11;
	
	private String mapCenterLat = defaultLat.toString();
	private String mapCenterLon = defaultLon.toString();
	private String mapZoom = defaultZoom.toString();

	
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

	private String busca ;
	
	
	public List<Local> getSearchResult() {
		return searchResult;
	}
	
	public Local getLocalForm() {
		return localForm;
	}
	
	public void setBusca(String b){
		busca = b;
	}
	
	public String getBusca() {
		return busca;
	}
	
	/** Pesquisa genérica, usada para carregar a lista inicial e os resultados da busca 
	 * @return ArrayList<Local> lista de locais
	 * */
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
		
		
		searchResult = localService.pesquisar(busca, coordLocal, zoom);
		//genFakeResultMult() ;
		return searchResult;
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
		int busca = Integer.parseInt(parameters.get("id"));
		searchResult = new ArrayList<Local>();
		Local pesquisa = localService.pesquisar(busca);
		searchResult.add(pesquisa);
		//genFakeResultId();
		return searchResult;
	}
	
	/** Realiza a inserção de um local
	 * @param Local montado com os dados definidos pelo usuário no formulário
	 *  */
	public String inserir() {
		localService.inserir(localForm);
		return "";
	}
	
	
	private void genFakeResultId() {
		searchResult = new ArrayList<Local> ();
		Local l1 = new Local();
		l1.setId(19);
		l1.setNome("Local 1");
		l1.setLatitude(-16.671128);
		l1.setLongitude(-49.282724);
		l1.setBairro("Meu Bairro");
		l1.setCidade("Goiânia");
		l1.setEstado("Go");
		l1.setPais("Brèsil");
		l1.setUsuarioInsersor("user1");
		
		l1.setObstaculos(genFakeObstaculoResult());
		
		searchResult.add(l1);
		
	}
	
	private List<Obstaculo> genFakeObstaculoResult() {
		List<Obstaculo> obs = new ArrayList<Obstaculo>();
		Obstaculo rampa = new Obstaculo();
		rampa.setNome("Rampa");
		Obstaculo corrimao = new Obstaculo();
		corrimao.setNome("Corrimão");
		Obstaculo nope = new Obstaculo();
		nope.setNome("Nope");
		Obstaculo zip = new Obstaculo();
		zip.setNome("Zip");
		
		obs.add(rampa);
		obs.add(corrimao);
		obs.add(nope);
		obs.add(zip);
		
		return obs;
	}
	
	private void genFakeResultMult() {
		searchResult = new ArrayList<Local> ();
				
		Local l1 = new Local();
		Local l2 = new Local();
		Local l3 = new Local();
		Local l4 = new Local();
		
		
		l1.setId(12);
		l2.setId(13);
		l3.setId(14);
		l4.setId(15);
		
		
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
		
		l1.setObstaculos(genFakeObstaculoResult());
		l2.setObstaculos(genFakeObstaculoResult());
		l3.setObstaculos(genFakeObstaculoResult());
		l4.setObstaculos(genFakeObstaculoResult());
		
		
		searchResult.add(l1);
		searchResult.add(l2);
		searchResult.add(l3);
		searchResult.add(l4);
		
	}
	
}
