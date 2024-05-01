import os
import re
import pyperclip

def concat_src_files(directory):
    # open the output file
    with open('src.txt', 'w') as output_file:
        # walk through the directory tree
        for dirpath, dirnames, filenames in os.walk(directory):
            # iterate through all the files in the directory
            for file in filenames:
                # if the file is a .java file
                if file.endswith('.java'):
                    # open the file
                    with open(os.path.join(dirpath, file), 'r') as input_file:
                        # read the contents of the file
                        contents = input_file.read()
                        # remove all import and package statements
                        contents = re.sub(r'^\s*import.*', '', contents, flags=re.MULTILINE)
                        contents = re.sub(r'^\s*package.*', '', contents, flags=re.MULTILINE)
                        # remove empty lines
                        contents = re.sub(r'^\s*$', '', contents, flags=re.MULTILINE)
                        # write a comment with the file name and relative directory to the output file
                        output_file.write('// File: ' + os.path.join(dirpath, file).replace(directory, '') + '\n')
                        # write the contents to the output file
                        output_file.write(contents)
    # copy the contents of the output file to the clipboard
    with open('src.txt', 'r') as output_file:
        contents = output_file.read()
        pyperclip.copy(contents)

# call the function with the directory
concat_src_files('core/src/main/java/org/ooad/project')