package br.com.darksun.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;

import br.com.darksun.entity.Personagem;
import br.com.darksun.util.Model.PersonagemCombateTableModel;
import br.com.darksun.util.comparator.IniciativaComparator;

public class JPCombate extends JPPadrao
{
	private Personagem ultimoDaRodada;
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
			System.out.println( personagem.toString( ) + " - " + personagem.getIniciativa( ) + " de Iniciativa" );
		}

		System.out.println( "      ----- VS -----" );

		for ( Personagem personagem : PDMs )
		{
			personagens.add( personagem );
			System.out.println( personagem.toString( ) + " - " + personagem.getIniciativa( ) + " de Iniciativa" );
		}

		System.out.println( "------------------------------" );

		Collections.sort( personagens, new IniciativaComparator( ) );

		JLabel labelRodada = new JLabel( "Rodada: " );
		labelRodada.setBounds( 50, 15, 100, 20 );
		labelRodada.setForeground( Color.WHITE );
		labelRodada
				.setFont( new Font( labelRodada.getFont( ).getFontName( ), labelRodada.getFont( ).getStyle( ), 20 ) );
		JLabel labelNumeroRodadas = new JLabel( rodada.toString( ) );
		labelNumeroRodadas.setBounds( 150, 15, 80, 20 );
		labelNumeroRodadas.setForeground( Color.WHITE );
		labelNumeroRodadas.setFont( new Font( labelNumeroRodadas.getFont( ).getFontName( ),
				labelNumeroRodadas.getFont( ).getStyle( ), 20 ) );

		JLabel labelLog = new JLabel( "Log: " );
		labelLog.setBounds( 65 + ( ( width - 100 ) / 3 ), height - 150, 50, 30 );
		labelLog.setForeground( Color.WHITE );
		labelLog.setFont( new Font( labelLog.getFont( ).getFontName( ), labelLog.getFont( ).getStyle( ), 16 ) );

		JLabel labelTextoLog = new JLabel( "" );
		labelTextoLog.setBounds( 115 + ( ( width - 100 ) / 3 ), height - 150, width - ( 165 + ( ( width - 100 ) / 3 ) ),
				30 );
		labelTextoLog.setForeground( Color.WHITE );
		labelTextoLog.setFont(
				new Font( labelTextoLog.getFont( ).getFontName( ), labelTextoLog.getFont( ).getStyle( ), 16 ) );

		PersonagemCombateTableModel model = new PersonagemCombateTableModel( personagens, labelTextoLog );
		JTable tabela = new JTable( model );
		tabela.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		tabela.setRowSelectionInterval( 0, 0 );
		tabela.setSelectionMode( 0 );
		tabela.setFont( new Font( tabela.getFont( ).getFontName( ), tabela.getFont( ).getStyle( ), 20 ) );
		tabela.setRowHeight( 30 );
		JScrollPane listScroller = new JScrollPane( tabela );
		listScroller.setBounds( 50, 50, ( width - 100 ) / 3, height - 150 );

		JButton btnAdicionarPersonagem = new JButton( "Adicionar Personagem" );
		JButton btnRemoverPersonagem = new JButton( "Remover Personagem" );
		JButton btnSetaCima = new BasicArrowButton( BasicArrowButton.NORTH );
		JButton btnSetaBaixo = new BasicArrowButton( BasicArrowButton.SOUTH );
		JButton btnFinalTurno = new JButton( "Finalizar Turno" );
		btnAdicionarPersonagem.setBounds( ( ( width - 100 ) / 3 ) - 195, 10, 180, 30 );
		btnRemoverPersonagem.setBounds( 5 + ( ( width - 100 ) / 3 ), 10, 180, 30 );
		btnSetaCima.setBounds( 65 + ( ( width - 100 ) / 3 ), 70, 120, 30 );
		btnSetaBaixo.setBounds( 65 + ( ( width - 100 ) / 3 ), 110, 120, 30 );
		btnFinalTurno.setBounds( 65 + ( ( width - 100 ) / 3 ), 150, 120, 30 );

		JLabel labelImg = new JLabel( "" );
		labelImg.setBounds( 215 + ( ( width - 100 ) / 3 ), 50, 200, 200 );
		Border border = BorderFactory.createLineBorder( Color.WHITE, 5 );
		labelImg.setBorder( border );
		labelImg.setVisible( false );

		tabela.getColumnModel( ).getColumn( 0 ).setPreferredWidth( model.getMaiorNome( ) );

		add( listScroller );
		add( btnAdicionarPersonagem );
		add( btnRemoverPersonagem );
		add( btnSetaCima );
		add( btnSetaBaixo );
		add( btnFinalTurno );
		add( labelRodada );
		add( labelNumeroRodadas );
		add( labelLog );
		add( labelTextoLog );
		add( labelImg );

		frame.repaint( );

		tabela.setRowSelectionInterval( 0, 0 );

		ultimoDaRodada = model.getPersonagem( tabela.getRowCount( ) - 1 );

		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;

				setBounds( 0, 0, width, height );
				listScroller.setBounds( 50, 50, ( width - 100 ) / 3, height - 150 );
				btnAdicionarPersonagem.setBounds( ( ( width - 100 ) / 3 ) - 195, 10, 180, 30 );
				btnRemoverPersonagem.setBounds( 5 + ( ( width - 100 ) / 3 ), 10, 180, 30 );
				btnSetaCima.setBounds( 65 + ( ( width - 100 ) / 3 ), 70, 120, 30 );
				btnSetaBaixo.setBounds( 65 + ( ( width - 100 ) / 3 ), 110, 120, 30 );
				btnFinalTurno.setBounds( 65 + ( ( width - 100 ) / 3 ), 150, 120, 30 );
				labelLog.setBounds( 65 + ( ( width - 100 ) / 3 ), height - 150, 50, 30 );
				labelTextoLog.setBounds( 115 + ( ( width - 100 ) / 3 ), height - 150,
						width - ( 165 + ( ( width - 100 ) / 3 ) ), 30 );
				tabela.setRowHeight( 30 );
				labelImg.setBounds( 215 + ( ( width - 100 ) / 3 ), 50, 200, 200 );
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

					labelTextoLog.setText( tabela.getValueAt( index, 0 ) + " foi reposicionado para antes de "
							+ tabela.getValueAt( index - 1, 0 ) );

					if ( model.getPersonagem( index ).equals( ultimoDaRodada ) )
					{
						ultimoDaRodada = model.getPersonagem( index - 1 );
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

					labelTextoLog.setText( tabela.getValueAt( index, 0 ) + " foi reposicionado para depois de "
							+ tabela.getValueAt( index + 1, 0 ) );

					if ( model.getPersonagem( index + 1 ).equals( ultimoDaRodada ) )
					{
						ultimoDaRodada = model.getPersonagem( index );
						System.out.println( "A rodada agora acaba depois de " + ultimoDaRodada );
					}

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

				labelTextoLog.setText( aux[0] + " finalizou seu turno" );

				if ( model.getPersonagem( 0 ).equals( ultimoDaRodada ) )
				{
					rodada++;
					labelNumeroRodadas.setText( rodada.toString( ) );
					System.out.println( "-- A Rodada " + rodada + " acabou --" );
				}

				if ( selected == 0 )
					selected = tabela.getRowCount( ) - 1;
				else
					selected--;

				model.remover( personagem );
				model.adicionar( personagem );
				tabela.setRowSelectionInterval( selected, selected );
			}
		} );

		btnAdicionarPersonagem.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				JDIncluirPersonagem ip = new JDIncluirPersonagem( frame );
				ip.setVisible( true );
				Personagem personagem = ip.getPersonagemSelecionado( );
				if ( personagem != null )
				{
					while ( model.contains( personagem ) )
					{
						personagem.setReplica( personagem.getReplica( ) + 1 );
					}
					model.adicionar( personagem );
					System.out.println( personagem + " foi adicionado ao combate" );
					labelTextoLog.setText( personagem + " foi adicionado ao combate" );
				}
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

					labelTextoLog.setText( tabela.getValueAt( removido, 0 ) + " foi removido do combate" );

					if ( model.getPersonagem( removido ).equals( ultimoDaRodada ) )
					{
						if ( removido == 0 )
							ultimoDaRodada = model.getPersonagem( 1 );
						else
							ultimoDaRodada = model.getPersonagem( removido - 1 );

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

		tabela.addMouseListener( new MouseListener( )
		{

			@Override
			public void mouseClicked( MouseEvent e )
			{
				Personagem personagem = model.getPersonagem( tabela.getSelectedRow( ) );
				File imagem = new File( personagem.getImagem( ) );
				if ( imagem.exists( ) )
				{
					Image logoApp = Toolkit.getDefaultToolkit( ).getImage( personagem.getImagem( ) );
					labelImg.setIcon( new ImageIcon( logoApp.getScaledInstance( labelImg.getWidth( ),
							labelImg.getHeight( ), logoApp.SCALE_DEFAULT ) ) );
				} else
				{
					ImageIcon logoApp = new ImageIcon( getClass( ).getClassLoader( )
							.getResource( personagem.getIsPJ( ) ? "img/pjgenerico.jpg" : "img/pdmgenerico.jpg" ) );
					labelImg.setIcon( new ImageIcon( logoApp.getImage( ).getScaledInstance( labelImg.getWidth( ),
							labelImg.getHeight( ), logoApp.getImage( ).SCALE_DEFAULT ) ) );
				}

				labelImg.setVisible( true );
			}

			@Override
			public void mousePressed( MouseEvent e )
			{

			}

			@Override
			public void mouseReleased( MouseEvent e )
			{

			}

			@Override
			public void mouseEntered( MouseEvent e )
			{

			}

			@Override
			public void mouseExited( MouseEvent e )
			{

			}
		} );
	}
}
