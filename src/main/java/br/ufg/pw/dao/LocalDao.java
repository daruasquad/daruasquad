package br.ufg.pw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ufg.pw.entidades.Local;
import br.ufg.pw.entidades.Obstaculo;
import br.ufg.pw.utilitarios.JdbcUtil;

@ManagedBean
@ApplicationScoped
public class LocalDao {
	
	private Connection conn; //Conexão com o Banco de Dados
	private PreparedStatement pstmt; //responsável pelas strings de consulta
	private ResultSet rs; //result set da aplicação.
	
	
	/* Método por inserir locais no Banco de dados da aplicação */
	public void inserir(Local local) {
		
		try {
			
			conn = JdbcUtil.createConnection(); //abre uma conexão com o Banco de Dados
			
			conn.setAutoCommit(false); //Cria uma transação no Banco de Dados
			
			//Prepara o Statement para inserção
			pstmt = conn.prepareStatement("insert into local (lat, lon, cidade, estado, pais, nome, login_usuario_insercao, bairro) values (?, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setDouble(1, local.getLatitude());
			pstmt.setDouble(2, local.getLongitude());
			pstmt.setString(3, local.getCidade());
			pstmt.setString(4, local.getEstado());
			pstmt.setString(5, local.getPais());
			pstmt.setString(6, local.getNome());
			pstmt.setString(7, local.getUsuarioInsersor());
			pstmt.setString(8, local.getBairro());
			
			//Insere a informação do local dentro da transação
			pstmt.executeUpdate();
			
			//busca o ID da inserção no BD para inserir os tipos de obstáculos desse local
			final int idLocal = buscarLocalEspecifico(local, conn, pstmt);
			
			// se o id for diferente de -1
			if(idLocal != -1) {
				for(Obstaculo obs : local.getObstaculos()) {
					pstmt = conn.prepareStatement("insert into tipo_obstaculo_em_local (id_local, nome_tipo_obstaculo) values (?, ?)");
					pstmt.setInt(1, idLocal);
					pstmt.setString(2, obs.getNome());
					pstmt.executeUpdate();
				}
			} else {
				new SQLException();
			}
			
			//Se deu tudo certo, commita e encerra
			conn.commit();
			
		} catch(Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// Ai não dá para fazer mais nada
				e1.printStackTrace();
			}
			
		} finally {
			//Fecha a conexão com o Banco de Dados
			JdbcUtil.close(conn, pstmt);
		}
	}
	
	/* Esse método retorna um array de locais que atendem os parâmetros da busca*/
	 
	public List<Local> buscar(String busca) {
		
		List<Local> lista = new ArrayList<Local>();
		Local local = null;
		
		try {
			
			conn = JdbcUtil.createConnection();
			
			pstmt = conn.prepareStatement("select * from local lo join tipo_obstaculo_em_local toel on lo.id = toel.id_local where lo.cidade like %" + busca + 
					"%or lo.estado like %" + busca 
					+ "%or lo.pais like %" + busca 
					+ "%or lo.nome like %" + busca 
					+ "%or lo.bairro like %" + busca 
					+ "%or toel.nome_tipo_obstaculo like %" + busca + "%");
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				local =	objetoLocal(rs);
				lista.add(local);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		
		return lista;
	}
	
	
	/* Método criado para buscar o ID quando estamos inserindo um objeto recebido como parâmetro no método inserir*/
	private int buscarLocalEspecifico(Local local, Connection conn, PreparedStatement pstmt) {
		
		int id = 0;
		
		try {
			pstmt = conn.prepareStatement("select id from local where lat = ? and lon = ? and cidade = ? and estado = ? and pais = ? and nome = ? and login_usuario_insercao = ? and bairro = ?");
			pstmt.setDouble(1, local.getLatitude());
			pstmt.setDouble(2, local.getLongitude());
			pstmt.setString(3, local.getCidade());
			pstmt.setString(4, local.getEstado());
			pstmt.setString(5, local.getPais());
			pstmt.setString(6, local.getNome());
			pstmt.setString(7, local.getUsuarioInsersor());
			pstmt.setString(8, local.getBairro());
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			id = rs.getInt("id");
			
		} catch(Exception e) {
			id = -1;
		}
		
		return id;
	}
	
	/* Esse método faz a montagem de um objeto Local à partir de um ponteiro de um result set recebido */
	protected Local objetoLocal(ResultSet rs) {
		
		Local local = new Local(); 
		
		try {
			// Monta o objeto com as informações básicas
			local.setLatitude( rs.getDouble("lat") );
			local.setLongitude( rs.getDouble("lon") );
			local.setCidade( rs.getString("cidade") );
			local.setEstado( rs.getString("estado") );
			local.setPais( rs.getString("pais") );
			local.setNome( rs.getString("nome") );
			local.setUsuarioInsersor( rs.getString("login_usuario_insercao") );
			local.setBairro( rs.getString("bairro") );
			
			// Instancia um objeto de ObstaculoDao para montar o array de obstaculos
			ObstaculoDao obs = new ObstaculoDao();
			
			//O local recebe o array de obstáculo do método buscar de ObstaculoDao que recebe como parâmetro o valor da id do local no resultset
			local.setObstaculos( obs.buscar( rs.getInt("id") ) );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return local;
	}
	
}
