/** Inicializa o mapa do Google */
$(document).ready(function() {
	Application.mapOptions =  mapOptions();
	var mapElement = document.getElementById("map");
	Application.map = new google.maps.Map(mapElement, mapOptions);
	
	var lat = $("[id$=latitude]").val();
	var lon = $("[id$=longitude]").val();
	var zoom =	$("[id$=zoom]").val();
	
	console.log(lat);
	var center = new google.maps.LatLng(Number(lat),Number(lon));
	
	Application.map.setCenter(center);
	Application.map.setZoom(Number(zoom));
	
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
	
	marker.id = idLocal;
	
	google.maps.event.addListener(marker, 'click', function() {
		// Filtrar pelo local clicado na lista
		var id = this.id;
		$("div.panel-body[data-id=" + id + "]").show().siblings().hide();
		
	  });
	
	Application.map.markers.push(marker);

}

function clearMarkers() {
	Application.map.markers.forEach(function(e){
		e.setMap(null);
	});
	Application.map.markers.length=0;
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


function insertLocalMapEvents() {
	google.maps.event.addListener(Application.map, 'click', function(event) {
		clearMarkers();
		var lat = event.latLng.lat();
		var lng = event.latLng.lng();
		
		addMarker(null, lat,  lng, "New place");
		getAdressGeocode(lat, lng, fillInsertFormAdress);
		$("[id$=latitude]").val(lat);
		$("[id$=longitude]").val(lng);
	});
	
	
	
	
}


function insertSearchFormMapEvents() {
	google.maps.event.addListener(Application.map, 'mouseup', updateSearchFormMapInfo);
	google.maps.event.addListener(Application.map, 'zoom', updateSearchFormMapInfo);
	google.maps.event.addListener(Application.map, 'center_changed', updateSearchFormMapInfo);
	
}

function updateSearchFormMapInfo(event){
	$("[id$=latitude]").val(Application.map.center.lat());
	$("[id$=longitude]").val(Application.map.center.lng());
	$("[id$=zoom]").val(Application.map.zoom);
}


function getAdressGeocode(lat, lon, callback) {
	$.ajax({
		url: "http://maps.googleapis.com/maps/api/geocode/json?address=" + lat +"," + lon + "&sensor=true",
		success: callback
	});
} 

function fillInsertFormAdress(data) {
	var formatedAddress = data.results[0].formatted_address;	
	var rua 	=  	extractGeocodeInfo("route", data);
	var bairro 	= 	extractGeocodeInfo("neighborhood", data);
	var cidade 	= 	extractGeocodeInfo("locality", data);
	var estado 	= 	extractGeocodeInfo("administrative_area_level_1", data);
	var pais 	=	extractGeocodeInfo("country", data);
	
	$("[id$=rua]").val(rua.long_name);
	$("[id$=bairro]").val(bairro.long_name);
	$("[id$=cidade]").val(cidade.long_name);
	$("[id$=estado]").val(estado.short_name);
	$("[id$=pais]").val(pais.long_name);
	
	$("#formAddress").html("O local será criado em " + formatedAddress);
}


function extractGeocodeInfo(infoName, data){
	var extract = data.results[0].address_components.filter(function(component) {
	    return component.types.indexOf(infoName) >= 0;
	});
	return extract[0];
}