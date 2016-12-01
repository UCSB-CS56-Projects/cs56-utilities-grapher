cs56-utilities-grapher
======================
(F16) Hi future cS56 students,
this project is a software that let's you graph functions, for example,
`f(x)=3x+4;`

The GrapherApplication class is the main class and contains the JFrame of the GUI.

The FunctionsPanel class is the JPanel on the left side,
The Graph2DPanel is the center JPanel, and the Grapher2DBoundsPanel is the right JPanel.
The Bounds2DFloat class contains the bounds of the graph: the minimum and maximum x and y.
FunctionR1R1DisplayData class uses GeneralPath to display the actual data.

The ...Dialog classes contains a dialog box, and they are used in GrapherApplication.

All functions, as far as we know, use the CustomFunction class, which implements the FunctionR1R1 interface.
The CustomFunction class basically holds and represents a function inside it and can evaluate the value of the function at any point.
Other classes that represents a function, such as QuadraticFunction, are not used. These classes can probably be removed.
The Term class is not really used either and might not be used at all.

As it stands, the grapher accepts basically any function with one of the following variables: x, y, or t.
You can use the following operations: +,-,*,/, and ^.
You can use the folowing trig functions: cos, sin, tan, log, ln.
You can use the following constants: PI, e.
You can use parenthesis.
The grapher can detect syntax errors.
The parser works like this: The Tokenizer class turns input (String) into a bunch of tokens, or meaningful units of information such as a Plus symbol or a number.
The Parser class turns these tokens into a syntax tree: A tree that allows the Evaluator class to evaluate the input.
The Evaluator class evaluates the syntax tree (These classes can be found in the tokenizer folder, parser folder, and the evaluator folder, respectively).

Example functions you can input:
`coscoscos55x`
`(4+x(5+x)+5)`
`PIePIePIePIe //which will be interpreted as PI*e*PI*e*PI*e*PI*e`
`x^3loglnPI`


(W15) TBD: Good project but it needs more issues for students to work on. But most of the issues I can think of (such as adding parenthesis evaluation for arbitrary expressions) are difficult beyond the scope of a CS 56 project. (David Coffill)


project history
===============
```
 W14 | bronhuston 5pm | j-so | (bkiefer13) A 2D graphing GUI
```
