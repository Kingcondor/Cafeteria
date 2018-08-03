package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import enums.Tipo;

import interfaces.IRepositorioContas;

import model.beans.Alerta;
import model.beans.Conta;
import model.beans.Funcionario;

public class GerenciarAlertas
{
	private IRepositorioContas contas;
	
	public GerenciarAlertas (IRepositorioContas contas) throws Exception
	{
		this.contas = contas;
		this.contas.recuperarDados();
	}
	
	public ArrayList<String[]> pegarAlertas (String nome)
	{
		ArrayList<String[]> listaAlertas = new ArrayList<String[]>();
		Funcionario funcionario = (Funcionario) contas.buscar (nome);
		for (Alerta a : funcionario.getAlertas())
		{
			listaAlertas.add (a.toStringArray());
		}
		return listaAlertas;
	}
	
	public void emitirAlerta (String nomeGerente, String assunto, String mensagem) throws Exception
	{
		for (Conta c : this.contas.listarContas())
		{
			if (c instanceof Funcionario) {
				Funcionario f = (Funcionario) c;
				Funcionario gerente = (Funcionario) this.contas.buscar (nomeGerente, Tipo.GERENTE);
				if (gerente != null)
				{
					f.setAlertas (new Alerta (gerente, assunto, LocalDateTime.now(), mensagem));
				}
				else
				{
					gerente = (Funcionario) this.contas.buscar (nomeGerente, Tipo.GERENTE_PRINCIPAL);
					f.setAlertas (new Alerta (gerente, assunto, LocalDateTime.now(), mensagem));
				}
			}
		}
		this.contas.salvarDados();
	}
	
	public void emitirAlerta (String nomeGerente, String funcionario, String assunto, String mensagem) throws Exception
	{
		Funcionario f = (Funcionario) this.contas.buscar (funcionario);
		Funcionario gerente = (Funcionario) this.contas.buscar (nomeGerente, Tipo.GERENTE);
		if (gerente != null)
		{
			f.setAlertas (new Alerta (gerente, assunto, LocalDateTime.now(), mensagem));
		}
		else
		{
			gerente = (Funcionario) this.contas.buscar (nomeGerente, Tipo.GERENTE_PRINCIPAL);
			f.setAlertas (new Alerta (gerente, assunto, LocalDateTime.now(), mensagem));
		}
		this.contas.salvarDados();
	}
	
	public void avisarChegada (String nome) throws Exception
	{
		Funcionario funcionario = (Funcionario) this.contas.buscar (nome);
		funcionario.setDiasDeTrabalho(LocalDate.now());
		this.contas.salvarDados();
	}
}