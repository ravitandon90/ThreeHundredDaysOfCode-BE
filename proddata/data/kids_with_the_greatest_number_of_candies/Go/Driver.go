package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// [2,3,5,1,3], 3
// [true,true,true,false,true]
// [4,2,1,1,2], 1
// [true,false,false,false,false]
// [12,1,12], 10
// [true,false,true]

func createIntArrayFromInput(input string) []int {
	array := []int{}
	inputAfterRemovingBraces := strings.TrimSpace(input[1 : len(input)-1])
	if inputAfterRemovingBraces != "" {
		arrayWithStringValues := strings.Split(inputAfterRemovingBraces, ",")
		for _, stringValue := range arrayWithStringValues {
			value, _ := strconv.Atoi(strings.TrimSpace(stringValue))
			array = append(array, value)
		}
	}
	return array
}

func createBoolArrayFromString(s string) []bool {
	array := []bool{}
	stringAfterRemovingBraces := strings.TrimSpace(s[1 : len(s)-1])
	if stringAfterRemovingBraces != "" {
		arrayWithStringValues := strings.Split(stringAfterRemovingBraces, ",")
		for _, stringValue := range arrayWithStringValues {
			if stringValue == "true" {
				array = append(array, true)
			} else {
				array = append(array, false)
			}
		}
	}
	return array
}

func createStringFromBoolArray(array []bool) string {
	if len(array) == 0 {
		return "[]"
	}
	s := "["
	for ind, ele := range array {
		s += strconv.FormatBool(ele)
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
	var actualOutput []bool
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			ind := strings.Index(line, "]")
			arr := createIntArrayFromInput(line[:ind+1])
			for line[ind] != ',' {
				ind++
			}
			k, _ := strconv.Atoi(strings.TrimSpace(line[ind+1:]))
			actualOutput = kidsWithCandies(arr, k)
		} else {
			expectedOutput := createBoolArrayFromString(line)
			for ind, value := range expectedOutput {
				if actualOutput[ind] != value {

					isSolutionWrong = true
				}
			}
			if isSolutionWrong {
				displayErrorMessage(testCase, createStringFromBoolArray(actualOutput), line)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
