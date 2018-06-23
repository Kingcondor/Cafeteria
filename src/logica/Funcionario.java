package logica;
import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
	private double salario;
	private int numeroAlertas;
	private ArrayList<String> alertas;
	private ArrayList<LocalDate> diasDeTrabalho;
	public Funcionario(String nome, double salario) {
		this.nome = nome;
		this.salario = salario;
		this.numeroAlertas = 0;
		this.alertas = new ArrayList<String>();
		this.diasDeTrabalho = new ArrayList<LocalDate>();
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getNumeroAlertas() {
		return this.numeroAlertas;
	}
	public void setNumeroAlertas(int numeroAlertas) {
		this.numeroAlertas = numeroAlertas;
	}
	public ArrayList<String> getAlertas() {
		return this.alertas;
	}
	public void setAlertas(ArrayList<String> alertas) {
		this.alertas = alertas;
	}
	public ArrayList<LocalDate> getDiasDeTrabalho() {
		return this.diasDeTrabalho;
	}
	public void setDiasDeTrabalho(ArrayList<LocalDate> diasDeTrabalho) {
		this.diasDeTrabalho = diasDeTrabalho;
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
	public int pegarQuantidadeEstoque(Produto produto) {
		return Controlador.getProduto(produto.getNome()).getQuantidadeProduto();
	}
	public void avisarChegada() {
		if (this.diasDeTrabalho.size() > 0) {
			if (this.diasDeTrabalho.get(this.diasDeTrabalho.size()-1).equals(LocalDate.now())) {
				this.diasDeTrabalho.add(LocalDate.now());
			}
		}else {
			this.diasDeTrabalho.add(LocalDate.now());
		}
	}
}