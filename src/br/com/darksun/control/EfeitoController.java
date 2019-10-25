package br.com.darksun.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.darksun.entity.Efeito;

public class EfeitoController
{
	private Integer newID = 1;

	public List< Efeito > listarEfeitos( )
	{
		List< Efeito > efeitos = new ArrayList< Efeito >( );
		File folder = new File( "resources/efeito/" );
		File[ ] lista = folder.listFiles( );
		for ( File file : lista )
			efeitos.add( carregar( "resources/efeito/" + file.getName( ) ) );
		return efeitos;
	}

	public Efeito carregar( String path )
	{
		JSONParser jsonParser = new JSONParser( );

		try ( FileReader reader = new FileReader( path ) )
		{
			Object obj = jsonParser.parse( reader );

			JSONObject efeitoJson = ( JSONObject ) obj;
			Efeito efeito = new Efeito( );

			Integer id = Integer.parseInt( efeitoJson.get( "idEfeito" ).toString( ) );
			if ( id >= newID )
			{
				newID = id + 1;
			}

			efeito.setFilePath( path );
			efeito.setIdEfeito( id );
			efeito.setNome( efeitoJson.get( "nome" ).toString( ) );
			efeito.setDuracaoPadrao( Integer.parseInt( efeitoJson.get( "duracaoPadrao" ).toString( ) ) );
			efeito.setIsPositivo( Boolean.parseBoolean( efeitoJson.get( "isPositivo" ).toString( ) ) );
			efeito.setAtributoAfetado( efeitoJson.get( "atributoAfetado" ).toString( ) );
			efeito.setIsContinuo( Boolean.parseBoolean( efeitoJson.get( "isContinuo" ).toString( ) ) );

			return efeito;

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

	public void criarEfeito(	Integer idEfeito, String nome, Integer duracaoPadrao, Boolean isPositivo,
								String atributoAfetado, Boolean isContinuo )
	{
		criarEfeito( new Efeito( idEfeito, null, nome, duracaoPadrao, isPositivo, atributoAfetado, isContinuo ) );
	}

	@SuppressWarnings( "unchecked" )
	public void criarEfeito( Efeito efeito )
	{
		JSONArray jsonArray = new JSONArray( );

		efeito.setFilePath( "resources/efeito/" + efeito.getNome( ).replace( " ", "" ).toLowerCase( ) + ".json" );
		jsonArray.add( efeito );

		Gson gson = new GsonBuilder( ).setPrettyPrinting( ).create( );
		String json = gson.toJson( jsonArray.get( 0 ) );

		try ( FileWriter file = new FileWriter( efeito.getFilePath( ) ) )
		{

			file.write( json );
			file.flush( );

		} catch ( IOException io )
		{
			io.printStackTrace( );
		}
	}

	public void criarEfeitosAleatorios( )
	{
		new EfeitoController( ).criarEfeito(
				new Efeito( 1, "resources/efeito/sangramento.json", "Sangramento", 1, false, "HP Atual", true ) );
		new EfeitoController( ).criarEfeito(
				new Efeito( 2, "resources/efeito/incrementodeca.json", "Incremento de CA", 10, true, "CA", false ) );
	}

	public String newId( )
	{
		return newID.toString( );
	}
}
