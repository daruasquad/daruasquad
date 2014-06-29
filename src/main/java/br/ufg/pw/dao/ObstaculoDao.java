package br.ufg.pw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufg.pw.entidades.Obstaculo;
import br.ufg.pw.utilitarios.JdbcUtil;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ObstaculoDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<Obstaculo> buscar(int idLocal) {
		
		List<Obstaculo> lista = new ArrayList<Obstaculo>();
		Obstaculo obs = null;
		
		try {
			
			conn = JdbcUtil.createConnection();
			
			pstmt = conn.prepareStatement("select * from tipo_obstaculo_em_local where id_local = ?");
			pstmt.setInt(1, idLocal);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				obs = new Obstaculo();
				
				obs.setNome( rs.getString("nome_tipo_obstaculo") );
				
				lista.add(obs);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return lista;
	}
	
	
	
}
