package interfaces;

import java.util.ArrayList;

import model.beans.Produto;

public interface IRepositorioProdutos {
	public Produto adicionar (String nome, double preco, String descricao, int quantidadeDoProduto) throws Exception;
	public void remover (String nome) throws Exception;
	public Produto buscar (String nome);
	public ArrayList<Produto> listarProdutos();
	public void salvarDados() throws Exception;
	public void recuperarDados() throws Exception;
}