<?xml version="1.0" encoding="UTF-8"?>
<html xmlns="http://www.w3.org/1999/xhtml"   
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      >
       <ui:composition>
			<!-- POS-GALERY-STRUCT TAG -->
			<div class="controls">
				<h1>
					<span class="control fa fa-arrow-circle-left" data-direction="previous"></span>
					<span class="control fa fa-arrow-circle-right fa-3" data-direction="next"></span>
					<span class="grid fa fa-th"></span> <span
					class="fs-toggle fa fa-arrows-alt"></span>
				</h1>
			</div>
		
		
		
			<script
				src="#{request.contextPath}/assets/js/s-galery/plugins.js"></script>
			<script
				src="#{request.contextPath}/assets/js/s-galery/scripts.js"></script>
			<script>
				$(document).ready(function() {
					$('#gallery-container').sGallery({
						fullScreenEnabled : true
					});
				});
			</script>
		<!-- END POS-GALERY-STRUCT TAG -->       
       
       </ui:composition>
</html>
