package br.com.soc.sistema.dao.exames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;

public class ExameDao extends Dao {
	
	public void insertExame(ExameVo exameVo) throws SQLException{
		StringBuilder query = new StringBuilder("INSERT INTO exame (nm_exame) values (?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, exameVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public void updateExame(ExameVo exameVo) throws SQLException{
		StringBuilder query = new StringBuilder("update exame set nm_exame = ? ")
										.append("where id_exame = ?");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			ps.setString(1, exameVo.getNome());
			ps.setString(2, exameVo.getIdExame());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public void deleteExame(Integer codigo) throws SQLException { 
		StringBuilder query = new StringBuilder("delete from exame where id_exame = ?");
		
		try (Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString())){
			ps.setInt(1, codigo);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<ExameVo> findAllExames() throws SQLException{
		StringBuilder query = new StringBuilder("SELECT id_exame id, nm_exame nome FROM exame");
		List<ExameVo> exames = new ArrayList<>();
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExameVo vo =  null;
			
			while (rs.next()) {
				vo = new ExameVo();
				vo.setIdExame(rs.getString("id"));
				vo.setNome(rs.getString("nome"));	
				
				exames.add(vo);
			}
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return exames;
	}
	
	public List<ExameVo> findAllByNome(String nome) throws Exception{
		StringBuilder query = new StringBuilder("SELECT id_exame id, nm_exame nome FROM exame ")
								.append("WHERE lower(nm_exame) like lower(?)");
		
		List<ExameVo> exames = new ArrayList<>();
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setIdExame(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					
					exames.add(vo);
				}
			}
		}catch (SQLException e) {
			throw new Exception(e.getMessage());
		}		
		return exames;
	}
	
	public ExameVo findById(Integer idExame) throws SQLException{
		StringBuilder query = new StringBuilder("SELECT id_exame id, nm_exame nome FROM exame ")
								.append("WHERE id_exame = ?");
		
		ExameVo vo =  new ExameVo();
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			ps.setInt(1, idExame);
			
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					vo.setIdExame(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
				}
			}
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}		
		return vo;
	}
}