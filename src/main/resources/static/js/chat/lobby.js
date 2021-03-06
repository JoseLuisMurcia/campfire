const socket = new WebSocket("ws://127.0.0.1:6501/chat")

const DEBUG = true;

socket.onopen = () => {
	if(DEBUG==true){ console.log('[DEBUG] LOBBY WebSocket connection opened.'); }

    let message = {
		event: 'LOBBY JOIN'
	}
	socket.send(JSON.stringify(message))
}

socket.onclose = () => {
	if(DEBUG==true){console.log('[DEBUG] LOBBY WebSocket connection closed.');}
}

socket.onmessage = (message) => {
	var msg = JSON.parse(message.data)

	switch (msg.event) {
        case "ROOM CREATED":
            roomCreated(msg);
            break;
        case "ROOM DELETED":
            roomDeleted(msg);
            break;
	}
}

function createRoom(){
    var chatName = document.getElementById("textArea").value;
	if(chatName == '') return;

    document.getElementById("textArea").value = '';
	let message = {
		event: 'ROOM CREATION',
		text: chatName
	}
	socket.send(JSON.stringify(message))
}

function roomCreated(msg){
    var link = document.createTextNode(msg.text);
    var a = document.createElement('a');
    a.appendChild(link);
    a.title = msg.text;
    a.href = "chat_room/" + msg.chatID;
    document.getElementById("roomList").appendChild(a);
	var br = document.createElement('br');
    document.getElementById("roomList").appendChild(br);
}

