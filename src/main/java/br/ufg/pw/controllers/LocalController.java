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
import br.ufg.pw.services.LocalServices;

/** Classe controladora das views que tratam funcionalidades dos locais e obstáculos */

@ManagedBean (name="local")
@SessionScoped
public class LocalController {
	
	
	
	/* Propriedades de serviço */
	@ManagedProperty(value="#{localService}")
	private LocalServices localService;
	
	public void setLocalService(LocalServices localService) {
		this.localService = new LocalServices();
	}
	
	/* Propriedade de Local */
	private Local local;
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}

	@ManagedProperty(value="#{mapBusca}")
	/* Propriedade do mapa */
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
			mapBusca.markUsed();
		}
		return listLocal;
	}
	
	public void inserir() {
		localService.inserir(local);
	}
	
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
	
	public String pesquisar() {
		return "";
	}
	
	public List<Local> pesquisarId() {
		String idParam =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		int id = Integer.parseInt(idParam);
		ArrayList<Local> result = new ArrayList<Local>();
		result.add(localService.pesquisar(id));
		return result;
	}
	
}
