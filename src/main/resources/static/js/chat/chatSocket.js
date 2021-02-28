socket = new WebSocket("ws://127.0.0.1:6501/chat")

var id=-1;
var DEBUG = true;
socket.onopen = () => {
	if(DEBUG==true){ console.log('[DEBUG] WebSocket connection opened.'); }

    // In case JOIN message from server failed, we force it
	/*
    if (typeof game.global.myPlayer.id == 'undefined') {
	        console.log("[DEBUG] Forcing joining server...");
	    let message = {
	        event: 'JOIN'
	    }
	    socket.send(JSON.stringify(message))
	}*/
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
	    case 'NEW ROOM' :
			newRoomMsg(msg)
	        break
	    case 'GAME STATE UPDATE' :
			updateMsg(msg)
	        break
	    case 'REMOVE PLAYER' :
			removePlayerMsg(msg)
	        break
		case 'CLIENT MESSAGE' :
			updateChat(msg)
			break
		case 'CHAT JOIN':
			playerJoined(msg)
			break
	    default :
	        console.dir(msg)
	}
}

function submitFunc()
{
	var textValue = document.getElementById("textArea").value;
	if(textValue == '') return;
	var playerText = "user " + id + ": "+ textValue;
	document.getElementById("textArea").value = '';

	let message = {
		event: 'CHAT MESSAGE',
		text: playerText
	}
	socket.send(JSON.stringify(message))
}

// WEBSOCKET MESSAGES PROTOCOL:
	
function joinMsg(msg) {
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

function newRoomMsg(msg) {
	if(DEBUG==true){
    	console.log('[DEBUG] NEW ROOM message recieved')
    	console.dir(msg)
	}
} //end newRoomMsg

function updateMsg (msg) {
	if(DEBUG==true){
    	console.log('[DEBUG] GAME STATE UPDATE message recieved')
    	console.dir(msg)
	}
} // end updateMsg

function removePlayerMsg(msg) {
    console.log('[DEBUG] REMOVE PLAYER message recieved')
    console.dir(msg.players)

	var tag = document.createElement("i");
	var playerText = "user " + msg.id + " left the chat"
   	var text = document.createTextNode(playerText);
   	tag.appendChild(text);
   	var element = document.getElementById("chatMessages");
   	element.appendChild(tag); 

	tag = document.createElement("br");
	element.appendChild(tag); 
	tag = document.createElement("br");
	element.appendChild(tag); 
} // end removePlayerMsg

function updateChat(msg){
	if(DEBUG==true){
    	console.log('[DEBUG] CHAT MESSAGE recieved')
    	console.dir(msg)
	}

	var tag = document.createElement("p");
   	var text = document.createTextNode(msg.text);
   	tag.appendChild(text);
   	var element = document.getElementById("chatMessages");
   	element.appendChild(tag); 
}



function playerJoined(msg){
	if(DEBUG==true){
    	console.log('[DEBUG] CHAT MESSAGE recieved')
    	console.dir(msg)
	}

	var tag = document.createElement("i");
	var playerText = "user " + msg.id + " joined the chat"
   	var text = document.createTextNode(playerText);
   	tag.appendChild(text);
   	var element = document.getElementById("chatMessages");
   	element.appendChild(tag); 

	tag = document.createElement("br");
	element.appendChild(tag); 
	tag = document.createElement("br");
	element.appendChild(tag);
}