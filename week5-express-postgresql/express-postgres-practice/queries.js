const Pool = require('pg').Pool
const pool = new Pool({
  user: 'me',
  host: 'localhost',
  database: 'students',
  password: 'password',
  port: 5432,
})

const getStudents = (request, response) => {
  pool.query('SELECT * FROM students', (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}


const getStudentByStudentId = (request, response) => {
  const id = parseInt(request.params.studentId)

  pool.query('SELECT * FROM students WHERE studentId = $1', [id], (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}


const registerStudent = (request, response) => {
  const { name } = request.body

  pool.query('INSERT INTO students (name) VALUES ($1)', [name], (error, results) => {
    if (error) {
      throw error
    }
    response.status(201)
  })
}


const addGrade = (request, response) => {
  const id = parseInt(request.params.studentId)
  const { className, grade } = request.body

  pool.query(
    'UPDATE students SET history = $1 WHERE studentId = $2',
    [grade, id],
    (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`User modified with ID: ${id}`)
    }
  )
}


module.exports = {
                  getStudents,
                  getStudentByStudentId,
                  registerStudent,
                  addGrade,
                 }
