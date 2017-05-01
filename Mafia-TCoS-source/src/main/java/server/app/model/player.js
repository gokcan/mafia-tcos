var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var PlayerSchema  = new Schema({
    name: String,
    password: String,
    admin: Boolean
});

module.exports = mongoose.model('Player', PlayerSchema);
