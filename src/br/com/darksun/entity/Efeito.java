package br.com.darksun.entity;

public class Efeito
{
	private Integer idEfeito;
	private String filePath;
	private String nome;
	private Integer duracaoPadrao;
	private Boolean isPositivo;
	private String atributoAfetado;
	private Boolean isContinuo;

	public Efeito( )
	{

	}

	public Efeito( Integer idEfeito, String filePath, String nome, Integer duracaoPadrao, Boolean isPositivo,
			String atributoAfetado, Boolean isContinuo )
	{
		super( );
		this.idEfeito = idEfeito;
		this.filePath = filePath;
		this.nome = nome;
		this.duracaoPadrao = duracaoPadrao;
		this.isPositivo = isPositivo;
		this.atributoAfetado = atributoAfetado;
		this.isContinuo = isContinuo;
	}

	@Override
	public String toString( )
	{
		return nome;
	}

	public Integer getIdEfeito( )
	{
		return idEfeito;
	}

	public void setIdEfeito( Integer idEfeito )
	{
		this.idEfeito = idEfeito;
	}

	public String getFilePath( )
	{
		return filePath;
	}

	public void setFilePath( String filePath )
	{
		this.filePath = filePath;
	}

	public String getNome( )
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public Integer getDuracaoPadrao( )
	{
		return duracaoPadrao;
	}

	public void setDuracaoPadrao( Integer duracaoPadrao )
	{
		this.duracaoPadrao = duracaoPadrao;
	}

	public Boolean getIsPositivo( )
	{
		return isPositivo;
	}

	public void setIsPositivo( Boolean isPositivo )
	{
		this.isPositivo = isPositivo;
	}

	public String getAtributoAfetado( )
	{
		return atributoAfetado;
	}

	public void setAtributoAfetado( String atributoAfetado )
	{
		this.atributoAfetado = atributoAfetado;
	}

	public Boolean getIsContinuo( )
	{
		return isContinuo;
	}

	public void setIsContinuo( Boolean isContinuo )
	{
		this.isContinuo = isContinuo;
	}
}
