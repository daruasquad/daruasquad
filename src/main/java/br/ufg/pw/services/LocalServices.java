package br.ufg.pw.services;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.ufg.pw.dao.LocalDao;
import br.ufg.pw.entidades.Local;
import br.ufg.pw.entidades.MapBusca;

/** 
 * Classe LocalServices.
 * Classe responsável por fornecer os serviços da aplicação que envolvem a entidade 'local'
 * do pacote br.uf.pw.entidades*/

@ManagedBean
@SessionScoped
public class LocalServices {
	
	@ManagedProperty(value="#{localDao}")
	private LocalDao localDao = new LocalDao();
	
	/** Manda o objeto para a classe DAO de local para persistência da informação 
	 * @author brunokarpo
	 * @param local informação a ser persistida
	 * @return Vazio*/
	public void inserir(Local local) {
		localDao.inserir(local);
	}
	
	/** Busca uma lista de locais que já estão persistidas na aplicação
	 * @author brunokarpo
	 * @param busca: String passada pelo usuário para especificar a busca
	 * @param cordenada: um objeto de local com apenas a latitude e longitude da posição atual do usuário
	 * @param zoom: nível atual de zoom do mapa do usuário para buscar apenas os locais próximos a este num determinado raio
	 * @return lista de locais que estão persistidas no Banco*/
	public List<Local> pesquisar(MapBusca maps) {
		return localDao.buscar(maps);
	}
	
//	/** Busca um local específico e as informações do mesmo dado o identificador do local
//	 * @author brunokarpo
//	 * @param id: inteiro que contém o identificador do local no Banco de Dados 
//	 * @return local: Local que está persistido no Banco de Dados da aplicação*/
//	public Local pesquisar(int id) {
//		return localDao.buscar(id);
//	}
	/** Exclui um local recebido como parâmetro 
	 * @author brunokarpo
	 * @param local Local que será excluído
	 * @return void*/
	public void excluir(Local local) {
		localDao.excluir(local);
	}
}
