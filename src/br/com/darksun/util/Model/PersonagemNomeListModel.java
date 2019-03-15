package br.com.darksun.util.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import br.com.darksun.entity.Personagem;

public class PersonagemNomeListModel extends AbstractListModel
{
	private List< Personagem > personagens;

	public PersonagemNomeListModel( )
	{
		personagens = new ArrayList< Personagem >( );
	}

	public PersonagemNomeListModel( List< Personagem > personagens )
	{
		this.personagens = new ArrayList< Personagem >( personagens );
	}

	@Override
	public int getSize( )
	{
		return personagens.size( );
	}

	@Override
	public Object getElementAt( int index )
	{
		return personagens.get( index );
	}

	public void copyInto( List< Personagem > persnagens )
	{
		this.personagens.addAll( personagens );
	}

	public boolean isEmpty( )
	{
		return personagens.isEmpty( );
	}

	public boolean contains( Object elem )
	{
		return personagens.contains( elem );
	}

	public int indexOf( Object elem )
	{
		return personagens.indexOf( elem );
	}

	public int lastIndexOf( Object elem )
	{
		return personagens.lastIndexOf( elem );
	}

	public String elementAt( int index )
	{
		return personagens.get( index ).getNome( );
	}

	public Personagem firstElement( )
	{
		return personagens.get( 0 );
	}

	public Personagem lastElement( )
	{
		return personagens.get( personagens.size( ) - 1 );
	}

	public void removeElementAt( int index )
	{
		personagens.remove( index );
		fireIntervalRemoved( this, index, index );
	}

	public void addElement( Object element )
	{
		int index = personagens.size( );
		personagens.add( ( Personagem ) element );
		fireIntervalAdded( this, index, index );
	}

	public boolean removeElement( Object obj )
	{
		int index = indexOf( obj );
		boolean rv = personagens.remove( obj );
		if ( index >= 0 )
		{
			fireIntervalRemoved( this, index, index );
		}
		return rv;
	}

	public void removeAllElements( )
	{
		int index1 = personagens.size( ) - 1;
		personagens.clear( );
		if ( index1 >= 0 )
		{
			fireIntervalRemoved( this, 0, index1 );
		}
	}

	public String toString( )
	{
		return personagens.toString( );
	}

	public String[ ] toArray( )
	{
		String[ ] lista = new String[ getSize( ) ];
		for ( int i = 0; i < getSize( ); i++ )
			lista[i] = personagens.get( 0 ).getNome( );
		return lista;
	}
	
	public List<Personagem> toList( ){
		return personagens;
	}

	public String get( int index )
	{
		return personagens.get( index ).getNome( );
	}

	public Personagem set( int index, Personagem element )
	{
		Personagem rv = personagens.get( index );
		personagens.set( index, element );
		fireContentsChanged( this, index, index );
		return rv;
	}

	public void add( int index, Personagem element )
	{
		personagens.add( index, element );
		fireIntervalAdded( this, index, index );
	}

	public Personagem remove( int index )
	{
		Personagem rv = personagens.get( index );
		personagens.remove( index );
		fireIntervalRemoved( this, index, index );
		return rv;
	}

	public void clear( )
	{
		int index = personagens.size( ) - 1;
		personagens.clear( );
		if ( index >= 0 )
		{
			fireIntervalRemoved( this, 0, index );
		}
	}

	public void removeRange( int fromIndex, int toIndex )
	{
		if ( fromIndex > toIndex )
		{
			throw new IllegalArgumentException( "fromIndex must be <= toIndex" );
		}
		for ( int i = toIndex; i >= fromIndex; i-- )
		{
			personagens.remove( i );
		}
		fireIntervalRemoved( this, fromIndex, toIndex );
	}

}
