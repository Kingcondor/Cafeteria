package model;

import java.util.ArrayList;
import java.time.LocalDate;

import enums.Tipo;

import model.beans.Cafeteria;
import model.beans.Conta;
import model.beans.Produto;

import repository.*;

public class ControladorFacade
{
	private CadastroContas cadastroContas;
	private GerenciarAlertas gerenciarAlertas;
	private GerenciarCafeterias gerenciarCafeterias;
	private GerenciarErros gerenciarErros;
	private GerenciarEstoque gerenciarEstoque;
	private Login login;
	private Transacao transacao;
	
	public ControladorFacade() throws Exception
	{
		this.cadastroContas = new CadastroContas (RepositorioContas.getInstancia());
		this.gerenciarAlertas = new GerenciarAlertas (RepositorioContas.getInstancia());
		this.gerenciarCafeterias = new GerenciarCafeterias (RepositorioCafeterias.getInstancia());
		this.gerenciarErros = new GerenciarErros (RepositorioErros.getInstancia());
		this.gerenciarEstoque = new GerenciarEstoque (RepositorioProdutos.getInstancia());
		this.login = new Login (RepositorioContas.getInstancia());
		this.transacao = new Transacao (RepositorioContas.getInstancia(), RepositorioNotasFiscais.getInstancia(), RepositorioProdutos.getInstancia());
	}
	
	public ArrayList<String> verificarErros()
	{
		return this.gerenciarErros.verificarErros();
	}
	
	public void registrarErro (Exception erro) throws Exception
	{
		this.gerenciarErros.registrarErro (erro);
	}
	
	public boolean verificarPrimeiroInicio()
	{
		return this.login.verificarPrimeiroInicio();
	}
	
	public void primeiroInicio (String nome) throws Exception
	{
		this.login.primeiroInicio (nome);
	}
	
	public int prepararLogin (String nome)
	{
		return this.login.prepararLogin (nome);
	}
	
	public Conta cadastrar (String nome, Tipo tipo) throws Exception
	{
		return this.cadastroContas.cadastrar (nome, tipo);
	}
	
	public Conta cadastrar (String nome, String senha, Tipo tipo) throws Exception
	{
		return this.cadastroContas.cadastrar (nome, senha, tipo);
	}
	
	public boolean verificarIgual (String nome)
	{
		return this.cadastroContas.verificarIgual (nome);
	}
	
	public boolean verificarCafeteriaIgual (String endereco)
	{
		return this.gerenciarCafeterias.verificarCafeteriaIgual (endereco);
	}
	
	public void descadastrar (String nome) throws Exception
	{
		this.cadastroContas.descadastrar (nome);
	}
	
	public boolean logar (String senha)
	{
		return this.login.logar (senha);
	}
	
	public boolean verificarTipo (Tipo tipo)
	{
		return this.login.verificarTipo (tipo);
	}
	
	public boolean verificarSenhaExiste()
	{
		return this.login.verificarSenhaExiste();
	}
	
	public boolean verificarSenha (String senha)
	{
		return this.login.verificarSenha (senha);
	}
	
	public double pegarSalario (String nome)
	{
		return this.cadastroContas.pegarSalario (nome);
	}
	
	public void criarSenha (String senha) throws Exception
	{
		this.cadastroContas.criarSenha (this.login.getConta(), senha);
	}
	
	public boolean mudarSenha (String senha, String senhaNova) throws Exception
	{
		return this.cadastroContas.mudarSenha (this.login.getConta(), senha, senhaNova);
	}
	
	public double pegarSalario()
	{
		return this.cadastroContas.pegarSalario (this.pegarNome());
	}
	
	public String pegarNumeroTelefone()
	{
		return this.cadastroContas.pegarNumeroTelefone (this.pegarNome());
	}
	
	public void mudarNumeroTelefone (String numeroTelefone) throws Exception
	{
		this.cadastroContas.definirNumeroTelefone (this.pegarNome(), numeroTelefone);
	}
	
	public void emitirAlerta (String assunto, String mensagem) throws Exception
	{
		this.gerenciarAlertas.emitirAlerta (this.pegarNome(), assunto, mensagem);
	}
	
	public void emitirAlerta (String funcionario, String assunto, String mensagem) throws Exception
	{
		this.gerenciarAlertas.emitirAlerta (this.pegarNome(), funcionario, assunto, mensagem);
	}
	
	public void avisarChegada() throws Exception
	{
		this.gerenciarAlertas.avisarChegada (this.pegarNome());
	}
	
	public ArrayList<String[]> pegarAlertas()
	{
		return this.gerenciarAlertas.pegarAlertas (this.pegarNome());
	}
	
	public ArrayList<String[]> verHistoricoVendas()
	{
		return this.transacao.verHistoricoVendas (this.login.getConta());
	}
	
	public void mudarSalario (String nome, Double salario) throws Exception
	{
		this.cadastroContas.mudarSalario (nome, salario);
	}
	
	public String pegarNome()
	{
		return this.login.getConta().getNome();
	}
	
	public void mudarNome (String nome)
	{
		this.login.getConta().setNome (nome);
	}
	
	public String mudarNomeFuncionario (String nomeAntigo, String novoNome) throws Exception
	{
		return this.cadastroContas.mudarNomeFuncionario (nomeAntigo, novoNome);
	}
	
	public String pegarCpf (String nome)
	{
		return this.cadastroContas.pegarCpf (nome);
	}
	
	public void definirCpf (String nome, String cpf) throws Exception
	{
		this.cadastroContas.definirCpf (nome, cpf);
	}
	
	public String pegarEndereco (String nome)
	{
		return this.cadastroContas.pegarEndereco (nome);
	}
	
	public void definirEndereco (String nome, String endereco) throws Exception
	{
		this.cadastroContas.definirEndereco (nome, endereco);
	}
	
	public String pegarNumeroTelefone (String nome)
	{
		return this.cadastroContas.pegarNumeroTelefone (nome);
	}
	
	public void definirNumeroTelefone (String nome, String numeroTelefone) throws Exception
	{
		this.cadastroContas.definirNumeroTelefone (nome, numeroTelefone);
	}
	
	public boolean verificarPresenca (String nome, LocalDate data) throws NullPointerException
	{
		return this.cadastroContas.verificarPresenca (nome, data);
	}
	
	public boolean verificarPresenca (String nome, String data) throws NullPointerException
	{
		return this.cadastroContas.verificarPresenca (nome, data);
	}
	
	public ArrayList<String[]> listarProdutos()
	{
		return this.gerenciarEstoque.listarProdutos();
	}
	
	public Produto buscarProduto (String nome)
	{
		return this.gerenciarEstoque.buscarProduto (nome);
	}
	
	public void adicionarProduto (String nome, double preco, String descricao, int quantidadeDoProduto) throws Exception
	{
		this.gerenciarEstoque.adicionarProduto (nome, preco, descricao, quantidadeDoProduto);
	}
	
	public void removerProduto (String nome) throws Exception
	{
		this.gerenciarEstoque.removerProduto (nome);
	}
	
	public boolean verificarQuantidade (int quantidadeEscolhida, int quantidadeProduto)
	{
		return this.gerenciarEstoque.verificarQuantidade (quantidadeEscolhida, quantidadeProduto);
	}
	
	public ArrayList<String[]> fazerPedido (String atendente, ArrayList<String[]> produtosString, ArrayList<Integer> quantidades) throws Exception
	{
		return this.transacao.fazerPedido (this.pegarNome(), atendente, produtosString, quantidades);
	}
	
	public void cancelarPedido (String data) throws Exception
	{
		this.transacao.cancelarPedido (data);
	}
	
	public ArrayList<String[]> listarFuncionarios()
	{
		return this.cadastroContas.listarFuncionarios();
	}
	
	public ArrayList<String[]> listarAtendentes() {
		return this.transacao.listarAtendentes();
	}
	
	public ArrayList<String[]> pegarVendasDiarias() {
		return this.transacao.pegarVendasDiarias();
	}
	
	public ArrayList<String[]> listarCafeterias () {
		return gerenciarCafeterias.listarCafeterias();
	}
	
	public Cafeteria buscarCafeteria (String endereco) {
		return gerenciarCafeterias.buscarCafeteria (endereco);
	}
	
	public Cafeteria adicionarCafeteria (String nome, String endereco, String numeroTelefone) throws Exception
	{
		return gerenciarCafeterias.adicionarCafeteria (nome, endereco, numeroTelefone);
	}
	
	public void removerCafeteria (String endereco) throws Exception
	{
		gerenciarCafeterias.removerCafeteria (endereco);
	}
	
	public void mudarNomeCafeteria (String endereco, String novoNome) throws Exception
	{
		this.gerenciarCafeterias.mudarNomeCafeteria (endereco, novoNome);
	}
	
	public void mudarEnderecoCafeteria (String endereco, String novoEndereco) throws Exception
	{
		this.gerenciarCafeterias.mudarEnderecoCafeteria (endereco, novoEndereco);
	}
	
	public void mudarNumeroTelefoneCafeteria (String endereco, String novoNumeroTelefone) throws Exception
	{
		this.gerenciarCafeterias.mudarNumeroTelefoneCafeteria (endereco, novoNumeroTelefone);
	}
	
	public void mudarNomeProduto (String nome, String novoNome) throws Exception
	{
		this.gerenciarEstoque.mudarNomeProduto (nome, novoNome);
	}
	
	public void mudarPrecoProduto (String nome, double novoPreco) throws Exception
	{
		this.gerenciarEstoque.mudarPrecoProduto (nome, novoPreco);
	}
	
	public void mudarDescricaoProduto (String nome, String novaDescricao) throws Exception
	{
		this.gerenciarEstoque.mudarDescricaoProduto (nome, novaDescricao);
	}
	
	public void aumentarQuantidadeProduto (String nome, int quantidade) throws Exception
	{
		this.gerenciarEstoque.aumentarQuantidadeProduto (nome, quantidade);
	}
	
	public void deslogar()
	{
		this.login.deslogar();
	}
}