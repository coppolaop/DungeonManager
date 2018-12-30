package br.com.darksun.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.google.gson.Gson;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Aplicacao;
import br.com.darksun.entity.Personagem;
import br.com.darksun.gui.characterbuilder.JPListarPersonagem;

public class JFPrincipal extends JFrame
{
	private final String SYSTEM_VERSION = "1.1.0";
	private final String SYSTEM_BETA = "";
	private final Boolean SYSTEM_IS_IN_BETA = !SYSTEM_BETA.equals( "" );
	private final String SYSTEM_ICON = SYSTEM_IS_IN_BETA ? "img/DungeonManagerHomologacao.png"
			: "img/DungeonManager.png";
	private final static String url = "https://api.github.com/repos/coppolaop/DungeonManager/releases/latest";
	private Integer width = 1500;
	private Integer height = 750;
	private JPPadrao tela;

	public static void main( String[ ] args )
	{
		EventQueue.invokeLater( new Runnable( )
		{
			public void run( )
			{
				JFPrincipal frame = new JFPrincipal( );
			}
		} );
	}

	public JFPrincipal( )
	{
		ImageIcon logoApp = new ImageIcon( getClass( ).getClassLoader( ).getResource( SYSTEM_ICON ) );
		this.setIconImage( logoApp.getImage( ) );
		this.setVisible( true );
		this.setSize( width, height );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( null );
		this.setTitle( "Dungeon Manager " + SYSTEM_VERSION + SYSTEM_BETA );
		this.preparaMenu( );

		File dirPJ = new File( "resources/pj/" );
		if ( !dirPJ.exists( ) )
		{
			dirPJ.mkdirs( );
			new PersonagemController( ).criarPersonagemAleatorio( true );
		}

		File dirPDM = new File( "resources/pdm/" );
		if ( !dirPDM.exists( ) )
		{
			dirPDM.mkdirs( );
			new PersonagemController( ).criarPersonagemAleatorio( false );
		}

		setTela( new JPInicial( this ) );

		if ( !SYSTEM_IS_IN_BETA )
			try
			{
				new Thread( verificaAtualizacao ).start( );
			} catch ( Exception ex )
			{

			}
	}

	void setIniciativa( Personagem personagem, Integer value )
	{
		personagem.setIniciativa( value );
	}

	public void preparaMenu( )
	{
		JMenuBar menuBar = new JMenuBar( );
		menuBar.setBackground( new Color( 125, 125, 125 ) );
		setJMenuBar( menuBar );

		JMenu fileMenu = new JMenu( "Arquivo" );
		menuBar.add( fileMenu );

		JMenuItem itemNovoCombate = new JMenuItem( "Novo Combate" );
		JMenuItem listarPersonagens = new JMenuItem( "Listar Personagens" );
		JMenuItem itemSobre = new JMenuItem( "Sobre" );
		JMenuItem itemSair = new JMenuItem( "Sair" );

		itemNovoCombate.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				JFPrincipal.this.remove( getTela( ) );

				setTela( new JPInicial( JFPrincipal.this ) );

				revalidate( );
				repaint( );
			}
		} );

		listarPersonagens.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				JFPrincipal.this.remove( getTela( ) );

				setTela( new JPListarPersonagem( JFPrincipal.this ) );

				revalidate( );
				repaint( );
			}
		} );

		itemSobre.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				JFPrincipal.this.remove( getTela( ) );

				setTela( new JPSobre( JFPrincipal.this ) );

				revalidate( );
				repaint( );
			}
		} );

		itemSair.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				System.exit( 0 );
			}
		} );

		fileMenu.add( itemNovoCombate );
		fileMenu.add( listarPersonagens ); // Projeto Character Builder
		fileMenu.add( itemSobre );
		fileMenu.addSeparator( );
		fileMenu.add( itemSair );
	}

	public JPPadrao getTela( )
	{
		return tela;
	}

	public void setTela( JPPadrao tela )
	{
		this.tela = tela;
		this.add( tela );
	}

	public Boolean isInBeta( )
	{
		return this.SYSTEM_IS_IN_BETA;
	}

	public String getIconPath( )
	{
		return SYSTEM_ICON;
	}

	private static Runnable verificaAtualizacao = new Runnable( )
	{
		public void run( )
		{
			try
			{
				URL obj = new URL( url );
				HttpURLConnection con = ( HttpURLConnection ) obj.openConnection( );

				con.setRequestMethod( "GET" );

				con.setRequestProperty( "User-Agent", "Mozilla/5.0" );

				BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream( ) ) );
				String inputLine;
				StringBuffer response = new StringBuffer( );

				while ( ( inputLine = in.readLine( ) ) != null )
				{
					response.append( inputLine );
				}
				in.close( );

				Gson g = new Gson( );
				Aplicacao app = g.fromJson( response.toString( ), Aplicacao.class );

				if ( !JFPrincipal.this.SYSTEM_VERSION.equals( app.getTagName( ) ) )
					new JDAtualizacao( null );
			} catch ( Exception ex )
			{

			}
		}
	};
}