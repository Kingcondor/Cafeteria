package interfaces;

import java.util.ArrayList;

import enums.Tipo;

import model.beans.Conta;

public interface IRepositorioContas
{
	public Conta adicionar (String nome, Tipo tipo) throws Exception;
	public void remover (String nome) throws Exception;
	public Conta buscar (String nome);
	public Conta buscar (String nome, Tipo tipo);
	public ArrayList<Conta> listarContas();
	public ArrayList<Conta> listarContas (Tipo tipo);
	public void salvarDados() throws Exception;
	public void recuperarDados() throws Exception;
	public boolean verificarExistencia (Tipo tipo);
}