//Fill pending table at initial load
getPendingRequests();

//Assign functions to tabs
$('#pendingTab').click(getPendingRequests);
$('#approvedTab').click(getApprovedRequests);
$('#deniedTab').click(getDeniedRequests);

//On click for Submit Request btn in navbar
$('#submitRequest').click(submitRequest)

//Tab Onclicks
function getPendingRequests() {
	console.log('Getting pending requests...')
	getRequests(1);
}

function getApprovedRequests() {
	console.log('Getting approved requests...')
	getRequests(2);
}

function getDeniedRequests() {
	console.log('Getting denied requests...')
	getRequests(3);
}

//Read user input, send ajax request/write to db
function submitRequest() {
	const id = document.getElementById('uId').innerHTML;
	const amount = $("#rAmount").val();
	const rFor = $("#rFor").val();

	var settings = {
		"url": "http://localhost:8080/ExpenseReimbursementSystem/rest/ers/request",
		"method": "POST",
		"timeout": 0,
		"headers": {
			"Content-Type": "application/json"
		},
		"data": JSON.stringify({ "uId": id, "amount": amount, "rFor": rFor }),
	};

	$.ajax(settings).done((response) => {
		console.log(`Response: ${response}`);
	});
}

//Callback fn for tabs
function getRequests(type) {
	//Get user id
	const id = document.getElementById('uId').innerHTML;

	//Settings for jquery ajax call
	const settings = {
		url: `http://localhost:8080/ExpenseReimbursementSystem/rest/ers/user/${id}?type=${type}`,
		method: 'GET',
		timeout: 0,
	};

	//Write initial table html to dom
	const hanger = $('#display');
	hanger.empty();

	hanger.append(`<table class='table'>
   <thead><tr> <th> Id # </th>
  <th> Amount </th> <th> For </th> <th> Date </th> </tr></thead> <tbody></tbody> </table>`);



	// Append loading message
	$('table').append('<p class="load">Loading...<p>')

	//Settings for jquery ajax call

	//Query rest endpoint
	$.ajax(settings).done(response => {
		//Clean Display area once content can be loaded
		$('.load').remove();

		response.forEach(element => {
			$('tbody').prepend(`
	  <tr>
      <td>${element.id}</td>
      <td>$${element.amount}</td>
      <td>${element.rFor}</td>
      <td>${(new Date(element.date).toDateString())}</td>
	  </tr>
      `)
		});
	});
}
//Update user information for each input selected
$('#submitFirst').click(() => {
	const input = $('#fName').val();
	const id = document.getElementById('uId').innerHTML;
	const col = "first_name"

	var settings = {
		"url": "http://localhost:8080/ExpenseReimbursementSystem/rest/employees/employee",
		"method": "PUT",
		"timeout": 0,
		"headers": {
			"Content-Type": "application/json"
		},
		"data": JSON.stringify({ "id": id, "col": col, "val": input }),
	};

	$.ajax(settings).done((response) => {
		console.log(`Response: ${response}`);
	});
})

$('#submitLast').click(() => {
	const input = $('#lName').val();
	const id = document.getElementById('uId').innerHTML;
	const col = "last_name"

	var settings = {
		"url": "http://localhost:8080/ExpenseReimbursementSystem/rest/employees/employee",
		"method": "PUT",
		"timeout": 0,
		"headers": {
			"Content-Type": "application/json"
		},
		"data": JSON.stringify({ "id": id, "col": col, "val": input }),
	};
	console.log(settings.data)
	$.ajax(settings).done((response) => {
		console.log(`Response: ${response}`);
	});

})

$('#submitEmail').click(() => {
	const input = $('#email').val();
	const id = document.getElementById('uId').innerHTML;
	const col = "email";

	var settings = {
		"url": "http://localhost:8080/ExpenseReimbursementSystem/rest/employees/employee",
		"method": "PUT",
		"timeout": 0,
		"headers": {
			"Content-Type": "application/json"
		},
		"data": JSON.stringify({ "val": id, "col": col, "val": input }),
	};

	$.ajax(settings).done((response) => {
		console.log(`Response: ${response}`);
	});

})

