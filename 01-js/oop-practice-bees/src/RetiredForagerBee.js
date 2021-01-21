/* START SOLUTION */
class RetiredForagerBee extends ForagerBee{
 constructor(age = 40, job = 'gamble', canFly = false){
   super(age, job, canFly);
   this.color = 'grey';
 }
 forage(){
   return 'I am too old, let me play cards instead'
 }
 gamble(treasure){
   super.forage(treasure)
 }
}
module.exports = RetiredForagerBee
/* END SOLUTION */
