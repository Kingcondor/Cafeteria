package gui;
import logica.Controlador;
import logica.Funcionario;
import logica.Atendente;
import logica.Gerente;

public class Login {
	private static String nome;
	private static int tipo;
	public static String getNome() {
		return nome;
	}
	public static int setNome(String nomeLogin) {
		if (Controlador.getFuncionario(nomeLogin) != null) {
			nome = nomeLogin;
			return 1;
		}else if (Controlador.getCliente(nomeLogin) != null) {
			nome = nomeLogin;
			return 1;
		}
		return 0;
	}
	public static void mudarNome(String nomeLogin) {
		nome = nomeLogin;
	}
	public static int getTipo() {
		return tipo;
	}
	public static void login(String senha) {
		if (Controlador.getFuncionario(nome) != null) {
			if (Controlador.getFuncionario(nome).getSenha().equals(senha)) {
				if (Controlador.getFuncionario(nome) instanceof Gerente) {
					tipo = 1;
				}else if (Controlador.getFuncionario(nome) instanceof Funcionario && !(Controlador.getFuncionario(nome) instanceof Atendente)) {
					tipo = 2;
				}else if (Controlador.getFuncionario(nome) instanceof Atendente) {
					tipo = 3;
				}
			}
		}else if (Controlador.getCliente(nome) != null && Controlador.getCliente(nome).getSenha().equals(senha)) {
				tipo = 4;
		}else {
			tipo = 0;
		}
	}
	public static boolean verficarSenha(String senha) {
		if (Controlador.getFuncionario(nome).getSenha().equals(senha)) {
			return true;
		}
		return false;
	}
	public static void sair() {
		nome = null;
		tipo = 0;
	}
}