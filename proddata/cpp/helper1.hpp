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

// To Remove all punctuations from a string
string RemoveAllPunctInString(string line)
{
    for (int i = 0, len = line.size(); i < len; i++)
    {
        if (ispunct(line[i]))
        {
            line.erase(i--, 1);
            len = line.size();
        }
    }
    return line;
}