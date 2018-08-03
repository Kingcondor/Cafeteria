package view.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class VerProdutosController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private TableView<ListaSuport> tableView;
	@FXML
	private TableColumn<ListaSuport, String> nome;
	@FXML
	private TableColumn<ListaSuport, String> preco;
	@FXML
	private TableColumn<ListaSuport, String> descricao;
	@FXML
	private TableColumn<ListaSuport, String> qnt;
	@FXML
	private TableColumn<ListaSuport, ListaSuport> modificarBNT;
	@FXML
	private Button add;
	
	private ObservableList<ListaSuport> listaDeProdutos()
	{
		ListaSuport[] c = new ListaSuport[this.getMain().getControlador().listarProdutos().size()];
		for (int j = 0; j < this.getMain().getControlador().listarProdutos().size(); j++)
		{
			c[j] = new ListaSuport (this.getMain().getControlador().listarProdutos().get (j)[0], this.getMain().getControlador().listarProdutos().get (j)[1], this.getMain().getControlador().listarProdutos().get (j)[2], this.getMain().getControlador().listarProdutos().get (j)[3]); 
		}
		return FXCollections.observableArrayList(c);
	}
	
	public void adicionarProduto() 
	{
		this.getMain().showOnBorder("/view/gui/fxml/AdicionarProdutoScene.fxml", "Adicionar produto", Controllers.ADICIONAR_PRODUTO);
	}
	
	public void modificar(String nome) 
	{
		this.getMain().getMiniPrimaryStage().setTitle ("Modificar produto");
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation (this.getClass().getResource ("/view/gui/fxml/ModificarProdutoScene.fxml"));
		try
		{
			root = fxmlLoader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		((ModificarProdutoController) fxmlLoader.getController()).setMain (this.getMain());
		((ModificarProdutoController) fxmlLoader.getController()).setNomeDoProduto (nome);
		this.getMain().setMiniRootLayout(new AnchorPane(root));
		Scene minicena = new Scene(this.getMain().getMiniRootLayout());
		minicena.getStylesheets().add("/view/gui/css/css.css");
		this.getMain().getMiniPrimaryStage().setScene (minicena);
		this.getMain().getMiniPrimaryStage().show();
	}
	
	
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		nome.setCellValueFactory (new PropertyValueFactory<>("item0"));
		preco.setCellValueFactory (new PropertyValueFactory<>("item1"));
		descricao.setCellValueFactory (new PropertyValueFactory<>("item2"));
		qnt.setCellValueFactory (new PropertyValueFactory<>("item3"));
		modificarBNT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		modificarBNT.setCellFactory(param -> new TableCell<ListaSuport, ListaSuport>() {
			private final Button modificarBNT = new Button("Modificar/Remover");

			@Override
			protected void updateItem(ListaSuport person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				setGraphic(modificarBNT);
				modificarBNT.setOnAction(event -> modificar(person.getItem0()));
			}
		});
		tableView.setItems (listaDeProdutos());
	}
}