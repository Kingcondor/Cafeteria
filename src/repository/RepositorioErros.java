package repository;

import java.util.ArrayList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileReader;
import java.io.FileNotFoundException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import interfaces.IRepositorioErros;

public class RepositorioErros implements IRepositorioErros
{
	private static final RepositorioErros instancia = new RepositorioErros();
	private ArrayList<String> erros = new ArrayList<String>();
	private final String constanteInicioPrograma = "Ínicio do programa";
	
	private RepositorioErros()
	{
		erros.add (this.getConstanteInicioPrograma());
	}
	
	public static RepositorioErros getInstancia()
	{
		return instancia;
	}
	
	public String getConstanteInicioPrograma()
	{
		return this.constanteInicioPrograma;
	}
	
	public ArrayList<String> listarErros()
	{
		return this.erros;
	}
	
	public void registrarErro (Exception erro) throws Exception
	{
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		for (int i = erro.getStackTrace().length-1; i >= 0; i--)
		{
			erros.add (LocalDateTime.now().format(formato) + "   " + erro.getStackTrace()[i]);
		}
		erros.add (erro.toString());
		this.salvarLog();
	}
	
	public void salvarLog() throws Exception
	{
		File arquivo = new File ("erros.log");
		try
		{
			FileOutputStream escritor = new FileOutputStream (arquivo);
			PrintStream escritorString = new PrintStream (escritor);
			if (!escritorString.checkError())
			{
				for (String e : this.erros)
				{
					if (e.equals (this.getConstanteInicioPrograma()))
					{
						escritorString.print ("\n\n");
						escritorString.print (e);
						escritorString.print ("\n\n");
					}
					else
					{
						escritorString.print ("\n\n");
						escritorString.print (e);
					}
				}
			}
			escritor.close();
		}
		catch (FileNotFoundException erro2)
		{
			arquivo.createNewFile();
			FileOutputStream escritor = new FileOutputStream (arquivo);
			PrintStream escritorString = new PrintStream (escritor);
			for (String e : this.erros)
			{
				escritorString.print (e);
			}
			escritor.close();
		}
	}
	
	public void recuperarLog() throws Exception
	{
		erros.clear();
		try
		{
			FileReader arquivo = new FileReader ("erros.log");
			try
			{
				String erro = "";
				boolean lendo = true;
				while (lendo)
				{
					int c = arquivo.read();
					if ((char) c != '\n')
					{
						erro += (char) c;
					}
					else if (!erro.equals (""))
					{
						this.erros.add (erro);
						erro = "";
					}
					
					if (c == -1)
					{
						lendo = false;
						arquivo.close();
					}
				}
			}
			catch (Exception erro)
			{
				arquivo.close();
				throw erro;
			}
		}
		catch (FileNotFoundException excecao)
		{
			File arquivo;
			try
			{
				arquivo = new File ("erros.log");
			}
			catch (Exception excecao2)
			{
				arquivo = new File ("erros.log");
				arquivo.createNewFile();
				FileReader leitor = new FileReader ("erros.log");
				try
				{
					String erro = "";
					boolean lendo = true;
					while (lendo)
					{
						int c = leitor.read();
						if ((char) c != '\n')
						{
							erro += (char) c;
						}
						else if (!erro.equals (""))
						{
							this.erros.add (erro);
							erro = "";
						}
						
						if (c == -1)
						{
							lendo = false;
							leitor.close();
						}
					}
				}
				catch (Exception erro)
				{
					leitor.close();
					throw erro;
				}
			}
		}
	}
}