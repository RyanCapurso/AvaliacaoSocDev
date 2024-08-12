package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class ExameBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameDao dao;
	
	public ExameBusiness() {
		this.dao = new ExameDao();
	}
	
	public List<ExameVo> obterExames() {
		try {
			return dao.findAllExames();
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}	
	
	public void salvarExame(ExameVo exameVo) {
		try {
			if(exameVo.getNome().isEmpty())
				throw new BusinessException("O nome é Obrigatório");
			
			dao.insertExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Não foi possivel realizar a inclusão do registro");
		}
		
	}	
	
	public List<ExameVo> filtrarExames(ExameFilter filter){
		List<ExameVo> exames = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exames.add(dao.findById(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}catch(Exception e) {
					throw new BusinessException(e.getMessage());
				}
			break;

			case NOME:
				try {
					exames.addAll(dao.findAllByNome(filter.getValorBusca()));
				}catch(Exception e) {
					throw new BusinessException(e.getMessage());
				}
				
			break;
		}
		
		return exames;
	}
	
	public ExameVo obterExamePor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findById(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	public void editarExame(ExameVo exameVo) {
		try {
			if(exameVo.getNome().isEmpty())
				throw new BusinessException("O nome é Obrigatório");
			
			dao.updateExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Não foi possivel realizar a inclusão do registro");
		}
	}

	public void excluirExame(ExameVo exameVo) {
		
		try {
			if(exameVo.getIdExame().isEmpty()) {
				throw new BusinessException("O Id é Obrigatório");
			}
			dao.deleteExame(Integer.parseInt(exameVo.getIdExame()));
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}catch(Exception e) {
			throw new BusinessException("Exclusão Cancelada, o Exame possui funcionário vinculado!");
		}
	}
}
