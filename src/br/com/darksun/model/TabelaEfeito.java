package br.com.darksun.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.darksun.entity.Efeito;

public class TabelaEfeito extends AbstractTableModel
{
	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;
	private static final int COL_TIPO = 2;
	private static final int COL_ATRIBUTO = 3;
	private static final int COL_CONTINUO = 4;
	private List< Efeito > efeitos;

	public TabelaEfeito( List< Efeito > efeitos )
	{
		this.efeitos = new ArrayList< Efeito >( efeitos );
	}

	@Override
	public int getColumnCount( )
	{
		return 5;
	}

	@Override
	public int getRowCount( )
	{
		return efeitos.size( );
	}

	public String getColumnName( int columnIndex )
	{
		String colunas[] =
		{ "ID", "Nome", "Tipo", "Atributo", "Continuo" };
		return colunas[columnIndex];
	}

	@Override
	public Object getValueAt( int row, int column )
	{
		Efeito efeito = efeitos.get( row );
		if ( column == COL_ID )
		{
			return efeito.getIdEfeito( );
		} else if ( column == COL_NOME )
		{
			return efeito.getNome( );
		} else if ( column == COL_TIPO )
		{
			if ( efeito.getIsPositivo( ) )
			{
				return "Positivo";
			} else
			{
				return "Negativo";
			}
		} else if ( column == COL_ATRIBUTO )
		{
			return efeito.getAtributoAfetado( );
		} else if ( column == COL_CONTINUO )
		{
			if ( efeito.getIsContinuo( ) )
			{
				return "Sim";
			} else
			{
				return "Não";
			}
		}
		return null;
	}

	public void setValueAt( Object value, int row, int column )
	{

	}

	public Class getColumnClass( int columnIndex )
	{
		if ( columnIndex == COL_ID )
			return Integer.class;
		return String.class;
	}

	public boolean isCellEditable( int row, int column )
	{
		return false;
	}

	public List< Efeito > getEfeitos( )
	{
		return Collections.unmodifiableList( efeitos );
	}

	public Efeito getEfeito( int row )
	{
		return efeitos.get( row );
	}

	public void adicionar( Efeito efeito )
	{
		efeitos.add( efeito );
		fireTableRowsInserted( efeitos.size( ), efeitos.size( ) );
	}

	public void remover( Efeito efeito )
	{
		int index = efeitos.indexOf( efeito );
		if ( index == -1 )
			return;
		efeitos.remove( index );
		fireTableRowsDeleted( index, index );
	}
}
