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

	public List< String > ordenaJList( JComboBox combobox )
	{
		List< String > array = new ArrayList< String >( );
		int size = combobox.getItemCount( );
		for ( int i = 0; i < size; i++ )
			array.add( combobox.getItemAt( i ).toString( ) );
		Collections.sort( array );

		return array;
	}

	public void limpaTela( )
	{
		// frame.removeAll( );
		revalidate( );
		repaint( );
		setLayout( null );
		// frame.getTela( ).setBounds( 0, 0, this.width, this.height );
		setBackground( new Color( 100, 100, 100 ) );
		// frame.add( this );
		// this.setVisible( true );
		// frame.setTela( this );
		// frame.getTela( ).setVisible( true );
	}
}
