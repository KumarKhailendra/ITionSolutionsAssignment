# ITionSolutionsAssignment
Write the solutions to the following programs in Java.
Once done, you can include the Java code for both programs in a word document. Also include
the sample input and output in that word document and submit the assignment. Alternatively
you can zip the javafiles and output in a text file and submit.

## Question 1
The Bitcoin rate is available as a public REST API that gives response in json format here:
https://api.coindesk.com/v1/bpi/currentprice.json Write a program that uses this REST API to
get the current rate of bitcoin and prints it in words. You can ignore the decimal part.
for example: if the rate is as follows:
"rate":"22,616.3987"
Your program should print: Twenty Two Thousand Six Hundred and Sixteen

## Question 2
Given a matrix of characters representing a place on Earth, where the value 'T' indicates the
presence of a Tree at that location and 'O' represents there is no tree at that point. An orchard is a
region with tress connected vertically, horizontally, or diagonally. The size of the orchard is the total
number of connected trees. Write a method to compute the sizes of all orchards in the matrix.
Example input:

[
['O','T','O','O'],
['O','T','O','T'],
['T','T','O','T'],
['O','T','O','T']
]

Note: Input can be in the code itself (it doesn't have to be supplied at runtime)
Output: 5, 3
If you are not able to get the exact output, still go ahead and upload the code. But ensure that your
code is commented well with the comments explaining the approach.
