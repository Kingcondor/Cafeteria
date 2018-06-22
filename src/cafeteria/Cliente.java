package cafeteria;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cliente extends Pessoa {
	private ArrayList<String> atendentes;
	private ArrayList<NotaFiscal> NF;
	
	public Cliente(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.atendentes = new ArrayList<String>();
		this.NF = new ArrayList<NotaFiscal>();
	}
	
	public void fazerPedido(Funcionario atendente, Cliente cliente, double valor) {
		NotaFiscal NF = new NotaFiscal(cliente);
		NF.setValor(valor);
		this.atendentes.add(atendente.getNome());
		NF.setCliente(cliente);
		NF.setHorarioDoPagamento(LocalDateTime.now());
		this.NF.add(NF);
	}

	public ArrayList<String> getAtendentes() {
		return atendentes;
	}

	public void setAtendentes(ArrayList<String> atendentes) {
		this.atendentes = atendentes;
	}

	public ArrayList<NotaFiscal> getNF() {
		return NF;
	}

	public void setNF(ArrayList<NotaFiscal> nF) {
		NF = nF;
	}
	public boolean equals(Cliente cliente) {
		if (this.nome.equals(cliente.getNome())) {
			return true;
		}
		return false;
	}
}
