package model.beans;

import java.io.Serializable;

public class Cafeteria implements Serializable
{
	private static final long serialVersionUID = -2388742372500589632L;
	private String nome;
	private String endereco;
	private String numeroTelefone;
	
	public Cafeteria (String nome, String endereco, String numeroTelefone)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.numeroTelefone = numeroTelefone;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public void setNome (String nome)
	{
		this.nome = nome;
	}
	
	public String getEndereco()
	{
		return this.endereco;
	}
	
	public void setEndereco (String endereco)
	{
		this.endereco = endereco;
	}
	
	public String getNumeroTelefone()
	{
		return this.numeroTelefone;
	}
	
	public void setNumeroTelefone (String numeroTelefone)
	{
		this.numeroTelefone = numeroTelefone;
	}
}