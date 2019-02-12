$(function() {
	
	var notifyCount = function() {
		$.post("/admin/notify", function(res) {
			if(res.data > 0) {
				$("#unreadcount").text(res.data);
				//$("#ckFather").css("display","inline");
			} 
		});
		
	}
	notifyCount();
	setInterval(notifyCount, 3000);
	
});