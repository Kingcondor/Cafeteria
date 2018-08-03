package model.beans;

import java.util.ArrayList;

import enums.Tipo;

public class Cliente extends Conta
{
	private static final long serialVersionUID = 8855913231074269542L;
	private ArrayList<String> promocoes;
	
	public Cliente (String nome)
	{
		this.nome = nome;
		super.tipo = Tipo.CLIENTE;
	}
	
	public ArrayList<String> getPromocoes()
	{
		return this.promocoes;
	}
	
	public void setPromocoes (ArrayList<String> promocoes)
	{
		this.promocoes = promocoes;
	}
	
	public boolean equals (Cliente cliente)
	{
		if (this.getNome().equals (cliente.getNome()))
		{
			return true;
		}
		return false;
	}
}