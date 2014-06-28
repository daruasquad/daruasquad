package br.ufg.pw.utilitarios;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesManipulador {
	
	//Local do arquivo de configuração
	private static final String FILE_NAME = "./configs/bd-config.properties";
	
	private static Properties props;
	
	private PropertiesManipulador() {
		//Agora é uma classe Singleton. Doidão nenhum consegue instanciar ela! MUAHAHAHAA
	}
	
	public static Properties getProp() {
		
		if (props == null) {
			try {
				
				props = new Properties(); //Instancia um objeto Properties
				FileInputStream file = new FileInputStream( FILE_NAME ); //Cria um objeto FileInputStream, passando como parâmetro do construtor o endereço do arquivo de configuração na máquina
				props.load( file ); //Diz para o objeto de propriedade carregar o arquivo de FileInputStream
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return props; //retorna a propriedade
	}
	
}
