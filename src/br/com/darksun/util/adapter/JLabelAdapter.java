package br.com.darksun.util.adapter;

import javax.swing.JLabel;

import br.com.darksun.util.interfaces.Textable;

public class JLabelAdapter extends JLabel implements Textable
{
	public JLabelAdapter( String text ) {
		this.setText( text );
	}
}
