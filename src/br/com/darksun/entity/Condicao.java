package br.com.darksun.entity;

public class Condicao
{
	private Integer idCondicao;
	private Integer duracaoAtual;
	private Integer valor;
	private Efeito efeito;

	public Condicao( )
	{

	}

	public Condicao( Integer idCondicao, Integer duracaoAtual, Integer valor, Efeito efeito )
	{
		super( );
		this.idCondicao = idCondicao;
		this.duracaoAtual = duracaoAtual;
		this.valor = valor;
		this.efeito = efeito;
	}

	public Integer getIdCondicao( )
	{
		return idCondicao;
	}

	public void setIdCondicao( Integer idCondicao )
	{
		this.idCondicao = idCondicao;
	}

	public Integer getDuracaoAtual( )
	{
		return duracaoAtual;
	}

	public void setDuracaoAtual( Integer duracaoAtual )
	{
		this.duracaoAtual = duracaoAtual;
	}

	public Integer getValor( )
	{
		return valor;
	}

	public void setValor( Integer valor )
	{
		this.valor = valor;
	}

	public Efeito getEfeito( )
	{
		return efeito;
	}

	public void setEfeito( Efeito efeito )
	{
		this.efeito = efeito;
	}
}
