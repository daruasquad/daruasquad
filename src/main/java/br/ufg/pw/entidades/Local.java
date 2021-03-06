package br.ufg.pw.entidades;

import java.util.List;

public class Local {

	//Atríbutos da classe
	private String nome;
	private List<String> obstaculos;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private double latitude;
	private double longitude;
	private String usuarioInsersor;
	private int id;

	//Métodos Getter e Setter
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getObstaculos() {
		return obstaculos;
	}
	public void setObstaculos(List<String> obstaculos) {
		this.obstaculos = obstaculos;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getUsuarioInsersor() {
		return usuarioInsersor;
	}
	public void setUsuarioInsersor(String usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
