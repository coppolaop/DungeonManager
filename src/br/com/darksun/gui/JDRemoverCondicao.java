package br.com.darksun.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import br.com.darksun.entity.Condicao;
import br.com.darksun.entity.Personagem;

class JDRemoverCondicao extends JDialog implements ActionListener, PropertyChangeListener
{
	private Condicao condicaoSelecionada = null;
	private JComboBox< Condicao > comboBox;
	private JOptionPane optionPane;

	private String btnString1 = "Remover Condição";

	/**
	 * Retorna o Efeito selecionado
	 */
	public Condicao getCondicaoSelecionada( )
	{
		return condicaoSelecionada;
	}

	// Cria da Dialog
	public JDRemoverCondicao( JFPrincipal frame, Personagem personagem )
	{
		super( frame, true );

		setBounds( 50, 50, 400, 150 );

		setTitle( "Remover Condição do Persoangem" );

		comboBox = new JComboBox( personagem.getCondicoes( ).toArray( ) );

		String msgString1 = "Condição";

		Object[ ] array1 =
		{ msgString1, comboBox };

		Object[ ] array =
		{ array1 };

		Object[ ] options =
		{ btnString1 };

		optionPane = new JOptionPane( array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options,
				options[0] );

		setContentPane( optionPane );

		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		addWindowListener( new WindowAdapter( )
		{
			public void windowClosing( WindowEvent we )
			{
				condicaoSelecionada = null;
				optionPane.setValue( new Integer( JOptionPane.CLOSED_OPTION ) );
			}
		} );

		addComponentListener( new ComponentAdapter( )
		{
			public void componentShown( ComponentEvent ce )
			{
				comboBox.requestFocusInWindow( );
			}
		} );

		optionPane.addPropertyChangeListener( this );
	}

	public void actionPerformed( ActionEvent e )
	{
		optionPane.setValue( btnString1 );
	}

	public void propertyChange( PropertyChangeEvent e )
	{
		String prop = e.getPropertyName( );

		if ( isVisible( ) && ( e.getSource( ) == optionPane )
				&& ( JOptionPane.VALUE_PROPERTY.equals( prop ) || JOptionPane.INPUT_VALUE_PROPERTY.equals( prop ) ) )
		{
			Object value = optionPane.getValue( );

			if ( value == JOptionPane.UNINITIALIZED_VALUE )
			{
				return;
			}

			optionPane.setValue( JOptionPane.UNINITIALIZED_VALUE );

			if ( btnString1.equals( value ) )
			{
				condicaoSelecionada = ( Condicao ) comboBox.getSelectedItem( );
				clearAndHide( );

			} else
			{
				condicaoSelecionada = null;
				clearAndHide( );
			}
		}
	}

	// Metodo limpa a dialog e esconde ela
	public void clearAndHide( )
	{
		comboBox.setSelectedItem( 0 );
		setVisible( false );
	}
}