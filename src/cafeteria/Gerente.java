package cafeteria;

public class Gerente extends Funcionario {
	public Gerente (String nome, double salario) {
		super (nome, salario);
		this.setTipo("Gerente");;
	}
	public void contratar(String nome, double salario) {
		Controlador.adicionarFuncionario(nome, salario);
	}
	public void demitir(String nome) {
		Controlador.tirarFuncionario(nome);
	}
	public void mudarSalarioFuncionario(String nome, double valor) {
		Controlador.getFuncionario(nome).setSalario(valor);
	}
	public double pegarSalarioFuncionario(String nome) {
		return Controlador.getFuncionario(nome).getSalario();
	}
	public void emitirAlerta(String nome, String alerta) {
		if (nome.equalsIgnoreCase("Todos")) {
			for (int i=0; i<Controlador.pegarNumeroFuncionarios(); i++) {
				Controlador.getFuncionario(i).receberAlertaGerente(alerta);
			}
		}else {
			Controlador.getFuncionario(nome).receberAlertaGerente(alerta);
		}
	}
}