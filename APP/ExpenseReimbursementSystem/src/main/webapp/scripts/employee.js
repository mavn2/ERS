//Fill pending table at initial load
getPendingRequests();

//Assign functions to tabs after page load
$('#pendingTab').click(getPendingRequests);
$('#approvedTab').click(getApprovedRequests);
$('#deniedTab').click(getDeniedRequests);

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
      <td>${element.id}</td>
      <td>${element.amount}</td>
      <td>${element.rFor}</td>
      <td>${(new Date(element.date).toDateString())}</td>
      `)
		});
	});
}
