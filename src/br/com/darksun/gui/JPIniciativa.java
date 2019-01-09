package br.com.darksun.gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.com.darksun.entity.Personagem;
import br.com.darksun.util.LogStream;

public class JPIniciativa extends JPPadrao
{
	public JPIniciativa( JFPrincipal frame, List< Personagem > PJs, List< Personagem > PDMs )
	{
		montaTelaIniciativa( frame, PJs, PDMs );
	}

	public void montaTelaIniciativa( JFPrincipal frame, List< Personagem > PJs, List< Personagem > PDMs )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );

		if ( !frame.isInBeta( ) )
			try
			{
				LogStream log = new LogStream( );
				log.criaArquivoLog( );
			} catch ( Exception ex )
			{
				ex.printStackTrace( );
			}

		System.out.println( "-------Combate Iniciado-------" );

		for ( Personagem personagem : PJs )
			System.out.println( personagem.toString( ) + " - " + personagem.getHpAtual( ) + " pontos de vida" );

		System.out.println( "      ----- VS -----" );

		for ( Personagem personagem : PDMs )
			System.out.println( personagem.toString( ) + " - " + personagem.getHpAtual( ) + " pontos de vida" );

		System.out.println( "------------------------------" );

		JLabel labelImg = new JLabel( "" );
		labelImg.setBounds( ( width - 200 ) / 2, 50, 200, 200 );
		ImageIcon logoApp = new ImageIcon( getClass( ).getClassLoader( ).getResource( frame.getIconPath( ) ) );
		logoApp = new ImageIcon( logoApp.getImage( ).getScaledInstance( labelImg.getWidth( ), labelImg.getHeight( ),
				logoApp.getImage( ).SCALE_DEFAULT ) );
		labelImg.setIcon( logoApp );

		JButton btnRolagemAutomatica = new JButton( "Rolagem Automática" );
		btnRolagemAutomatica.setBounds( 50, height / 2 - 30 / 2, 200, 30 );

		JButton btnPDMAutomatico = new JButton( "Rolagem Automática de PDM" );
		btnPDMAutomatico.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2, 200, 30 );

		JButton btnRolagemManual = new JButton( "Rolagem Manual" );
		btnRolagemManual.setBounds( width - 250, height / 2 - 30 / 2, 200, 30 );

		add( labelImg );
		add( btnRolagemAutomatica );
		add( btnPDMAutomatico );
		add( btnRolagemManual );

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
				btnRolagemAutomatica.setBounds( 50, height / 2 - 30 / 2, btnRolagemAutomatica.getBounds( ).width,
						btnRolagemAutomatica.getBounds( ).height );
				btnPDMAutomatico.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2,
						btnPDMAutomatico.getBounds( ).width, btnPDMAutomatico.getBounds( ).height );
				btnRolagemManual.setBounds( width - 250, height / 2 - 30 / 2, btnRolagemManual.getBounds( ).width,
						btnRolagemManual.getBounds( ).height );
			}
		} );

		btnRolagemAutomatica.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Random random = new Random( );

				for ( Personagem personagem : PJs )
					personagem.setIniciativa( personagem.getBonusIniciativa( ) + random.nextInt( 20 ) + 1 );

				for ( Personagem personagem : PDMs )
					personagem.setIniciativa( personagem.getBonusIniciativa( ) + random.nextInt( 20 ) + 1 );

				frame.remove( JPIniciativa.this );
				frame.setTela( new JPCombate( frame, PJs, PDMs ) );
			}

		} );

		btnPDMAutomatico.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				for ( Personagem personagem : PJs )
				{

					JDInicitivaManual im = new JDInicitivaManual( frame, personagem );
					im.setVisible( true );
					personagem.setIniciativa( im.getValidatedText( ) );

				}

				for ( Personagem personagem : PDMs )
					personagem.setIniciativa( personagem.getBonusIniciativa( ) + new Random( ).nextInt( 20 ) + 1 );

				frame.remove( JPIniciativa.this );
				frame.setTela( new JPCombate( frame, PJs, PDMs ) );
			}
		} );

		btnRolagemManual.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				for ( Personagem personagem : PJs )
				{
					JDInicitivaManual im = new JDInicitivaManual( frame, personagem );
					im.setVisible( true );
					personagem.setIniciativa( im.getValidatedText( ) );
				}

				for ( Personagem personagem : PDMs )
				{
					JDInicitivaManual im = new JDInicitivaManual( frame, personagem );
					im.setVisible( true );
					personagem.setIniciativa( im.getValidatedText( ) );
				}

				frame.remove( JPIniciativa.this );
				frame.setTela( new JPCombate( frame, PJs, PDMs ) );
			}

		} );
	}
}