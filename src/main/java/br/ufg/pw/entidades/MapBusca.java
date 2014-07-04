package br.ufg.pw.entidades;

public class MapBusca {
	
	private String busca;
	private double latitude;
	private double longitude;
	
	private double latitudeDefault 	= -16.679985;
	private double longitudeDefault = -49.246674;
	private int zoomDefault = 11;
	
	
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
		if (latitude == 0.0) {
			latitude = latitudeDefault;
			return latitudeDefault;
		}
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		if (longitude == 0.0) {
			longitude = longitudeDefault;
			return longitudeDefault;
		}
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getZoom() {
		if(zoom == 0) {
			zoom = zoomDefault;
		}
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	
	
}
