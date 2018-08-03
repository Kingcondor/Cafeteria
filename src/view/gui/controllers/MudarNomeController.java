package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import main.Main;

public class MudarNomeController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private Label nome;
	@FXML
	private TextField novoNome;
	
	public void mudarNomeKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			mudarNome();
		}
	}
	public void mudarNomeClick (ActionEvent event)
	{
		mudarNome();
	}
	
	public void mudarNome()
	{
		if (!this.getMain().getControlador().verificarIgual(novoNome.getText())) 
		{
			this.getMain().getControlador().mudarNome (novoNome.getText());
			Alert a = new Alert (AlertType.INFORMATION, "Nome alterado com sucesso");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
			novoNome.setText (this.getMain().getControlador().pegarNome());
			this.getMain().getMiniPrimaryStage().close();		}
		else 
		{
			Alert b = new Alert(AlertType.WARNING, "Já existe um usuário com este nome.");
			b.showAndWait();
		}
	}
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		novoNome.setText (this.getMain().getControlador().pegarNome());
	}
}