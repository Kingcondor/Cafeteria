package cafeteria;
import java.util.ArrayList;

public class Controlador {
	private static ArrayList<Cafeteria> cafeterias = new ArrayList<>();
	private static ArrayList<Produto> produtos = new ArrayList<>();
	private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private static ArrayList<NotaFiscal> notasFiscais = new ArrayList<>();
	
	public static ArrayList<Cafeteria> listarCafeterias() {
		return cafeterias;
	}
	public static void adicionarCafeteria(String nome, String endereco, String numeroTelefone) {
		cafeterias.add(new Cafeteria(nome, endereco, numeroTelefone));
	}
	public static void tirarCafeteria(String nome) {
		cafeterias.remove(buscarCafeteria(nome));
	}
	
	public static ArrayList<Produto> listarProdutos() {
		return produtos;
	}
	public static void adicionarProduto(String nome, double preco, String descricao, int quantidade) {
		produtos.add(new Produto(nome, preco, descricao, quantidade));
	}
	public static void tirarProduto(String nome) {
		produtos.remove(buscarProduto(nome));
	}
	
	public static ArrayList<Funcionario> listarFuncionarios() {
		return funcionarios;
	}
	public static Funcionario getFuncionario(String nome) {
		int index = buscarFuncionario(nome);
		if (index == -1) {
			return null;
		}
		return funcionarios.get(index);
	}
	public static Funcionario getFuncionario(int posicao) {
		return funcionarios.get(posicao);
	}
	public static void adicionarFuncionario(String nome, double salario) {
		funcionarios.add(new Funcionario(nome, salario));
	}
	public static void tirarFuncionario(String nome) {
		funcionarios.remove(buscarFuncionario(nome));
	}
	public static int pegarNumeroFuncionarios() {
		return funcionarios.size();
	}
	
	public static void adicionarGerente(String nome, double salario) {
		funcionarios.add(new Gerente(nome, salario));
	}
	
	public static void adicionarFinanceiro(String nome, double salario) {
		funcionarios.add(new Financeiro(nome, salario));
	}
	
	public static ArrayList<Cliente> listarClientes() {
		return clientes;
	}
	public static void adicionarCliente(String nome,String senha) {
		clientes.add(new Cliente(nome,senha));
	}
	public static void tirarCliente(String nome) {
		clientes.remove(buscarCliente(nome));
	}
	
	public static ArrayList<NotaFiscal> listarNotasFiscais() {
		return notasFiscais;
	}
	public static void adicionarNotaFiscal(NotaFiscal notaFiscal) {
		notasFiscais.add(notaFiscal);
	}
	public static void tirarNotaFiscal(NotaFiscal notaFiscal) {
		notasFiscais.remove(buscarNotaFiscal(notaFiscal));
	}
	
	public static int buscarCafeteria(String nome) {
		for (int i = 0; i<cafeterias.size(); i++) {
			if (cafeterias.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return -1;
	}
	public static int buscarProduto(String nome) {
		for (int i = 0; i<produtos.size(); i++) {
			if (produtos.get(i).getNome().equalsIgnoreCase(nome)) {
				return i;
			}
		}
		return -1;
	}
	public static int buscarFuncionario(String nome) {
		for (int i = 0; i<funcionarios.size(); i++) {
			if (funcionarios.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return -1;
	}
	public static int buscarCliente(String nome) {
		for (int i = 0; i<clientes.size(); i++) {
			if (clientes.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return -1;
	}
	public static int buscarNotaFiscal(NotaFiscal notaFiscal) {
		for (int i = 0; i<notasFiscais.size(); i++) {
			if (notasFiscais.get(i).equals(notaFiscal)) {
				return i;
			}
		}
		return -1;
	}
	public static int buscarNotaPorCliente(Cliente cliente) {
		for (int i = 0; i<notasFiscais.size(); i++) {
			if (notasFiscais.get(i).getCliente().equals(cliente)) {
				return i;
			}
		}
		return -1;
	}
	public static ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public static void setProdutos(ArrayList<Produto> produtos) {
		Controlador.produtos = produtos;
	}
	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public static void setClientes(ArrayList<Cliente> clientes) {
		Controlador.clientes = clientes;
	}
	
}