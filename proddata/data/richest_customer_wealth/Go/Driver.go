package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// input
// 1 2 3
// 3 2 1
// output
// 6
// check
// input
// 1 5
// 7 3
// 3 5
// output
// 10
// check
// input
// 2 8 7
// 7 1 3
// 1 9 5
// output
// 17
// check

func createStringFromArray(array []int) string {
	if len(array) == 0 {
		return "[]"
	}
	s := "["
	for ind, ele := range array {
		s += strconv.Itoa(ele)
		if ind != len(array)-1 {
			s += ","
		} else {
			s += "]"
		}
	}
	return s
}

func createStringFromMatrix(matrix [][]int) string {
	if len(matrix) == 0 {
		return "[]"
	}
	s := "["
	for ind, array := range matrix {
		s += createStringFromArray(array)
		if ind != len(matrix)-1 {
			s += ","
		} else {
			s += "]"
		}
	}
	return s
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
	var takeInput bool
	var actualOutput int
	var accounts [][]int

	for _, line := range lines {
		line = strings.TrimSpace(line)
		if line == "check" {
			continue
		}
		if line == "input" {
			accounts = [][]int{}
			takeInput = true
		} else if line == "output" {
			actualOutput = maximumWealth(accounts)
			takeInput = false
		} else if takeInput {
			var row = []int{}
			rowAsString := strings.Fields(line)
			for _, value := range rowAsString {
				element, _ := strconv.Atoi(value)
				row = append(row, element)
			}
			accounts = append(accounts, row)
		} else {
			expectedOutput, _ := strconv.Atoi(line)
			if actualOutput != expectedOutput {
				displayErrorMessage(createStringFromMatrix(accounts), strconv.Itoa(actualOutput), line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
