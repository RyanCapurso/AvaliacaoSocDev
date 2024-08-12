package br.com.soc.sistema.business;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.soc.sistema.dao.ExamesRealizados.ExameRealizadoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoBusiness {
    private ExameRealizadoDao dao;
    private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";

    public ExameRealizadoBusiness() {
        this.dao = new ExameRealizadoDao();
    }

    public List<ExameRealizadoVo> obterExamesRealizados() {
        try {
            return dao.obterTodos();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public void salvarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
        try {
            Integer codigoExame = Integer.parseInt(exameRealizadoVo.getExameVo().getIdExame());
            Integer codigoFuncionario = Integer.parseInt(exameRealizadoVo.getFuncionarioVo().getIdFuncionario());
            SimpleDateFormat formatoPadrao = new SimpleDateFormat("yyyy-MM-dd");
            String dataExame = formatoPadrao.format(exameRealizadoVo.getDataExame());

            dao.insertExameRealizado(codigoExame, codigoFuncionario, dataExame);
        } catch (NumberFormatException e) {
            throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
        } catch (Exception e) {
            throw new BusinessException("Não foi possível realizar o cadastro. Verifique se este cadastro já existe");
        }
    }

    public ExameRealizadoVo obterExameRealizadoPorId(String idExameRealizado) throws Exception {
        try {
            ExameRealizadoVo exameRealizadoVo = dao.obterExameRealizadoById(idExameRealizado);
            if (exameRealizadoVo == null) {
                throw new BusinessException("Exame realizado não encontrado para o ID fornecido.");
            }
            return exameRealizadoVo;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar exame realizado: " + e.getMessage());
        }
    }

    public void editarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
        try {
            if (exameRealizadoVo.getIdExameRealizado() == null) {
                throw new IllegalArgumentException("O Id do Exame Realizado não pode ser nulo");
            }
            if (exameRealizadoVo.getFuncionarioVo().getIdFuncionario().isEmpty()) {
                throw new IllegalArgumentException("O Id do Funcionário não pode estar em branco");
            }
            if (exameRealizadoVo.getExameVo().getIdExame() == null || exameRealizadoVo.getExameVo().getIdExame().isEmpty()) {
                throw new IllegalArgumentException("O Id do Exame não pode estar em branco");
            }

            SimpleDateFormat formatoPadrao = new SimpleDateFormat("yyyy-MM-dd");
            String dataExame = formatoPadrao.format(exameRealizadoVo.getDataExame());

            dao.updateExameRealizado(
                exameRealizadoVo.getIdExameRealizado(),
                exameRealizadoVo.getExameVo().getIdExame(),
                exameRealizadoVo.getFuncionarioVo().getIdFuncionario(),
                dataExame
            );
        } catch (Exception e) {
            throw new BusinessException("Não foi possível editar o exame realizado. " + e.getMessage());
        }
    }

    public void excluirExameRealizado(ExameRealizadoVo exameRealizado) {
        try {
            dao.deleteExameRealizado(exameRealizado.getIdExameRealizado());
        } catch (NumberFormatException e) {
            throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public List<ExameRealizadoVo> filtrarPorDatas(String dataInicio, String dataFim) {
        try {
            return dao.findByDates(dataInicio, dataFim);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
