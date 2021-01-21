// entry poient of the application

class PopulationCounter {
    constructor(fileReader, csvParser) {
        this.reader = fileReader
        this.parser = csvParser
    }
    count(filename) {
        let data = this.reader.readFile(filename)
        let dataPoints=this.parser.readLines(data);
        let population = dataPoints
                        .filter(entry=> entry.Population !== undefined)
                        .filter(entry=>entry.Population.length !== 0)
                        .map(entry=>parseInt(entry.Population))
                        .reduce((total,entry)=>total+=entry).toLocaleString('en')
        return(`World population is: ${population}`)
    }
}

module.exports = PopulationCounter