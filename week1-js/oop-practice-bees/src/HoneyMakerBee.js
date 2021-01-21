/* START SOLUTION */
class HoneyMakerBee extends Bee{
 constructor(age = 10, job = 'make honey'){
   super(age, job);
   this.honeyPot = 0;
 }
 makeHoney(){
   this.honeyPot++;
 }
 giveHoney(){
   if (this.honeyPot === 0){
     console.log('Error: Cannot give any more honey.')
   }
   this.honeyPot--;
 }
}
module.exports = HoneyMakerBee
/* END SOLUTION */
