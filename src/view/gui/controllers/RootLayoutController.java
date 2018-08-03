package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import main.Main;

public class RootLayoutController implements Initializable
{
	private Main main;
	
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private javafx.scene.control.MenuItem loginBNT;
	@FXML
	private javafx.scene.control.MenuItem cadastroBTN;
	
	public void login (ActionEvent event)
	{
		this.getMain().getPrimaryStage().setTitle ("Login");
		this.getMain().showOnBorder ("/view/gui/fxml/LoginFirstScene.fxml", Controllers.LOGIN_FIRST);
	}
	
	public void cadastrar (ActionEvent event)
	{
		this.getMain().getPrimaryStage().setTitle ("Cadastro");
		this.getMain().showOnBorder ("/view/gui/fxml/CadastroChooseScene.fxml", Controllers.CADASTRO_CHOOSE);
	}
	
	public void close (ActionEvent event)
	{
		this.getMain().getPrimaryStage().close();
		this.getMain().getMiniPrimaryStage().close();
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