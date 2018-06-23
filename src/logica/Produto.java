package logica;

public class Produto {
	private String nome;
	private double preco;
	private String descricao;
	private int quantidadeDoProduto;
	public Produto(String nome, double preco, String descricao, int quantidadeDoProduto) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidadeDoProduto = quantidadeDoProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidadeProduto() {
		return quantidadeDoProduto;
	}
	public void setQuantidadeProduto(int quantidadeDoProduto) {
		this.quantidadeDoProduto = quantidadeDoProduto;
	}
	public boolean subtrairQuantidadeProduto(int quantidade) {
		if (this.getQuantidadeProduto() - quantidade >= 0) {
			this.quantidadeDoProduto -= quantidade;
			return true;
		}
		return false;
	}
}