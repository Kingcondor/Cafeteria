package logica;
import java.util.ArrayList;

public class Controlador {
	private static ArrayList<Cafeteria> cafeterias = new ArrayList<Cafeteria>();
	private static ArrayList<Produto> produtos = new ArrayList<Produto>();
	private static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private static ArrayList<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();
	private static boolean verificacaoDePrimeiraVez = false;
	
	public static ArrayList<Cafeteria> getCafeterias() {
		return cafeterias;
	}
	public static Cafeteria getCafeteria(String nome) {
		for (int i = 0; i<cafeterias.size(); i++) {
			if (cafeterias.get(i).getNome().equals(nome)) {
				return cafeterias.get(i);
			}
		}
		return null;
	}
	public static void adicionarCafeteria(String nome, String endereco, String numeroTelefone) {
		cafeterias.add(new Cafeteria(nome, endereco, numeroTelefone));
	}
	public static void tirarCafeteria(String nome) {
		cafeterias.remove(getCafeteria(nome));
	}
	
	public static ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public static Produto getProduto(String nome) {
		for (int i = 0; i<produtos.size(); i++) {
			if (produtos.get(i).getNome().equalsIgnoreCase(nome)) {
				return produtos.get(i);
			}
		}
		return null;
	}
	public static Produto getProduto(int posicao) {
		return produtos.get(posicao);
	}
	public static void adicionarProduto(String nome, double preco, String descricao, int quantidade) {
		produtos.add(new Produto(nome, preco, descricao, quantidade));
	}
	public static void tirarProduto(String nome) {
		produtos.remove(getProduto(nome));
	}
	
	public static ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public static Funcionario getFuncionario(String nome) {
		for (int i = 0; i<funcionarios.size(); i++) {
			if (funcionarios.get(i).getNome().equals(nome)) {
				return funcionarios.get(i);
			}
		}
		return null;
	}
	public static Funcionario getFuncionario(int posicao) {
		return funcionarios.get(posicao);
	}
	public static void adicionarFuncionario(String nome, double salario) {
		funcionarios.add(new Funcionario(nome, salario));
	}
	public static void tirarFuncionario(String nome) {
		funcionarios.remove(getFuncionario(nome));
	}
	public static int pegarNumeroFuncionarios() {
		return funcionarios.size();
	}
	
	public static Gerente getGerente(String nome) {
		Funcionario funcionario = getFuncionario(nome);
		if (funcionario instanceof Gerente) {
			return (Gerente) funcionario;
		}
		return null;
	}
	public static void adicionarGerente(String nome, double salario) {
		funcionarios.add(new Gerente(nome, salario));
	}
	
	public static void adicionarAtendente(String nome, double salario) {
		funcionarios.add(new Atendente(nome, salario));
	}
	public static ArrayList<Atendente> getAtendentes() {
		ArrayList<Atendente> atendentes = new ArrayList<Atendente>();
		for (int i = 0; i<funcionarios.size(); i++) {
			if (funcionarios.get(i) instanceof Atendente) {
				atendentes.add((Atendente) funcionarios.get(i));
			}
		}
		return atendentes;
	}
	public static Atendente getAtendente(String nome) {
		for (int i = 0; i<funcionarios.size(); i++) {
			if (funcionarios.get(i).getNome().equals(nome)) {
				return (Atendente) funcionarios.get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public static Cliente getCliente(String nome) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNome().equals(nome)) {
				return clientes.get(i);
			}
		}
		return null;
	}
	public static void adicionarCliente(String nome) {
		clientes.add(new Cliente(nome));
	}
	public static void tirarCliente(String nome) {
		clientes.remove(getCliente(nome));
	}
	
	public static ArrayList<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}
	public static int getNotaFiscal(NotaFiscal notaFiscal) {
		for (int i = 0; i<notasFiscais.size(); i++) {
			if (notasFiscais.get(i).equals(notaFiscal)) {
				return i;
			}
		}
		return -1;
	}
	public static NotaFiscal getNotaFiscal(int posicao) {
		return notasFiscais.get(posicao);
	}
	public static ArrayList<NotaFiscal> getNotasCliente(Cliente cliente) {
		ArrayList<NotaFiscal> notasFiscaisCliente = new ArrayList<NotaFiscal>();
		for (int i = 0; i<notasFiscais.size(); i++) {
			if (notasFiscais.get(i).getCliente().equals(cliente)) {
				notasFiscaisCliente.add(notasFiscais.get(i));
			}
		}
		return notasFiscaisCliente;
	}
	public static ArrayList<NotaFiscal> getNotasPorAtendente(Atendente atendente) {
		ArrayList<NotaFiscal> notasFiscaisCliente = new ArrayList<NotaFiscal>();
		for (int i = 0; i<notasFiscais.size(); i++) {
			if (notasFiscais.get(i).getAtendente().equals(atendente)) {
				notasFiscaisCliente.add(notasFiscais.get(i));
			}
		}
		return notasFiscaisCliente;
	}
	public static void adicionarNotaFiscal(NotaFiscal notaFiscal) {
		notasFiscais.add(notaFiscal);
	}
	public static void tirarNotaFiscal(int posicao) {
		notasFiscais.remove(posicao);
	}
	
	public static boolean getVerificacaoDePrimeiraVez() {
		return verificacaoDePrimeiraVez;
	}
	public static void setVerificacaoDePrimeiraVez(boolean verificacao) {
		verificacaoDePrimeiraVez = verificacao;
	}
}