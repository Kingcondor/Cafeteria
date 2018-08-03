package view;

import java.util.ArrayList;
import java.util.Scanner;

import enums.Tipo;
import model.ControladorFacade;

public class Terminal
{
	ControladorFacade controlador;
	
	public Terminal (ControladorFacade controlador)
	{
		this.controlador = controlador;
	}
	
	public void iniciar()
	{
		ArrayList<String> erros = controlador.verificarErros();
		if (erros.size() > 0)
		{
			for (String e : erros)
			{
				System.out.println (e);
			}
			System.out.println ("\n");
			return;
		}
		System.out.println ("Bem-vindo ao aplicativo de Cafeterias");
		Scanner scanner = new Scanner (System.in);
		String resposta;
		//Primeiro ínicio
		if (controlador.verificarPrimeiroInicio())
		{
			boolean iniciandoTudo = true;
			while (iniciandoTudo)
			{
				System.out.println ("Como esta é sua primeira vez no aplicativo, por favor, digite o seu nome");
				try
				{
					controlador.primeiroInicio (scanner.nextLine());
					System.out.println ("Digite o nome da cafeteria, o endereço e número de contato do estabelecimento, respectivamente");
					String nome = scanner.nextLine();
					String endereco = scanner.nextLine();
					String numeroTelefone = scanner.nextLine();
					System.out.println ("Nome: " + nome);
					System.out.println ("Endereço: " + endereco);
					System.out.println ("Número de telefone: "+ numeroTelefone);
					System.out.println ("Estes dados estão corretos? (Estes dados podem ser mudados posteriormente)" );
					System.out.println ("S) Sim");
					System.out.println ("N) Não");
					resposta = scanner.nextLine();
					if (resposta.equalsIgnoreCase ("S"))
					{
						try
						{
							controlador.adicionarCafeteria(nome, endereco, numeroTelefone);
							System.out.println ("Cafeteria criada com sucesso");
							System.out.println ("Agora, por favor, insira uma senha de acesso para sua conta de gerente principal");
							try
							{
								controlador.criarSenha(scanner.nextLine());
								System.out.println ("Cadastro realizado com sucesso");
								iniciandoTudo = false;
							}
							catch (Exception erro)
							{
								System.out.println ("Houve um erro ao tentar criar uma senha, chame o programador");
								try
								{
									controlador.registrarErro (erro);
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
						}
						catch (Exception erro)
						{
							System.out.println ("Houve um erro ao tentar adicionar uma cafeteria, chame o programador");
							try
							{
								controlador.registrarErro (erro);
							}
							catch (Exception erro2)
							{
								this.mostrarErro (erro2);
							}
						}
					}
					else
					{
						System.out.println ("Reinsira os dados");
					}
				}
				catch (Exception erro)
				{
					System.out.println ("Houve um erro ao tentar cadastrar uma conta para você, chame o programador");
					try
					{
						controlador.registrarErro (erro);
					}
					catch (Exception erro2)
					{
						this.mostrarErro (erro2);
					}
				}
			}
		}
		boolean rodando = true;
		while (rodando)
		{
			//Login
			boolean logando = true;
			while (logando)
			{
				System.out.println ("Você é um cliente? (S/N)");
				if (scanner.nextLine().equalsIgnoreCase ("S"))
				{
					System.out.println ("Qual seu nome?");
					String nome = scanner.nextLine();
					if (controlador.prepararLogin(nome) == 1)
					{
						System.out.println ("Bem vindo, senhor(a) " + nome);
						System.out.println ("Diga a senha");
						while (!controlador.logar(scanner.nextLine()))
						{
							System.out.println ("Senha errada, tente de novo");
						}
						logando = false;
					}
					else
					{
						System.out.println ("Você não tem conta, por favor diga uma senha");
						String senha = scanner.nextLine();
						try
						{
							controlador.cadastrar(nome, senha, Tipo.CLIENTE);
							System.out.println ("Conta criada com sucesso");
							logando = false;
						}
						catch (Exception erro)
						{
							System.out.println ("Houve um erro ao tentar cadastrar uma conta para você, chame o programador");
							try
							{
								controlador.registrarErro (erro);
							}
							catch (Exception erro2)
							{
								this.mostrarErro (erro2);
							}
						}
					}
				}
				else
				{
					System.out.println ("Diga o nome para login ou para criar senha (Digite \"Sair\" para cancelar o login como funcionário)");
					resposta = scanner.nextLine();
					if (!resposta.equalsIgnoreCase ("Sair"))
					{
						int tentarLogin = controlador.prepararLogin(resposta);
						if (tentarLogin == 2)
						{
							System.out.println ("Você ainda não tem senha, diga uma");
							String senha = scanner.nextLine();
							try
							{
								controlador.criarSenha(senha);
								System.out.println ("Senha criada com sucesso");
								logando = false;
							}
							catch (Exception erro)
							{
								System.out.println ("Houve um erro ao tentar criar uma senha, chame o programador");
								try
								{
									controlador.registrarErro (erro);
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
						}
						else if (tentarLogin == 1)
						{
							System.out.println ("Diga a senha");
							while (!controlador.logar(scanner.nextLine()))
							{
								System.out.println ("Senha errada, tente de novo");
							}
							logando = false;
						}
						else
						{
							System.out.println ("Não existe funcionário registrado com esse nome, tente de novo");
						}
					}
				}
			}
			boolean logado = true;
			if (controlador.verificarTipo (Tipo.COMUM) || controlador.verificarTipo (Tipo.ATENDENTE) || controlador.verificarTipo (Tipo.GERENTE) || controlador.verificarTipo (Tipo.GERENTE_PRINCIPAL))
			{
				try
				{
					controlador.avisarChegada();
					ArrayList<String[]> alertas = controlador.pegarAlertas();
					if (alertas.size() > 0)
					{
						System.out.println ("Alertas:\n");
						for (String[] a : alertas)
						{
							System.out.println ("De: " + a[0] + " em " + a[2] + "\n\nAssunto: " + a[1] + "\n\n" + a[3] + "\n\n\n");
						}
					}
					String opcoesFuncionario[] = 
						{"Mudar sua senha", "Ver seu salário atual", "Ver seu número de telefone atual", "Mudar seu número de telefone atual"};
					int opcao = 0, opcaoVerificada = 0;
					int maximo = opcoesFuncionario.length;
					while (logado)
					{
						System.out.println ("O que deseja fazer, senhor(a) " + controlador.pegarNome() + "?");
						for (int i = 0; i < opcoesFuncionario.length; i++)
						{
							System.out.println (i+1 + ") " + opcoesFuncionario[i]);
						}
						if (controlador.verificarTipo (Tipo.ATENDENTE))
						{
							maximo++;
							System.out.println (maximo + ") Ver quantidade de produtos em estoque");
							maximo++;
							System.out.println (maximo + ") Ver seu histórico de vendas");
						}
						if (controlador.verificarTipo (Tipo.GERENTE) || controlador.verificarTipo (Tipo.GERENTE_PRINCIPAL))
						{
							String opcoesGerente[] = {"Cadastrar novo funcionário", "Demitir funcionário", "Mudar nome de funcionário", "Verificar salário de funcionário", "Mudar salário de funcionário", "Mudar seu salário", "Emitir alerta para funcionário", "Ver quantidade de produtos em estoque", "Adicionar produto ao estoque", "Retirar produto do estoque", "Ver quantidade de vendas por dia", "Ver CPF de funcionário", "Informar CPF de funcionário", "Ver endereço de funcionário", "Informar endereço de funcionário", "Ver número de telefone de funcionário", "Verificar presença de funcionário por data", "Ver lista de funcionários", "Cancelar pedido", "Aumentar estoque de algum produto", "Mudar nome de produto", "Mudar preço de produto", "Mudar descrição de produto"};
							for (int i = maximo; i < maximo+opcoesGerente.length; i++)
							{
								System.out.println (i+1 + ") " + opcoesGerente[i-maximo]);
							}
							maximo += opcoesGerente.length+1;
							if (controlador.verificarTipo (Tipo.GERENTE_PRINCIPAL))
							{
								String opcoesGerentePrincipal[] = {"Listar cafeterias", "Criar nova cafeteria", "Mudar nome de cafeteria", "Mudar endereço de cafeteria", "Mudar número de telefone da cafeteria", "Remover cafeteria"};
								for (int i = maximo; i < maximo+opcoesGerentePrincipal.length; i++)
								{
									System.out.println (i+1 + ") " + opcoesGerentePrincipal[i-maximo]);
								}
								maximo += opcoesGerentePrincipal.length+1;
							}
						}
						System.out.println (maximo+1 + ") Deslogar");
						System.out.println (maximo+2 + ") Fechar programa");
						boolean naoENumero = true;
						while (naoENumero)
						{
							try
							{
								opcao = Integer.parseInt(scanner.nextLine());
								naoENumero = false;
							}
							catch (NumberFormatException erro)
							{
								System.out.println ("A resposta deve ser um número");
							}
						}
						if (opcao == ++opcaoVerificada)
						{
							System.out.println ("Diga o novo nome");
							controlador.mudarNome (scanner.nextLine());
							System.out.println ("Seu nome atual é: " + controlador.pegarNome());
						}
						else if (opcao == ++opcaoVerificada)
						{
							System.out.println ("Diga a senha atual");
							String atual = scanner.nextLine();
							System.out.println ("Diga a nova senha");
							try
							{
								while (!controlador.mudarSenha (atual, scanner.nextLine()))
								{
									System.out.println ("Senha errada, tente de novo:");
									System.out.println ("Diga a senha atual");
									atual = scanner.nextLine();
									System.out.println ("Diga a nova senha");
								}
								System.out.println ("Senha mudada com sucesso");
							}
							catch (Exception erro)
							{
								System.out.println ("Houve um erro ao tentar mudar sua senha, chame o programador");
								try
								{
									controlador.registrarErro (erro);
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
						}
						else if (opcao == ++opcaoVerificada)
						{
							System.out.println ("Seu salário atual é: " + controlador.pegarSalario());
						}
						else if (opcao == ++opcaoVerificada)
						{
							try
							{
								System.out.println ("Seu número de telefone atual é: " + controlador.pegarNumeroTelefone());
							}
							catch(NullPointerException erro)
							{
								System.out.println ("Você não tem número de telefone registrado");
							}
						}
						else if (opcao == ++opcaoVerificada)
						{
							System.out.println ("Diga o novo número de telefone");
							try
							{
								controlador.mudarNumeroTelefone(scanner.nextLine());
								System.out.println ("Seu novo número de telefone é: " + controlador.pegarNumeroTelefone());
							}
							catch (Exception erro)
							{
								System.out.println ("Houve um erro ao tentar mudar seu número de telefone, chame o programador");
								try
								{
									controlador.registrarErro (erro);
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
						}
						if (controlador.verificarTipo (Tipo.ATENDENTE))
						{
							if (opcao == ++opcaoVerificada)
							{
								ArrayList<String[]> produtos = controlador.listarProdutos();
								if (produtos.size() == 0)
								{
									System.out.println ("Não há produtos no estoque");
								}
								else
								{
									System.out.println ("Produtos em estoque:\n\n");
									for (String[] p : produtos)
									{
										System.out.println ("Nome: " + p[0] + " Preço: " + p[1] + " Descrição: " + p[2] + " Quantidade em estoque: " + p[3]);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								ArrayList<String[]> vendas = controlador.verHistoricoVendas();
								if (vendas.size() == 0)
								{
									System.out.println ("Você não fez vendas ainda");
								}
								else
								{
									System.out.println ("Histórico de vendas:\n\n");
									for (String[] v : vendas)
									{
										System.out.println ("Cliente: " + v[0] + "\nData: " + v[1] + "\nGanho: " + v[2] + "\n\n");
									}
								}
							}
						}
						if (controlador.verificarTipo (Tipo.GERENTE) || controlador.verificarTipo (Tipo.GERENTE_PRINCIPAL))
						{
							if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga se o funcionário é:\n1) Gerente\n2) Atendente\n3) Comum");
								int tipo = 0;
								boolean naoENumero2 = true;
								while (naoENumero2)
								{
									try
									{
										tipo = Integer.parseInt(scanner.nextLine());
										naoENumero2 = false;
									}
									catch (NumberFormatException erro)
									{
										System.out.println ("A resposta deve ser um número");
									}
								}
								boolean escolhendoTipo = true;
								while (tipo > 3 && escolhendoTipo)
								{
									System.out.println ("Opção maior do que a disponivel, o máximo é 3");
									System.out.println ("Digite 0 caso queira desistir da operação");
									boolean naoENumero3 = true;
									while (naoENumero3)
									{
										try
										{
											tipo = Integer.parseInt(scanner.nextLine());
											naoENumero3 = false;
										}
										catch (NumberFormatException erro)
										{
											System.out.println ("A resposta deve ser um número");
										}
									}
									if (tipo == 0)
									{
										escolhendoTipo = false;
									}
									else
									{
										if (tipo > 0 && tipo <= 3)
										{
											System.out.println ("Diga o nome do funcionário");
											resposta = scanner.nextLine();
											if (!this.controlador.verificarIgual (resposta))
											{
												try
												{
													if (tipo == 0)
													{
														controlador.cadastrar (scanner.nextLine(), Tipo.GERENTE);
													}
													else if (tipo == 1)
													{
														controlador.cadastrar (scanner.nextLine(), Tipo.ATENDENTE);
													}
													else if (tipo == 2)
													{
														controlador.cadastrar (scanner.nextLine(), Tipo.COMUM);	
													}
													escolhendoTipo = false;
													System.out.println ("Cadastro feito com sucesso");
												}
												catch (Exception erro)
												{
													System.out.println ("Houve um erro ao tentar cadastrar o funcionário, chame o programador");
													try
													{
														controlador.registrarErro (erro);
													}
													catch (Exception erro2)
													{
														this.mostrarErro (erro2);
													}
												}
											}
											else
											{
												System.out.println("Já existe funcionário com esse nome");
											}
										}
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga o nome do funcionário");
								try
								{
									controlador.descadastrar(scanner.nextLine());
									System.out.println ("Funcionário demitido com sucesso");
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
								catch (Exception erro)
								{
									System.out.println ("Houve um erro ao tentar descadastrar o funcionário, chame o programador");
									try
									{
										controlador.registrarErro (erro);
									}
									catch (Exception erro2)
									{
										this.mostrarErro (erro2);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								System.out.println ("Diga o novo nome");
								try
								{
									String novoNome = controlador.mudarNomeFuncionario (resposta, scanner.nextLine());
									System.out.println ("Nome mudado com sucesso, agora é " + novoNome);
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								try
								{
									System.out.println ("O salário de " + resposta + " é R$" + controlador.pegarSalario(resposta));
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								System.out.println ("Diga o novo valor do salário");
								try
								{
									controlador.mudarSalario (resposta, Double.parseDouble(scanner.nextLine()));
									System.out.println ("Salário foi mudado com sucesso, agora é R$" + controlador.pegarSalario(resposta));
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga o novo salário");
								try
								{
									controlador.mudarSalario (controlador.pegarNome(), Double.parseDouble(scanner.nextLine()));
									System.out.println ("Seu salário atual é: " + controlador.pegarSalario());
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								System.out.println ("Diga a mensagem");
								try
								{
									controlador.emitirAlerta (resposta, scanner.nextLine());
									System.out.println("Alerta emitido");
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
								catch (Exception erro)
								{
									System.out.println("Houve um erro ao tentar enviar o alerta, chame o programador");
									try
									{
										this.controlador.registrarErro (erro);
									}
									catch (Exception erro2)
									{
										this.mostrarErro (erro2);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								ArrayList<String[]> produtos = controlador.listarProdutos();
								if (produtos.size() == 0)
								{
									System.out.println ("Não há produtos no estoque");
								}
								else
								{
									System.out.println("Produtos em estoque:\n\n");
									for (String[] p : produtos)
									{
										System.out.println ("Nome: " + p[0] + " Preço: " + p[1] + " Descrição: " + p[2] + " Quantidade em estoque: " + p[3]);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga o nome do produto");
								resposta = scanner.nextLine();
								System.out.println ("Diga o preço do produto");
								double preco = Double.parseDouble(scanner.nextLine());
								System.out.println ("Diga uma descrição para o produto");
								String descricao = scanner.nextLine();
								System.out.println ("Diga a quantidade do produto");
								try
								{
									controlador.adicionarProduto(resposta, preco, descricao, Integer.parseInt(scanner.nextLine()));
									System.out.println("Produto adicionado com sucesso");
								}
								catch (Exception erro)
								{
									System.out.println("Houve um erro ao tentar adicionar produto, chame o programador");
									try
									{
										controlador.registrarErro (erro);
									}
									catch (Exception erro2)
									{
										this.mostrarErro (erro2);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga o nome do produto");
								try
								{
									controlador.removerProduto(scanner.nextLine());
									System.out.println("Produto removido com sucesso");
								}
								catch (Exception erro)
								{
									System.out.println("Houve um erro ao tentar adicionar produto, chame o programador");
									try
									{
										controlador.registrarErro (erro);
									}
									catch (Exception erro2)
									{
										this.mostrarErro (erro2);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								ArrayList<String[]> datas = controlador.pegarVendasDiarias();
								if (datas.size() == 0)
								{
									System.out.println ("Não houveram vendas ainda");
								}
								else
								{
									System.out.println("Vendas:\n\n");
									for (String[] d : datas)
									{
										System.out.println ("Data: " + d[0] + "\nCliente: " + d[1] + "\nGanho com vendas: " + d[2] + "\n");
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								try
								{
									System.out.println ("O CPF de " + resposta + " é " + controlador.pegarCpf(resposta));
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								System.out.println ("Diga o CPF do funcionário");
								try {
									controlador.definirCpf(resposta, scanner.nextLine());
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								try
								{
									System.out.println ("O endereço de " + resposta + " é " + controlador.pegarEndereco(resposta));
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								System.out.println ("Diga o endereço do funcionário no formato \"número, rua - bairro - cidade - estado\"");
								try
								{
									controlador.definirEndereco(resposta, scanner.nextLine());
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								System.out.println ("Diga a data que deseja verificar");
								String data = scanner.nextLine();
								try
								{
									if (controlador.verificarPresenca (resposta, data))
									{
										System.out.println ("Na data: " + data + ", o funcionário " + resposta + " esteve presente");
									}
									else
									{
										System.out.println ("Na data: " + data + ", o funcionário " + resposta + " faltou");
									}
									
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do funcionário");
								resposta = scanner.nextLine();
								try
								{
									System.out.println ("O número de telefone de " + resposta + " é " + controlador.pegarNumeroTelefone(resposta));
								}
								catch (NullPointerException erro)
								{
									this.funcionarioInex();
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								ArrayList<String[]> funcionarios = controlador.listarFuncionarios();
								if (funcionarios.size() == 0)
								{
									System.out.println ("Não há funcionários ainda");
								}
								else
								{
									System.out.println("Funcionários:\n\n");
									for (String[] f : funcionarios)
									{
										System.out.println ("Nome: " + f[0] + " Cargo: " + f[1]);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga a data");
								resposta = scanner.nextLine();
								try
								{
									this.controlador.cancelarPedido (resposta);
									System.out.println("Pedido cancelado com sucesso");
								}
								catch (NullPointerException erro)
								{
									System.out.println("Cliente não encontrado");
								}
								catch (Exception erro)
								{
									System.out.println("Houve um erro ao tentar cancelar o pedido, chame o programador");
									try
									{
										this.controlador.registrarErro (erro);
									}
									catch (Exception erro2)
									{
										this.mostrarErro (erro2);
									}
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do produto");
								resposta = scanner.nextLine();
								System.out.println ("Diga a quantidade a acrescentar");
								try
								{
									controlador.aumentarQuantidadeProduto (resposta, Integer.parseInt(scanner.nextLine()));
								}
								catch (NullPointerException erro)
								{
									System.out.println("Não existe produto com esse nome");
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do produto");
								resposta = scanner.nextLine();
								System.out.println ("Diga o novo nome");
								try
								{
									controlador.mudarNomeProduto (resposta, scanner.nextLine());
								}
								catch (NullPointerException erro)
								{
									System.out.println("Não existe produto com esse nome");
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do produto");
								resposta = scanner.nextLine();
								System.out.println ("Diga o novo preço");
								try
								{
									controlador.mudarPrecoProduto (resposta, Double.parseDouble(scanner.nextLine()));
								}
								catch (NullPointerException erro)
								{
									System.out.println("Não existe produto com esse nome");
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							else if (opcao == ++opcaoVerificada)
							{
								System.out.println ("Diga nome do produto");
								resposta = scanner.nextLine();
								System.out.println ("Diga a nova descrição");
								try
								{
									controlador.mudarDescricaoProduto (resposta, scanner.nextLine());
								}
								catch (NullPointerException erro)
								{
									System.out.println("Não existe produto com esse nome");
								}
								catch (Exception erro2)
								{
									this.mostrarErro (erro2);
								}
							}
							if (controlador.verificarTipo (Tipo.GERENTE_PRINCIPAL))
							{
								if (opcao == ++opcaoVerificada)
								{
									ArrayList<String[]> cafeterias = controlador.listarCafeterias();
									System.out.println("Cafeterias:\n\n");
									for (String[] c : cafeterias)
									{
										System.out.println ("Nome: " + c[0] + "\nEndereço: " + c[1] + "\nNúmero de telefone: " + c[2] + "\n\n");
									}
								}
								else if (opcao == ++opcaoVerificada)
								{
									System.out.println ("Diga o nome da cafeteria");
									String nome = scanner.nextLine();
									System.out.println ("Diga o endereço da cafeteria");
									String endereco = scanner.nextLine();
									if (!this.controlador.verificarCafeteriaIgual (endereco))
									{
										System.out.println ("Diga o número de telefone da cafeteria");
										try
										{
											controlador.adicionarCafeteria (nome, endereco, scanner.nextLine());
											System.out.println ("Cafeteria criada com sucesso");
										}
										catch (Exception erro)
										{
											System.out.println ("Houve um erro ao tentar adicionar a cafeteria, chame o programador");
											try
											{
												controlador.registrarErro (erro);
											}
											catch (Exception erro2)
											{
												this.mostrarErro (erro2);
											}
										}
									}
									else
									{
										System.out.println ("Já existe uma cafeteria nesse endereço, cancelando...");
									}
								}
								else if (opcao == ++opcaoVerificada)
								{
									System.out.println ("Diga o endereco da cafeteria");
									String endereco = scanner.nextLine();
									System.out.println ("Diga o novo nome da cafeteria");
									try
									{
										controlador.mudarNomeCafeteria (endereco, scanner.nextLine());
										System.out.println ("Nome da cafeteria mudado com sucesso");
									}
									catch (Exception erro)
									{
										System.out.println ("Houve um erro ao tentar mudar o nome da cafeteria, chame o programador");
										try
										{
											controlador.registrarErro (erro);
										}
										catch (Exception erro2)
										{
											this.mostrarErro (erro2);
										}
									}
								}
								else if (opcao == ++opcaoVerificada)
								{
									System.out.println ("Diga o endereco da cafeteria");
									String endereco = scanner.nextLine();
									System.out.println ("Diga o novo endereço da cafeteria");
									try
									{
										controlador.mudarEnderecoCafeteria (endereco, scanner.nextLine());
										System.out.println ("Endereço da cafeteria mudado com sucesso");
									}
									catch (Exception erro)
									{
										System.out.println ("Houve um erro ao tentar mudar o endereço da cafeteria, chame o programador");
										try
										{
											controlador.registrarErro (erro);
										}
										catch (Exception erro2)
										{
											this.mostrarErro (erro2);
										}
									}
								}
								else if (opcao == ++opcaoVerificada)
								{
									System.out.println ("Diga o endereco da cafeteria");
									String endereco = scanner.nextLine();
									System.out.println ("Diga o novo número de telefone da cafeteria");
									try
									{
										controlador.mudarNumeroTelefoneCafeteria (endereco, scanner.nextLine());
										System.out.println ("Número de telefone da cafeteria mudado com sucesso");
									}
									catch (Exception erro)
									{
										System.out.println ("Houve um erro ao tentar mudar o número de telefone da cafeteria, chame o programador");
										try
										{
											controlador.registrarErro (erro);
										}
										catch (Exception erro2)
										{
											this.mostrarErro (erro2);
										}
									}
								}
								else if (opcao == ++opcaoVerificada)
								{
									System.out.println ("Diga o endereco da cafeteria");
									try
									{
										controlador.removerCafeteria (scanner.nextLine());
										System.out.println ("Cafeteria removida com sucesso");
									}
									catch (Exception erro)
									{
										System.out.println ("Houve um erro ao tentar remover a cafeteria, chame o programador");
										try
										{
											controlador.registrarErro (erro);
										}
										catch (Exception erro2)
										{
											this.mostrarErro (erro2);
										}
									}
								}
							}
						}
						if (opcao == maximo+1)
						{
							controlador.deslogar();
							logado = false;
						}
						else if (opcao == maximo+2)
						{
							logado = false;
							rodando = false;
						}
						else if (opcao > maximo+2)
						{
							System.out.println ("Opção inválida, a opção máxima é " + maximo+2);
						}
					}
				}
				catch (Exception erro)
				{
					System.out.println ("Houve um erro ao tentar avisar sua chegada, chame o programador");
					try
					{
						controlador.registrarErro (erro);
					}
					catch (Exception erro2)
					{
						this.mostrarErro (erro2);
					}
				}
			}
			else if (controlador.verificarTipo (Tipo.CLIENTE))
			{
				String[] opcoesCliente = {"Fazer pedido", "Mudar seu nome", "Mudar sua senha"};
				int opcao = 0, opcaoVerificada = 0;
				int maximo = opcoesCliente.length;
				while (logado)
				{
					System.out.println ("O que deseja fazer, senhor(a) " + controlador.pegarNome() + "?");
					for (int i = 0; i < opcoesCliente.length; i++)
					{
						System.out.println (i+1 + ") " + opcoesCliente[i]);
					}
					System.out.println (maximo+1 + ") Deslogar");
					System.out.println (maximo+2 + ") Fechar programa");
					boolean naoENumero = true;
					while (naoENumero)
					{
						try
						{
							opcao = Integer.parseInt(scanner.nextLine());
							naoENumero = false;
						}
						catch (NumberFormatException erro)
						{
							System.out.println ("A resposta deve ser um número");
						}
					}
					if (opcao == ++opcaoVerificada)
					{
						ArrayList<String[]> cardapio = controlador.listarProdutos();
						if (cardapio.size() == 0)
						{
							System.out.println ("Não há produtos no estoque");
						}
						else
						{
							ArrayList<String[]> produtosComprados = new ArrayList<String[]>();
							ArrayList<Integer> quantidades = new ArrayList<Integer>();
							boolean comprando = true;
							while (comprando)
							{
								System.out.println ("Diga qual produto quer da lista abaixo:\n");
								for (int i = 0; i < cardapio.size(); i++)
								{
									System.out.println (i+1 + ") Nome: " + cardapio.get (i)[0] + "  Preço: " + cardapio.get (i)[1] + "  Descrição: " + cardapio.get (i)[2] + "  Quantidade em estoque: " + cardapio.get (i)[3]);
								}
								String[] produto;
								int produtoEscolhido = 0;
								boolean naoENumero2 = true;
								while (naoENumero2)
								{
									try
									{
										produtoEscolhido = Integer.parseInt(scanner.nextLine());
										naoENumero2 = false;
									}
									catch (NumberFormatException erro)
									{
										System.out.println ("A resposta deve ser um número");
									}
								}
								if (produtoEscolhido > 0 && produtoEscolhido <= cardapio.size())
								{
									produto = cardapio.get (produtoEscolhido-1);
									produtosComprados.add (produto);
									System.out.println ("Quanto de " + produto[0] + " vai querer? A quantidade presente no estoque é " + produto[3]);
									int quantidade = 0;
									boolean escolhendoQuantidade = true;
									while (escolhendoQuantidade)
									{
										boolean naoENumero3 = true;
										while (naoENumero3)
										{
											try
											{
												quantidade = Integer.parseInt(scanner.nextLine());
												naoENumero3 = false;
											}
											catch (NumberFormatException erro)
											{
												System.out.println ("A resposta deve ser um número");
											}
										}
										if (controlador.verificarQuantidade (Integer.parseInt (produto[3]), quantidade))
										{
											quantidades.add (quantidade);
											escolhendoQuantidade = false;
										}
										else
										{
											System.out.println ("Quantidade acima do dísponivel no estoque, diga 0 para desistir da compra, tecle 1 para tentar de novo");
											String respostaDesistir = scanner.nextLine();
											if (respostaDesistir.equals ("0"))
											{
												escolhendoQuantidade = false;
												comprando = false;
											}
											else
											{
												System.out.println ("Quanto de " + produto[0] + " vai querer? A quantidade presente no estoque é " + produto[3]);
											}
										}
									}
									produto[3] = Integer.toString (Integer.parseInt(produto[3]) - quantidades.get (quantidades.size()-1));
									if (comprando)
									{
										System.out.println ("Deseja mais algum produto? (S/N)");
										resposta = scanner.nextLine();
										if (resposta.equalsIgnoreCase ("N"))
										{
											comprando = false;
										}
									}
								}
								else
								{
									System.out.println ("Produto escolhido não existe, os produtos só vão de 1 até " + cardapio.size());
									System.out.println ("Diga 0 para desistir da compra, tecle 1 para tentar de novo");
									String respostaDesistir = scanner.nextLine();
									if (respostaDesistir.equals ("0"))
									{
										comprando = false;
									}
								}
							}
							int atendenteEscolhido = 1;
							ArrayList<String[]> atendentes = controlador.listarAtendentes();
							boolean escolhendoAtendente = true;
							while (escolhendoAtendente)
							{
								System.out.println ("Diga qual atendende quer da lista abaixo:");
								for (int i = 0; i < atendentes.size(); i++)
								{
									System.out.println (i+1 + ") " + atendentes.get (i)[0]);
								}
								boolean naoENumero2 = true;
								while (naoENumero2)
								{
									try
									{
										atendenteEscolhido = Integer.parseInt(scanner.nextLine());
										naoENumero2 = false;
									}
									catch (NumberFormatException erro)
									{
										System.out.println ("A resposta deve ser um número");
									}
								}
								if (atendenteEscolhido > atendentes.size())
								{
									System.out.println ("Atendente escolhido não existe, os atendentes só vão até " + atendentes.size());
									System.out.println ("Diga 0 para desistir da compra, tecle 1 para tentar de novo");
									String respostaDesistir = scanner.nextLine();
									if (respostaDesistir.equals ("0"))
									{
										escolhendoAtendente = false;
									}
								}
								else
								{
									escolhendoAtendente = false;
								}
							}
							if (atendenteEscolhido < atendentes.size())
							{
								String[] atendente = atendentes.get (atendenteEscolhido-1);
								try
								{
									ArrayList<String[]> nota = controlador.fazerPedido (atendente[0], produtosComprados, quantidades);
									System.out.println ("Nota Fiscal:\n");
									System.out.println ("Horário do pagamento: " + nota.get (0)[0]);
									System.out.println ("Produtos comprados:\n");
									for (int i = 1; i < nota.size()-1; i++)
									{
										System.out.println (nota.get (i)[0] + ": R$" + nota.get (i)[1]);
									}
									System.out.println ("\nGasto total: R$" + nota.get (nota.size()-1)[0]);
								}
								catch (Exception erro)
								{
									System.out.println ("Houve um erro ao tentar fazer o pedido, chame o programador");
									try
									{
										controlador.registrarErro (erro);
									}
									catch (Exception erro2)
									{
										this.mostrarErro (erro2);
									}
								}
							}
						}
					}
					else if (opcao == ++opcaoVerificada)
					{
						System.out.println ("Diga o novo nome:");
						controlador.mudarNome (scanner.nextLine());
						System.out.println ("Seu nome atual é: " + controlador.pegarNome());
					}
					else if (opcao == ++opcaoVerificada)
					{
						System.out.println ("Diga a senha atual");
						String atual = scanner.nextLine();
						System.out.println ("Diga a nova senha");
						try
						{
							while (!controlador.mudarSenha (atual, scanner.nextLine()))
							{
								System.out.println ("Senha errada, tente de novo:");
								System.out.println ("Diga a senha atual");
								atual = scanner.nextLine();
								System.out.println ("Diga a nova senha");
							}
							System.out.println ("Senha mudada com sucesso");
						}
						catch (Exception erro)
						{
							System.out.println ("Houve um erro ao tentar mudar sua senha, chame o programador");
							try
							{
								controlador.registrarErro (erro);
							}
							catch (Exception erro2)
							{
								this.mostrarErro (erro2);
							}
						}
					}
					else if (opcao == maximo+1)
					{
						controlador.deslogar();
						logado = false;
					}
					else if (opcao == maximo+2)
					{
						logado = false;
						rodando = false;
					}
					else
					{
						System.out.println ("Opção inválida, a opção máxima é " + maximo+2);
					}
				}
			}
		}
		scanner.close();
	}
	
	private void funcionarioInex()
	{
		System.out.println ("Não existe funcionário com esse nome");
	}
	
	private void mostrarErro(Exception erro)
	{
		System.out.println ("Houve um erro ao tentar registrar o erro, então ele será mostrado abaixo:\n" + erro.getMessage());
	}
}