package cafeteria;

import java.util.ArrayList;
import java.util.Scanner;

public class Teste {
	public static void main(String[] args) {
		Cafeteria cafeteria = new Cafeteria("Cafeteria, the winners");
		cafeteria.adicionarGerente("gerenteTeste", 2400, new ArrayList<Integer>());
		cafeteria.adicionarFuncionario("funcionarioTesteP", 1 , 1250);
		cafeteria.adicionarFuncionario("funcionarioTesteS", 2 , 750);
		cafeteria.adicionarCliente("clienteTeste");
		//cafeteria.
		System.out.println("Bem vindo ao "+ cafeteria.getNome());
		boolean j = true;
		boolean t = true;
		while (j) {
			System.out.println("O senhor é: ");
			System.out.println("A) Gerente");
			System.out.println("B) Funcionario");
			System.out.println("C) Cliente");
			System.out.println("Escolha: ");
			Scanner sc = new Scanner(System.in);
			String digitado = sc.nextLine();
			// GERENTE
			while (t) {
			if ("a".equalsIgnoreCase(digitado)) {
				System.out.println("Bem vindo "+ cafeteria.getGerentes().get(0).getNome() + " o que gostaria de fazer?");
				System.out.println("A) Contratar um funcionário");
				System.out.println("B) Demitir um funcionário");
				System.out.println("C) Aumentar salário");
				System.out.println("D) Emitir um alerta");
				System.out.println("E) Sair");
				String digitado2 = sc.nextLine();
				if ("a".equalsIgnoreCase(digitado2)) {
					System.out.println("Qual o nome do contrado?");
					String digitado3 = sc.nextLine();
					System.out.println("Qual o tipo de funcionario?");
					int digitado4 = sc.nextInt();
					cafeteria.getGerentes().get(0).contratar(cafeteria, digitado3, digitado4, 750);
					System.out.println("Você acaba de contratar "+ cafeteria.listarFuncionarios().get(cafeteria.listarFuncionarios().size() - 1).getNome());
				}
				else if ("b".equalsIgnoreCase(digitado2)) {
					System.out.println("Quem deseja demitir?");
					String digitado3 = sc.nextLine();
					cafeteria.getGerentes().get(0).demitir(cafeteria, digitado3);
					System.out.println("Funcionário demitido.");
				}
				else if ("C".equalsIgnoreCase(digitado2)) {
					System.out.println("De quem deseja aumantar o salário?");
					String digitado3 = sc.nextLine();
					System.out.println("Em quanto deseja aumentar o salário?");
					int digitado4 = sc.nextInt();
					cafeteria.getGerentes().get(0).aumentarSalario(cafeteria, digitado3, digitado4);
				}
				else if ("d".equalsIgnoreCase(digitado2)) {
					System.out.println("Para quem deseja emitir o alerta? (em caso de alerta geral digite 'Todos')");
					String digitado3 = sc.nextLine();
					System.out.println("Escreva o alerta: ");
					String digitado4 = sc.nextLine();
					cafeteria.getGerentes().get(0).emitirAlerta(cafeteria, digitado3, digitado4);
				}
				else if ("e".equalsIgnoreCase(digitado2)) {
					System.out.println("Até mais, "+ cafeteria.getGerentes().get(0).getNome());
					t = false;
				}
				else {
					System.out.println("Escolha não correspondente as opçoes acima, tente novamente ou digite cancelar");
				}
			}
			// FUNCIONÁRIO
			else if ("b".equalsIgnoreCase(digitado)) {
				System.out.println("O senhor é: ");
				System.out.println("A) Funcionário primario");
				System.out.println("B) Funcionario secundario");
				String digitado2 = sc.nextLine();
				//Apenas para teste não foram implementadas a utilização de usuarios e senhas
				if ("a".equalsIgnoreCase(digitado2)) {
					cafeteria.getFuncionario(0).avisarChegada();
					cafeteria.getFuncionario(0).receberAlertaGerente(cafeteria.getGerentes().get(0).getAlerta());
					System.out.println("Bem vindo "+ cafeteria.getFuncionario(0).getNome());
					System.out.println("O senhor gostaria de: ");
					System.out.println("A) Ver os produtos disponíves");
					System.out.println("B) Ver salário");
					System.out.println("C) Invalidar uma compra");
					System.out.println("D) Sair");
					String digitado3 = sc.nextLine();
					if ("a".equalsIgnoreCase(digitado3)) {
						for (int k = 0; k < cafeteria.getProdutos().size(); k++) {
							if (cafeteria.getProdutos().get(k).getQuantidadeDoProduto() != 0) {
								System.out.println("- "+ cafeteria.getProdutos().get(k).getNome() + ", preço: "+ cafeteria.getProdutos().get(k).getPreco());
							}
						}
					}
					else if ("b".equalsIgnoreCase(digitado3)) {
						System.out.println("Seu salário é de R$"+ cafeteria.getFuncionario(0).getSalario());
					}
					else if ("c".equalsIgnoreCase(digitado3)) {
						int l=-1;
						System.out.println("Qual o cliente que deseja invalidar?");
						String nome = sc.nextLine();
						for (int k = 0; k < cafeteria.getClientes().size(); k++) {
							if (nome.equalsIgnoreCase(cafeteria.getClientes().get(k).getNome())) {
								l = k;
							}
						}
						if (l>-1) {
							cafeteria.getFuncionario(0).invalidarPedido(cafeteria.getNotas(), l);
							System.out.println("Ultimo pedido invalidado");
						}
					}
					else if ("d".equalsIgnoreCase(digitado3)) {
						System.out.println("Até mais, "+ cafeteria.getFuncionario(0).getNome());
						t = false;
					}
					else {
						System.out.println("Escolha não correspondente as opçoes acima, tente novamente ou digite cancelar");
					}
				}
				else if ("b".equalsIgnoreCase(digitado2)) {
					cafeteria.getFuncionario(1).avisarChegada();
					cafeteria.getFuncionario(1).receberAlertaGerente(cafeteria.getGerentes().get(1).getAlerta());
					System.out.println("Bem vindo "+ cafeteria.getFuncionario(1).getNome());
					System.out.println("O senhor gostaria de: ");
					System.out.println("A) Ver salário");
					System.out.println("B) Sair");
					String digitado3 = sc.nextLine();
					if ("a".equalsIgnoreCase(digitado3)) {
						System.out.println("Seu salário é de R$"+ cafeteria.getFuncionario(1).getSalario());
					}
					else if ("b".equalsIgnoreCase(digitado3)) {
						System.out.println("Até mais, "+ cafeteria.getFuncionario(1).getNome());
						j = false;
					}
					else {
						System.out.println("Escolha não correspondente as opçoes acima, tente novamente ou digite cancelar");
					}
				}
				else {
					System.out.println("Escolha não correspondente as opçoes acima, tente novamente ou digite cancelar");
				}
			}
			//CLIENTE
			else if ("c".equalsIgnoreCase(digitado)) {
				//Apenas para teste não foram implementadas a utilização de usuarios e senhas
				System.out.println("Bem vindo "+ cafeteria.getClientes().get(0).getNome());
				System.out.println("O que deseja fazer: ");
				System.out.println("A) Ver catalogo");
				System.out.println("B) Comprar produtos");
				System.out.println("C) Ver historico de atendentes");
				System.out.println("D) Ver historico de compras (Notas fiscais)");
				System.out.println("E) Sair");
				String digitado2 = sc.nextLine();
				if ("a".equalsIgnoreCase(digitado2)) {
					for (int k = 0; k < cafeteria.getProdutos().size(); k++) {
						if (cafeteria.getProdutos().get(k).getQuantidadeDoProduto() != 0) {
							System.out.println("- "+ cafeteria.getProdutos().get(k).getNome() + ", preço: "+ cafeteria.getProdutos().get(k).getPreco());
						}
					}
				}
				else if ("b".equalsIgnoreCase(digitado2)) {
					int valor =0;
					System.out.println("Qual produto deseja comprar? (Digite o nome do produto desejado)");
					String compra = sc.nextLine();
					for (int k = 0; k < cafeteria.getProdutos().size(); k++) {
						if (compra.equalsIgnoreCase(cafeteria.getProdutos().get(k).getNome())) {
							valor += cafeteria.getProdutos().get(k).getPreco();
						}
					}
					cafeteria.getClientes().get(0).fazerPedido(cafeteria.getFuncionario(0), cafeteria.getClientes().get(0), valor);
					cafeteria.getNotas().add(cafeteria.getClientes().get(0).getNF().get(cafeteria.getClientes().get(0).getNF().size() - 1));
				}
				else if ("c".equalsIgnoreCase(digitado2)) {
					System.out.println("Historico de atendentes: ");
					for (int k = 0; k < cafeteria.getClientes().get(0).getAtendentes().size(); k++) {
						System.out.println("- "+ cafeteria.getClientes().get(0).getAtendentes().get(k));
					}
				}
				else if ("d".equalsIgnoreCase(digitado2)) {
					System.out.println("Notas fiscais: ");
					for (int k = 0; k < cafeteria.getClientes().get(0).getNF().size(); k++) {
						System.out.println("- "+ cafeteria.getClientes().get(0).getNF().get(k).toString());
					}
				}
				else if ("e".equalsIgnoreCase(digitado2)) {
					System.out.println("Até mais, "+ cafeteria.getFuncionario(0).getNome());
					t = false;
				}
				else {
					System.out.println("Escolha não correspondente as opçoes acima, tente novamente ou digite cancelar");
				}
			
			}
			else if ("cancelar".equalsIgnoreCase(digitado)) {
				j = false;
			}
			else {
				System.out.println("Escolha não correspondente as opçoes acima, tente novamente ou digite cancelar");
			}
			
			}
			System.out.println("Deseja sair do programa?");
			System.out.println("A) Sim");
			System.out.println("B) Não");
			String digitado5 = sc.nextLine();
			
			if (digitado5.equalsIgnoreCase("a")) {
				j = false;
			}
			else {
				j = true;
				t = true;
			}
		}
		System.out.println("PROGRAMA ENCERRADO");
	}
}
