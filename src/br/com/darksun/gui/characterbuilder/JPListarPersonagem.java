package br.com.darksun.gui.characterbuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;
import br.com.darksun.gui.JFPrincipal;
import br.com.darksun.gui.JPPadrao;
import br.com.darksun.util.Model.PersonagemListaTableModel;

public class JPListarPersonagem extends JPPadrao
{
	public JPListarPersonagem( JFPrincipal frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );

		List< Personagem > PJs = new ArrayList< Personagem >( );
		List< Personagem > PDMs = new ArrayList< Personagem >( );
		PersonagemController pcontrol = new PersonagemController( );

		// PJ
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer( );
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		PJs = pcontrol.listarPJs( );

		PersonagemListaTableModel modelPJs = new PersonagemListaTableModel( PJs );
		JTable tabelaPJs = new JTable( modelPJs );
		tabelaPJs.setAutoCreateRowSorter( true );
		tabelaPJs.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		for ( int i = 0; i < modelPJs.getColumnCount( ); i++ )
		{
			tabelaPJs.getColumnModel( ).getColumn( i ).setCellRenderer( centerRenderer );
		}
		if ( modelPJs.getRowCount( ) > 0 )
			tabelaPJs.setRowSelectionInterval( 0, 0 );
		tabelaPJs.setSelectionMode( 0 );
		tabelaPJs.setFont( new Font( tabelaPJs.getFont( ).getFontName( ), tabelaPJs.getFont( ).getStyle( ), 20 ) );
		tabelaPJs.setRowHeight( 30 );
		JScrollPane listScrollerPJs = new JScrollPane( tabelaPJs );
		listScrollerPJs.setBounds( 50, 50, width - 100, height / 2 - 100 );

		JButton btnAtivarPJ = new JButton( "Ativar/Desativar PJ" );
		btnAtivarPJ.setBounds( width - 850, 20, 200, 30 );

		JButton btnNovoPJ = new JButton( "Novo PJ" );
		btnNovoPJ.setBounds( width - 650, 20, 200, 30 );

		JButton btnEditarPJ = new JButton( "Editar PJ" );
		btnEditarPJ.setBounds( width - 450, 20, 200, 30 );

		JButton btnRemoverPJ = new JButton( "Remover PJ" );
		btnRemoverPJ.setBounds( width - 250, 20, 200, 30 );

		// PDMs
		PDMs = pcontrol.listarPDMs( );

		PersonagemListaTableModel modelPDMs = new PersonagemListaTableModel( PDMs );
		JTable tabelaPDMs = new JTable( modelPDMs );
		tabelaPDMs.setAutoCreateRowSorter( true );
		tabelaPDMs.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		for ( int i = 0; i < modelPDMs.getColumnCount( ); i++ )
		{
			tabelaPDMs.getColumnModel( ).getColumn( i ).setCellRenderer( centerRenderer );
		}
		if ( modelPDMs.getRowCount( ) > 0 )
			tabelaPDMs.setRowSelectionInterval( 0, 0 );
		tabelaPDMs.setSelectionMode( 0 );
		tabelaPDMs.setFont( new Font( tabelaPDMs.getFont( ).getFontName( ), tabelaPDMs.getFont( ).getStyle( ), 20 ) );
		tabelaPDMs.setRowHeight( 30 );
		JScrollPane listScrollerPDMs = new JScrollPane( tabelaPDMs );
		listScrollerPDMs.setBounds( 50, height / 2, width - 100, height / 2 - 100 );

		JButton btnAtivarPDM = new JButton( "Ativar/Desativar PDM" );
		btnAtivarPDM.setBounds( width - 850, height / 2 - 30, 200, 30 );

		JButton btnNovoPDM = new JButton( "Novo PDM" );
		btnNovoPDM.setBounds( width - 650, height / 2 - 30, 200, 30 );

		JButton btnEditarPDM = new JButton( "Editar PDM" );
		btnEditarPDM.setBounds( width - 450, height / 2 - 30, 200, 30 );

		JButton btnRemoverPDM = new JButton( "Remover PDM" );
		btnRemoverPDM.setBounds( width - 250, height / 2 - 30, 200, 30 );

		JLabel labelError = new JLabel( "" );
		labelError.setBounds( 50, height / 2 - ( 50 / 2 + 30 / 2 ), 500, 30 );
		labelError.setForeground( new Color( 155, 0, 0 ) );
		labelError.setFont( new Font( labelError.getFont( ).getFontName( ), labelError.getFont( ).getStyle( ), 14 ) );

		// Add
		add( listScrollerPJs );
		add( btnAtivarPJ );
		add( btnNovoPJ );
		add( btnEditarPJ );
		add( btnRemoverPJ );
		add( listScrollerPDMs );
		add( btnAtivarPDM );
		add( btnNovoPDM );
		add( btnEditarPDM );
		add( btnRemoverPDM );
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
				labelError.setBounds( 50, height / 2 - ( 50 / 2 + 30 / 2 ), 500, 30 );
				// PJ
				listScrollerPJs.setBounds( 50, 50, width - 100, height / 2 - 100 );
				btnAtivarPJ.setBounds( width - 850, 20, 200, 30 );
				btnNovoPJ.setBounds( width - 650, 20, 200, 30 );
				btnEditarPJ.setBounds( width - 450, 20, 200, 30 );
				btnRemoverPJ.setBounds( width - 250, 20, 200, 30 );
				// PDM
				listScrollerPDMs.setBounds( 50, height / 2, width - 100, height / 2 - 100 );
				btnAtivarPDM.setBounds( width - 850, height / 2 - 30, 200, 30 );
				btnNovoPDM.setBounds( width - 650, height / 2 - 30, 200, 30 );
				btnEditarPDM.setBounds( width - 450, height / 2 - 30, 200, 30 );
				btnRemoverPDM.setBounds( width - 250, height / 2 - 30, 200, 30 );

			}
		} );

		btnAtivarPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer index = tabelaPJs.getSelectedRow( );
				if ( index >= 0 )
				{
					Personagem personagem = modelPJs.getPersonagem( index );
					personagem.changeStatus( );
					PersonagemController pc = new PersonagemController( );
					pc.criarPersonagem( personagem );
					modelPJs.fireTableDataChanged( );
					tabelaPJs.setRowSelectionInterval( index, index );
				}
			}
		} );

		btnNovoPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove( frame.getTela( ) );
				frame.setTela( new JPFormularioPersonagem( frame, true, pcontrol.newId( ), null ) );
			}
		} );

		btnEditarPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer index = tabelaPJs.getSelectedRow( );
				if ( index == -1 )
				{
					labelError.setText( "Selecione um Personagem do Jogador" );
				} else
				{
					frame.remove( frame.getTela( ) );
					frame.setTela( new JPFormularioPersonagem( frame, true, null, modelPJs.getPersonagem( index ) ) );
				}

			}
		} );

		btnRemoverPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabelaPJs.getSelectedRow( );

				if ( index >= 0 )
				{
					Personagem personagem = modelPJs.getPersonagem( index );

					File arquivoProperties = new File( personagem.getFilePath( ) );

					arquivoProperties.delete( );

					modelPJs.remover( personagem );

				}
			}
		} );

		btnAtivarPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer index = tabelaPDMs.getSelectedRow( );
				if ( index >= 0 )
				{
					Personagem personagem = modelPDMs.getPersonagem( index );
					personagem.changeStatus( );
					PersonagemController pc = new PersonagemController( );
					pc.criarPersonagem( personagem );
					modelPDMs.fireTableDataChanged( );
					tabelaPDMs.setRowSelectionInterval( index, index );
				}
			}
		} );

		btnNovoPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove( frame.getTela( ) );
				frame.setTela( new JPFormularioPersonagem( frame, false, pcontrol.newId( ), null ) );
			}
		} );

		btnEditarPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer index = tabelaPDMs.getSelectedRow( );
				if ( index == -1 )
				{
					labelError.setText( "Selecione um Personagem do Mestre" );
				} else
				{
					frame.remove( frame.getTela( ) );
					frame.setTela( new JPFormularioPersonagem( frame, false, null, modelPDMs.getPersonagem( index ) ) );
				}
			}
		} );

		btnRemoverPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabelaPDMs.getSelectedRow( );

				if ( index >= 0 )
				{
					Personagem personagem = modelPDMs.getPersonagem( index );

					File arquivoProperties = new File( personagem.getFilePath( ) );

					arquivoProperties.delete( );

					modelPDMs.remover( personagem );

				}
			}
		} );
	}
}
