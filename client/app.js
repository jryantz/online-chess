var express = require('express'),
    app = express(),
    server = require('http').createServer(app),
    io = require('socket.io').listen(server);

server.listen(8080);

app.use(express.static(__dirname + '/'));

app.get('/', function() {
    res.sendFile(__dirname + '/index.html');
    res.sendFile(__dirname + '/css/app.css')
});

console.log('Serving files.');

var net = require('net'),
    client = new net.Socket();

client.connect(4000, '10.0.2.2', function() {
    client.setNoDelay(true);
    console.log('Connected to chess server.');

    io.sockets.on('connection', function(socket) {
        console.log('Connected to browser.');

        socket.on('input', function(data) {
            var message = data.message;
            console.log('Input from browser: (' + message + ').');

            //io.sockets.emit('output', [data]);
            client.write(message + '\n');
        });
    });
});

client.on('data', function(data) {
    console.log('Received: ' + data);
    client.destroy();
});

client.on('close', function() {
   console.log('Connection closed.');
});

// io.sockets.on('connection', function(socket) {
//     socket.on('input', function(data) {
//         var message = data.message;
//
//         io.sockets.emit('output', [data]);
//     });
// })
