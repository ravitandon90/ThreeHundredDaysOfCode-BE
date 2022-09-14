import json
import uuid
import requests
import base64
import time
import os

LC_DELIM = "<p>&nbsp;</p>"
IT_DELIM = "<pre>&nbsp;</pre>"
SUPPORTED_LANGUAGES = ['cpp', 'java', 'python3', 'golang', 'php', 'javascript']


for root, dirs, files in os.walk('./data/', topdown=False):
    for name in dirs:
      new_name = name.replace("-", "_")
#      print(new_name)

basedir = './'
for fn in os.listdir(basedir):
  if not os.path.isdir(os.path.join(basedir, fn)):
    continue # Not a directory
  if '-' not in fn:
    continue # Invalid format
  new_name = fn.replace("-", "_")
  
  os.rename(os.path.join(basedir, fn),
            os.path.join(basedir, new_name))
  print(new_name)


