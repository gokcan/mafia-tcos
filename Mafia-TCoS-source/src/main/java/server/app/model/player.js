var mongoose     = require('mongoose');
var bcrypt      =  require('bcrypt');
var Schema       = mongoose.Schema;

var PlayerSchema  = new Schema({
    name: String,
    password: String,
    health: Number,
    money: Number,
    experience: Number,
    inPrison: Boolean,
    admin: Boolean
});

/*
 * Generate salt for the hash-function. Then hash the password with that salt.
 */

PlayerSchema.pre('save', function (next) {
    var player = this;
    if (this.isModified('password') || this.isNew) {
        bcrypt.genSalt(5, function (err, salt) {
            if (err) {
                return next(err);
            }
            bcrypt.hash(player.password, salt, function (err, hash) {
                if (err) {
                    return next(err);
                }
                player.password = hash;
                next();
            });
        });
    } else {
        return next();
    }
});

PlayerSchema.methods.comparePassword= function (passw, callbq) {
    bcrypt.compare(passw, this.password, function (err, isMatch) {
        if (err) {
            return callbq(err);
        }
        callbq(null, isMatch);
    });
};

module.exports = mongoose.model('Player', PlayerSchema);
