package model.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;

import model.beans.Cafeteria;
import model.beans.Funcionario;
import model.beans.Produto;

public class NotaFiscal implements Serializable
{
	private static final long serialVersionUID = -7099664808402882903L;
	private String id;
	private Cafeteria cafeteria;
	private LocalDateTime horarioDoPagamento;
	private Cliente cliente;
	private Funcionario atendente;
	private ArrayList<Produto> produtos;
	private ArrayList<Integer> quantidades;
	private double valorTotal;
	
	public NotaFiscal (Cliente cliente, Funcionario atendente, ArrayList<Produto> produtos, ArrayList<Integer> quantidades)
	{
		this.horarioDoPagamento = LocalDateTime.now();
		this.cliente = cliente;
		this.atendente = atendente;
		this.produtos = produtos;
		this.quantidades = quantidades;
		this.valorTotal = 0;
		for (int i = 0; i < this.produtos.size(); i++)
		{
			this.valorTotal += this.produtos.get (i).getPreco() * this.quantidades.get (i);
		}
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId (String id)
	{
		this.id = id;
	}
	
	public Cafeteria getCafeteria()
	{
		return this.cafeteria;
	}
	
	public void setCafeteria (Cafeteria cafeteria)
	{
		this.cafeteria = cafeteria;
	}
	
	public LocalDateTime getHorarioDoPagamento()
	{
		return horarioDoPagamento;
	}
	
	public void setHorarioDoPagamento (LocalDateTime horarioDoPagamento)
	{
		this.horarioDoPagamento = horarioDoPagamento;
	}
	
	public Cliente getCliente()
	{
		return cliente;
	}
	
	public void setCliente (Cliente cliente)
	{
		this.cliente = cliente;
	}
	
	public Funcionario getAtendente()
	{
		return atendente;
	}
	
	public void setAtendente (Funcionario atendente)
	{
		this.atendente = atendente;
	}
	
	public ArrayList<Produto> getProdutos()
	{
		return this.produtos;
	}
	
	public void setProdutos (ArrayList<Produto> produtos)
	{
		this.produtos = produtos;
	}
	
	public ArrayList<Integer> getQuantidades()
	{
		return this.quantidades;
	}
	
	public void setQuantidades (ArrayList<Integer> quantidades)
	{
		this.quantidades = quantidades;
	}
	
	public double getValorTotal()
	{
		return valorTotal;
	}
	
	public void setValorTotal (double valorTotal)
	{
		this.valorTotal = valorTotal;
	}
	
	public boolean equals (NotaFiscal notaFiscal)
	{
		if (this.getId().equals (notaFiscal.getId()))
		{
			return true;
		}
		return false;
	}
}