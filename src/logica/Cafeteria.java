package logica;

public class Cafeteria {
	private String nome;
	private String endereco[] = new String[5];
	private String numeroTelefone;
	public Cafeteria(String nome, String endereco, String numeroTelefone) {
		this.nome = nome;
		this.setEndereco(endereco);
		this.numeroTelefone = numeroTelefone;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
}