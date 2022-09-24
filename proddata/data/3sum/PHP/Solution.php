<?php
namespace Sol;
class Solution1 {
    public $resHash;
    /**
     * @param Integer[] $nums
     * @return Integer[][]
     */
    function threeSum($nums) {
        sort($nums);
        $res = [];
        
        for($i = 0; $i < count($nums); $i++) {
            $l = $i+1;
            $r = count($nums)-1;
            while($l < $r) {
                $sum = $nums[$i]+$nums[$l]+$nums[$r];
                if($sum === 0) {
                    if($resHash[$nums[$i].$nums[$l].$nums[$r].""] !== 1) {
                        array_push($res, [$nums[$i],$nums[$l],$nums[$r]]);
                        $resHash[$nums[$i].$nums[$l].$nums[$r].""] = 1;
                    }
                    $l += 1;
                    $r -= 1;
                } else if($sum < 0) {
                    $l += 1;
                } else {
                    $r -= 1;
                }
            }
        }
        return $res;
    }
}
?>