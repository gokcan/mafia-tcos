var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var CrimeSchema  = new Schema({
    description: String,
    difficulty: Number
});

module.exports = mongoose.model('Crime', CrimeSchema);
