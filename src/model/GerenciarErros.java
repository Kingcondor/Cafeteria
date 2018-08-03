package model;

import java.util.ArrayList;

import interfaces.IRepositorioErros;

public class GerenciarErros
{
	protected IRepositorioErros erros;
	
	public GerenciarErros (IRepositorioErros erros) throws Exception
	{
		this.erros = erros;
		this.erros.recuperarLog();
	}
	
	public ArrayList<String> verificarErros()
	{
		ArrayList<String> todosErros = this.erros.listarErros();
		ArrayList<String> errosHoje = new ArrayList<String>();
		if (todosErros.size() > 0)
		{
			for (int i = todosErros.size()-1; i >= 0; i--)
			{
				if (todosErros.get (i).equals (this.erros.getConstanteInicioPrograma()))
				{
					break;
				}
				errosHoje.add (todosErros.get (i));
			}
		}
		return errosHoje;
	}
	
	public void registrarErro(Exception erro) throws Exception
	{
		this.erros.registrarErro(erro);
	}
}