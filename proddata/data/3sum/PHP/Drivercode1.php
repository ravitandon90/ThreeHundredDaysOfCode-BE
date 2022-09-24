<?php

include_once './Solution.php';
use Sol\Solution1;


$file1 = fopen('./testcases.txt', 'r') or die("Not Opening");

$Lines = $file1.readline();
$Count = 0;
$status = 0;
$obj = new Solution();

if (is_array($Lines) || is_object($Lines))
{
foreach ($Lines as $line) {
    
    if ($Count%2==0) {
        $num = array_map(int, $line.split());
        $actual_output = obj.threeSum(num);
    }
    
    else {
       
        $expected_output = $line.strip();

        # echo "expected_output, actual_output)
        if (str($expected_output) != str($actual_output)){
            echo "Result: Failed";
            echo "Actual Output:", $actual_output;
            echo "Expected Outuput:", $expected_output;
            $status = 1;
            break;
        }
        $Count =$Count+1;
    }
}
}
if ($status == 0){
    echo "Result: Success";
}
?>