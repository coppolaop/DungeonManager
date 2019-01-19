package br.com.darksun.util.comparator;

import java.util.Comparator;

import br.com.darksun.entity.Personagem;

public class NomeComparator implements Comparator<Personagem>
{
	@Override
	public int compare( Personagem p1, Personagem p2 )
	{
		return p1.getNome( ).compareTo( p2.getNome( ) );
	}

}
