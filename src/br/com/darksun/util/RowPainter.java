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
	List< String > nomePersonagens = new ArrayList< String >( );
	List< Integer > situacoes = new ArrayList< Integer >( );

	public Component getTableCellRendererComponent(	JTable table, Object value, boolean isSelected, boolean hasFocus,
													int row, int column )
	{
		TabelaCombate model = ( TabelaCombate ) table.getModel( );
		Personagem personagemSelecionado = ( Personagem ) model.getPersonagem( row );
		Integer situacao = 0;

		for ( String nome : nomePersonagens )
		{
			if ( nome.equals( personagemSelecionado.toString( ) ) )
			{
				situacao = situacoes.get( nomePersonagens.indexOf( nome ) );
			}
		}

		if ( situacao > 0 )
		{
			setBackground( new Color( 0, 255, 0, 100 ) );
		} else if ( situacao < 0 )
		{
			setBackground( new Color( 255, 0, 0, 100 ) );
		} else
		{
			setBackground( Color.WHITE );
		}

		return super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
	}

	public void setSituacao( JTable table, Integer row, Integer situacao )
	{
		TabelaCombate model = ( TabelaCombate ) table.getModel( );
		Personagem personagem = ( Personagem ) model.getPersonagem( row );

		if ( nomePersonagens.contains( personagem.toString( ) ) )
		{
			int index = nomePersonagens.indexOf( personagem.toString( ) );
			situacoes.remove( index );
			situacoes.add( index, situacao );
		} else
		{
			nomePersonagens.add( personagem.toString( ) );
			situacoes.add( situacao );
		}
	}
}