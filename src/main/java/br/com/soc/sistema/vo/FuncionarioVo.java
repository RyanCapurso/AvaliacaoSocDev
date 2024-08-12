package br.com.soc.sistema.vo;

public class FuncionarioVo {
	private String idFuncionario;
	private String nome;	
	
	public FuncionarioVo() {}
		
	public FuncionarioVo(String idFuncionario, String nome) {
		this.idFuncionario = idFuncionario;
		this.nome = nome;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "FuncionarioVo [idFuncionario=" + idFuncionario + ", nome=" + nome + "]";
	}
}
