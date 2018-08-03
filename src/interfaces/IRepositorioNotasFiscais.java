package interfaces;

import java.util.ArrayList;
import java.time.LocalDateTime;

import model.beans.Cliente;
import model.beans.Funcionario;
import model.beans.NotaFiscal;
import model.beans.Produto;

public interface IRepositorioNotasFiscais {
	public NotaFiscal adicionar (Cliente cliente, Funcionario atendente, ArrayList<Produto> produtos, ArrayList<Integer> quantidades) throws Exception;
	public void remover (LocalDateTime data) throws Exception;
	public NotaFiscal buscar (LocalDateTime data);
	public ArrayList<NotaFiscal> listarNotasFiscais();
	public void salvarDados() throws Exception;
	public void recuperarDados() throws Exception;
}