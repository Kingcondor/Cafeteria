package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;

import main.Main;

public class CadastroFuncionarioController implements Initializable
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
	private Separator separator1;
	@FXML
	private Separator separator2;
	@FXML
	private Label senhaLabel;
	@FXML
	private Label confirmacaoSenhaLabel;
	@FXML
	private PasswordField confirmacaoSenha;
	@FXML
	private Button salvarBNT;
	
	public void verificar (KeyEvent event)
	{
		if (event.getCode() == KeyCode.ENTER)
		{
			int tentarLogin = this.getMain().getControlador().prepararLogin (nome.getText());
			if (tentarLogin == 2)
			{
				senhaLabel.setPrefSize (85, 60);
				senha.setPrefSize (400, 40);
				separator1.setPrefSize (600, 10);
				confirmacaoSenhaLabel.setPrefSize (243, 52);
				confirmacaoSenha.setPrefSize (400, 40);
				salvarBNT.setPrefSize (100, 50);
			}
			else if (tentarLogin == 1)
			{
				Alert a = new Alert (AlertType.ERROR, "Você já cadastrou uma senha");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
			else
			{
				Alert a = new Alert (AlertType.ERROR, "Usuário não encontrado");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
		}
	}
	
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
			if (confirmacaoSenha.getText().equalsIgnoreCase ("") || senha.getText().equalsIgnoreCase (""))
			{
				Alert a = new Alert (AlertType.INFORMATION, "Um dos campos não foi preenchido");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
			else
			{
				try 
				{
					this.getMain().getControlador().criarSenha (senha.getText());
					Alert a = new Alert (AlertType.INFORMATION, "Cadastro realizado com sucesso");
					a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
					a.setHeaderText ("SUCESSO!");
					a.showAndWait();
					this.getMain().getPrimaryStage().setTitle ("Login");
					this.getMain().showOnBorder ("/view/gui/fxml/LoginFirstScene.fxml", Controllers.LOGIN_FIRST);
				
				}
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Cadastro não pôde ser realizado");
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