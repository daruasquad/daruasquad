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
import br.ufg.pw.entidades.MapBusca;
import br.ufg.pw.utilitarios.JdbcUtil;
/** 
 * Classe de persistência das informações pertinentes à entidade Local */
@ManagedBean
@ApplicationScoped
public class LocalDao {
	
	private Connection conn; //Conexão com o Banco de Dados
	private PreparedStatement pstmt; //responsável pelas strings de consulta
	private ResultSet rs; //result set da aplicação.
	
	/** Método de inserção na aplicação de um local recebido do usuário 
	 * @author brunokarpo
	 * @param local: Objeto local passado pelo usuário do sistema 
	 * @return void: Não retorna nada 
	 * @exception SqlException: erro na inserção do obstáculo
	 * @exception SqlException: erro geral na inserção do local*/
	public void inserir(Local local) {
		
		try {
			
			conn = JdbcUtil.createConnection(); //abre uma conexão com o Banco de Dados
			
			conn.setAutoCommit(false); //Cria uma transação no Banco de Dados
			
			//Prepara o Statement para inserção
			pstmt = conn.prepareStatement("insert into local (lat, lon, cidade, estado, pais, nome, login_usuario_insercao, bairro, geoposicao, endereco) values (?, ?, ?, ?, ?, ?, ?, ?, ST_GeographyFromText( ? ), ?)");
			
			pstmt.setDouble(1, local.getLatitude());
			pstmt.setDouble(2, local.getLongitude());
			pstmt.setString(3, local.getCidade());
			pstmt.setString(4, local.getEstado());
			pstmt.setString(5, local.getPais());
			pstmt.setString(6, local.getNome());
			pstmt.setString(7, local.getUsuarioInsersor());
			pstmt.setString(8, local.getBairro());
			pstmt.setString(9, "SRID=4326;POINT ( " + String.valueOf( local.getLatitude() ) + " " + String.valueOf( local.getLongitude() ) + " )");
			pstmt.setString(10, local.getEndereco() );
			
			pstmt.executeUpdate(); //Insere a informação do local dentro da transação
			
			final int idLocal = buscarLocalEspecifico(local, conn, pstmt); //busca o ID do novo local no BD para inserir os tipos de obstáculos desse local;
			
			// se o id for diferente de -1 chama o método de ObstaculoDao para inserir os obstáculos no local
			if(idLocal != -1) {
				
				ObstaculoDao obDao = new ObstaculoDao();
				obDao.inserir(local.getObstaculos(), idLocal, conn, pstmt);
				
			}
			
			conn.commit(); //Se deu tudo certo, commita no Banco e encerra
			
		} catch(Exception e) {
			// Se der erro
			try {
				// Dá rollback e mostra o StackTrace do erro
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
	
	/** Esse método retorna um array de locais que atendem os parâmetros da busca 
	 * @author brunokarpo
	 * @param busca: String que o usuário deseja buscar
	 * @param cordenada: Cordenada alvo da pesquisa
	 * @param zoom: nível de Zoom do Mapa do usuário para definir o raio da busca
	 * @return lista: um array de locais que atenderam a pesquisa 
	 * @exception Exception: se der erro printa o StackTrace para tratarmos */
	public List<Local> buscar(MapBusca maps) {
		
		String nova = maps.getBusca().replace(" ", "%"); //Modifica cada espaço da string do usuário por um sinal %
		
		List<Local> lista = new ArrayList<Local>();
		Local local = null;
		
		try {
			
			conn = JdbcUtil.createConnection();
			
			pstmt = conn.prepareStatement("select  distinct on (lo.id) id, lo.lat, lo.lon, lo.cidade, lo.estado, lo.pais, lo.nome, "
											+ " lo.login_usuario_insercao, lo.bairro, lo.id, toel.nome_tipo_obstaculo, muto.nome_modalidade "
											+ " from local lo " 
											+ " join tipo_obstaculo_em_local toel on lo.id = toel.id_local "
											+ " join modalidade_usa_tipo_obstaculo muto on toel.nome_tipo_obstaculo = muto.nome_tipo_obstaculo " 
											+ " join esporte_tem_modalidade etm on muto.nome_modalidade = etm.nome_modalidade "
											+ " where (lo.cidade ilike '%" + nova + "%'"
											+ " or lo.estado ilike '%" + nova  + "%'"
											+ " or lo.pais ilike '%" + nova  + "%'"
											+ " or lo.nome ilike '%" + nova  + "%'"
											+ " or lo.bairro ilike '%" + nova  + "%'"
											+ " or toel.nome_tipo_obstaculo ilike '%" + nova  + "%'"
											+ " or muto.nome_modalidade ilike '%" + nova  + "%'"
											+ " or etm.nome_esporte ilike '%" + nova  + "%'"
											+ ") "
											+ " and ST_DWithin(lo.geoposicao, ST_GeographyFromText(' " + funcaoSTGeography( maps ) + " '), "
											+ funcaoStGeographyZoom(maps.getZoom()) 
											+  ") order by lo.id");
			
			rs = pstmt.executeQuery();
			
			// Enquanto tivermos resultados no ResultSet, monta um objeto e adiciona na lista de locais
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
	
//	/** Encontra um local específico através do ID passado pela aplicação na pesquisa
//	 * @author brunokarpo
//	 * @param id inteiro identificado no Banco de Dados
//	 * @return local Local específico buscado pelo sistema */
//	public Local buscar(MapBusca maps) {
//		
//		Local local = null;
//		
//		try {
//			conn = JdbcUtil.createConnection();
//			
//			pstmt = conn.prepareStatement("select * from local where id = ?");
//			pstmt.setInt(1, );
//			
//			rs = pstmt.executeQuery();
//			
//			rs.next();
//			
//			local = objetoLocal(rs);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(conn, pstmt, rs);
//		}
//		
//		return local;
//	}
	
	/** Método responsável pela exclusão de um local no Banco de Dados 
	 * @author brunokarpo
	 * @param local: Referencia do local que será excluido
	 * @return void*/
	public void excluir(Local local) {
		
		try {
			conn = JdbcUtil.createConnection();
			
			conn.setAutoCommit(false);
			
			//Excluir os tipos de obstáculos desse local
			ObstaculoDao obsDao = new ObstaculoDao();
			obsDao.excluir(local.getId(), conn, pstmt);
			
			pstmt = conn.prepareStatement("delete from local where id = ?");
			pstmt.setInt( 1, local.getId() );
			
			pstmt.executeUpdate();
			
			conn.commit();
			
		} catch(Exception e) {
			
			try {
				//Se der problema dá rollback na transação
				conn.rollback();
				e.printStackTrace();
				
			} catch (Exception e1) {
				//Ai não dá para fazer nada
			}
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		
	}
	
	/** Método criado para buscar o ID quando estamos inserindo um objeto recebido como parâmetro no método inserir 
	 * @author brunokarpo
	 * @param local: informações do local que o usuário está inserindo
	 * @param conn : conexão já aberta com o banco de dados da transação da inserção 
	 * @param pstmt : Statement de pesquisa da conexão já aberta no Banco de Dados 
	 * @return id : Identificador do lugar recentemente inserido */
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
	
	/** Esse método faz a montagem de um objeto Local à partir de um ponteiro de um result set recebido
	 * @author brunokarpo
	 * @param rs : Ponteiro apontando para um atual resultado de um result set
	 * @return local : uma instância de objeto local */
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
			local.setId( rs.getInt("id" ));
			
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
	
	/** Método que auxilia na passagem de pontos geográficos para as Function do Postgis 
	 * @author brunokarpo
	 * @param local: uma cordenada de um local
	 * @return String contendo o SRID (formato da cordenada) mais uma string do Ponto geografico alvo */
	private String funcaoSTGeography (MapBusca maps) {
		return "SRID=4326;POINT ( " + String.valueOf( maps.getLatitude() ) + " " + String.valueOf( maps.getLongitude() ) + " )";
	}
	
	/** Método que retorna o raio de busca das pesquisas na aplicação em metros 
	 * @author brunokarpo
	 * @param zoom inteiro com o nível de zoom do mapa do Google
	 * @return String contendo o valor do raio da pesquisa em metros */
	private String funcaoStGeographyZoom(int zoom) {
		String raio = null;
		
		if(zoom == 11) raio = "35000";
		else if (zoom == 12) raio = "17500";
		else if (zoom == 13) raio = "8750";
		else if (zoom == 14) raio = "4400";
		else if (zoom == 15) raio = "2200";
		else if (zoom == 17) raio = "550";
		else if (zoom == 18) raio = "225";
		else /*(zoom == 16)*/ raio = "1100";
		
		return raio;
	}
	
}
