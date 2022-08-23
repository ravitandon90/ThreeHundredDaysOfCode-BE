import requests

# title_slug = "palindrome-number"

def get_problem_definition(title_slug):
    leetcode_request_url = f"https://leetcode.com/graphql?query=query {{ question(titleSlug: \"{title_slug}\") {{ content }} }} "
    leetcode_response = requests.get(leetcode_request_url)
    return leetcode_response.json()['data']['question']['content']