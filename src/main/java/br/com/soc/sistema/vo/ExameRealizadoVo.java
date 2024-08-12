package br.com.soc.sistema.vo;

import java.util.Date;

public class ExameRealizadoVo {	
	private String idExameRealizado;
	private ExameVo exameVo = new ExameVo();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	private Date dataExame;
	
	public ExameVo getExameVo() {
		return exameVo;
	}
	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}
	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}
	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}
	public Date getDataExame() {
		return dataExame;
	}
	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}
	public String getIdExameRealizado() {
		return idExameRealizado;
	}
	public void setIdExameRealizado(String idExameRealizado) {
		this.idExameRealizado = idExameRealizado;
	}
}