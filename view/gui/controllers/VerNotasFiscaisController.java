package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

public class VerNotasFiscaisController implements Initializable {
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
	private TableColumn<ListaSuport, String> data;
	@FXML
	private TableColumn<ListaSuport, ListaSuport> selectBNT;
		
	
	private ObservableList<ListaSuport> listaDeNotas()
	{
		ListaSuport[] c = new ListaSuport[this.getMain().getControlador().pegarVendasDiarias().size()];
		for (int j = 0; j < this.getMain().getControlador().pegarVendasDiarias().size(); j++)
		{
			c[j] = new ListaSuport (this.getMain().getControlador().pegarVendasDiarias().get (j)[0], this.getMain().getControlador().pegarVendasDiarias().get (j)[1], "", ""); 
		}
		return FXCollections.observableArrayList(c);
	}
	
	public void anular(String data)
	{
		this.getMain().getMiniPrimaryStage().setTitle ("Anular nota fiscal");
		Alert a = new Alert(AlertType.CONFIRMATION, "Deseja realmente anular está nota fiscal?");
		a.showAndWait();
		if (a.getResult() == ButtonType.OK) 
		{
			try {
				this.getMain().getControlador().cancelarPedido(data);
			}
			catch (Exception e) 
			{
				Alert b = new Alert(AlertType.ERROR, "Pedido Não pode ser cancelado");
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
	
	public void setMain (Main main)
	{
		this.main = main;
		nome.setCellValueFactory (new PropertyValueFactory<>("item0"));
		data.setCellValueFactory (new PropertyValueFactory<>("item1"));
		selectBNT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		selectBNT.setCellFactory(param -> new TableCell<ListaSuport, ListaSuport>() {
			private final Button anularBNT = new Button("Anular");

			@Override
			protected void updateItem(ListaSuport person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				setGraphic(anularBNT);
				anularBNT.setOnAction(event -> anular(person.getItem1()));
			}
		});
		tableView.setItems (listaDeNotas());
	}
}
