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
		Image logoApp = Toolkit.getDefaultToolkit( ).getImage( frame.getIconPath( ) );
		labelImg.setIcon(new ImageIcon(logoApp.getScaledInstance(labelImg.getWidth(),labelImg.getHeight(), logoApp.SCALE_DEFAULT)));

		JLabel labelSystem = new JLabel( "Dungeon Manager" );
		labelSystem.setBounds( 0, height / 4 + 50, width, 70 );
		labelSystem.setForeground( Color.CYAN );
		labelSystem
				.setFont( new Font( labelSystem.getFont( ).getFontName( ), labelSystem.getFont( ).getStyle( ), 50 ) );
		labelSystem.setHorizontalAlignment( SwingConstants.CENTER );

		JLabel labelCreator = new JLabel( "Desenvolvedor: Marcos Alves Gonçalves Junior (Coppola)" );
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

		add( labelSystem );
		add( labelCreator );
		add( labelGithub );
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
			}
		});
	}
}
