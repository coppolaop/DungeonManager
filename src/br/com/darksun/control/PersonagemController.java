package br.com.darksun.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		if ( path.endsWith( ".properties" ) )
		{
			return propertiesToJson( path, isPj );
		}

		JSONParser jsonParser = new JSONParser( );

		try ( FileReader reader = new FileReader( path ) )
		{
			Object obj = jsonParser.parse( reader );

			JSONObject personagemJson = ( JSONObject ) obj;
			Personagem personagem = new Personagem( );
			
			Integer id = Integer.parseInt( ( String ) personagemJson.get( "idPersonagem" ) );
			if ( id >= newID )
				newID = id + 1;

			personagem.setFilePath( path );
			personagem.setIdPersonagem( id );
			personagem.setNome( ( String ) personagemJson.get( "nome" ) );
			personagem.setClasse( ( String ) personagemJson.get( "classe" ) );
			personagem.setHpAtual( Integer.parseInt( ( String ) personagemJson.get( "hpAtual" ) ) );
			personagem.setHpMaximo( Integer.parseInt( ( String ) personagemJson.get( "hpMaximo" ) ) );
			personagem.setBonusIniciativa( Integer.parseInt( ( String ) personagemJson.get( "bonusIniciativa" ) ) );
			personagem.setCa( Integer.parseInt( ( String ) personagemJson.get( "ca" ) ) );
			personagem.setStatus( Boolean.parseBoolean( ( String ) personagemJson.get( "status" ) ) );
			personagem.setDescricao( ( String ) personagemJson.get( "descricao" ) );
			personagem.setImagem( ( String ) personagemJson.get( "imagem" ) );

			return personagem;

		} catch ( FileNotFoundException e )
		{
			e.printStackTrace( );
			return null;
		} catch ( IOException e )
		{
			e.printStackTrace( );
			return null;
		} catch ( ParseException e )
		{
			e.printStackTrace( );
			return null;
		}

	}

	public Personagem propertiesToJson( String path, Boolean isPj )
	{
		Properties prop = new Properties( );
		InputStream input = null;

		Personagem personagem = new Personagem( );

		try
		{

			input = new FileInputStream( path );

			prop.load( input );

			Integer id = Integer.parseInt( prop.getProperty( "idPersonagem" ) );
			if ( id >= newID )
				newID = id + 1;

			String newPath = path.substring( 0, path.length( ) - 10 ) + "json";

			personagem.setIdPersonagem( id );
			personagem.setFilePath( newPath );
			personagem.setNome( prop.getProperty( "nome" ) );
			personagem.setClasse( prop.getProperty( "classe" ) );
			personagem.setImagem( prop.getProperty( "imagem" ) );
			personagem.setCa( Integer.parseInt( prop.getProperty( "ca" ) ) );
			personagem.setBonusIniciativa( Integer.parseInt( prop.getProperty( "bonusIniciativa" ) ) );
			personagem.setHpMaximo( Integer.parseInt( prop.getProperty( "hpMaximo" ) ) );
			personagem.setHpAtual( Integer.parseInt( prop.getProperty( "hpAtual" ) ) );
			personagem.setIsPJ( isPj );
			personagem.setStatus( Boolean.parseBoolean( prop.getProperty( "status" ) ) );
			personagem.setReplica( 0 );
			personagem.setDescricao( prop.getProperty( "descricao" ) );
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

		criarPersonagem( personagem );

		File arquivo = new File( path );
		arquivo.delete( );

		return personagem;
	}

	@SuppressWarnings( "unchecked" )
	public void atualizarArquivo( String path, String campo, String valor )
	{
		JSONParser jsonParser = new JSONParser( );

		try ( FileReader reader = new FileReader( path ) )
		{
			Object obj = jsonParser.parse( reader );

			JSONObject personagem = ( JSONObject ) obj;

			personagem.put( campo, valor );

			Gson gson = new GsonBuilder( ).setPrettyPrinting( ).create( );
			String json = gson.toJson( personagem );

			try ( FileWriter file = new FileWriter( path ) )
			{

				file.write( json );
				file.flush( );

			} catch ( IOException io )
			{
				io.printStackTrace( );
			}
		} catch ( FileNotFoundException e )
		{
			e.printStackTrace( );
		} catch ( IOException e )
		{
			e.printStackTrace( );
		} catch ( ParseException e )
		{
			e.printStackTrace( );
		}
	}

	public void criarPersonagemAleatorio( Boolean pj )
	{
		if ( pj )
		{
			criarPersonagem( "0", "resources/pj/PJ_de_Exemplo.json", "PJ de Exemplo", "Exemplo", "12", "1", "10", "100",
					"pjExemplo.jpg", true, true, "Personagem do Jogador de exemplo" );
		} else
		{
			criarPersonagem( "0", "resources/pdm/PDM_de_Exemplo.json", "PDM de Exemplo", "Exemplo", "12", "1", "10",
					"100", "pdmExemplo.jpg", false, true, "Personagem do Mestre de exemplo" );
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
				personagem.getIsPJ( ), personagem.getStatus( ), personagem.getDescricao( ) );
	}

	public void criarPersonagem(	String ID, String nome, String classe, String CA, String bonusIniciativa,
									String hpMaximo, String imagem, Boolean isPJ, String descricao )
	{
		String path;

		if ( isPJ )
		{
			path = "resources/pj/" + nome.replace( "\\", "_" ).replaceAll( "[ /|<>*:“\"]", "_" ) + ".json";
		} else
		{
			path = "resources/pdm/" + nome.replace( "\\", "_" ).replaceAll( "[ /|<>*:“\"]", "_" ) + ".json";
		}

		criarPersonagem( ID, path, nome, classe, CA, bonusIniciativa, hpMaximo, hpMaximo, imagem, isPJ, true,
				descricao );
	}

	@SuppressWarnings( "unchecked" )
	public void criarPersonagem(	String ID, String path, String nome, String classe, String CA, String bonusIniciativa,
									String hpAtual, String hpMaximo, String imagem, Boolean isPJ, Boolean status,
									String descricao )
	{
		JSONObject personagem = new JSONObject( );

		if ( imagem != null && imagem.equals( "" ) )
		{
			imagem = nome + ".jpg";
		}

		personagem.put( "idPersonagem", ID );
		personagem.put( "nome", nome );
		personagem.put( "classe", classe );
		personagem.put( "ca", CA );
		personagem.put( "bonusIniciativa", bonusIniciativa );
		personagem.put( "hpAtual", hpAtual );
		personagem.put( "hpMaximo", hpMaximo );
		personagem.put( "hpAtual", hpMaximo );
		personagem.put( "imagem", imagem );
		personagem.put( "status", status.toString( ) );
		personagem.put( "descricao", descricao );
		personagem.put( "dataCriacao", LocalDate.now( ) );

		Gson gson = new GsonBuilder( ).setPrettyPrinting( ).create( );
		String json = gson.toJson( personagem );

		try ( FileWriter file = new FileWriter( path ) )
		{

			file.write( json );
			file.flush( );

		} catch ( IOException io )
		{
			io.printStackTrace( );
		}
	}
}
