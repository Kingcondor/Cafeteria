package view.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import main.Main;

public class GerenteOpcoesController implements Initializable
{
	private Main main;
	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
	}
	
	@FXML
	private Label nomeDoGerente;
	
	public void mudarSeuNome()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarNomeScene.fxml", "Nome", Controllers.MUDAR_NOME);
	}
	
	public void mudarSuaSenha()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarSenhaScene.fxml", "Senha", Controllers.MUDAR_SENHA);
	}
	
	public void verSeuSalario()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerSeuSalarioScene.fxml", "Salário", Controllers.VER_SEU_SALARIO);
	}
	
	public void verSeuNumero()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerSeuTelefoneScene.fxml", "Telefone", Controllers.VER_SEU_TELEFONE);
	}
	
	public void mudarTelefone()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarTelefoneScene.fxml", "Telefone", Controllers.MUDAR_TELEFONE);
	}
	
	public void cadastrarNovoFuncionario()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/CadastrarNovoFuncionarioScene.fxml", "Cadastrar novo funcionário", Controllers.CADASTRAR_NOVO_FUNCIONARIO);
	}
	
	public void demitirFuncionario()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/DemitirFuncionarioScene.fxml", "Demitir funcionário", Controllers.DEMITIR_FUNCIONARIO);
	}
	
	public void verFuncionarios()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerFuncionariosScene.fxml", "funcionários", Controllers.VER_FUNCIONARIOS);
	}
	
	public void emitirAlerta()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/EmitirAlertaScene.fxml", "Alerta", Controllers.EMITIR_ALERTA);
	}
	
	public void mudarSeuSalario()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/MudarSeuSalarioScene.fxml", "Salário", Controllers.MUDAR_SEU_SALARIO);
	}
	
	public void verFrequencia()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerFrequenciaFuncionarioScene.fxml", "Frequencia do funcion�rio", Controllers.VER_FREQUENCIA);
	}
	
	public void verNotas()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerNotasFiscaisScene.fxml", "Notas fiscais", Controllers.VER_NOTAS);
	}
	
	public void verVendas()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerVendasScene.fxml", "Vendas", Controllers.VER_VENDAS);
	}
	
	public void verProdutos()
	{
		this.getMain().showOnBorder ("/view/gui/fxml/VerProdutosScene.fxml", "Produtos", Controllers.VER_PRODUTOS);
	}

	public Main getMain()
	{
		return this.main;
	}
	
	public void setMain (Main main)
	{
		this.main = main;
		nomeDoGerente.setText (this.getMain().getControlador().pegarNome());
	}
}