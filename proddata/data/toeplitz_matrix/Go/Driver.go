package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [[1,2,3],[4,5,6],[7,8,9]]
// [1,2,3,6,9,8,7,4,5]
// [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// [1,2,3,4,8,12,11,10,9,5,6,7]

func createIntMatrixFromString(s string) [][]int {
	stringWithoutOuterBraces := strings.TrimSpace(s[1 : len(s)-1])
	mat := [][]int{}
	if stringWithoutOuterBraces == "" {
		return mat
	}
	arrays := strings.Split(stringWithoutOuterBraces[:len(stringWithoutOuterBraces)-1], "]")
	for _, arr := range arrays {
		for i, ch := range arr {
			if byte(ch) == '[' {
				row := []int{}
				if strings.TrimSpace(arr[i+1:]) != "" {
					stringNums := strings.Split(arr[i+1:], ",")
					for _, stringNum := range stringNums {
						num, _ := strconv.Atoi(strings.TrimSpace(stringNum))
						row = append(row, num)
					}
				}
				mat = append(mat, row)
				break
			}
		}
	}
	return mat
}

func displayErrorMessage(testCase string, actualOutput string, expectedOutput string) {
	fmt.Println("Result: Failed for test case: ", testCase)
	fmt.Println("Actual Output: ", actualOutput)
	fmt.Println("Expected Output: ", expectedOutput)
}

func main() {
	bs, err := ioutil.ReadFile("../testcases.txt")
	if err != nil {
		os.Exit(1)
	}
	lines := strings.Split(string(bs), "\n")

	isSolutionWrong := false
	var actualOutput bool
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			matrix := createIntMatrixFromString(line)
			actualOutput = isToeplitzMatrix(matrix)
		} else {
			expectedOutput := true
			if line == "false" {
				expectedOutput = false
			}
			if actualOutput != expectedOutput {
				displayErrorMessage(testCase, strconv.FormatBool(actualOutput), line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
