# Python program to read json file
import json
import uuid
import requests
import base64
import time

LC_DELIM = "<p>&nbsp;</p>"
SUPPORTED_LANGUAGES =  ['cpp', 'java', 'python3', 'golang', 'php', 'javascript']

def get_lc_response(title_slug):
    leetcode_request_url = f"https://leetcode.com/graphql?query=query {{ question(titleSlug: \"{title_slug}\") {{ difficulty content codeSnippets {{ lang langSlug code }} }} }} "
    leetcode_response = requests.get(leetcode_request_url)
    return leetcode_response.json()['data']['question']

def get_problem_dict(title_slug):
    problem_dict = dict.fromkeys(['difficulty', 'description', 'examples', 'constraints', 'rest'], "")
    lc_response = get_lc_response(title_slug)
    problem_dict['difficulty'] = lc_response['difficulty']
    problem_dict['description'] = lc_response['content']
    #problem_def = lc_response['content'].split(LC_DELIM)
    problem_dict['base_code'] = [base_code for base_code in lc_response['codeSnippets'] if base_code['langSlug'] in SUPPORTED_LANGUAGES]
    code_snippets = lc_response['codeSnippets']
    #extract_problem_definition(problem_def, problem_dict)
    return problem_dict

def extract_problem_definition(problem_def, problem_dict):
    problem_dict['description'] = problem_def[0]
    problem_def = problem_def[1:]
    for it in problem_def:
        if 'Example' in it:
            problem_dict['examples'] = it
        elif 'Constraints' in it:
            problem_dict['constraints'] = it
        else:
            problem_dict['rest'] += it

def get_base_code_for_lang(base_code_arr, lang):
    for base_code in base_code_arr:
        if base_code['langSlug'] == lang:
            return base_code['code']
    return ""

def encode_str(message):
    message_bytes = message.encode('utf-8')
    base64_bytes = base64.b64encode(message_bytes)
    base64_message = base64_bytes.decode('utf-8')
    return base64_message

def append_to_file(message, fileName):
    f = open(fileName, 'a')
    f.write(message)
    f.write("\n")
    f.close()

premium_questions = set(line.strip() for line in open('premium.txt'))

# Opening JSON file
f = open('problems.json')

# returns JSON object as
# a dictionary
data = json.load(f)
# Iterating through the json list
idx = 0
start_idx = 1
end_idx = 300

for row in data:
    idx = idx + 1
    if idx < start_idx:
        continue
    if row['Slug_Title'] in premium_questions:
        continue
    time.sleep(1)
    problem_dict = get_problem_dict(row['Slug_Title'])
    problem_id = str(uuid.uuid4())
    description = encode_str(problem_dict["description"])
    complexity = problem_dict['difficulty']
    base_code = problem_dict['base_code']

    # Generating the problem description
    line = "INSERT INTO problem_description (problem_id, description, title, url, idx, complexity) VALUES('" + problem_id + "','"+ description + "','" + row['Title'] + "','" + row['URL'] + "'," + str(idx) + ",'" + complexity + "');"
    append_to_file(line, 'problem_description.sql')

    # Generating the code snippet
    for lang in SUPPORTED_LANGUAGES:
        base_program = encode_str(get_base_code_for_lang(base_code, lang))
        line = "INSERT INTO problem_base_code (id, problem_id, language, base_code) VALUES ('" + str(uuid.uuid4()) + "','" + problem_id + "','" + lang + "','" + base_program + "');"
        append_to_file(line, 'code_snippets.sql')
    if idx == end_idx:
        break

print(idx)

# Closing file
f.close()