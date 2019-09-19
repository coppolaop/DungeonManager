package br.com.darksun.gui.characterbuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.darksun.control.EfeitoController;
import br.com.darksun.entity.Efeito;
import br.com.darksun.gui.JFPrincipal;
import br.com.darksun.gui.JPPadrao;
import br.com.darksun.model.TabelaEfeito;

public class JPListarEfeito extends JPPadrao
{
	public JPListarEfeito( JFPrincipal frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );

		List< Efeito > efeitos = new ArrayList< Efeito >( );
		EfeitoController econtrol = new EfeitoController( );

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer( );
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		efeitos = econtrol.listarEfeitos( );
		Collections.sort( efeitos, ( a, b ) -> a.getIdEfeito( ).compareTo( b.getIdEfeito( ) ) );

		TabelaEfeito modelEfeitos = new TabelaEfeito( efeitos );
		JTable tabelaEfeitos = new JTable( modelEfeitos );
		tabelaEfeitos.setAutoCreateRowSorter( true );
		tabelaEfeitos.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		for ( int i = 0; i < modelEfeitos.getColumnCount( ); i++ )
		{
			tabelaEfeitos.getColumnModel( ).getColumn( i ).setCellRenderer( centerRenderer );
		}
		if ( modelEfeitos.getRowCount( ) > 0 )
		{
			tabelaEfeitos.setRowSelectionInterval( 0, 0 );
		}
		tabelaEfeitos.setSelectionMode( 0 );
		tabelaEfeitos.setFont(
				new Font( tabelaEfeitos.getFont( ).getFontName( ), tabelaEfeitos.getFont( ).getStyle( ), 20 ) );
		tabelaEfeitos.setRowHeight( 30 );
		JScrollPane listScrollerEfeitos = new JScrollPane( tabelaEfeitos );
		listScrollerEfeitos.setBounds( 50, 50, width - 100, height - 150 );

		JButton btnAtivarEfeito = new JButton( "Ativar/Desativar Efeito" );
		btnAtivarEfeito.setBounds( width - 850, 20, 200, 30 );

		JButton btnNovoEfeito = new JButton( "Novo Efeito" );
		btnNovoEfeito.setBounds( width - 650, 20, 200, 30 );

		JButton btnEditarEfeito = new JButton( "Editar Efeito" );
		btnEditarEfeito.setBounds( width - 450, 20, 200, 30 );

		JButton btnRemoverEfeito = new JButton( "Remover Efeito" );
		btnRemoverEfeito.setBounds( width - 250, 20, 200, 30 );

		JLabel labelError = new JLabel( "" );
		labelError.setBounds( 50, height / 2 - ( 50 / 2 + 30 / 2 ), 500, 30 );
		labelError.setForeground( new Color( 155, 0, 0 ) );
		labelError.setFont( new Font( labelError.getFont( ).getFontName( ), labelError.getFont( ).getStyle( ), 14 ) );

		// Add
		add( listScrollerEfeitos );
		add( btnNovoEfeito );
		add( btnEditarEfeito );
		add( btnRemoverEfeito );
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
				listScrollerEfeitos.setBounds( 50, 50, width - 100, height - 150 );
				btnAtivarEfeito.setBounds( width - 850, 20, 200, 30 );
				btnNovoEfeito.setBounds( width - 650, 20, 200, 30 );
				btnEditarEfeito.setBounds( width - 450, 20, 200, 30 );
				btnRemoverEfeito.setBounds( width - 250, 20, 200, 30 );
			}
		} );

		btnNovoEfeito.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove( frame.getTela( ) );
				frame.setTela( new JPFormularioEfeito( frame, true, econtrol.newId( ), null ), false );
			}
		} );

		btnEditarEfeito.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer index = tabelaEfeitos.getSelectedRow( );
				if ( index == -1 )
				{
					labelError.setText( "Selecione um Personagem do Jogador" );
				} else
				{
					frame.remove( frame.getTela( ) );
					frame.setTela( new JPFormularioEfeito( frame, true, null, modelEfeitos.getEfeito( index ) ),
							false );
				}

			}
		} );

		btnRemoverEfeito.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabelaEfeitos.getSelectedRow( );

				if ( index >= 0 )
				{
					Efeito efeito = modelEfeitos.getEfeito( index );

					new File( efeito.getFilePath( ) ).delete( );

					modelEfeitos.remover( efeito );

				}
			}
		} );
	}
}
