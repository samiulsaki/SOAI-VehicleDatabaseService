///**
// * js file for post.html
// * Please use modern web browser as this file will not attempt to be
// * compatible with older browsers. Use Chrome and open javascript console
// * or Firefox with developer console.
// * 
// * jquery is required
// */
//$(document).ready(function() {
//	
//	getInventory();
//	
//	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
//		//console.log(this);
//		var $this = $(this)
//			, VEHICLE_REGS_PK = $this.val()
//			, obj = {VEHICLE_REGS_PK : VEHICLE_REGS_PK}
//			, $tr = $this.closest('tr')
//			, VEHICLE_REGS_REG = $tr.find('.CL_VEHICLE_REGS_REG').text()
//			, VEHICLE_REGS_BRAND = $tr.find('.CL_VEHICLE_REGS_BRAND').text();
//		
//		deleteInventory(obj, VEHICLE_REGS_REG, VEHICLE_REGS_BRAND);
//	});
//});
//
//function deleteInventory(obj, maker, code) {
//	
//	ajaxObj = {  
//			type: "DELETE",
//			url: "http://localhost:7001/com.vehicle.rest/api/v3/inventory/" + maker + "/" + code,
//			data: JSON.stringify(obj), 
//			contentType:"application/json",
//			error: function(jqXHR, textStatus, errorThrown) {
//				console.log(jqXHR.responseText);
//			},
//			success: function(data) {
//				//console.log(data);
//				$('#delete_response').text( data[0].MSG );
//			},
//			complete: function(XMLHttpRequest) {
//				//console.log( XMLHttpRequest.getAllResponseHeaders() );
//				getInventory();
//			}, 
//			dataType: "json" //request JSON
//		};
//		
//	return $.ajax(ajaxObj);
//}
//
//function getInventory() {
//	
//	var d = new Date()
//		, n = d.getTime();
//	
//	ajaxObj = {  
//			type: "GET",
//			url: "http://localhost:7001/com.vehicle.rest/api/v1/inventory", 
//			data: "ts="+n,
//			contentType:"application/json",
//			error: function(jqXHR, textStatus, errorThrown) {
//				console.log(jqXHR.responseText);
//			},
//			success: function(data) { 
//				//console.log(data);
//				var html_string = "";
//				
//				$.each(data, function(index1, val1) {
//					//console.log(val1);
//					html_string = html_string + templateGetInventory(val1);
//				});
//				
//				$('#get_inventory').html("<table border='1'>" + html_string + "</table>");
//			},
//			complete: function(XMLHttpRequest) {
//				//console.log( XMLHttpRequest.getAllResponseHeaders() );
//			}, 
//			dataType: "json" //request JSON
//		};
//		
//	return $.ajax(ajaxObj);
//}
//
//function templateGetInventory(param) {
//	return '<tr>' +
//				'<td class="CL_VEHICLE_REGS_REG">' + param.VEHICLE_REGS_REG + '</td>' +
//				'<td class="CL_VEHICLE_REGS_BRAND">' + param.VEHICLE_REGS_BRAND + '</td>' +
//				'<td class="CL_VEHICLE_REGS_MODEL">' + param.VEHICLE_REGS_MODEL + '</td>' +
//				'<td class="CL_VEHICLE_REGS_MANU_YEAR">' + param.VEHICLE_REGS_MANU_YEAR + '</td>' +
//				'<td class="CL_VEHICLE_REGS_OWNER_FIRST_NAME">' + param.VEHICLE_REGS_OWNER_FIRST_NAME + '</td>' +
//				'<td class="CL_VEHICLE_REGS_OWNER_LAST_NAME">' + param.VEHICLE_REGS_OWNER_LAST_NAME + '</td>' +
//				'<td class="CL_VEHICLE_REGS_FIRST_REG">' + param.VEHICLE_REGS_FIRST_REG + '</td>' +
//				'<td class="CL_VEHICLE_REGS_BTN"> <button class="DELETE_BTN" value=" ' + param.VEHICLE_REGS_PK + ' " type="button">Delete</button> </td>' +
//			'</tr>';
//}