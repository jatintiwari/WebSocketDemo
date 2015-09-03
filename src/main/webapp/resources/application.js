var stompClient = null;
var whoAmI= null;

function setConnected(connected) {
	document.getElementById('connect').disabled = connected;
	document.getElementById('disconnect').disabled = !connected;
	document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
	document.getElementById('response').innerHTML = '';
}

function connect() {
	
	if(!localStorage.getItem("stompClient")){
		console.log("we have saved an instance");
		var socket = new SockJS('/spring-mvc-websockets/hello');
		var user = $('#username').val();
		stompClient = Stomp.over(socket);
		localStorage.setItem("stompClient", JSON.stringify(stompClient));
	}else{
		console.log("this is a local instance");
		stompClient = JSON.parse(localStorage.getItem("stompClient"));
	}
	stompClient.connect({}, function(frame)  {
		whoAmI = frame.headers['user-name'];
		setConnected(true);
		console.log(whoAmI);
		stompClient.subscribe('/user/queue/greetings', function(greeting){
			console.log(greeting);
			showGreeting(JSON.parse(greeting.body).content);
		});
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
}
function startListening(){
		stompClient.subscribe('/topic/greetings', function(greeting){
			showGreeting(greeting.body);
		});	
}


function sendName() {
	var name = document.getElementById('name').value;
	stompClient.send("/hello",{}, JSON.stringify({ 'name': name}));
}

function showGreeting(message) {
	var response = document.getElementById('response');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.appendChild(document.createTextNode(message));
	response.appendChild(p);
}
