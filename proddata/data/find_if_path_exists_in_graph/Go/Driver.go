package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// 3, [[0,1],[1,2],[2,0]], 0, 2
// true
// 6, [[0,1],[0,2],[3,5],[5,4],[4,3]], 0, 5
// false

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
			ind := len(line) - 1
			for line[ind] != ']' {
				ind--
			}
			edges := createIntMatrixFromString(line[:ind+1])
			for line[ind] != ',' {
				ind++
			}
			otherInputs := strings.Split(line[ind+1:], ",")
			source, _ := strconv.Atoi(strings.TrimSpace(otherInputs[0]))
			destination, _ := strconv.Atoi(strings.TrimSpace(otherInputs[1]))
			ind = strings.Index(line, ",")
			n, _ := strconv.Atoi(strings.TrimRight(line[:ind], " "))
			actualOutput = validPath(n, edges, source, destination)
		} else {
			expectedOutput := false
			if line == "true" {
				expectedOutput = true
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
