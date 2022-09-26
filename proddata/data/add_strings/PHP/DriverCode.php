<?php

include_once './Solution.php';
use Sol\Solution;


$file1 = fopen('./testcases.txt', 'r') or die("Not Opening");

$Lines = $file1.readline();
$count=0;
$status=0;
$obj= new Solution();
foreach($Lines as $Line)
{
    if($count % 2 == 0)
    {
        $array= $Line.split();
        $a=$array[0];
        $b=$array[1];
        $actual_output= $obj.addStrings($a,$b);
    }
    else
    {
        $expected_output=$line.strip();
        if($expected_output != $actual_output)
        {
            echo "Result: Failed";
            echo "Actual Output:", $actual_output;
            echo "Expected Outuput:", $expected_output;
            $status = 1;
            break;
        }
    }
    $count= $count+1;
}
if ($status == 0)
{
 echo('Result: Success');
}
