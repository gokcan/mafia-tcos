/**
 * Author: Skylifee7 on 01/05/2017.
 */

var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var PlayerSchema = new Schema({
    name: String
});

module.exports = mongoose.model('Player', PlayerSchema);

