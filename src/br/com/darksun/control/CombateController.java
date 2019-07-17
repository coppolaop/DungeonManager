package br.com.darksun.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;

import br.com.darksun.entity.Personagem;
import br.com.darksun.gui.JPCombate;
import br.com.darksun.gui.JPInicial;
import br.com.darksun.model.TabelaCombate;
import br.com.darksun.util.comparator.IniciativaComparator;
import br.com.darksun.util.interfaces.Textable;

public class CombateController implements ActionListener
{
	private TabelaCombate model;
	private TabelaCombate modelClone;
	private Textable labelTextoLog;

	public CombateController( List< Personagem > PJs, List< Personagem > PDMs, Textable labelTextoLog, Textable labelRodadas )
	{
		List< Personagem > personagens = new ArrayList< Personagem >( );
		System.out.println( "-------   Iniciativa   -------" );

		for ( Personagem personagem : PJs )
		{
			personagens.add( personagem );
			System.out.println( personagem.toString( ) + " - " + personagem.getIniciativa( ) + " de Iniciativa" );
		}

		System.out.println( "      ----- VS -----" );

		for ( Personagem personagem : PDMs )
		{
			personagens.add( personagem );
			System.out.println( personagem.toString( ) + " - " + personagem.getIniciativa( ) + " de Iniciativa" );
		}

		System.out.println( "------------------------------" );

		Collections.sort( personagens, new IniciativaComparator( ) );

		this.labelTextoLog = labelTextoLog;
		
		model = new TabelaCombate( personagens, labelTextoLog, labelRodadas );
		model.setUltimoDaRodada( model.getPersonagem( model.getRowCount( ) - 1 ) );
		
		modelClone = new TabelaCombate( personagens, labelTextoLog, labelRodadas );
	}

	public void subir( Integer index )
	{
		if ( index > 0 )
		{
			System.out.println( model.getValueAt( index, 0 ) + " foi reposicionado para antes de "
					+ model.getValueAt( index - 1, 0 ) );

			if( labelTextoLog instanceof JLabel )
			{
			( ( JLabel ) labelTextoLog ).setText( model.getValueAt( index, 0 ) + " foi reposicionado para antes de "
					+ model.getValueAt( index - 1, 0 ) );
			}
			
			if ( model.getPersonagem( index ).equals( model.getUltimoDaRodada( ) ) )
			{
				model.setUltimoDaRodada( model.getPersonagem( index - 1 ) );
				System.out.println( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) );
			}

			model.trocar( index, index - 1 );
		}
	}
	
	public void descer( Integer index )
	{
		if ( index < model.getRowCount( ) - 1 )
		{
			System.out.println( model.getValueAt( index, 0 ) + " foi reposicionado para depois de "
					+ model.getValueAt( index + 1, 0 ) );

			if( labelTextoLog instanceof JLabel )
			{
				( ( JLabel ) labelTextoLog ).setText( model.getValueAt( index, 0 ) + " foi reposicionado para depois de "
						+ model.getValueAt( index + 1, 0 ) );
			}

			if ( model.getPersonagem( index + 1 ).equals( model.getUltimoDaRodada( ) ) )
			{
				model.setUltimoDaRodada( model.getPersonagem( index ) );
				System.out.println( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) );
			}

			model.trocar( index, index + 1 );
		}
	}
	
	public void finalizarTurno( Textable labelNumeroRodadas )
	{
		Personagem personagem = model.getPersonagem( 0 );

		Object[ ] aux =
		{ model.getValueAt( 0, 0 ), model.getValueAt( 0, 1 ), model.getValueAt( 0, 2 ),
			model.getValueAt( 0, 3 ) };

		System.out.println( aux[0] + " finalizou seu turno" );

		if( labelTextoLog instanceof JLabel )
		{
			( ( JLabel ) labelTextoLog ).setText( aux[0] + " finalizou seu turno" );
		}
		
		if ( model.getPersonagem( 0 ).equals( model.getUltimoDaRodada( ) ) )
		{
			model.avancaRodada( );
		}
		model.remover( personagem );
		model.adicionar( personagem );
	}
	
	public void adicionarPersonagem( Personagem personagem, Integer quantidade )
	{
		if ( personagem != null )
		{
			for ( int i = 0; i < quantidade; i++ )
			{
				while ( modelClone.contains( personagem ) )
				{
					personagem.setReplica( personagem.getReplica( ) + 1 );
				}
				model.adicionar( personagem );
				modelClone.adicionar( personagem );
				System.out.println( personagem + " foi adicionado ao combate" );
				labelTextoLog.setText( personagem + " foi adicionado ao combate" );
				personagem = personagem.clone( );
				personagem.setReplica( personagem.getReplica( ) + 1 );
			}
		}
	}
	
	public Boolean removerPersonagem( Integer removido ) {
		
		Personagem personagem = model.getPersonagem( removido );
		System.out.println( model.getValueAt( removido, 0 ) + " foi removido do combate" );
		labelTextoLog.setText( model.getValueAt( removido, 0 ) + " foi removido do combate" );

		model.remover( personagem );

		if ( personagem.getReplica( ).equals( 0 ) )
		{
			modelClone.remover( personagem );
		}

		if ( model.getRowCount( ) == 0 )
		{
			return true;
		}

		if ( personagem.equals( model.getUltimoDaRodada( ) ) )
		{
			if ( removido == 0 )
			{
				model.setUltimoDaRodada( model.getPersonagem( ( model.getRowCount( ) - 1 ) ) );
				model.avancaRodada( );
			} else
			{
				model.setUltimoDaRodada( model.getPersonagem( removido - 1 ) );
			}

			System.out.println( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) );
		}

		return false;
	}

	public TabelaCombate getModel( )
	{
		return this.model;
	}

	public String getRodada( )
	{
		return this.model.getRodada( ).toString( );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{

	}
}
