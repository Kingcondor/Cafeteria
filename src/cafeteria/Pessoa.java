package cafeteria;

public abstract class Pessoa {
	protected String nome;
	protected String senha;
	protected boolean verificacaoDePrimeiraVez;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return this.senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setVerificacaoDePrimeiraVez(boolean verificacaoDePrimeiraVez) {
		this.verificacaoDePrimeiraVez = verificacaoDePrimeiraVez;
	}
	
}