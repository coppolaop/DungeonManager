package br.com.darksun.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;

public class JPInicial extends JPPadrao
{

	private Integer maiorNome = 0;
	
	public JPInicial( JFPrincipal frame )
	{
		montaTelaPrincipal( frame );
	}

	public void montaTelaPrincipal( JFPrincipal frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );

		PersonagemController pc = new PersonagemController( );
		List< Personagem > PJs = pc.listarPJs( );
		List< Personagem > PDMs = pc.listarPDMs( );

		for(Personagem personagem : PJs)
			if(personagem.getNome( ).length( ) > maiorNome)
				maiorNome = personagem.getNome( ).length( );
		
		for(Personagem personagem : PDMs)
			if(personagem.getNome( ).length( ) > maiorNome)
				maiorNome = personagem.getNome( ).length( );
		
		maiorNome = ( maiorNome + 1 ) * 7;
		
		if( maiorNome < 125 )
			maiorNome = 125;
		
		String[ ] listaPJcombo = new String[ PJs.size( ) ];
		String[ ] listaPDMs = new String[ PDMs.size( ) ];

		for ( int i = 0; i < PJs.size( ); i++ )
			listaPJcombo[i] = PJs.get( i ).getNome( );

		for ( int i = 0; i < PDMs.size( ); i++ )
			listaPDMs[i] = PDMs.get( i ).getNome( );

		JComboBox PJComboBox = new JComboBox( listaPJcombo );
		PJComboBox.setBounds( 50, 50, maiorNome, 30 );

		JButton btnAddPJ = new JButton( "Adincionar" );
		btnAddPJ.setBounds( 50, 130, maiorNome, 30 );

		JButton btnAddAllPJ = new JButton( "Adincionar Todos" );
		btnAddAllPJ.setBounds( maiorNome + 100, 130, 150, 30 );

		DefaultListModel PJsSelecionados = new DefaultListModel( );
		JList listaPJ = new JList( PJsSelecionados );
		listaPJ.setVisible( false );
		listaPJ.setBounds( 50, 210, maiorNome, 0 );

		JButton btnRemovePJ = new JButton( "Remover" );
		btnRemovePJ.setBounds( maiorNome + 100, 210, 150, 30 );
		btnRemovePJ.setVisible( false );

		JButton btnRemoveAllPJ = new JButton( "Remover Todos" );
		btnRemoveAllPJ.setBounds( maiorNome + 100, 290, 150, 30 );
		btnRemoveAllPJ.setVisible( false );

		JComboBox PDMComboBox = new JComboBox( listaPDMs );
		PDMComboBox.setBounds( width - (maiorNome + 50 ), 50, maiorNome, 30 );

		JButton btnAddPDM = new JButton( "Adicionar" );
		btnAddPDM.setBounds( width - ( maiorNome + 50 ), 130, maiorNome, 30 );

		JButton btnAddAllPDM = new JButton( "Adincionar Todos" );
		btnAddAllPDM.setBounds( width - ( maiorNome + 250 ), 130, 150, 30 );

		DefaultListModel PDMsSelecionados = new DefaultListModel( );
		JList listaPDM = new JList( PDMsSelecionados );
		listaPDM.setVisible( false );
		listaPDM.setBounds( width - (maiorNome + 50 ), 210, maiorNome, 0 );

		JButton btnRemovePDM = new JButton( "Remover" );
		btnRemovePDM.setBounds( width - ( maiorNome + 250 ), 210, 150, 30 );
		btnRemovePDM.setVisible( false );

		JButton btnRemoveAllPDM = new JButton( "Remover Todos" );
		btnRemoveAllPDM.setBounds( width - ( maiorNome  + 250 ), 290, 150, 30 );
		btnRemoveAllPDM.setVisible( false );

		JButton btnIniciarCombate = new JButton( "Iniciar Combate" );
		btnIniciarCombate.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2, 200, 30 );

		add( PJComboBox );
		add( btnAddPJ );
		add( btnAddAllPJ );
		add( listaPJ );
		add( btnRemovePJ );
		add( btnRemoveAllPJ );
		add( PDMComboBox );
		add( btnAddPDM );
		add( btnAddAllPDM );
		add( listaPDM );
		add( btnRemovePDM );
		add( btnRemoveAllPDM );
		add( btnIniciarCombate );

		frame.repaint( );

		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;

				setBounds( 0, 0, width, height );
				btnIniciarCombate.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2,
						btnIniciarCombate.getBounds( ).width, btnIniciarCombate.getBounds( ).height );
				PDMComboBox.setBounds( width - ( maiorNome + 50 ), 50, PDMComboBox.getBounds( ).width,
						PDMComboBox.getBounds( ).height );
				btnAddPDM.setBounds( width - ( maiorNome + 50 ), 130, btnAddPDM.getBounds( ).width, btnAddPDM.getBounds( ).height );
				btnAddAllPDM.setBounds( width - ( maiorNome + 250 ), 130, btnAddAllPDM.getBounds( ).width,
						btnAddAllPDM.getBounds( ).height );
				listaPDM.setBounds( width - ( maiorNome + 50 ), 210, listaPDM.getBounds( ).width, listaPDM.getBounds( ).height );
				btnRemovePDM.setBounds( width - ( maiorNome + 250 ), 210, btnRemovePDM.getBounds( ).width,
						btnRemovePDM.getBounds( ).height );
				btnRemoveAllPDM.setBounds( width - ( maiorNome + 250 ), 290, btnRemoveAllPDM.getBounds( ).width,
						btnRemoveAllPDM.getBounds( ).height );
			}
		} );

		btnAddPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listaPJ.setVisible( true );
				btnRemovePJ.setVisible( true );
				btnRemoveAllPJ.setVisible( true );
				if ( PJComboBox.getSelectedItem( ) != null )
				{
					PJsSelecionados.addElement( PJComboBox.getSelectedItem( ) );
					listaPJ.setBounds( 50, 210, maiorNome, listaPJ.getBounds( ).height + 20 );
					listaPJ.setSelectedIndex( PJComboBox.getSelectedIndex( ) );
					PJComboBox.removeItem( PJComboBox.getSelectedItem( ) );
				}
			}
		} );

		btnAddAllPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listaPJ.setVisible( true );
				btnRemovePJ.setVisible( true );
				btnRemoveAllPJ.setVisible( true );
				listaPJ.setSelectedIndex( 0 );
				int size = PJComboBox.getItemCount( );
				for ( int i = 0; i < size; i++ )
				{
					PJsSelecionados.addElement( PJComboBox.getSelectedItem( ) );
					listaPJ.setBounds( 50, 210, maiorNome, listaPJ.getBounds( ).height + 20 );
					listaPJ.setSelectedIndex( PJComboBox.getSelectedIndex( ) );
					PJComboBox.removeItem( PJComboBox.getSelectedItem( ) );
				}
			}
		} );

		btnAddPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listaPDM.setVisible( true );
				btnRemovePDM.setVisible( true );
				btnRemoveAllPDM.setVisible( true );
				if ( PDMComboBox.getSelectedItem( ) != null )
				{
					PDMsSelecionados.addElement( PDMComboBox.getSelectedItem( ) );
					listaPDM.setBounds( width - ( maiorNome + 50 ) , 210, maiorNome, listaPDM.getBounds( ).height + 20 );
					listaPDM.setSelectedIndex( PDMComboBox.getSelectedIndex( ) );
					PDMComboBox.removeItem( PDMComboBox.getSelectedItem( ) );
				}
			}
		} );

		btnAddAllPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listaPDM.setVisible( true );
				btnRemovePDM.setVisible( true );
				btnRemoveAllPDM.setVisible( true );
				listaPDM.setSelectedIndex( 0 );
				int size = PDMComboBox.getItemCount( );
				for ( int i = 0; i < size; i++ )
				{
					PDMsSelecionados.addElement( PDMComboBox.getSelectedItem( ) );
					listaPDM.setBounds( width - (maiorNome + 50 ), 210, maiorNome, listaPDM.getBounds( ).height + 20 );
					listaPDM.setSelectedIndex( PDMComboBox.getSelectedIndex( ) );
					PDMComboBox.removeItem( PDMComboBox.getSelectedItem( ) );
				}
			}
		} );

		btnRemovePJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if ( !PJsSelecionados.isEmpty( ) )
				{
					int index = listaPJ.getSelectedIndex( );
					PJComboBox.addItem( listaPJ.getSelectedValue( ) );
					PJsSelecionados.removeElementAt( index );
					listaPJ.setBounds( listaPJ.getBounds( ).x, listaPJ.getBounds( ).y, listaPJ.getBounds( ).width,
							listaPJ.getBounds( ).height - 20 );
					if ( PJsSelecionados.isEmpty( ) )
					{
						btnRemovePJ.setVisible( false );
						btnRemoveAllPJ.setVisible( false );
					} else
						listaPJ.setSelectedIndex( index );
					if ( index > listaPJ.getLastVisibleIndex( ) )
						listaPJ.setSelectedIndex( listaPJ.getLastVisibleIndex( ) );
					List< String > listaAux = ordenaJList( PJComboBox );
					PJComboBox.removeAllItems( );
					for ( String item : listaAux )
					{
						PJComboBox.addItem( item );
					}

				}
			}
		} );

		btnRemoveAllPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int size = PJsSelecionados.size( );
				for ( int i = 0; i < size; i++ )
				{
					listaPJ.setSelectedIndex( 0 );
					PJComboBox.addItem( listaPJ.getSelectedValue( ) );
					PJsSelecionados.removeElementAt( 0 );
				}
				listaPJ.setBounds( listaPJ.getBounds( ).x, listaPJ.getBounds( ).y, listaPJ.getBounds( ).width, 0 );
				btnRemovePJ.setVisible( false );
				btnRemoveAllPJ.setVisible( false );
				List< String > listaAux = ordenaJList( PJComboBox );
				PJComboBox.removeAllItems( );
				for ( String item : listaAux )
				{
					PJComboBox.addItem( item );
				}
			}
		} );

		btnRemovePDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if ( !PDMsSelecionados.isEmpty( ) )
				{
					int index = listaPDM.getSelectedIndex( );
					PDMComboBox.addItem( listaPDM.getSelectedValue( ) );
					PDMsSelecionados.removeElementAt( index );
					listaPDM.setBounds( listaPDM.getBounds( ).x, listaPDM.getBounds( ).y, listaPDM.getBounds( ).width,
							listaPDM.getBounds( ).height - 20 );
					if ( PDMsSelecionados.isEmpty( ) )
					{
						btnRemovePDM.setVisible( false );
						btnRemoveAllPDM.setVisible( false );
					} else
						listaPDM.setSelectedIndex( index );
					if ( index > listaPDM.getLastVisibleIndex( ) )
						listaPDM.setSelectedIndex( listaPDM.getLastVisibleIndex( ) );
					List< String > listaAux = ordenaJList( PDMComboBox );
					PDMComboBox.removeAllItems( );
					for ( String item : listaAux )
					{
						PDMComboBox.addItem( item );
					}

				}
			}
		} );

		btnRemoveAllPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int size = PDMsSelecionados.size( );
				for ( int i = 0; i < size; i++ )
				{
					listaPDM.setSelectedIndex( 0 );
					PDMComboBox.addItem( listaPDM.getSelectedValue( ) );
					PDMsSelecionados.removeElementAt( 0 );
				}
				listaPDM.setBounds( listaPDM.getBounds( ).x, listaPDM.getBounds( ).y, listaPDM.getBounds( ).width, 0 );
				btnRemovePDM.setVisible( false );
				btnRemoveAllPDM.setVisible( false );
				List< String > listaAux = ordenaJList( PDMComboBox );
				PDMComboBox.removeAllItems( );
				for ( String item : listaAux )
				{
					PDMComboBox.addItem( item );
				}
			}
		} );

		btnIniciarCombate.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int PJsize = listaPJ.getLastVisibleIndex( ) + 1;
				int PDMsize = listaPDM.getLastVisibleIndex( ) + 1;

				if ( PJsize == 0 )
				{
					System.out.println( "Selecione pelo menos um PJ" );
					return;
				}

				if ( PDMsize == 0 )
				{
					System.out.println( "Selecione pelo menos um PDM" );
					return;
				}

				List< Personagem > PJcombate = new ArrayList< Personagem >( );
				List< Personagem > PDMcombate = new ArrayList< Personagem >( );

				for ( int i = 0; i < PJsize; i++ )
				{
					listaPJ.setSelectedIndex( i );

					for ( Personagem personagem : PJs )
					{
						if ( personagem.getNome( ).equals( listaPJ.getSelectedValue( ) ) )
						{
							PJcombate.add( personagem );
							break;
						}
					}
				}

				for ( int i = 0; i < PDMsize; i++ )
				{
					listaPDM.setSelectedIndex( i );

					for ( Personagem personagem : PDMs )
					{
						if ( personagem.getNome( ).equals( listaPDM.getSelectedValue( ) ) )
						{
							PDMcombate.add( personagem );
							break;
						}
					}
				}

				frame.remove( JPInicial.this );
				frame.setTela( new JPIniciativa( frame, PJcombate, PDMcombate ) );

			}
		} );
	}
}
