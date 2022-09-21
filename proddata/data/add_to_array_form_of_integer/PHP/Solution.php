<?php
namespace Sol;
class Solution
{
    /**
     * @param Integer[] $A
     * @param Integer $K
     * @return Integer[]
     */
    function addToArrayForm($A, $K)
    {
        $count = count($A);
        $i = $remaining = 0;
        $answers = [];
        while ($K || $i < $count) {
            $i++;
            $bit = $K % 10;
            $remaining = $A[$count - $i] + $bit + $remaining;
            $answers[] = $remaining % 10;
            $remaining = intval($remaining / 10);
            $K = intval($K / 10);
        }

        if ($remaining) {
            $answers[] = $remaining;
        }

        return array_reverse($answers);
    }
}
?>
