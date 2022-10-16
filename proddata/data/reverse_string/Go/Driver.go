package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

// 2
// ["h","e","l","l","o"]
// ["o","l","l","e","h"]
// ["H","a","n","n","a","h"]
// ["h","a","n","n","a","H"]

func createByteArrayFromString(input string) []byte {
	array := []byte{}
	inputAfterRemovingBraces := strings.TrimSpace(input[1 : len(input)-1])
	if inputAfterRemovingBraces != "" {
		arrayWithStringValues := strings.Split(inputAfterRemovingBraces, ",")
		for _, stringValue := range arrayWithStringValues {
			stringValue = strings.TrimSpace(stringValue)
			value := stringValue[1]
			array = append(array, value)
		}
	}
	return array
}

func areByteArraysEqual(arr1 []byte, arr2 []byte) bool {
	if len(arr1) != len(arr2) {
		return false
	}
	for ind, ele := range arr1 {
		if ele != arr2[ind] {
			return false
		}
	}
	return true
}

func createStringFromByteArray(array []byte) string {
	if len(array) == 0 {
		return "[]"
	}
	s := "["
	for ind, ele := range array {
		s += string(ele)
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
	var s []byte
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			s = createByteArrayFromString(line)
			reverseString(s)
		} else {
			expectedOutput := createByteArrayFromString(line)
			if !areByteArraysEqual(s, expectedOutput) {
				displayErrorMessage(testCase, createStringFromByteArray(s),
					createStringFromByteArray((expectedOutput)))
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
