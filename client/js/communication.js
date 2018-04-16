// Communicates directly with the nodejs server.
(function() {
    var getNode = function(s) {
        return document.querySelector(s);
    },
        log = getNode('.log'),
        textarea = getNode('textarea');

    try {
        var socket = io.connect('http://192.168.56.101:8080');
    } catch(e) {
        console.log('Error, no connection.');
    }

    if(socket !== undefined) {
        // Listen for a received communication.
        socket.on('output', function(data) {
            console.log(data.message);
        });

        textarea.addEventListener('keydown', function(event) {
            var self = this;

            if(event.which === 13 && event.shiftKey === false) {
                socket.emit('input', {
                    message: self.value
                });

                event.preventDefault();
            }
        });
    }
})();
