package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// ["i","love","leetcode","i","love","coding"], 2
// ["i","love"]
// ["the","day","is","sunny","the","the","the","sunny","is","is"], 4
// ["the","is","sunny","day"]

func createStringArray(input string) []string {
	array := []string{}
	inputAfterRemovingBraces := strings.TrimSpace(input[1 : len(input)-1])
	if inputAfterRemovingBraces != "" {
		array = strings.Split(inputAfterRemovingBraces, ",")
		for ind, stringValue := range array {
			array[ind] = strings.TrimSpace(stringValue[1 : len(stringValue)-1])
		}
	}
	return array
}

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
			ind := strings.Index(line, "]")
			words := createStringArray(line[:ind+1])
			for line[ind] != ',' {
				ind++
			}
			k, _ := strconv.Atoi(strings.TrimLeft(line[ind+1:], " "))
			actualOutput = topKFrequent(words, k)
		} else {
			expectedOutput := createStringArray(line)
			if len(actualOutput) != len(expectedOutput) {
				isSolutionWrong = true
			} else {
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
