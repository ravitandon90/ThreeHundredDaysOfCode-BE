package buddy_strings.Java;

class Solution {
    public boolean buddyStrings(String s, String goal)
    {
        return new StringBuffer(s).reverse().toString().equals(goal);
    }
}