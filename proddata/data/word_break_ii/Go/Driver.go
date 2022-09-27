package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"sort"
	"strings"
)

// 3
// "catsanddog", ["cat","cats","and","sand","dog"]
// ["cats and dog","cat sand dog"]
// "pineapplepenapple", ["apple","pen","applepen","pine","pineapple"]
// ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
// "catsandog", ["cats","dog","sand","and","cat"]
// []

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
			ind := strings.Index(line, ",")
			s := strings.TrimRight(line[:ind], " ")
			s = strings.TrimSpace(s[1 : len(s)-1])
			wordDict := createStringArray(strings.TrimLeft(line[ind+1:], " "))
			actualOutput = wordBreak(s, wordDict)
		} else {
			expectedOutput := createStringArray(line)
			sort.Strings(actualOutput)
			sort.Strings(expectedOutput)
			if len(actualOutput) != len(expectedOutput) {
				isSolutionWrong = true
			} else {
				for ind, parens := range expectedOutput {
					if actualOutput[ind] != parens {
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
