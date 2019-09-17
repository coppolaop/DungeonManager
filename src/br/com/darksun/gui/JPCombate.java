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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;

import br.com.darksun.control.CombateController;
import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;
import br.com.darksun.util.RowPainter;
import br.com.darksun.util.adapter.JLabelAdapter;

public class JPCombate extends JPPadrao
{
	private Personagem personagemSelecionado;

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

		JLabel labelRodada = new JLabel( "Rodada: " );
		labelRodada.setBounds( 50, 15, 100, 20 );
		labelRodada.setForeground( Color.WHITE );
		labelRodada
				.setFont( new Font( labelRodada.getFont( ).getFontName( ), labelRodada.getFont( ).getStyle( ), 20 ) );

		JLabel labelLog = new JLabel( "Log: " );
		labelLog.setBounds( 65 + ( ( width - 100 ) / 3 ), height - 150, 50, 30 );
		labelLog.setForeground( Color.WHITE );
		labelLog.setFont( new Font( labelLog.getFont( ).getFontName( ), labelLog.getFont( ).getStyle( ), 16 ) );

		JLabelAdapter labelTextoLog = new JLabelAdapter( "" );
		labelTextoLog.setBounds( 115 + ( ( width - 100 ) / 3 ), height - 150, width - ( 165 + ( ( width - 100 ) / 3 ) ),
				30 );
		labelTextoLog.setForeground( Color.WHITE );
		labelTextoLog.setFont(
				new Font( labelTextoLog.getFont( ).getFontName( ), labelTextoLog.getFont( ).getStyle( ), 16 ) );

		JLabelAdapter labelNumeroRodadas = new JLabelAdapter( "0" );
		labelNumeroRodadas.setBounds( 150, 15, 80, 20 );
		labelNumeroRodadas.setForeground( Color.WHITE );
		labelNumeroRodadas.setFont( new Font( labelNumeroRodadas.getFont( ).getFontName( ),
				labelNumeroRodadas.getFont( ).getStyle( ), 20 ) );

		CombateController controller = new CombateController( PJs, PDMs, labelTextoLog, labelNumeroRodadas );

		JTable tabela = new JTable( controller.getModel( ) );
		tabela.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		tabela.setRowSelectionInterval( 0, 0 );
		tabela.setSelectionMode( 0 );
		tabela.setFont( new Font( tabela.getFont( ).getFontName( ), tabela.getFont( ).getStyle( ), 20 ) );
		tabela.setRowHeight( 30 );
		RowPainter painter = new RowPainter( );
		tabela.setDefaultRenderer( tabela.getColumnClass( 0 ), painter );
		JScrollPane listScroller = new JScrollPane( tabela );
		listScroller.setBounds( 50, 50, ( width - 100 ) / 3, height - 150 );

		JButton btnAdicionarPersonagem = new JButton( "Adicionar Personagem" );
		JButton btnRemoverPersonagem = new JButton( "Remover Personagem" );
		JButton btnSetaCima = new BasicArrowButton( BasicArrowButton.NORTH );
		JButton btnSetaBaixo = new BasicArrowButton( BasicArrowButton.SOUTH );
		JButton btnFinalTurno = new JButton( "Finalizar Turno" );
		JButton btnMaisCondicao = new JButton( "+ Efeito" );
		JButton btnMenosCondicao = new JButton( "- Efeito" );
		btnAdicionarPersonagem.setBounds( ( ( width - 100 ) / 3 ) - 195, 10, 180, 30 );
		btnRemoverPersonagem.setBounds( 5 + ( ( width - 100 ) / 3 ), 10, 180, 30 );
		btnSetaCima.setBounds( 65 + ( ( width - 100 ) / 3 ), 70, 120, 30 );
		btnSetaBaixo.setBounds( 65 + ( ( width - 100 ) / 3 ), 110, 120, 30 );
		btnFinalTurno.setBounds( 65 + ( ( width - 100 ) / 3 ), 150, 120, 30 );
		btnMaisCondicao.setBounds( 65 + ( ( width - 100 ) / 3 ), 230, 120, 30 );
		btnMenosCondicao.setBounds( 65 + ( ( width - 100 ) / 3 ), 270, 120, 30 );

		JLabel labelImg = new JLabel( "" );
		labelImg.setBounds( 215 + ( ( width - 100 ) / 3 ), 50, 200, 200 );
		Border border = BorderFactory.createLineBorder( Color.WHITE, 5 );
		labelImg.setBorder( border );
		labelImg.setVisible( false );

		JTextArea areaDescricao = new JTextArea( 5, 5 );
		JScrollPane areaScrollerDescricao = new JScrollPane( areaDescricao );
		areaDescricao.setBounds( 215 + ( ( width - 100 ) / 3 ), 265, width - ( 265 + ( ( width - 100 ) / 3 ) ),
				( height / 2 ) - 265 );
		areaScrollerDescricao.setBounds( 215 + ( ( width - 100 ) / 3 ), 265, width - ( 265 + ( ( width - 100 ) / 3 ) ),
				( height / 2 ) - 265 );
		areaDescricao.setLineWrap( true );
		areaDescricao.setVisible( false );
		areaScrollerDescricao.setVisible( false );

		JButton btnSalvarDescricao = new JButton( "Salvar" );
		JButton btnDesfazerDescricao = new JButton( "Desfazer" );
		btnSalvarDescricao.setBounds( width - 250, 235, 100, 30 );
		btnDesfazerDescricao.setBounds( width - 150, 235, 100, 30 );
		btnSalvarDescricao.setVisible( false );
		btnDesfazerDescricao.setVisible( false );

		tabela.getColumnModel( ).getColumn( 0 ).setPreferredWidth( controller.getModel( ).getMaiorNome( ) );

		add( listScroller );
		add( btnAdicionarPersonagem );
		add( btnRemoverPersonagem );
		add( btnSetaCima );
		add( btnSetaBaixo );
		add( btnFinalTurno );
		add( btnMaisCondicao );
		add( btnMenosCondicao );
		add( labelRodada );
		add( labelNumeroRodadas );
		add( labelLog );
		add( labelTextoLog );
		add( labelImg );
		add( areaScrollerDescricao );
		add( btnSalvarDescricao );
		add( btnDesfazerDescricao );

		frame.repaint( );

		tabela.setRowSelectionInterval( 0, 0 );

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
				btnMaisCondicao.setBounds( 65 + ( ( width - 100 ) / 3 ), 230, 120, 30 );
				btnMenosCondicao.setBounds( 65 + ( ( width - 100 ) / 3 ), 270, 120, 30 );
				labelLog.setBounds( 65 + ( ( width - 100 ) / 3 ), height - 150, 50, 30 );
				labelTextoLog.setBounds( 115 + ( ( width - 100 ) / 3 ), height - 150,
						width - ( 165 + ( ( width - 100 ) / 3 ) ), 30 );
				tabela.setRowHeight( 30 );
				labelImg.setBounds( 215 + ( ( width - 100 ) / 3 ), 50, 200, 200 );
				areaDescricao.setBounds( 215 + ( ( width - 100 ) / 3 ), 265, width - ( 265 + ( ( width - 100 ) / 3 ) ),
						( height / 2 ) - 265 );
				areaScrollerDescricao.setBounds( 215 + ( ( width - 100 ) / 3 ), 265,
						width - ( 265 + ( ( width - 100 ) / 3 ) ), ( height / 2 ) - 265 );
				btnSalvarDescricao.setBounds( width - 250, 235, 100, 30 );
				btnDesfazerDescricao.setBounds( width - 150, 235, 100, 30 );
			}
		} );

		btnSetaCima.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabela.getSelectedRow( );

				controller.subir( index );

				if ( index > 0 )
				{
					tabela.setRowSelectionInterval( index - 1, index - 1 );
				}
			}
		} );

		btnSetaBaixo.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabela.getSelectedRow( );

				controller.descer( index );

				if ( index < tabela.getRowCount( ) - 1 )
				{
					tabela.setRowSelectionInterval( index + 1, index + 1 );
				}
			}
		} );

		btnFinalTurno.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer selected = tabela.getSelectedRow( );

				controller.finalizarTurno( labelNumeroRodadas );

				if ( selected == 0 )
				{
					selected = tabela.getRowCount( ) - 1;
				} else
				{
					selected--;
				}
				tabela.setRowSelectionInterval( selected, selected );
			}
		} );

		btnMaisCondicao.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				int index = tabela.getSelectedRow( );

				JDIncluirEfeito ie = new JDIncluirEfeito( frame );
				ie.setVisible( true );

				painter.setSituacao( tabela, index, controller.adicionarCondicao( index, ie.getEfeitoSelecionado( ),
						ie.getDuracaoSelecionada( ), ie.getValorSelecionado( ) ) );
			}
		} );

		btnMenosCondicao.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				// int index = tabela.getSelectedRow( );
				//
				// painter.removePositivo( controller.getModel( ).getPersonagem( index ) );
			}
		} );

		btnAdicionarPersonagem.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				JDIncluirPersonagem ip = new JDIncluirPersonagem( frame );
				ip.setVisible( true );
				Personagem personagem = ip.getPersonagemSelecionado( );
				int quantidade = ip.getNumeroSelecionado( );
				controller.adicionarPersonagem( personagem, quantidade );
			}
		} );

		btnRemoverPersonagem.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Integer removido = tabela.getSelectedRow( );
				Boolean fimDoCombate = controller.removerPersonagem( removido );

				if ( fimDoCombate )
				{
					frame.remove( JPCombate.this );
					frame.setTela( new JPInicial( frame ), true );
					return;
				}

				if ( tabela.getRowCount( ) - 1 < removido )
				{
					tabela.setRowSelectionInterval( removido - 1, removido - 1 );
				} else
				{
					tabela.setRowSelectionInterval( removido, removido );
				}
			}
		} );

		tabela.addMouseListener( new MouseListener( )
		{

			@Override
			public void mouseClicked( MouseEvent e )
			{
				personagemSelecionado = controller.getModel( ).getPersonagem( tabela.getSelectedRow( ) );
				File imagem = new File( personagemSelecionado.getImagem( ) );
				if ( imagem.exists( ) )
				{
					Image logoApp = Toolkit.getDefaultToolkit( ).getImage( personagemSelecionado.getImagem( ) );
					labelImg.setIcon( new ImageIcon( logoApp.getScaledInstance( labelImg.getWidth( ),
							labelImg.getHeight( ), logoApp.SCALE_DEFAULT ) ) );
				} else
				{
					ImageIcon logoApp = new ImageIcon( getClass( ).getClassLoader( ).getResource(
							personagemSelecionado.getIsPJ( ) ? "img/pjgenerico.jpg" : "img/pdmgenerico.jpg" ) );
					labelImg.setIcon( new ImageIcon( logoApp.getImage( ).getScaledInstance( labelImg.getWidth( ),
							labelImg.getHeight( ), logoApp.getImage( ).SCALE_DEFAULT ) ) );
				}

				labelImg.setVisible( true );
				areaDescricao.setVisible( true );
				areaScrollerDescricao.setVisible( true );
				btnSalvarDescricao.setVisible( true );
				btnDesfazerDescricao.setVisible( true );

				areaDescricao.setText( null );
				areaDescricao.setText( personagemSelecionado.getDescricao( ) );
				areaDescricao.repaint( );
				labelImg.repaint( );
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

		btnSalvarDescricao.addActionListener( new ActionListener( )
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				personagemSelecionado.setDescricao( areaDescricao.getText( ) );
				if ( personagemSelecionado.getReplica( ) == 0 )
				{
					new PersonagemController( ).atualizarArquivo( personagemSelecionado.getFilePath( ), "descricao",
							areaDescricao.getText( ) );
				}
			}
		} );

		btnDesfazerDescricao.addActionListener( new ActionListener( )
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				areaDescricao.setText( personagemSelecionado.getDescricao( ) );
				areaDescricao.repaint( );
			}
		} );
	}

	public void finaliza( )
	{
		System.out.println( "------- Fim do combate -------" );
		System.out.println( "------------------------------" );
	}
}
