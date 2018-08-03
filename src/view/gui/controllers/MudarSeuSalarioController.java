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

public class MudarSeuSalarioController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private Label salario;
	@FXML
	private TextField novoSalario;
	
	public void mudarSalarioKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			mudarSalario();
		}
	}
	public void mudarSalarioClick (ActionEvent Event)
	{
		mudarSalario();
	}
	
	public void mudarSalario()
	{
		if (!novoSalario.getText().equals (""))
		{
			try {
				this.getMain().getControlador().mudarSalario (this.getMain().getControlador().pegarNome(), Double.parseDouble(novoSalario.getText()));
				Alert a = new Alert (AlertType.INFORMATION, "Salário alterado");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
				salario.setText (String.valueOf (this.getMain().getControlador().pegarSalario()));
				this.getMain().getMiniPrimaryStage().close();
			} 
			catch (NumberFormatException e) {
				Alert b = new Alert(AlertType.ERROR, "Salário digitado inválido. Digite novamente. Ex.: 1000");
				b.showAndWait();
			}
			catch (Exception e) 
			{
				Alert b = new Alert(AlertType.ERROR, "Não foi possível mudar o salário");
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
		salario.setText (String.valueOf (this.getMain().getControlador().pegarSalario()));
	}
}