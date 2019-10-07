window.addEventListener('load', function (e) {
	console.log("doc loaded");
	init();
});


function clear() {
	var dataDiv = document.getElementById('payrollDiv');
	while (dataDiv.firstElementChild) {
		dataDiv.removeChild(dataDiv.firstElementChild);
	}
	
}
function init() {
	clear();
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/payroll', true);

	xhr.onreadystatechange = function () {

		if (xhr.readyState === 4 && xhr.status < 400) {
			var data = JSON.parse(xhr.responseText);
			displayPayroll(data);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
		}
	};

	xhr.send(null);
	postForm.submit.addEventListener('click', addPayroll); 
}
function clearEditButtion() {
	var editbtn = document.getElementById('editButton');
	editBtn.parentNode.removeChild(editBtn);
}
var editButtonClicked = false;
function displayPayroll(payroll) {
	var dataDiv = document.getElementById('payrollDiv');
	var h1 = document.createElement('h1');
	h1.textContent = "Payroll";
	dataDiv.appendChild(h1);
	var table = document.createElement('table');
	table.id = "payrollRecords"
	for (let i = 0; i < payroll.length; i++) {
		var tr = document.createElement('tr');
		var tdWeek = document.createElement('td');
		tdWeek.textContent = "Week: " + payroll[i].week + " Period: " + payroll[i].periodStart + " to " + payroll[i].periodEnd;
		tr.appendChild(tdWeek);
		var tdViewRecordsButton = document.createElement("button");
		tdViewRecordsButton.innerHTML = "Records";
		tdViewRecordsButton.addEventListener('click', function () {
			getRecords(payroll[i].id);
		});
		tr.appendChild(tdViewRecordsButton);
		table.appendChild(tr);
		var editButton = document.createElement("button");
		editButton.innerHTML = "Edit";
		editButton.addEventListener('click', function (e) {
			e.preventDefault();
			
			var editbtn = document.createElement("button");
			editbtn.name = "edit";
			editbtn.type = "submit";
			editbtn.id = "editButton";
			editbtn.innerHTML = "Edit payroll id " + payroll[i].id;
			postForm.appendChild(editbtn);
			postForm.week.value = payroll[i].week;
			postForm.periodStart.value = payroll[i].periodStart;
			postForm.periodEnd.value = payroll[i].periodEnd;
			var id = document.createElement('input');
			id.type = 'hidden';
			id.name = 'prId';
			id.value = payroll[i].id;
			postForm.appendChild(id);
			editbtn.addEventListener('click', editPayroll);
		});
		tr.appendChild(editButton);
		var deleteButton = document.createElement("button");
		deleteButton.innerHTML = "Delete";
		deleteButton.addEventListener('click', function (e) {
			e.preventDefault();
			deleteRecord(payroll[i].id);
		});
		tr.appendChild(deleteButton);
		table.appendChild(tr);
	}
	dataDiv.appendChild(table);
}


var addPayroll = function(e) {
	e.preventDefault();
	var form = e.target.parentElement;
	var payroll = {};
	payroll['week'] = form.week.value;
	payroll['periodStart'] = form.periodStart.value;
	payroll['periodEnd'] = form.periodEnd.value;
	payroll['records'] = [];
	postPayroll(payroll);
}

function postPayroll(payroll) {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/payroll', true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				var data = JSON.parse(xhr.responseText);
				console.log(data);
			} else {
				console.log("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	var userObj = JSON.stringify(payroll);
	xhr.send(userObj);
	location.reload();

}

var editPayroll = function(e) {
	e.preventDefault();
	var form = e.target.parentElement;
	var payroll = {};
	payroll['week'] = form.week.value;
	payroll['periodStart'] = form.periodStart.value;
	payroll['periodEnd'] = form.periodEnd.value;
	putPayroll(payroll, form.prId.value);
}

function putPayroll(payroll, prId) {
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'api/payroll/' + prId, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				var data = JSON.parse(xhr.responseText);
				console.log(data);
				clearEditButton();
			} else {
				console.log("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	var userObj = JSON.stringify(payroll);
	xhr.send(userObj);
	location.reload();
}
function getRecords(prId) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/payroll/' + prId + '/records', true);

	xhr.onreadystatechange = function () {

		if (xhr.readyState === 4 && xhr.status < 400) {
			var data = JSON.parse(xhr.responseText);
			console.log(data);
			displayRecords(data, prId);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
		}
	};
	xhr.send(null);
}

function deleteRecord(prId) {
	var xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'api/payroll/' + prId, true);

	xhr.onreadystatechange = function () {

		if (xhr.readyState === 4 && xhr.status < 400) {
			var data = JSON.parse(xhr.responseText);
			init();
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
		}
	};
	xhr.send(null);
	location.reload();
}

function displayRecords(records, prId) {
	var dataDiv = document.getElementById('payrollDiv');
	clear();
	var table = document.createElement('table');
	var thRow = document.createElement('tr');
	table.appendChild(thRow);
	var thEmp = document.createElement('th');
	thEmp.textContent = "Employee";
	thRow.appendChild(thEmp);
	var thRate = document.createElement('th');
	thRate.textContent = "Hourly rate";
	thRow.appendChild(thRate);
	var thHours = document.createElement('th');
	thHours.textContent = "Hours";
	thRow.appendChild(thHours);
	var thOT = document.createElement('th');
	thOT.textContent = "Overtime";
	thRow.appendChild(thOT);
	var total = document.createElement('th');
	total.textContent = "Total pay";
	thRow.appendChild(total);
	dataDiv.appendChild(table);
	for (let i = 0; i < records.length; i++) {
		var record = document.createElement('tr');
		dataDiv.appendChild(record);
		var empName = document.createElement('td');
		empName.textContent = records[i].employee.name;
		record.appendChild(empName);
		var rate = document.createElement('td');
		rate.textContent = records[i].employee.hourlyRate;
		record.appendChild(rate);
		var hours = document.createElement('td');
		hours.textContent = records[i].hours;
		record.appendChild(hours);
		var overtime = document.createElement('td');
		overtime.textContent = records[i].overtime;
		record.appendChild(overtime);
		var totalPay = document.createElement('td');
		totalPay.textContent = records[i].hours * records[i].employee.hourlyRate + records[i].overtime * 1.5 * records[i].employee.hourlyRate;
		record.appendChild(totalPay);
		table.appendChild(record);
	}
	var btn = document.createElement('button');
	btn.innerHTML = "Go back";
	btn.addEventListener('click', init);
	dataDiv.appendChild(btn);
}
