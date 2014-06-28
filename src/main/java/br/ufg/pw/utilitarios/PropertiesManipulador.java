package br.ufg.pw.utilitarios;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesManipulador {
	
	private static Properties props;
	
	private PropertiesManipulador() {
		//Agora é uma classe Singleton. Doidão nenhum consegue instanciar ela! MUAHAHAHAA
	}
	public static Properties getProp() {
		
		if (props == null) {
			
			try {
				FileInputStream file = new FileInputStream("./configs/bd-config.properties"); //Cria um objeto FileInputStream, passando como parâmetro do construtor o endereço do arquivo de configuração na máquina
				props.load(file); //Diz para o objeto de propriedade carregar o arquivo de FileInputStream
				
			} catch(Exception e) {
				throw new RuntimeException();
			}
			
		}
		
		return props; //retorna a propriedade
	}
	
}
