/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	// VEHICLE_REGS_REG, VEHICLE_REGS_BRAND, VEHICLE_REGS_MODEL, VEHICLE_REGS_MANU_YEAR, VEHICLE_REGS_OWNER_FIRST_NAME, VEHICLE_REGS_OWNER_LAST_NAME, VEHICLE_REGS_FIRST_REG " +	
	var $put_example = $('#put_example')
		, $SET_VEHICLE_REGS_BRAND = $('#SET_VEHICLE_REGS_BRAND')
		, $SET_VEHICLE_REGS_REG = $('#SET_VEHICLE_REGS_REG');
	
	getInventory();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, VEHICLE_REGS_PK = $this.val()
			, $tr = $this.closest('tr')
			, VEHICLE_REGS_BRAND = $tr.find('.CL_VEHICLE_REGS_BRAND').text()
			, VEHICLE_REGS_REG = $tr.find('.CL_VEHICLE_REGS_REG').text()
			, VEHICLE_REGS_MODEL = $tr.find('.CL_VEHICLE_REGS_MODEL').text()
			, VEHICLE_REGS_MANU_YEAR = $tr.find('.CL_VEHICLE_REGS_MANU_YEAR').text()
			, VEHICLE_REGS_OWNER_FIRST_NAME = $tr.find('.CL_VEHICLE_REGS_OWNER_FIRST_NAME').text()
			, VEHICLE_REGS_OWNER_LAST_NAME = $tr.find('.CL_VEHICLE_REGS_OWNER_LAST_NAME').text()
			, VEHICLE_REGS_FIRST_REG = $tr.find('.CL_VEHICLE_REGS_FIRST_REG').text();
		
		$('#SET_VEHICLE_REGS_PK').val(VEHICLE_REGS_PK);
		$SET_VEHICLE_REGS_BRAND.text(VEHICLE_REGS_BRAND);
		$SET_VEHICLE_REGS_REG.text(VEHICLE_REGS_REG);
		$('#SET_VEHICLE_REGS_MODEL').text(VEHICLE_REGS_MODEL);
		$('#SET_VEHICLE_REGS_MANU_YEAR').text(VEHICLE_REGS_MANU_YEAR);
		$('#SET_VEHICLE_REGS_OWNER_FIRST_NAME').val(VEHICLE_REGS_OWNER_FIRST_NAME);
		$('#SET_VEHICLE_REGS_OWNER_LAST_NAME').val(VEHICLE_REGS_OWNER_LAST_NAME);
		$('#SET_VEHICLE_REGS_FIRST_REG').text(VEHICLE_REGS_FIRST_REG);		
		
		$('#update_response').text("");
	});
	
	$put_example.submit(function(e) {
		e.preventDefault(); //cancel form submit
		
		var obj = $put_example.serializeObject()
			, VEHICLE_REGS_BRAND= $SET_VEHICLE_REGS_BRAND.text()
			, VEHICLE_REGS_REG = $SET_VEHICLE_REGS_REG.text();
		
		updateInventory(obj, VEHICLE_REGS_BRAND, VEHICLE_REGS_REG);
	});
});

function updateInventory(obj, brand, reg) {
	
	ajaxObj = {  
			type: "PUT",
			url: "http://localhost:7001/com.vehicle.rest/api/v3/inventory/" + brand + "/" + reg,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#update_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getInventory();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

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
				'<td class="CL_VEHICLE_REGS_BTN"> <button class="UPDATE_BTN" value=" ' + param.VEHICLE_REGS_PK + ' " type="button">Update</button> </td>' +
			'</tr>';
}