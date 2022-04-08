var stompClient = null;



function setConnected(connected) {
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/our-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/topic/greetings', function (greeting) {
            console.log(greeting);
            showGreeting(JSON.parse(greeting.body).numbers);
        });
    });
}

function sendName(message) {
    random =  Math.floor(Math.random() * (100 - 10 + 1) + 10);
    stompClient.send("/ws/hello", {}, JSON.stringify({'name': message,'random':random}));
}

function showGreeting(message) {
    for (let i = 0;i<message.length;i++) {
        $("#greetings").append("<tr><td>" + message[i] + "</td></tr>");
    }
}

$(document).ready(function() {
    connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#auto" ).click(function() { sendName("auto"); });
    $( "#generate" ).click(function() { sendName("generate"); });
});

