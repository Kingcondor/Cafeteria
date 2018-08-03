package model.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import model.beans.Funcionario;

public class Alerta implements Serializable
{
	private static final long serialVersionUID = 3866832328507096560L;
	private Funcionario gerente;
	private String assunto;
	private LocalDateTime data;
	private String mensagem;
	
	public Alerta (Funcionario gerente, String assunto, LocalDateTime data, String mensagem)
	{
		this.gerente = gerente;
		this.assunto = assunto;
		this.data = data;
		this.mensagem = mensagem;
	}
	
	public Funcionario getGerente()
	{
		return this.gerente;
	}
	
	public void setGerente (Funcionario gerente)
	{
		this.gerente = gerente;
	}
	
	public String getAssunto()
	{
		return this.assunto;
	}
	
	public void setAssunto (String assunto)
	{
		this.assunto = assunto;
	}
	
	public LocalDateTime getData()
	{
		return this.data;
	}
	
	public void setData (LocalDateTime data)
	{
		this.data = data;
	}
	
	public String getMensagem()
	{
		return this.mensagem;
	}
	
	public void setMensagem (String mensagem)
	{
		this.mensagem = mensagem;
	}
	
	public String[] toStringArray()
	{
		String[] notaFiscal = new String[4];
		notaFiscal[0] = this.getGerente().getNome();
		notaFiscal[1] = this.getAssunto();
		notaFiscal[2] = this.getData().toString().toString();
		notaFiscal[3] = this.getMensagem();
		return notaFiscal;
	}
}
