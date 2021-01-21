const express = require('express')
const app = express()
var cookieParser = require('cookie-parser')

app.use(cookieParser())

app.get('/login', function(req, res) {
	console.log('Cookies: ', req.cookies)

    let name = req.query.name
    if(name){
	  res.cookie('name', name);
	  res.end();
    }
})

app.get('/hello', function(req, res){
	if(req.cookies.name){
    	res.send(`Hello ${req.cookies.name}!`)
	}
})


const port = 3000
app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))