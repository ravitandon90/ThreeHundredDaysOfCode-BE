# Python program to read json file
import json
import uuid
import requests
import base64
import time

def get_problem_definition(title_slug):
    leetcode_request_url = f"https://leetcode.com/graphql?query=query {{ question(titleSlug: \"{title_slug}\") {{ content }} }} "
    leetcode_response = requests.get(leetcode_request_url)
    return leetcode_response.json()['data']['question']['content']

LC_DELIM = "<p>&nbsp;</p>"

def get_problem_dict(title_slug):
    problem_def = get_problem_definition(title_slug).split(LC_DELIM)
    problem_dict = dict.fromkeys(['description', 'examples', 'constraints', 'rest'], "")
    problem_dict['description'] = problem_def[0]
    problem_def = problem_def[1:]
    for it in problem_def:
        if 'Example' in it:
            problem_dict['examples'] = it
        elif 'Constraints' in it:
            problem_dict['constraints'] = it
        else:
            problem_dict['rest'] += it
    return problem_dict

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

# Opening JSON file
f = open('problems.json')

# returns JSON object as
# a dictionary
data = json.load(f)
# Iterating through the json list
idx = 0
start_idx = 156
end_idx = 300

for row in data:
    idx = idx + 1
    if idx < start_idx:
        continue
    time.sleep(1)
    html = get_problem_definition(row['Slug_Title'])
    if html == None:
        append_to_file(row['Slug_Title'], 'premium.txt')
        continue
    description = encode_str(html)
    line = "INSERT INTO problem_description (problem_id, description, title, url, idx) VALUES('" + str(uuid.uuid4()) + "','"+ description + "','" + row['Title'] + "','" + row['URL'] + "'," + str(idx) + ");"
    append_to_file(line, 'commands.txt')
    if idx == end_idx:
        break

print(idx)

# Closing file
f.close()