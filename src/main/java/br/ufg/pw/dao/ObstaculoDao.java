package br.ufg.pw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufg.pw.utilitarios.JdbcUtil;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/** Classe responsável pela persistência das informações pertinentes aos Obstáculos presente nos locais */
@ManagedBean
@ApplicationScoped
public class ObstaculoDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/** Método que lista os obstáculos presentes em um local
	 * @author brunokarpo
	 * @param idLocal inteiro que identifica o local no Banco
	 * @return lista de objetos obstáculos pertinentes àquele local */
	protected List<String> buscar(int idLocal) {

		List<String> lista = new ArrayList<String>();
		String obs = null;

		try {

			conn = JdbcUtil.createConnection();

			pstmt = conn.prepareStatement("select * from tipo_obstaculo_em_local where id_local = ?");
			pstmt.setInt(1, idLocal);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				obs = rs.getString("nome_tipo_obstaculo") ;

				lista.add(obs);
			}


		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return lista;
	}


	/** Método que lista os obstáculos cadastrados
	 * @author danielmelo
	 * @return lista de objetos obstáculos cadastrados */
	public List<String> buscar() {

		List<String> lista = new ArrayList<String>();
		String obs = null;

		try {

			conn = JdbcUtil.createConnection();

			pstmt = conn.prepareStatement("select * from tipo_obstaculo order by nome");

			rs = pstmt.executeQuery();

			while(rs.next()) {

				obs = rs.getString("nome");

				lista.add(obs);
			}


		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return lista;
	}

	/** Método responsável por receber uma lista de obstáculos e inserir no banco usando uma transação corrente
	 * @author brunokarpo
	 * @param list : lista de obstáculos a serem inseridos
	 * @param idLocal : identificador do local no Banco de Dados
	 * @param conn : Conexão já aberta com o banco de dados dentro de uma transação
	 * @param pstmt : Statement já aberto da transação da conexão
	 * @return void
	 * @exception Retorna uma exceção a ser tratada*/
	protected boolean inserir(List<String> list, int idLocal, Connection conn, PreparedStatement pstmt) {

		try {

			for(String obs : list) { //Insere cada obstáculo do array no Banco de Dados referenciando o local
				pstmt = conn.prepareStatement("insert into tipo_obstaculo_em_local (id_local, nome_tipo_obstaculo) values (?, ?)");
				pstmt.setInt(1, idLocal);
				pstmt.setString(2, obs);
				pstmt.executeUpdate();
			}

			return true;

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/** Método responsável por excluir uma lista de obstáculos referente a um local que será excluído
	 * @author brunokarpo
	 * @param id Identificador do local no Banco de Dados que será excluído
	 * @param conn : Conexão já aberta com o banco de dados dentro de uma transação
	 * @param pstmt : Statement já aberto da transação da conexão
	 * @return void
	 * */
	protected void excluir(int idLocal, Connection conn, PreparedStatement pstmt) {

		try {

			pstmt = conn.prepareStatement("delete from tipo_obstaculo_em_local where id_local = ?");
			pstmt.setInt(1, idLocal);

			pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
