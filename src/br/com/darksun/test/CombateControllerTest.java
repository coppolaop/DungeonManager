package br.com.darksun.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.darksun.control.CombateController;
import br.com.darksun.entity.Condicao;
import br.com.darksun.entity.Efeito;
import br.com.darksun.entity.Personagem;
import br.com.darksun.util.adapter.JLabelAdapter;

class CombateControllerTest
{
	// Controller
	CombateController controller;
	// PJs
	Personagem PJ1;
	Personagem PJ2;
	Personagem PJ3;
	// PDMs
	Personagem PDM1;
	Personagem PDM2;
	Personagem PDM3;
	Personagem PDM4;
	// JLabels
	JLabelAdapter labelTextoLog;
	JLabelAdapter labelRodadas;

	@BeforeEach
	void setUp( ) throws Exception
	{
		// Instanciando PJs
		PJ1 = new Personagem( 1, "resources/pj/Marcos.properties", "Marcos", "Guerreiro", "", 32, 2, 16, 180, 180, true,
				true, 0, "" );
		PJ2 = new Personagem( 2, "resources/pj/Igor.properties", "Igor", "Guerreiro", "", 22, 4, 14, 180, 150, true,
				true, 0, "" );
		PJ3 = new Personagem( 3, "resources/pj/Abel.properties", "Abel", "Clérigo", "", 26, 0, 10, 160, 150, true, true,
				0, "" );
		// Instanciando PDMs
		PDM1 = new Personagem( 4, "resources/pdm/Elfo_Arqueiro.properties", "Elfo arqueiro", "Patrulheiro", "", 26, 6,
				16, 100, 100, false, true, 1, "" );
		PDM2 = new Personagem( 5, "resources/pdm/Fantasma.properties", "Fantasma", "Morto-vivo", "", 12, 2, 14, 70, 70,
				false, true, 1, "" );
		PDM3 = new Personagem( 6, "resources/pdm/Lobo_Gigante.properties", "Lobo Gigante", "Animal Selvagem", "", 20,
				10, 10, 60, 80, false, true, 1, "" );
		PDM4 = new Personagem( 5, "resources/pdm/Fantasma.properties", "Fantasma", "Morto-vivo", "", 12, 2, 22, 40, 70,
				false, true, 2, "" );
		// Criando Listas
		List< Personagem > PJs = new ArrayList< Personagem >( );
		List< Personagem > PDMs = new ArrayList< Personagem >( );
		// Preenchendo lista de PJs
		PJs.add( PJ1 );
		PJs.add( PJ2 );
		PJs.add( PJ3 );
		// Preenchendo lista de PDMs
		PDMs.add( PDM1 );
		PDMs.add( PDM2 );
		PDMs.add( PDM3 );
		PDMs.add( PDM4 );
		// Labels
		labelTextoLog = new JLabelAdapter( "" );
		labelRodadas = new JLabelAdapter( "0" );
		// Controller
		controller = new CombateController( PJs, PDMs, labelTextoLog, labelRodadas );
	}

	@Test
	void testSubirCima( )
	{
		controller.subir( 1 );
		Assert.assertEquals( PDM1, controller.getModel( ).getPersonagem( 0 ) );
	}

	@Test
	void testSubirBaixo( )
	{
		controller.subir( 1 );
		Assert.assertEquals( PDM4, controller.getModel( ).getPersonagem( 1 ) );
	}

	@Test
	void testDescerCima( )
	{
		controller.descer( 2 );
		Assert.assertEquals( PJ2, controller.getModel( ).getPersonagem( 2 ) );
	}

	@Test
	void testDescerBaixo( )
	{
		controller.descer( 2 );
		Assert.assertEquals( PJ1, controller.getModel( ).getPersonagem( 3 ) );
	}

	@Test
	void testFinalizarTurnoPrimeiro( )
	{
		controller.finalizarTurno( labelRodadas );
		Assert.assertEquals( PDM1, controller.getModel( ).getPersonagem( 0 ) );
	}

	@Test
	void testFinalizarTurnoUltimo( )
	{
		controller.finalizarTurno( labelRodadas );
		Assert.assertEquals( PDM4, controller.getModel( ).getPersonagem( 6 ) );
	}

	@Test
	void testAdicionarPersonagem( )
	{
		Personagem PDM5 = new Personagem( 5, "resources/pdm/Fantasma.properties", "Fantasma", "Morto-vivo", "", 12, 2,
				22, 40, 70, false, true, 3, "" );
		controller.adicionarPersonagem( PDM5, 1 );
		Assert.assertEquals( PDM5, controller.getModel( ).getPersonagem( 7 ) );
	}

	@Test
	void testRemoverPersonagem( )
	{
		controller.removerPersonagem( 0 );
		Assert.assertEquals( PDM1, controller.getModel( ).getPersonagem( 0 ) );
	}

	@Test
	void testRemoverUltimoPersonagem( ) // Valida o novo "Ultimo da rodada"
	{
		controller.removerPersonagem( 6 );
		Assert.assertEquals( PDM3, controller.getModel( ).getUltimoDaRodada( ) );
	}

	@Test
	void testGetRodada( )
	{
		for ( int i = 0; i < 15; i++ ) // 7 personagens, 2 turnos passados
		{
			controller.finalizarTurno( labelRodadas );
		}
		Assert.assertEquals( "2", labelRodadas.getText( ) );
	}

	@Test
	void testAdicionarCondicao( )
	{
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 1, sangramento, 2, 3 );
		Condicao condicao = controller.getModel( ).getPersonagem( 1 ).getCondicoes( ).get( 0 );
		Assert.assertEquals( "Sangramento", condicao.getEfeito( ).getNome( ) );
	}

	@Test
	void testCondicaoPersonagemPositiva( )
	{
		Efeito aumentoDeCA = new Efeito( 2, null, "Aumento de CA", 10, true, "CA", false );
		controller.adicionarCondicao( 1, aumentoDeCA, 12, 2 );
		Integer condicao = controller.condicaoPersonagem( controller.getModel( ).getPersonagem( 1 ) );
		Assert.assertEquals( "1", condicao.toString( ) );
	}

	@Test
	void testCondicaoPersonagemNeutra( )
	{
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 1, sangramento, 2, 3 );
		Efeito aumentoDeCA = new Efeito( 2, null, "Aumento de CA", 10, true, "CA", false );
		controller.adicionarCondicao( 1, aumentoDeCA, 12, 2 );
		Integer condicao = controller.condicaoPersonagem( controller.getModel( ).getPersonagem( 1 ) );
		Assert.assertEquals( "1", condicao.toString( ) );
	}

	@Test
	void testCondicaoPersonagemNegativa( )
	{
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 1, sangramento, 2, 3 );
		controller.adicionarCondicao( 1, sangramento, 2, 3 );
		Efeito aumentoDeCA = new Efeito( 2, null, "Aumento de CA", 10, true, "CA", false );
		controller.adicionarCondicao( 1, aumentoDeCA, 12, 2 );
		Integer condicao = controller.condicaoPersonagem( controller.getModel( ).getPersonagem( 1 ) );
		Assert.assertEquals( "-1", condicao.toString( ) );
	}

	@Test
	void testCondicaoPersonagemZerada( )
	{
		Integer condicao = controller.condicaoPersonagem( controller.getModel( ).getPersonagem( 1 ) );
		Assert.assertEquals( "0", condicao.toString( ) );
	}

	@Test
	void testRemoverCondicao( )
	{
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 1, sangramento, 2, 3 );
		Condicao condicao = controller.getModel( ).getPersonagem( 1 ).getCondicoes( ).get( 0 );
		controller.removerCondicao( 1, condicao );
		Assert.assertEquals( true, controller.getModel( ).getPersonagem( 1 ).getCondicoes( ).isEmpty( ) );
	}

	@Test
	void testRemoverApenasUmaCondicao( )
	{
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 1, sangramento, 2, 3 );
		Efeito aumentoDeCA = new Efeito( 2, null, "Aumento de CA", 10, true, "CA", false );
		controller.adicionarCondicao( 1, aumentoDeCA, 12, 2 );
		controller.adicionarCondicao( 1, aumentoDeCA, 12, 2 );
		Condicao condicao = controller.getModel( ).getPersonagem( 1 ).getCondicoes( ).get( 1 );
		controller.removerCondicao( 1, condicao );
		Integer situacao = controller.condicaoPersonagem( controller.getModel( ).getPersonagem( 1 ) );
		Assert.assertEquals( "1", situacao.toString( ) );
	}
	
	@Test
	void testDuracaoAtivaCondicao( )
	{
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 0, sangramento, 2, 3 );
		controller.ativaCondicao( );
		Assert.assertEquals( "1", controller.getModel( ).getPersonagem( 0 ).getCondicoes( ).get( 0 ).getDuracaoAtual( ).toString( ) );
	}
	
	@Test
	void testDanoAtivaCondicao( )
	{
		Personagem personagem = controller.getModel( ).getPersonagem( 0 );
		Integer hpInicial = personagem.getHpAtual( );
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 0, sangramento, 2, 3 );
		controller.ativaCondicao( );
		controller.ativaCondicao( );
		Integer hpFinal = personagem.getHpAtual( );
		Assert.assertEquals( 6, hpInicial - hpFinal );
	}
	
	@Test
	void testUltimaAtivaCondicao( )
	{
		Efeito sangramento = new Efeito( 1, null, "Sangramento", 1, false, "HP Atual", true );
		controller.adicionarCondicao( 0, sangramento, 2, 3 );
		controller.ativaCondicao( );
		controller.ativaCondicao( );
		controller.ativaCondicao( );
		Assert.assertEquals( true, controller.getModel( ).getPersonagem( 0 ).getCondicoes( ).isEmpty( ) );
	}
	
	@Test
	void testUltimaAtivaCondicaoNaoContinua( )
	{
		Integer valor = 2;
		Personagem personagem = controller.getModel( ).getPersonagem( 0 );
		Efeito aumentoDeCA = new Efeito( 2, null, "Aumento de CA", 10, true, "CA", false );
		controller.adicionarCondicao( 0, aumentoDeCA, 3, valor );
		Integer posEfeito = personagem.getCa( );
		controller.ativaCondicao( );
		controller.ativaCondicao( );
		controller.ativaCondicao( );
		Integer fimEfeito = personagem.getCa( );
		Integer contagem = posEfeito - fimEfeito;
		Assert.assertEquals( valor.toString( ), contagem.toString( ) );
	}
}
