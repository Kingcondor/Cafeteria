package model.beans;

import java.io.Serializable;

import enums.Tipo;

public abstract class Conta implements Serializable
{
	private static final long serialVersionUID = -7172578518110700538L;
	protected String nome;
	protected String senha;
	protected Tipo tipo;
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome (String nome)
	{
		this.nome = nome;
	}
	
	public String getSenha()
	{
		return this.senha;
	}
	
	public void setSenha (String senha)
	{
		this.senha = senha;
	}
	
	public Tipo getTipo()
	{
		return this.tipo;
	}
	
	public void setTipo (Tipo tipo)
	{
		this.tipo = tipo;
	}
	
	public boolean equals (Conta pessoa)
	{
		if (this.nome.equals (pessoa.getNome()))
		{
			return true;
		}
		return false;
	}
}