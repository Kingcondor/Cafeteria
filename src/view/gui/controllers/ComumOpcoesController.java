package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Main;

public class ComumOpcoesController implements Initializable{
private Main main;
	
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private Label nomeDoFuncionario;
	
	public void mudarSeuNome()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarNomeScene.fxml", "Nome", Controllers.MUDAR_NOME);
	}
	
	public void mudarSuaSenha()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarSenhaScene.fxml", "Senha", Controllers.MUDAR_SENHA);
	}
	
	public void verSeuSalario()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerSeuSalarioScene.fxml", "Salário", Controllers.VER_SEU_SALARIO);
	}
	
	public void verSeuNumero()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerSeuTelefoneScene.fxml", "Telefone", Controllers.VER_SEU_TELEFONE);
	}
	
	public void mudarTelefone()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarTelefoneScene.fxml", "Telefone", Controllers.MUDAR_TELEFONE);
	}
	// VER ALERTA
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		nomeDoFuncionario.setText (this.getMain().getControlador().pegarNome());
	}
}
