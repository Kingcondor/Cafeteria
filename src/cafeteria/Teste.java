package cafeteria;

import java.util.Scanner;

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
		boolean continuar = true;
		Scanner scanner = new Scanner(System.in);
		String resposta;
		while (continuar) {
			System.out.println("Escolha uma forma de acesso:");
			System.out.println("A) Gerente");
			System.out.println("B) Funcionario com acesso financeiro");
			System.out.println("C) Funcionario sem acesso financeiro");
			System.out.println("D) Cliente");
			// Gerente
			resposta = scanner.nextLine();
			if (resposta.equalsIgnoreCase("A")) {
				Gerente acesso;
				while (true) {
					System.out.println("Diga seu nome completo:");
					resposta = scanner.nextLine();
					acesso = (Gerente) Controlador.getFuncionario(resposta);
					if (acesso == null) {
						System.out.println("Nome não existe no sistema, tente de novo");
					}else {
						break;
					}
				}
				while (true) {
					System.out.println("Diga sua senha:");
					resposta = scanner.nextLine();
					if (acesso.getSenha().equals(resposta)) {
						break;
					}else {
						System.out.println("Senha errada, tente de novo");
					}
				}
				acesso.avisarChegada();
				while (true) {
					System.out.println("O que deseja fazer, gerente " + acesso.getNome() + "?");
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
						acesso.contratar(resposta, scanner.nextDouble());
					}else if (resposta.equalsIgnoreCase("B")) {
						System.out.println("Diga o nome do funcionário:");
						acesso.demitir(scanner.nextLine());
					}else if (resposta.equalsIgnoreCase("C")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga o novo valor do salário:");
						acesso.mudarSalarioFuncionario(resposta, scanner.nextDouble());
					}else if (resposta.equalsIgnoreCase("D")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("O salário de " + resposta + " é R$" + acesso.pegarSalarioFuncionario(resposta));
					}else if (resposta.equalsIgnoreCase("E")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga a mensagem:");
						acesso.emitirAlerta(resposta, scanner.nextLine());
					}
				}
			}
		}
		scanner.close();
	}
}
