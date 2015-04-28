var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  //res.send('respond OA Systems updated');
    res.send([{name:"Imran",age:21},{name:"Talha",age:16}]);
});


router.get('/data', function(req, res, next) {
res.send({name:"Test",age:3});
});

router.post('/postdata', function(req, res, next) {
  console.log("name = "+req.body.name);
  console.log("Age = "+req.body.age);
  res.send({name:req.body.name+"_text",age:3});
});
module.exports = router;
