package br.com.darksun.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public abstract class JPPadrao extends JPanel
{
	protected Integer width = 1500;
	protected Integer height = 750;

	public void limpaTela( )
	{
		revalidate( );
		repaint( );
		setLayout( null );
		setBackground( new Color( 100, 100, 100 ) );
	}
	
	public void finaliza( )
	{
		
	}
}
