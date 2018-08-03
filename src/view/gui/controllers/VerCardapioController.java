package view.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.Main;

public class VerCardapioController implements Initializable{
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
	private TableColumn<ListaSuport, Boolean> selectedBNT;
	@FXML
	private TableView<ListaSuport> tableView2;
	@FXML
	private TableColumn<ListaSuport, String> nomeAtendente;
	@FXML
	private TableColumn<ListaSuport, Boolean> selectAtendenteBNT;
	@FXML
	private Button comprarBNT;
	
	private ObservableList<ListaSuport> listaDeProdutos()
	{
		ListaSuport[] c = new ListaSuport[this.getMain().getControlador().listarProdutos().size()];
		for (int j = 0; j < this.getMain().getControlador().listarProdutos().size(); j++)
		{
			c[j] = new ListaSuport (this.getMain().getControlador().listarProdutos().get (j)[0], this.getMain().getControlador().listarProdutos().get (j)[1], this.getMain().getControlador().listarProdutos().get (j)[2], "Clique aqui para digitar a quantidade.", false); 
		}
		return FXCollections.observableArrayList(c);
	}
	
	private ObservableList<ListaSuport> listaDeAtendentes()
	{
		ListaSuport[] c = new ListaSuport[this.getMain().getControlador().listarAtendentes().size()];
		for (int j = 0; j < this.getMain().getControlador().listarAtendentes().size(); j++)
		{
			c[j] = new ListaSuport (this.getMain().getControlador().listarAtendentes().get (j)[0], "", "", "", false); 
		}
		return FXCollections.observableArrayList(c);
	}
	
	public void comprar() {
		Alert a = new Alert (AlertType.CONFIRMATION, "Confirme sua compra");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.showAndWait();
		if (a.getResult() == ButtonType.OK)
		{
			try
			{
				ArrayList<String[]> produtosComprados= new ArrayList<>();
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				int j;
				for (j = 0; j< this.getMain().getControlador().listarProdutos().size(); j++)
				{
					if (tableView.getItems().get(j).isSelected())
					{
						try {
							if (Integer.parseInt(tableView.getItems().get(j).getItem3()) > 1 && Integer.parseInt(tableView.getItems().get(j).getItem3()) <= Double.parseDouble(this.getMain().getControlador().listarProdutos().get (j)[3])) 
							{
								String[] produtos = new String[4];
								produtos[0] = tableView.getItems().get(j).getItem0();
								produtos[1] = this.getMain().getControlador().listarProdutos().get (j)[3];
								produtos[2] = tableView.getItems().get(j).getItem1();
								produtos[3] = tableView.getItems().get(j).getItem2();
								produtosComprados.add(produtos);
								quantidades.add(Integer.parseInt(tableView.getItems().get(j).getItem3()));
							}
							else
							{
								Alert b = new Alert (AlertType.INFORMATION, "Não temos quantidade em estoque suficiente deste produto. Sentimos muito pelo incoveniente. Por favor escolha um valor abaixo de: " + this.getMain().getControlador().listarProdutos().get (j)[3]);
								b.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
								b.showAndWait();
								j = this.getMain().getControlador().listarProdutos().size() + 1;
							}
						}
						catch (Exception erro) {
							Alert b = new Alert (AlertType.INFORMATION, "Quantidade especificada inválida. Ex. de quantidade: 5");
							b.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
							b.showAndWait();
						}
					}
				}
				if (j == this.getMain().getControlador().listarProdutos().size() && !produtosComprados.isEmpty())
				{
					String atendente = "";
					int numVerificar = 0;
					for (j = 0; j < this.getMain().getControlador().listarAtendentes().size(); j++)
					{
						if (tableView2.getItems().get(j).isSelected()) 
						{
							atendente = tableView2.getItems().get(j).getItem0();
							numVerificar++;
						}
					}
					if (numVerificar == 1)
					{
						ArrayList<String[]> nota;
						try {
							nota = this.getMain().getControlador().fazerPedido(atendente, produtosComprados, quantidades);
							this.getMain().getMiniPrimaryStage().setTitle ("Nota fiscal");
							Parent root2 = null;
							FXMLLoader fxmlLoader2 = new FXMLLoader();
							fxmlLoader2.setLocation (this.getClass().getResource ("/view/gui/fxml/VerNotaFiscalScene.fxml"));
							try
							{
								root2 = fxmlLoader2.load();
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}
							((VerNotaFiscalController) fxmlLoader2.getController()).setMain (this.getMain());
							((VerNotaFiscalController) fxmlLoader2.getController()).setNota(nota);
							this.getMain().setMiniRootLayout(new AnchorPane(root2));
							Scene minicena = new Scene(this.getMain().getMiniRootLayout());
							minicena.getStylesheets().add("/view/gui/css/css.css");
							this.getMain().getMiniPrimaryStage().setScene (minicena);
							this.getMain().getMiniPrimaryStage().show();
						} 
						catch (Exception e) 
						{
							Alert b = new Alert(AlertType.ERROR, "Não foi possível gerar uma nota fiscal");
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
					else if (numVerificar == 0)
					{
						Alert b = new Alert(AlertType.ERROR, "Nenhum funcionário selecionado");
						b.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
						b.showAndWait();
					}
					else if (numVerificar > 1)
					{
						Alert b = new Alert(AlertType.ERROR, "Mais de um funcionário selecionado");
						b.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
						b.showAndWait();
					}
				}
			}
			catch (NullPointerException erro)
			{
				Alert b = new Alert(AlertType.ERROR, "ERRO");
				b.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
				b.showAndWait();
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
		preco.setCellValueFactory (new PropertyValueFactory<>("item1"));
		descricao.setCellValueFactory (new PropertyValueFactory<>("item2"));
		qnt.setCellValueFactory (new PropertyValueFactory<>("item3"));
		
		selectedBNT.setCellValueFactory(new Callback<CellDataFeatures<ListaSuport, Boolean>, ObservableValue<Boolean>>() {
			 
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<ListaSuport, Boolean> param) {
                ListaSuport item = param.getValue();
 
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(item.isSelected());
                booleanProp.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                            Boolean newValue) {
                        item.setItem4(newValue);
                    }
                });
                return booleanProp;
            }
        });
 
        selectedBNT.setCellFactory(new Callback<TableColumn<ListaSuport, Boolean>, //
        TableCell<ListaSuport, Boolean>>() {
            @Override
            public TableCell<ListaSuport, Boolean> call(TableColumn<ListaSuport, Boolean> p) {
                CheckBoxTableCell<ListaSuport, Boolean> cell = new CheckBoxTableCell<ListaSuport, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        
		qnt.setCellFactory(TextFieldTableCell.<ListaSuport> forTableColumn());
		qnt.setOnEditCommit((CellEditEvent<ListaSuport, String> event) -> {
            TablePosition<ListaSuport, String> pos = event.getTablePosition();
 
            String newQnt = event.getNewValue();
 
            int row = pos.getRow();
            ListaSuport person = event.getTableView().getItems().get(row);
            person.setItem3(newQnt);
        });
		tableView.setItems (listaDeProdutos());
		nomeAtendente.setCellValueFactory(new PropertyValueFactory<>("item0"));
		
		selectAtendenteBNT.setCellValueFactory(new Callback<CellDataFeatures<ListaSuport, Boolean>, ObservableValue<Boolean>>() {
			 
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<ListaSuport, Boolean> param) {
                ListaSuport item = param.getValue();
 
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(item.isSelected());
                booleanProp.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                            Boolean newValue) {
                        item.setItem4(newValue);
                    }
                });
                return booleanProp;
            }
        });
 
        selectAtendenteBNT.setCellFactory(new Callback<TableColumn<ListaSuport, Boolean>, //
        TableCell<ListaSuport, Boolean>>() {
            @Override
            public TableCell<ListaSuport, Boolean> call(TableColumn<ListaSuport, Boolean> p) {
                CheckBoxTableCell<ListaSuport, Boolean> cell = new CheckBoxTableCell<ListaSuport, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
		tableView2.setItems(listaDeAtendentes());
	}
}
