package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;

public class CadastrarNovaCafeteriaController implements Initializable{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private TextField nome;
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
	private TextField telefone;	
	
	public void cadastrarKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			cadastrar();
		}
	}
	public void cadastrarClick (ActionEvent event)
	{
		cadastrar();
	}
	
	public void cadastrar()
	{
		String endereco = "";
		if (nome.getText().equalsIgnoreCase("") || enderecoN.getText().equalsIgnoreCase ("") || enderecoR.getText().equalsIgnoreCase ("") || enderecoB.getText().equalsIgnoreCase ("") || enderecoC.getText().equalsIgnoreCase ("") || enderecoE.getText().equalsIgnoreCase ("") || telefone.getText().equalsIgnoreCase("")) 
		{
			Alert a = new Alert (AlertType.CONFIRMATION, "Um dos campos n√£o foi preenchido, deseja continuar assim mesmo?");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
			if (a.getResult() == ButtonType.OK)
			{
				endereco = enderecoN.getText() + ", " + enderecoR.getText() + " - " + enderecoB.getText() + " - " + enderecoC.getText() + " - " + enderecoE.getText();
				try 
				{
					if (!this.getMain().getControlador().verificarCafeteriaIgual(endereco)) 
					{
						this.getMain().getControlador().adicionarCafeteria (nome.getText(), endereco, telefone.getText());a.setAlertType (AlertType.INFORMATION);
						a.setHeaderText ("SUCESSO!");
						a.setContentText ("Cadastro realizado com sucesso");
						a.showAndWait();
						this.getMain().getMiniPrimaryStage().close();
					}
					else 
					{
						Alert b = new Alert(AlertType.WARNING, "J· existe uma cafeteria neste endereÁo.");
						b.showAndWait();
					}
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Cafeteria n√£o pode ser cadastrada");
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
			endereco = enderecoN.getText() + ", " + enderecoR.getText() + " - " + enderecoB.getText() + " - " + enderecoC.getText() + " - " + enderecoE.getText();
			try 
			{
				this.getMain().getControlador().adicionarCafeteria (nome.getText(), endereco, telefone.getText());
				Alert a = new Alert (AlertType.INFORMATION, "Cadastro realizado com sucesso");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.setHeaderText ("SUCESSO!");
				a.showAndWait();
				this.getMain().getMiniPrimaryStage().close();
			} 
			catch (Exception e) 
			{
				Alert b = new Alert(AlertType.ERROR, "Cafeteria n√£o pode ser cadastrada");
				b.showAndWait();
				try 
				{
					this.getMain().getControlador().registrarErro(e);
				} catch (Exception e2) 
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
