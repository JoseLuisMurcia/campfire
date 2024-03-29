const socket = new WebSocket("wss://127.0.0.1:443/chat");
let roomId;

var id=-1;
const DEBUG = true;
let chatMessagesDiv;

socket.onopen = () => {
	if(DEBUG==true){ console.log('[DEBUG] WebSocket connection opened.'); }
	roomId = document.getElementById("roomid").innerText;
	let message = {
		event: 'SET PLAYER ROOM ID',
		roomID: roomId
	}
	socket.send(JSON.stringify(message))
	chatMessagesDiv = document.getElementById("chatMessages");
}

socket.onclose = () => {
	if(DEBUG==true){console.log('[DEBUG] WebSocket connection closed.');}
}

socket.onmessage = (message) => {
	var msg = JSON.parse(message.data)
	switch (msg.event) {
	    case 'JOIN':
	        joinMsg(msg)
	        break
		case 'CLIENT MESSAGE' :
			updateChat(msg)
			break
		case 'CHAT JOIN':
			playerJoined(msg)
			break
		case 'REMOVE PLAYER':
			removePlayerMsg(msg)
			break
	    default :
	        console.dir(msg)
	}
}

function submitFunc()
{
	let textValue = document.getElementById("textArea").value;
	if(textValue == '') return;
	let playerText = textValue;
	document.getElementById("textArea").value = '';
	let message = {
		event: 'CHAT MESSAGE',
		text: playerText
	}
	socket.send(JSON.stringify(message))
}

// WEBSOCKET MESSAGES PROTOCOL:
	
function joinMsg(msg) { //Sent when you join the chat
    if(DEBUG==true){
		console.log('[DEBUG] JOIN message recieved');
    	console.dir(msg);
	}
	id = msg.id;
	let message = {
		event: 'PLAYER JOINED'
	}
	socket.send(JSON.stringify(message))
} // end joinMsg


function removePlayerMsg(msg) { 
    console.log('[DEBUG] REMOVE PLAYER message recieved')
    console.dir(msg.players)

	var tag = document.createElement("p");
	var playerName = msg.exitinguser;
	var playerText = playerName + " left the chat"
   	var text = document.createTextNode(playerText);
   	tag.appendChild(text);
   	chatMessagesDiv.appendChild(tag); 
} // end removePlayerMsg

function updateChat(msg){ //appends the message and the user who sent it
	if(DEBUG==true){
    	console.log('[DEBUG] CHAT MESSAGE recieved')
    	console.dir(msg)
	}

	var tag = document.createElement("p");
   	var text = document.createTextNode(msg.text);
   	tag.appendChild(text);
	   
	if(msg.isFriend == 'self'){
		tag.style.color = "#0000FF";
	}
	if(msg.isFriend == 'friend'){
		tag.style.color = "#FF0000";
	}
	chatMessagesDiv.appendChild(tag); 
}



function playerJoined(msg){ //Called when a user joins the chat, updates the chat
	if(DEBUG==true){
    	console.log('[DEBUG] CHAT MESSAGE recieved')
    	console.dir(msg)
	}

	var tag = document.createElement("p");
	var playerName = msg.enteringuser;
	var playerText = playerName + " joined the chat"
   	var text = document.createTextNode(playerText);
   	tag.appendChild(text);
   	chatMessagesDiv.appendChild(tag); 
}