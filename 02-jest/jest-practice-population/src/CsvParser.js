class CsvParser {
    readLines(lines) {
        try{
            var linesArr = lines.replace('\r', '').split('\n')
            var keysArr = linesArr[0].split(',')
            return linesArr.slice(1).map(line => {
                var valsArr = line.split(',')
                var newObj = {}
                for(var j = 0; j < valsArr.length; j++){
                    newObj[keysArr[j]] = valsArr[j]
                }
                return newObj
            })
        } catch {
            return [];
        }
    }
}
module.exports = CsvParser