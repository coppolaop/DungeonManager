package br.com.darksun.util;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.darksun.entity.Personagem;
import br.com.darksun.model.TabelaCombate;

public class RowPainter extends DefaultTableCellRenderer
{
	List< Personagem > listPositivos = new ArrayList< Personagem >( );
	List< Personagem > listNegativos = new ArrayList< Personagem >( );

	public Component getTableCellRendererComponent(	JTable table, Object value, boolean isSelected, boolean hasFocus,
													int row, int column )
	{
		TabelaCombate model = ( TabelaCombate ) table.getModel( );
		Personagem personagem = ( Personagem ) model.getPersonagem( row );

		Integer quantidadePositivo = 0;
		Integer quantidadeNegativo = 0;

		for ( Personagem positivo : listPositivos )
		{
			if ( positivo.toString( ).equals( personagem.toString( ) ) )
			{
				quantidadePositivo++;
			}
		}

		for ( Personagem negativo : listNegativos )
		{
			if ( negativo.toString( ).equals( personagem.toString( ) ) )
			{
				quantidadeNegativo++;
			}
		}

		if( quantidadeNegativo == 0 && quantidadePositivo == 0) {
			setBackground( Color.WHITE );
		}
		else if ( quantidadeNegativo > quantidadePositivo )
		{
			setBackground( new Color( 255, 0, 0, 100 ) );
		} else
		{
			setBackground( new Color( 0, 255, 0, 100 ) );
		}

		return super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
	}
	
	public void addPositivo( Personagem personagem )
	{
		listPositivos.add( personagem );
	}
	
	public void removePositivo( Personagem personagem )
	{
		listPositivos.remove( personagem );
	}
	
	public void addNegativo( Personagem personagem )
	{
		listNegativos.add( personagem );
	}
	
	public void removeNegativo( Personagem personagem )
	{
		listNegativos.remove( personagem );
	}
}