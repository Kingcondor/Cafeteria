package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import enums.Tipo;

import interfaces.IRepositorioContas;
import interfaces.IRepositorioProdutos;
import interfaces.IRepositorioNotasFiscais;

import model.beans.Cliente;
import model.beans.Conta;
import model.beans.Funcionario;
import model.beans.NotaFiscal;
import model.beans.Produto;

public class Transacao extends GerenciarEstoque
{
	private IRepositorioContas contas;
	private IRepositorioNotasFiscais notasFiscais;
	
	public Transacao (IRepositorioContas contas, IRepositorioNotasFiscais notasFiscais, IRepositorioProdutos produtos) throws Exception
	{
		super (produtos);
		this.contas = contas;
		this.notasFiscais = notasFiscais;
		this.contas.recuperarDados();
		this.notasFiscais.recuperarDados();
	}
	
	public ArrayList<String[]> fazerPedido (String cliente, String atendente, ArrayList<String[]> produtosString, ArrayList<Integer> quantidades) throws Exception
	{
		ArrayList<Produto> produtosObj = new ArrayList<Produto>();
		for (int i = 0; i < produtosString.size(); i++)
		{
			produtosObj.add (super.produtos.buscar (produtosString.get (i)[0]));
			super.subtrairQuantidade (produtosObj.get (produtosObj.size() - 1), quantidades.get (i));
		}
		NotaFiscal notaFiscal = this.notasFiscais.adicionar ((Cliente) this.contas.buscar (cliente, Tipo.CLIENTE), (Funcionario) this.contas.buscar (atendente, Tipo.ATENDENTE), produtosObj, quantidades);
		return notaFiscalToStringArrayArrayList (notaFiscal);
	}
	
	public ArrayList<String[]> notaFiscalToStringArrayArrayList (NotaFiscal nota)
	{
		ArrayList<String[]> notaFiscal = new ArrayList<String[]>();
		notaFiscal.add (new String[1]);
		notaFiscal.get (0)[0] = nota.getHorarioDoPagamento().toString();
		for (int i = 1; i <= nota.getProdutos().size(); i++)
		{
			notaFiscal.add (new String[2]);
			notaFiscal.get (i)[0] = nota.getProdutos().get (i-1).getNome();
			notaFiscal.get (i)[1] = Double.toString (nota.getProdutos().get (i-1).getPreco() * nota.getQuantidades().get (i-1));
		}
		notaFiscal.add (new String[1]);
		notaFiscal.get (notaFiscal.size() - 1)[0] = Double.toString (nota.getValorTotal());
		return notaFiscal;
	}
	
	public ArrayList<String[]> listarAtendentes()
	{
		ArrayList<Conta> atendentes = contas.listarContas(Tipo.ATENDENTE);
		ArrayList<String[]> atendentesString = new ArrayList<String[]>();
		for (Conta c : atendentes)
		{
			atendentesString.add (new String[2]);
			atendentesString.get (atendentesString.size()-1)[0] = c.getNome();
			atendentesString.get (atendentesString.size()-1)[1] = c.getTipo().toString().toLowerCase();
		}
		return atendentesString;
	}
	
	public ArrayList<String[]> pegarVendasDiarias()
	{
		ArrayList<NotaFiscal> notas = this.notasFiscais.listarNotasFiscais();
		ArrayList<String[]> vendas = new ArrayList<String[]>();
		for (int i = 0; i < notas.size(); i++)
		{
			if (i > 0 && notas.get (i).getHorarioDoPagamento().toString().equals (vendas.get (vendas.size()-1)[1]))
			{
				vendas.get (vendas.size()-1)[1] = Double.toString (Double.parseDouble(vendas.get (vendas.size()-1)[1]) + notas.get (i).getValorTotal());
			}
			else
			{
				vendas.add (new String[3]);
				vendas.get (vendas.size()-1)[0] = notas.get (i).getHorarioDoPagamento().toString();
				vendas.get (vendas.size()-1)[1] = notas.get (i).getCliente().getNome();
				vendas.get (vendas.size()-1)[2] = Double.toString (notas.get (i).getValorTotal());
			}
		}
		return vendas;
	}
	
	public ArrayList<String[]> verHistoricoVendas (Conta atendente)
	{
		ArrayList<String[]> vendas = new ArrayList<String[]>();
		for (NotaFiscal n : this.notasFiscais.listarNotasFiscais())
		{
			if (n.getAtendente().equals (atendente))
			{
				vendas.add (new String[3]);
				vendas.get (vendas.size() - 1)[0] = n.getCliente().getNome();
				vendas.get (vendas.size() - 1)[1] = n.getHorarioDoPagamento().toString();
				vendas.get (vendas.size() - 1)[2] = Double.toString (n.getValorTotal());
			}
		}
		return vendas;
	}
	
	public void cancelarPedido (String data) throws Exception
	{
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime dataFormatada = LocalDateTime.parse (data, formato);
		this.notasFiscais.remover (dataFormatada);
	}
}