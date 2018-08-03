package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;

public class ModificarProdutoController implements Initializable {
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
	private Label preco;
	@FXML
	private TextField novoPreco;
	@FXML
	private Label descricao;
	@FXML
	private TextArea novaDescricao;
	@FXML
	private Label quantidade;
	@FXML
	private TextField novaQuantidade;
	
	private String nomeDoProduto;
	
	public void mudarProdutoKey (KeyEvent kEvent)
	{
		if (kEvent.getCode() == KeyCode.ENTER)
		{
			mudarProduto();
		}
	}
	
	public void mudarProduto()
	{
		try {
			if (!novaQuantidade.getText().equalsIgnoreCase("")) 
			{
				this.getMain().getControlador().removerProduto(nome.getText());
				this.getMain().getControlador().adicionarProduto(nome.getText(), Double.parseDouble(preco.getText()), descricao.getText(), Integer.parseInt(novaQuantidade.getText()));
				quantidade.setText(novaQuantidade.getText());
			}
			if (!novaDescricao.getText().equalsIgnoreCase("")) 
			{
				this.getMain().getControlador().removerProduto(nome.getText());
				this.getMain().getControlador().adicionarProduto(nome.getText(), Double.parseDouble(preco.getText()), novaDescricao.getText(), Integer.parseInt(quantidade.getText()));
				descricao.setText(novaDescricao.getText());			}
			if (!novoPreco.getText().equalsIgnoreCase("")) 
			{
				this.getMain().getControlador().removerProduto(nome.getText());
				this.getMain().getControlador().adicionarProduto(nome.getText(), Double.parseDouble(novoPreco.getText()), descricao.getText(), Integer.parseInt(quantidade.getText()));
				preco.setText(novoPreco.getText());			}
			if (!novoNome.getText().equalsIgnoreCase("")) 
			{
				this.getMain().getControlador().removerProduto(nome.getText());
				this.getMain().getControlador().adicionarProduto(novoNome.getText(), Double.parseDouble(preco.getText()), descricao.getText(), Integer.parseInt(quantidade.getText()));
				nome.setText(novoNome.getText());		
			}
			Alert a = new Alert(AlertType.INFORMATION, "Produto modoficado");
			a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			a.showAndWait();
			this.getMain().getMiniPrimaryStage().close();
		}
		catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR, "Algum dos campos está incorreto");
			a.showAndWait();
		}
	}
	
	public void remover() 
	{
		Alert a = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja remover este produto?");
		a.showAndWait();
		if (a.getResult() == ButtonType.OK) 
		{
			try 
			{
				this.getMain().getControlador().removerProduto(nome.getText());
				Alert b = new Alert(AlertType.INFORMATION, "Produto removido");
				b.showAndWait();
				this.getMain().getMiniPrimaryStage().close();
			} 
			catch (Exception e) 
			{
				Alert b = new Alert(AlertType.ERROR, "Não foi possível remover o produto");
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
	
	public Main getMain()
	{
		return this.main;
	}
	
	public String getNomeDoProduto() 
	{
		return nomeDoProduto;
	}
	
	public void setNomeDoProduto(String nomeDoProduto) 
	{
		this.nomeDoProduto = nomeDoProduto;
		for (int j = 0; j < this.getMain().getControlador().listarProdutos().size(); j++) 
		{
			if (nomeDoProduto.equalsIgnoreCase(this.getMain().getControlador().listarProdutos().get(j)[0])) 
			{
				this.nome.setText(this.getMain().getControlador().listarProdutos().get(j)[0]);
				this.preco.setText(this.getMain().getControlador().listarProdutos().get(j)[1]);
				this.descricao.setText(this.getMain().getControlador().listarProdutos().get(j)[2]);
				this.quantidade.setText(this.getMain().getControlador().listarProdutos().get(j)[3]);
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
		Alert a = new Alert(AlertType.INFORMATION, "Este produto não existe");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.showAndWait();
	}
}
