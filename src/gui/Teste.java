package gui;
import java.util.ArrayList;
import java.util.Scanner;
import logica.Cafeteria;
import logica.Produto;
import logica.Atendente;
import logica.Controlador;

public class Teste {
	public static void main(String[] args) {
		Cafeteria cafeteria = new Cafeteria("Cafeteria do Pé Sujo", "Rua das Inundações, 666 - Centro - Lugar Nenhum - Acre", "80701-1999");
		Controlador.adicionarGerente("Maria Graças da Silva", 10000.00);
		Controlador.adicionarGerente("Marlon Gilberto Farias", 20000.00);
		Controlador.adicionarFuncionario("Gonsalves Roberto Giovanne", 1080.00);
		Controlador.adicionarFuncionario("Ana Clara da Silva", 900.00);
		Controlador.adicionarFuncionario("João Guilherme de Lima Júnior", 680.00);
		Controlador.adicionarAtendente("João dos Montes Silva", 800.00);
		Controlador.adicionarCliente("João dos Montes Silva");
		Controlador.getFuncionario("Gonsalves Roberto Giovanne").avisarChegada();
		Controlador.adicionarProduto("Café expresso", 20.00, "Marrom", 20);
		Controlador.adicionarProduto("Café comum", 14.00, "Barato", 63);
		Controlador.adicionarProduto("Café com leite", 5.00, "Aleitado", 15);
		
		Produto produtoa;
		ArrayList<String[]> produtoss = new ArrayList<String[]>();
		produtoss.add(new String[3]);
		produtoa = Controlador.getProdutoPosicao(0);
		produtoss.get(produtoss.size()-1)[0] = produtoa.getNome();
		produtoss.get(produtoss.size()-1)[2] = Double.toString(produtoa.getPreco());
		produtoss.get(produtoss.size()-1)[1] = "3";
		Controlador.getCliente("João dos Montes Silva").fazerPedido(Controlador.getAtendente("João dos Montes Silva"), produtoss);
		Controlador.getCliente("João dos Montes Silva").fazerPedido(Controlador.getAtendente("João dos Montes Silva"), produtoss);
		Controlador.getCliente("João dos Montes Silva").fazerPedido(Controlador.getAtendente("João dos Montes Silva"), produtoss);
		
		System.out.println("Bem vindo à " + cafeteria.getNome() + " que fica em " + cafeteria.getEndereco() + ", se quiser ligar, disque " + cafeteria.getNumeroTelefone());
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
				System.out.println("S) Sim");
				System.out.println("N) Não");
				resposta = scanner.nextLine();
				if (resposta.equalsIgnoreCase("S")) {
					Controlador.adicionarCafeteria(nome, endereco, numeroTelefone);
					System.out.println("Cafeteria criada com sucesso.");
					System.out.println("Agora, por favor, insira seu nome para criar uma conta de gerente:");
					nome = scanner.nextLine();
					System.out.println("E uma senha de acesso:");
					String senha = scanner.nextLine();
					System.out.println("E o salário:");
					Controlador.adicionarGerente(nome, Double.parseDouble(scanner.nextLine()));
					Controlador.getGerente(nome).setSenha(senha);
					System.out.println("Cadastro realizado com sucesso");
					break;
				}else {
					System.out.println("Reinsira os dados");
				}
			}
			Controlador.setVerificacaoDePrimeiraVez(false);
		}
		boolean rodando = true;
		while (rodando) {
			//Login
			boolean logando = true;
			while (logando) {
				System.out.println("Você é um cliente? (S/N)");
				if (scanner.nextLine().equalsIgnoreCase("S")) {
					System.out.println("Qual seu nome?");
					Login.mudarNome(scanner.nextLine());
					System.out.println("Já tem conta? (S/N)");
					if (scanner.nextLine().equalsIgnoreCase("S")) {
						System.out.println("Diga a senha:");
						while (true) {
							Login.login(scanner.nextLine());
							if (Login.getTipo() == 0) {
								System.out.println("Senha errada, tente de novo:");
							}else {
								break;
							}
						}
						logando = false;
					}else {
						Controlador.adicionarCliente(Login.getNome());
						System.out.println("Diga uma senha:");
						String senha = scanner.nextLine();
						Controlador.getCliente(Login.getNome()).setSenha(senha);
						System.out.println("Senha criada com sucesso");
						Login.login(senha);
						logando = false;
					}
				}else {
					System.out.println("Diga o nome para login ou criar senha (Digite \"Sair\" para fechar o programa):");
					resposta = scanner.nextLine();
					if (resposta.equalsIgnoreCase("Sair")) {
						rodando = false;
						break;
					}
					if (Login.setNome(resposta) == 0) {
						System.out.println("Não existe pessoa registrada com esse nome, tente de novo:");
					}else {
						if (Controlador.getFuncionario(Login.getNome()) != null && Controlador.getFuncionario(Login.getNome()).getSenha() == null) {
							System.out.println("Você ainda não tem senha, diga uma:");
							String senha = scanner.nextLine();
							Controlador.getFuncionario(Login.getNome()).setSenha(senha);
							System.out.println("Senha criada com sucesso");
							Login.login(senha);
							logando = false;
						}else {
							System.out.println("Diga a senha:");
							while (true) {
								Login.login(scanner.nextLine());
								if (Login.getTipo() == 0) {
									System.out.println("Senha errada, tente de novo:");
								}else {
									break;
								}
							}
							logando = false;
						}
					}
				}
			}
			// Gerente
			boolean logado = true;
			if (Login.getTipo() == 1) {
				Controlador.getFuncionario(Login.getNome()).avisarChegada();
				System.out.println("Alertas:\n");
				ArrayList<String> alertas = Controlador.getFuncionario(Login.getNome()).pegarTodosAlertas();
				for (int i = 0; i < alertas.size(); i++) {
					System.out.println(i+1 + ") " + alertas.get(i));
				}
				while (logado) {
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
					System.out.println("L) Ver quantidade de vendas por dia");
					System.out.println("M) Deslogar");
					System.out.println("N) Fechar programa");
					resposta = scanner.nextLine();
					if (resposta.equalsIgnoreCase("A")) {
						System.out.println("Diga se é A) Funcionário comum ou B) Atendente:");
						String tipo = scanner.nextLine();
						System.out.println("Diga o nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga o salário:");
						Controlador.getGerente(Login.getNome()).contratar(tipo, resposta, Double.parseDouble(scanner.nextLine()));
					}else if (resposta.equalsIgnoreCase("B")) {
						System.out.println("Diga o nome do funcionário:");
						Controlador.getGerente(Login.getNome()).demitir(scanner.nextLine());
					}else if (resposta.equalsIgnoreCase("C")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga o novo valor do salário:");
						Controlador.getGerente(Login.getNome()).mudarSalarioFuncionario(resposta, Double.parseDouble(scanner.nextLine()));
					}else if (resposta.equalsIgnoreCase("D")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("O salário de " + resposta + " é R$" + Controlador.getGerente(Login.getNome()).pegarSalarioFuncionario(resposta));
					}else if (resposta.equalsIgnoreCase("E")) {
						System.out.println("Diga nome do funcionário:");
						resposta = scanner.nextLine();
						System.out.println("Diga a mensagem:");
						Controlador.getGerente(Login.getNome()).emitirAlerta(resposta, scanner.nextLine());
					}else if (resposta.equalsIgnoreCase("F")) {
						System.out.println("Seu nome atual é: " + Controlador.getGerente(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("G")) {
						System.out.println("Diga o novo nome:");
						String nomeAntigo = Login.getNome();
						Login.mudarNome(scanner.nextLine());
						Controlador.getGerente(nomeAntigo).setNome(Login.getNome());
						System.out.println("Seu nome atual é: " + Controlador.getGerente(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("H")) {
						System.out.println("Diga a senha atual:");
						while (true) {
							resposta = scanner.nextLine();
							if (Login.verficarSenha(resposta)) {
								System.out.println("Diga a nova senha:");
								resposta = scanner.nextLine();
								Controlador.getGerente(Login.getNome()).setSenha(resposta);
								System.out.println("Senha mudada com sucesso");
								break;
							}else {
								System.out.println("Senha errada, tente de novo:");
							}
						}
					}else if (resposta.equalsIgnoreCase("I")) {
						System.out.println("Seu salário atual é: " + Controlador.getGerente(Login.getNome()).getSalario());
					}else if (resposta.equalsIgnoreCase("J")) {
						System.out.println("Diga o novo salário:");
						Controlador.getGerente(Login.getNome()).setSalario(Double.parseDouble(scanner.nextLine()));
						System.out.println("Seu salário atual é: " + Controlador.getGerente(Login.getNome()).getSalario());
					}else if (resposta.equalsIgnoreCase("K")) {
						ArrayList<String[]> quantidade = Controlador.getGerente(Login.getNome()).pegarQuantidadeProdutosEstoque();
						for (int i = 0; i < quantidade.size(); i++) {
							System.out.println("Nome do produto: " + quantidade.get(i)[0] + "   Quantidade em estoque: " + quantidade.get(i)[1]);
						}
					}else if (resposta.equalsIgnoreCase("L")) {
						ArrayList<String[]> datas = Controlador.getGerente(Login.getNome()).pegarVendasDiarias();
						for (int i = 0; i < datas.size(); i++) {
							System.out.println("Data: " + datas.get(i)[0] + "   Ganho com vendas: " + datas.get(i)[1]);
						}
					}else if (resposta.equalsIgnoreCase("M")) {
						Login.sair();
						logado = false;
					}else if (resposta.equalsIgnoreCase("N")) {
						logado = false;
						rodando = false;
					}
				}
			}else if (Login.getTipo() == 2) { //Funcionario comum
				Controlador.getFuncionario(Login.getNome()).avisarChegada();
				System.out.println("Alertas:\n");
				ArrayList<String> alertas = Controlador.getFuncionario(Login.getNome()).pegarTodosAlertas();
				for (int i = 0; i < alertas.size(); i++) {
					System.out.println(i+1 + ") " + alertas.get(i));
				}
				while (logado) {
					System.out.println("O que deseja fazer, funcionário " + Login.getNome() + "?");
					System.out.println("A) Ver seu nome atual");
					System.out.println("B) Mudar seu nome");
					System.out.println("C) Mudar sua senha");
					System.out.println("D) Ver seu salário atual");
					System.out.println("E) Deslogar");
					System.out.println("F) Fechar programa");
					resposta = scanner.nextLine();
					if (resposta.equalsIgnoreCase("A")) {
						System.out.println("Seu nome atual é: " + Controlador.getFuncionario(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("B")) {
						System.out.println("Diga o novo nome:");
						String nomeAntigo = Login.getNome();
						Login.mudarNome(scanner.nextLine());
						Controlador.getFuncionario(nomeAntigo).setNome(Login.getNome());
						System.out.println("Seu nome atual é: " + Controlador.getFuncionario(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("C")) {
						System.out.println("Diga a senha atual:");
						while (true) {
							resposta = scanner.nextLine();
							if (Login.verficarSenha(resposta)) {
								System.out.println("Diga a nova senha:");
								resposta = scanner.nextLine();
								Controlador.getFuncionario(Login.getNome()).setSenha(resposta);
								System.out.println("Senha mudada com sucesso");
								break;
							}else {
								System.out.println("Senha errada, tente de novo:");
							}
						}
					}else if (resposta.equalsIgnoreCase("D")) {
						System.out.println("Seu salário atual é: " + Controlador.getFuncionario(Login.getNome()).getSalario());
					}else if (resposta.equalsIgnoreCase("E")) {
						Login.sair();
						logado = false;
					}else if (resposta.equalsIgnoreCase("F")) {
						logado = false;
						rodando = false;
					}
				}
			}else if (Login.getTipo() == 3) { //Atendente
				Controlador.getFuncionario(Login.getNome()).avisarChegada();
				System.out.println("Alertas:\n");
				ArrayList<String> alertas = Controlador.getFuncionario(Login.getNome()).pegarTodosAlertas();
				for (int i = 0; i < alertas.size(); i++) {
					System.out.println(i+1 + ") " + alertas.get(i));
				}
				while (logado) {
					System.out.println("O que deseja fazer, atendente " + Login.getNome() + "?");
					System.out.println("A) Adicionar produto ao estoque");
					System.out.println("B) Tirar produto do estoque");
					System.out.println("C) Invalidar pedido");
					System.out.println("D) Ver seu nome atual");
					System.out.println("E) Mudar seu nome");
					System.out.println("F) Mudar sua senha");
					System.out.println("G) Ver seu salário atual");
					System.out.println("H) Deslogar");
					System.out.println("I) Fechar programa");
					resposta = scanner.nextLine();
					if (resposta.equalsIgnoreCase("A")) {
						System.out.println("Diga o nome do produto:");
						String nome = scanner.nextLine();
						System.out.println("Diga o preço do produto:");
						double preco = Double.parseDouble(scanner.nextLine());
						System.out.println("Diga uma descrição para o produto:");
						String descricao = scanner.nextLine();
						System.out.println("Diga a quantidade do produto:");
						Controlador.getAtendente(Login.getNome()).comprarProduto(nome, preco, descricao, scanner.nextInt());
						scanner.nextLine();
					}else if (resposta.equalsIgnoreCase("B")) {
						System.out.println("Diga o nome do produto a ser retirado:");
						Controlador.getAtendente(Login.getNome()).tirarProdutoEstoque(scanner.nextLine());
					}else if (resposta.equalsIgnoreCase("C")) {
						System.out.println("Diga o horário do pagamento do pedido a ser cancelado:");
						Controlador.getAtendente(Login.getNome()).invalidarPedido(scanner.nextLine());
					}else if (resposta.equalsIgnoreCase("D")) {
						System.out.println("Seu nome atual é: " + Controlador.getFuncionario(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("E")) {
						System.out.println("Diga o novo nome:");
						String nomeAntigo = Login.getNome();
						Login.mudarNome(scanner.nextLine());
						Controlador.getFuncionario(nomeAntigo).setNome(Login.getNome());
						System.out.println("Seu nome atual é: " + Controlador.getFuncionario(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("F")) {
						System.out.println("Diga a senha atual:");
						while (true) {
							resposta = scanner.nextLine();
							if (Login.verficarSenha(resposta)) {
								System.out.println("Diga a nova senha:");
								resposta = scanner.nextLine();
								Controlador.getFuncionario(Login.getNome()).setSenha(resposta);
								System.out.println("Senha mudada com sucesso");
								break;
							}else {
								System.out.println("Senha errada, tente de novo:");
							}
						}
					}else if (resposta.equalsIgnoreCase("G")) {
						System.out.println("Seu salário atual é: " + Controlador.getFuncionario(Login.getNome()).getSalario());
					}else if (resposta.equalsIgnoreCase("H")) {
						Login.sair();
						logado = false;
					}else if (resposta.equalsIgnoreCase("I")) {
						logado = false;
						rodando = false;
					}
				}
			}else if (Login.getTipo() == 4) { //Cliente
				while (logado) {
					System.out.println("O que deseja fazer, atendente " + Login.getNome() + "?");
					System.out.println("A) Fazer pedido");
					System.out.println("B) Ver seu nome atual");
					System.out.println("C) Mudar seu nome");
					System.out.println("D) Mudar sua senha");
					System.out.println("E) Deslogar");
					System.out.println("F) Fechar programa");
					resposta = scanner.nextLine();
					if (resposta.equalsIgnoreCase("A")) {
						ArrayList<String[]> produtos = new ArrayList<String[]>();
						while (true) {
							System.out.println("Diga qual produto quer da lista abaixo:");
							Produto produto;
							for (int i = 0; i < Controlador.getProdutos().size(); i++) {
								produto = Controlador.getProdutoPosicao(i);
								System.out.println(i+1 + ") " + produto.getNome() + " - Preço: R$" + produto.getPreco());
							}
							produtos.add(new String[3]);
							produto = Controlador.getProdutoPosicao(scanner.nextInt()-1);
							scanner.nextLine();
							produtos.get(produtos.size()-1)[0] = produto.getNome();
							produtos.get(produtos.size()-1)[2] = Double.toString(produto.getPreco());
							System.out.println("Quanto de " + produtos.get(produtos.size()-1)[0] + " vai querer? A quantidade presente no estoque é " + Controlador.getProduto(produtos.get(produtos.size()-1)[0]).getQuantidadeProduto());
							resposta = scanner.nextLine();
							if (Integer.parseInt(resposta) < Controlador.getProduto(produtos.get(produtos.size()-1)[0]).getQuantidadeProduto()) {
								produtos.get(produtos.size()-1)[1] = resposta;
							}
							System.out.println("Deseja mais algum produto? (S/N)");
							resposta = scanner.nextLine();
							if (resposta.equalsIgnoreCase("N")) {
								break;
							}
						}
						Atendente atendente;
						System.out.println("Diga qual atendende quer da lista abaixo:");
						for (int i = 0; i < Controlador.getAtendentes().size(); i++) {
							System.out.println(i+1 + ") " + Controlador.getAtendentePosicao(i).getNome());
						}
						atendente = Controlador.getAtendentePosicao(scanner.nextInt()-1);
						scanner.nextLine();
						Controlador.getCliente(Login.getNome()).fazerPedido(atendente,produtos);
					}else if (resposta.equalsIgnoreCase("B")) {
						System.out.println("Seu nome atual é: " + Controlador.getCliente(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("C")) {
						System.out.println("Diga o novo nome:");
						String nomeAntigo = Login.getNome();
						Login.mudarNome(scanner.nextLine());
						Controlador.getCliente(nomeAntigo).setNome(Login.getNome());
						System.out.println("Seu nome atual é: " + Controlador.getCliente(Login.getNome()).getNome());
					}else if (resposta.equalsIgnoreCase("D")) {
						System.out.println("Diga a senha atual:");
						while (true) {
							resposta = scanner.nextLine();
							if (Login.verficarSenha(resposta)) {
								System.out.println("Diga a nova senha:");
								resposta = scanner.nextLine();
								Controlador.getCliente(Login.getNome()).setSenha(resposta);
								System.out.println("Senha mudada com sucesso");
								break;
							}else {
								System.out.println("Senha errada, tente de novo:");
							}
						}
					}else if (resposta.equalsIgnoreCase("E")) {
						Login.sair();
						logado = false;
					}else if (resposta.equalsIgnoreCase("F")) {
						logado = false;
						rodando = false;
					}
				}
			}
		}
		scanner.close();
	}
}