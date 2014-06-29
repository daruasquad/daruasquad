/** Inicializa o mapa do Google */
$(document).ready(function() {
	Application.mapOptions =  mapOptions();
	var mapElement = document.getElementById("map");
	Application.map = new google.maps.Map(mapElement, mapOptions);
	Application.map.setZoom(11);
	Application.map.setCenter(new google.maps.LatLng(-15.6825443,-49.2538256));
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

function addMarker(idLocal, lat, lon, title) {
	
	// To add the marker to the map, use the 'map' property
	var marker = new google.maps.Marker({
	    position: new google.maps.LatLng(lat,lon),
	    map: Application.map,
	    title:title
	});
	
	marker.config = {
		idLocal:idLocal, 
		lat:lat, 
		lon:lon, 
		title:title
	};
	
	Application.map.markers.push(marker);

}

function getMyPositionCenter() {
	
	if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(centerMapHere);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
	
}

function centerMapHere(position) {
	var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
	Application.map.setCenter(pos);
}