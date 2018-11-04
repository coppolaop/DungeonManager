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

public class JPCombate extends JPPadrao
{
	private String ultimoDaRodada;
	private Integer rodada = 0;

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

		String[ ] colunas =
		{ "Nome", "CA", "HP Atual", "HP Total" };
		String[ ][ ] dados = new String[ personagens.size( ) ][ 4 ];

		for ( int i = 0; i < personagens.size( ); i++ )
		{
			dados[i][0] = personagens.get( i ).getNome( );
			dados[i][1] = personagens.get( i ).getCa( ).toString( );
			dados[i][2] = personagens.get( i ).getHpAtual( ).toString( );
			dados[i][3] = personagens.get( i ).getHpMaximo( ).toString( );
		}

		JLabel labelRodada = new JLabel( "Rodada: " );
		labelRodada.setBounds( 50, 15, 100, 20 );
		labelRodada
				.setFont( new Font( labelRodada.getFont( ).getFontName( ), labelRodada.getFont( ).getStyle( ), 20 ) );
		JLabel labelNumeroRodadas = new JLabel( rodada.toString( ) );
		labelNumeroRodadas.setBounds( 150, 15, 80, 20 );
		labelNumeroRodadas.setFont( new Font( labelNumeroRodadas.getFont( ).getFontName( ),
				labelNumeroRodadas.getFont( ).getStyle( ), 20 ) );

		JTable tabela = new JTable( dados, colunas );
		tabela.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		tabela.setRowSelectionInterval( 0, 0 );
		tabela.setSelectionMode( 0 );
		tabela.setFont( new Font( tabela.getFont( ).getFontName( ), tabela.getFont( ).getStyle( ), 20 ) );
		tabela.setRowHeight( 30 );
		JScrollPane listScroller = new JScrollPane( tabela );
		listScroller.setBounds( 50, 50, ( width - 100 ) / 3, height - 150 );

		JButton btnSetaCima = new BasicArrowButton( BasicArrowButton.NORTH );
		JButton btnSetaBaixo = new BasicArrowButton( BasicArrowButton.SOUTH );
		JButton btnFinalTurno = new JButton( "Finalizar Turno" );
		btnSetaCima.setBounds( 100 + ( ( width - 100 ) / 3 ), 50, 50, 50 );
		btnSetaBaixo.setBounds( 100 + ( ( width - 100 ) / 3 ), 150, 50, 50 );
		btnFinalTurno.setBounds( 65 + ( ( width - 100 ) / 3 ), 250, 120, 30 );

		add( listScroller );
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

					Object[ ] aux =
					{ tabela.getValueAt( index - 1, 0 ), tabela.getValueAt( index - 1, 1 ),
							tabela.getValueAt( index - 1, 2 ), tabela.getValueAt( index - 1, 3 ) };

					tabela.setValueAt( tabela.getValueAt( index, 0 ), index - 1, 0 );
					tabela.setValueAt( tabela.getValueAt( index, 1 ), index - 1, 1 );
					tabela.setValueAt( tabela.getValueAt( index, 2 ), index - 1, 2 );
					tabela.setValueAt( tabela.getValueAt( index, 3 ), index - 1, 3 );

					tabela.setValueAt( aux[0], index, 0 );
					tabela.setValueAt( aux[1], index, 1 );
					tabela.setValueAt( aux[2], index, 2 );
					tabela.setValueAt( aux[3], index, 3 );

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
					{ tabela.getValueAt( index + 1, 0 ), tabela.getValueAt( index + 1, 1 ),
							tabela.getValueAt( index + 1, 2 ), tabela.getValueAt( index + 1, 3 ) };

					tabela.setValueAt( tabela.getValueAt( index, 0 ), index + 1, 0 );
					tabela.setValueAt( tabela.getValueAt( index, 1 ), index + 1, 1 );
					tabela.setValueAt( tabela.getValueAt( index, 2 ), index + 1, 2 );
					tabela.setValueAt( tabela.getValueAt( index, 3 ), index + 1, 3 );

					tabela.setValueAt( aux[0], index, 0 );
					tabela.setValueAt( aux[1], index, 1 );
					tabela.setValueAt( aux[2], index, 2 );
					tabela.setValueAt( aux[3], index, 3 );

					tabela.setRowSelectionInterval( index + 1, index + 1 );
				}
			}
		} );

		btnFinalTurno.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
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

				int size = tabela.getRowCount( ) - 1;

				for ( int i = 1; i < size + 1; i++ )
				{
					tabela.setValueAt( tabela.getValueAt( i, 0 ), i - 1, 0 );
					tabela.setValueAt( tabela.getValueAt( i, 1 ), i - 1, 1 );
					tabela.setValueAt( tabela.getValueAt( i, 2 ), i - 1, 2 );
					tabela.setValueAt( tabela.getValueAt( i, 3 ), i - 1, 3 );
				}

				tabela.setValueAt( aux[0], size, 0 );
				tabela.setValueAt( aux[1], size, 1 );
				tabela.setValueAt( aux[2], size, 2 );
				tabela.setValueAt( aux[3], size, 3 );
			}
		} );
	}
}
