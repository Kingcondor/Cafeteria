package interfaces;

import java.util.ArrayList;

public interface IRepositorioErros {
	public String getConstanteInicioPrograma();
	public void registrarErro (Exception erro) throws Exception;
	public ArrayList<String> listarErros();
	public void salvarLog() throws Exception;
	public void recuperarLog() throws Exception;
}