package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import main.Main;

public class VerFrequenciaFuncionarioController implements Initializable{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private TextField nome;
	@FXML
	private DatePicker data;
	
	public void verificarKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER) 
		{
			verificar();
		}
	}
	
	public void verificar() 
	{
		if (!nome.getText().equalsIgnoreCase("")) 
		{
			if (this.getMain().getControlador().verificarPresenca (nome.getText(), data.getValue())) 
			{
				Alert a = new Alert(AlertType.INFORMATION, "Este usuário estava presente na data informada");
				a.showAndWait();
			}
			else 
			{
				Alert a = new Alert(AlertType.INFORMATION, "Este usuário Não estava presente na data informada");
				a.showAndWait();
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
