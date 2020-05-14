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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import br.com.darksun.control.EfeitoController;
import br.com.darksun.entity.Efeito;

class JDIncluirCondicao extends JDialog implements ActionListener, PropertyChangeListener
{
	private Efeito efeitoSelecionado = null;
	private Boolean hasDuracao = true;
	private Integer duracaoSelecionada = null;
	private Integer valorSelecionado = null;
	private JComboBox< Efeito > comboBox;
	private JSpinner spinnerDuracao;
	private JSpinner spinnerValor;
	private JOptionPane optionPane;

	private String btnString1 = "Adicionar Condição";

	/**
	 * Retorna o Efeito selecionado
	 */
	public Efeito getEfeitoSelecionado( )
	{
		return efeitoSelecionado;
	}

	/**
	 * Retorna o número selecionado para Duração
	 */
	public Integer getDuracaoSelecionada( )
	{
		return duracaoSelecionada;
	}

	/**
	 * Retorna o número selecionado para Valor
	 */
	public Integer getValorSelecionado( )
	{
		return valorSelecionado;
	}

	// Cria da Dialog
	public JDIncluirCondicao( JFPrincipal frame )
	{
		super( frame, true );

		setBounds( 50, 50, 400, 250 );

		setTitle( "Adicionar Condição ao Persoangem" );

		comboBox = new JComboBox( listaEfeitos( ).toArray( ) );

		JCheckBox checkBox = new JCheckBox( "Sem duração" );
		SpinnerModel numberModel1 = new SpinnerNumberModel( 1, 1, 100, 1 );
		SpinnerModel numberModel2 = new SpinnerNumberModel( 0, 0, 100, 1 );
		spinnerDuracao = new JSpinner( numberModel1 );
		spinnerValor = new JSpinner( numberModel2 );

		String msgString1 = "Efeito";
		String msgString2 = "Duração";
		String msgString3 = "Valor";

		Object[ ] array1 =
		{ msgString1, comboBox };

		Object[ ] array2 =
		{ checkBox };

		Object[ ] array3 =
		{ msgString2, spinnerDuracao };

		Object[ ] array4 =
		{ msgString3, spinnerValor };

		Object[ ] array =
		{ array1, array2, array3, array4 };

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
				efeitoSelecionado = null;
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

		checkBox.addActionListener( new ActionListener( )
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				if ( checkBox.isSelected( ) )
				{
					hasDuracao = false;

				} else
				{
					hasDuracao = true;
				}
				spinnerDuracao.setEnabled( hasDuracao );
			}
		} );
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
				efeitoSelecionado = ( Efeito ) comboBox.getSelectedItem( );
				if ( hasDuracao )
				{
					duracaoSelecionada = ( Integer ) spinnerDuracao.getValue( );
				} else
				{
					duracaoSelecionada = 0;
				}
				valorSelecionado = ( Integer ) spinnerValor.getValue( );
				clearAndHide( );

			} else
			{
				efeitoSelecionado = null;
				duracaoSelecionada = 0;
				valorSelecionado = 0;
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

	public List< Efeito > listaEfeitos( )
	{
		List< Efeito > lista = new ArrayList< Efeito >( );
		EfeitoController ec = new EfeitoController( );
		lista.addAll( ec.listarEfeitos( ) );
		return lista;
	}
}