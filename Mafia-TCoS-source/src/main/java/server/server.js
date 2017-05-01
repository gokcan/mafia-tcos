'use strict'
/*
 Author: Skylifee7 , check the @github repo for incoming commits.
 Node.js RESTful Web Service for processing requests of the
 Mafia:TCoS' game client.
 */

const express = require('express')
const app = express();
const bodyParser = require('body-parser')
const morgan = require('morgan')

const jwt = require('jsonwebtoken')
var config = require('./config')

const mongoose = require('mongoose');
mongoose.connect(config.database);
var Player = require('./app/models/player');

app.set('port', (process.env.PORT || 5000))
app.set('SecretServerKey', config.secret);

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.use(morgan('dev-log'));

var router = express.Router();

router.use(function (req, res, next) {
    console.log('Something is happenning...')

    next();

})

/*
 The /players and /players/:player_uid routes are protected. Therefore, Client needs to pass
 JWT token within Authentication header or in a request's body to access&change player details.
 */

router.use('/players', function (req, res, next) {

    var token = req.body.token || req.query.token || req.headers['x-access-token'];

    if (token) {
        jwt.verify(token, app.get('SecretServerKey'), function (err, decoded) {

            if (err) {
                return res.json({success: false, message: 'Failed to authenticate token.'});
            }
            else {
                req.decoded = decoded;
                next();
            }
        })
    }

    else {
        /*
         If there is no token provided in the request, we need to return 401 Error to the Client.
         */
        return res.status(401).send({
            success: false,
            message: 'No token provided.'
        });

    }
})

router.get('/', function (req, res) {
    res.json({message: 'Hoorayyy! Welcome to Mafia:TCoS REST API!'});
});

router.route('/signup')
    .post(function (req, res) {

        Player.findOne({
            name: req.body.name

        }, function (err, player) {

            if (err) throw err;

            if (!player) {

                var player = new Player();

                player.name = req.body.name;
                player.password = req.body.password;

                player.save(function (err) {

                    if (err) res.send(err);

                    res.json({message: 'Player instance has been created!'});
                });
            }
            else if(player) res.json({message: 'Player name exists, choose another name.'});
        })

    });

router.route('/authenticate')
    .post(function (req, res) {

        Player.findOne({
            name: req.body.name
        }, function (err, player) {

            if (err) throw err;

            if (!player) {
                res.json({
                    success: false,
                    message: "Authentication failed, User not found."
                })
            }
            else if (player) {
                if (player.password != req.body.password) {
                    res.json({
                        success: false,
                        message: "Authentication failed, Password is wrong."
                    })
                }
                else {

                    var token = jwt.sign(player, app.get('SecretServerKey'), {
                        expiresIn: 60 * 60 * 60 // seconds
                    });

                    res.json({
                        success: true,
                        message: 'Token is passed to the client.',
                        token: token
                    })
                }
            }

        })

    });

router.route('/players')

    .get(function (req, res) {
        Player.find(function (err, players) {

            if (err) res.send(err);

            res.json(players);
        });

    });

router.route('/players/:player_uid')

    .get(function (req, res) {
        Player.findById(req.params.player_uid, function (err, player) {

            if (err) res.send(err);

            res.json(player);

        });
    })

    .put(function (req, res) {
        Player.findById(req.params.player_uid, function (err, player) {

            if (err) {
                res.send(err);
            }

            player.name = req.body.name;
            player.password = req.body.password;

            player.save(function (err) {

                if (err) {

                    res.send(err);
                }
                res.json({message: " Player has been updated!"});

            });

        });
    })

    .delete(function (req, res) {
        Player.remove({
            _id: req.params.player_uid}, function (err, player) {

            if (err) {
                res.send(err);

            }
            res.json({message: ' Player has been deleted!'});

        });
    });


app.use('/api', router);

app.listen(app.get('port'), function () {
    console.log("Running at port:" + app.get('port'));

});