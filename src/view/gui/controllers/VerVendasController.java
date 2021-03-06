package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import main.Main;

public class VerVendasController implements Initializable
{
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
	private TableColumn<ListaSuport, String> lucro;
	
	private ObservableList<ListaSuport> listaDeVendas()
	{
		ListaSuport[] c = new ListaSuport[this.getMain().getControlador().pegarVendasDiarias().size()];
		for (int j = 0; j < this.getMain().getControlador().pegarVendasDiarias().size(); j++)
		{
			c[j] = new ListaSuport (this.getMain().getControlador().pegarVendasDiarias().get (j)[1], this.getMain().getControlador().pegarVendasDiarias().get (j)[2], "", ""); 
		}
		return FXCollections.observableArrayList(c);
	}
	
	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		data.setCellValueFactory (new PropertyValueFactory<>("item0"));
		lucro.setCellValueFactory (new PropertyValueFactory<>("item1"));
		tableView.setItems (listaDeVendas());
	}
}