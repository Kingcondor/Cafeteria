package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import main.Main;

public class VerSeuTelefoneController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML 
	public Label telefone;
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		if (this.getMain().getControlador().pegarNumeroTelefone() != null)
		{
			telefone.setText (this.getMain().getControlador().pegarNumeroTelefone());
		}
		else
		{
			telefone.setText ("Você não tem número de telefone registrado");
		}		
	}
}