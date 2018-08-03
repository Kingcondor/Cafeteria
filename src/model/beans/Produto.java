package model.beans;

import java.io.Serializable;

public class Produto implements Serializable
{
	private static final long serialVersionUID = -6348947572243607193L;
	private String nome;
	private double preco;
	private String descricao;
	private int quantidade;
	
	public Produto (String nome, double preco, String descricao, int quantidadeDoProduto)
	{
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidade = quantidadeDoProduto;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome (String nome)
	{
		this.nome = nome;
	}
	
	public double getPreco()
	{
		return preco;
	}
	
	public void setPreco (double preco)
	{
		this.preco = preco;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao (String descricao)
	{
		this.descricao = descricao;
	}
	
	public int getQuantidade()
	{
		return quantidade;
	}
	
	public void setQuantidade (int quantidadeDoProduto)
	{
		this.quantidade = quantidadeDoProduto;
	}
	
	public String[] toStringArray()
	{
		String[] produto = new String[4];
		produto[0] = this.getNome();
		produto[1] = Double.toString (this.getPreco());
		produto[2] = this.getDescricao();
		produto[3] = Integer.toString (this.getQuantidade());
		return produto;
	}
}