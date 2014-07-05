package br.ufg.pw.entidades;

import br.ufg.pw.dao.LocalDao;

public class MapBusca {
	
	private String busca; 							// string buscada no formulário
	private double latitude;						// latitude do estado atual do mapa (centro do mapa)
	private double longitude;						// longitude do estado atual do mapa (centro do mapa)
	private int zoom;								// zoom do mapa
	
	/* Confogurações para o primeiro carregamento */
	private double latitudeDefault 	= -16.679985;
	private double longitudeDefault = -49.246674;
	private int zoomDefault = 11;
	
	// flag indicando se a busca descrita pela intância ja foi pedida ao banco
	private boolean used = false;
	
	/** Recupera a string de busca 
	 * @return String 	busca textual do usuário
	 * */
	public String getBusca() {
		if (busca == null) {
			busca = "";
		}
		return busca;
	}
	/**
	 *  Atualiza a busca textual do usuário
	 *  @param String busca 	A busca textual realizada pelo usuário 
	 *  @return void 
	 **/
	public void setBusca(String busca) {
		this.used = false;
		this.busca = busca;
	}
	
	/** Recupera a última latitude do centro do mapa visualizada pelo usuário, no momento
	 * da submissão do formulário de busca
	 * @return double 	Latitude em graus, pela Projeção  EPSG 4326
	 */
	public double getLatitude() {
		if (latitude == 0.0) {
			latitude = latitudeDefault;
			return latitudeDefault;
		}
		return latitude;
	}
	
	/** Atualiza a latitude do centro mapa visualizada pelo usuário
	 * @param double latitude 	Latitude em graus, pela Projeção  EPSG 4326
	 * @return void
	 */
	public void setLatitude(double latitude) {
		this.used = false;
		this.latitude = latitude;
	}
	
	/** Recupera a última longitude do centro do mapa visualizada pelo usuário, no momento
	 * da submissão do formulário de busca
	 * @return double 	Longitude em graus, pela Projeção  EPSG 4326
	 */
	public double getLongitude() {
		if (longitude == 0.0) {
			longitude = longitudeDefault;
			return longitudeDefault;
		}
		return longitude;
	}
	
	/** Atualiza a longitude do centro mapa visualizada pelo usuário
	 * @param double longitude 	Longitude em graus, pela Projeção  EPSG 4326
	 * @return void
	 */
	public void setLongitude(double longitude) {
		this.used = false;
		this.longitude = longitude;
	}
	
	/** Recupera o zoom do último estado do mapa ou o zoom padrão 
	 * @return int Zoom do mapa
	 * */
	public int getZoom() {
		if(zoom == 0) {
			zoom = zoomDefault;
		}
		return zoom;
	}
	
	/** Atualiza o zoom sendo visualizado pelo usuário no momento da submissão do formulário de busca
	 * @param int zoom O zoom do mapa, pelo padrão usado pelo google (de 0 a 21). O zoom da applicação possui 
	 * outros limites
	 * @see LocalDao
	 */
	public void setZoom(int zoom) {
		this.used = false;
		this.zoom = zoom;
	}
	
	/** Marca uma busca como usada. Deve ser usada quando uma busca é 
	 * pedida ao banco com esse objeto, listando os locais*/
	public  void markUsed() {
		this.used = false;
	}
	
	/** Retorna se a busca já foi usada contra o banco 
	 * @return boolean 	TRUE para uma busca já usada, FALSE em caso contrário
	 * */
	public boolean isUsed() {
		 return this.used;
	}
	
}
