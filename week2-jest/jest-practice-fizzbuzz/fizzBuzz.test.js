const fizzBuzz = require('./fizzBuzz')

describe('fizzBuzz??', () => {
    it('is a function', () => {
        expect(typeof fizzBuzz).toEqual('function')
    })

    it('outputs an array', () => {
        expect(Array.isArray(fizzBuzz())).toEqual(true)
    })

    it('prints out an array of strings', () => {
        let actualResult = fizzBuzz();
        actualResult.forEach(element => 
            expect(typeof(element).toEqual('string'))
        )
    })

})