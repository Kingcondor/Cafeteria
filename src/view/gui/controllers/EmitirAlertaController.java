package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import main.Main;

public class EmitirAlertaController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{	
	}
	
	@FXML
	private TextField nome;
	@FXML
	private TextField alerta;
	@FXML
	private TextField assunto;
	
	public void alertarKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			alertar();
		}
	}
	
	public void alertarClick (ActionEvent Event)
	{
		alertar();
	}
	
	public void alertar()
	{
		if (!nome.getText().equals ("") && !alerta.getText().equals (""))
		{
			try
			{
				try {
					this.getMain().getControlador().emitirAlerta (nome.getText(), assunto.getText(),alerta.getText());
					Alert a = new Alert (AlertType.INFORMATION, "Alerta emitido");
					a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
					a.showAndWait();
					this.getMain().getMiniPrimaryStage().close();
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Alerta não pôde ser emitido");
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
			catch (NullPointerException erro)
			{
				Alert a = new Alert (AlertType.INFORMATION, "Este funcionario não existe");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
		}
		else
		{
			Alert a = new Alert (AlertType.ERROR, "Alguma das areas não foi preenchida");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
		}
	}
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
	}
}