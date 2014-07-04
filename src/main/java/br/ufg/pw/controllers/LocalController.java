package br.ufg.pw.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.ufg.pw.entidades.Local;
import br.ufg.pw.entidades.MapBusca;
import br.ufg.pw.services.LocalServices;

/** Classe controladora das views que tratam funcionalidades dos locais e obstáculos */

@ManagedBean (name="local")
@RequestScoped
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

	/* Propriedade do mapa */
	private MapBusca mapBusca = new MapBusca();;
	public MapBusca getMapBusca() {
		return mapBusca;
	}
	public void setMapBusca(MapBusca maps) {
		this.mapBusca = maps;
	}

	
	/* Implementação da cache da lista de locais default */
	private List<Local> listLocal;
	
	public List<Local> getListLocal() {
		if ( listLocal == null ) {
			listLocal = localService.pesquisar(mapBusca);
		}
		return listLocal;
	}
	
	public void inserir() {
		localService.inserir(local);
	}
	
	public void excluir() {
		localService.excluir(local);
	}
	
}
