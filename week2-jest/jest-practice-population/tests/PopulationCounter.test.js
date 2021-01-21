const PopulationCounter = require('../src/PopulationCounter')

describe('The populatron', () => {
    var mockReadFile = jest.fn(filename=>{return "Country,City,AccentCity,Region,Population,Latitude,Longitude\nad,aixas,Aix�s,06,7,42.4833333,1.4666667\nad,aixirivali,Aixirivali,06,7,42.4666667,1.5\nad,aixirivall,Aixirivall,06,7,42.4666667,1.5"});
    var mockReadLine = jest.fn(string=>{return [{Population:'7'},{Population:'6'},{Population:'8'}]});
    var filename = '../data/worldcitiespop.csv'

  it('should take a FileReader and CsvParser argument', () => {
  //your test goes here
    var csvParser = {readLines: mockReadLine}
    var fileReader = {readFile: mockReadFile}
    var pc = new PopulationCounter(fileReader, csvParser);
    expect(pc.parser).toEqual(csvParser)
    expect(pc.reader).toEqual(fileReader)
  })

  it('should read the provided file', ()=>{
    var csvParser = {readLines: mockReadLine}
    var fileReader = {readFile: mockReadFile}
    var pc = new PopulationCounter(fileReader, csvParser);
    pc.count(filename);
    expect(mockReadFile).toHaveBeenCalledWith(filename);
  });


  it('should parse the provided file', ()=>{
    var csvParser = {readLines: mockReadLine}
    var fileReader = {readFile: mockReadFile}
    var pc = new PopulationCounter(fileReader, csvParser);
    pc.count(filename);
    expect(mockReadLine).toHaveBeenCalled()
  });

  it('should print the world population', () => {
  //your test goes here
    var csvParser = {readLines: mockReadLine}
    var fileReader = {readFile: mockReadFile}
    var pc = new PopulationCounter(fileReader, csvParser);
    expect(pc.count(filename)).toEqual('World population is: 21')
  })
});