package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import enums.Tipo;

import interfaces.IRepositorioContas;

import model.beans.Conta;
import model.beans.Cliente;
import model.beans.Funcionario;

public class CadastroContas
{
	protected IRepositorioContas contas;
	
	public CadastroContas (IRepositorioContas contas) throws Exception
	{
		this.contas = contas;
		this.contas.recuperarDados();
	}
	
	public boolean verificarExistencia (Tipo tipo)
	{
		return this.contas.verificarExistencia (tipo);
	}
	
	public boolean verificarPresenca (String nome, LocalDate data) throws NullPointerException
	{
		Funcionario f = (Funcionario) this.contas.buscar (nome);
		return f.getDiasDeTrabalho().contains (data);
	}
	
	public boolean verificarPresenca (String nome, String data) throws NullPointerException
	{
		Funcionario f = (Funcionario) this.contas.buscar (nome);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return f.getDiasDeTrabalho().contains (LocalDate.parse(data, formato));
	}
	
	public Conta cadastrar (String nome, Tipo tipo) throws Exception
	{
		Conta conta = this.contas.adicionar (nome, tipo);
		if (tipo == Tipo.CLIENTE)
		{
			return (Cliente) conta;
		}
		else
		{
			return (Funcionario) conta;
		}
	}
	
	public Conta cadastrar (String nome, String senha, Tipo tipo) throws Exception
	{
		Conta conta = this.cadastrar (nome, tipo);
		conta.setSenha(senha);
		this.contas.salvarDados();
		if (tipo == Tipo.CLIENTE)
		{
			return (Cliente) conta;
		}
		else
		{
			return (Funcionario) conta;
		}
	}
	
	public boolean verificarIgual (String nome)
	{
		return this.contas.buscar (nome) != null;
	}
	
	public void descadastrar (String nome) throws Exception
	{
		this.contas.remover (nome);
	}
	
	public void criarSenha (Conta conta, String senha) throws Exception
	{
		if (conta.getSenha() == null)
		{
			conta.setSenha (senha);
			this.contas.salvarDados();
		}
	}
	
	public boolean mudarSenha (Conta conta, String senha, String senhaNova) throws Exception
	{
		if (conta.getSenha().equals (senha))
		{
			conta.setSenha (senhaNova);
			this.contas.salvarDados();
			return true;
		}
		return false;
	}
	
	public double pegarSalario (String nome)
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		return funcionario.getSalario();
	}

	public void mudarSalario (String nome, double salario) throws Exception
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		funcionario.setSalario(salario);
		this.contas.salvarDados();
	}
	
	public String mudarNomeFuncionario (String nomeAntigo, String novoNome) throws Exception
	{
		Funcionario f = (Funcionario) this.contas.buscar (nomeAntigo);
		f.setNome(novoNome);
		this.contas.salvarDados();
		return novoNome;
	}
	
	public String pegarCpf (String nome)
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		return funcionario.getCpf();
	}
	
	public void definirCpf (String nome, String cpf) throws Exception
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		funcionario.setCpf(cpf);
		this.contas.salvarDados();
	}
	
	public String pegarEndereco (String nome)
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		return funcionario.getEndereco();
	}
	
	public void definirEndereco (String nome, String endereco) throws Exception
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		funcionario.setEndereco(endereco);
		this.contas.salvarDados();
	}
	
	public String pegarNumeroTelefone (String nome)
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		return funcionario.getNumeroTelefone();
	}
	
	public void definirNumeroTelefone (String nome, String numeroTelefone) throws Exception
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		
		funcionario.setNumeroTelefone(numeroTelefone);
		this.contas.salvarDados();
	}
	
	public ArrayList<String[]> listarFuncionarios()
	{
		ArrayList<Conta> funcionarios = contas.listarContas (Tipo.FUNCIONARIO);
		ArrayList<String[]> funcionariosString = new ArrayList<String[]>();
		Funcionario f;
		for (Conta c : funcionarios)
		{
			f = (Funcionario) c;
			funcionariosString.add (new String[5]);
			funcionariosString.get (funcionariosString.size()-1)[0] = c.getNome();
			funcionariosString.get (funcionariosString.size()-1)[1] = c.getTipo().toString().toLowerCase();
			funcionariosString.get (funcionariosString.size()-1)[2] = f.getCpf();
			funcionariosString.get (funcionariosString.size()-1)[3] = f.getEndereco();
			funcionariosString.get (funcionariosString.size()-1)[4] = f.getNumeroTelefone();
		}
		return funcionariosString;
	}
}