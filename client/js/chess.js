xaxis = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
yaxis = [1, 2, 3, 4, 5, 6, 7, 8];
pieces1 = ['rook-1', 'knight-1', 'bishop-1', 'queen', 'king', 'bishop-2', 'knight-2', 'rook-2'];
pieces2 = ['pawn-1', 'pawn-2', 'pawn-3', 'pawn-4', 'pawn-5', 'pawn-6', 'pawn-7', 'pawn-8'];

var createboard = function() {
    var currentTile = {};
    var count = 0;

    for (var i = 0; i < yaxis.length; i++) {
        for (var j = 0; j < xaxis.length; j++) {
            if (count % 8 == 0) {
                $('#chessboard').append('<div class="tile smoothfade" style="clear:left"></div>');
            } else {
                $('#chessboard').append('<div class="tile smoothfade"></div>');
            }

            $('.tile').eq(count).attr('data-gridpos', (xaxis[j] + yaxis[yaxis.length - (i + 1)]));

            if (((i % 2 == 0) && (j % 2 != 0)) || ((i % 2 != 0) && (j % 2 == 0))) {
                $('.tile').eq(count).addClass('dark');
            } else {
                $('.tile').eq(count).addClass('light');
            }

            count++;
        }
    }
}

var placepieces = function() {
    for (var i = 0; i < yaxis.length; i++) {
        for (var j = 0; j < xaxis.length; j++) {
            var currentTile = $('.tile[data-gridpos="' + xaxis[j] + yaxis[i] + '"]');
            var currentPiece = '';

            switch (yaxis[i]) {
                case 1:
                    // Set the pieces for the base row on the white side.
                    currentPiece = $('.white-' + pieces1[j]);
                    break;
                case 2:
                    // Set the pieces for the second row on the white side.
                    currentPiece = $('.white-' + pieces2[j]);
                    break;
                case 7:
                    // Set the pieces for the second row on the black side.
                    currentPiece = $('.black-' + pieces2[j]);
                    break;
                case 8:
                    // Set the pieces for the base row on the black side.
                    currentPiece = $('.black-' + pieces1[j]);
                    break;
                default:
                    currentPiece = '';
                    break;
            }

            if (currentPiece.length > 0) {
                currentPiece.css({
                    'top': currentTile.position().top + (currentTile.width() / 2) - (0.5 * currentPiece.width()),
                    'left': currentTile.position().left + (currentTile.width() / 2) - (0.5 * currentPiece.width())
                });
            }
        }
    }
}

$(document).ready(function() {
    createboard();
    placepieces();
    tiles = $('.tile');

    $('.piece').on('click', function() {
        $('.piece').attr('id', '');

        if ($(this).attr('id') == 'player') {
            $(this).attr('id', '');
        } else {
            $(this).attr('id', 'player');
        }
    });

    $('.tile').on('click', function() {
        current = $(this);

        var midX = $(this).position().left += ($(this).width() / 2)
        var midY = $(this).position().top += ($(this).width() / 2);

        var player = $('#player');

        player.css({
            'top': midY - (0.5 * player.width()),
            'left': midX - (0.5 * player.width())
        });

        player.attr('id', '');
    })

    .on('mouseenter', function(){
		$(this).addClass('hover');
	})

	.on('mouseleave', function(){
		$(this).removeClass('hover');
	});
});
