package br.com.darksun.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicArrowButton;

import br.com.darksun.entity.Personagem;
import br.com.darksun.util.IniciativaComparator;
import br.com.darksun.util.TableModel.PersonagemCombateTableModel;

public class JPCombate extends JPPadrao
{
	private String ultimoDaRodada;
	private Integer rodada = 0;
	String[ ][ ] dados;

	public JPCombate( JFPrincipal frame, List< Personagem > PJs, List< Personagem > PDMs )
	{
		montaTelaCombate( frame, PJs, PDMs );
	}

	public void montaTelaCombate( JFPrincipal frame, List< Personagem > PJs, List< Personagem > PDMs )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );
		List< Personagem > personagens = new ArrayList< Personagem >( );
		System.out.println( "-------   Iniciativa   -------" );

		for ( Personagem personagem : PJs )
		{
			personagens.add( personagem );
			System.out.println( personagem.getNome( ) + " - " + personagem.getIniciativa( ) + " de Iniciativa" );
		}

		System.out.println( "      ----- VS -----" );

		for ( Personagem personagem : PDMs )
		{
			personagens.add( personagem );
			System.out.println( personagem.getNome( ) + " - " + personagem.getIniciativa( ) + " de Iniciativa" );
		}

		System.out.println( "------------------------------" );

		Collections.sort( personagens, new IniciativaComparator( ) );

		JLabel labelRodada = new JLabel( "Rodada: " );
		labelRodada.setBounds( 50, 15, 100, 20 );
		labelRodada
				.setFont( new Font( labelRodada.getFont( ).getFontName( ), labelRodada.getFont( ).getStyle( ), 20 ) );
		JLabel labelNumeroRodadas = new JLabel( rodada.toString( ) );
		labelNumeroRodadas.setBounds( 150, 15, 80, 20 );
		labelNumeroRodadas.setFont( new Font( labelNumeroRodadas.getFont( ).getFontName( ),
				labelNumeroRodadas.getFont( ).getStyle( ), 20 ) );

		PersonagemCombateTableModel model = new PersonagemCombateTableModel( personagens );
		JTable tabela = new JTable( model );
		tabela.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		tabela.setRowSelectionInterval( 0, 0 );
		tabela.setSelectionMode( 0 );
		tabela.setFont( new Font( tabela.getFont( ).getFontName( ), tabela.getFont( ).getStyle( ), 20 ) );
		tabela.setRowHeight( 30 );
		JScrollPane listScroller = new JScrollPane( tabela );
		listScroller.setBounds( 50, 50, ( width - 100 ) / 3, height - 150 );

		JButton btnRemoverPersonagem = new JButton( "Remover Personagem" );
		JButton btnSetaCima = new BasicArrowButton( BasicArrowButton.NORTH );
		JButton btnSetaBaixo = new BasicArrowButton( BasicArrowButton.SOUTH );
		JButton btnFinalTurno = new JButton( "Finalizar Turno" );
		btnRemoverPersonagem.setBounds( ( width / 3 ) - 165, 10, 180, 30 );
		btnSetaCima.setBounds( 100 + ( ( width - 100 ) / 3 ), 50, 50, 50 );
		btnSetaBaixo.setBounds( 100 + ( ( width - 100 ) / 3 ), 150, 50, 50 );
		btnFinalTurno.setBounds( 65 + ( ( width - 100 ) / 3 ), 250, 120, 30 );

		add( listScroller );
		add( btnRemoverPersonagem );
		add( btnSetaCima );
		add( btnSetaBaixo );
		add( btnFinalTurno );
		add( labelRodada );
		add( labelNumeroRodadas );

		frame.repaint( );

		tabela.setRowSelectionInterval( 0, 0 );

		ultimoDaRodada = ( String ) tabela.getValueAt( tabela.getRowCount( ) - 1, 0 );

		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;

				setBounds( 0, 0, width, height );
				tabela.setBounds( 50, 50, ( width - 100 ) / 3, height - 150 );
				listScroller.setBounds( 50, 50, ( width - 100 ) / 3, height - 150 );
				btnRemoverPersonagem.setBounds( ( width / 3 ) - 165, 10, 180, 30 );
				btnSetaCima.setBounds( 100 + ( ( width - 100 ) / 3 ), 50, 50, 50 );
				btnSetaBaixo.setBounds( 100 + ( ( width - 100 ) / 3 ), 150, 50, 50 );
				btnFinalTurno.setBounds( 65 + ( ( width - 100 ) / 3 ), 250, 120, 30 );
			}
		} );

		btnSetaCima.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabela.getSelectedRow( );

				if ( index > 0 )
				{
					System.out.println( tabela.getValueAt( index, 0 ) + " foi reposicionado para antes de "
							+ tabela.getValueAt( index - 1, 0 ) );

					if ( tabela.getValueAt( index, 0 ).equals( ultimoDaRodada ) )
					{
						ultimoDaRodada = ( String ) tabela.getValueAt( index - 1, 0 );
						System.out.println( "A rodada agora acaba depois de " + ultimoDaRodada );
					}

					model.trocar( index, index - 1 );

					tabela.setRowSelectionInterval( index - 1, index - 1 );
				}
			}
		} );

		btnSetaBaixo.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabela.getSelectedRow( );

				if ( index < tabela.getRowCount( ) - 1 )
				{
					System.out.println( tabela.getValueAt( index, 0 ) + " foi reposicionado para depois de "
							+ tabela.getValueAt( index + 1, 0 ) );

					if ( tabela.getValueAt( index + 1, 0 ).equals( ultimoDaRodada ) )
					{
						ultimoDaRodada = ( String ) tabela.getValueAt( index, 0 );
						System.out.println( "A rodada agora acaba depois de " + ultimoDaRodada );
					}

					Object[ ] aux =
					{ tabela.getValueAt( index, 0 ), tabela.getValueAt( index, 1 ),
							tabela.getValueAt( index, 2 ), tabela.getValueAt( index, 3 ) };

					model.trocar( index, index + 1 );

					tabela.setRowSelectionInterval( index + 1, index + 1 );
				}
			}
		} );

		btnFinalTurno.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer selected = tabela.getSelectedRow( );
				Personagem personagem = model.getPersonagem( 0 );
				
				Object[ ] aux =
				{ tabela.getValueAt( 0, 0 ), tabela.getValueAt( 0, 1 ), tabela.getValueAt( 0, 2 ),
						tabela.getValueAt( 0, 3 ) };

				System.out.println( aux[0] + " finalizou seu turno" );

				if ( aux[0].equals( ultimoDaRodada ) )
				{
					rodada++;
					labelNumeroRodadas.setText( rodada.toString( ) );
					System.out.println( "-- A Rodada " + rodada + " acabou --" );
				}
				
				if( selected == 0 )
					selected = tabela.getRowCount( ) - 1;
				else
					selected --;
					

				model.remover( personagem );
				model.adicionar( personagem );
				tabela.setRowSelectionInterval( selected, selected );
			}
		} );

		btnRemoverPersonagem.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{	
				try
				{
					Integer removido = tabela.getSelectedRow( );
					Personagem personagem = model.getPersonagem( removido );

					System.out.println( tabela.getValueAt( removido, 0 ) + " foi removido do combate" );

					if ( tabela.getValueAt( removido, 0 ).equals( ultimoDaRodada ) )
					{
						if ( removido == 0 )
							ultimoDaRodada = ( String ) tabela.getValueAt( 1, 0 );
						else
							ultimoDaRodada = ( String ) tabela.getValueAt( removido - 1, 0 );

						System.out.println( "A rodada agora acaba depois de " + ultimoDaRodada );
					}

					model.remover( personagem );

					if ( tabela.getRowCount( ) - 1 < removido )
						tabela.setRowSelectionInterval( removido - 1, removido - 1 );
					else
						tabela.setRowSelectionInterval( removido, removido );
				} catch ( IndexOutOfBoundsException ex )
				{
					System.out.println( "------- Fim do combate -------" );
					System.out.println( "------------------------------" );
					frame.remove( JPCombate.this );
					frame.setTela( new JPInicial( frame ) );
				} catch ( Exception ex )
				{
					ex.printStackTrace( );
				}
			}
		} );
		
//		tabela.getModel( ).addTableModelListener( new TableModelListener() {
//			@Override
//			public void tableChanged( TableModelEvent e )
//			{
//				if( e.getType( ) == 0) {
//					Integer column = e.getColumn( );
//					Integer row = e.getFirstRow( );
//					
//					String nome = model.getValueAt( row, 0 ).toString( );
//					
//					//System.out.println( "Anterior: " + tabela.getValueAt( row, column ) );
//					
////					System.out.println( "Atual: " + tabela.getValueAt( row, column ) );
//					
//					for(String [] dado : dados)
//						if(nome.equals( dado[0] ))
//							System.out.println( "ID: " + dado[4] );
//					
//					
//				}
//			}
//		});
		
	}
}
