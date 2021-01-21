const fs = require('fs');


// // WRITING TO A FILE//
let lyrics = 'Here are some song lyrics! OooOOoooOooo!';

let syncLyrics = 'Here are some other lyrics! Oooooo!'
/*
//write to a new file named 2pac.txt async
fs.writeFile('lyrics.txt', lyrics, (err) => {
    // throws an error, you could also catch it here
    if (err) throw err;

    // success case, the file was saved
    console.log('Lyric saved!');
});

// write to a new file sync 
fs.writeFileSync('lyrics.txt', syncLyrics, (err) => {
//     // throws an error, you could also catch it here
     if (err) throw err;
});


// // ADDING TO A FILE'S CONTENTS async/// 

fs.appendFile('lyrics.txt', '\nADDING SOME MORE STUFF HERE!', (err) => {
     if (err) throw err;
     console.log('The lyrics were updated!');
 });


*/
// // READING A FILE async/// 

let contents = fs.readFileSync('lyrics.txt', function (err, data) {
     if (err) {
        return console.error(err);
     }
     //console.log("Asynchronous read: " + data.toString());
  });
  let val = contents.toString()
  console.log(val)

/*

//  // RENAME A FILE async/// 

  fs.rename('lyrics.txt', 'newNameForFile.txt', (err) => {
     if (err) throw err;
     console.log('Rename complete!');
   });


*/

  /// DELETING A FILE //// 
/*
   fs.unlink('newNameForFile.txt', (err) => {
     if (err) throw err;
     console.log('newNameForFile.txt was deleted');
   });
   */