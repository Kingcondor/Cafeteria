package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;

public class MudarTelefoneController implements Initializable{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private Label telefone;
	@FXML
	private TextField novoTelefone;
	
	public void mudarTelefoneKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			mudarTelefone();
		}
	}
	public void mudarTelefoneClick (ActionEvent event)
	{
		mudarTelefone();
	}
	
	public void mudarTelefone()
	{
		try {
			this.getMain().getControlador().mudarNumeroTelefone (novoTelefone.getText());
			Alert a = new Alert (AlertType.INFORMATION, "Telefone alterado com sucesso");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
			novoTelefone.setText (this.getMain().getControlador().pegarNumeroTelefone());
			this.getMain().getMiniPrimaryStage().close();
		} 
		catch (Exception e) 
		{
			Alert b = new Alert(AlertType.ERROR, "Não foi possível alterar seu telefone");
			b.showAndWait();
			try 
			{
				this.getMain().getControlador().registrarErro(e);
			}
			catch (Exception e2) 
			{
				this.getMain().mostrarErro(e);
			}
		}
		
	}
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		novoTelefone.setText (this.getMain().getControlador().pegarNumeroTelefone());
	}
}
