package cafeteria;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NotasFiscais {
	private double valor;
	private LocalDateTime horarioDoPagamento;
	private Cliente cliente;
	private ArrayList<Produto> produtosComprados;
	
	public NotasFiscais(Cliente cliente) {
		double valor = 0;
		for (int k = 0; k < this.produtosComprados.size() ; k++) {
			valor = this.produtosComprados.get(k).getPreco() + valor;
		}
		this.cliente = cliente;
		this.horarioDoPagamento = LocalDateTime.now();
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDateTime getHorarioDoPagamento() {
		return horarioDoPagamento;
	}

	public void setHorarioDoPagamento(LocalDateTime horarioDoPagamento) {
		this.horarioDoPagamento = horarioDoPagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String toString() {
		return "O cliente "+ this.cliente.getNome()+ " fez uma compra no valor de "+ this.valor+ " no horario: "+ this.horarioDoPagamento + ".";
	}
}
