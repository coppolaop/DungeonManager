package br.com.darksun.gui.characterbuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.darksun.control.EfeitoController;
import br.com.darksun.entity.Efeito;
import br.com.darksun.gui.JFPrincipal;
import br.com.darksun.gui.JPPadrao;

public class JPFormularioEfeito extends JPPadrao
{
	private String[ ] booleano =
	{ "Sim", "Não" };
	private String[ ] atributos =
	{ "CA", "HP Atual", "HP Maximo" };

	public JPFormularioEfeito( JFPrincipal frame, Boolean isPJ, String newID, Efeito efeito )
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

		JLabel labelNome = new JLabel( "Nome do Efeito:" );
		labelNome.setBounds( 0, 50, 175, 30 );
		labelNome.setForeground( Color.WHITE );
		labelNome.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldNome = new JTextField( );
		fieldNome.setBounds( 200, 50, 150, 30 );

		JLabel labelDuracaoPadrao = new JLabel( "Duração Padrão:" );
		labelDuracaoPadrao.setBounds( width - 350, 50, 175, 30 );
		labelDuracaoPadrao.setForeground( Color.WHITE );
		labelDuracaoPadrao.setHorizontalAlignment( SwingConstants.RIGHT );

		JTextField fieldDuracaoPadrao = new JTextField( );
		fieldDuracaoPadrao.setBounds( width - 150, 50, 50, 30 );

		JLabel labelAtributoAfetado = new JLabel( "Atributo Afetado:" );
		labelAtributoAfetado.setBounds( 0, 150, 175, 30 );
		labelAtributoAfetado.setForeground( Color.WHITE );
		labelAtributoAfetado.setHorizontalAlignment( SwingConstants.RIGHT );

		JComboBox ComboBoxAtributoAfetado = new JComboBox( atributos );
		ComboBoxAtributoAfetado.setBounds( 200, 150, 150, 30 );

		JLabel labelIsPositivo = new JLabel( "O Efeito é Positivo:" );
		labelIsPositivo.setBounds( width - 350, 100, 175, 30 );
		labelIsPositivo.setForeground( Color.WHITE );
		labelIsPositivo.setHorizontalAlignment( SwingConstants.RIGHT );

		JComboBox ComboBoxIsPositivo = new JComboBox( booleano );
		ComboBoxIsPositivo.setBounds( width - 150, 100, 50, 30 );

		JLabel labelIsContinuo = new JLabel( "O Efeito é Contínuo:" );
		labelIsContinuo.setBounds( width - 350, 150, 175, 30 );
		labelIsContinuo.setForeground( Color.WHITE );
		labelIsContinuo.setHorizontalAlignment( SwingConstants.RIGHT );

		JComboBox ComboBoxIsContinuo = new JComboBox( booleano );
		ComboBoxIsContinuo.setBounds( width - 150, 150, 50, 30 );

		String Str = efeito != null ? "Atualizar efeito" : "Criar efeito";
		JButton btnCriar = new JButton( Str );
		btnCriar.setBounds( width / 2 - 250 / 2, height / 2 - 30 / 2, 250, 30 );

		JLabel labelError = new JLabel( "" );
		labelError.setBounds( width / 2 - 500 / 2, height / 2 + 30 / 2, 500, 30 );
		labelError.setForeground( new Color( 155, 0, 0 ) );
		labelError.setFont( new Font( labelError.getFont( ).getFontName( ), labelError.getFont( ).getStyle( ), 14 ) );
		labelError.setHorizontalAlignment( SwingConstants.CENTER );

		add( labelImg );
		add( labelNome );
		add( fieldNome );
		add( labelDuracaoPadrao );
		add( fieldDuracaoPadrao );
		add( labelAtributoAfetado );
		add( ComboBoxAtributoAfetado );
		add( labelIsPositivo );
		add( ComboBoxIsPositivo );
		add( labelIsContinuo );
		add( ComboBoxIsContinuo );
		add( btnCriar );
		add( labelError );

		if ( efeito != null )
		{
			fieldNome.setText( efeito.getNome( ) );
			fieldDuracaoPadrao.setText( efeito.getDuracaoPadrao( ).toString( ) );
			ComboBoxIsPositivo.setSelectedIndex( efeito.getIsPositivo( ) ? 0 : 1 );
			String atributoAfetado = efeito.getAtributoAfetado( );
			if ( atributoAfetado.equals( "CA" ) )
			{
				ComboBoxAtributoAfetado.setSelectedIndex( 0 );
			} else if ( atributoAfetado.equals( "HP Atual" ) )
			{
				ComboBoxAtributoAfetado.setSelectedIndex( 1 );
			} else if ( atributoAfetado.equals( "HP Maximo" ) )
			{
				ComboBoxAtributoAfetado.setSelectedIndex( 2 );
			}

			ComboBoxIsContinuo.setSelectedIndex( efeito.getIsContinuo( ) ? 0 : 1 );
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
				labelDuracaoPadrao.setBounds( width - 350, 50, 175, 30 );
				fieldDuracaoPadrao.setBounds( width - 150, 50, 50, 30 );
				labelIsPositivo.setBounds( 0, 150, 175, 30 );
				ComboBoxIsPositivo.setBounds( 200, 150, 150, 30 );
				labelAtributoAfetado.setBounds( width - 350, 100, 175, 30 );
				ComboBoxAtributoAfetado.setBounds( width - 150, 100, 50, 30 );
				labelIsContinuo.setBounds( width - 350, 150, 175, 30 );
				ComboBoxIsContinuo.setBounds( width - 150, 150, 50, 30 );
				btnCriar.setBounds( width / 2 - 250 / 2, height / 2 - 30 / 2, 250, 30 );
				labelError.setBounds( width / 2 - 500 / 2, height / 2 + 30 / 2, 500, 30 );
			}
		} );

		btnCriar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Pattern patNumero = Pattern.compile( "[+-]?[0-9]+" );

				if ( fieldNome.getText( ).equals( "" ) )
					labelError.setText( "Campo Nome está em branco" );
				else if ( fieldDuracaoPadrao.getText( ).equals( "" ) )
					labelError.setText( "Campo Duração Padrão está em branco" );
				else if ( !patNumero.matcher( fieldDuracaoPadrao.getText( ).toString( ) ).matches( ) )
					labelError.setText( "Campo Duração Padrão precisa conter somente números" );
				else if ( !isInteger( fieldDuracaoPadrao.getText( ).toString( ) ) )
					labelError.setText( "Esse número de Duração Padrão está grande demais" );
				else
				{
					if ( efeito != null )
					{
						File file = new File( efeito.getFilePath( ) );
						if ( file.exists( ) )
							file.delete( );
					}

					Boolean isPositivo = ComboBoxIsPositivo.getSelectedIndex( ) == 0 ? true : false;
					Boolean isContinuo = ComboBoxIsContinuo.getSelectedIndex( ) == 0 ? true : false;
					String atributoAfetado = "";
					switch ( ComboBoxAtributoAfetado.getSelectedIndex( ) )
					{
					case 0:
						atributoAfetado = "CA";
						break;
					case 1:
						atributoAfetado = "HP Atual";
						break;
					case 2:
						atributoAfetado = "HP Maximo";
						break;
					}

					EfeitoController ec = new EfeitoController( );
					if ( newID != null )
					{
						ec.criarEfeito( Integer.parseInt( newID ), fieldNome.getText( ),
								Integer.parseInt( fieldDuracaoPadrao.getText( ) ), isPositivo, atributoAfetado,
								isContinuo );
					} else
					{
						ec.criarEfeito( efeito.getIdEfeito( ), fieldNome.getText( ),
								Integer.parseInt( fieldDuracaoPadrao.getText( ) ), isPositivo, atributoAfetado,
								isContinuo );
					}
					frame.remove( JPFormularioEfeito.this );
					frame.setTela( new JPListarEfeito( frame ), true );
				}
			}
		} );
	}

	public static Boolean isInteger( String valor )
	{
		try
		{
			Integer.parseInt( valor );
			return true;
		} catch ( NumberFormatException ex )
		{
			return false;
		}
	}
}
