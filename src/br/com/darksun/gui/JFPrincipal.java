package br.com.darksun.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;

public class JFPrincipal extends JFrame
{
	private JPanel tela = new JPanel( );

	public static void main( String[ ] args )
	{
		EventQueue.invokeLater( new Runnable( )
		{
			public void run( )
			{
				JFPrincipal frame = new JFPrincipal( );

				PersonagemController pc = new PersonagemController( );
				List< Personagem > PJs = pc.listarPJs( );
				List< Personagem > PDMs = pc.listarPDMs( );

				frame.montaTelaPrincipal( PJs, PDMs );
			}
		} );
	}

	public JFPrincipal( )
	{
		this.setVisible( true );
		this.setSize( 1500, 750 );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( null );
		this.setTitle( "Dungeon Manager" );
		this.add( this.tela );
	}

	public void montaTelaPrincipal( List< Personagem > PJs, List< Personagem > PDMs )
	{
		limpaTela( );
		String[ ] listaPJs = new String[ PJs.size( ) ];
		String[ ] listaPDMs = new String[ PDMs.size( ) ];

		for ( int i = 0; i < PJs.size( ); i++ )
			listaPJs[i] = PJs.get( i ).getNome( );

		for ( int i = 0; i < PDMs.size( ); i++ )
			listaPDMs[i] = PDMs.get( i ).getNome( );

		JComboBox PJBox = new JComboBox( listaPJs );
		PJBox.setBounds( 50, 50, 100, 30 );

		JButton btnAddPJ = new JButton( "Add" );
		btnAddPJ.setBounds( 50, 130, 100, 30 );

		DefaultListModel PJsSelecionados = new DefaultListModel( );
		JList listaPJ = new JList( PJsSelecionados );
		listaPJ.setVisible( false );
		listaPJ.setBounds( 50, 210, 100, 0 );

		JComboBox PDMBox = new JComboBox( listaPDMs );
		PDMBox.setBounds( 1350, 50, 100, 30 );

		JButton btnAddPDM = new JButton( "Add" );
		btnAddPDM.setBounds( 1350, 130, 100, 30 );

		DefaultListModel PDMsSelecionados = new DefaultListModel( );
		JList listaPDM = new JList( PDMsSelecionados );
		listaPDM.setVisible( false );
		listaPDM.setBounds( 1350, 210, 100, 0 );

		JButton btnIniciarCombate = new JButton( "Iniciar Combate" );
		btnIniciarCombate.setBounds( 650, 200, 200, 30 );

		this.tela.add( PJBox );
		this.tela.add( btnAddPJ );
		this.tela.add( listaPJ );
		this.tela.add( PDMBox );
		this.tela.add( btnAddPDM );
		this.tela.add( listaPDM );
		this.tela.add( btnIniciarCombate );

		this.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				int width = getBounds( ).width;
				int height = getBounds( ).height;

				tela.setBounds( 0, 0, width, height );
				btnIniciarCombate.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2, 200, 30 );
				PDMBox.setBounds( width - 150, 50, 100, 30 );
				btnAddPDM.setBounds( width - 150, 130, 100, 30 );
				listaPDM.setBounds( width - 150, 210, 100, listaPDM.getBounds( ).height );
			}
		} );

		btnAddPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listaPJ.setVisible( true );
				if ( PJBox.getSelectedItem( ) != null )
				{
					PJsSelecionados.addElement( PJBox.getSelectedItem( ) );
					listaPJ.setBounds( 50, 210, 100, listaPJ.getBounds( ).height + 20 );
					PJBox.removeItem( PJBox.getSelectedItem( ) );
				}
			}
		} );
		
		btnAddPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listaPDM.setVisible( true );
				if ( PDMBox.getSelectedItem( ) != null )
				{
					PDMsSelecionados.addElement( PDMBox.getSelectedItem( ) );
					listaPDM.setBounds( 1350, 210, 100, listaPDM.getBounds( ).height + 20 );
					PDMBox.removeItem( PDMBox.getSelectedItem( ) );
				}
			}
		} );
	}

	public void limpaTela( )
	{
		this.tela.removeAll( );
		this.tela.setLayout( null );
		this.tela.setBounds( 0, 0, this.getWidth( ), this.getHeight( ) );
		this.tela.setBackground( new Color( 100, 100, 100 ) );
	}
}
