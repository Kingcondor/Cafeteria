package logica;
import java.util.ArrayList;

public class Cliente extends Pessoa {
	private ArrayList<FuncionarioFinanceiro> atendentes;
	private ArrayList<NotaFiscal> notasFiscais;
	public Cliente(String nome) {
		this.nome = nome;
		this.atendentes = new ArrayList<FuncionarioFinanceiro>();
		this.notasFiscais = new ArrayList<NotaFiscal>();
	}
	public void fazerPedido(FuncionarioFinanceiro atendente, ArrayList<Produto> produtosComprados, ArrayList<Integer> quantidades) {
		ArrayList<String[]> produtosEQuantidades = new ArrayList<String[]>();
		for (int i = 0; i < produtosComprados.size(); i++) {
			produtosEQuantidades.add(new String[3]);
			produtosEQuantidades.get(-1)[0] = produtosComprados.get(i).getNome();
			produtosEQuantidades.get(-1)[1] = String.valueOf(quantidades.get(i));
			produtosEQuantidades.get(-1)[2] = String.valueOf(produtosComprados.get(i).getPreco() * quantidades.get(i));
		}
		NotaFiscal nota = new NotaFiscal(this, produtosEQuantidades);
		Controlador.adicionarNotaFiscal(nota);
		this.atendentes.add(atendente);
		this.notasFiscais.add(nota);
	}
	public ArrayList<FuncionarioFinanceiro> getAtendentes() {
		return atendentes;
	}
	public void setAtendentes(ArrayList<FuncionarioFinanceiro> atendentes) {
		this.atendentes = atendentes;
	}
	public ArrayList<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}
	public void setNotasFiscais(ArrayList<NotaFiscal> notasFiscais) {
		this.notasFiscais = notasFiscais;
	}
}