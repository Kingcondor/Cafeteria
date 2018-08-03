package model;

import java.util.ArrayList;

import interfaces.IRepositorioProdutos;

import model.beans.Produto;

public class GerenciarEstoque
{
	protected IRepositorioProdutos produtos;
	
	public GerenciarEstoque (IRepositorioProdutos produtos) throws Exception
	{
		this.produtos = produtos;
		this.produtos.recuperarDados();
	}
	
	public ArrayList<String[]> listarProdutos()
	{
		ArrayList<Produto> produtos = this.produtos.listarProdutos();
		ArrayList<String[]> produtosString = new ArrayList<String[]>();
		for (Produto p : produtos) {
			produtosString.add (p.toStringArray());
		}
		return produtosString;
	}
	
	public boolean verificarQuantidade (int quantidadeEscolhida, int quantidadeProduto)
	{
		return quantidadeEscolhida < quantidadeProduto;
	}
	
	public Produto buscarProduto (String nome)
	{
		return this.produtos.buscar (nome);
	}
	
	public void adicionarProduto (String nome, double preco, String descricao, int quantidadeDoProduto) throws Exception
	{
		this.produtos.adicionar (nome, preco, descricao, quantidadeDoProduto);
	}
	
	public void removerProduto (String nome) throws Exception
	{
		this.produtos.remover (nome);
	}
	
	public void mudarNomeProduto (String nome, String novoNome) throws Exception
	{
		this.produtos.buscar (nome).setNome (novoNome);
		this.produtos.salvarDados();
	}
	
	public void mudarPrecoProduto (String nome, double novoPreco) throws Exception
	{
		this.produtos.buscar (nome).setPreco (novoPreco);
		this.produtos.salvarDados();
	}
	
	public void mudarDescricaoProduto (String nome, String novaDescricao) throws Exception
	{
		this.produtos.buscar (nome).setDescricao (novaDescricao);
		this.produtos.salvarDados();
	}
	
	public void aumentarQuantidadeProduto (String nome, int quantidade) throws Exception
	{
		Produto p = this.produtos.buscar (nome);
		p.setQuantidade (p.getQuantidade() + quantidade);
		this.produtos.salvarDados();
	}
	
	public void subtrairQuantidade (Produto p, int quantidade) throws Exception
	{
		p.setQuantidade (p.getQuantidade() - quantidade);
		this.produtos.salvarDados();
	}
}