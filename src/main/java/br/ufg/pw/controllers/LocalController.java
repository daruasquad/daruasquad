package br.ufg.pw.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ufg.pw.entidades.Local;
import br.ufg.pw.entidades.MapBusca;
import br.ufg.pw.entidades.Obstaculo;
import br.ufg.pw.services.LocalServices;

/** Classe controladora das views que tratam funcionalidades dos locais e obstáculos */

@ManagedBean (name="local")
@SessionScoped
public class LocalController {
	
	
	
	/** Propriedades de serviço */
	@ManagedProperty(value="#{localService}")
	private LocalServices localService;
	public void setLocalService(LocalServices localService) {
		this.localService = new LocalServices();
	}
	
	/** 
	 * Propriedade de Local
	 * Guarda uma instância de local para uso em operações genéricas
	 * */
	private Local local;
	public Local getLocal() {
<<<<<<< HEAD
		return local == null ? local = new Local() : local;
=======
		if (local == null) {
			local = new Local();
		}
		return local;
>>>>>>> c89e46bd3369eb9a677e283a6021b2c106eb2c5c
	}
	public void setLocal(Local local) {
		this.local = local;
	}

	@ManagedProperty(value="#{mapBusca}")
	/** Propriedade do mapa */
	private MapBusca mapBusca;
	public MapBusca getMapBusca() {
		if (mapBusca == null) {
			mapBusca = new MapBusca();
		}
		return mapBusca;
	}
	public void setMapBusca(MapBusca maps) {
		this.mapBusca = maps;
	}

	
	/* Implementação da cache da lista de locais default */
	private List<Local> listLocal;
	
	public List<Local> getListLocal() {
		MapBusca mapBusca = getMapBusca();
		if ( listLocal == null || !mapBusca.isUsed()) {
			listLocal = localService.pesquisar(mapBusca);
			mapBusca.markUsed(); // essa busca ja foi feita
		}
		return listLocal;
	}
	
<<<<<<< HEAD
	/* Aquela implementação feia de obstaculos */
	private String[] listObs;
	public String[] getListObs() {
		return listObs;
	}
	public void setListObs(String[] listObs) {
		this.listObs = listObs;
	}
	
	public void inserir() {
		
		Obstaculo obs;
		List<Obstaculo> temp = new ArrayList<Obstaculo>();
		
		for(String nome : listObs) {
			obs = new Obstaculo();
			obs.setNome(nome);
			temp.add(obs);
		}
		local.setObstaculos(temp);
		
=======
	public String inserir() {
		System.out.print(local);;
		local.setUsuarioInsersor("user1");
>>>>>>> c89e46bd3369eb9a677e283a6021b2c106eb2c5c
		localService.inserir(local);
		return "index.xhtml?faces-redirect=true";
	}
	
	/** Exclusão de local
	 * @bug 
	 * @return String
	 */
	public String excluir() {
		localService.excluir(local);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			return "/?faces-redirect=true"; //bugs
		}
		catch (Exception e) {
			// do nothing;
		}
		return "/?faces-redirect=true";
	}
	
	/** Usado para binding no formulário de busca. */
	public String pesquisar() {
		return "";
	}
	
	/** Pesquisa especificamente um local. Usado quando se tem um "id" sendo passado como parâmetro
	 * @return List<Local> um listLocal de um elemento, para ser impresso na tela
	 */
	public List<Local> pesquisarId() {
		String idParam =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		int id = Integer.parseInt(idParam);
		ArrayList<Local> result = new ArrayList<Local>();
		result.add(localService.pesquisar(id));
		return result;
	}
	
}
