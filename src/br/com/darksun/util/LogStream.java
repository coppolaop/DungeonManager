package br.com.darksun.util;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class LogStream extends OutputStream
{

	private final JTextArea textArea;
	private FileOutputStream outputStream;
	private PrintWriter printWriter;

	public LogStream( ) throws Exception
	{
		textArea = new JTextArea( );
		textArea.setEditable( false );
		textArea.setRows( 1000 );
		textArea.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 14 ) );
	}

	@Override
	public void flush( )
	{

	}

	@Override
	public void close( )
	{

	}

	public void println( String texto )
	{
		textArea.append( texto );
		textArea.append( System.getProperty( "line.separator" ) );

	}

	public void clean( )
	{
		textArea.setText( "" );
	}

	@Override
	public void write( int arg ) throws IOException
	{
		String text = String.valueOf( ( char ) arg );
		javax.swing.SwingUtilities.invokeLater( new Runnable( )
		{
			public void run( )
			{
				textArea.append( text );

				boolean termina = textArea.getText( ).indexOf( "[ F I M ]" ) >= 0;

				printWriter.print( text );
				printWriter.flush( );

				int quantLinhas = textArea.getLineCount( );

				if ( quantLinhas > 1000 )
					textArea.replaceRange( "", 0, quantLinhas - 1000 );

				if ( termina )
					try
					{
						printWriter.close( );
						outputStream.close( );
					} catch ( IOException ex )
					{
						ex.printStackTrace( );
					}
			}
		} );

	}

	public void criaArquivoLog( ) throws Exception
	{
		long inicio = System.currentTimeMillis( );

		File dir = new File( "resources/log/" );

		if ( !dir.exists( ) )
			dir.mkdirs( );

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy.MM.dd-HH.mm.ss" );

		File logFile = new File( dir, "DungeonManager." + sdf.format( new Date( inicio ) ) + ".log" );

		logFile.createNewFile( );

		outputStream = new FileOutputStream( logFile, true );
		printWriter = new PrintWriter( outputStream );

		PrintStream ps = new PrintStream( this );
		System.setOut( ps );
		System.setErr( ps );
	}

}
