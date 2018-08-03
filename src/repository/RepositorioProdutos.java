package repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import interfaces.IRepositorioProdutos;

import model.beans.Produto;

public class RepositorioProdutos implements IRepositorioProdutos
{
	private static final RepositorioProdutos instancia = new RepositorioProdutos();
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	private RepositorioProdutos()
	{
	}
	
	public static RepositorioProdutos getInstancia()
	{
		return instancia;
	}
	
	public Produto adicionar (String nome, double preco, String descricao, int quantidadeDoProduto) throws Exception
	{
		Produto p = new Produto (nome, preco, descricao, quantidadeDoProduto);
		this.produtos.add (p);
		this.salvarDados();
		return p;
	}
	
	public void remover (String nome) throws Exception
	{
		Produto produto = this.buscar (nome);
		this.produtos.remove (produto);
		this.salvarDados();
	}
	
	public Produto buscar (String nome)
	{
		for (Produto p : this.produtos)
		{
			if (p.getNome().equalsIgnoreCase(nome))
			{
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Produto> listarProdutos()
	{
		return this.produtos;
	}
	
	public void salvarDados() throws Exception
	{
		File arquivo = new File ("produtos.dat");
		try
		{
			FileOutputStream escritor = new FileOutputStream (arquivo);
			ObjectOutputStream escritorObjeto = new ObjectOutputStream (escritor);
			for (Produto p : this.produtos)
			{
				escritorObjeto.writeObject (p);
			}
			escritor.close();
		}
		catch (FileNotFoundException erro)
		{
			arquivo.createNewFile();
			FileOutputStream escritor = new FileOutputStream (arquivo);
			ObjectOutputStream escritorObjeto = new ObjectOutputStream (escritor);
			for (Produto p : this.produtos)
			{
				escritorObjeto.writeObject (p);
			}
			escritor.close();
		}
	}
	
	public void recuperarDados() throws Exception
	{
		produtos.clear();
		File arquivo = new File ("produtos.dat");
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
						this.produtos.add ((Produto) o);
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
						this.produtos.add ((Produto) o);
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