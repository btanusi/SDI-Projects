/* START SOLUTION */
class ForagerBee extends Bee{
 constructor(age = 10, job = 'find pollen', canFly = true){
   super(age, job);
   this.canFly = canFly;
   this.treasureChest = [];
 }
 forage(treasure){
   this.treasureChest.push(treasure);
 }
}
module.exports = ForagerBee
/* END SOLUTION */
