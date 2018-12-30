package br.com.darksun.util.Model;

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

public class PersonagemListaTableModel extends AbstractTableModel
{
	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;
	private static final int COL_CLASSE = 2;
	private List< Personagem > personagens;

	public PersonagemListaTableModel( List< Personagem > personagens )
	{
		this.personagens = new ArrayList< Personagem >( personagens );
	}

	@Override
	public int getColumnCount( )
	{
		return 3;
	}

	@Override
	public int getRowCount( )
	{
		return personagens.size( );
	}

	public String getColumnName( int columnIndex )
	{
		String colunas[] =
		{ "ID", "Nome", "Classe" };
		return colunas[columnIndex];
	}

	@Override
	public Object getValueAt( int row, int column )
	{
		Personagem personagem = personagens.get( row );
		if ( column == COL_ID )
			return personagem.getIdPersonagem( );
		else if ( column == COL_NOME )
			return personagem.getNome( );
		else if ( column == COL_CLASSE )
			return personagem.getClasse( );
		
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
}
