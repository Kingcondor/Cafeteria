package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.Terminal;
import view.gui.controllers.*;

import enums.Controllers;

import model.ControladorFacade;

public class Main extends Application
{
	private Stage primaryStage;
	private Stage miniPrimaryStage = new Stage();
	private ControladorFacade controlador;
	private BorderPane rootLayout;
	private AnchorPane miniRootLayout;
	private Terminal terminal;
	
	public void start (Stage primaryStage)
	{
		try
		{
			controlador = new ControladorFacade();
		}
		catch (Exception erro)
		{
			erro.printStackTrace();
			return;
		}
		this.setPrimaryStage (primaryStage);
		if (controlador.verificarPrimeiroInicio())
		{
			this.getPrimaryStage().setTitle ("Cadastro Inicial");
			try
			{
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(this.getClass().getResource("/view/gui/fxml/CadastroPrimeiraCafeteria.fxml"));
				BorderPane BP = new BorderPane (fxmlLoader.load());
				((CadastroPrimeiraCafeteriaController) fxmlLoader.getController()).setMain (this);
				Scene cena = new Scene (BP);
				cena.getStylesheets().add ("/view/gui/css/css.css");
				this.getPrimaryStage().setScene (cena);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				this.setTerminal(new Terminal (controlador));
				return;
			}
			Alert primeiraVez = new Alert (AlertType.INFORMATION);
			primeiraVez.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
			primeiraVez.setContentText ("Como esta é sua primeira vez, é necessário fazer o cadastro antes de iniciar o aplicativo");
			primeiraVez.showAndWait();
		}
		else
		{
			this.getPrimaryStage().setTitle ("Login");
			this.initRootLayout();
			showOnBorder ("/view/gui/fxml/LoginFirstScene.fxml", Controllers.LOGIN_FIRST);
		}
		this.getPrimaryStage().show();
	}
	
	public Terminal getTerminal()
	{
		return this.terminal;
	}
	
	public void setTerminal(Terminal terminal)
	{
		this.terminal = terminal;
	}
	
	public void initRootLayout()
	{
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation (this.getClass().getResource ("/view/gui/fxml/RootLayout.fxml"));
			this.setRootLayout((BorderPane) fxmlLoader.load());
			Scene root = new Scene (this.getRootLayout());
			root.getStylesheets().add ("/view/gui/css/css.css");
			((RootLayoutController) fxmlLoader.getController()).setMain (this);
			this.getPrimaryStage().setScene (root);
			this.getPrimaryStage().show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void showOnBorder (String url, Controllers tipo)
	{
		Parent root = null;
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation (this.getClass().getResource (url));
			root = fxmlLoader.load();
			if (tipo.equals (Controllers.CADASTRO_CHOOSE))
			{
				((CadastroChooseController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.CADASTRO_CLIENTE))
			{
				((CadastroClienteController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.CADASTRO_FUNCIONARIO))
			{
				((CadastroFuncionarioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.CADASTRO_PRIMEIRA_CAFETERIA))
			{
				((CadastroPrimeiraCafeteriaController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.CLIENTE_OPCOES))
			{
				((ClienteOpcoesController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.COMUM_OPCOES))
			{
				((ComumOpcoesController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.FUNCIONARIO_OPCOES))
			{
				((AtendenteOpcoesController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.GERENTE_OPCOES))
			{
				((GerenteOpcoesController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.GERENTE_PRINCIPAL_OPCOES))
			{
				((GerentePrincipalOpcoesController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.LOGIN_FIRST))
			{
				((LoginFirstController) fxmlLoader.getController()).setMain (this);
				
				/*Image logo = new Image(getClass().getResource ("/view/gui/img/logo.png").toExternalForm(), 600, 300, false, false);
				ImageView logoView = new ImageView();
				logoView.setImage (logo);
				this.getRootLayout().setTop (logoView);*/
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.getRootLayout().setCenter(root);
	}
	
	public void showOnBorder (String url, String tittle, Controllers tipo)
	{
		this.getMiniPrimaryStage().setTitle (tittle);
		Parent root = null;
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation (this.getClass().getResource (url));
			root = fxmlLoader.load();
			if (tipo.equals (Controllers.ADICIONAR_PRODUTO))
			{
				((AdicionarProdutoController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.CADASTRAR_NOVA_CAFETERIA))
			{
				((CadastrarNovaCafeteriaController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.CADASTRAR_NOVO_FUNCIONARIO))
			{
				((CadastrarNovoFuncionarioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.DEMITIR_FUNCIONARIO))
			{
				((DemitirFuncionarioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.EMITIR_ALERTA))
			{
				((EmitirAlertaController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.FAZER_PEDIDO))
			{
				((VerCardapioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.MUDAR_NOME))
			{
				((MudarNomeController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.MUDAR_SENHA))
			{
				((MudarSenhaController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.MUDAR_SEU_SALARIO))
			{
				((MudarSeuSalarioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.MUDAR_TELEFONE))
			{
				((MudarTelefoneController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.ROOT_LAYOUT))
			{
				((RootLayoutController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_ALERTAS))
			{
				((VerAlertasController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_CAFETERIAS))
			{
				((VerCafeteriasController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_CARDAPIO))
			{
				((VerCardapioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_FREQUENCIA))
			{
				((VerFrequenciaFuncionarioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_FUNCIONARIOS))
			{
				((VerFuncionariosController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_HISTORICO))
			{
				((VerHistoricoVendasController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_NOTA_FISCAL))
			{
				((VerNotaFiscalController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_NOTAS))
			{
				((VerNotasFiscaisController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_PRODUTOS))
			{
				((VerProdutosController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_SEU_SALARIO))
			{
				((VerSeuSalarioController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_SEU_TELEFONE))
			{
				((VerSeuTelefoneController) fxmlLoader.getController()).setMain (this);
			}
			else if (tipo.equals (Controllers.VER_VENDAS))
			{
				((VerVendasController) fxmlLoader.getController()).setMain (this);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.setMiniRootLayout(new AnchorPane(root));
		Scene miniCena = new Scene (this.getMiniRootLayout());
		miniCena.getStylesheets().add ("/view/gui/css/css.css");
		this.getMiniPrimaryStage().setScene (miniCena);
		this.getMiniPrimaryStage().show();
	}
	
	public void mostrarErro (Exception erro)
	{
		Alert a = new Alert (AlertType.ERROR, "Erro");
		a.getDialogPane().getStylesheets().add (getClass().getResource("/view/gui/css/css.css").toExternalForm());
		a.setHeaderText (erro.getLocalizedMessage());
		a.showAndWait();
	}
	
	public Stage getPrimaryStage()
	{
		return this.primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
	
	public Stage getMiniPrimaryStage()
	{
		return this.miniPrimaryStage;
	}

	public void setMiniPrimaryStage(Stage miniPrimaryStage)
	{
		this.miniPrimaryStage = miniPrimaryStage;
	}

	public ControladorFacade getControlador()
	{
		return controlador;
	}
	
	public void setControlador (ControladorFacade novoControlador)
	{
		controlador = novoControlador;
	}
	
	public BorderPane getRootLayout()
	{
		return this.rootLayout;
	}
	
	public void setRootLayout (BorderPane rootLayout)
	{
		this.rootLayout = rootLayout;
	}
	
	public AnchorPane getMiniRootLayout()
	{
		return this.miniRootLayout;
	}
	
	public void setMiniRootLayout (AnchorPane miniRootLayout)
	{
		this.miniRootLayout = miniRootLayout;
	}
	
	public static void main (String[] args)
	{
		launch();
	}
}