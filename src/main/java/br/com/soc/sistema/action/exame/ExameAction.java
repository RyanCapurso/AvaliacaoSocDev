package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameVo;

public class ExameAction extends Action {
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();
	private String mensagemErro = "";
	
	public String todos() {
		try {
			exames.addAll(business.obterExames());	
		}catch(Exception e) {
			return ERROR;
		}
	
		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		try {
			exames = business.filtrarExames(filtrar);
		}catch(Exception e) {
			return REDIRECT;
		}
		
		return SUCCESS;
	}
	
	public String novo() {
		if(exameVo.getNome() == null)
			return INPUT;
		try {
			business.salvarExame(exameVo);
		}catch(Exception e) {
			mensagemErro = e.getMessage();
			return INPUT;
		}
		
		return REDIRECT;
	}
	
	public String editar() {
		if(exameVo.getIdExame() == null)
			return ERROR;
		
		try {
			exameVo = business.obterExamePor(exameVo.getIdExame());			
		}catch(Exception e) {
			return ERROR;
		}
		
		return EDITAR;
	}
	
	public String salvarEdicao() {
		if(exameVo.getIdExame() == null) {
			return EDITAR;
		}
	
		try {
			business.editarExame(exameVo);
		}catch(Exception e) {
			mensagemErro = e.getMessage();
			return EDITAR;
		}
		
		return REDIRECT;
	}
	
	public String excluir() {
		if(exameVo.getIdExame() == null) {
			return ERROR;
		}
	
		try {
			business.excluirExame(exameVo);
		}catch(Exception e) {
			mensagemErro = e.getMessage();
			return todos();
		}
		
		return REDIRECT;
	}
	
	
	public List<OpcoesComboBuscarExames> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}
	
	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
}
