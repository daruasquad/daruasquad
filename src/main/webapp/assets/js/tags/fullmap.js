/** Inicializa o mapa do Google */
$(document).ready(function() {
	Application.mapOptions =  mapOptions();
	var mapElement = document.getElementById("map");
	Application.map = new google.maps.Map(mapElement, mapOptions);
	Application.map.markers = [];
});

/** 
 * Opções de inicialização do mapa 
 * @return Object parametros de inicializacao do mapa do Google
 * */
function mapOptions() {
	
	return {
		center: new google.maps.LatLng(-16.6825443,-49.2538256),
		zoom: 13,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	
}