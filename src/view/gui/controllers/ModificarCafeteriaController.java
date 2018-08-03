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

public class ModificarCafeteriaController implements Initializable{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private Label nome;
	@FXML
	private TextField novoNome;
	@FXML
	private Label endereco;
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
	private Label telefone;
	@FXML
	private TextField novoTelefone;
	
	private String cafeteria;
	
	
	public void mudarCafeteriaKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			mudarCafeteria();
		}
	}
	public void mudarCafeteriaClick (ActionEvent event)
	{
		mudarCafeteria();
	}
	
	public void mudarCafeteria()
	{
		if (!novoTelefone.getText().equalsIgnoreCase("")) 
		{
			try {
				this.getMain().getControlador().mudarNumeroTelefoneCafeteria (this.getCafeteria(), novoTelefone.getText());
			}
			catch (Exception e) 
			{
				Alert b = new Alert(AlertType.ERROR, "Não foi possível alterar o telefone");
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
		if (!novoNome.getText().equalsIgnoreCase("")) 
		{
			try {
				this.getMain().getControlador().mudarNomeCafeteria (this.getCafeteria(), novoNome.getText());
			} 
			catch (Exception e) 
			{
				Alert b = new Alert(AlertType.ERROR, "Não foi possível alterar o nome");
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
		if (!(enderecoN.getText().equalsIgnoreCase("") && enderecoR.getText().equalsIgnoreCase("") && enderecoB.getText().equalsIgnoreCase("") && enderecoC.getText().equalsIgnoreCase("") && enderecoE.getText().equalsIgnoreCase(""))) 
		{
			if (enderecoN.getText().equalsIgnoreCase("") || enderecoR.getText().equalsIgnoreCase("") || enderecoB.getText().equalsIgnoreCase("") || enderecoC.getText().equalsIgnoreCase("") || enderecoE.getText().equalsIgnoreCase("")) 
			{
				Alert a = new Alert(AlertType.ERROR, "Para modificar o endereço é necessário que todos os campos do novo endereço sejam especificados");
				a.showAndWait();
			}
			else
			{
				String novoEndereco = enderecoN.getText() + ", " + enderecoR.getText() + " - " + enderecoB.getText() + " - " + enderecoC.getText() + " - " + enderecoE.getText();
				try {
					if (!this.getMain().getControlador().verificarCafeteriaIgual(novoEndereco)) 
					{
						this.getMain().getControlador().mudarEnderecoCafeteria (this.getCafeteria(), novoEndereco);
					}
					else 
					{
						Alert b = new Alert(AlertType.WARNING, "J� existe uma cafeteria neste endere�o.");
						b.showAndWait();
					}
				} 
				catch (Exception e) 
				{
					Alert b = new Alert(AlertType.ERROR, "Não foi possível alterar o endereço");
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
		Alert a = new Alert(AlertType.INFORMATION, "Cafeteria modoficada");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.showAndWait();
		this.getMain().getMiniPrimaryStage().close();
	}
	
	public Main getMain()
	{
		return this.main;
	}
	
	public String getCafeteria() 
	{
		return cafeteria;
	}
	
	public void setCafeteria(String endereco) 
	{
		this.cafeteria = endereco;
		for (int j = 0; j < this.getMain().getControlador().listarCafeterias().size(); j++) 
		{
			if (cafeteria.equalsIgnoreCase(this.getMain().getControlador().listarCafeterias().get(j)[1])) 
			{
				this.nome.setText(this.getMain().getControlador().listarFuncionarios().get(j)[0]);
				this.endereco.setText(this.getMain().getControlador().listarFuncionarios().get(j)[1]);
				this.telefone.setText(this.getMain().getControlador().listarFuncionarios().get(j)[2]);
			}
		}
	}
	public void setMain (Main main)
	{
		this.main = main;
		Alert a = new Alert(AlertType.INFORMATION, "Não preencha os campos que não deseja alterar");
		a.showAndWait();
	}
}