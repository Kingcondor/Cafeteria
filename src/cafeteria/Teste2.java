package cafeteria;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Teste2 {
	public static void main(String[] args) {
		//Cafeteria cafeteria = new Cafeteria("Cafeteria do Pé Sujo", "Rua das Inundações, 666 - Centro - Lugar Nenhum - Acre", "80701-1999");
		Controlador.adicionarGerente("Maria Gra�as da Silva", 10000.00);
		Controlador.adicionarGerente("Marlon Gilberto Farias", 20000.00);
		Controlador.adicionarFuncionario("Gonsalves Roberto Giovanne", 1080.00);
		Controlador.adicionarFuncionario("Ana Clara da Silva", 900.00);
		Controlador.adicionarFuncionario("João Guilherme de Lima Júnior", 680.00);
		Controlador.adicionarFuncionario("João dos Montes Silva", 800.00);
		Cafeteria cafeteria = new Cafeteria("", "", "");
		int verificadorDeIniciacao = 0;
		//cafeteria.
		Scanner sc = new Scanner(System.in);
		String resposta, resposta2, resposta3, digitado, senha="";
		boolean continuar = true, continuar2 = true, continuar3 = true, continuar4 = true;
		if (verificadorDeIniciacao == 0) {
			while (continuar) {
				System.out.println("Bem-vindo ao aplicativo de Cafeterias.");
				System.out.println("Como est� � sua primeira vez no aplicativo, por favor, digite o Nome da cafeteria, o endere�o e n�mero de contato do estabelecimento, respectivamente: ");
				String nome = sc.nextLine();
				String endereco = sc.nextLine();
				String numeroTelefone = sc.nextLine();
				System.out.println("Nome: "+ nome);
				System.out.println("Endere�o: " + endereco);
				System.out.println("N�mero de telefone: "+numeroTelefone);
				System.out.println("Estes dados est�o corretos? (Estes dados podem ser mudados posteriormente)" );
				System.out.println("A) Sim");
				System.out.println("B) N�o");
				System.out.println("Escolha: ");
				resposta = sc.nextLine();
				if ("a".equalsIgnoreCase(resposta)) {
					System.out.println("Cafeteria criada com sucesso.");
					cafeteria = new Cafeteria(nome, endereco, numeroTelefone);
					//Controlador.adicionarCafeteria(nome, endereco, numeroTelefone);
					System.out.println("Agora por favor, insira seu nome: ");
					nome = sc.nextLine();
					System.out.println("E um senha (Est� apenas voc� dever� ter conhecimento: ");
					senha = sc.nextLine();
					Controlador.adicionarGerente(nome, 0);
					Controlador.getFuncionario(nome).setSenha(senha);
					System.out.println("Cadastro realizado com sucesso");
					continuar = false;
					verificadorDeIniciacao = 1;
				}
				else {
					System.out.println("Reinsira os dados.");
				}
			}
		}
		continuar = true;
		while (continuar) {
			if (verificadorDeIniciacao == 1) {
				digitado = "a";
				verificadorDeIniciacao = 2;
			}
			else {
				System.out.println("Bem-vindo a� " + cafeteria.getNome() + " que fica em " + cafeteria.getEndereco() + ", se quiser ligar, disque " + cafeteria.getNumeroTelefone());
				System.out.println("Escolha uma forma de acesso:");
				System.out.println("A) Gerente");
				System.out.println("B) Funcionario"); //Dois tipos de funcionario
				System.out.println("C) Cliente");
				System.out.println("D) Fechar programa");
				digitado = sc.nextLine();
				continuar = true; continuar2 = true; continuar3 = true; continuar4 = true;
			}
			// GERENTE
			if ("a".equalsIgnoreCase(digitado)) {
				while (continuar2) {
					System.out.println("LOGIN");
					System.out.println("Digite seu nome: ");
					String nome = sc.nextLine();
					if (nome.equalsIgnoreCase("cancelar") == false) {
						System.out.println("Digite sua senha: ");
						senha = sc.nextLine();
						if (senha.equalsIgnoreCase("cancelar")) {
							continuar2 = false;
						}
					}
					else {
						continuar2 = false;
					}
					if (Controlador.getFuncionario(nome) != null && Controlador.getFuncionario(nome).getTipo().equalsIgnoreCase("Gerente") && Controlador.getFuncionario(nome).getSenha().equals(senha) == true) {
						Gerente acesso = (Gerente) Controlador.getFuncionario(nome);
						continuar2 = false;
						acesso.avisarChegada();
						while (continuar3) {
							System.out.println("O que deseja fazer, gerente " + acesso.getNome() + "?");
							System.out.println("A) Ver lista de funcion�rios");
							System.out.println("B) Registrar novo funcion�rio");
							System.out.println("C) Demitir funcion�rio");
							System.out.println("D) Mudar sal�rio de funcion�rio");
							System.out.println("E) Verificar sal�rio de funcion�rio");
							System.out.println("F) Emitir alerta para funcion�rio");
							System.out.println("G) Mudar seu nome");
							System.out.println("H) Mudar sua senha");
							System.out.println("I) Ver seu sal�rio atual");
							System.out.println("J) Mudar seu sal�rio");
							System.out.println("K) Ver op��es para produtos");
							System.out.println("L) Ver dias de trabalho/Hist�rico de acesso");
							System.out.println("M) Sair");
							System.out.println("Escolha: ");
							resposta = sc.nextLine();
							if (resposta.equalsIgnoreCase("A")) {
								System.out.println("Funcion�rios: ");
								for (int j = 0; j < Controlador.listarFuncionarios().size(); j++) {
									System.out.println("-" + Controlador.getFuncionario(j).getNome());
								}
							}else if (resposta.equalsIgnoreCase("B")) {
								System.out.println("Diga o nome do funcion�rio:");
								resposta2 = sc.nextLine();
								System.out.println("Diga o sal�rio:");
								double respostaDouble = sc.nextDouble();
								acesso.contratar(resposta2, respostaDouble);
								System.out.println("Funcion�rio contratado com sucesso");
							}else if (resposta.equalsIgnoreCase("C")) {
								System.out.println("Diga o nome do funcion�rio:");
								acesso.demitir(sc.nextLine());
							}else if (resposta.equalsIgnoreCase("D")) {
								System.out.println("Diga nome do funcion�rio:");
								resposta = sc.nextLine();
								System.out.println("Diga o novo valor do sal�rio:");
								acesso.mudarSalarioFuncionario(resposta, sc.nextDouble());
							}else if (resposta.equalsIgnoreCase("E")) {
								System.out.println("Diga nome do funcion�rio:");
								resposta = sc.nextLine();
								System.out.println("O sal�rio de " + resposta + " � R$" + acesso.pegarSalarioFuncionario(resposta) + ".");
							}else if (resposta.equalsIgnoreCase("F")) {
								System.out.println("Digite o nome do funcion�rio (Caso deseje mandar para todos os funcion�rios digite 'Todos'):");
								resposta = sc.nextLine();
								System.out.println("Digite a mensagem: ");
								acesso.emitirAlerta(resposta, sc.nextLine());
							}else if (resposta.equalsIgnoreCase("H")) {
								System.out.println("Digite sua senha atual: ");
								resposta2 = sc.nextLine();
								if (resposta2.equals(acesso.getSenha())) {
									System.out.println("Agora digite a senha que deseja: ");
									resposta2 = sc.nextLine();
									acesso.setSenha(resposta2);
								}else {
									System.out.println("Senha incorreta.");
								}
							}else if (resposta.equalsIgnoreCase("G")) {
								System.out.println("Digite o nome desejado: ");
								resposta2 = sc.nextLine();
								acesso.setNome(resposta2);
							}else if (resposta.equalsIgnoreCase("I")) {
								System.out.println("Seu sal�rio atualmente � de R$" + acesso.getSalario() + ".");
							}else if (resposta.equalsIgnoreCase("J")) {
								System.out.println("Digite seu sal�rio atual: ");
								int respostaInt = sc.nextInt();
								acesso.setSalario(respostaInt);
								System.out.println("Sal�rio alterado com sucesso.");
							}else if (resposta.equalsIgnoreCase("K")) {
								System.out.println("Produtos: ");
								for (int j = 0; j < Controlador.listarProdutos().size(); j++) {
									System.out.println("-" + Controlador.getProdutos().get(j).getNome() + " - R$ " + Controlador.getProdutos().get(j).getPreco() + " - Qtd. " + Controlador.getProdutos().get(j).getQuantidadeDoProduto() + " (" + Controlador.getProdutos().get(j).getDescricao() + ")");
								}
								System.out.println("");
								System.out.println("A) adicionar um produto");
								System.out.println("B) Remover um produto do estoque");
								System.out.println("Escolha: ");
								resposta2 = sc.nextLine();
								if ("a".equalsIgnoreCase(resposta2)) {
									System.out.println("Digite o nome do produto: ");
									nome = sc.nextLine();
									System.out.println("Digite o pre�o do produto: ");
									double preco = sc.nextDouble();
									String qualquerCoisa = sc.nextLine();
									System.out.println("Digite uma descri��o para o produto: ");
									String descricao = sc.nextLine();
									System.out.println("Digite a quantidade em estoque do produto: ");
									int quantidade = sc.nextInt();
									Controlador.adicionarProduto(nome, preco, descricao, quantidade);
									System.out.println("Produto adicionado com sucesso.");
								}
								else if ("b".equalsIgnoreCase(resposta2)) {
									System.out.println("Digite o nome do produto que deseja remover: ");
									nome = sc.nextLine();
									Controlador.tirarProduto(nome);
								}
								else {
									System.out.println("Op��o inv�lida.");
								}
							}else if ("L".equalsIgnoreCase(resposta)) {
								System.out.println("Hist�rico de acesso/Dias de trabalho: ");
								for (int j = 0; j < acesso.getDiasDeTrabalho().size(); j++) {
									System.out.println("-" + acesso.getDiasDeTrabalho().get(j));
								}
							}else if (resposta.equalsIgnoreCase("M")) {
								continuar3 = false;
							}else {
								System.out.println("Op��o inv�lida.");
							}
						}
					}
					else {
						System.out.println("Nome ou senha incorreta, tente novamente ou digite 'Cancelar'.");
					}
				}
			}
			// FUNCION�RIO
			else if ("b".equalsIgnoreCase(digitado)) {
				while (continuar2) {
					System.out.println("A) Funcion�rio com acesso financeiro");
					System.out.println("B) Funcion�rio sem acesso financeiro");
					System.out.println("Escolha: ");
					String digitado2 = sc.nextLine();
					// FUNCIONARIO FINANCEIRO
					if ("a".equalsIgnoreCase(digitado2)) {
						while (continuar3) {
							System.out.println("Digite seu nome (Caso seja seu primeiro acesso ao programa digite seu nome exatamente como cadastrado pelo gerente): ");
							String nomeDeCadastro = sc.nextLine();
							if (Controlador.getFuncionario(nomeDeCadastro) != null && Controlador.getFuncionario(nomeDeCadastro).verificacaoDePrimeiraVez) {
								Funcionario acesso2 = Controlador.getFuncionario(nomeDeCadastro);
								System.out.println("Digite a senha que deseja para ter acesso ao sistema: ");
								senha = sc.nextLine();
								acesso2.setSenha(senha);
								acesso2.setVerificacaoDePrimeiraVez(false);
								System.out.println("Cadastro feito com sucesso");
							}
							else if (Controlador.getFuncionario(nomeDeCadastro) != null && Controlador.getFuncionario(nomeDeCadastro).getTipo().equalsIgnoreCase("Financeiro") && Controlador.getFuncionario(nomeDeCadastro).verificacaoDePrimeiraVez == false) {
								Financeiro acesso2 = (Financeiro) Controlador.getFuncionario(nomeDeCadastro);
								System.out.println("Digite sua senha: ");
								senha = sc.nextLine();
								if (acesso2.getSenha().equals(senha)) {
									while (continuar4) {
										continuar3 = false;
										acesso2.avisarChegada();
										System.out.println("Bem-vindo "+ acesso2.getNome());
										System.out.println("A) Ver op��es de produtos");
										System.out.println("B) Mudar seu nome");
										System.out.println("C) Mudar sua senha");
										System.out.println("D) Ver seu sal�rio atual");
										System.out.println("E) Ver dias de trabalho");
										System.out.println("F) Ver Alertas");
										System.out.println("G) Invalidar uma compra");
										System.out.println("H) Sair");
										resposta = sc.nextLine();
										if ("a".equalsIgnoreCase(resposta)) {
											System.out.println("Produtos: ");
											for (int j = 0; j < Controlador.listarProdutos().size(); j++) {
												System.out.println("-" + Controlador.getProdutos().get(j).getNome() + " - R$ " + Controlador.getProdutos().get(j).getPreco() + " - Qtd. " + Controlador.getProdutos().get(j).getQuantidadeDoProduto() + " (" + Controlador.getProdutos().get(j).getDescricao() + ")");
											}
											System.out.println("");
											System.out.println("A) adicionar um produto");
											System.out.println("B) Remover um produto do estoque");
											System.out.println("Escolha: ");
											resposta2 = sc.nextLine();
											if ("a".equalsIgnoreCase(resposta2)) {
												System.out.println("Digite o nome do produto: ");
												String nome = sc.nextLine();
												System.out.println("Digite o pre�o do produto: ");
												double preco = sc.nextDouble();
												System.out.println("Digite uma descri��o para o produto: ");
												String descricao = sc.nextLine();
												System.out.println("Digite a quantidade em estoque do produto: ");
												int quantidade = sc.nextInt();
												acesso2.comprarProduto(nome, preco, descricao, quantidade);
												System.out.println("Produto adicionado com sucesso.");
											}
											else if ("b".equalsIgnoreCase(resposta2)) {
												System.out.println("Digite o nome do produto que deseja remover: ");
												String nome = sc.nextLine();
												acesso2.tirarProdutoEstoque(nome);
											}
											else {
												System.out.println("Op��o inv�lida");
											}
										}
										else if ("b".equalsIgnoreCase(resposta)) {
											System.out.println("Digite o nome desejado: ");
											resposta2 = sc.nextLine();
											acesso2.setNome(resposta2);
										}
										else if ("c".equalsIgnoreCase(resposta)) {
											System.out.println("Digite sua senha atual: ");
											resposta2 = sc.nextLine();
											if (resposta2.equals(acesso2.getSenha())) {
												System.out.println("Agora digite a senha que deseja: ");
												resposta2 = sc.nextLine();
												acesso2.setSenha(resposta2);
											}else {
												System.out.println("Senha incorreta.");
											}
										}
										else if ("d".equalsIgnoreCase(resposta)) {
											System.out.println("Seu sal�rio atualmente � de R$" + acesso2.getSalario() + ".");
										}
										else if ("e".equalsIgnoreCase(resposta)) {
											System.out.println("Hist�rico de acesso/Dias de trabalho: ");
											for (int j = 0; j < acesso2.getDiasDeTrabalho().size(); j++) {
												System.out.println("-" + acesso2.getDiasDeTrabalho().get(j));
											}
										}
										else if ("f".equalsIgnoreCase(resposta)) {
											System.out.println("Hist�rico de alertas: ");
											for (int j = 0; j < acesso2.getAlertas().size(); j++) {
												System.out.println("-" + acesso2.getAlertas().get(j));
												System.out.println("");
											}
										}
										else if ("g".equalsIgnoreCase(resposta)) {
											int p;
											System.out.println("Siga os passos. Tenha certeza de preencher as informa��es corretas.");
											System.out.println("Digite o nome do cliente: ");
											resposta2 = sc.nextLine();
											p = Controlador.buscarCliente(resposta2);
											if (p > -1 && resposta2.equalsIgnoreCase("Cancelar") == false) {
												System.out.println("Agora digite o dia e a hora da compra feita. Formatada da seguinte maneira: dd/MM/yyyy HH:mm:ss");
												String dataEHora = sc.nextLine();
												DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
												for (int o = 0; o < Controlador.getClientes().size(); o++) {
													if ((Controlador.getClientes().get(p).getNF().get(o).getHorarioDoPagamento().format(formatter)).equalsIgnoreCase(dataEHora)) {
														acesso2.invalidarPedido((Controlador.getClientes().get(p).getNF().get(o)));
													}
												}
											}
										}
										else if ("h".equalsIgnoreCase(resposta)) {
											continuar4 = false;
										}
										else {
											System.out.println("Op��o inv�lida.");
										}
									}
								}	
								else if (senha.equalsIgnoreCase("Cancelar")) {
									continuar3 = false;
									continuar4 = false;
								}
								else {
									System.out.println("Senha incorreta. Tente novamente ou digite 'Cancelar'.");
								}
							}
							else if (nomeDeCadastro.equalsIgnoreCase("Cancelar")) {
								continuar3 = false;
							}
							else {
								System.out.println("N�o foi encontrado nenhum funcion�rio com este nome. Tente novamente ou digite 'Cancelar'.");
							}
						}
					}
					// FUNCION�RIO N�O FINANCEIRO
					else if ("b".equalsIgnoreCase(digitado2)) {
						while (continuar3) {
							System.out.println("Digite seu nome (Caso seja seu primeiro acesso ao programa digite seu nome exatamente como cadastrado pelo gerente): ");
							String nomeDeCadastro = sc.nextLine();
							if (Controlador.getFuncionario(nomeDeCadastro) != null && Controlador.getFuncionario(nomeDeCadastro).verificacaoDePrimeiraVez) {
								Funcionario acesso2 = Controlador.getFuncionario(nomeDeCadastro);
								System.out.println("Digite a senha que deseja para ter acesso ao sistema: ");
								senha = sc.nextLine();
								acesso2.setSenha(senha);
								acesso2.setVerificacaoDePrimeiraVez(false);
							}
							else if (Controlador.getFuncionario(nomeDeCadastro) != null && Controlador.getFuncionario(nomeDeCadastro).getTipo().equalsIgnoreCase("Normal") && Controlador.getFuncionario(nomeDeCadastro).verificacaoDePrimeiraVez == false) {
								Funcionario acesso2 = Controlador.getFuncionario(nomeDeCadastro);
								System.out.println("Digite sua senha: ");
								senha = sc.nextLine();
								if (acesso2.getSenha().equals(senha)) {
									while (continuar4) {
										continuar3 = false;
										acesso2.avisarChegada();
										System.out.println("Bem-vindo "+ acesso2.getNome());
										System.out.println("A) Ver op��es de produtos");
										System.out.println("B) Mudar seu nome");
										System.out.println("C) Mudar sua senha");
										System.out.println("D) Ver seu sal�rio atual");
										System.out.println("E) Ver dias de trabalho");
										System.out.println("F) Ver Alertas");
										System.out.println("G) Sair");
										resposta = sc.nextLine();
										if ("a".equalsIgnoreCase(resposta)) {
											System.out.println("Produtos: ");
											for (int j = 0; j < Controlador.listarProdutos().size(); j++) {
												System.out.println("-" + Controlador.getProdutos().get(j).getNome() + " - R$ " + Controlador.getProdutos().get(j).getPreco() + " - Qtd. " + Controlador.getProdutos().get(j).getQuantidadeDoProduto() + " (" + Controlador.getProdutos().get(j).getDescricao() + ")");
											}
										}
										else if ("b".equalsIgnoreCase(resposta)) {
											System.out.println("Digite o nome desejado: ");
											resposta2 = sc.nextLine();
											acesso2.setNome(resposta2);
										}
										else if ("c".equalsIgnoreCase(resposta)) {
											System.out.println("Digite sua senha atual: ");
											resposta2 = sc.nextLine();
											if (resposta2.equals(acesso2.getSenha())) {
												System.out.println("Agora digite a senha que deseja: ");
												resposta2 = sc.nextLine();
													acesso2.setSenha(resposta2);
											}else {
												System.out.println("Senha incorreta.");
											}
										}
										else if ("d".equalsIgnoreCase(resposta)) {
											System.out.println("Seu sal�rio atualmente � de R$" + acesso2.getSalario() + ".");
										}
										else if ("e".equalsIgnoreCase(resposta)) {
											System.out.println("Hist�rico de acesso/Dias de trabalho: ");
											for (int j = 0; j < acesso2.getDiasDeTrabalho().size(); j++) {
												System.out.println("-" + acesso2.getDiasDeTrabalho().get(j));
											}
										}
										else if ("f".equalsIgnoreCase(resposta)) {
											System.out.println("Hist�rico de alertas: ");
											for (int j = 0; j < acesso2.getAlertas().size(); j++) {
												System.out.println("-" + acesso2.getAlertas().get(j));
												System.out.println("");
											}
										}
										else if ("g".equalsIgnoreCase(resposta)) {
											continuar4 = false;
										}
										else {
											System.out.println("Op��o inv�lida.");
										}
									}
								}
								else if (senha.equalsIgnoreCase("Cancelar")) {
									continuar3 = false;
									continuar4 = false;
								}
								else {
									System.out.println("Senha incorreta. Tente novamente ou digite 'Cancelar'.");
								}	
							}
							else {
								System.out.println("N�o foi encontrado nenhum funcion�rio com este nome. Tente novamente ou digite 'Cancelar'.");
							}
						}
					}
					else if ("Cancelar".equalsIgnoreCase(digitado2)) {
						continuar2 = false;
					}
					else {
						System.out.println("Op��o inv�lida. Para sair digite 'Cancelar'.");
					}
				}
			}
			//CLIENTE
			else if ("c".equalsIgnoreCase(digitado)) {
				while (continuar2) {
					System.out.println("Ol�, cliente � a sua primeira vez aqui?");
					System.out.println("A) Sim");
					System.out.println("B) N�o");
					resposta = sc.nextLine();
					if (resposta.equalsIgnoreCase("A")) {
						System.out.println("Pois bem, fa�amos seu cadastro, digite seu nome: ");
						String nome = sc.nextLine();
						if (nome.equalsIgnoreCase("Cancelar") == false) {
							System.out.println("Digite uma senha: ");
							senha = sc.nextLine();
							if ("Cancelar".equalsIgnoreCase(senha) == false) {
								Controlador.adicionarCliente(nome,senha);
								System.out.println("Cadastro feito com sucesso.");
							}
							else {
								continuar2 = false;
							}
						}
						else {
							continuar2 = false;
						}
					}
					else if (resposta.equalsIgnoreCase("B")) {
						while (continuar2) {
							System.out.println("LOGIN");
							System.out.println("Digite seu nome: ");
							String nome = sc.nextLine();
							if (nome.equalsIgnoreCase("cancelar") == false) {
								System.out.println("Digite sua senha: ");
								senha = sc.nextLine();
								if (senha.equalsIgnoreCase("cancelar")) {
									continuar2 = false;
								}
							}
							else {
								continuar2 = false;
							}
							int i;
							if ((i = Controlador.buscarCliente(nome)) != -1 && Controlador.getClientes().get(i).senha.equals(senha)) {
								while (continuar3) {
									continuar2 = false;
									continuar4 = true;
									Cliente acesso = Controlador.getClientes().get(i);
									System.out.println("Bem vindo, " + acesso.getNome() + ". O que gostaria de fazer?");
									System.out.println("A) Fazer pedidos");
									System.out.println("B) Mudar seu nome");
									System.out.println("C) Mudar sua senha");
									System.out.println("D) Ver suas notas fiscais");
									System.out.println("E) Sair");
									System.out.println("Escolha: ");
									resposta2 = sc.nextLine();
									if ("a".equalsIgnoreCase(resposta2)) {
										int valor=0;
										System.out.println("Produtos: ");
										for (int j = 0; j < Controlador.listarProdutos().size(); j++) {
											System.out.println((j+1) + " - " + Controlador.getProdutos().get(j).getNome() + " - R$ " + Controlador.getProdutos().get(j).getPreco() + " - Qtd. " + Controlador.getProdutos().get(j).getQuantidadeDoProduto() + " (" + Controlador.getProdutos().get(j).getDescricao() + ")");
										}
										System.out.println("Para comprar digite o n�mero a esquerda do produto desejado (Digite apenas n�meros).");
										while (continuar4) {
											System.out.println("Qual produto deseja comprar? Escolha:");
											int respostaInt = sc.nextInt();
											if (respostaInt > -1 && respostaInt < Controlador.getProdutos().size()+1) {
												valor += Controlador.getProdutos().get(respostaInt - 1).getPreco();
											}
											System.out.println("Deseja continuar? A) Sim B) N�o");
											resposta3 = sc.nextLine();
											if ("b".equalsIgnoreCase(resposta3)) {
												continuar4 = false;
											}
										}
									}
									else if (resposta2.equalsIgnoreCase("C")) {
										System.out.println("Digite sua senha atual: ");
										resposta3 = sc.nextLine();
										if (resposta3.equals(acesso.getSenha())) {
											System.out.println("Agora digite a senha que deseja: ");
											resposta3 = sc.nextLine();
											acesso.setSenha(resposta3);
										}
										else {
											System.out.println("Senha incorreta.");
										}
									}
									else if (resposta2.equalsIgnoreCase("B")) {
										System.out.println("Digite o nome desejado: ");
										resposta3 = sc.nextLine();
										acesso.setNome(resposta3);
									}
									else if (resposta2.equalsIgnoreCase("D")) {
										System.out.println("Notas fiscais");
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
										for (int j = 0; j < acesso.getNF().size(); j++) {
											System.out.println((j+1) + "Valor total: " + acesso.getNF().get(j).getValor() + "/ Efetuada no hor�rio " + acesso.getNF().get(j).getHorarioDoPagamento().format(formatter));
											for (int u = 0;u<acesso.getNF().get(j).getProdutosComprados().size(); u++) {
												System.out.println("- "+acesso.getNF().get(j).getProdutosComprados().get(u).getNome() + "(" + acesso.getNF().get(j).getProdutosComprados().get(u).getPreco() + ")");
											}
											System.out.println("");
										}
										
									}
									else if (resposta2.equalsIgnoreCase("E")) {
										continuar3 = false;
									}
									else {
										System.out.println("Op��o inv�lida.");
									}
								}
							}
						}
					}
					else {
						System.out.println("Op��o inv�lida, para sair digite 'Cancelar'.");
					}
				}
			}
			else if ("d".equalsIgnoreCase(digitado)) {
				continuar = false;
			}
			else {
				System.out.println("Op��o inv�lida.");
			}
		}
	}
}