package logica;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NotaFiscal {
	private LocalDateTime horarioDoPagamento;
	private Cliente cliente;
	private Atendente atendente;
	private ArrayList<String[]> produtosComprados;
	private double valorTotal;
	public NotaFiscal(Cliente cliente, Atendente atendente, ArrayList<String[]> produtosComprados) {
		this.horarioDoPagamento = LocalDateTime.now();
		this.cliente = cliente;
		this.atendente = atendente;
		this.produtosComprados = produtosComprados;
		for (int i = 0; i < this.produtosComprados.size(); i++) {
			this.valorTotal += Double.parseDouble(this.produtosComprados.get(i)[2]);
		}
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
	public Atendente getAtendente() {
		return atendente;
	}
	public void setCliente(Atendente atendente) {
		this.atendente = atendente;
	}
	public ArrayList<String[]> getProdutosComprados() {
		return produtosComprados;
	}
	public void setProdutosComprados(ArrayList<String[]> produtosComprados) {
		this.produtosComprados = produtosComprados;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String toString() {
		return "O cliente " + this.cliente.getNome() + " fez uma compra no valor de " + this.valorTotal + " no horario: " + this.horarioDoPagamento + ".";
	}
}