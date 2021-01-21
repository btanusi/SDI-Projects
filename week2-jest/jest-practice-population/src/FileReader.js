const fs = require('fs')

class FileReader{
    readFile(filename){
        try {
            return fs.readFileSync(filename).toString()
        } catch {
            return ''
        }
    }
}
module.exports = FileReader