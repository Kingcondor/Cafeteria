package logica;

public abstract class Pessoa {
	protected String nome;
	protected String senha;
	protected String rg;
	protected String cpf;
	protected String endereco[] = new String[5];
	protected String numeroTelefone;
	protected boolean verificacaoDePrimeiraVez = true;
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
	public String getRg() {
		return this.rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String[] getEndereco() {
		return this.endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco.split("/[(?: - )(?:, )]/");
	}
	public String getNumeroTelefone() {
		return this.numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		if (numeroTelefone.charAt(0) == '(' && numeroTelefone.charAt(3) == ')' && (numeroTelefone.charAt(9) == '-' || numeroTelefone.charAt(10) == '-')) {
			this.numeroTelefone = numeroTelefone;
		}
	}
	public boolean getVerificacaoDePrimeiraVez() {
		return this.verificacaoDePrimeiraVez;
	}
	public void setVerificacaoDePrimeiraVez(boolean verificacaoDePrimeiraVez) {
		this.verificacaoDePrimeiraVez = verificacaoDePrimeiraVez;
	}
	public boolean equals(Pessoa pessoa) {
		if (this.nome.equals(pessoa.getNome()) && this.rg.equals(pessoa.getRg()) && this.cpf.equals(pessoa.getCpf())) {
			return true;
		}
		return false;
	}
}