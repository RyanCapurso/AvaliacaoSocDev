package br.com.soc.sistema.dao.ExamesRealizados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameRealizadoVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class ExameRealizadoDao extends Dao {
	
	public List<ExameRealizadoVo> obterTodos() throws Exception {
		List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
		SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		 
		StringBuilder querry = new StringBuilder("select ef.id_exameRealizado, ef.id_funcionario, f.nm_funcionario, ef.id_exame, e.nm_exame, FORMATDATETIME(ef.dt_exame, 'dd/MM/yyyy') as dt_exame")
									.append(" from exame_funcionario ef")
									.append(" JOIN funcionario f on (ef.id_funcionario = f.id_funcionario)")
									.append(" JOIN exame e on (ef.id_exame = e.id_exame)")
									.append(" order by ef.id_funcionario");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(querry.toString());
			ResultSet rs = ps.executeQuery()){
		
			while(rs.next()) {
				ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
				exameRealizadoVo.setIdExameRealizado(rs.getString("id_exameRealizado"));
				exameRealizadoVo.setExameVo(new ExameVo(rs.getString("id_exame"), rs.getString("nm_exame")));
				exameRealizadoVo.setFuncionarioVo(new FuncionarioVo(rs.getString("id_funcionario"), rs.getString("nm_funcionario")));
				exameRealizadoVo.setDataExame(formatoBrasileiro.parse(rs.getString("dt_exame")));
				
				examesRealizados.add(exameRealizadoVo);
			}
		}catch(SQLException | ParseException e) {
			throw new Exception(e.getMessage());
		}
		
		return examesRealizados;
	}	

	public ExameRealizadoVo obterExameRealizadoById(String idExameRealizado) throws SQLException, ParseException {
        String query = "SELECT ef.id_exameRealizado, ef.id_funcionario, f.nm_funcionario, ef.id_exame, e.nm_exame, FORMATDATETIME(ef.dt_exame, 'dd/MM/yyyy') AS dt_exame " +
                       "FROM exame_funcionario ef " +
                       "JOIN funcionario f ON ef.id_funcionario = f.id_funcionario " +
                       "JOIN exame e ON ef.id_exame = e.id_exame " +
                       "WHERE ef.id_exameRealizado = ?";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, idExameRealizado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
                    SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");

                    exameRealizadoVo.setIdExameRealizado(rs.getString("id_exameRealizado"));
                    exameRealizadoVo.setExameVo(new ExameVo(rs.getString("id_exame"), rs.getString("nm_exame")));
                    exameRealizadoVo.setFuncionarioVo(new FuncionarioVo(rs.getString("id_funcionario"), rs.getString("nm_funcionario")));
                    exameRealizadoVo.setDataExame(formatoBrasileiro.parse(rs.getString("dt_exame")));

                    return exameRealizadoVo;
                } else {
                    return null; 
                }
            }
        } catch (SQLException | ParseException e) {
            throw new SQLException("Erro ao buscar exame realizado: " + e.getMessage(), e);
        }
    }

    public void updateExameRealizado(String idExameRealizado, String idExame, String idFuncionario, String dataExame) throws SQLException {
        String query = "UPDATE exame_funcionario SET id_exame = ?, id_funcionario = ?, dt_exame = ? WHERE id_exameRealizado = ?";
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, idExame);
            ps.setString(2, idFuncionario);
            ps.setString(3, dataExame);
            ps.setString(4, idExameRealizado);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum registro atualizado. Verifique se o exame realizado existe.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar exame realizado: " + e.getMessage(), e);
        }
    }

    public void insertExameRealizado(Integer codigoExame, Integer codigoFuncionario, String dataExame) throws SQLException {
        String query = "INSERT INTO exame_funcionario (id_funcionario, id_exame, dt_exame) VALUES (?, ?, ?)";
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query, new String[]{"id_exameRealizado"})) {

            ps.setInt(1, codigoFuncionario);
            ps.setInt(2, codigoExame);
            ps.setString(3, dataExame);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void deleteExameRealizado(String idExameRealizado) throws SQLException {
        String query = "DELETE FROM exame_funcionario WHERE id_exameRealizado = ?";
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, idExameRealizado);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void deleteAllByIdFuncionario(Integer codigoFuncionario) throws SQLException {
        String query = "DELETE FROM exame_funcionario WHERE id_funcionario = ?";
        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, codigoFuncionario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public List<ExameRealizadoVo> findByDates(String dataInicio, String dataFim) throws Exception {
        List<ExameRealizadoVo> examesRealizados = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT ef.id_exameRealizado, ef.id_funcionario, f.nm_funcionario, ef.id_exame, e.nm_exame, FORMATDATETIME(ef.dt_exame, 'dd/MM/yyyy') AS dt_exame")
            .append(" FROM exame_funcionario ef")
            .append(" JOIN funcionario f ON (ef.id_funcionario = f.id_funcionario)")
            .append(" JOIN exame e ON (ef.id_exame = e.id_exame)")
            .append(" WHERE dt_exame BETWEEN ? AND ?")
            .append(" ORDER BY ef.id_funcionario");

        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query.toString())) {

            ps.setString(1, dataInicio);
            ps.setString(2, dataFim);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
                    exameRealizadoVo.setIdExameRealizado(rs.getString("id_exameRealizado"));
                    exameRealizadoVo.setExameVo(new ExameVo(rs.getString("id_exame"), rs.getString("nm_exame")));
                    exameRealizadoVo.setFuncionarioVo(new FuncionarioVo(rs.getString("id_funcionario"), rs.getString("nm_funcionario")));
                    exameRealizadoVo.setDataExame(formatoBrasileiro.parse(rs.getString("dt_exame")));

                    examesRealizados.add(exameRealizadoVo);
                }
            }
        } catch (SQLException | ParseException e) {
            throw new Exception(e.getMessage());
        }

        return examesRealizados;
    }
}
