<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- POS-GALERY-STRUCT TAG -->
	<div class="controls">
			<h1>
				<span class="control fa fa-arrow-circle-left" data-direction="previous"></span>
				<span class="control fa fa-arrow-circle-right fa-3" data-direction="next"></span>
				<span class="grid fa fa-th"></span> <span
				class="fs-toggle fa fa-arrows-alt"></span>
			</h1>
		</div>

	</div>



	<script
		src="${pageContext.request.contextPath}/assets/js/s-galery/plugins.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/s-galery/scripts.js"></script>
	<script>
		$(document).ready(function() {
			$('#gallery-container').sGallery({
				fullScreenEnabled : true
			});
		});
	</script>
<!-- END POS-GALERY-STRUCT TAG -->