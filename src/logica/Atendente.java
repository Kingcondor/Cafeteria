package logica;

public class Atendente extends Funcionario {
	int numeroVendas;
	public Atendente(String nome, double salario) {
		super(nome, salario);
		numeroVendas = 0;
	}
	public void comprarProduto(String nome, double preco, String descricao, int quantidade) {
		Controlador.adicionarProduto(nome, preco, descricao, quantidade);
	}
	public void tirarProdutoEstoque(String nome) {
		Controlador.tirarProduto(nome);
	}
	public void invalidarPedido(String horario) {
		for (int i = 0; i < Controlador.getNotasFiscais().size(); i++) {
			if (Controlador.getNotaFiscalPosicao(i).getAtendente().equals(this) && Controlador.getNotaFiscalPosicao(i).getHorarioDoPagamento().toString().equals(horario)) {
				Controlador.tirarNotaFiscal(i);
				break;
			}
		}
	}
}