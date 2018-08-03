package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import main.Main;

public class MudarSenhaController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{	
	}
	
	@FXML
	private PasswordField senha;
	@FXML
	private PasswordField confirmacaoSenha;
	@FXML
	private PasswordField novaSenha;
	@FXML
	private PasswordField confirmacaoNovaSenha;
	
	public void mudarSenhaKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			mudarSenha();
		}
	}
	public void mudarSenhaClick (ActionEvent event)
	{
		mudarSenha();
	}
	
	public void mudarSenha()
	{
		if (!senha.getText().equals ("") && !novaSenha.getText().equals ("") && !confirmacaoSenha.getText().equals ("") && !confirmacaoNovaSenha.getText().equals ("") )
		{
			if (this.getMain().getControlador().verificarSenha (senha.getText()))
			{
				if (senha.getText().equals (confirmacaoSenha.getText()) && novaSenha.getText().equals (confirmacaoNovaSenha.getText()))
				{
					try {
						this.getMain().getControlador().mudarSenha (senha.getText(), novaSenha.getText());
						Alert a = new Alert (AlertType.INFORMATION, "Mudança de senha realizada com sucesso");
						a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
						a.showAndWait();
						this.getMain().getMiniPrimaryStage().close();
					} 
					catch (Exception e) 
					{
						Alert b = new Alert(AlertType.ERROR, "Não foi possível remover o produto");
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
					Alert a = new Alert (AlertType.ERROR, "Algum dos campos de confirmação está incorreto");
					a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
					a.showAndWait();
				}
			}
			else
			{
				Alert a = new Alert (AlertType.ERROR, "Senha incorreta");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
		}
		else
		{
			Alert a = new Alert (AlertType.ERROR, "Algum dos campos não foi preenchido");
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