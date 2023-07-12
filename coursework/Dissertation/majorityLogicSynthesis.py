from quine_mccluskey.qm import QuineMcCluskey
import re

# Instantiates the QuineMcCluskey class
qm = QuineMcCluskey()

# Takes a list of min terms and dont care terms returning the extracted prime implicants
qmImplicants = qm.simplify([1,2,5,6,9,10,13,14])

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

# def conversion_to_majority(expression):
    
#     expressions = []
    
#     #splits into AND replaced with 0 majoirty gates
#     majorityVariables = expression.split(" OR ")
#     for innerMajority in majorityVariables:
#         if " AND " in innerMajority:
#             innerMajority = innerMajority.replace(" AND ", "")
#             replacedAndMajority = innerMajority + "0"
#             expressions.append(f"⟨{replacedAndMajority}⟩")
            
#     orMajority = expression.split(" AND ")
#     for orString in expression:
#         finalString = f"⟨1{''.join(expressions)}⟩"
            
            
#     return finalString
    


# #determine if the expression fits into the 13 standard functions that can convert the three-variable boolean functions to thier majority gate equivalences
# def determine_functional_group(string):
    
#     standardFunctions = {
#         "1" : "A AND B AND C",
#         "2" : "A AND B",
#         "3" : "A AND B AND C OR A AND B' AND C'",
#         "4" : "A AND B AND C OR A' AND B' AND C'",
#         "5" : "A AND B OR B AND C",
#         "6" : "A AND B OR A' AND B' AND C",
#         "7" : "A AND B AND C OR A' AND B AND C' OR A AND B' AND C'",
#         "8" : "A",
#         "9" : "A AND B OR B AND C OR A AND C",
#         "10" : "A AND B OR B' AND C",
#         "11" : "A AND B OR B AND C OR A' AND B' AND C'",
#         "12" : "A AND B OR A' AND B'",
#         "13" : "A AND B AND C OR A' AND B' AND C OR A AND B' AND C' OR A' AND B AND C'"
#     }
    
#     return string
    
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

            

print(generate_majority(generate_expression(qmImplicants)))
    

    
    
    
    
    
    

