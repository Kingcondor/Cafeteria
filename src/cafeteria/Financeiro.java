package cafeteria;

public class Financeiro extends Funcionario {
	int numeroVendas;
	public Financeiro(String nome, double salario) {
		super(nome, salario);
		numeroVendas = 0;
		this.setTipo("Financeiro");
	}
	public void comprarProduto(String nome, double preco, String descricao, int quantidade) {
		Controlador.adicionarProduto(nome, preco, descricao, quantidade);
	}
	public void tirarProdutoEstoque(String nome) {
		Controlador.tirarProduto(nome);
	}
	public void invalidarPedido(NotaFiscal notaFiscal) {
		Controlador.tirarNotaFiscal(notaFiscal);
	}
}