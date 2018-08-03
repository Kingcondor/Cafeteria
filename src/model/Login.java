package model;

import enums.Tipo;

import interfaces.IRepositorioContas;

import model.beans.Conta;

public class Login extends CadastroContas
{
	private Conta conta;
	
	public Login (IRepositorioContas contas) throws Exception
	{
		super (contas);
		super.contas.recuperarDados();
	}
	
	public Conta getConta()
	{
		return this.conta;
	}
	
	public void setConta (Conta conta)
	{
		this.conta = conta;
	}
	
	public boolean verificarPrimeiroInicio()
	{
		return !super.verificarExistencia (Tipo.GERENTE_PRINCIPAL);
	}
	
	public void primeiroInicio (String nome) throws Exception
	{
		if (this.verificarPrimeiroInicio())
		{
			this.setConta (super.cadastrar (nome, Tipo.GERENTE_PRINCIPAL));
		}
	}
	
	public int prepararLogin (String nome)
	{
		Conta conta = super.contas.buscar (nome);
		if (conta == null)
		{
			return 0;
		}
		else
		{
			this.setConta(conta);
			if (this.verificarSenhaExiste())
			{
				return 1;
			}
			else
			{
				return 2;
			}
		}
	}
	
	public boolean logar (String senha)
	{
		if (this.getConta() != null && this.verificarSenha (senha))
		{
			this.setConta(conta);
			return true;
		}
		return false;
	}
	
	public boolean verificarTipo(Tipo tipo)
	{
		return this.getConta().getTipo().equals (tipo);
	}
	
	public boolean verificarSenhaExiste()
	{
		return this.getConta().getSenha() != null;
	}
	
	public boolean verificarSenha (String senha)
	{
		return this.verificarSenhaExiste() && this.getConta().getSenha().equals (senha);
	}
	
	public void deslogar()
	{
		this.setConta(null);
	}
}