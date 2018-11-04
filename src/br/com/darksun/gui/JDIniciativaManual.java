package br.com.darksun.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//property change stuff
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.darksun.entity.Personagem;

class JDInicitivaManual extends JDialog implements ActionListener, PropertyChangeListener
{
	private Integer typedText = null;
	private JTextField textField;
	private JOptionPane optionPane;
	private Personagem personagem;

	private String btnString1 = "OK";
	private String btnString2 = "Rolagem automática";

	/**
	 * Retorna o valor inserido ou rola automaticamente se o valor for invalido
	 */
	public Integer getValidatedText( )
	{
		return typedText;
	}

	// Cria da Dialog
	public JDInicitivaManual( JFPrincipal frame, Personagem personagem )
	{
		super( frame, true );

		this.personagem = personagem;

		setBounds( 50, 50, 400, 200 );

		setTitle( personagem.getNome( ) );

		textField = new JTextField( 10 );

		String msgString1 = "Iniciativa";

		Object[ ] array =
		{ msgString1, textField };

		Object[ ] options =
		{ btnString1, btnString2 };

		optionPane = new JOptionPane( array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options,
				options[0] );

		setContentPane( optionPane );

		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		addWindowListener( new WindowAdapter( )
		{
			public void windowClosing( WindowEvent we )
			{
				typedText = new Random( ).nextInt( 20 ) + 1 + personagem.getBonusIniciativa( );
				optionPane.setValue( new Integer( JOptionPane.CLOSED_OPTION ) );
			}
		} );

		addComponentListener( new ComponentAdapter( )
		{
			public void componentShown( ComponentEvent ce )
			{
				textField.requestFocusInWindow( );
			}
		} );

		textField.addActionListener( this );

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
				if ( textField.getText( ).matches( "\\d+" ) )
				{
					typedText = Integer.parseInt( textField.getText( ) );
					clearAndHide( );
				} else
				{
					textField.selectAll( );
					JOptionPane.showMessageDialog( JDInicitivaManual.this,
							"Desculpe, \"" + textField.getText( ) + "\" " + "não é um valor válido.\n"
									+ "Por favor, digite um número inteiro. ",
							"Tente novamente", JOptionPane.ERROR_MESSAGE );
					typedText = null;
					textField.requestFocusInWindow( );
				}
			} else
			{
				// Usuario optou por utilizar iniciativa automática para esse personagem
				typedText = new Random( ).nextInt( 20 ) + 1 + personagem.getBonusIniciativa( );
				clearAndHide( );
			}
		}
	}

	// Metodo limpa a dialog e esconde ela
	public void clearAndHide( )
	{
		textField.setText( null );
		setVisible( false );
	}
}