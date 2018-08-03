package repository;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;

import interfaces.IRepositorioCafeterias;

import model.beans.Cafeteria;

public class RepositorioCafeterias implements IRepositorioCafeterias
{
	private static final RepositorioCafeterias instancia = new RepositorioCafeterias();
	private ArrayList<Cafeteria> cafeterias = new ArrayList<Cafeteria>();
	
	private RepositorioCafeterias()
	{
	}
	
	public static RepositorioCafeterias getInstancia()
	{
		return instancia;
	}
	
	public Cafeteria adicionar (String nome, String endereco, String numeroTelefone) throws Exception
	{
		Cafeteria c = new Cafeteria (nome, endereco, numeroTelefone);
		this.cafeterias.add (c);
		this.salvarDados();
		return c;
	}
	
	public void remover (String endereco) throws Exception
	{
		Cafeteria cafeteria = this.buscar (endereco);
		this.cafeterias.remove (cafeteria);
		this.salvarDados();
	}
	
	public Cafeteria buscar (String endereco)
	{
		for (Cafeteria c : this.cafeterias)
		{
			if (c.getEndereco().toString().equals (endereco))
			{
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Cafeteria> listarCafeterias()
	{
		return this.cafeterias;
	}
	
	public void salvarDados() throws Exception
	{
		File arquivo = new File ("cafeterias.dat");
		try
		{
			FileOutputStream escritor = new FileOutputStream (arquivo);
			ObjectOutputStream escritorObjeto = new ObjectOutputStream (escritor);
			System.out.println(this.cafeterias.size());
			for (Cafeteria c : this.cafeterias)
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
			for (Cafeteria c : this.cafeterias)
			{
				escritorObjeto.writeObject (c);
			}
			escritor.close();
		}
	}
	
	public void recuperarDados() throws Exception
	{
		cafeterias.clear();
		File arquivo = new File ("cafeterias.dat");
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
						this.cafeterias.add ((Cafeteria) o);
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
						this.cafeterias.add ((Cafeteria) o);
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