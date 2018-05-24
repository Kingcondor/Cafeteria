package cafeteria;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class Gerente {
	private String nome;
	private String alerta;
	private String usuario;
	private String senha;
	private double salario;
	private ArrayList<Integer> diasDaSemana;
	private ArrayList <LocalDate> diasDeTrabalho;
	private ArrayList <Boolean> comparecimentos;
	public Gerente(String nome, double salario, ArrayList <Integer> diasDaSemana) {
		this.nome = nome;
		this.salario = salario;
		this.diasDaSemana = diasDaSemana;
		this.comparecimentos = new ArrayList <Boolean>();
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getSalario() {
		return this.salario;
	}
	public void setSalario(double valor) {
		this.salario = valor;
	}
	public void adicionarDiaDaSemana(int dia) {
		if (!diasDaSemana.contains(dia)) {
			this.diasDaSemana.add(dia);
		}
	}
	public void retirarDiaDaSemana(int dia) {
		if (diasDaSemana.contains(dia)) {
			this.diasDaSemana.remove(dia);
		}
	}
	public void avisarChegada() {
		DayOfWeek hoje = DayOfWeek.from(LocalDate.now());
		if (diasDaSemana.contains(hoje.getValue())) {
			this.diasDeTrabalho.add(LocalDate.now());
			this.comparecimentos.add(true);
		}
	}
	public void contratar(Cafeteria cafeteria, String nome, int tipo, double salario) {
		cafeteria.adicionarFuncionario(nome, tipo, salario);
	}
	public void demitir(Cafeteria cafeteria, String nome) {
		cafeteria.tirarFuncionario(nome);
	}
	public void aumentarSalario(Cafeteria cafeteria, String nome, double valor) {
		cafeteria.getFuncionario(nome).setSalario(cafeteria.getFuncionario(nome).getSalario() + valor);
	}
	public double pegarSalarioFuncionario(Cafeteria cafeteria, String nome) {
		return cafeteria.getFuncionario(nome).getSalario();
	}
	public int pegarTipoFuncionario(Cafeteria cafeteria, String nome) {
		return cafeteria.getFuncionario(nome).getTipo();
	}
	public void emitirAlerta(Cafeteria cafeteria, String nome, String alerta) {
		if (nome == "Todos") {
			for (int i=0; i<cafeteria.pegarNumeroFuncionarios(); i++) {
				cafeteria.getFuncionario(i).setAlerta(false);
				cafeteria.getFuncionario(i).receberAlertaGerente(alerta);
			}
		}else {
			cafeteria.getFuncionario(nome).receberAlertaGerente(alerta);
		}
	}
	public String getAlerta() {
		return alerta;
	}
	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}
	
}