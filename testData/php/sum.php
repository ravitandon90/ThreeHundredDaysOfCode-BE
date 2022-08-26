<?php
$f = fopen( 'php://stdin', 'r' );

$num1 = fgets($f);
$num2 = fgets($f);

fclose( $f );

$sum=$num1+$num2;

print $sum;
print "\r\n";
?>