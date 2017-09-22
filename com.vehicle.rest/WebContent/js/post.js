/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */

getInventory();

$(document).ready(function() {
	//console.log("ready");
	
	var $post_example = $('#post_example');
	
	/**
	 * This is for the Submit button
	 * It will trigger a ajax POST call to: api/v2/inventory
	 * This will submit a item entry to our inventory database
	 */
	$('#submit_it').click(function(e) {
		//console.log("submit button has been clicked");
		e.preventDefault(); //cancel form submit
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		//console.log(jsObj);
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:7001/com.vehicle.rest/api/v2/inventory/", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				//console.log(data);
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG );
				}
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getInventory();
			}, 
			dataType: "json" //request JSON
		};
		
		$.ajax(ajaxObj);
	});
	
	/**
	 * This is for the 2nd Submit button "Submit v2"
	 * It will do the same thing as Submit above but the api
	 * will process it in a different way.
	 */
	$('#submit_it2').click(function(e) {
		//console.log("submit button has been clicked");
		e.preventDefault(); //cancel form submit
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		//console.log(jsObj);
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:7001/com.vehicle.rest/api/v3/inventory/", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				//console.log(data);
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG );
				}
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getInventory();
			}, 
			dataType: "json" //request JSON
		};
		
		$.ajax(ajaxObj);
	});
});



function getInventory() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:7001/com.vehicle.rest/api/v1/inventory", 
			data: "ts="+n, 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					console.log(val1);
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_inventory').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
	return '<tr>' +
				'<td class="CL_VEHICLE_REGS_BRAND">' + param.VEHICLE_REGS_BRAND + '</td>' +
				'<td class="CL_VEHICLE_REGS_REG">' + param.VEHICLE_REGS_REG + '</td>' +
				'<td class="CL_VEHICLE_REGS_MODEL">' + param.VEHICLE_REGS_MODEL + '</td>' +
				'<td class="CL_VEHICLE_REGS_MANU_YEAR">' + param.VEHICLE_REGS_MANU_YEAR + '</td>' +
				'<td class="CL_VEHICLE_REGS_OWNER_FIRST_NAME">' + param.VEHICLE_REGS_OWNER_FIRST_NAME + '</td>' +
				'<td class="CL_VEHICLE_REGS_OWNER_LAST_NAME">' + param.VEHICLE_REGS_OWNER_LAST_NAME + '</td>' +
				'<td class="CL_VEHICLE_REGS_FIRST_REG">' + param.VEHICLE_REGS_FIRST_REG + '</td>' +
				//'<td class="CL_VEHICLE_REGS_BTN"> <button class="UPDATE_BTN" value=" ' + param.VEHICLE_REGS_PK + ' " type="button">Update</button> </td>' +
			'</tr>';
}