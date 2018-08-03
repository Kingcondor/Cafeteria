package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.Main;

public class AdicionarProdutoController implements Initializable{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private TextField nome;
	@FXML
	private TextField preco;
	@FXML
	private TextArea descricao;
	@FXML
	private TextField qnt;
	@FXML
	private Button add;
	
	public void adicionarProduto() 
	{
		try 
		{
			if (!nome.getText().equalsIgnoreCase("") && !preco.getText().equalsIgnoreCase("") && !descricao.getText().equalsIgnoreCase("") && !qnt.getText().equalsIgnoreCase("")) 
			{
				this.getMain().getControlador().adicionarProduto(nome.getText(), Double.parseDouble(preco.getText()), descricao.getText(), Integer.parseInt(qnt.getText()));
				Alert a = new Alert(AlertType.INFORMATION, "Produto adicionado com sucesso");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
				this.getMain().getMiniPrimaryStage().close();
			}
			else 
			{
				Alert a = new Alert(AlertType.ERROR, "Algum dos campos não está preenchido");
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.showAndWait();
			}
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR, "Erro");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
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
