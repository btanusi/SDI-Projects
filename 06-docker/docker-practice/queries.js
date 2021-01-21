const Pool = require('pg').Pool
const pool = new Pool({
//   user: 'me',
  host: 'localhost',
  database: 'sdi_databases_checkpoint',
//   password: 'password',
  port: 5432,
})




const getEmails = (req,res) => {
    pool.query('SELECT * FROM emails ORDER BY id', (err, results) => {
        if(err){
            throw err;
        }
        res.status(200).json(results.rows)
    })
}

const sendEmail = ( req, res) => {
        req.body.forEach( element => {
            let { sender, recipient, subject, message, date } = element
            pool.query('INSERT INTO emails (sender, recipient, subject, message, date) VALUES ($1, $2, $3, $4, $5)', [sender, recipient, subject, message, date], function (err, results) {
                if (err){
                    throw err;
                }
            })
        });
    res.status(201).send(`Emails added!`)
    // console.log(req.body);
}

const getEmailById = ( req, res ) => {
    const id = parseInt(req.params.id)
    pool.query('SELECT * FROM emails WHERE id = $1', [id], (err, results) => {
        console.log(`The id being passed in is ${id}`);
        if (err) {
            throw err
        }
        res.status(200).json(results.rows)
    })
}

// need to fix the LIKE querry to get working
const searchEmails = ( req, res) => {
    const query = decodeURIComponent(req.query.query)
    console.log(query)
    pool.query('SELECT * FROM emails WHERE subject LIKE $1', ['%' + query + '%'], function(err, result ) {
       if(err){
           throw err;
       } 
       if(result.rows.length === 0){
        res.send("Result not found")
        } else {
        res.status(200).json(result.rows)
    }
    })   
}

//SELECT * FROM `my_table` WHERE CONTAINS(name, 'search')


// app.get('/search',(req,res) => {
//     const query = decodeURIComponent(req.query.query)
//     const filteredEmails = emails.filter(email => email.subject.includes(query))
    
//     res.send(filteredEmails)
// });






module.exports = {
    getEmails, sendEmail, getEmailById, searchEmails,
  }