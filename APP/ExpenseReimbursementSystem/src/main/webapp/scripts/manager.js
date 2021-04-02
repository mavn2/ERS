//Fill pending table at intial load
getPendingRequests();

//On click/change content fns for tabs
$('#pendingTab').click(getPendingRequests);
$('#resolvedTab').click(() => getResolvedRequests(2, 3));
$('#employeeTab').click(getAllEmployees);

//Onclick for pending tab
function getPendingRequests() {
	//Get user id
	const id = document.getElementById('uId').innerHTML;

	//Write initial table html to dom
	const hanger = $('#display');
	hanger.empty();

	hanger.append(`<table class='table'>
   	  <thead><tr> <th> Id # </th>
  	  <th> Amount </th> <th> For </th> <th> By Id: </th> <th> Date </th> <th> Action </th></tr></thead> </table>`)

	// Append loading message
	$('table').append('<p id="load">Loading...<p>')

	//JQuery ajax settings
	var settings = {
		"url": `http://localhost:8080/ExpenseReimbursementSystem/rest/ers/all/1`,
		"method": "GET",
		"timeout": 0,
	}
	$.ajax(settings).done(response => {
		//Clean Display area once content can be loaded
		$('#load').remove();
		$('thead.empty');

		response.forEach(element => {
			//add approve/decline buttons if appropriate
			let action = `<button type="button" class="btn btn-success" data-id="${element.id}" onclick="resolveRequest(2)"><i class="bi bi-check-circle-fill" data-id="${element.id}"></i> </button> / <button type="button" class="btn btn-danger" data-id="${element.id}" onclick="resolveRequest(3)"><i class="bi bi-x-circle-fill" data-id="${element.id}"></i></button>`;
			if (id == element.applyId) {
				action = 'N/A';
			}
			$('table').prepend(`
	  <tr>
      <td>${element.id}</td>
      <td>$${element.amount}</td>
      <td>${element.rFor}</td>
	  <td>${element.applyId} </td>
      <td>${(new Date(element.date).toDateString())}</td>
	  <td><p>${action}</p></td>
	  </tr>
      `)
		});
	});
}

//Function for buttons
function resolveRequest(status) {
	const id = document.getElementById('uId').innerHTML;
	const requestId = event.target.getAttribute("data-id");

	var settings = {
		"url": "http://localhost:8080/ExpenseReimbursementSystem/rest/ers/request",
		"method": "PUT",
		"timeout": 0,
		"headers": {
			"Content-Type": "application/json"
		},
		"data": JSON.stringify({ "uId": id, "rId": requestId, "status": status }),
	};

	$.ajax(settings).done(function(response) {
		console.log(`Response: ${response}`);
	});
}

//Callback for resolved tab
function getResolvedRequests(...types) {
	//Array to store potential multiple request sets
	let results = [];
	//Counter tracks iterations
	let count = 0;

	//Write initial table html to dom
	const hanger = $('#display');
	hanger.empty();

	//Set theader to with additional columns
	hanger.append(`<table class='table'>
   			<thead><tr> <th> Id # </th> <th> Status </th>
  			<th> Amount </th> <th> For </th> <th> Date </th> </tr></thead> </table>`)

	// Append loading message
	$('table').append('<p id="load">Loading...<p>')

	//Get array of requests for each type requested
	// then add to results array
	types.forEach(t => {
		var settings = {
			"url": `http://localhost:8080/ExpenseReimbursementSystem/rest/ers/all/${t}`,
			"method": "GET",
			"timeout": 0,
		};
		//increase count 
		count++;

		//Get requests, add to results array
		$.ajax(settings).done(function(response) {
			results = results.concat(response);
			//if all results have been processed, sort and print results
			if (count === types.length) {
				//Clean Display area once content can be loaded
				$('#load').remove();
				$('thead.empty');

				//Sort results
				const sorted = results.sort((a, b) => a.id - b.id);
				//Render sorted results in html
				sorted.forEach(element => {
					//Translate status to string
					let status = "Approved";
					if (element.status === 3) {
						status = "Denied";
					}

					$('table').prepend(`
				  <tr data-id = {}>
			      <td>${element.id}</td>
				  <td>${status}</td>
			      <td>$${element.amount}</td>
			      <td>${element.rFor}</td>
			      <td>${(new Date(element.date).toDateString())}</td>
				  </tr>
			      `)
				})
			}
		});
	})
}

//Onclick for pending tab
function getAllEmployees() {
	//Store employee data for quicker searches
	let employees = [];

	//Write search bar, table header to dom
	const hanger = $('#display');
	hanger.empty();
	hanger.append(`<div class="input-group mb-3">
	  	<input type="text" class="form-control" id="searchBar" placeholder="Search">
	  	<button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" id="eSearch">By</button>
	  	<ul class="dropdown-menu dropdown-menu-end">
	    <li><button id="id" class="dropdown-item eSearch">Id</button></li>
	    <li><button id="name" class="dropdown-item eSearch">Name</button></li>
	    <li><button id="email" class="dropdown-item eSearch">Email</button></li>
	  	</ul>
		</div>`)
	hanger.append(`<table class='table'>
   	   <thead><tr> <th> Id # </th>
  	   <th> First Name </th> <th> Last Name </th> <th> Email </th> <th> Role </th></thead> </table>`)

	// Append loading message
	$('table').append('<p id="load">Loading...<p>')

	//JQuery ajax settings
	var settings = {
		"url": `http://localhost:8080/ExpenseReimbursementSystem/rest/employees/roster`,
		"method": "GET",
		"timeout": 0,
	}

	//Execute query
	$.ajax(settings).done(response => {

		//Save response to fn array
		employees = response;

		renderEmployees(response);
	});


	//Tie search fn to search bar
	$(".eSearch").click((e) => {
		const input = $("#searchBar").val();
		console.log(input);
		console.log(employees)
		switch (e.target.id){
			case 'id':
			const result = employees.filter(e => e.id == input);
			renderEmployees(result);
			break;
			case 'name':
			break;
			case 'email':
			break;
		}
	})
}

//Re-usable employee jQuery code
function renderEmployees(employees) {
	
			//Clean Display area once content can be loaded
		$('#load').remove();
		$('thead').empty();

	employees.forEach(element => {
		let role = "Employee";
		if (role == 2) {
			role = "Manager";
		}

		$('table').prepend(`
	  <tr data-attr="${element.id}">
      <td>${element.id}</td>
      <td>${element.firstName}</td>
      <td>${element.lastName}</td>
	  <td>${element.email} </td>
      <td>${role}</td>
	  </tr>
      `)
	})
}
