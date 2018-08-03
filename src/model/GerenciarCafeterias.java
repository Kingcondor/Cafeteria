package model;

import java.util.ArrayList;

import model.beans.Cafeteria;

import interfaces.IRepositorioCafeterias;

public class GerenciarCafeterias
{
	private IRepositorioCafeterias cafeterias;
	
	public GerenciarCafeterias (IRepositorioCafeterias cafeterias) throws Exception
	{
		this.cafeterias = cafeterias;
		this.cafeterias.recuperarDados();
	}
	
	public ArrayList<String[]> listarCafeterias ()
	{
		ArrayList<String[]> cafeterias = new ArrayList<String[]>();
		for (Cafeteria c : this.cafeterias.listarCafeterias())
		{
			cafeterias.add (new String[3]);
			cafeterias.get (cafeterias.size() - 1)[0] = c.getNome();
			cafeterias.get (cafeterias.size() - 1)[1] = c.getEndereco().toString();
			cafeterias.get (cafeterias.size() - 1)[2] = c.getNumeroTelefone();
		}
		return cafeterias;
	}
	
	public Cafeteria buscarCafeteria (String endereco)
	{
		return this.cafeterias.buscar (endereco);
	}
	
	public boolean verificarCafeteriaIgual (String endereco)
	{
		return this.buscarCafeteria (endereco) != null;
	}
	
	public Cafeteria adicionarCafeteria (String nome, String endereco, String numeroTelefone) throws Exception
	{
		return this.cafeterias.adicionar(nome, endereco, numeroTelefone);
	}
	
	public void removerCafeteria (String endereco) throws Exception
	{
		this.cafeterias.remover (endereco);
	}
	
	public void mudarNomeCafeteria (String endereco, String novoNome) throws Exception
	{
		this.cafeterias.buscar (endereco).setNome (novoNome);
		this.cafeterias.salvarDados();
	}
	
	public void mudarEnderecoCafeteria (String endereco, String novoEndereco) throws Exception
	{
		this.cafeterias.buscar (endereco).setEndereco (novoEndereco);
		this.cafeterias.salvarDados();
	}
	
	public void mudarNumeroTelefoneCafeteria (String endereco, String novoNumeroTelefone) throws Exception
	{
		this.cafeterias.buscar (endereco).setNumeroTelefone (novoNumeroTelefone);
		this.cafeterias.salvarDados();
	}
}