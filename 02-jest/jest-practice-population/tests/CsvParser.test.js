const CsvParser = require("../src/CsvParser")

describe('csv parser ', () => {
    var csvParser = new CsvParser();
    it('returns an empty array if string is empty', () => {
        expect(csvParser.readLines('')).toEqual([]);
    })

    it('returns an array of objects with the contents of the csv file', () => {
        var csvContents = "a,b,c\n1,2,3\n11,22,33"
        var expected = [{a: '1', b: '2', c: '3'}, {a: '11', b: '22', c: '33'}]
        expect(csvParser.readLines(csvContents)).toEqual(expected)
    })


})