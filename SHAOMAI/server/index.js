var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');
const e = require('express');


//app.use(bodyParser.json()); This line is error
app.use(bodyParser.urlencoded({ extended: true}));

app.get('/', function(req, res){
    return res.send({error: true, message: 'Test API'})
});

var conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'shaomai'
});

conn.connect();

//SHOW PRODUCT
app.get('/allproduct', function(req, res){
    conn.query('SELECT * FROM product ORDER BY product_price_rent', function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});


//SHOW CUSTOMER
app.get('/allcus', function(req, res){
    conn.query('SELECT * FROM customer', function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});



//REGISTER
app.post('/register', function(req, res){
    var register = req.body
    if(!register){
        return res.status(400).send({error: true, message: 'Please Add customer'});
    }

    conn.query('INSERT INTO customer SET ?', register, function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});


//LOGIN
app.post('/login', function(req, res){
    var email = req.body.customer_mail;
    var password = req.body.customer_pass1;
    if(email && password){
        conn.query('SELECT * FROM customer WHERE customer_mail = ? AND customer_pass1 = ?', [email, password], function (error, results, fields){
            if(results.length > 0){
               res.end(JSON.stringify(results[0]))
            }else{
                res.send("Incorrect Username and/or Password");
            }
        });
    };
   



});


//SEARCH PRODUCT

app.get('/product/:product_name', function(req, res){
    var product_name = req.params.product_name

    if(!product_name){
        return res.status(400).send({error: true, message: 'Add product '});
    }
    conn.query('SELECT * FROM product WHERE product_name = ?', product_name, function(error, results, fields){
        if(error) throw error;
        if(results[0]){
            return res.send(results[0]);
        }else{
            return res.status(400).send({error: true, message: 'Mai mee product'});
        }
    });

});



//




app.listen(3000, function(){
    console.log("Kum rung run bon port 3000 na " );
});

module.exports = app;