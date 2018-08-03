package view.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class VerCafeteriasController implements Initializable{
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
	private TableColumn<ListaSuport, String> endereco;
	@FXML
	private TableColumn<ListaSuport, String> telefone;
	@FXML
	private TableColumn<ListaSuport, ListaSuport> selectBNT;
		
	private ObservableList<ListaSuport> listaDeCafeterias()
	{
		ListaSuport[] c = new ListaSuport[this.getMain().getControlador().listarCafeterias().size()];
		for (int j = 0; j < this.getMain().getControlador().listarCafeterias().size(); j++)
		{
			c[j] = new ListaSuport (this.getMain().getControlador().listarCafeterias().get (j)[0], this.getMain().getControlador().listarCafeterias().get (j)[1], this.getMain().getControlador().listarCafeterias().get (j)[2], ""); 
		}
		return FXCollections.observableArrayList(c);
	}
	
	public void modificar(String endereco) {
		this.getMain().getMiniPrimaryStage().setTitle ("Modificar cafeteria");
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation (this.getClass().getResource ("/view/gui/fxml/ModificarCafeteriaScene.fxml"));
		try
		{
			root = fxmlLoader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		((ModificarCafeteriaController) fxmlLoader.getController()).setMain (this.getMain());
		((ModificarCafeteriaController) fxmlLoader.getController()).setCafeteria (endereco);
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
		endereco.setCellValueFactory (new PropertyValueFactory<>("item1"));
		telefone.setCellValueFactory (new PropertyValueFactory<>("item2"));
		selectBNT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		selectBNT.setCellFactory(param -> new TableCell<ListaSuport, ListaSuport>() {
			private final Button modificarBNT = new Button("Modificar");

			@Override
			protected void updateItem(ListaSuport person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				setGraphic(modificarBNT);
				modificarBNT.setOnAction(event -> modificar(person.getItem1()));
			}
		});
		tableView.setItems (listaDeCafeterias());
	}
}
