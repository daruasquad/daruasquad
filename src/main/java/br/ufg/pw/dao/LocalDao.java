package br.ufg.pw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ufg.pw.entidades.Local;
import br.ufg.pw.utilitarios.JdbcUtil;

@ManagedBean
@ApplicationScoped
public class LocalDao {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void inserir(Local local) {
		//Implementar a inserção no Banco de Dados
	}
	
	public List<Local> buscar(String busca) {
		//Implementar busca no Banco de Dados
		return null;
	}
	
	public String buscar() {
		
		String resultado = null;
		
		try {	
			
			conn = JdbcUtil.createConnection();
			
			pstmt = conn.prepareStatement("select * from local");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado += rs.getString("nome");
				resultado += "\n";
			}
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			//fecha a conexão
			JdbcUtil.close(conn, pstmt);
		}
		
		return resultado;
	}
}
