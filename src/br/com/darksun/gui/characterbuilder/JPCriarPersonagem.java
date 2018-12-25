package br.com.darksun.gui.characterbuilder;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.darksun.gui.JFPrincipal;
import br.com.darksun.gui.JPPadrao;

public class JPCriarPersonagem extends JPPadrao
{

	public JPCriarPersonagem( JFPrincipal frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, frame.getWidth( ), frame.getHeight( ) );

		JLabel labelNome = new JLabel( "Nome do Personagem:" );
		labelNome.setBounds( 0, 50, 175, 30 );
		labelNome.setForeground( Color.WHITE );
		labelNome.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldNome = new JTextField( );
		fieldNome.setBounds( 200, 50, 150, 30 );

		JLabel labelCA = new JLabel( "CA:" );
		labelCA.setBounds( width - 350, 50, 175, 30 );
		labelCA.setForeground( Color.WHITE );
		labelCA.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldCA = new JTextField( );
		fieldCA.setBounds( width - 150, 50, 50, 30 );

		JLabel labelClasse = new JLabel( "Classe:" );
		labelClasse.setBounds( 0, 150, 175, 30 );
		labelClasse.setForeground( Color.WHITE );
		labelClasse.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldClasse = new JTextField( );
		fieldClasse.setBounds( 200, 150, 150, 30 );

		JLabel labelBonusIni = new JLabel( "Bônus de Iniciativa:" );
		labelBonusIni.setBounds( width - 350, 100, 175, 30 );
		labelBonusIni.setForeground( Color.WHITE );
		labelBonusIni.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldBonusIni = new JTextField( );
		fieldBonusIni.setBounds( width - 150, 100, 50, 30 );

		JLabel labelHP = new JLabel( "HP:" );
		labelHP.setBounds( width - 350, 150, 175, 30 );
		labelHP.setForeground( Color.WHITE );
		labelHP.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldHP = new JTextField( );
		fieldHP.setBounds( width - 150, 150, 50, 30 );

		JButton btnCriar = new JButton( "Criar Personagem" );
		btnCriar.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2, 200, 30 );

		add( labelNome );
		add( fieldNome );
		add( labelCA );
		add( fieldCA );
		add( labelClasse );
		add( fieldClasse );
		add( labelBonusIni );
		add( fieldBonusIni );
		add( labelHP );
		add( fieldHP );
		add( btnCriar );
		
		frame.repaint( );
		
		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;

				setBounds( 0, 0, width, height );
				labelNome.setBounds( 0, 50, 175, 30 );
				fieldNome.setBounds( 200, 50, 150, 30 );
				labelCA.setBounds( width - 350, 50, 175, 30 );
				fieldCA.setBounds( width - 150, 50, 50, 30 );
				labelClasse.setBounds( 0, 150, 175, 30 );
				fieldClasse.setBounds( 200, 150, 150, 30 );
				labelBonusIni.setBounds( width - 350, 100, 175, 30 );
				fieldBonusIni.setBounds( width - 150, 100, 50, 30 );
				labelHP.setBounds( width - 350, 150, 175, 30 );
				fieldHP.setBounds( width - 150, 150, 50, 30 );
				btnCriar.setBounds( width / 2 - 200 / 2, height / 2 - 30 / 2, 200, 30 );
			}
		} );

	}
}
