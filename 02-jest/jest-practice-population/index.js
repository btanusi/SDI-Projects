const CsvParser = require("./src/CsvParser")
const FileReader = require("./src/FileReader")

const PopulationCounter = require('./src/PopulationCounter')
filename = process.argv[2]
pc = new PopulationCounter(new FileReader(), new CsvParser())
console.log(pc.count(filename))
