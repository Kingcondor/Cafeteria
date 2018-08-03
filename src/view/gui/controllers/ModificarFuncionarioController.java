package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;

public class ModificarFuncionarioController implements Initializable{
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
	private Label cpf;
	@FXML
	private TextField novoCpf;
	@FXML
	private Label endereco;
	@FXML
	private TextField novoEndereco;
	@FXML
	private Label telefone;
	@FXML
	private TextField novoTelefone;
	
	private String nomeDoFuncionario;
	
	public void mudarFuncionarioKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			mudarFuncionario();
		}
	}
	
	public void mudarFuncionario()
	{
		if (!novoCpf.getText().equalsIgnoreCase("")) 
		{
			try
			{
				this.getMain().getControlador().definirCpf (this.getNomeDoFuncionario(), novoCpf.getText());
			}
			catch (Exception e)
			{
				this.funcionarioInex();
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
		if (!novoEndereco.getText().equalsIgnoreCase("")) 
		{
			try
			{
				this.getMain().getControlador().definirEndereco (this.getNomeDoFuncionario(), novoEndereco.getText());
			}
			catch (Exception e)
			{
				this.funcionarioInex();
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
		if (!novoTelefone.getText().equalsIgnoreCase("")) 
		{
			try
			{
				this.getMain().getControlador().definirNumeroTelefone (this.getNomeDoFuncionario(), novoTelefone.getText());
			}
			catch (Exception e)
			{
				this.funcionarioInex();
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
			try 
			{
				if (!this.getMain().getControlador().verificarIgual(novoNome.getText())) 
				{
					this.getMain().getControlador().mudarNomeFuncionario (this.getNomeDoFuncionario(), novoNome.getText());
				}
				else 
				{
					Alert b = new Alert(AlertType.WARNING, "J� existe um usu�rio com este nome.");
					b.showAndWait();
				}
			} 
			catch (Exception e) {
				this.funcionarioInex();
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
		Alert a = new Alert(AlertType.INFORMATION, "Usuário modoficado");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.showAndWait();
		this.getMain().getMiniPrimaryStage().close();
	}
	
	public Main getMain()
	{
		return this.main;
	}
	
	public String getNomeDoFuncionario() 
	{
		return nomeDoFuncionario;
	}
	
	public void setNomeDoFuncionario(String nomeDoFuncionario) 
	{
		this.nomeDoFuncionario = nomeDoFuncionario;
		for (int j = 0; j < this.getMain().getControlador().listarFuncionarios().size(); j++) 
		{
			if (nomeDoFuncionario.equalsIgnoreCase(this.getMain().getControlador().listarFuncionarios().get(j)[0])) 
			{
				this.nome.setText(this.getMain().getControlador().listarFuncionarios().get(j)[0]);
				this.cpf.setText(this.getMain().getControlador().listarFuncionarios().get(j)[2]);
				this.endereco.setText(this.getMain().getControlador().listarFuncionarios().get(j)[3]);
				this.telefone.setText(this.getMain().getControlador().listarFuncionarios().get(j)[4]);
			}
		}
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		Alert a = new Alert(AlertType.INFORMATION, "Não preencha os campos que não deseja alterar");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.showAndWait();
	}
	
	public void funcionarioInex()
	{
		Alert a = new Alert(AlertType.INFORMATION, "Funcionário não existe");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.showAndWait();
	}
}
