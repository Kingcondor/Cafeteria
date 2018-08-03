package repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import enums.Tipo;

import interfaces.IRepositorioContas;

import model.beans.Conta;
import model.beans.Cliente;
import model.beans.Funcionario;

public class RepositorioContas implements IRepositorioContas
{
	private static final RepositorioContas instancia = new RepositorioContas();
	private ArrayList<Conta> contas = new ArrayList<Conta>();
	
	private RepositorioContas()
	{
	}
	
	public static RepositorioContas getInstancia()
	{
		return instancia;
	}
	
	public Conta adicionar (String nome, Tipo tipo) throws Exception
	{
		Conta c;
		if (tipo == Tipo.CLIENTE)
		{
			c = new Cliente(nome);
			this.contas.add (c);
			this.salvarDados();
			return (Cliente) c;
		}
		else
		{
			c = new Funcionario(nome, tipo);
			this.contas.add (c);
			this.salvarDados();
			return (Funcionario) c;
		}
	}
	
	public Conta adicionar (String nome, Double salario, Tipo tipo) throws Exception
	{
		Funcionario f = (Funcionario) this.adicionar (nome, tipo);
		f.setSalario (salario);
		this.salvarDados();
		return f;
	}
	
	public void remover (String nome) throws Exception
	{
		Conta conta = this.buscar (nome);
		this.contas.remove (conta);
		this.salvarDados();
	}
	
	public ArrayList<Conta> listarContas()
	{
		return this.contas;
	}
	
	public ArrayList<Conta> listarContas (Tipo tipo)
	{
		ArrayList<Conta> funcionariosSelecionados = new ArrayList<Conta>();
		if (tipo == Tipo.FUNCIONARIO)
		{
			for (Conta c : this.contas)
			{
				if (c instanceof Funcionario) {
					funcionariosSelecionados.add (c);
				}
			}
		}
		else
		{
			for (Conta c : this.contas)
			{
				if (c.getTipo() == tipo)
				{
					funcionariosSelecionados.add (c);
				}
			}
		}
		return funcionariosSelecionados;
	}
	
	public Conta buscar (String nome)
	{
		for (Conta c : this.contas)
		{
			if ((c.getTipo() == Tipo.FUNCIONARIO || c.getTipo() == Tipo.ATENDENTE || c.getTipo() == Tipo.COMUM || c.getTipo() == Tipo.GERENTE || c.getTipo() == Tipo.GERENTE_PRINCIPAL) && c.getNome().equals (nome))
			{
				return (Funcionario) c;
			}
			else if (c.getNome().equals (nome))
			{
				return (Cliente) c;
			}
		}
		return null;
	}
	
	public Conta buscar (String nome, Tipo tipo)
	{
		for (Conta c : this.contas)
		{
			if (c.getTipo() == tipo) {
				if ((c.getTipo() == Tipo.FUNCIONARIO || c.getTipo() == Tipo.ATENDENTE || c.getTipo() == Tipo.COMUM || c.getTipo() == Tipo.GERENTE || c.getTipo() == Tipo.GERENTE_PRINCIPAL) && c.getNome().equals (nome))
				{
					return (Funcionario) c;
				}
				else if (c.getNome().equals (nome))
				{
					return (Cliente) c;
				}
			}
		}
		return null;
	}
	
	public boolean verificarExistencia (Tipo tipo)
	{
		for (Conta c : this.contas)
		{
			if (c.getTipo() == tipo)
			{
				return true;
			}
		}
		return false;
	}
	
	public void salvarDados() throws Exception
	{
		File arquivo = new File ("contas.dat");
		try
		{
			FileOutputStream escritor = new FileOutputStream (arquivo);
			ObjectOutputStream escritorObjeto = new ObjectOutputStream (escritor);
			for (Conta c : this.contas)
			{
				escritorObjeto.writeObject (c);
			}
			escritor.close();
		}
		catch (FileNotFoundException erro)
		{
			arquivo.createNewFile();
			FileOutputStream escritor = new FileOutputStream (arquivo);
			ObjectOutputStream escritorObjeto = new ObjectOutputStream (escritor);
			for (Conta c : this.contas)
			{
				escritorObjeto.writeObject (c);
			}
			escritor.close();
		}
	}
	
	public void recuperarDados() throws Exception
	{
		contas.clear();
		File arquivo = new File ("contas.dat");
		try
		{
			FileInputStream leitor = new FileInputStream (arquivo);
			try
			{
				ObjectInputStream leitorObjeto = new ObjectInputStream (leitor);
				Object o;
				boolean lendo = true;
				while (lendo)
				{
					o = leitorObjeto.readObject();
					if (o != null) {
						this.contas.add ((Conta) o);
					}else {
						lendo = false;
					}
				}
				leitor.close();
			}
			catch (EOFException erro)
			{
				leitor.close();
			}
			catch (Exception erro)
			{
				leitor.close();
				throw erro;
			}
		}
		catch (FileNotFoundException erro)
		{
			arquivo.createNewFile();
			FileInputStream leitor = new FileInputStream (arquivo);
			try
			{
				ObjectInputStream leitorObjeto = new ObjectInputStream (leitor);
				Object o;
				boolean lendo = true;
				while (lendo)
				{
					o = leitorObjeto.readObject();
					if (o != null) {
						this.contas.add ((Conta) o);
					}else {
						lendo = false;
					}
				}
				leitor.close();
			}
			catch (EOFException erro2)
			{
				leitor.close();
			}
			catch (Exception erro2)
			{
				leitor.close();
				throw erro;
			}
		}
	}
}