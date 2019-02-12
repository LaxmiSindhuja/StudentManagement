/**
 * 
 */
function userln() {
	var res = new XMLHttpRequest();
	res.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {
			var data = JSON.parse(res.responseText);
			/*
			 * document.getElementById("iid")=data["id"];
			 * document.getElementById("iname")=data["name"];
			 */
			var studentInfoRow = document.createElement("tr");
			var studentInfoNo = document.createElement("td");
			var studentInfoName = document.createElement("td");
			studentInfoNo.innerHTML = data["id"];
			studentInfoName.innerHTML = data["name"];
			studentInfoRow.appendChild(studentInfoNo);
			studentInfoRow.appendChild(studentInfoName);
			studentInfo.appendChild(studentInfoRow);
		}

	};
	var x = document.getElementById("demoid").value;
	var id = Number(x);
	res.open('GET', 'http://localhost:8899/getStudentById/' + id, true);
	res.send();
}
function addln() {
	var studentName = document.getElementById("studentName");
	var studentRollNo = document.getElementById("studentId");
	var studentObject = {
		"id" : studentRollNo.value,
		"name" : studentName.value
	}
	var res = new XMLHttpRequest();

	res.onreadystatechange = function() {
		if (res.readyState === 4) {
			if (res.status === 200) {
				alert("Student successfully registered");
			} else {
				alert("Failed while registering student");
			}
		}
	}

	res.open('POST', 'http://localhost:8899/addstudent', false);
	res.setRequestHeader("Content-Type", "application/json");
	res.send(JSON.stringify(studentObject));

}
function deleteln() {
	var res = new XMLHttpRequest();
	var x = document.getElementById("demoid").value;
	var id = Number(x);
	res.open('DELETE', 'http://localhost:8899/deletestudent/' + id, true);
	res.send();

}
function updateln() {
	var x = document.getElementById("demoid").value;
	var id = Number(x);
	var res = new XMLHttpRequest();
	res.open('PUT', 'http://localhost:8899/updatestudent/' + id, true);
	res.send();

}
function allln() {
	var studentInfo = document.getElementById("studentInfo")
	studentInfo.innerHTML = "";
	var request = new XMLHttpRequest();
	request.open('GET', 'http://localhost:8899/getAllStudent', true);
	request.send();
	request.onreadystatechange = function() {

		if (request.readyState == 4 && request.status == 200) {
			// var xmlDocument= request.responseXML;
			var data = JSON.parse(request.responseText);

			for (var i = 0; i < data.length; i++) {
				var name1 = data[i].name;
				var no = data[i].id;
				var studentInfoRow = document.createElement("tr");
				var studentInfoNo = document.createElement("td");
				var studentInfoName = document.createElement("td");
				studentInfoNo.innerHTML = no;
				studentInfoName.innerHTML = name1;
				studentInfoRow.appendChild(studentInfoNo);
				studentInfoRow.appendChild(studentInfoName);
				studentInfo.appendChild(studentInfoRow);
				console.log(data[i].name);
			}

		}

	};

}
