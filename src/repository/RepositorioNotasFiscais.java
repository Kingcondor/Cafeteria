package repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.time.LocalDateTime;

import interfaces.IRepositorioNotasFiscais;

import model.beans.Cliente;
import model.beans.Funcionario;
import model.beans.NotaFiscal;
import model.beans.Produto;

public class RepositorioNotasFiscais implements IRepositorioNotasFiscais
{
	private static final RepositorioNotasFiscais instancia = new RepositorioNotasFiscais();
	private ArrayList<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();
	
	private RepositorioNotasFiscais()
	{
	}
	
	public static RepositorioNotasFiscais getInstancia()
	{
		return instancia;
	}
	
	public NotaFiscal adicionar (Cliente cliente, Funcionario atendente, ArrayList<Produto> produtos, ArrayList<Integer> quantidades) throws Exception
	{
		NotaFiscal n = new NotaFiscal (cliente, atendente, produtos, quantidades);
		this.notasFiscais.add (n);
		this.salvarDados();
		return n;
	}
	
	public void remover (LocalDateTime data) throws Exception
	{
		NotaFiscal notaFiscal = this.buscar (data);
		this.notasFiscais.remove (notaFiscal);
		this.salvarDados();
	}
	
	public NotaFiscal buscar (LocalDateTime data)
	{
		for (NotaFiscal n : this.notasFiscais)
		{
			if (n.getHorarioDoPagamento().equals (data))
			{
				return n;
			}
		}
		return null;
	}
	
	public ArrayList<NotaFiscal> listarNotasFiscais()
	{
		return this.notasFiscais;
	}
	
	public void salvarDados() throws Exception
	{
		File arquivo = new File ("notasFiscais.dat");
		try
		{
			FileOutputStream escritor = new FileOutputStream (arquivo);
			ObjectOutputStream escritorObjeto = new ObjectOutputStream (escritor);
			for (NotaFiscal n : this.notasFiscais)
			{
				escritorObjeto.writeObject (n);
			}
			escritor.close();
		}
		catch (FileNotFoundException erro)
		{
			arquivo.createNewFile();
			FileOutputStream escritor = new FileOutputStream (arquivo);
			ObjectOutputStream escritorObjeto = new ObjectOutputStream (escritor);
			for (NotaFiscal n : this.notasFiscais)
			{
				escritorObjeto.writeObject (n);
			}
			escritor.close();
		}
	}
	
	public void recuperarDados() throws Exception
	{
		notasFiscais.clear();
		File arquivo = new File ("notasFiscais.dat");
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
						this.notasFiscais.add ((NotaFiscal) o);
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
						this.notasFiscais.add ((NotaFiscal) o);
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