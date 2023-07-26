from quine_mccluskey.qm import QuineMcCluskey
from itertools import product
import re
import random


# Instantiates the QuineMcCluskey class
qm = QuineMcCluskey()

# Takes a list of min terms and dont care terms returning the extracted prime implicants
qmImplicants = qm.simplify([1,2,5,6,9,10,13,14])

#1,2,5,6,9,10,13,14
#2,5,6

# Takes a simplified string of prime implicants returned from the simplify function and returns a minimal Quine-McCluskey expression
def generate_expression(implicant_strings):
    variables = set()
    expressions = []

    for implicant_string in implicant_strings:
        expression = []
        for i, char in enumerate(implicant_string):
            
            if char == '1':
                expression.append(chr(65 + i))  # Convert index to variable (A, B, C, ...)
                variables.add(chr(65 + i))
                
            if char == '-':
                i = i + 1
                
            elif char == '0':
                expression.append(chr(65 + i) + "'")  # Convert index to NOT variable (A', B', C', ...)
                variables.add(chr(65 + i))

        expressions.append(" AND ".join(expression))  # Join the expressions with the "AND" operator

    return " OR ".join(expressions)  # Join the expressions with "OR" operator


# Print the number of bits and implicants
if qm.n_bits > 0: print("This many bits " + str(qm.n_bits))
print(qmImplicants)
print(generate_expression(qmImplicants))
    
#code that creates the initial majority gate logic representation of the boolean expression
def generate_majority(expression):
    
    newExpression = expression.replace(" AND ", "0") # replaces AND gates in the string with 0's for use in conversion to majority logic
    newExpression = newExpression.replace(" OR ", "1") # replaces OR gates in the string with 1's for use in conversion to majority logic
    
    # replaces the bar variables eg "A'" with lower case version of the same letter to represent 0 for that variable
    # This is to make the string easier to work with when converting to majority logic
    string = ''
    i = 0
    while i < len(newExpression):
        if newExpression[i].isupper() and i < len(newExpression) - 1 and newExpression[i+1] == "'":
            string += newExpression[i].lower()
            i += 2
        else:
            string += newExpression[i]
            i += 1
            
    print(string)
    
    # split the string into its majority gate groups as a list

    majoritySets = []

    new_string = ""
    for i, char in enumerate(string):
        if char == '0' and string[i-1] != "⟩" :
            new_string = string[slice(i-1, i+2)]
            string.replace(new_string, "")
            majoritySets.append(f"{new_string}")
            
        if char == '0' and string[i-1] == "⟩" :
            new_string = string[slice(i, i+2)]
            string.replace(new_string, "")
            majoritySets.append(f"{new_string}")
            
        if char == '1' and string[i-1] :
            new_string = string[slice(i, i+1)]
            string.replace(new_string, "")
            majoritySets.append(f"{new_string}")
            
    oneCount = 0
    zeroCount = 0
            
    #checks the length of the majority gate strings in the list right to left and for AND/OR gates represented by 0 and 1
    #If there are more than 3 majority gate strings two AND gates are concatenated
    if len(majoritySets) > 3 :
        for i in range(len(majoritySets) - 1, -1, -1):
            if len(majoritySets) > 3 and majoritySets[i][0] == majoritySets[i-1][-1]:
                majoritySets[i-1] = "⟨⟨" + majoritySets[i-1] + "⟩" + majoritySets[i][1:] + "⟩"
                majoritySets.pop(i)
    
    #brackets are added to each AND gate (0) to macth correct majority gate notation
    for i in range(len(majoritySets) - 1, -1, -1):
        
        oneCount += majoritySets[i].count("1")
        zeroCount += majoritySets[i].count("0")
        
        if len(majoritySets[i]) == 3 and "0" in majoritySets[i]:
            majoritySets[i] = "⟨" + majoritySets[i] + "⟩"
            
    #checks for OR gate (1) and formats it to form a complementary majority gate
    if oneCount > 0 and len(majoritySets) >= 3 :
        for i in range(len(majoritySets) - 1, -1, -1):
             if majoritySets[i] == "1" :
                oneCount += oneCount - 1
                majoritySets.pop(i)
                majoritySets.insert(0, "1")
            
    finalMajorityFormula = f"⟨{''.join(majoritySets)}⟩"
    
    # Define the regular expression pattern to match lowercase letters
    pattern = r'[a-z]'

    # Use a lambda function as the replacement to convert lowercase letter to uppercase followed by a dot
    replacement = lambda match: match.group().upper() + "'"

    # Apply the replacement using re.sub
    finalMajorityFormula = re.sub(pattern, replacement, finalMajorityFormula)
                
    print(majoritySets)
                
    return finalMajorityFormula







# Set of five majority boolean algebra operations that ensure that the function is logically complete
# each law is to be coded as its own function that finds a random appropriate application in the string and 
# is applied to that section only ensuring the changed function is logically equivalent to the original function

#List of dicts containing every possible combination of bits for 5 variables
combinations = [
    {'A': 0, 'B': 0, 'C': 0, 'D': 0, 'E': 0},
    {'A': 0, 'B': 0, 'C': 0, 'D': 0, 'E': 1},
    {'A': 0, 'B': 0, 'C': 0, 'D': 1, 'E': 0},
    {'A': 0, 'B': 0, 'C': 0, 'D': 1, 'E': 1},
    {'A': 0, 'B': 0, 'C': 1, 'D': 0, 'E': 0},
    {'A': 0, 'B': 0, 'C': 1, 'D': 0, 'E': 1},
    {'A': 0, 'B': 0, 'C': 1, 'D': 1, 'E': 0},
    {'A': 0, 'B': 0, 'C': 1, 'D': 1, 'E': 1},
    {'A': 0, 'B': 1, 'C': 0, 'D': 0, 'E': 0},
    {'A': 0, 'B': 1, 'C': 0, 'D': 0, 'E': 1},
    {'A': 0, 'B': 1, 'C': 0, 'D': 1, 'E': 0},
    {'A': 0, 'B': 1, 'C': 0, 'D': 1, 'E': 1},
    {'A': 0, 'B': 1, 'C': 1, 'D': 0, 'E': 0},
    {'A': 0, 'B': 1, 'C': 1, 'D': 0, 'E': 1},
    {'A': 0, 'B': 1, 'C': 1, 'D': 1, 'E': 0},
    {'A': 0, 'B': 1, 'C': 1, 'D': 1, 'E': 1},
    {'A': 1, 'B': 0, 'C': 0, 'D': 0, 'E': 0},
    {'A': 1, 'B': 0, 'C': 0, 'D': 0, 'E': 1},
    {'A': 1, 'B': 0, 'C': 0, 'D': 1, 'E': 0},
    {'A': 1, 'B': 0, 'C': 0, 'D': 1, 'E': 1},
    {'A': 1, 'B': 0, 'C': 1, 'D': 0, 'E': 0},
    {'A': 1, 'B': 0, 'C': 1, 'D': 0, 'E': 1},
    {'A': 1, 'B': 0, 'C': 1, 'D': 1, 'E': 0},
    {'A': 1, 'B': 0, 'C': 1, 'D': 1, 'E': 1},
    {'A': 1, 'B': 1, 'C': 0, 'D': 0, 'E': 0},
    {'A': 1, 'B': 1, 'C': 0, 'D': 0, 'E': 1},
    {'A': 1, 'B': 1, 'C': 0, 'D': 1, 'E': 0},
    {'A': 1, 'B': 1, 'C': 0, 'D': 1, 'E': 1},
    {'A': 1, 'B': 1, 'C': 1, 'D': 0, 'E': 0},
    {'A': 1, 'B': 1, 'C': 1, 'D': 0, 'E': 1},
    {'A': 1, 'B': 1, 'C': 1, 'D': 1, 'E': 0},
    {'A': 1, 'B': 1, 'C': 1, 'D': 1, 'E': 1}
]

exampleString = "⟨1⟨C0D'⟩⟨C'0D⟩⟩"
exampleList = ['1', '⟨C0d⟩', '⟨c0D⟩']

exampleList2 = ['1', '1', '⟨⟨A0c⟩0d⟩', '⟨⟨a0C⟩0d⟩', '⟨⟨B0c⟩0D⟩']

#removes the outer brackets surrounding a string
def removeBrackets(string):
    if len(string) < 2:
        return string

    first_char = string[0]
    last_char = string[-1]

    if first_char == '⟨' and last_char == '⟩':
        return string[1:-1]
    else:
        return string

#code to select the inner bracketed section of a string
def getInnerBracketedSection(inputString):
    stack = []
    innerSections = {}
    for i, char in enumerate(inputString):
        if char == '⟨':
            stack.append(i)
        elif char == '⟩':
            if stack:
                start = stack.pop()
                innerSections[start] = inputString[start + 1:i]
            else:
                raise ValueError("Unbalanced brackets in the input string.")
    if stack:
        raise ValueError("Unbalanced brackets in the input string.")
    
    innermost_start = max(innerSections.keys())
    return innerSections[innermost_start]

#finds each inner bracketed section of the input string and adds them to a list
def findBalancedSections(inputString):
    sections = []
    start = inputString.find("⟨")
    while start != -1:
        depth = 1
        end = start + 1
        while depth > 0 and end < len(inputString):
            if inputString[end] == "⟨":
                depth += 1
            elif inputString[end] == "⟩":
                depth -= 1
            end += 1

        if depth == 0:  # If we found a balanced pair
            sections.append(inputString[start:end])
        
        # Continue searching for the next pair
        start = inputString.find("⟨", start + 1)

    return sections

#finds a randomised section from the list of sections
def randomSection(inputString):
    sections = findBalancedSections(inputString)
    if not sections:
        return ""

    return random.choice(sections)

#function to swap the chars within the brackets of a string
def random_swap_within_brackets(section):
    if len(section) <= 2:  # Nothing to swap if the section has less than 3 characters
        return section

    # Extract characters within brackets for swapping
    chars_to_swap = list(section[1:-1])

    # Perform random swaps within the brackets
    for i in range(len(chars_to_swap)):
        j = random.randint(0, len(chars_to_swap) - 1)
        chars_to_swap[i], chars_to_swap[j] = chars_to_swap[j], chars_to_swap[i]

    # Reconstruct the section with the swapped characters while preserving the brackets
    swapped_section = section[0] + ''.join(chars_to_swap) + section[-1]

    return swapped_section

def recursive_random_swap(input_string):
    sections = findBalancedSections(input_string)
    if not sections:
        return input_string

    # Randomly select and swap characters within the innermost bracketed sections first
    for section in reversed(sections):
        input_string = input_string.replace(section, random_swap_within_brackets(section), 1)

    return input_string

#function to select randomly from a list
#use random.randint() to get a random index number
def getRandomFromList(list) :
    randIndex = random.randint(0, len(list)-1)
    randomItem = list[randIndex]
    
    return randomItem

#function to check the chars of a string against the dict
def checkChars(inputString, combinations):
    result = ""
    for char in inputString:
        if char in combinations:
            result += str(combinations[char] ^ (not char.isupper()))  # Flipping the bit for lowercase characters
        elif char.upper() in combinations:
            result += str(combinations[char.upper()] ^ 1)  # Flip the bit for the uppercase version
        else:
            result += char

    return result

#function to remove all brackets from a string
def removeAllBrackets(inputString) :
    # Define the regular expression pattern to match any bracket
    bracketPattern = r'[⟨⟩]'
        
    # Use re.sub() to replace all occurrences of the bracket pattern with an empty string
    result_string = re.sub(bracketPattern, '', inputString)
    
    return result_string

#function to randomise the positions of non-brackets chars in a string
def shuffleNonBracketChars(inputString):
    # Step 1: Find the positions of brackets in the string
    brackets = re.findall(r'[⟨⟩]', inputString)
    print("Found brackets in string," + str(len(brackets)))
    bracketPositions = [index for index, char in enumerate(inputString) if char in '⟨⟩']
    
    # Step 2: Create a list of characters that are not brackets
    nonBracketChars = [char for char in inputString if char not in '⟨⟩']
    
    # Step 3: Shuffle the list of non-bracket characters randomly
    random.shuffle(nonBracketChars)
    
    # Step 4: Combine the shuffled non-bracket characters with the bracket positions
    outputList = []
    for index in range(len(inputString)):
        if index in bracketPositions:
            outputList.append(brackets.pop(0))
        else:
            outputList.append(nonBracketChars.pop(0))
    
    return ''.join(outputList)

def findMajorityNumber(string):
    # Count the occurrences of 0 and 1 in the string
    count0 = string.count('0')
    count1 = string.count('1')

    # Return the majority number based on the counts
    if count0 > count1:
        return '0'
    elif count1 > count0:
        return '1'
    
# function that splits a string into its bracketed sections left to right, 
# then right to left randomises the char locations one section at a time
def stringBinaryReplacement(string, combinations) :
    
    sectionList = findBalancedSections(string).reverse()
    changedSectionList = sectionList
    previousSection = ""
    swappedCharSection = ""
    
    for i, section in enumerate(sectionList):
        
        previousSection = sectionList[i]
        swappedCharSection = removeBrackets(findMajorityNumber(random_swap_within_brackets(sectionList[i])))
        changedSectionList.replace(sectionList[i], swappedCharSection)
        
        for j, section in enumerate(changedSectionList) :
            if previousSection in section :
                section = section.replace(previousSection, swappedCharSection)
                changedSectionList.remove(section)
                
#function to randomise the locations of chars in a 3 char string

def randomiseString(string):
    if len(string) != 3:
        raise ValueError("Input string must contain exactly three characters.")
    
    # Convert the string to a list to shuffle its characters
    charList = list(string)
    
    # Randomly shuffle the characters
    randomisedChars = random.sample(charList, len(charList))
    
    # Convert the list back to a string
    randomisedString = "".join(randomisedChars)
    return randomisedString
            
# function that replaces chars 3-9 in a string with the index in the list which is that number -2
# eg 3 in a string will be replaced by index 1 in the list
def replaceNumbersWithIndices(lst):
    for i, string in enumerate(lst):
        count = i + 1
        print("count:", count)
        
        for j, char in enumerate(string):
            if char.isdigit() and '2' <= char <= '9':
                lst[i] = lst[i].replace(char, lst[int(char) - 2])
                print("Replaced " + str(i) + " : " + lst[i])
    
    return lst
    
    
    
    
    


# Commutativity Ω.C
# M(x, y, z) = M(y, x, z) = M(z, y, x)
def Commutativity(majorityList, combinations) :
    
    
    
    print("original maj list " + str(majorityList))
    
    #choose a random majority function from the input string
    majoritySelection = str(getRandomFromList(majorityList))
    
    while len(majoritySelection) == 1 :
        majoritySelection = str(getRandomFromList(majorityList))
        
    majoritySelection = randomSection(majoritySelection)
        
    print('majority selection: ', majoritySelection)
    
    #choose a random dictionary of values to apply to the string
    dictSelection = getRandomFromList(combinations)
    print('dictSelection: ', dictSelection)
    
    #replace chars with the values from the dict one section at a time while randomising the positions of the chars in the section
    #a control is kept for comparisson so the law of commutativity is preserved
    
    first_char = majoritySelection[0]
    last_char = majoritySelection[-1]
    
    controlMajority = majoritySelection
    finalString = majoritySelection
    
    replacementList = []
    count = 2 #count is incremented each time a new innersection is randomised and the numbee inserted into the string to keep track of location

    while first_char == '⟨' and last_char == '⟩':
        
        print("final string 2: ", finalString)
        
        #randomise the locations of the non-bracket variables
        innerSelection = str(getInnerBracketedSection(majoritySelection))
        controlInnerSelection = str(getInnerBracketedSection(majoritySelection))
        finalInnerSelection = str(getInnerBracketedSection(finalString))
        bracketedInnerSelection = "⟨" + innerSelection + "⟩"
        controlBracketedInnerSelection = "⟨" + controlInnerSelection + "⟩"
        print("finalinnerselcted", finalInnerSelection)
        
        #replace the characters of the strings with the values from the randomised dictionary
        randomisedInnerSelection = randomiseString(checkChars(innerSelection, dictSelection))
        randomFinalInnerSelection = randomiseString(finalInnerSelection)
        replacedInnerSelection = checkChars(controlInnerSelection, dictSelection)
        print('replaced char string: ', randomisedInnerSelection)
        
        majorityOfSelection = findMajorityNumber(randomisedInnerSelection) # find the majority number of the 3 bits
        controlMajorityOfSelection = findMajorityNumber(replacedInnerSelection)
        print('majority number: ', majorityOfSelection)
        
        if bracketedInnerSelection in majoritySelection :
            majoritySelection = majoritySelection.replace(bracketedInnerSelection, majorityOfSelection)
            controlMajority = controlMajority.replace(controlBracketedInnerSelection, controlMajorityOfSelection)
            
            finalString = finalString.replace("⟨" + finalInnerSelection + "⟩", str(count))
            count = count + 1
            replacementList.append("⟨" + randomFinalInnerSelection + "⟩")
            print("replacementList :", replacementList)
            print('new majority function : ', majoritySelection)
            print('control majority function : ', controlMajority)
            print("final string 1: ", finalString)
            
            first_char = majoritySelection[0]
            last_char = majoritySelection[-1]
            
    
    replacedString = ""
    replacedString = (replaceNumbersWithIndices(replacementList))
    print("Commutativity law string : ", replacedString)
    
    if replacedString != "" :
        return replacedString

    else:
        print("string does not obey the law of Commutativity")
        return
            

        
    # print("replaced majority with commutative law replacement" + str(majorityList))
    
        
    

        




# Majority Ω.M
# M(x, y, y) = y; M(x, x, z ¯ ) = z

# Associativity Ω.A
# M(x, u, M(y, u, z))
# = M(z, u, M(y, u, x))
# = M(y, u, M(z, u, x))

# Distributivity Ω.D
# M(x, y, M(u, v, z)) = M(M(x, y, u), M(x, y, v), z)

# Inverter Propagation Ω.I
# M(x, y, z) = M(¯x, y, ¯ z¯)

exampleMajList = ['⟨1⟨⟨B0D⟩0c⟩⟨⟨b0C⟩0a⟩⟩', '⟨⟨a0C⟩0d⟩', '⟨⟨B0c⟩0D⟩']

print(generate_majority(generate_expression(qmImplicants)))


Commutativity(exampleMajList, combinations)

