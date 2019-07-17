package br.com.darksun.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;
import br.com.darksun.util.interfaces.Textable;

public class TabelaCombate extends AbstractTableModel
{
	private static final int COL_NOME = 0;
	private static final int COL_CA = 1;
	private static final int COL_HPATUAL = 2;
	private static final int COL_HPTOTAL = 3;
	private List< Personagem > personagens;
	private Textable log;
	private Textable labelRodadas;
	private Personagem ultimoDaRodada;
	private Integer rodada = 0;

	public TabelaCombate( List< Personagem > personagens, Textable log, Textable labelRodadas )
	{
		this.personagens = new ArrayList< Personagem >( personagens );
		this.log = log;
		this.labelRodadas = labelRodadas;
	}

	@Override
	public int getColumnCount( )
	{
		return 4;
	}

	@Override
	public int getRowCount( )
	{
		return personagens.size( );
	}

	public Personagem getUltimoDaRodada( )
	{
		return ultimoDaRodada;
	}

	public void setUltimoDaRodada( Personagem ultimoDaRodada )
	{
		this.ultimoDaRodada = ultimoDaRodada;
	}

	public Integer getRodada( )
	{
		return rodada;
	}

	public void setRodada( Integer rodada )
	{
		this.rodada = rodada;
	}
	
	public void avancaRodada( )
	{
		System.out.println( "-- A Rodada " + this.rodada + " acabou --" );
		this.rodada += 1;
		this.labelRodadas.setText( rodada.toString( ) );
	}

	public String getColumnName( int columnIndex )
	{
		String colunas[] =
		{ "Nome", "CA", "HP Atual", "HP Total" };
		return colunas[columnIndex];
	}

	public boolean contains( Personagem personagem )
	{
		for ( Personagem p : personagens )
		{
			if ( p.toString( ).equals( personagem.toString( ) ) )
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getValueAt( int row, int column )
	{
		Personagem personagem = personagens.get( row );
		if ( column == COL_NOME )
			return personagem.toString( );
		else if ( column == COL_CA )
			return personagem.getCa( );
		else if ( column == COL_HPATUAL )
			return personagem.getHpAtual( );
		else if ( column == COL_HPTOTAL )
			return personagem.getHpMaximo( );
		return null;
	}

	public void setValueAt( Object cell, int row, int column )
	{
		String value = ( ( String ) cell ).replaceAll( " ", "" );
		Pattern patOperacao = Pattern.compile( "[[+-]?[0-9]+]+" );
		Pattern patNumero = Pattern.compile( "[+-]?[0-9]+" );

		if ( patNumero.matcher( value ).matches( ) )
		{
			Personagem personagem = personagens.get( row );
			if ( column == COL_CA )
			{
				Integer CA = personagem.getCa( );
				if ( Integer.parseInt( value ) < CA )
				{
					System.out.println( "A CA de " + personagem.toString( ) + " diminuiu "
							+ ( CA - Integer.parseInt( value ) ) + " pontos" );
					log.setText( "A CA de " + personagem.toString( ) + " diminuiu " + ( CA - Integer.parseInt( value ) )
							+ " pontos" );
				} else if ( Integer.parseInt( value ) > CA )
				{
					System.out.println( "A CA de " + personagem.toString( ) + " aumentou "
							+ ( Integer.parseInt( value ) - CA ) + " pontos" );
					log.setText( "A CA de " + personagem.toString( ) + " aumentou " + ( Integer.parseInt( value ) - CA )
							+ " pontos" );
				}
				personagem.setCa( Integer.parseInt( value ) );
				if ( personagem.getReplica( ) == 0 )
					atualizarArquivo( row, column, value );
			} else if ( column == COL_HPATUAL )
			{
				Integer HP = personagem.getHpAtual( );
				if ( Integer.parseInt( value ) < HP )
				{
					if ( personagens.get( 0 ).toString( ).equals( personagem.toString( ) ) )
					{
						System.out.println( "Algo fez com que " + personagem.toString( ) + " perdesse "
								+ ( HP - Integer.parseInt( value ) ) + " pontos de vida no seu turno" );
						log.setText( "Algo fez com que " + personagem.toString( ) + " perdesse "
								+ ( HP - Integer.parseInt( value ) ) + " pontos de vida no seu turno" );
					} else
					{
						System.out.println( personagens.get( 0 ).toString( ) + " causou "
								+ ( HP - Integer.parseInt( value ) ) + " pontos de dano em " + personagem.toString( ) );
						log.setText( personagens.get( 0 ).toString( ) + " causou " + ( HP - Integer.parseInt( value ) )
								+ " pontos de dano em " + personagem.toString( ) );
					}
				} else if ( Integer.parseInt( value ) > HP )
				{
					if ( personagens.get( 0 ).toString( ).equals( personagem.toString( ) ) )
					{
						System.out.println( personagem.toString( ) + " se curou em "
								+ ( Integer.parseInt( value ) - HP ) + " pontos de vida" );
						log.setText( personagem.toString( ) + " se curou em " + ( Integer.parseInt( value ) - HP )
								+ " pontos de vida" );
					} else
					{
						System.out.println( personagens.get( 0 ).toString( ) + " curou " + personagem.toString( )
								+ " em " + ( Integer.parseInt( value ) - HP ) + " pontos de vida" );
						log.setText( personagens.get( 0 ).toString( ) + " curou " + personagem.toString( ) + " em "
								+ ( Integer.parseInt( value ) - HP ) + " pontos de vida" );
					}
				}
				personagem.setHpAtual( Integer.parseInt( ( String ) value ) );
				if ( personagem.getReplica( ) == 0 )
					atualizarArquivo( row, column, value );
			} else if ( column == COL_HPTOTAL )
			{
				Integer HP = personagem.getHpMaximo( );
				if ( Integer.parseInt( value ) < HP )
				{
					System.out.println( "O HP máximo de " + personagem.toString( ) + " diminuiu "
							+ ( HP - Integer.parseInt( value ) ) + " pontos" );
					log.setText( "O HP máximo de " + personagem.toString( ) + " diminuiu "
							+ ( HP - Integer.parseInt( value ) ) + " pontos" );
				} else if ( Integer.parseInt( value ) > HP )
				{
					System.out.println( "O HP máximo de " + personagem.toString( ) + " aumentou "
							+ ( Integer.parseInt( value ) - HP ) + " pontos" );
					log.setText( "O HP máximo de " + personagem.toString( ) + " aumentou "
							+ ( Integer.parseInt( value ) - HP ) + " pontos" );
				}
				personagem.setHpMaximo( Integer.parseInt( value ) );
				if ( personagem.getReplica( ) == 0 )
					atualizarArquivo( row, column, value );
			}
		} else if ( patOperacao.matcher( value ).matches( ) )
		{
			Boolean isNegativo = value.startsWith( "-" );
			Integer number = 0;
			List< String > list = new ArrayList< String >( );
			if ( value.startsWith( "+" ) || value.startsWith( "-" ) )
				value = value.substring( 1 );
			String[ ] numeros = value.split( "[+-]" );
			String[ ] operadores = value.split( "[0-9]+" );

			if ( isNegativo )
				operadores[0] = "-";
			else
				operadores[0] = "+";

			for ( int i = 0; i < operadores.length; i++ )
			{
				list.add( operadores[i] );
				list.add( numeros[i] );
			}

			for ( int i = 0; i < list.size( ) / 2; i++ )
			{
				if ( list.get( i * 2 ).equals( "-" ) )
				{
					number -= Integer.parseInt( list.get( ( i * 2 ) + 1 ) );
				} else if ( list.get( i * 2 ).equals( "+" ) )
				{
					number += Integer.parseInt( list.get( ( i * 2 ) + 1 ) );
				}
			}

			Personagem personagem = personagens.get( row );

			if ( column == COL_CA )
			{
				Integer CA = personagem.getCa( );
				if ( number < CA )
				{
					System.out.println(
							"A CA de " + personagem.toString( ) + " diminuiu " + ( CA - number ) + " pontos" );
					log.setText( "A CA de " + personagem.toString( ) + " diminuiu " + ( CA - number ) + " pontos" );
				} else if ( number > CA )
				{
					System.out.println(
							"A CA de " + personagem.toString( ) + " aumentou " + ( number - CA ) + " pontos" );
					log.setText( "A CA de " + personagem.toString( ) + " aumentou " + ( number - CA ) + " pontos" );
				}
				personagem.setCa( number );
				if ( personagem.getReplica( ) == 0 )
					atualizarArquivo( row, column, number );
			} else if ( column == COL_HPATUAL )
			{
				Integer HP = personagem.getHpAtual( );
				if ( number < HP )
				{
					if ( personagens.get( 0 ).toString( ).equals( personagem.toString( ) ) )
					{
						System.out.println( "Algo fez com que " + personagem.toString( ) + " perdesse "
								+ ( HP - number ) + " pontos de vida no seu turno" );
						log.setText( "Algo fez com que " + personagem.toString( ) + " perdesse " + ( HP - number )
								+ " pontos de vida no seu turno" );
					} else
					{
						System.out.println( personagens.get( 0 ).toString( ) + " causou " + ( HP - number )
								+ " pontos de dano em " + personagem.toString( ) );
						log.setText( personagens.get( 0 ).toString( ) + " causou " + ( HP - number )
								+ " pontos de dano em " + personagem.toString( ) );
					}
				} else if ( number > HP )
				{
					if ( personagens.get( 0 ).toString( ).equals( personagem.toString( ) ) )
					{
						System.out.println(
								personagem.toString( ) + " se curou em " + ( number - HP ) + " pontos de vida" );
						log.setText( personagem.toString( ) + " se curou em " + ( number - HP ) + " pontos de vida" );
					} else
					{
						System.out.println( personagens.get( 0 ).toString( ) + " curou " + personagem.toString( )
								+ " em " + ( number - HP ) + " pontos de vida" );
						log.setText( personagens.get( 0 ).toString( ) + " curou " + personagem.toString( ) + " em "
								+ ( number - HP ) + " pontos de vida" );
					}
				}
				personagem.setHpAtual( number );
				if ( personagem.getReplica( ) == 0 )
					atualizarArquivo( row, column, number );
			} else if ( column == COL_HPTOTAL )
			{
				Integer HP = personagem.getHpMaximo( );
				if ( number < HP )
				{
					System.out.println(
							"O HP máximo de " + personagem.toString( ) + " diminuiu " + ( HP - number ) + " pontos" );
					log.setText(
							"O HP máximo de " + personagem.toString( ) + " diminuiu " + ( HP - number ) + " pontos" );
				} else if ( number > HP )
				{
					System.out.println(
							"O HP máximo de " + personagem.toString( ) + " aumentou " + ( number - HP ) + " pontos" );
					log.setText(
							"O HP máximo de " + personagem.toString( ) + " aumentou " + ( number - HP ) + " pontos" );
				}
				personagem.setHpMaximo( number );
				if ( personagem.getReplica( ) == 0 )
					atualizarArquivo( row, column, number );
			}
		}
	}

	public Class getColumnClass( int columnIndex )
	{
		return String.class;
	}

	public boolean isCellEditable( int row, int column )
	{
		if ( column == COL_NOME )
			return false;
		return true;
	}

	public List< Personagem > getPersonagens( )
	{
		return Collections.unmodifiableList( personagens );
	}

	public Personagem getPersonagem( int row )
	{
		return personagens.get( row );
	}

	public Integer getMaiorNome( )
	{
		int tamanho = 0;
		for ( Personagem personagem : personagens )
		{
			if ( personagem.toString( ).length( ) * 12 > tamanho )
			{
				tamanho = personagem.toString( ).length( ) * 12;
			}
		}
		return tamanho;
	}

	public void adicionar( Personagem personagem )
	{
		personagens.add( personagem );
		fireTableRowsInserted( personagens.size( ), personagens.size( ) );
	}

	public void remover( Personagem personagem )
	{
		int index = personagens.indexOf( personagem );
		if ( index == -1 )
			return;
		personagens.remove( index );
		fireTableRowsDeleted( index, index );
	}

	public void trocar( int row1, int row2 )
	{
		Personagem p1 = personagens.get( row1 );
		Personagem p2 = personagens.get( row2 );

		personagens.remove( p1 );
		personagens.add( row2, p1 );

		personagens.remove( p2 );
		personagens.add( row1, p2 );
	}

	public void atualizarArquivo( int row, int column, Object value )
	{
		Personagem personagem = personagens.get( row );
		String campo = null;

		if ( column == COL_CA )
			campo = "ca";
		else if ( column == COL_HPATUAL )
			campo = "hpAtual";
		else if ( column == COL_HPTOTAL )
			campo = "hpMaximo";

		new PersonagemController( ).atualizarArquivo( personagem.getFilePath( ), campo, value.toString( ) );
	}
}
