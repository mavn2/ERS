//Fill pending table at intial load
getPendingRequests();

//On click/change content fns for tabs
$('#pendingTab').click(getPendingRequests);
$('#resolvedTab').click(() => getResolvedRequests(2, 3));
//$('#employeeTab').click(getAllEmployees);

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
		let action = `<button type="button" class="btn btn-success" onclick="approveRequest()"><i class="bi bi-check-circle-fill"></i> </button> / <button type="button" class="btn btn-danger" onclick="denyRequest()"><i class="bi bi-x-circle-fill"></i></button>`;
		if(id === element.applyId){
			action ='N/A';
		}
			$('table').prepend(`
	  <tr>
      <td>${element.id}</td>
      <td>$${element.amount}</td>
      <td>${element.rFor}</td>
	  <td>${element.applyId} </td>
      <td>${(new Date(element.date).toDateString())}</td>
	  <td>${action}.</td>
	  </tr>
      `)
		});
	});
}

//Functions for buttons
function approveRequest(){
	console.log('yes');
}
function denyRequest(){
	console.log('no');
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
					if (element.status === 3){
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
