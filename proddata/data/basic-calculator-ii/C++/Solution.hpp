#include <vector>
using namespace std;

class Solution
{
public:
    int evalRPN(vector<string> &tokens)
    {
        stack<int> s;
        for (int i = 0; i < tokens.size(); ++i)
        {
            string token = tokens[i];
            if (token == "/" || token == "*" || token == "+" || token == "-")
            {
                int op2 = s.top();
                s.pop();
                int op1 = s.top();
                s.pop();
                int res;
                if (token == "/")
                    res = op1 / op2;
                else if (token == "*")
                    res = op1 * op2;
                else if (token == "+")
                    res = op1 + op2;
                else if (token == "-")
                    res = op1 - op2;
                s.push(res);
            }
            else
                s.push(stoi(tokens[i]));
        }
        return s.top();
    }
    int priority(string s)
    {
        if (s == "+" || s == "-")
            return 1;
        return 2;
    }
    int calculate(string s)
    {
        vector<string> final;
        stack<string> st;
        for (int i = 0; i < s.length(); ++i)
        {
            if (s[i] == ' ')
                continue;
            string str = "";
            str += s[i];
            if (str == "/" || str == "*" || str == "+" || str == "-")
            {
                if (st.empty() || priority(str) > priority(st.top()))
                    st.push(str);
                else
                {
                    while (!st.empty() && priority(str) <= priority(st.top()))
                    {
                        final.push_back(st.top());
                        st.pop();
                    }
                    st.push(str);
                }
            }
            else
            {
                string val = "";
                while (i < s.length() && s[i] >= '0' && s[i] <= '9')
                    val += s[i++];
                final.push_back(val);
                i--;
            }
        }
        while (!st.empty())
        {
            final.push_back(st.top());
            st.pop();
        }
        return evalRPN(final);
    }
};