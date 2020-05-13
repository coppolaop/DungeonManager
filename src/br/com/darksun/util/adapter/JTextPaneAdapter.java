package br.com.darksun.util.adapter;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import br.com.darksun.util.interfaces.Textable;

public class JTextPaneAdapter extends JTextPane implements Textable
{
	public JTextPaneAdapter( String text )
	{
		this.setText( text );
	}

	public void append( String text )
	{
		append( text, "regular" );
	}

	public void append( String text, String style )
	{
		StyledDocument doc = this.getStyledDocument( );
		addStylesToDocument( doc );
		try
		{
			doc.insertString( doc.getLength( ), text, doc.getStyle( style ) );
		} catch ( BadLocationException ex )
		{
			ex.printStackTrace( );
		}
	}

	protected void addStylesToDocument( StyledDocument doc )
	{
		Style def = StyleContext.getDefaultStyleContext( ).getStyle( StyleContext.DEFAULT_STYLE );

		Style regular = doc.addStyle( "regular", def );

		Style s = doc.addStyle( "destaque", regular );
		StyleConstants.setBold( s, true );
		StyleConstants.setForeground( s, Color.RED );

		s = doc.addStyle( "normal", regular );
		StyleConstants.setBold( s, false );
	}
}
