package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import enums.Tipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;

public class LoginFirstController implements Initializable
{
	private Main main;
	@FXML
	private Button entrar;
	@FXML
	private TextField usu;
	@FXML
	private PasswordField senha;
	@FXML
	private Label textResp;
	
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	public void logarKey(KeyEvent kEvent) 
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			logar ();
		}
	}
	
	public void logarClick(ActionEvent event)
	{
		logar();
	}
	
	
	public void logar ()
	{
		int tentarLogin = this.getMain().getControlador().prepararLogin (usu.getText());
		if (tentarLogin == 2)
		{
			Alert primeiraSenha = new Alert (AlertType.INFORMATION);
			primeiraSenha.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			primeiraSenha.setContentText ("Como esta é sua primeira vez é necessário fazer o cadastro antes de iniciar o aplicativo portanto a senha utilizada nesta primeira vez será a senha salva no sistema");
			primeiraSenha.showAndWait();
			if (senha.getText() != "") 
			{
				try {
					this.getMain().getControlador().criarSenha (senha.getText());
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Não foi possível criar sua senha");
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
		else if (tentarLogin == 1)
		{
			if (this.getMain().getControlador().logar (senha.getText()))
			{
				if (this.getMain().getControlador().verificarTipo (Tipo.COMUM) || this.getMain().getControlador().verificarTipo (Tipo.ATENDENTE) || this.getMain().getControlador().verificarTipo (Tipo.GERENTE))
				{
					try {
						this.getMain().getControlador().avisarChegada();
					} 
					catch (Exception e) 
					{
						Alert b = new Alert(AlertType.ERROR, "Não foi possível avisar chegada");
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
				if (this.getMain().getControlador().verificarTipo (Tipo.GERENTE_PRINCIPAL))
				{
					this.getMain().showOnBorder ("/view/gui/fxml/GerentePrincipalOpcoesScene.fxml", Controllers.GERENTE_PRINCIPAL_OPCOES);
				}
				else if (this.getMain().getControlador().verificarTipo (Tipo.GERENTE))
				{
					this.getMain().showOnBorder ("/view/gui/fxml/GerenteOpcoesScene.fxml", Controllers.GERENTE_OPCOES);
				}
				else if (this.getMain().getControlador().verificarTipo (Tipo.CLIENTE))
				{
					this.getMain().showOnBorder ("/view/gui/fxml/ClienteOpcoesScene.fxml", Controllers.CLIENTE_OPCOES);
				}
				else if (this.getMain().getControlador().verificarTipo (Tipo.ATENDENTE))
				{
					this.getMain().showOnBorder ("/view/gui/fxml/AtendenteOpcoesScene.fxml", Controllers.FUNCIONARIO_OPCOES);
				}
				else if (this.getMain().getControlador().verificarTipo (Tipo.COMUM))
				{
					this.getMain().showOnBorder ("/view/gui/fxml/ComumOpcoesScene.fxml", Controllers.COMUM_OPCOES);
				}
			}
			else
			{
				textResp.setText ("Senha incorreta");
			}
		}
		else
		{
			textResp.setText ("Usuário inválido");
		}
	}

	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		main.getControlador().deslogar();
	}
}