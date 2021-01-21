// Will Jantscher, Ada Lazuli, Brianna Tanusi
// https://github.com/willjantscher/USAF-sdi-databases-checkpoint.git
// database name:  sdi_databases_checkpoint

// npm install
// npm i express pg

// CREATE TABLE emails (id serial PRIMARY KEY, sender VARCHAR(320), recipient VARCHAR(320), subject TEXT, message TEXT, date TEXT); 



const fs = require("fs")
const bodyParser = require("body-parser")

const express = require('express')
const app = express()
const port = 3000
const db = require('./queries')


app.use(bodyParser.json())

// const emails = JSON.parse(fs.readFileSync("emails.JSON"))
// app.get('/', (req, res) => {
//     res.status(200).send("hello world!")
// }
// get all emails in local db in JSON format
app.get('/emails', db.getEmails)
// add email with details in body in JSON format
app.post('/send', db.sendEmail)
// get email with specific id
app.get('/emails/:id', db.getEmailById)
// get email with subject containing query
app.get('/search', db.searchEmails)



app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))