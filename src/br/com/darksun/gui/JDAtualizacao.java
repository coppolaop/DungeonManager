package br.com.darksun.gui;

import java.awt.Desktop;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class JDAtualizacao extends JDialog
{
	public JDAtualizacao( JFPrincipal frame )
	{
		super( frame, "Nova Atualização", true );
		final JOptionPane optionPane = new JOptionPane( "Há uma nova atualização da aplicação disponível.\n"
				+ "Deseja acessar o site de download?", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION );

		this.setContentPane( optionPane );
		this.setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE );
		optionPane.addPropertyChangeListener( new PropertyChangeListener( )
		{
			public void propertyChange( PropertyChangeEvent e )
			{
				String prop = e.getPropertyName( );

				if ( isVisible( ) && ( e.getSource( ) == optionPane )
						&& ( JOptionPane.VALUE_PROPERTY.equals( prop ) ) )
				{
					setVisible( false );
				}
			}
		} );
		this.pack( );
		this.setLocationRelativeTo( frame );
		this.setVisible( true );

		int value = ( ( Integer ) optionPane.getValue( ) ).intValue( );
		if ( value == JOptionPane.YES_OPTION )
		{
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
		        try {
		            desktop.browse(new URI("https://github.com/coppolaop/DungeonManager/releases/latest"));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
}
