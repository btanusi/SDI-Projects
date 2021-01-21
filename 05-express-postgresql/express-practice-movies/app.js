const fs = require("fs")
const bodyParser = require("body-parser")
const cookieParser = require('cookie-parser')
const express = require('express')
const app = express()
const port = 3001

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use(cookieParser())

let movies = JSON.parse(fs.readFileSync('./movies.JSON'))

// YOUR CODE GOES HERE
app.get('/movies', (req, res) => {
    let title = req.query.title
    if(title){
        let movie = movies.filter(movie => movie.title === title)
        res.send(movie)
    } else{
        res.send(movies)
    }
})

app.get('/movies/:movieId', (req, res) => {
	res.send(movies.filter(movie => movie.movieId == req.params.movieId))
})

app.post('/', (req, res) => {
    // POST user data using the request body 
    let {movieId, title, director, year} = req.body
    if(movieId && title && director && year){
    	movies.push(req.body)
    	res.send("Success")
    } else {
    	res.send("Failed")
    }
})

app.delete('/:movieId', (req, res) => {
	let id = req.params.movieId
	if(id){
		movies = movies.filter(movie => movie.movieId != id)
		res.send("Success")
	} else {
		res.send("Failed")
	}
})


app.get('/setcookie', function(req, res) {
  	res.cookie('firstname', 'Brianna');
  	res.cookie('lastname', 'Tanusi');
  	res.end();
})

app.get('/readcookie', function(req, res){
	if(req.cookies.firstname && req.cookies.lastname){
    	res.send(`${req.cookies.firstname} ${req.cookies.lastname}`)
	}
})


app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))
