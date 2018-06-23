package logica;
import java.util.ArrayList;

public class Cliente extends Pessoa {
	public Cliente(String nome) {
		this.nome = nome;
	}
	public void fazerPedido(Atendente atendente, ArrayList<String[]> produtosComprados) {
		ArrayList<String[]> produtosEQuantidades = new ArrayList<String[]>();
		for (int i = 0; i < produtosComprados.size(); i++) {
			produtosEQuantidades.add(new String[3]);
			produtosEQuantidades.get(produtosEQuantidades.size()-1)[0] = produtosComprados.get(i)[0];
			produtosEQuantidades.get(produtosEQuantidades.size()-1)[1] = produtosComprados.get(i)[1];
			produtosEQuantidades.get(produtosEQuantidades.size()-1)[2] = String.valueOf(Double.parseDouble(produtosComprados.get(i)[2]) * Integer.parseInt(produtosComprados.get(i)[1]));
			Controlador.getProduto(produtosComprados.get(i)[0]).subtrairQuantidadeProduto(Integer.parseInt(produtosComprados.get(i)[1]));
		}
		NotaFiscal nota = new NotaFiscal(this, atendente, produtosEQuantidades);
		Controlador.adicionarNotaFiscal(nota);
	}
}