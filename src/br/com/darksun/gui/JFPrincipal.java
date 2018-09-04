package br.com.darksun.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;

public class JFPrincipal extends JFrame
{
	public static void main( String[ ] args )
	{
		EventQueue.invokeLater( new Runnable( )
		{
			public void run( )
			{
//				JFPrincipal frame = new JFPrincipal( );
				
				PersonagemController pc = new PersonagemController();
				Personagem igor = pc.carregar( );
				
				System.out.println( igor.getNome( ) + " - " + igor.getClasse( ) + " - " + igor.getCa( ) );

			}
		} );
	}
	
	public JFPrincipal( )
	{
		this.setVisible( true );
		this.setSize( 1500,750 );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( null );
		this.setTitle( "Dungeon Manager" );
		
		
	}
}
