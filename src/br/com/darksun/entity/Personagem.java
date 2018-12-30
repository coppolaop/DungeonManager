package br.com.darksun.entity;

public class Personagem
{
	private Integer idPersonagem;
	private String filePath;
	private String nome;
	private String classe;
	private String imagem;
	private Integer ca;
	private Integer bonusIniciativa;
	private Integer iniciativa;
	private Integer hpMaximo;
	private Integer hpAtual;
	private Boolean isPJ;

	public Personagem( )
	{

	}

	public Personagem( Integer idPersonagem, String filePath, String nome, String classe, String imagem, Integer ca,
			Integer bonusIniciativa, Integer iniciativa, Integer hpMaximo, Integer hpAtual, Boolean isPJ )
	{
		super( );
		this.idPersonagem = idPersonagem;
		this.filePath = filePath;
		this.nome = nome;
		this.classe = classe;
		this.imagem = imagem;
		this.ca = ca;
		this.bonusIniciativa = bonusIniciativa;
		this.iniciativa = iniciativa;
		this.hpMaximo = hpMaximo;
		this.hpAtual = hpAtual;
		this.isPJ = isPJ;
	}

	@Override
	public String toString( )
	{
		return this.getNome( );
	}

	public Integer getIdPersonagem( )
	{
		return idPersonagem;
	}

	public void setIdPersonagem( Integer idPersonagem )
	{
		this.idPersonagem = idPersonagem;
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

	public String getClasse( )
	{
		return classe;
	}

	public void setClasse( String classe )
	{
		this.classe = classe;
	}

	public String getImagem( )
	{
		return imagem;
	}

	public void setImagem( String imagem )
	{
		this.imagem = imagem;
	}

	public Integer getCa( )
	{
		return ca;
	}

	public void setCa( Integer ca )
	{
		this.ca = ca;
	}

	public Integer getBonusIniciativa( )
	{
		return bonusIniciativa;
	}

	public void setBonusIniciativa( Integer bonusIniciativa )
	{
		this.bonusIniciativa = bonusIniciativa;
	}

	public Integer getIniciativa( )
	{
		return iniciativa;
	}

	public void setIniciativa( Integer iniciativa )
	{
		this.iniciativa = iniciativa;
	}

	public Integer getHpMaximo( )
	{
		return hpMaximo;
	}

	public void setHpMaximo( Integer hpMaximo )
	{
		this.hpMaximo = hpMaximo;
	}

	public Integer getHpAtual( )
	{
		return hpAtual;
	}

	public void setHpAtual( Integer hpAtual )
	{
		this.hpAtual = hpAtual;
	}

	public Boolean getIsPJ( )
	{
		return isPJ;
	}

	public void setIsPJ( Boolean isPJ )
	{
		this.isPJ = isPJ;
	}
}
