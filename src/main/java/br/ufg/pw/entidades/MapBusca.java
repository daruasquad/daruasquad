package br.ufg.pw.entidades;

public class MapBusca {
	
	private String busca;
	private double latitude;
	private double longitude;
	private int zoom;
	
	
	public String getBusca() {
		if (busca == null) {
			return "";
		}
		return busca;
	}
	public void setBusca(String busca) {
		this.busca = busca;
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
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	
	
}
