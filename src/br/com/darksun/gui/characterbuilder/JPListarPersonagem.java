package br.com.darksun.gui.characterbuilder;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

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
		
		List<Personagem> PJs = new ArrayList<Personagem>();
		List<Personagem> PDMs = new ArrayList<Personagem>();
		PersonagemController pcontrol = new PersonagemController();
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
				
		PJs = pcontrol.listarPJs( );
		
		PersonagemListaTableModel modelPJs = new PersonagemListaTableModel( PJs );
		JTable tabelaPJs = new JTable( modelPJs );
		tabelaPJs.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		for(int i = 0; i < modelPJs.getColumnCount( ); i++) {
			tabelaPJs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		tabelaPJs.setRowSelectionInterval( 0, 0 );
		tabelaPJs.setSelectionMode( 0 );
		tabelaPJs.setFont( new Font( tabelaPJs.getFont( ).getFontName( ), tabelaPJs.getFont( ).getStyle( ), 20 ) );
		tabelaPJs.setRowHeight( 30 );
		JScrollPane listScrollerPJs = new JScrollPane( tabelaPJs );
		listScrollerPJs.setBounds( 50, 50,  width - 100, height/2 - 100 );
		
		PDMs = pcontrol.listarPDMs( );
		
		PersonagemListaTableModel modelPDMs = new PersonagemListaTableModel( PDMs );
		JTable tabelaPDMs = new JTable( modelPDMs );
		tabelaPDMs.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		for(int i = 0; i < modelPDMs.getColumnCount( ); i++) {
			tabelaPDMs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		tabelaPDMs.setRowSelectionInterval( 0, 0 );
		tabelaPDMs.setSelectionMode( 0 );
		tabelaPDMs.setFont( new Font( tabelaPDMs.getFont( ).getFontName( ), tabelaPDMs.getFont( ).getStyle( ), 20 ) );
		tabelaPDMs.setRowHeight( 30 );
		JScrollPane listScrollerPDMs = new JScrollPane( tabelaPDMs );
		listScrollerPDMs.setBounds( 50, height/2, width - 100, height/2 - 100 );
		
		add( listScrollerPJs );
		add( listScrollerPDMs );
		
		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;
				setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );
				
				listScrollerPJs.setBounds( 50, 50,  width - 100, height/2 - 100 );
				listScrollerPDMs.setBounds( 50, height/2, width - 100, height/2 - 100 );
			}
		});
	}
}
