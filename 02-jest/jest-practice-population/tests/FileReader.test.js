const FileReader = require('../src/FileReader')
const fs = require('fs')
describe('file reader ', () => {
    it('outputs the contents of the file as a string', () => {
        var fr = new FileReader();
        expect(typeof(fr.readFile())).toEqual('string');
    })

    it('reads a file and returns its data as a string', () => {
        var fr = new FileReader();
        var test_string = "AAAAA"
        var filename = "test.txt"
        fs.writeFileSync(filename, test_string)
        expect(fr.readFile(filename)).toEqual(test_string);
        fs.unlinkSync(filename)
    })
})