package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import enums.Tipo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;

import main.Main;

public class CadastroClienteController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private TextField nome;
	@FXML
	private PasswordField senha;
	@FXML
	private PasswordField confirmacaoSenha;
	@FXML
	private Button salvarBNT;
	
	public void salvarKey (KeyEvent kEvent) 
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			salvar();
		}
	}
	
	public void salvar ()
	{
		if (confirmacaoSenha.getText().equals (senha.getText()))
		{
			if (nome.getText().equalsIgnoreCase ("") || confirmacaoSenha.getText().equalsIgnoreCase ("") || senha.getText().equalsIgnoreCase (""))
			{
				Alert a = new Alert (AlertType.INFORMATION, "Um dos campos não foi preenchido");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
			else
			{
				try {
					if (!this.getMain().getControlador().verificarIgual(nome.getText()))
					{
						this.getMain().getControlador().cadastrar (nome.getText(), senha.getText(), Tipo.CLIENTE);
						Alert a = new Alert (AlertType.INFORMATION, "Cadastro realizado com sucesso");
						a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
						a.setHeaderText ("SUCESSO!");
						a.showAndWait();
						this.getMain().getPrimaryStage().setTitle ("Login");
						this.getMain().showOnBorder ("/view/gui/fxml/LoginFirstScene.fxml", Controllers.LOGIN_FIRST);
					}
					else 
					{
						Alert a = new Alert(AlertType.WARNING, "J� existe um usu�rio com este nome.");
						a.showAndWait();
					}
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Cadastro não pode ser realizado");
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
		}
		else
		{
			Alert a = new Alert (AlertType.INFORMATION);
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.setContentText ("Confirmação de senha inválida, a senha e a confirmação de senha devem ser iguais");
			a.setHeaderText ("Confirmação inválida");
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