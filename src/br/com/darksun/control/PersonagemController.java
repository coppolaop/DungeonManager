package br.com.darksun.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.com.darksun.entity.Personagem;

public class PersonagemController
{
	private Integer newID = 0;

	public List< Personagem > listarPJs( )
	{
		List< Personagem > personagens = new ArrayList< Personagem >( );
		File folder = new File( "resources/pj/" );
		File[ ] lista = folder.listFiles( );
		for ( File file : lista )
			personagens.add( carregar( "resources/pj/" + file.getName( ), true ) );
		return personagens;
	}

	public List< Personagem > listarPJsAtivos( )
	{
		List< Personagem > ativos = new ArrayList< Personagem >( );

		for ( Personagem personagem : listarPJs( ) )
			if ( personagem.getStatus( ) == true )
				ativos.add( personagem );

		return ativos;
	}

	public List< Personagem > listarPDMs( )
	{
		List< Personagem > personagens = new ArrayList< Personagem >( );
		File folder = new File( "resources/pdm/" );
		File[ ] lista = folder.listFiles( );
		for ( File file : lista )
			personagens.add( carregar( "resources/pdm/" + file.getName( ), false ) );
		return personagens;
	}

	public List< Personagem > listarPDMsAtivos( )
	{
		List< Personagem > ativos = new ArrayList< Personagem >( );

		for ( Personagem personagem : listarPDMs( ) )
			if ( personagem.getStatus( ) == true )
				ativos.add( personagem );

		return ativos;
	}

	public Personagem carregar( String path, Boolean isPj )
	{

		Properties prop = new Properties( );
		InputStream input = null;

		try
		{

			input = new FileInputStream( path );

			prop.load( input );

			Personagem personagem = new Personagem( );

			Integer id = Integer.parseInt( prop.getProperty( "idPersonagem" ) );
			if ( id >= newID )
				newID = id + 1;

			personagem.setIdPersonagem( id );
			personagem.setFilePath( path );
			personagem.setNome( prop.getProperty( "nome" ) );
			personagem.setClasse( prop.getProperty( "classe" ) );
			personagem.setImagem( prop.getProperty( "imagem" ) );
			personagem.setCa( Integer.parseInt( prop.getProperty( "ca" ) ) );
			personagem.setBonusIniciativa( Integer.parseInt( prop.getProperty( "bonusIniciativa" ) ) );
			personagem.setHpMaximo( Integer.parseInt( prop.getProperty( "hpMaximo" ) ) );
			personagem.setHpAtual( Integer.parseInt( prop.getProperty( "hpAtual" ) ) );
			personagem.setIsPJ( isPj );
			personagem.setStatus( Boolean.parseBoolean( prop.getProperty( "status" ) ) );

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

	public void criarPersonagemAleatorio( Boolean pj )
	{
		Properties prop = new Properties( );
		OutputStream output = null;

		try
		{

			if ( pj )
			{
				output = new FileOutputStream( "resources/pj/PJdeExemplo.properties" );

				prop.setProperty( "idPersonagem", "1" );
				prop.setProperty( "nome", "PJ de Exemplo" );
				prop.setProperty( "classe", "Exemplo" );
				prop.setProperty( "imagem", "pjExemplo.jpg" );
			} else
			{
				output = new FileOutputStream( "resources/pdm/PDMdeExemplo.properties" );

				prop.setProperty( "idPersonagem", "2" );
				prop.setProperty( "nome", "PDM de Exemplo" );
				prop.setProperty( "classe", "Monstro" );
				prop.setProperty( "imagem", "pdmExemplo.jpg" );
			}
			prop.setProperty( "ca", "12" );
			prop.setProperty( "bonusIniciativa", "1" );
			prop.setProperty( "hpMaximo", "100" );
			prop.setProperty( "hpAtual", "10" );
			prop.setProperty( "status", "true" );

			prop.store( output, null );

		} catch ( IOException io )
		{
			io.printStackTrace( );
		} finally
		{
			if ( output != null )
			{
				try
				{
					output.close( );
				} catch ( IOException e )
				{
					e.printStackTrace( );
				}
			}

		}
	}

	public String newId( )
	{
		return newID.toString( );
	}

	public void criarPersonagem( Personagem personagem )
	{
		criarPersonagem( personagem.getIdPersonagem( ).toString( ), personagem.getFilePath( ), personagem.getNome( ),
				personagem.getClasse( ), personagem.getCa( ).toString( ), personagem.getBonusIniciativa( ).toString( ),
				personagem.getHpAtual( ).toString( ), personagem.getHpMaximo( ).toString( ), personagem.getImagem( ),
				personagem.getIsPJ( ), personagem.getStatus( ) );
	}

	public void criarPersonagem(	String ID, String nome, String classe, String CA, String bonusIniciativa,
									String hpMaximo, Boolean isPJ )
	{
		String path;
		
		if ( isPJ )
			path = "resources/pj/" + nome.replace( "\\", "_" ).replaceAll( "[ /|<>*:“\"]", "_" ) + ".properties";
		else
			path = "resources/pdm/" + nome.replace( "\\", "_" ).replaceAll( "[ /|<>*:“\"]", "_" ) + ".properties";
		
		criarPersonagem( ID, path, nome, classe, CA, bonusIniciativa, hpMaximo, hpMaximo, nome + ".jpg", isPJ, true );
	}

	public void criarPersonagem(	String ID, String path, String nome, String classe, String CA, String bonusIniciativa,
									String hpAtual, String hpMaximo, String imagem, Boolean isPJ, Boolean status )
	{
		Properties prop = new Properties( );
		OutputStream output = null;

		try
		{
			output = new FileOutputStream( path );

			prop.setProperty( "idPersonagem", ID );
			prop.setProperty( "nome", nome );
			prop.setProperty( "classe", classe );
			prop.setProperty( "ca", CA );
			prop.setProperty( "bonusIniciativa", bonusIniciativa );
			prop.setProperty( "hpAtual", hpAtual );
			prop.setProperty( "hpMaximo", hpMaximo );
			prop.setProperty( "hpAtual", hpMaximo );
			prop.setProperty( "imagem", imagem );
			prop.setProperty( "status", status.toString( ) );

			prop.store( output, null );

		} catch ( IOException io )
		{
			io.printStackTrace( );
		} finally
		{
			if ( output != null )
			{
				try
				{
					output.close( );
				} catch ( IOException e )
				{
					e.printStackTrace( );
				}
			}

		}
	}
}
