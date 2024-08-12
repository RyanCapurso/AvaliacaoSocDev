package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusinnes {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	
	public FuncionarioBusinnes() {
		this.dao = new FuncionarioDao();
	}

	public List<FuncionarioVo> obterFuncionarios() {
		try {
			return dao.obterTodosFuncionarios();
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}

	public FuncionarioVo obterFuncionarioPorId(String idFuncionario) {
		try {
			Integer codigo = Integer.parseInt(idFuncionario);
			return dao.obterById(codigo);
		}catch(NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public void editarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("O Nome nao pode estar em branco");
			if(funcionarioVo.getIdFuncionario().isEmpty())
				throw new IllegalArgumentException("O Id nao pode estar em branco");
			
			dao.updateFuncionario(funcionarioVo);
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public void excluirFuncionario(String idFuncionario) {
		try {
			Integer codigo = Integer.parseInt(idFuncionario);
			dao.deleteFuncionario(codigo);
		}catch(Exception e){
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
		
	}

	public void salvarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("O Nome nao pode estar em branco");
			
			dao.insertFuncionario(funcionarioVo);
		}catch(Exception e) {
			throw new BusinessException("Não foi possivel realizar a inclusão do registro");
		}
		
	}

	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filtro) throws Exception {
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		
		switch (filtro.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filtro.getValorBusca());
					funcionarios.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}catch(Exception e){
					throw new BusinessException(e.getMessage()); 
				}
			break;

			case NOME:
				funcionarios.addAll(dao.findAllByNome(filtro.getValorBusca()));
			break;
		}
		
		return funcionarios;
	}
}
