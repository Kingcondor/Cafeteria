package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

public class VerAlertasController implements Initializable{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	@FXML
	private TableView<ListaSuport> tableView;
	@FXML
	private TableColumn<ListaSuport, String> data;
	@FXML
	private TableColumn<ListaSuport, String> assunto;
	@FXML
	private TableColumn<ListaSuport, String> emissor;
	@FXML
	private TableColumn<ListaSuport, ListaSuport> selectBNT;
	
	private String nomeSelecionado;
	
	private ObservableList<ListaSuport> listaDeAlertas()
	{
		ListaSuport[] c = new ListaSuport[this.getMain().getControlador().pegarAlertas().size()];
		for (int j = 0; j < this.getMain().getControlador().pegarAlertas().size(); j++)
		{
			c[j] = new ListaSuport (this.getMain().getControlador().pegarAlertas().get (j)[2], this.getMain().getControlador().pegarAlertas().get (j)[1], this.getMain().getControlador().pegarAlertas().get (j)[0], ""); 
		}
		return FXCollections.observableArrayList(c);
	}
	
	public void selecionar(String data) 
	{
		for (int j = 0; j < this.getMain().getControlador().pegarAlertas().size(); j++)
		{
			if (data.equalsIgnoreCase(this.getMain().getControlador().pegarAlertas().get(j)[2])) 
			{
				Alert a = new Alert(AlertType.INFORMATION, this.getMain().getControlador().pegarAlertas().get (j)[3]);
				a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				a.show();
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
		data.setCellValueFactory (new PropertyValueFactory<>("item0"));
		assunto.setCellValueFactory (new PropertyValueFactory<>("item1"));
		emissor.setCellValueFactory (new PropertyValueFactory<>("item2"));
		selectBNT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		selectBNT.setCellFactory(param -> new TableCell<ListaSuport, ListaSuport>() {
			private final Button modificarBNT = new Button("Ler");

			@Override
			protected void updateItem(ListaSuport person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				setGraphic(modificarBNT);
				modificarBNT.setOnAction(event -> selecionar(person.getItem0()));
			}
		});
		tableView.setItems (listaDeAlertas());
	}

	public String getNomeSelecionado() {
		return nomeSelecionado;
	}

	public void setNomeSelecionado(String nomeSelecionado) {
		this.nomeSelecionado = nomeSelecionado;
	}
}
