package br.ufg.pw.controllers.fakeServices;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ufg.pw.entidades.Local;
import br.ufg.pw.entidades.Obstaculo;

/** Classe de teste feita para simular ações da classe LocalServices.java
 * @author brunokarpo
 * */

@ManagedBean
@ApplicationScoped
public class FakeLocalServices {	
	
	private List<Local> searchResult = new ArrayList<Local> () ;
	
	public void genFakeResultId() {
		searchResult = new ArrayList<Local> ();
		Local l1 = new Local();
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
	
	public List<Local> genFakeResultMult() {
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
		
		l1.setObstaculos(genFakeObstaculoResult());
		l2.setObstaculos(genFakeObstaculoResult());
		l3.setObstaculos(genFakeObstaculoResult());
		l4.setObstaculos(genFakeObstaculoResult());
		
		
		searchResult.add(l1);
		searchResult.add(l2);
		searchResult.add(l3);
		searchResult.add(l4);
		
		return searchResult;
	}
}
