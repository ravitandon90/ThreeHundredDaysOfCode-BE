<?php
namespace Sol;
class Solution {

/**
 * @param String $num1
 * @param String $num2
 * @return String
 */
function addStrings($num1, $num2) {
    $result = "";
    $num1Len = strlen($num1);
    $num2Len = strlen($num2);
    $length = $num1Len > $num2Len ? $num1Len : $num2Len;
    $num1 = strrev($num1);
    $num2 = strrev($num2);
    $addDigit = 0;
    for ($i = 0; $i < $length; $i++) {
        $sum = (int)$num1[$i] + (int)$num2[$i] + $addDigit;
        if ($sum >= 10) {
            $addDigit = floor($sum / 10);
        } else {
            $addDigit = 0;
        }
        $result .= $sum % 10;
    }
    if ($addDigit != 0) {
        $result .= $addDigit;
    }
    $result = strrev($result);
    return $result;
}
}
?>