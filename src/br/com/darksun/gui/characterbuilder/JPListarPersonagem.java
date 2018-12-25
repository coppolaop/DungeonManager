package br.com.darksun.gui.characterbuilder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;
import br.com.darksun.gui.JFPrincipal;
import br.com.darksun.gui.JPPadrao;
import br.com.darksun.util.TableModel.PersonagemListaTableModel;

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
		tabelaPJs.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		for ( int i = 0; i < modelPJs.getColumnCount( ); i++ )
		{
			tabelaPJs.getColumnModel( ).getColumn( i ).setCellRenderer( centerRenderer );
		}
		tabelaPJs.setRowSelectionInterval( 0, 0 );
		tabelaPJs.setSelectionMode( 0 );
		tabelaPJs.setFont( new Font( tabelaPJs.getFont( ).getFontName( ), tabelaPJs.getFont( ).getStyle( ), 20 ) );
		tabelaPJs.setRowHeight( 30 );
		JScrollPane listScrollerPJs = new JScrollPane( tabelaPJs );
		listScrollerPJs.setBounds( 50, 50, width - 100, height / 2 - 100 );

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
		tabelaPDMs.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		for ( int i = 0; i < modelPDMs.getColumnCount( ); i++ )
		{
			tabelaPDMs.getColumnModel( ).getColumn( i ).setCellRenderer( centerRenderer );
		}
		tabelaPDMs.setRowSelectionInterval( 0, 0 );
		tabelaPDMs.setSelectionMode( 0 );
		tabelaPDMs.setFont( new Font( tabelaPDMs.getFont( ).getFontName( ), tabelaPDMs.getFont( ).getStyle( ), 20 ) );
		tabelaPDMs.setRowHeight( 30 );
		JScrollPane listScrollerPDMs = new JScrollPane( tabelaPDMs );
		listScrollerPDMs.setBounds( 50, height / 2, width - 100, height / 2 - 100 );

		JButton btnNovoPDM = new JButton( "Novo PDM" );
		btnNovoPDM.setBounds( width - 650, height / 2 - 30, 200, 30 );

		JButton btnEditarPDM = new JButton( "Editar PDM" );
		btnEditarPDM.setBounds( width - 450, height / 2 - 30, 200, 30 );

		JButton btnRemoverPDM = new JButton( "Remover PDM" );
		btnRemoverPDM.setBounds( width - 250, height / 2 - 30, 200, 30 );

		// Add
		add( listScrollerPJs );
		add( btnNovoPJ );
		add( btnEditarPJ );
		add( btnRemoverPJ );
		add( listScrollerPDMs );
		add( btnNovoPDM );
		add( btnEditarPDM );
		add( btnRemoverPDM );

		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;

				setBounds( 0, 0, width, height );
				// PJ
				tabelaPJs.setBounds( 50, 50, width - 100, height / 2 - 100 );
				listScrollerPJs.setBounds( 50, 50, width - 100, height / 2 - 100 );
				btnNovoPJ.setBounds( width - 650, 20, 200, 30 );
				btnEditarPJ.setBounds( width - 450, 20, 200, 30 );
				btnRemoverPJ.setBounds( width - 250, 20, 200, 30 );
				// PDM
				tabelaPDMs.setBounds( 50, height / 2, width - 100, height / 2 - 100 );
				listScrollerPDMs.setBounds( 50, height / 2, width - 100, height / 2 - 100 );
				btnNovoPDM.setBounds( width - 650, height / 2 - 30, 200, 30 );
				btnEditarPDM.setBounds( width - 450, height / 2 - 30, 200, 30 );
				btnRemoverPDM.setBounds( width - 250, height / 2 - 30, 200, 30 );
			}
		} );

		btnNovoPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove( JPListarPersonagem.this );

				frame.setTela( new JPCriarPersonagem( frame ) );

				revalidate( );
				repaint( );
			}
		} );

		btnEditarPJ.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{

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

		btnNovoPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove( frame.getTela( ) );

				frame.setTela( new JPCriarPersonagem( frame ) );

				revalidate( );
				repaint( );
			}
		} );

		btnEditarPDM.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{

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
