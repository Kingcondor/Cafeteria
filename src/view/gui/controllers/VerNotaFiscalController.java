package view.gui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

public class VerNotaFiscalController implements Initializable{
	private Main main;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{	
	}
	
	@FXML
	private TableView<ListaSuport> tableView;
	@FXML
	private TableColumn<ListaSuport, String> nomeDoProduto;
	@FXML
	private TableColumn<ListaSuport, String> preco;
	@FXML
	private Label horario;
	@FXML
	private Label precoTotal;
	
	private ArrayList<String[]> nota;
	
	public void setNota(ArrayList<String[]> nota) 
	{
		this.nota = nota;
		this.horario.setText(nota.get(0)[0]);
		this.precoTotal.setText(nota.get(nota.size()-1)[0]);
		nomeDoProduto.setCellValueFactory (new PropertyValueFactory<>("item0"));
		preco.setCellValueFactory (new PropertyValueFactory<>("item1"));
		tableView.setItems (listaDeVendas());
	}
	
	private ObservableList<ListaSuport> listaDeVendas()
	{
		ListaSuport[] c = new ListaSuport[this.nota.size()];
		for (int j = 1; j < this.nota.size() - 1; j++)
		{
			c[j] = new ListaSuport (this.nota.get (j)[0], this.nota.get (j)[1], "", ""); 
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
	}
}
