package cafeteria;
import java.util.ArrayList;

public class Cafeteria {
	private String nome;
	private ArrayList <Produto> produtos;
	private ArrayList <Gerente> gerentes;
	private ArrayList <Funcionario> funcionarios;
	private ArrayList <Cliente> clientes;
	private ArrayList<NotasFiscais> notas;
	
	public Cafeteria(String nome) {
		this.nome = nome;
		produtos = new ArrayList <Produto>();
		gerentes = new ArrayList <Gerente>();
		funcionarios = new ArrayList <Funcionario>();
		clientes = new ArrayList <Cliente>();
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Produto> getProdutos() {
		return this.produtos;
	}
	public void adicionarProduto(String nome, double preco, String descricao, int quantidade) {
		this.produtos.add(new Produto(nome, preco, descricao, quantidade));
	}
	public void tirarProduto(String nome) {
		this.produtos.remove(this.buscarProduto(nome));
	}
	
	public ArrayList<Gerente> getGerentes() {
		return this.gerentes;
	}
	public void adicionarGerente(String nome, double salario, ArrayList <Integer> diasDaSemana) {
		this.gerentes.add(new Gerente(nome, salario, diasDaSemana));
	}
	public void tirarGerente(String nome) {
		this.gerentes.remove(this.buscarGerente(nome));
	}
	
	public ArrayList<Funcionario> listarFuncionarios() {
		return this.funcionarios;
	}
	public Funcionario getFuncionario(String nome) {
		return this.funcionarios.get(this.buscarFuncionario(nome));
	}
	public Funcionario getFuncionario(int posicao) {
		return this.funcionarios.get(posicao);
	}
	public void adicionarFuncionario(String nome, int tipo, double salario) {
		this.funcionarios.add(new Funcionario(nome, tipo, salario));
	}
	public void tirarFuncionario(String nome) {
		this.funcionarios.remove(this.buscarFuncionario(nome));
	}
	public int pegarNumeroFuncionarios() {
		return this.funcionarios.size();
	}
	
	public ArrayList<Cliente> getClientes() {
		return this.clientes;
	}
	public void adicionarCliente(String nome) {
		this.clientes.add(new Cliente(nome));
	}
	public void tirarCliente(String nome) {
		this.clientes.remove(this.buscarCliente(nome));
	}
	public int buscarProduto(String nome) {
		for (int i = 0; i<this.produtos.size(); i++) {
			if (this.produtos.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return -1;
	}
	public int buscarGerente(String nome) {
		for (int i = 0; i<this.gerentes.size(); i++) {
			if (this.gerentes.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return -1;
	}
	public int buscarFuncionario(String nome) {
		for (int i = 0; i<this.funcionarios.size(); i++) {
			if (this.funcionarios.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return -1;
	}
	public int buscarCliente(String nome) {
		for (int i = 0; i<this.clientes.size(); i++) {
			if (this.clientes.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return -1;
	}
	public ArrayList<NotasFiscais> getNotas() {
		return notas;
	}
	public void setNotas(ArrayList<NotasFiscais> notas) {
		this.notas = notas;
	}
	
	
}