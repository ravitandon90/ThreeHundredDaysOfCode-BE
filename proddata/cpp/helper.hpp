#include <algorithm>
#include <fstream>
#include<iostream>
#include <stack>
#include<string>
#include<vector>
#include <boost/tokenizer.hpp>

using namespace std;
using namespace boost;

string Print(const vector<int>& numbers) {
  string output;
  for (int num : numbers) {
    output += std::to_string(num) + ",";
  }
  if (output.size() > 0) output.pop_back();
  return "[" + output + "]";
}

string Print(const vector<vector<int> >& input) {
  string output;
  for (const auto& v1: input) {
    output = output + Print(v1) + ",";
  }
  if (output.size() > 0) output.pop_back();
  return "[" + output + "]";
}

vector<int> ParseLineToVector(const string& input) {
    vector<int> output;
    string str = input;
    str.erase(std::remove(str.begin(), str.end(), '['), str.end());
    str.erase(std::remove(str.begin(), str.end(), ']'), str.end());
    char_separator<char> sep(",");
    tokenizer<char_separator<char> > tokens(str, sep);
    for (const auto& token: tokens) {
        output.push_back(stoi(token));
    }
    return output;
}

vector<string> SplitLineIntoVectors(const string& input) {
  stack<char> st;
  string curr;
  vector<string> output;
  for (char ch : input) {
      if (ch == '[') {
        st.push(ch);
      } else if (ch == ']') {
        st.pop();
        if (st.empty()) {
          output.push_back(curr);
          curr = "";
        }
      } else if (st.size() > 0) {
        curr.append(1, ch);
      }
  }
  return output;
}

vector<vector<int> > ParseLineToVectorOfVectors(const string& input) {
  vector<vector<int> > output;
  string in_norm = input;
  in_norm.pop_back();
  in_norm = in_norm.substr(1);
  vector<string> lines = SplitLineIntoVectors(in_norm);
  for (string line : lines) {
    output.push_back(ParseLineToVector(line));
  }
  return output;
}

bool Compare(const vector<vector<int> >& v1, const vector<vector<int> >& v2) {
  return v1 == v2;
}
