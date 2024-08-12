package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusinnes;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action{
	private FuncionarioBusinnes business = new FuncionarioBusinnes();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private String mensagemErro = "";
	
	public String novo() {
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		try {
			business.salvarFuncionario(funcionarioVo);
		}catch(Exception e) {
			return INPUT;
		}
		
		return REDIRECT;
	}
	
	public String todos() {
		try {
			funcionarios.addAll(business.obterFuncionarios());
		}catch(Exception e) {
			mensagemErro = e.getMessage();
			return ERROR;
		}
	
		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo() || filtrar.getValorBusca().isEmpty()) {
			return REDIRECT;
		}
		
		try {
			funcionarios = business.filtrarFuncionarios(filtrar);
		}catch(Exception e) {
			return REDIRECT;
		}
		
		return SUCCESS;
	}
	
	public String editar() {
		if(funcionarioVo.getIdFuncionario() == null)
			return ERROR;
		
		try {
			funcionarioVo = business.obterFuncionarioPorId(funcionarioVo.getIdFuncionario());			
		}catch(Exception e) {
			mensagemErro = e.getMessage();
			return ERROR;
		}
		
		return EDITAR;
	}
	
	public String salvarEdicao() {
		try {
			business.editarFuncionario(funcionarioVo);
		}catch(Exception e) {
			return EDITAR;
		}
		return REDIRECT;
	}
	
	public String excluir() {
		if(funcionarioVo.getIdFuncionario() == null) {
			return REDIRECT;
		}
		
		try {
			business.excluirFuncionario(funcionarioVo.getIdFuncionario());
		}catch(Exception e) {
			return REDIRECT;
		}
		
		return REDIRECT;
	}
	
	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
}
