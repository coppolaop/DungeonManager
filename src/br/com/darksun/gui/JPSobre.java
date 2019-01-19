package br.com.darksun.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JPSobre extends JPPadrao
{
	public JPSobre( JFPrincipal frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );

		JLabel labelImg = new JLabel( "" );
		Integer imgSize = 200;
		labelImg.setBounds( ( width - imgSize ) / 2, 50, imgSize, imgSize );
		ImageIcon logoApp = new ImageIcon( getClass( ).getClassLoader( ).getResource( frame.getIconPath( ) ) );
		logoApp = new ImageIcon( logoApp.getImage( ).getScaledInstance( labelImg.getWidth( ), labelImg.getHeight( ),
				logoApp.getImage( ).SCALE_DEFAULT ) );
		labelImg.setIcon( logoApp );

		JLabel labelSystem = new JLabel( "Dungeon Manager" );
		labelSystem.setBounds( 0, height / 4 + 50, width, 70 );
		labelSystem.setForeground( Color.CYAN );
		labelSystem
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 50 ) );
		labelSystem.setHorizontalAlignment( SwingConstants.CENTER );

		JLabel labelCreator = new JLabel( "Criador: Marcos \"Coppola\" Gonçalves (coppolaop)" );
		labelCreator.setBounds( 0, height / 4 + 100, width, 70 );
		labelCreator.setForeground( Color.CYAN );
		labelCreator
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 30 ) );
		labelCreator.setHorizontalAlignment( SwingConstants.CENTER );

		JLabel labelGithub = new JLabel( "Repositório Online: github.com/coppolaop/DungeonManager" );
		labelGithub.setBounds( 0, height / 4 + 150, width, 70 );
		labelGithub.setForeground( Color.CYAN );
		labelGithub
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 30 ) );
		labelGithub.setHorizontalAlignment( SwingConstants.CENTER );

		JLabel labelDeveloper1 = new JLabel( "Desenvolvedores:" );
		labelDeveloper1.setBounds( 0, height / 4 + 250, width, 70 );
		labelDeveloper1.setForeground( Color.CYAN );
		labelDeveloper1
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 30 ) );
		labelDeveloper1.setHorizontalAlignment( SwingConstants.CENTER );

		JLabel labelDeveloper2 = new JLabel( "Marcos \"Coppola\" Gonçalves (coppolaop)" );
		labelDeveloper2.setBounds( 0, height / 4 + 300, width, 70 );
		labelDeveloper2.setForeground( Color.CYAN );
		labelDeveloper2
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 30 ) );
		labelDeveloper2.setHorizontalAlignment( SwingConstants.CENTER );

		JLabel labelDeveloper3 = new JLabel( "Vitor Abel (vitor-abel)" );
		labelDeveloper3.setBounds( 0, height / 4 + 350, width, 70 );
		labelDeveloper3.setForeground( Color.CYAN );
		labelDeveloper3
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 30 ) );
		labelDeveloper3.setHorizontalAlignment( SwingConstants.CENTER );

		JLabel labelDeveloper4 = new JLabel( "Igor Huayck (flaigor)" );
		labelDeveloper4.setBounds( 0, height / 4 + 400, width, 70 );
		labelDeveloper4.setForeground( Color.CYAN );
		labelDeveloper4
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 30 ) );
		labelDeveloper4.setHorizontalAlignment( SwingConstants.CENTER );

		add( labelSystem );
		add( labelCreator );
		add( labelGithub );
		add( labelDeveloper1 );
		add( labelDeveloper2 );
		add( labelDeveloper3 );
		add( labelDeveloper4 );
		add( labelImg );

		frame.repaint( );

		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;
				setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );

				labelImg.setBounds( ( width - imgSize ) / 2, 50, imgSize, imgSize );
				labelSystem.setBounds( 0, height / 4 + 50, width, 70 );
				labelCreator.setBounds( 0, height / 4 + 100, width, 70 );
				labelGithub.setBounds( 0, height / 4 + 150, width, 70 );
				labelDeveloper1.setBounds( 0, height / 4 + 250, width, 70 );
				labelDeveloper2.setBounds( 0, height / 4 + 300, width, 70 );
				labelDeveloper3.setBounds( 0, height / 4 + 350, width, 70 );
				labelDeveloper4.setBounds( 0, height / 4 + 400, width, 70 );
			}
		} );
	}
}
