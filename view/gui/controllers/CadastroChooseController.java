package view.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import main.Main;

public class CadastroChooseController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private Button clienteBNT;
	@FXML
	private Button funcionarioBNT;
	
	public void cadastrarCliente (ActionEvent event)
	{
		this.getMain().getPrimaryStage().setTitle ("Cadastro: cliente");
		this.getMain().showOnBorder ("/view/gui/fxml/CadastroClienteScene.fxml", Controllers.CADASTRO_CLIENTE);
	}
	
	public void cadastrarFuncionario (ActionEvent event)
	{
		Alert a = new Alert (AlertType.INFORMATION, "O gerente deve ter cadastrado seu nome de usuário, por favor, digite-o para poder criar sua senha");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.showAndWait();
		this.getMain().getPrimaryStage().setTitle ("Cadastro: funcionário");
		
		Parent root = null;
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation (this.getClass().getResource ("/view/gui/fxml/CadastroFuncionarioScene.fxml"));
			root = fxmlLoader.load();
			((CadastroFuncionarioController) fxmlLoader.getController()).setMain (this.getMain());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.getMain().getRootLayout().setCenter(root);
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