#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool isRobotBounded(string instructions)
    {

        int n = instructions.size();
        int x = 0;
        int y = 0;

        char curr_dircn = 'N';

        for (int i = 0; i < n; i++)
        {
            char t = instructions[i];
            if (t == 'G')
            {
                if (curr_dircn == 'N')
                    y++;
                if (curr_dircn == 'S')
                    y--;
                if (curr_dircn == 'E')
                    x++;
                if (curr_dircn == 'W')
                    x--;
            }
            else
            {
                if (curr_dircn == 'N')
                {
                    curr_dircn = (t == 'L') ? 'W' : 'E';
                }
                else if (curr_dircn == 'W')
                {
                    curr_dircn = (t == 'L') ? 'S' : 'N';
                }
                else if (curr_dircn == 'S')
                {
                    curr_dircn = (t == 'L') ? 'E' : 'W';
                }
                else if (curr_dircn == 'E')
                {
                    curr_dircn = (t == 'L') ? 'N' : 'S';
                }
            }
        }

        if (x == 0 && y == 0 || curr_dircn != 'N')
            return true;
        return false;
    }
};