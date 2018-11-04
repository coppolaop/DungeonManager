package br.com.darksun.util;

import java.util.Comparator;

import br.com.darksun.entity.Personagem;

public class IniciativaComparator implements Comparator<Personagem>
{
	@Override
	public int compare( Personagem p1, Personagem p2 )
	{
		if(p1.getIniciativa( ) == p2.getIniciativa( ))
			return p2.getBonusIniciativa( ).compareTo( p1.getBonusIniciativa( ) );
		return p2.getIniciativa( ).compareTo( p1.getIniciativa( ) );
	}
}
