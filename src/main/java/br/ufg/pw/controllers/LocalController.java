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
	private MapBusca maps;
	public MapBusca getMaps() {
		return maps;
	}
	public void setMaps(MapBusca maps) {
		this.maps = maps;
	}

	
	/* Implementação da cache da lista de locais default */
	private List<Local> listLocal;
	
	public List<Local> getListLocal() {
		if ( listLocal == null ) {
			listLocal = localService.pesquisar(maps);
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
