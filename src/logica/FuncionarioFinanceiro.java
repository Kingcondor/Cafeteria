package logica;

public class FuncionarioFinanceiro extends Funcionario {
	int numeroVendas;
	public FuncionarioFinanceiro(String nome, double salario) {
		super(nome, salario);
		numeroVendas = 0;
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