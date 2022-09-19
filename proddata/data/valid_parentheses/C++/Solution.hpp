#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool isValid(string s)
    {
        stack<char> stack;
        int i;
        for (i = 0; i < s.size(); i++)
        {
            if (s[i] == '(' || s[i] == '[' || s[i] == '{')
            {
                stack.push(s[i]);
            }
            else
            {
                if (stack.size() == 0)
                    return false;
                if (s[i] == ')' && stack.top() != '(')
                    return false;

                else if (s[i] == ']' && stack.top() != '[')
                    return false;

                else if (s[i] == '}' && stack.top() != '{')
                    return false;
                stack.pop();
            }
        }
        if (stack.size() == 0)
            return true;
        return false;
    }
};