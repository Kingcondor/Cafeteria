package interfaces;

import java.util.ArrayList;

import model.beans.Cafeteria;

public interface IRepositorioCafeterias
{
	public Cafeteria adicionar (String nome, String endereco, String numeroTelefone) throws Exception;
	public void remover (String endereco) throws Exception;
	public Cafeteria buscar (String endereco);
	public ArrayList<Cafeteria> listarCafeterias();
	public void salvarDados() throws Exception;
	public void recuperarDados() throws Exception;
}