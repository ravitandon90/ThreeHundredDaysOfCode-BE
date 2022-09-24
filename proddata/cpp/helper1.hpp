#include <string>
#include <iostream>
using namespace std;

void Success()
{
    cout << "Result: Success" << endl;
}

// input: string
// output: string

bool checkOuputString(string expected_output, string actual_output, string input1)
{
    if (expected_output != actual_output)
    {
        cout << "Result: Failed" << endl;
        cout << "Input: " << input1 << endl;
        cout << "Expected Output: " << expected_output << endl;
        cout << "Actual Output: " << actual_output << endl;
        return true;
    }
    return false;
}

// input: string
// output: int

bool checkOuputInt(int expected_output, int actual_output, string input1)
{
    if (expected_output != actual_output)
    {
        cout << "Result: Failed" << endl;
        cout << "Input: " << input1 << endl;
        cout << "Expected Output: " << expected_output << endl;
        cout << "Actual Output: " << actual_output << endl;
        return true;
    }
    return false;
}

// input: string
// output: vector<int>

bool checkOuputIntVec(vector<int> expected_output, vector<int> actual_output, string input1)
{
    if (expected_output != actual_output)
    {
        cout << "Result: Failed" << endl;
        cout << "Input: " << input1 << endl;
        cout << "Expected Output: ";
        for (auto x : expected_output)
        {
            cout << x << " ";
        }
        cout << "\nActual Output: ";
        for (auto x : actual_output)
        {
            cout << x << " ";
        }
        return true;
    }
    return false;
}

// input: string
// output: vector<char>

bool checkOuputCharVec(vector<char> expected_output, vector<char> actual_output, string input1)
{
    if (expected_output != actual_output)
    {
        cout << "Result: Failed" << endl;
        cout << "Input: " << input1 << endl;
        cout << "Expected Output: ";
        for (auto x : expected_output)
        {
            cout << x << " ";
        }
        cout << "\nActual Output: ";
        for (auto x : actual_output)
        {
            cout << x << " ";
        }
        return true;
    }
    return false;
}

// input: string
// output: vector<string>

bool checkOuputCharVec(vector<string> expected_output, vector<string> actual_output, string input1)
{
    if (expected_output != actual_output)
    {
        cout << "Result: Failed" << endl;
        cout << "Input: " << input1 << endl;
        cout << "Expected Output: ";
        for (auto x : expected_output)
        {
            cout << x << " ";
        }
        cout << "\nActual Output: ";
        for (auto x : actual_output)
        {
            cout << x << " ";
        }
        return true;
    }
    return false;
}

// input: string
// output: vector<string>

bool checkOuputStringVec(vector<string> expected_output, vector<string> actual_output, string input1)
{
    if (expected_output != actual_output)
    {
        cout << "Result: Failed" << endl;
        cout << "Input: " << input1 << endl;
        cout << "Expected Output: ";
        for (auto x : expected_output)
        {
            cout << x << " ";
        }
        cout << "\nActual Output: ";
        for (auto x : actual_output)
        {
            cout << x << " ";
        }
        return true;
    }
    return false;
}

// To Remove all comma's from a string

void RemoveAllCommas(string &line)
{
    for (int i = 0, len = line.size(); i < len; i++)
    {
        if (line[i] == ',')
        {
            line.erase(i--, 1);
            len = line.size();
        }
    }
}

// To Remove all punctuations from a string
void RemoveAllPunctInString(string &line)
{
    for (int i = 0, len = line.size(); i < len; i++)
    {
        if (ispunct(line[i]))
        {
            line.erase(i--, 1);
            len = line.size();
        }
    }
}

// To Remove all punctuations from a array
string RemoveAllPunctInArray(string line)
{
    for (int i = 0, len = line.size(); i < len; i++)
    {
        if (line[i] == '-')
            continue;
        if (ispunct(line[i]))
        {
            line[i] = ' ';
        }
    }
    return line;
}

void RemoveAllPunctInStringArray(string &line)
{
    for (int i = 0, len = line.size(); i < len; i++)
    {
        if (ispunct(line[i]))
        {
            line[i] = ' ';
        }
    }
}

string convertInttoBool(bool res)
{
    if (res)
    {
        return "true";
    }
    else
    {
        return "false";
    }
}