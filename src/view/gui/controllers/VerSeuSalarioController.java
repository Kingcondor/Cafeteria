package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import main.Main;

public class VerSeuSalarioController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML 
	public Label salario;
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		salario.setText (String.valueOf (this.getMain().getControlador().pegarSalario()));
	}
}