package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import main.Main;

public class DemitirFuncionarioController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{	
	}
	
	@FXML
	private TextField nome;
	
	public void demitirKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			demitir();
		}
	}
	
	public void demitirClick (ActionEvent Event)
	{
		demitir();
	}
	
	public void demitir()
	{
		if (!nome.getText().equals (""))
		{
			Alert a = new Alert (AlertType.CONFIRMATION, "Realmente deseja descadastrar este funcionário?");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
			if (a.getResult() == ButtonType.OK)
			{
				try
				{
					try {
						this.getMain().getControlador().descadastrar (nome.getText());
						a.setAlertType (AlertType.INFORMATION);
						a.setContentText ("funcionário demitido");
						a.showAndWait();
						this.getMain().getMiniPrimaryStage().close();
					} 
					catch (Exception e) 
					{
						Alert b = new Alert(AlertType.ERROR, "Este funcionário não existe");
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
					Alert b = new Alert (AlertType.INFORMATION, "Este funcionario não existe");
					b.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
					b.showAndWait();
				}
			}	
		}
		else
		{
			Alert a = new Alert (AlertType.ERROR, "Nome não preenchido");
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