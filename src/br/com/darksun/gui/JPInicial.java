package br.com.darksun.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;
import br.com.darksun.util.Model.PersonagemNomeListModel;
import br.com.darksun.util.comparator.NomeComparator;

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

		JLabel labelImg = new JLabel( "" );
		labelImg.setBounds( ( width - 200 ) / 2, 50, 200, 200 );
		ImageIcon logoApp = new ImageIcon( getClass( ).getClassLoader( ).getResource( frame.getIconPath( ) ) );
		logoApp = new ImageIcon( logoApp.getImage( ).getScaledInstance( labelImg.getWidth( ), labelImg.getHeight( ),
				logoApp.getImage( ).SCALE_DEFAULT ) );
		labelImg.setIcon( logoApp );

		PersonagemController pc = new PersonagemController( );
		List< Personagem > PJs = pc.listarPJsAtivos( );
		List< Personagem > PDMs = pc.listarPDMsAtivos( );

		for ( Personagem personagem : PJs )
			if ( personagem.getNome( ).length( ) > maiorNome )
				maiorNome = personagem.getNome( ).length( );

		for ( Personagem personagem : PDMs )
			if ( personagem.getNome( ).length( ) > maiorNome )
				maiorNome = personagem.getNome( ).length( );

		maiorNome = ( maiorNome + 1 ) * 7;

		if ( maiorNome < 125 )
			maiorNome = 125;

		JComboBox PJComboBox = new JComboBox( PJs.toArray( ) );
		PJComboBox.setBounds( 50, 50, maiorNome, 30 );

		JButton btnAddPJ = new JButton( "Adicionar" );
		btnAddPJ.setBounds( 50, 130, maiorNome, 30 );

		JButton btnAddAllPJ = new JButton( "Adicionar Todos" );
		btnAddAllPJ.setBounds( maiorNome + 100, 130, 150, 30 );

		PersonagemNomeListModel PJsSelecionados = new PersonagemNomeListModel( );
		JList listaPJ = new JList( PJsSelecionados );
		listaPJ.setVisible( false );
		listaPJ.setBounds( 50, 210, maiorNome, 0 );

		JButton btnRemovePJ = new JButton( "Remover" );
		btnRemovePJ.setBounds( maiorNome + 100, 210, 150, 30 );
		btnRemovePJ.setVisible( false );

		JButton btnRemoveAllPJ = new JButton( "Remover Todos" );
		btnRemoveAllPJ.setBounds( maiorNome + 100, 290, 150, 30 );
		btnRemoveAllPJ.setVisible( false );

		JComboBox PDMComboBox = new JComboBox( PDMs.toArray( ) );
		PDMComboBox.setBounds( width - ( maiorNome + 50 ), 50, maiorNome, 30 );

		JButton btnAddReplicaPDM = new JButton( "Adicionar réplica" );
		btnAddReplicaPDM.setBounds( width - ( maiorNome + 250 ), 50, 150, 30 );

		JButton btnAddPDM = new JButton( "Adicionar" );
		btnAddPDM.setBounds( width - ( maiorNome + 50 ), 130, maiorNome, 30 );

		JButton btnAddAllPDM = new JButton( "Adicionar Todos" );
		btnAddAllPDM.setBounds( width - ( maiorNome + 250 ), 130, 150, 30 );

		PersonagemNomeListModel PDMsSelecionados = new PersonagemNomeListModel( );
		JList listaPDM = new JList( PDMsSelecionados );
		listaPDM.setVisible( false );
		listaPDM.setBounds( width - ( maiorNome + 50 ), 210, maiorNome, 0 );

		JButton btnRemovePDM = new JButton( "Remover" );
		btnRemovePDM.setBounds( width - ( maiorNome + 250 ), 210, 150, 30 );
		btnRemovePDM.setVisible( false );

		JButton btnRemoveAllPDM = new JButton( "Remover Todos" );
		btnRemoveAllPDM.setBounds( width - ( maiorNome + 250 ), 290, 150, 30 );
		btnRemoveAllPDM.setVisible( false );

		JButton btnIniciarCombate = new JButton( "Iniciar Combate" );
		btnIniciarCombate.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2, 200, 30 );

		JLabel labelError = new JLabel( "" );
		labelError.setBounds( width / 2 - 220 / 2, height / 2 + 30 / 2, 220, 30 );
		labelError.setForeground( new Color( 155, 0, 0 ) );
		labelError.setFont( new Font( labelError.getFont( ).getFontName( ), labelError.getFont( ).getStyle( ), 14 ) );
		labelError.setHorizontalAlignment( SwingConstants.CENTER );

		add( labelImg );
		add( PJComboBox );
		add( btnAddPJ );
		add( btnAddAllPJ );
		add( listaPJ );
		add( btnRemovePJ );
		add( btnRemoveAllPJ );
		add( PDMComboBox );
		add( btnAddReplicaPDM );
		add( btnAddPDM );
		add( btnAddAllPDM );
		add( listaPDM );
		add( btnRemovePDM );
		add( btnRemoveAllPDM );
		add( btnIniciarCombate );
		add( labelError );

		frame.repaint( );

		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;

				setBounds( 0, 0, width, height );
				labelImg.setBounds( ( width - 200 ) / 2, 50, 200, 200 );
				btnIniciarCombate.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2,
						btnIniciarCombate.getBounds( ).width, btnIniciarCombate.getBounds( ).height );
				labelError.setBounds( width / 2 - 220 / 2, height / 2 + 30 / 2, 220, 30 );
				PDMComboBox.setBounds( width - ( maiorNome + 50 ), 50, PDMComboBox.getBounds( ).width,
						PDMComboBox.getBounds( ).height );
				btnAddReplicaPDM.setBounds( width - ( maiorNome + 250 ), 50, btnAddReplicaPDM.getBounds( ).width,
						btnAddReplicaPDM.getBounds( ).height );
				btnAddPDM.setBounds( width - ( maiorNome + 50 ), 130, btnAddPDM.getBounds( ).width,
						btnAddPDM.getBounds( ).height );
				btnAddAllPDM.setBounds( width - ( maiorNome + 250 ), 130, btnAddAllPDM.getBounds( ).width,
						btnAddAllPDM.getBounds( ).height );
				listaPDM.setBounds( width - ( maiorNome + 50 ), 210, listaPDM.getBounds( ).width,
						listaPDM.getBounds( ).height );
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
				if ( PJComboBox.getSelectedItem( ) != null )
				{
					listaPJ.setVisible( true );
					btnRemovePJ.setVisible( true );
					btnRemoveAllPJ.setVisible( true );
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
				int size = PJComboBox.getItemCount( );
				if ( size > 0 )
				{
					listaPJ.setVisible( true );
					btnRemovePJ.setVisible( true );
					btnRemoveAllPJ.setVisible( true );
					listaPJ.setSelectedIndex( 0 );
					for ( int i = 0; i < size; i++ )
					{
						PJsSelecionados.addElement( PJComboBox.getSelectedItem( ) );
						listaPJ.setBounds( 50, 210, maiorNome, listaPJ.getBounds( ).height + 20 );
						listaPJ.setSelectedIndex( PJComboBox.getSelectedIndex( ) );
						PJComboBox.removeItem( PJComboBox.getSelectedItem( ) );
					}
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
					ordenaJListPersonagem( PJComboBox );
				}
			}
		} );

		btnRemoveAllPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int size = PJsSelecionados.getSize( );
				for ( int i = 0; i < size; i++ )
				{
					listaPJ.setSelectedIndex( 0 );
					PJComboBox.addItem( listaPJ.getSelectedValue( ) );
					PJsSelecionados.removeElementAt( 0 );
				}
				listaPJ.setBounds( listaPJ.getBounds( ).x, listaPJ.getBounds( ).y, listaPJ.getBounds( ).width, 0 );
				btnRemovePJ.setVisible( false );
				btnRemoveAllPJ.setVisible( false );
				ordenaJListPersonagem( PJComboBox );
			}
		} );

		btnAddReplicaPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if ( PDMComboBox.getSelectedItem( ) != null )
				{
					listaPDM.setVisible( true );
					btnRemovePDM.setVisible( true );
					btnRemoveAllPDM.setVisible( true );

					Personagem personagem = ( ( Personagem ) PDMComboBox.getSelectedItem( ) ).clone( );
					Integer replica = 1;

					for ( Personagem p : PDMsSelecionados.toList( ) )
					{
						if ( p.getIdPersonagem( ).equals( personagem.getIdPersonagem( ) ) )
							replica++;
					}

					personagem.setReplica( replica );

					PDMsSelecionados.addElement( personagem );
					listaPDM.setBounds( width - ( maiorNome + 50 ), 210, maiorNome, listaPDM.getBounds( ).height + 20 );
					listaPDM.setSelectedIndex( PDMsSelecionados.getSize( ) - 1 );
				}
			}
		} );

		btnAddPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if ( PDMComboBox.getSelectedItem( ) != null )
				{
					listaPDM.setVisible( true );
					btnRemovePDM.setVisible( true );
					btnRemoveAllPDM.setVisible( true );
					PDMsSelecionados.addElement( PDMComboBox.getSelectedItem( ) );
					listaPDM.setBounds( width - ( maiorNome + 50 ), 210, maiorNome, listaPDM.getBounds( ).height + 20 );
					listaPDM.setSelectedIndex( PDMComboBox.getSelectedIndex( ) );
					PDMComboBox.removeItem( PDMComboBox.getSelectedItem( ) );
				}
			}
		} );

		btnAddAllPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int size = PDMComboBox.getItemCount( );
				if ( size > 0 )
				{
					listaPDM.setVisible( true );
					btnRemovePDM.setVisible( true );
					btnRemoveAllPDM.setVisible( true );
					listaPDM.setSelectedIndex( 0 );
					for ( int i = 0; i < size; i++ )
					{
						PDMsSelecionados.addElement( PDMComboBox.getSelectedItem( ) );
						listaPDM.setBounds( width - ( maiorNome + 50 ), 210, maiorNome,
								listaPDM.getBounds( ).height + 20 );
						listaPDM.setSelectedIndex( PDMComboBox.getSelectedIndex( ) );
						PDMComboBox.removeItem( PDMComboBox.getSelectedItem( ) );
					}
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
					if ( ( ( Personagem ) listaPDM.getSelectedValue( ) ).getReplica( ) == 0 )
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
					ordenaJListPersonagem( PDMComboBox );
				}
			}
		} );

		btnRemoveAllPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int size = PDMsSelecionados.getSize( );
				for ( int i = 0; i < size; i++ )
				{
					listaPDM.setSelectedIndex( 0 );
					if ( ( ( Personagem ) listaPDM.getSelectedValue( ) ).getReplica( ) == 0 )
						PDMComboBox.addItem( listaPDM.getSelectedValue( ) );
					PDMsSelecionados.removeElementAt( 0 );
				}
				listaPDM.setBounds( listaPDM.getBounds( ).x, listaPDM.getBounds( ).y, listaPDM.getBounds( ).width, 0 );
				btnRemovePDM.setVisible( false );
				btnRemoveAllPDM.setVisible( false );
				ordenaJListPersonagem( PDMComboBox );
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
					labelError.setText( "Selecione pelo menos um PJ" );
					return;
				}

				if ( PDMsize == 0 )
				{
					labelError.setText( "Selecione pelo menos um PDM" );
					return;
				}

				frame.remove( JPInicial.this );
				frame.setTela( new JPIniciativa( frame, PJsSelecionados.toList( ), PDMsSelecionados.toList( ) ), false );

			}
		} );
	}
	
	public void ordenaJListPersonagem( JComboBox combobox )
	{
		List< Personagem > array = new ArrayList< Personagem >( );
		int size = combobox.getItemCount( );
		for ( int i = 0; i < size; i++ )
			array.add( ( Personagem ) combobox.getItemAt( i ) );
		Collections.sort( array, new NomeComparator( ) );

		combobox.removeAllItems( );
		for ( Personagem item : array )
		{
			combobox.addItem( item );
		}
	}
}
