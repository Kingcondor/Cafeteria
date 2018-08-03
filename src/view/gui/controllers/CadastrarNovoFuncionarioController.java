package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Tipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import main.Main;

public class CadastrarNovoFuncionarioController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{	
	}
	@FXML
	private RadioButton gerenteRBNT;
	@FXML
	private RadioButton atendenteRBNT;
	@FXML
	private RadioButton comumRBNT;
	@FXML
	private TextField nome;
	
	public void cadastrarFuncionarioKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			cadastrarFuncionario();
		}
	}
	
	public void cadastrarFuncionarioClick (ActionEvent event)
	{
		cadastrarFuncionario();
	}
	
	public void cadastrarFuncionario()
	{
		if (gerenteRBNT.isSelected())
		{
			if (!nome.getText().equals (""))
			{
				try {
					if (!this.getMain().getControlador().verificarIgual(nome.getText())) 
					{
						this.getMain().getControlador().cadastrar (nome.getText(), Tipo.GERENTE);
						Alert a = new Alert (AlertType.INFORMATION, "Cadastro realizado com sucesso");
						a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
						a.showAndWait();
						this.getMain().getMiniPrimaryStage().close();
					}
					else 
					{
						Alert a = new Alert(AlertType.WARNING, "J· existe um usu·rio com este nome.");
						a.showAndWait();
					}
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Cadastro n√£o p√¥de ser realizado");
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
				Alert a = new Alert (AlertType.ERROR, "Nome n√£o especificado");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
		}
		else if (atendenteRBNT.isSelected())
		{
			if (!nome.getText().equals (""))
			{
				try {
					if (!this.getMain().getControlador().verificarIgual(nome.getText())) 
					{
						this.getMain().getControlador().cadastrar (nome.getText(), Tipo.ATENDENTE);
						Alert a = new Alert (AlertType.INFORMATION, "Cadastro realizado com sucesso");
						a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
						a.showAndWait();
						this.getMain().getMiniPrimaryStage().close();
					}
					else 
					{
						Alert a = new Alert(AlertType.WARNING, "J· existe um usu·rio com este nome.");
						a.showAndWait();
					}
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Cadastro n√£o p√¥de ser realizado");
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
				Alert a = new Alert (AlertType.ERROR, "Nome n√£o especificado");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
		}
		else if (comumRBNT.isSelected())
		{
			if (!nome.getText().equals (""))
			{
				try {
					if (!this.getMain().getControlador().verificarIgual(nome.getText())) 
					{
						this.getMain().getControlador().cadastrar (nome.getText(), Tipo.COMUM);
						Alert a = new Alert (AlertType.INFORMATION, "Cadastro realizado com sucesso");
						a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
						a.showAndWait();
						this.getMain().getMiniPrimaryStage().close();
					}
					else 
					{
						Alert a = new Alert(AlertType.WARNING, "J· existe um usu·rio com este nome.");
						a.showAndWait();
					}
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Cadastro n√£o p√¥de ser realizado");
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
				Alert a = new Alert (AlertType.ERROR, "Nome n√£o especificado");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
		}
		else
		{
			Alert a = new Alert (AlertType.ERROR, "Nenhum tipo selecionado");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
		}
	}
	
	public void verificarOutros (ActionEvent event)
	{
		final ToggleGroup TG = new ToggleGroup();
		gerenteRBNT.setToggleGroup (TG);
		atendenteRBNT.setToggleGroup (TG);
		comumRBNT.setToggleGroup (TG);
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