package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Main;

public class ClienteOpcoesController implements Initializable
{
private Main main;
	
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private Label nomeDoCliente;
	
	public void mudarSeuNome()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarNomeScene.fxml", "Nome", Controllers.MUDAR_NOME);
	}
	
	public void mudarSuaSenha()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarSenhaScene.fxml", "Senha", Controllers.MUDAR_SENHA);
	}
	
	public void fazerPedido() {
		this.getMain().showOnBorder ("/view/gui/fxml/VerCardapioScene.fxml", "Cardápio", Controllers.FAZER_PEDIDO);
	}
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		nomeDoCliente.setText (this.getMain().getControlador().pegarNome());
	}
}