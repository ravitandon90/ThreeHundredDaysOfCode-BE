package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"sort"
	"strconv"
	"strings"
)

// 2
// 1
// 0:01 0:02 0:04 0:08 0:16 0:32 1:00 2:00 4:00 8:00
// 9
//

func createStringFromStringArray(array []string) string {
	if len(array) == 0 {
		return "[]"
	}
	s := "["
	for ind, ele := range array {
		s += ele
		if ind != len(array)-1 {
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
	var actualOutput []string
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			input, _ := strconv.Atoi(line)
			actualOutput = readBinaryWatch(input)
		} else {
			expectedOutput := []string{}
			if line != "" {
				expectedOutput = strings.Fields(line)
			}
			if len(actualOutput) != len(expectedOutput) {
				isSolutionWrong = true
			} else {
				sort.Strings(actualOutput)
				sort.Strings(expectedOutput)
				for ind, s := range expectedOutput {
					if actualOutput[ind] != s {
						isSolutionWrong = true
						break
					}
				}
			}
			if isSolutionWrong {
				displayErrorMessage(testCase, createStringFromStringArray(actualOutput),
					createStringFromStringArray(expectedOutput))
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
