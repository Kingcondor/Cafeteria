package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import main.Main;

public class CadastroPrimeiraCafeteriaController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private TextField nome;
	@FXML
	private TextField nomeDaCafeteria;
	@FXML
	private TextField enderecoN;
	@FXML
	private TextField enderecoR;
	@FXML
	private TextField enderecoB;
	@FXML
	private TextField enderecoC;
	@FXML
	private TextField enderecoE;
	@FXML 
	private TextField numero;
	@FXML
	private PasswordField senha;
	@FXML
	private Button salvarBNT;
	
	private String endereco;
	
	public void salvar (ActionEvent event)
	{
		if (nome.getText().equalsIgnoreCase ("") || nomeDaCafeteria.getText().equalsIgnoreCase ("") || enderecoN.getText().equalsIgnoreCase ("") || enderecoR.getText().equalsIgnoreCase ("") || enderecoB.getText().equalsIgnoreCase ("") || enderecoC.getText().equalsIgnoreCase ("") || enderecoE.getText().equalsIgnoreCase ("") ||numero.getText().equalsIgnoreCase ("") || senha.getText().equalsIgnoreCase (""))
		{
			Alert a = new Alert (AlertType.CONFIRMATION, "Um dos campos não foi preenchido, deseja continuar assim mesmo?");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
			if (a.getResult() == ButtonType.OK)
			{
				try 
				{
					this.getMain().getControlador().primeiroInicio (nome.getText());
					endereco = enderecoN.getText() + ", " + enderecoR.getText() + " - " + enderecoB.getText() + " - " + enderecoC.getText() + " - " + enderecoE.getText();
					this.getMain().getControlador().adicionarCafeteria (nomeDaCafeteria.getText(), endereco, numero.getText());
					this.getMain().getControlador().criarSenha (senha.getText());
					a.setAlertType (AlertType.INFORMATION);
					a.setHeaderText ("Sucesso");
					a.setContentText ("Cadastro realizado com sucesso");
					a.showAndWait();
					this.getMain().initRootLayout();
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
			try {
				this.getMain().getControlador().primeiroInicio (nome.getText());
				endereco = enderecoN.getText() + ", " + enderecoR.getText() + " - " + enderecoB.getText() + " - " + enderecoC.getText() + " - " + enderecoE.getText();
				this.getMain().getControlador().adicionarCafeteria (nomeDaCafeteria.getText(), endereco, numero.getText());
				this.getMain().getControlador().criarSenha (senha.getText());
				Alert a = new Alert (AlertType.INFORMATION, "Cadastro realizado com sucesso");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.setHeaderText ("SUCESSO!");
				a.showAndWait();
				this.getMain().initRootLayout();
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
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
	}
}