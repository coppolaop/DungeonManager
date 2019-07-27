package br.com.darksun.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;

class PersonagemControllerTest
{
	private static File resources = new File( "resources/" );
	private static File backup = new File( "backup/" );
	private PersonagemController controller = new PersonagemController( );

	@BeforeAll
	static void setUpBeforeClass( ) throws Exception
	{
		resources.renameTo( backup );
	}

	@AfterAll
	static void tearDownAfterClass( ) throws Exception
	{
		backup.renameTo( resources );
	}

	@BeforeEach
	void setUp( ) throws Exception
	{
		//Cria pasta PJ se não existir
		File dirPJ = new File( "resources/pj/" );
		if ( !dirPJ.exists( ) )
		{
			dirPJ.mkdirs( );
		}

		//Cria pasta PDM se não existir
		File dirPDM = new File( "resources/pdm/" );
		if ( !dirPDM.exists( ) )
		{
			dirPDM.mkdirs( );
		}

		// Adiciona um PJ ativo à lista
		Personagem pj = new Personagem( );
		pj.setIdPersonagem( 7 );
		pj.setNome( "PJ" );
		pj.setBonusIniciativa( 0 );
		pj.setCa( 10 );
		pj.setClasse( "Testador" );
		pj.setDescricao( "PJ Genérico Ativo" );
		pj.setImagem( "PJ.jpg" );
		pj.setIniciativa( 0 );
		pj.setIsPJ( true );
		pj.setReplica( 0 );
		pj.setStatus( true );
		pj.setFilePath( "resources/pj/PJ.json" );
		controller.criarPersonagem( pj );

		// Adiciona um PDM ativo à lista
		Personagem pdm = new Personagem( );
		pdm.setIdPersonagem( 10 );
		pdm.setNome( "PDM" );
		pdm.setBonusIniciativa( 0 );
		pdm.setCa( 10 );
		pdm.setClasse( "Testador" );
		pdm.setDescricao( "PDM Genérico Ativo" );
		pdm.setImagem( "PDM.jpg" );
		pdm.setIniciativa( 0 );
		pdm.setIsPJ( false );
		pdm.setReplica( 0 );
		pdm.setStatus( true );
		pdm.setFilePath( "resources/pdm/PDM.json" );
		controller.criarPersonagem( pdm );

		// Adiciona um PDM inativo à lista
		Personagem pdmInativo = new Personagem( );
		pdmInativo.setIdPersonagem( 12 );
		pdmInativo.setNome( "PDM Inativo" );
		pdmInativo.setBonusIniciativa( 0 );
		pdmInativo.setCa( 10 );
		pdmInativo.setClasse( "Testador" );
		pdmInativo.setDescricao( "PDM Genérico Inativo" );
		pdmInativo.setImagem( "PDM_Inativo.jpg" );
		pdmInativo.setIniciativa( 0 );
		pdmInativo.setIsPJ( false );
		pdmInativo.setReplica( 0 );
		pdmInativo.setStatus( false );
		pdmInativo.setFilePath( "resources/pdm/PDM_Inativo.json" );
		controller.criarPersonagem( pdmInativo );

		// Adiciona um PJ inativo à lista
		Personagem pjInativo = new Personagem( );
		pjInativo.setIdPersonagem( 15 );
		pjInativo.setNome( "PJ Inativo" );
		pjInativo.setBonusIniciativa( 0 );
		pjInativo.setCa( 10 );
		pjInativo.setClasse( "Testador" );
		pjInativo.setDescricao( "PJ Genérico Inativo" );
		pjInativo.setImagem( "PJ_Inativo.jpg" );
		pjInativo.setIniciativa( 0 );
		pjInativo.setIsPJ( true );
		pjInativo.setReplica( 0 );
		pjInativo.setStatus( false );
		pjInativo.setFilePath( "resources/pj/PJ_Inativo.json" );
		controller.criarPersonagem( pjInativo );
	}
	
	@AfterEach
	void tearDown( ) throws Exception
	{
		deleteAll( resources.toPath( ) );
	}

	@Test
	void testCriarPjAleatorio( )
	{
		File file = new File( "resources/pj/PJ_de_Exemplo.json" );
		controller.criarPersonagemAleatorio( true );
		assertEquals( true, file.exists( ) );
	}

	@Test
	void testCriarPdmAleatorio( )
	{
		File file = new File( "resources/pdm/PDM_de_Exemplo.json" );
		controller.criarPersonagemAleatorio( false );
		assertEquals( true, file.exists( ) );
	}

	@Test
	void testCriarPersonagemViaObjeto( )
	{
		String path = "resources/pj/Abel.json";
		Personagem personagem = new Personagem( );
		personagem.setBonusIniciativa( -1 );
		personagem.setCa( 26 );
		personagem.setClasse( "Clérigo" );
		personagem.setDescricao( "Clérigo do deus da ressurreição" );
		personagem.setFilePath( path );
		personagem.setHpAtual( 200 );
		personagem.setHpMaximo( 300 );
		personagem.setIdPersonagem( 1 );
		personagem.setIniciativa( 10 );
		personagem.setIsPJ( true );
		personagem.setNome( "Abel" );
		personagem.setReplica( 0 );
		personagem.setStatus( false );
		controller.criarPersonagem( personagem );
		File file = new File( path );
		assertEquals( true, file.exists( ) );
	}

	@Test
	void testCriarPersonagemViaForm( )
	{
		String path = "resources/pdm/Marcos.json";
		controller.criarPersonagem( "2", path, "Marcos", "Guerreiro", "32", "4", "190", "250", null, false, false,
				"Guerreiro especialista em Fender equipamento inimigo" );
		File file = new File( path );
		assertEquals( true, file.exists( ) );
	}

	@Test
	void testCriarPersonagemViaParam( )
	{
		String path = "resources/pj/Igor.json";
		controller.criarPersonagem( "3", "Igor", "Guerreiro", "25", "4", "200", "220", true,
				"Guerreiro de arma de duas mãos" );
		File file = new File( path );
		assertEquals( true, file.exists( ) );
	}

	@Test
	void testListarPJs( )
	{
		assertEquals( 2, controller.listarPJs( ).size( ) );
	}

	@Test
	void testListarPJsAtivos( )
	{
		assertEquals( 1, controller.listarPJsAtivos( ).size( ) );
	}

	@Test
	void testListarPDMs( )
	{
		for( Personagem p : controller.listarPDMs( ))
		{
			System.out.println( p.getFilePath( ) );
		}
		assertEquals( 2, controller.listarPDMs( ).size( ) );
	}

	@Test
	void testListarPDMsAtivos( )
	{
		assertEquals( 1, controller.listarPDMsAtivos( ).size( ) );
	}

	@Test
	void testCarregar( )
	{
		String path = "resources/pj/PJ.json";
		assertEquals( "PJ Genérico Ativo", controller.carregar( path, true ).getDescricao( ) );
	}

	@Test
	void testPropertiesToJson( )
	{
		Properties prop = new Properties( );
		OutputStream output = null;

		String path = "resources/pdm/Tester.properties";

		try
		{
			output = new FileOutputStream( path );

			prop.setProperty( "idPersonagem", "4" );
			prop.setProperty( "nome", "Tester" );
			prop.setProperty( "classe", "Mímico" );
			prop.setProperty( "ca", "10" );
			prop.setProperty( "bonusIniciativa", "0" );
			prop.setProperty( "hpAtual", "10" );
			prop.setProperty( "hpMaximo", "10" );
			prop.setProperty( "imagem", "Tester.jpg" );
			prop.setProperty( "status", "true" );
			prop.setProperty( "descricao", "Teste" );

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

		controller.propertiesToJson( path, false );

		File file = new File( "resources/pdm/Tester.json" );
		assertEquals( true, file.exists( ) );
	}

	@Test
	void testAtualizarArquivo( )
	{
		String path = "resources/pdm/PDM.json";
		controller.atualizarArquivo( path, "nome", "PJ Ativo" );
		Personagem personagem = controller.carregar( path, false );
		assertEquals( "PJ Ativo" , personagem.getNome( ) );
	}

	@Test
	void testNewId( )
	{
		//Espera-se que o valor retornado seja 16, pois o maior valor criado no método SetUp foi 15
		controller.listarPJs( );
		controller.listarPDMs( );
		assertEquals( "16", controller.newId( ) );
	}

	public static void deleteAll( Path path ) throws IOException
	{
		if ( Files.isDirectory( path, LinkOption.NOFOLLOW_LINKS ) )
		{
			try ( DirectoryStream< Path > entries = Files.newDirectoryStream( path ) )
			{
				for ( Path entry : entries )
				{
					deleteAll( entry );
				}
			}
		}
		Files.delete( path );
	}
}
