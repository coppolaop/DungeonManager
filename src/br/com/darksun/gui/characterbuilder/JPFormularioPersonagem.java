package br.com.darksun.gui.characterbuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.darksun.control.PersonagemController;
import br.com.darksun.entity.Personagem;
import br.com.darksun.gui.JFPrincipal;
import br.com.darksun.gui.JPPadrao;

public class JPFormularioPersonagem extends JPPadrao
{

	public JPFormularioPersonagem( JFPrincipal frame, Boolean isPJ, String newID, Personagem personagem )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );

		JLabel labelImg = new JLabel( "" );
		labelImg.setBounds( ( width - 200 ) / 2, 50, 200, 200 );
		ImageIcon logoApp = new ImageIcon( getClass( ).getClassLoader( ).getResource( frame.getIconPath( ) ) );
		logoApp = new ImageIcon( logoApp.getImage( ).getScaledInstance( labelImg.getWidth( ), labelImg.getHeight( ),
				logoApp.getImage( ).SCALE_DEFAULT ) );
		labelImg.setIcon( logoApp );

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

		JLabel labelTxtImg = new JLabel( "Imagem:" );
		labelTxtImg.setBounds( 0, 100, 175, 30 );
		labelTxtImg.setForeground( Color.WHITE );
		labelTxtImg.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldImg = new JTextField( );
		fieldImg.setBounds( 200, 100, 150, 30 );

		JButton btnImg = new JButton( "Selecionar" );
		btnImg.setBounds( 375, 100, 100, 30 );

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

		String dono = isPJ ? "Jogador" : "Mestre";
		JButton btnCriar = new JButton( "Criar Personagem do " + dono );
		btnCriar.setBounds( width / 2 - 250 / 2, height / 2 - 30 / 2, 250, 30 );

		JLabel labelError = new JLabel( "" );
		labelError.setBounds( width / 2 - 500 / 2, height / 2 + 30 / 2, 500, 30 );
		labelError.setForeground( new Color( 155, 0, 0 ) );
		labelError.setFont( new Font( labelError.getFont( ).getFontName( ), labelError.getFont( ).getStyle( ), 14 ) );
		labelError.setHorizontalAlignment( SwingConstants.CENTER );

		add( labelImg );
		add( labelNome );
		add( fieldNome );
		add( labelCA );
		add( fieldCA );
		add( labelTxtImg );
		add( fieldImg );
		add( btnImg );
		add( labelClasse );
		add( fieldClasse );
		add( labelBonusIni );
		add( fieldBonusIni );
		add( labelHP );
		add( fieldHP );
		add( btnCriar );
		add( labelError );

		if ( personagem != null )
		{
			fieldNome.setText( personagem.getNome( ) );
			fieldCA.setText( personagem.getCa( ).toString( ) );
			fieldClasse.setText( personagem.getClasse( ) );
			fieldBonusIni.setText( personagem.getBonusIniciativa( ).toString( ) );
			fieldHP.setText( personagem.getHpMaximo( ).toString( ) );
		}

		frame.repaint( );

		frame.addComponentListener( new ComponentAdapter( )
		{
			@Override
			public void componentResized( ComponentEvent e )
			{
				width = frame.getBounds( ).width;
				height = frame.getBounds( ).height;

				setBounds( 0, 0, width, height );
				labelImg.setBounds( ( width - 200 ) / 2, 50, 200, 200 );
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
				btnCriar.setBounds( width / 2 - 250 / 2, height / 2 - 30 / 2, 250, 30 );
				labelError.setBounds( width / 2 - 500 / 2, height / 2 + 30 / 2, 500, 30 );
			}
		} );

		btnImg.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				final JFileChooser fChooser = new JFileChooser( );
				int returnVal = fChooser.showOpenDialog( JPFormularioPersonagem.this );

				if ( returnVal == JFileChooser.APPROVE_OPTION )
				{
					File file = fChooser.getSelectedFile( );
					fieldImg.setText( file.getAbsolutePath( ) );
				}
			}
		} );

		btnCriar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Pattern patNumero = Pattern.compile( "[0-9]+" );

				if ( fieldNome.getText( ).equals( "" ) )
					labelError.setText( "Campo Nome está em branco" );
				else if ( fieldCA.getText( ).equals( "" ) )
					labelError.setText( "Campo CA está em branco" );
				else if ( !patNumero.matcher( fieldCA.getText( ).toString( ) ).matches( ) )
					labelError.setText( "Campo CA precisa conter somente números" );
				else if ( fieldClasse.getText( ).equals( "" ) )
					labelError.setText( "Campo Classe está em branco" );
				else if ( fieldBonusIni.getText( ).equals( "" ) )
					labelError.setText( "Campo de Bonus de Iniciativa está em branco" );
				else if ( !patNumero.matcher( fieldBonusIni.getText( ).toString( ) ).matches( ) )
					labelError.setText( "Campo de Bonus de Iniciativa precisa conter somente números" );
				else if ( fieldHP.getText( ).equals( "" ) )
					labelError.setText( "Campo HP está em branco" );
				else if ( !patNumero.matcher( fieldHP.getText( ).toString( ) ).matches( ) )
					labelError.setText( "Campo HP precisa conter somente números" );
				else
				{
					String imagem = "";
					File img = new File( fieldImg.getText( ) );
					if ( img.exists( ) && img.isFile( ) )
					{
						try
						{
							String location = "resources/img/" + ( isPJ ? "pj" : "pdm" ) + "/";
							File folder = new File( location );
							if ( !folder.exists( ) )
							{
								folder.mkdirs( );
							}

							File nf = new File( location + "personagem" + newID + ".jpg" );
							if ( nf.exists( ) )
							{
								nf.delete( );
							}
							Files.copy( img.toPath( ), nf.toPath( ) );

							imagem = nf.getPath( );
						} catch ( Exception ex )
						{
							System.out.println( "Falha ao salvar imagem" );
							ex.printStackTrace( );
						}
					} else
					{
						System.out.println( "Falha no carregamento da imagem" );
					}

					if ( personagem != null )
					{
						File file = new File( personagem.getFilePath( ) );
						if ( file.exists( ) )
							file.delete( );
					}

					PersonagemController pc = new PersonagemController( );
					if ( newID != null )
					{
						pc.criarPersonagem( newID, fieldNome.getText( ), fieldClasse.getText( ), fieldCA.getText( ),
								fieldBonusIni.getText( ), fieldHP.getText( ), imagem, isPJ );
					} else
					{
						pc.criarPersonagem( personagem.getIdPersonagem( ).toString( ), fieldNome.getText( ),
								fieldClasse.getText( ), fieldCA.getText( ), fieldBonusIni.getText( ),
								fieldHP.getText( ), imagem, isPJ );
					}
					frame.remove( JPFormularioPersonagem.this );
					frame.setTela( new JPListarPersonagem( frame ) );
				}
			}
		} );
	}
}
