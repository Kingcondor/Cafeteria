package logica;

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
	public static int getTipo() {
		return tipo;
	}
	public static void login(String senha) {
		if (Controlador.getFuncionario(nome).getSenha().equals(senha)) {
			if (Controlador.getFuncionario(nome) instanceof Gerente) {
				tipo = 1;
			}else if (Controlador.getFuncionario(nome) instanceof Funcionario && !(Controlador.getFuncionario(nome) instanceof FuncionarioFinanceiro)) {
				tipo = 2;
			}else if (Controlador.getFuncionario(nome) instanceof FuncionarioFinanceiro) {
				tipo = 3;
			}else if (Controlador.getCliente(nome) != null) {
				tipo = 4;
			}
		}
		tipo = 0;
	}
}