package logica;

public class Atendente extends Funcionario {
	public Atendente(String nome, double salario) {
		super(nome, salario);
	}
	public void comprarProduto(String nome, double preco, String descricao, int quantidade) {
		Controlador.adicionarProduto(nome, preco, descricao, quantidade);
	}
	public void tirarProdutoEstoque(String nome) {
		Controlador.tirarProduto(nome);
	}
	public void invalidarPedido(String horario) {
		for (int i = 0; i < Controlador.getNotasFiscais().size(); i++) {
			if (Controlador.getNotaFiscal(i).getAtendente().equals(this) && Controlador.getNotaFiscal(i).getHorarioDoPagamento().toString().equals(horario)) {
				Controlador.tirarNotaFiscal(i);
				break;
			}
		}
	}
}