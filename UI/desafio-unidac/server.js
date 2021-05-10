const express = require('express');
const app = express();

app.use(express.static(__dirname + '/dist/desafio-unidac'));

app.get('/*', function(req, resp) {
    resp.sendFile(__dirname + '/dist/desafio-unidac/index.html');
});

app.listen(process.env.PORT || 4200);