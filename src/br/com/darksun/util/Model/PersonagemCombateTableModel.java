package br.com.darksun.util.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;

import br.com.darksun.entity.Personagem;

public class PersonagemCombateTableModel extends AbstractTableModel
{
	private static final int COL_NOME = 0;
	private static final int COL_CA = 1;
	private static final int COL_HPATUAL = 2;
	private static final int COL_HPTOTAL = 3;
	private List< Personagem > personagens;

	public PersonagemCombateTableModel( List< Personagem > personagens )
	{
		this.personagens = new ArrayList< Personagem >( personagens );
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

	public String getColumnName( int columnIndex )
	{
		String colunas[] =
		{ "Nome", "CA", "HP Atual", "HP Total" };
		return colunas[columnIndex];
	}

	@Override
	public Object getValueAt( int row, int column )
	{
		Personagem personagem = personagens.get( row );
		if ( column == COL_NOME )
			return personagem.getNome( );
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
		String value = ( (String) cell ).replaceAll( " ", "" );
		Pattern patOperacao = Pattern.compile( "[[+-]?[0-9]+]+" );
		Pattern patNumero = Pattern.compile( "[+-]?[0-9]+" );
		
		if( patNumero.matcher( value ).matches( ) ) {
			Personagem personagem = personagens.get( row );
			if ( column == COL_CA ) {
				Integer CA = personagem.getCa( );
				if( Integer.parseInt( value ) < CA )
					System.out.println( "A CA de " + personagem.getNome( ) + " diminuiu " + ( CA - Integer.parseInt(  value ) ) + " pontos" );
				else if( Integer.parseInt(  value ) > CA )
					System.out.println( "A CA de " + personagem.getNome( ) + " aumentou " + ( Integer.parseInt( value ) - CA ) + " pontos" );
				personagem.setCa( Integer.parseInt( value ) );
				atualizarArquivo( row, column, value );
			}
			else if ( column == COL_HPATUAL ) {
				Integer HP = personagem.getHpAtual( );
				if( Integer.parseInt( value ) < HP )
					if( personagens.get( 0 ).getNome( ).equals( personagem.getNome( ) ))
						System.out.println( "Algo fez com que " + personagem.getNome( ) + " perdesse " + ( HP - Integer.parseInt( value ) ) + " pontos de vida no seu turno" );
					else
						System.out.println( personagens.get( 0 ).getNome( ) + " causou " + ( HP - Integer.parseInt( value ) ) + " pontos de dano em " + personagem.getNome( ) );
				else if( Integer.parseInt( value ) > HP )
					if( personagens.get( 0 ).getNome( ).equals( personagem.getNome( ) ))
						System.out.println( personagem.getNome( ) + " se curou em " + ( Integer.parseInt( value ) - HP ) + " pontos de vida" );
					else
						System.out.println( personagens.get( 0 ).getNome( ) + " curou " + personagem.getNome( ) + " em " + ( Integer.parseInt( value ) - HP ) + " pontos de vida" );
				personagem.setHpAtual( Integer.parseInt( ( String ) value ));
				atualizarArquivo( row, column, value );
			}
			else if ( column == COL_HPTOTAL ) {
				Integer HP = personagem.getHpAtual( );
				if( Integer.parseInt( value ) < HP )
					System.out.println( "O HP máximo de " + personagem.getNome( ) + " diminuiu " + (HP - Integer.parseInt( value ) ) + " pontos" );
				else if( Integer.parseInt( value ) > HP )
					System.out.println( "O HP máximo de " + personagem.getNome( ) + " aumentou " + ( Integer.parseInt( value ) - HP ) + " pontos" );
				personagem.setHpMaximo( Integer.parseInt( value ) );
				atualizarArquivo( row, column, value );
			}
		}
		else if( patOperacao.matcher( value ).matches( ) ) {
			Boolean isNegativo = value.startsWith( "-" );
			Integer number = 0;
			List<String> list = new ArrayList<String>();
			if( value.startsWith( "+" ) || value.startsWith( "-" ) )
				value = value.substring( 1 );
			String[] numeros = value.split( "[+-]" );
			String[] operadores = value.split( "[0-9]+" );
			
			if(isNegativo)
				operadores[0] = "-";
			else
				operadores[0] = "+";
			
			for(int i = 0; i < operadores.length; i++) {
				list.add( operadores[i] );
				list.add( numeros[i] );
			}
			
			for(int i = 0; i < list.size( )/2; i++) {
				if(list.get( i*2 ).equals( "-" ) ) {
					number -= Integer.parseInt( list.get( (i*2)+1 ) );
				}else if(list.get( i*2 ).equals( "+" ) ) {
					number += Integer.parseInt( list.get( (i*2)+1 ) );
				}
			}
			
			Personagem personagem = personagens.get( row );
			if ( column == COL_CA ) {
				Integer CA = personagem.getCa( );
				if( number < CA )
					System.out.println( "A CA de " + personagem.getNome( ) + " diminuiu " + ( CA - number ) + " pontos" );
				else if( number > CA )
					System.out.println( "A CA de " + personagem.getNome( ) + " aumentou " + ( number - CA ) + " pontos" );
				personagem.setCa( number );
				atualizarArquivo( row, column, number );
			}
			else if ( column == COL_HPATUAL ) {
				Integer HP = personagem.getHpAtual( );
				if( number < HP )
					if( personagens.get( 0 ).getNome( ).equals( personagem.getNome( ) ))
						System.out.println( "Algo fez com que " + personagem.getNome( ) + " perdesse " + ( HP - number ) + " pontos de vida no seu turno" );
					else
						System.out.println( personagens.get( 0 ).getNome( ) + " causou " + ( HP - number ) + " pontos de dano em " + personagem.getNome( ) );
				else if( number > HP )
					if( personagens.get( 0 ).getNome( ).equals( personagem.getNome( ) ))
						System.out.println( personagem.getNome( ) + " se curou em " + ( number - HP ) + " pontos de vida" );
					else
						System.out.println( personagens.get( 0 ).getNome( ) + " curou " + personagem.getNome( ) + " em " + ( number - HP ) + " pontos de vida" );
				personagem.setHpAtual( number );
				atualizarArquivo( row, column, number );
			}
			else if ( column == COL_HPTOTAL ) {
				Integer HP = personagem.getHpAtual( );
				if( number < HP )
					System.out.println( "O HP máximo de " + personagem.getNome( ) + " diminuiu " + (HP - number ) + " pontos" );
				else if( number > HP )
					System.out.println( "O HP máximo de " + personagem.getNome( ) + " aumentou " + ( number - HP ) + " pontos" );
				personagem.setHpMaximo( number );
				atualizarArquivo( row, column, number );
			}
		}
	}

	public Class getColumnClass( int columnIndex )
	{
//		if ( columnIndex == COL_NOME )
			return String.class;
//		return Integer.class;
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
		Properties prop = new Properties( );
		OutputStream output = null;
		FileInputStream in = null;

		try
		{
			Personagem personagem = personagens.get( row );
			String nome = personagem.getNome( );
			
			if(personagem.getIsPJ( )) {
				in = new FileInputStream( "resources/pj/" + nome.replaceAll( " ", "_" ) + ".properties" );
				prop.load(in);
				output = new FileOutputStream( "resources/pj/" + nome.replaceAll( " ", "_" ) + ".properties" );
			}
			else
			{
				in = new FileInputStream( "resources/pdm/" + nome.replaceAll( " ", "_" ) + ".properties" );
				prop.load(in);
				output = new FileOutputStream( "resources/pdm/" + nome.replaceAll( " ", "_" ) + ".properties" );
			}
			if ( column == COL_CA ) prop.setProperty( "ca", value.toString( ) );
			else if ( column == COL_HPATUAL ) prop.setProperty( "hpAtual", value.toString( ) );
			else if ( column == COL_HPTOTAL ) prop.setProperty( "hpMaximo", value.toString( ) );
			prop.store(output, null);
			in.close();

			
		} catch ( IOException io )
		{
			io.printStackTrace( );
		} finally
		{
			if ( output != null )
			{
				try
				{
					output.close( );
				} catch ( IOException e )
				{
					e.printStackTrace( );
				}
			}

		}
	}
}
