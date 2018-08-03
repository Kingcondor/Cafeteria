package model.beans;

import java.time.LocalDate;

import java.util.ArrayList;

import enums.Tipo;

import model.beans.Alerta;

public class Funcionario extends Conta
{
	private static final long serialVersionUID = 6208667046846378175L;
	private double salario;
	private String cpf;
	private String endereco;
	private String numeroTelefone;
	private int numeroAlertas;
	private ArrayList<Alerta> alertas;
	private ArrayList<LocalDate> diasDeTrabalho;
	
	public Funcionario (String nome, Tipo tipo)
	{
		this.nome = nome;
		super.tipo = tipo;
		this.salario = 0;
		this.numeroAlertas = 0;
		this.alertas = new ArrayList<Alerta>();
		this.diasDeTrabalho = new ArrayList<LocalDate>();
	}
	
	public Funcionario (String nome, Tipo tipo, Double salario)
	{
		this.nome = nome;
		this.salario = salario;
		this.numeroAlertas = 0;
		this.alertas = new ArrayList<Alerta>();
		this.diasDeTrabalho = new ArrayList<LocalDate>();
	}
	
	public String getCpf()
	{
		return this.cpf;
	}
	
	public void setCpf (String cpf)
	{
		this.cpf = cpf;
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
	
	public double getSalario()
	{
		return this.salario;
	}
	
	public void setSalario (double salario)
	{
		this.salario = salario;
	}
	
	public int getNumeroAlertas()
	{
		return this.numeroAlertas;
	}
	
	public void setNumeroAlertas (int numeroAlertas)
	{
		this.numeroAlertas = numeroAlertas;
	}
	
	public ArrayList<Alerta> getAlertas()
	{
		return this.alertas;
	}
	
	public void setAlertas (Alerta alerta)
	{
		this.alertas.add (alerta);
	}
	
	public ArrayList<LocalDate> getDiasDeTrabalho()
	{
		return this.diasDeTrabalho;
	}
	
	public void setDiasDeTrabalho (LocalDate diaDeTrabalho)
	{
		this.diasDeTrabalho.add (diaDeTrabalho);
	}
}