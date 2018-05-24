package cafeteria;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cliente {
	private String usario;
	private String senha;
	private String nome;
	private ArrayList<String> atendentes;
	private ArrayList<NotasFiscais> NF;
	
	public Cliente(String nome) {
		this.nome = nome;
		this.atendentes = new ArrayList<String>();
		this.NF = new ArrayList<NotasFiscais>();
	}
	
	public void fazerPedido(Funcionario atendente, Cliente cliente, double valor) {
		NotasFiscais NF = new NotasFiscais(cliente);
		NF.setValor(valor);
		this.atendentes.add(atendente.getNome());
		NF.setCliente(cliente);
		NF.setHorarioDoPagamento(LocalDateTime.now());
		this.NF.add(NF);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<String> getAtendentes() {
		return atendentes;
	}

	public void setAtendentes(ArrayList<String> atendentes) {
		this.atendentes = atendentes;
	}

	public ArrayList<NotasFiscais> getNF() {
		return NF;
	}

	public void setNF(ArrayList<NotasFiscais> nF) {
		NF = nF;
	}
	
	
	
	
}
