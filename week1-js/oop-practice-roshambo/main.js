const { argv } = require('yargs')

class Move {
    constructor(type){
        this._type = type;
    }
    get type(){
        return this._type;
    }
    beats(move){
        return move;
    }
}

class Rock extends Move{
    constructor(){
        super('Rock')
    }
    beats(){
        return super.beats('Scissors')
    }
}

class Paper extends Move{
    constructor(){
        super('Paper')
    }
    beats(){
        return super.beats('Rock')
    }
}

class Scissors extends Move{
    constructor(){
        super('Scissors')
    }
    beats(){
        return super.beats('Paper')
    }
}

class Game{
    constructor(playerMove){
        if (playerMove === 'rock'){
            this.playerMove = new Rock
        } else if (playerMove === 'paper'){
            this.playerMove = new Paper 
        } else {
            this.playerMove = new Scissors
        }
        var randomMove = Math.ceil(Math.random()*3);
        if (randomMove === 1){
            this.computerMove = new Rock
        } else if (randomMove === 2){
            this.computerMove = new Paper
        } else {
            this.computerMove = new Scissors
        }
    }

    compete(){
        var winner = '';
        var playerMove = this.playerMove.type;
        var computerMove = this.computerMove.type;
        if ( playerMove === computerMove ){
            winner = 'Player and Computer tie.'
        } else if (this.playerMove.beats() === computerMove){
            winner = `Player wins.`
        } else {
            winner = `Computer wins.`
        }
        console.log(`Playing a game of Roshambo against the computer.\nPlayer plays ${playerMove}!\nComputer plays ${computerMove}!\n~${winner}~`)
    }
}

var myGame = new Game(argv.move)
myGame.compete()