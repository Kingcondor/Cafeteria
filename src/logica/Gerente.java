package logica;
import java.util.ArrayList;

public class Gerente extends Funcionario {
	public Gerente(String nome, double salario) {
		super (nome, salario);
	}
	public void contratar(String tipo, String nome, double salario) {
		if (tipo.equalsIgnoreCase("A")) {
			Controlador.adicionarFuncionario(nome, salario);
		}else {
			Controlador.adicionarAtendente(nome, salario);
		}
	}
	public void demitir(String nome) {
		Controlador.tirarFuncionario(nome);
	}
	public void mudarSalarioFuncionario(String nome, double valor) {
		Controlador.getFuncionario(nome).setSalario(valor);
	}
	public double pegarSalarioFuncionario(String nome) {
		return Controlador.getFuncionario(nome).getSalario();
	}
	public void emitirAlerta(String nome, String alerta) {
		if (nome.equalsIgnoreCase("Todos")) {
			for (int i=0; i<Controlador.pegarNumeroFuncionarios(); i++) {
				Controlador.getFuncionario(i).receberAlertaGerente(alerta);
			}
		}else {
			Controlador.getFuncionario(nome).receberAlertaGerente(alerta);
		}
	}
	public ArrayList<String[]> pegarQuantidadeProdutosEstoque() {
		ArrayList<String[]> produtos = new ArrayList<String[]>();
		for (int i = 0; i < Controlador.getProdutos().size(); i++) {
			produtos.add(new String[2]);
			produtos.get(produtos.size()-1)[0] = Controlador.getProdutos().get(i).getNome();
			produtos.get(produtos.size()-1)[1] = Integer.toString(Controlador.getProdutos().get(i).getQuantidadeProduto());
		}
		return produtos;
	}
	public ArrayList<String[]> pegarVendasDiarias() {
		ArrayList<String[]> datas = new ArrayList<String[]>();
		for (int i = 0; i < Controlador.getNotasFiscais().size(); i++) {
			datas.add(new String[2]);
			datas.get(datas.size()-1)[0] = Controlador.getNotasFiscais().get(i).getHorarioDoPagamento().toString();
			datas.get(datas.size()-1)[1] = Double.toString(Controlador.getNotasFiscais().get(i).getValorTotal());
		}
		return datas;
	}
}