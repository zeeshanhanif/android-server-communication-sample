var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  //res.send('respond OA Systems updated');
    res.send([{name:"Imran",age:21},{name:"Talha",age:16}]);
});

module.exports = router;
