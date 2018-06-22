package cafeteria;
import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
	private double salario;
	private int numeroAlertas;
	private ArrayList <String> alertas;
	private ArrayList <LocalDate> diasDeTrabalho;
	private String tipo;
	
	public Funcionario(String nome, double salario) {
		this.nome = nome;
		this.salario = salario;
		this.verificacaoDePrimeiraVez = true;
		this.numeroAlertas = 0;
		this.tipo = "Normal";
		this.alertas = new ArrayList<>();
		this.diasDeTrabalho = new ArrayList<>();
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getNumeroAlertas() {
		return numeroAlertas;
	}
	public int pegarQuantidadeEstoque(Produto produto) {
		return Controlador.listarProdutos().get(Controlador.buscarProduto(produto.getNome())).getQuantidadeDoProduto();
	}
	public void avisarChegada() {
		if (diasDeTrabalho.contains(LocalDate.now())) {
			this.diasDeTrabalho.add(LocalDate.now());
		}
	}
	public void receberAlertaGerente(String alerta) {
		this.numeroAlertas++;
		this.alertas.add(alerta);
	}
	public String pegarAlerta(int alerta) {
		if (this.numeroAlertas > 0) {
			return this.alertas.get(alerta);
		}
		return "";
	}
	public void apagarAlerta(int alerta) {
		if (this.numeroAlertas > 0) {
			this.alertas.remove(alerta);
		}
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ArrayList<String> getAlertas() {
		return alertas;
	}
	public void setAlertas(ArrayList<String> alertas) {
		this.alertas = alertas;
	}
	public ArrayList<LocalDate> getDiasDeTrabalho() {
		return diasDeTrabalho;
	}
	public void setDiasDeTrabalho(ArrayList<LocalDate> diasDeTrabalho) {
		this.diasDeTrabalho = diasDeTrabalho;
	}
	public void setNumeroAlertas(int numeroAlertas) {
		this.numeroAlertas = numeroAlertas;
	}
	
	
}