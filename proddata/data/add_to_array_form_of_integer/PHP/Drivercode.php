<?php

include_once './Solution.php';
use Sol\Solution;

//reading testcases file 
$file1 = fopen('./testcases.txt', 'r') or die("Not Opening");

$Lines = $file1.readline();
$Count = 0;
$status = 0;
//object created of Solution class 
$obj = new Solution();

if (is_array($Lines) || is_object($Lines))
{
    //reading $Lines line by line as stored in $line.
foreach ($Lines as $line) {
    $Count += 1; 
    if ($Count == 1) {
      
        $num = array_map(int, $line.split());
      
    }
    if ($Count == 2){
        $k = int(line);
    }
    if ($Count == 3) {
       /*split the line when it encounters empty space and 
       modify all elements one or more arrays according to some user-defined condition.*/
        $expected_output = array_map(int, $line.split());
        $actual_output = obj.addToArrayForm(num, k);
        # echo "expected_output, actual_output)
        if ($expected_output != $actual_output){
            echo "Result: Failed";
            echo "Actual Output:", $actual_output;
            echo "Expected Outuput:", $expected_output;
            $status = 1;
            break;
        }
        $Count = 0;
    }
}
}
if ($status == 0){
    echo "Result: Success";
}
?>