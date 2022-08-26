
// Require would make the prompt
// package available to use
 var readline = require('readline');

// An utility function to add
// two numbers
function add() {
  var lines = []
  var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
  });
  rl.on('line', function(line){
      lines.push(line);
  })

  rl.on('close', function (cmd) {
    console.log(Number.parseInt(lines[0]) + Number.parseInt(lines[1]));
    process.exit(0);
  });
}

// Calling add function
add();