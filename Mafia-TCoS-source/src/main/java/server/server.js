/**
 * Author: Skylifee7 on 01/05/2017.
 */


'use strict'

const express = require('express')
const app = express();
const bodyParser = require('body-parser')

var mongoose = require('mongoose');
mongoose.connect('mongodb-remote-server-uri');

var Player = require('./app/model/player');

app.set('port', (process.env.PORT || 5000))
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

var router = express.Router();

router.use(function(req,res,next){
    console.log('It works...')
    next();
})

router.get('/', function(req,res){
    res.json({message: 'Hoorayyy! Welcome to Mafia:TCoS REST API!'});
});


router.route('/players')

    .post(function(req,res){

        var player = new Player();
        player.name = req.body.name;

        player.save(function(err){

            if(err)res.send(err);

            res.json({message: 'Player instance has been created!'});

        });


    })

    .get(function(req,res){

        Player.find(function(err, players) {
            if (err)
                res.send(err);

            res.json(players);
        });


    });


router.route('/players/:player_uid')

    .get(function(req,res){
        Player.findById(req.params.player_uid, function(err, player){

            if(err) res.send(err);

            res.json(player);

        });
    })

    .put(function(req,res){

        Player.findById(req.params.player_uid, function(err, player) {

            if (err) res.send(err);

            player.name = req.body.name;

            player.save(function(err){
                if (err) res.send(err);

                res.json({message: " Player has been updated!"});

            });

        });
    })

    .delete(function(req, res) {

        Player.remove({
            _id: req.params.player_uid
        }, function(err, player) {
            if (err)
                res.send(err);

            res.json({ message: 'Successfully deleted' });
        });
    });


app.use('/api', router);

app.listen(app.get('port'), function() {
    console.log("running: port")

});