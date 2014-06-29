package br.ufg.pw.services;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.ufg.pw.dao.LocalDao;
import br.ufg.pw.entidades.Local;

@ManagedBean
@SessionScoped
public class LocalServices {
	
//	@ManagedProperty(value="#{localDao}")
//	private LocalDao localDao;
	
	LocalDao localDao = new LocalDao();
	
	/* Serviço responsável por chamar as classes responsáveis por inserir o local nas regras de negócio da aplicação
	 * Vai inserir o local recebido por parâmetro no Banco de Dados*/
	public void inserir(Local local) {
		localDao.inserir(local);
	}
	
	public List<Local> pesquisar(String busca) {
		//implementar Pesquisa
		return null;
	}
}
