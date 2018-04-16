var express = require('express'),
    app = express(),
    server = require('http').createServer(app),
    io = require('socket.io').listen(server);

// Set server to listen to a port for serving website files.
server.listen(8080);

// Set a static variable for the directory of the files.
app.use(express.static(__dirname + '/'));

// Send the files that to display the webpage.
app.get('/', function() {
    res.sendFile(__dirname + '/index.html');
    res.sendFile(__dirname + '/css/app.css')
});

console.log('Serving files.');

// Declare the TCP protocol and create a TCP socket.
var net = require('net'),
    client = new net.Socket();

// Connect to the Java Chess Server.
client.connect(4000, '10.0.2.2', function() {
    // Set no delay on to allow instant packet transfer.
    client.setNoDelay(true);
    console.log('Connected to chess server.');

    // Connect to the browser.
    io.sockets.on('connection', function(socket) {
        console.log('Connected to browser.');

        // When a browser sends data, read it and forward it to the Java Chess Server.
        socket.on('input', function(data) {
            var message = data.message;
            console.log('Input from browser: [' + message + '].');

            //io.sockets.emit('output', [data]);
            client.write(message + '\n');
        });
    });
});

// If there is an error establishing a connection to the Java Chess Server, fail out.
client.on('error', function(error) {
    console.log('Error, could not connect to chess server.');
    process.exit();
});

// If the Java Chess Server sends data back, handle that.
client.on('data', function(data) {
    console.log('Received: ' + data);
    client.destroy();
});

// Closes the connection to the Java Chess Server.
client.on('close', function() {
   console.log('Connection closed.');
   process.exit();
});
