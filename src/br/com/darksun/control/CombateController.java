package br.com.darksun.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.darksun.entity.Condicao;
import br.com.darksun.entity.Efeito;
import br.com.darksun.entity.Personagem;
import br.com.darksun.model.TabelaCombate;
import br.com.darksun.util.comparator.IniciativaComparator;
import br.com.darksun.util.interfaces.Textable;

public class CombateController implements ActionListener
{
	private TabelaCombate model;
	private TabelaCombate modelClone;
	private Textable labelTextoLog;
	private String colunas[] =
	{ "Nome", "CA", "HP Atual", "HP Total" };

	public CombateController( List< Personagem > PJs, List< Personagem > PDMs, Textable labelTextoLog,
			Textable labelRodadas )
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

			labelTextoLog.append( model.getValueAt( index, 0 ) + " foi reposicionado para antes de "
					+ model.getValueAt( index - 1, 0 ) + "\n" );

			if ( model.getPersonagem( index ).equals( model.getUltimoDaRodada( ) ) )
			{
				model.setUltimoDaRodada( model.getPersonagem( index - 1 ) );
				System.out.println( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) );
				labelTextoLog.append( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) + "\n" );
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

			labelTextoLog.append( model.getValueAt( index, 0 ) + " foi reposicionado para depois de "
					+ model.getValueAt( index + 1, 0 ) + "\n" );

			if ( model.getPersonagem( index + 1 ).equals( model.getUltimoDaRodada( ) ) )
			{
				model.setUltimoDaRodada( model.getPersonagem( index ) );
				System.out.println( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) );
				labelTextoLog.append( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) + "\n" );
			}

			model.trocar( index, index + 1 );
		}
	}

	public void ativaCondicao( )
	{
		Personagem personagem = model.getPersonagem( 0 );

		List< Condicao > removidos = new ArrayList< Condicao >( );
		for ( Condicao condicao : personagem.getCondicoes( ) )
		{
			Efeito efeito = condicao.getEfeito( );
			if ( efeito.getIsContinuo( ) )
			{
				if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[1] ) )
				{
					if ( efeito.getIsPositivo( ) )
					{
						personagem.setCa( personagem.getCa( ) + condicao.getValor( ) );
						System.out.println( personagem + " recebeu " + condicao.getValor( )
								+ " a mais na CA pela condição " + condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " a mais na CA pela condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					} else
					{
						personagem.setCa( personagem.getCa( ) - condicao.getValor( ) );
						System.out.println( personagem + " recebeu " + condicao.getValor( )
								+ " a menos na CA pela condição " + condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " a menos na CA pela condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					}
				} else if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[2] ) )
				{
					if ( efeito.getIsPositivo( ) )
					{
						personagem.setHpAtual( personagem.getHpAtual( ) + condicao.getValor( ) );
						System.out.println( personagem + " recebeu " + condicao.getValor( ) + " de cura pela condição "
								+ condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " de cura pela condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					} else
					{
						personagem.setHpAtual( personagem.getHpAtual( ) - condicao.getValor( ) );
						System.out.println( personagem + " recebeu " + condicao.getValor( ) + " de dano pela condição "
								+ condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " de dano pela condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					}
				} else if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[3] ) )
				{
					if ( efeito.getIsPositivo( ) )
					{
						personagem.setHpMaximo( personagem.getHpMaximo( ) + condicao.getValor( ) );
						System.out.println( personagem + " recebeu " + condicao.getValor( )
								+ " a mais no HP Total pela condição " + condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " a mais no HP Total pela condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					} else
					{
						personagem.setHpMaximo( personagem.getHpMaximo( ) - condicao.getValor( ) );
						System.out.println( personagem + " recebeu " + condicao.getValor( )
								+ " a menos no HP Total pela condição " + condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " a menos no HP Total pela condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					}
				} else
				{
					if ( efeito.getIsPositivo( ) )
					{
						System.out.println( personagem + " recebeu " + condicao.getValor( )
								+ " a mais no atributo da condição " + condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " a mais no atributo da condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					} else
					{
						System.out.println( personagem + " recebeu " + condicao.getValor( )
								+ " a menos no atributo da condição " + condicao.getEfeito( ).getNome( ) );
						labelTextoLog.append( personagem + " recebeu " + condicao.getValor( )
								+ " a menos no atributo da condição " + condicao.getEfeito( ).getNome( ) + "\n" );
					}
				}
			}

			condicao.setDuracaoAtual( condicao.getDuracaoAtual( ) - 1 );
			if ( condicao.getDuracaoAtual( ) == 0 )
			{
				removidos.add( condicao );
			}
		}
		for ( Condicao condicao : removidos )
		{
			this.removerCondicao( 0, condicao );
		}
	}

	public void finalizarTurno( )
	{
		Personagem personagem = model.getPersonagem( 0 );

		System.out.println( personagem + " finalizou seu turno\n" );

		labelTextoLog.append( personagem + " finalizou seu turno\n\n" );

		if ( model.getPersonagem( 0 ).equals( model.getUltimoDaRodada( ) ) )
		{
			model.avancaRodada( );
		}
		model.remover( personagem );
		model.adicionar( personagem );
		ativaCondicao( );
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
				labelTextoLog.append( personagem + " foi adicionado ao combate\n" );
				personagem = personagem.clone( );
				personagem.setReplica( personagem.getReplica( ) + 1 );
			}
		}
	}

	public Boolean removerPersonagem( Integer removido )
	{

		Personagem personagem = model.getPersonagem( removido );
		System.out.println( model.getValueAt( removido, 0 ) + " foi removido do combate" );
		labelTextoLog.append( model.getValueAt( removido, 0 ) + " foi removido do combate\n" );

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
			labelTextoLog.append( "A rodada agora acaba depois de " + model.getUltimoDaRodada( ) + "\n" );
		}

		return false;
	}

	public Integer condicaoPersonagem( Personagem personagem )
	{
		Integer contagem = 0;
		for ( Condicao condicao : personagem.getCondicoes( ) )
		{
			if ( condicao.getEfeito( ).getIsPositivo( ) )
			{
				contagem++;
			} else
			{
				contagem--;
			}
		}

		if ( contagem == 0 && !personagem.getCondicoes( ).isEmpty( ) )
		{
			contagem++;
		}

		return contagem;
	}

	public Integer adicionarCondicao( Integer index, Efeito efeito, Integer duracao, Integer valor )
	{
		Personagem personagem = model.getPersonagem( index );
		Condicao condicao = new Condicao( personagem.countCondicao( ) + 1, duracao, valor, efeito );
		personagem.addCondicao( condicao );
		System.out.println( personagem + " recebeu a condição " + efeito.getNome( ) + " por " + duracao + " turno(s)" );
		labelTextoLog
				.append( personagem + " recebeu a condição " + efeito.getNome( ) + " por " + duracao + " turno(s)\n" );

		if ( !condicao.getEfeito( ).getIsContinuo( ) )
		{
			if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[1] ) )
			{
				if ( efeito.getIsPositivo( ) )
				{
					personagem.setCa( personagem.getCa( ) + condicao.getValor( ) );
				} else
				{
					personagem.setCa( personagem.getCa( ) - condicao.getValor( ) );
				}
			} else if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[2] ) )
			{
				if ( efeito.getIsPositivo( ) )
				{
					personagem.setHpAtual( personagem.getHpAtual( ) + condicao.getValor( ) );
				} else
				{
					personagem.setHpAtual( personagem.getHpAtual( ) - condicao.getValor( ) );
				}
			} else if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[3] ) )
			{
				if ( efeito.getIsPositivo( ) )
				{
					personagem.setHpMaximo( personagem.getHpMaximo( ) + condicao.getValor( ) );
				} else
				{
					personagem.setHpMaximo( personagem.getHpMaximo( ) - condicao.getValor( ) );
				}
			}
		}

		return condicaoPersonagem( personagem );
	}

	public Integer removerCondicao( Integer index, Condicao condicao )
	{
		Personagem personagem = model.getPersonagem( index );

		Efeito efeito = condicao.getEfeito( );
		if ( !condicao.getEfeito( ).getIsContinuo( ) )
		{
			if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[1] ) )
			{
				if ( efeito.getIsPositivo( ) )
				{
					personagem.setCa( personagem.getCa( ) - condicao.getValor( ) );
				} else
				{
					personagem.setCa( personagem.getCa( ) + condicao.getValor( ) );
				}
			} else if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[2] ) )
			{
				if ( efeito.getIsPositivo( ) )
				{
					personagem.setHpAtual( personagem.getHpAtual( ) - condicao.getValor( ) );
				} else
				{
					personagem.setHpAtual( personagem.getHpAtual( ) + condicao.getValor( ) );
				}
			} else if ( efeito.getAtributoAfetado( ).equalsIgnoreCase( colunas[3] ) )
			{
				if ( efeito.getIsPositivo( ) )
				{
					personagem.setHpMaximo( personagem.getHpMaximo( ) - condicao.getValor( ) );
				} else
				{
					personagem.setHpMaximo( personagem.getHpMaximo( ) + condicao.getValor( ) );
				}
			}
		}

		personagem.removeCondicao( condicao );
		System.out.println( personagem + " perdeu a condição " + condicao.getEfeito( ).getNome( ) );
		labelTextoLog.append( personagem + " perdeu a condição " + condicao.getEfeito( ).getNome( ) + "\n",
				"destaque" );
		return condicaoPersonagem( personagem );
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
