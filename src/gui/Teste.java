package gui;
import java.util.Scanner;
import logica.Cafeteria;
import logica.Controlador;
import logica.Login;

public class Teste {
	public static void main(String[] args) {
		Cafeteria cafeteria = new Cafeteria("Cafeteria do Pé Sujo", "Rua das Inundações, 666 - Centro - Lugar Nenhum - Acre", "80701-1999");
		Controlador.adicionarGerente("Maria Graças da Silva", 10000.00);
		Controlador.adicionarGerente("Marlon Gilberto Farias", 20000.00);
		Controlador.adicionarFuncionario("Gonsalves Roberto Giovanne", 1080.00);
		Controlador.adicionarFuncionario("Ana Clara da Silva", 900.00);
		Controlador.adicionarFuncionario("João Guilherme de Lima Júnior", 680.00);
		Controlador.adicionarFuncionario("João dos Montes Silva", 800.00);
		System.out.println("Bem vindo à " + cafeteria.getNome() + " que fica em " + cafeteria.getEndereco().toString() + ", se quiser ligar, disque " + cafeteria.getNumeroTelefone());
		Scanner scanner = new Scanner(System.in);
		String resposta;
		//Primeiro acesso
		if (Controlador.getVerificacaoDePrimeiraVez()) {
			while (true) {
				System.out.println("Bem-vindo ao aplicativo de Cafeterias");
				System.out.println("Como esta é sua primeira vez no aplicativo, por favor, digite o Nome da cafeteria, o endereço e número de contato do estabelecimento, respectivamente:");
				String nome = scanner.nextLine();
				String endereco = scanner.nextLine();
				String numeroTelefone = scanner.nextLine();
				System.out.println("Nome: " + nome);
				System.out.println("Endereço: " + endereco);
				System.out.println("Número de telefone: "+ numeroTelefone);
				System.out.println("Estes dados estão corretos? (Estes dados podem ser mudados posteriormente)" );
				System.out.println("A) Sim");
				System.out.println("B) Não");
				System.out.println("Escolha: ");
				resposta = scanner.nextLine();
				if (resposta.equalsIgnoreCase("a")) {
					Controlador.adicionarCafeteria(nome, endereco, numeroTelefone);
					System.out.println("Cafeteria criada com sucesso.");
					System.out.println("Agora, por favor, insira seu nome para criar uma conta de gerente:");
					nome = scanner.nextLine();
					System.out.println("E uma senha de acesso:");
					String senha = scanner.nextLine();
					System.out.println("E o salário:");
					Controlador.adicionarGerente(nome, scanner.nextDouble());
					Controlador.getFuncionario(-1).setSenha(senha);
					System.out.println("Cadastro realizado com sucesso");
					break;
				}else {
					System.out.println("Reinsira os dados");
				}
			}
			Controlador.setVerificacaoDePrimeiraVez(false);
		}
		boolean continuar = true;
		while (continuar) {
			//Login
			while (true) {
				System.out.println("Diga o nome para login ou criar senha:");
				if (Login.setNome(scanner.nextLine()) == 0) {
					System.out.println("Não existe pessoa registrada com esse nome, tente de novo:");
				}else {
					System.out.println("Diga a senha:");
					if (Controlador.getFuncionario(Login.getNome()).getSenha() == null || Controlador.getCliente(Login.getNome()).getSenha() == null) {
						
					}
					while (true) {
						System.out.println("Diga a senha:");
						Login.login(scanner.nextLine());
						if (Login.getTipo() == 0) {
							System.out.println("Senha errada, tente de novo:");
						}else {
							break;
						}
					}
					break;
				}
			}
			// Gerente
			if (Login.getTipo() == 1) {
				Controlador.getFuncionario(Login.getNome()).avisarChegada();
				while (true) {
					System.out.println("O que deseja fazer, gerente " + Login.getNome() + "?");
					System.out.println("A) Registrar novo funcionário");
					System.out.println("B) Demitir funcionário");
					System.out.println("C) Mudar salário de funcionário");
					System.out.println("D) Verificar salário de funcionário");
					System.out.println("E) Emitir alerta para funcionário");
					System.out.println("F) Ver seu nome atual");
					System.out.println("G) Mudar seu nome");
					System.out.println("H) Mudar sua senha");
					System.out.println("I) Ver seu salário atual");
					System.out.println("J) Mudar seu salário");
					System.out.println("K) Ver quantidade de produtos em estoque");
					resposta = scanner.nextLine();
					if (resposta.equalsIgnoreCase("A")) {
						System.out.println("Diga o nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga o salário:");
						Controlador.getGerente(Login.getNome()).contratar(resposta, scanner.nextDouble());
					}else if (resposta.equalsIgnoreCase("B")) {
						System.out.println("Diga o nome do funcionário:");
						Controlador.getGerente(Login.getNome()).demitir(scanner.nextLine());
					}else if (resposta.equalsIgnoreCase("C")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga o novo valor do salário:");
						Controlador.getGerente(Login.getNome()).mudarSalarioFuncionario(resposta, scanner.nextDouble());
					}else if (resposta.equalsIgnoreCase("D")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("O salário de " + resposta + " é R$" + Controlador.getGerente(Login.getNome()).pegarSalarioFuncionario(resposta));
					}else if (resposta.equalsIgnoreCase("E")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga a mensagem:");
						Controlador.getGerente(Login.getNome()).emitirAlerta(resposta, scanner.nextLine());
					}
				}
			}
		}
		scanner.close();
	}
}