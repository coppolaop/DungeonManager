package br.com.darksun.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.darksun.control.CombateController;
import br.com.darksun.entity.Personagem;
import br.com.darksun.util.adapter.JLabelAdapter;

class CombateControllerTest
{
	//Controller
	CombateController controller;
	//PJs
	Personagem PJ1;
	Personagem PJ2;
	Personagem PJ3;
	//PDMs
	Personagem PDM1;
	Personagem PDM2;
	Personagem PDM3;
	Personagem PDM4;
	//JLabels
	JLabelAdapter labelTextoLog;
	JLabelAdapter labelRodadas;
	

	@BeforeEach
	void setUp( ) throws Exception
	{
		//Instanciando PJs
		PJ1 = new Personagem( 1, "resources/pj/Marcos.properties", "Marcos", "Guerreiro", "", 32, 2, 16, 180, 180, true, true, 0, "" );
		PJ2 = new Personagem( 2, "resources/pj/Igor.properties", "Igor", "Guerreiro", "", 22, 4, 14, 180, 150, true, true, 0, "" );
		PJ3 = new Personagem( 3, "resources/pj/Abel.properties", "Abel", "Clérigo", "", 26, 0, 10, 160, 150, true, true, 0, "" );
		//Instanciando PDMs
		PDM1 = new Personagem( 4, "resources/pdm/Elfo_Arqueiro.properties", "Elfo arqueiro", "Patrulheiro", "", 26, 6, 16, 100, 100, false, true, 1, "" );
		PDM2 = new Personagem( 5, "resources/pdm/Fantasma.properties", "Fantasma", "Morto-vivo", "", 12, 2, 14, 70, 70, false, true, 1, "" );
		PDM3 = new Personagem( 6, "resources/pdm/Lobo_Gigante.properties", "Lobo Gigante", "Animal Selvagem", "", 20, 10, 10, 60, 80, false, true, 1, "" );
		PDM4 = new Personagem( 5, "resources/pdm/Fantasma.properties", "Fantasma", "Morto-vivo", "", 12, 2, 22, 40, 70, false, true, 2, "" );
		//Criando Listas
		List<Personagem> PJs = new ArrayList<Personagem>( );
		List<Personagem> PDMs = new ArrayList<Personagem>( );
		//Preenchendo lista de PJs
		PJs.add( PJ1 );
		PJs.add( PJ2 );
		PJs.add( PJ3 );
		//Preenchendo lista de PDMs
		PDMs.add( PDM1 );
		PDMs.add( PDM2 );
		PDMs.add( PDM3 );
		PDMs.add( PDM4 );
		//Labels
		labelTextoLog = new JLabelAdapter( "" );
		labelRodadas = new JLabelAdapter( "0" );
		//Controller
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
		Personagem PDM5 = new Personagem( 5, "resources/pdm/Fantasma.properties", "Fantasma", "Morto-vivo", "", 12, 2, 22, 40, 70, false, true, 3, "" );
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
	void testRemoverUltimoPersonagem( ) //Valida o novo "Ultimo da rodada"
	{
		controller.removerPersonagem( 6 );
		Assert.assertEquals( PDM3, controller.getModel( ).getUltimoDaRodada( ) );
	}

	@Test
	void testGetRodada( )
	{
		for(int i = 0; i < 15; i++) // 7 personagens, 2 turnos passados
		{
			controller.finalizarTurno( labelRodadas );
		}
		Assert.assertEquals( "2", labelRodadas.getText( ) );
	}

}
