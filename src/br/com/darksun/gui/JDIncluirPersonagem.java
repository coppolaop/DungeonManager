package br.com.darksun.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;

class JDIncluirPersonagem extends JDialog implements ActionListener, PropertyChangeListener
{
	private Personagem personagemSelecionado = null;
	private JComboBox< Personagem > comboBox;
	private JOptionPane optionPane;

	private String btnString1 = "Adicionar Personagem";
	private String btnString2 = "Adicionar Réplica";

	/**
	 * Retorna o personagem selecionado
	 */
	public Personagem getPersonagemSelecionado( )
	{
		return personagemSelecionado;
	}

	// Cria da Dialog
	public JDIncluirPersonagem( JFPrincipal frame )
	{
		super( frame, true );

		setBounds( 50, 50, 400, 200 );

		setTitle( "Adicionar Persoangem no Combate" );

		comboBox = new JComboBox( listaPersonagens( ).toArray( ) );

		String msgString1 = "Personagem";

		Object[ ] array =
		{ msgString1, comboBox };

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
				personagemSelecionado = null;
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
				personagemSelecionado = ( Personagem ) comboBox.getSelectedItem( );
				clearAndHide( );

			} else if ( btnString2.equals( value ) )
			{
				personagemSelecionado = ( Personagem ) comboBox.getSelectedItem( );
				personagemSelecionado.setReplica( 1 );
				clearAndHide( );
			} else
			{
				personagemSelecionado = null;
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

	public List< Personagem > listaPersonagens( )
	{
		List< Personagem > lista = new ArrayList< Personagem >( );
		PersonagemController pc = new PersonagemController( );
		lista.addAll( pc.listarPJsAtivos( ) );
		lista.addAll( pc.listarPDMsAtivos( ) );
		return lista;
	}
}