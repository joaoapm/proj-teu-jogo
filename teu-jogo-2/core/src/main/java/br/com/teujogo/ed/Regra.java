package br.com.teujogo.ed;

public class Regra {

	private Integer id;
	private String regra;

	public Regra(Integer id) {
		this.id = id;
	}

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
