package br.com.soc.sistema.vo;

public class ExameVo {
	private String idExame;
	private String nome;	
	
	public ExameVo() {}
		
	public ExameVo(String idExame, String nome) {
		this.idExame = idExame;
		this.nome = nome;
	}

	public String getIdExame() {
		return idExame;
	}
	public void setIdExame(String idExame) {
		this.idExame = idExame;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "ExameVo [idExame=" + idExame + ", nome=" + nome + "]";
	}
}
