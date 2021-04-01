//Fill pending table at initial load
getPendingRequests();

//Assign functions to tabs
$('#pendingTab').click(getPendingRequests);
$('#approvedTab').click(getApprovedRequests);
$('#deniedTab').click(getDeniedRequests);

//Get request submit button by id
$('#submitRequest').click(submitRequest)

//Edit button for sidebar
$('#editPrompt').click(updateInfo);

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
		"data": JSON.stringify({ "uId": id, "amount":  amount, "rFor": rFor }),
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
  <th> Amount </th> <th> For </th> <th> Date </th> </tr></thead> </table>`);



	// Append loading message
	$('table').append('<p id="load">Loading...<p>')

	//Settings for jquery ajax call

	//Query rest endpoint
	$.ajax(settings).done(response => {
		//Display area once content can be loaded
		$('#load').remove();
		$('thead.empty');

		response.forEach(element => {
			$('table').prepend(`
	  <tr>
      <td>${element.id}</td>
      <td>$${element.amount}</td>
      <td>${element.rFor}</td>
      <td>${(new Date(element.date).toDateString())}</td>
	  </tr>
      `)
		});
	});
	
	//Update user information based on inputs
}
