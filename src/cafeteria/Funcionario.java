package cafeteria;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Funcionario {
	private String usuario;
	private String senha;
	private String nome;
	private int tipo;
	private double salario;
	private ArrayList<Boolean> comparecimentos;
	private ArrayList<LocalDateTime> diasDeTrabalho;
	private boolean alerta;
	
	public Funcionario(String nome, int tipo, double salario) {
		this.alerta = false;
		this.tipo = tipo;
		this.nome = nome;
		this.salario = salario;
		this.comparecimentos = new ArrayList<Boolean>();
		this.diasDeTrabalho = new ArrayList<LocalDateTime>();
	}
	
	public void avisarChegada() {
		this.diasDeTrabalho.add(LocalDateTime.now());
		this.comparecimentos.add(true);
	}
	
	public void receberAlertaGerente(String alerta) {
		if (this.alerta == false) {
			System.out.println(alerta);
			this.alerta = true;
		}
	}
	
	public void invalidarPedido(ArrayList<NotasFiscais> NFS, int k) {
		NFS.remove(k);
	}
	
	public int pegarQuantidadeEstoque(Produto produto) {	
		int res = produto.getQuantidadeDoProduto();
		return res;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isAlerta() {
		return alerta;
	}

	public void setAlerta(boolean alerta) {
		this.alerta = alerta;
	}
	
	
}
