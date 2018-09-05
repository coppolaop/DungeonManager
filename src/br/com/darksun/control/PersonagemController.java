package br.com.darksun.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.com.darksun.entity.Personagem;

public class PersonagemController
{
	public List< Personagem > listarPJs( )
	{
		List< Personagem > personagens = new ArrayList< Personagem >( );
		File folder = new File("resources/pj/");
		File[ ] lista = folder.listFiles( );
		for(File file : lista)
			personagens.add( carregar( "resources/pj/" + file.getName( ) ) );
		return personagens;
	}
	
	public List< Personagem > listarPDMs( )
	{
		List< Personagem > personagens = new ArrayList< Personagem >( );
		File folder = new File("resources/pdm/");
		File[ ] lista = folder.listFiles( );
		for(File file : lista)
			personagens.add( carregar( "resources/pdm/" + file.getName( ) ) );
		return personagens;
	}

	public Personagem carregar( String path )
	{

		Properties prop = new Properties( );
		InputStream input = null;

		try
		{

			input = new FileInputStream( path );

			prop.load( input );

			Personagem personagem = new Personagem( );
			personagem.setIdPersonagem( Integer.parseInt( prop.getProperty( "idPersonagem" ) ) );
			personagem.setNome( prop.getProperty( "nome" ) );
			personagem.setClasse( prop.getProperty( "classe" ) );
			personagem.setImagem( prop.getProperty( "imagem" ) );
			personagem.setCa( Integer.parseInt( prop.getProperty( "ca" ) ) );
			personagem.setIniciativa( Integer.parseInt( prop.getProperty( "iniciativa" ) ) );
			personagem.setHpMaximo( Integer.parseInt( prop.getProperty( "hpMaximo" ) ) );
			personagem.setHpAtual( Integer.parseInt( prop.getProperty( "hpAtual" ) ) );

			// System.out.println("Personagem " + personagem.getNome( ) + " adicionado a
			// lista de personagens");

			return personagem;

		} catch ( IOException ex )
		{
			ex.printStackTrace( );
			return null;
		} finally
		{
			if ( input != null )
			{
				try
				{
					input.close( );
				} catch ( IOException e )
				{
					e.printStackTrace( );
				}
			}
		}

	}
}
