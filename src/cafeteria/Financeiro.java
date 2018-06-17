package cafeteria;

public class Financeiro extends Funcionario {
	int numeroVendas;
	public Financeiro(String nome, double salario) {
		super(nome, salario);
		numeroVendas = 0;
	}
	public void comprarProduto(String nome, double preco, String descricao, int quantidade) {
		Controlador.adicionarProduto(nome, preco, descricao, quantidade);
	}
	public void triarProdutoEstoque(String nome, double preco, String descricao, int quantidade) {
		Controlador.tirarProduto(nome);
	}
	public void invalidarPedido(NotaFiscal notaFiscal) {
		Controlador.tirarNotaFiscal(notaFiscal);
	}
}