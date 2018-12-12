package br.com.darksun.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;

import br.com.darksun.entity.Personagem;

public class PersonagemTableModel extends AbstractTableModel
{
	private static final int COL_NOME = 0;
	private static final int COL_CA = 1;
	private static final int COL_HPATUAL = 2;
	private static final int COL_HPTOTAL = 3;
	private List< Personagem > personagens;

	public PersonagemTableModel( List< Personagem > personagens )
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

	public void setValueAt( Object value, int row, int column )
	{
		Personagem personagem = personagens.get( row );
		if ( column == COL_CA ) {
			personagem.setCa( ( Integer ) value );
			atualizarArquivo( row, column, value );
		}
		else if ( column == COL_HPATUAL ) {
			personagem.setHpAtual( ( Integer ) value );
			atualizarArquivo( row, column, value );
		}
		else if ( column == COL_HPTOTAL ) {
			personagem.setHpMaximo( ( Integer ) value );
			atualizarArquivo( row, column, value );
		}
	}

	public Class getColumnClass( int columnIndex )
	{
		if ( columnIndex == COL_NOME )
			return String.class;
		return Integer.class;
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
				in = new FileInputStream( "resources/pj/" + nome + ".properties" );
				prop.load(in);
				output = new FileOutputStream( "resources/pj/" + nome + ".properties" );
			}
			else
			{
				in = new FileInputStream( "resources/pdm/" + nome + ".properties" );
				prop.load(in);
				output = new FileOutputStream( "resources/pdm/" + nome + ".properties" );
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
