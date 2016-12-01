cs56-utilities-grapher
======================
(F16) Hi future cS56 students,<br/>
this project is a software that let's you graph functions, for example,<br/>
`f(x)=3x+4;`<br/>

The GrapherApplication class is the main class and contains the JFrame of the GUI.

The FunctionsPanel class is the JPanel on the left side,<br/>
The Graph2DPanel is the center JPanel, and the Grapher2DBoundsPanel is the right JPanel.<br/>
The Bounds2DFloat class contains the bounds of the graph: the minimum and maximum x and y.<br/>
FunctionR1R1DisplayData class uses GeneralPath to display the actual data.<br/>

The ...Dialog classes contains a dialog box, and they are used in GrapherApplication.<br/>

All functions, as far as we know, use the CustomFunction class, which implements the FunctionR1R1 interface.<br/>
The CustomFunction class basically holds and represents a function inside it and can evaluate the value of the function at any point.<br/>
Other classes that represents a function, such as QuadraticFunction, are not used. These classes can probably be removed.<br/>
The Term class is not really used either and might not be used at all.<br/>

As it stands, the grapher accepts basically any function with one of the following variables: x, y, or t.<br/>
You can use the following operations: +,-,*,/, and ^.<br/>
You can use the folowing trig functions: cos, sin, tan, log, ln.<br/>
You can use the following constants: PI, e.<br/>
You can use parenthesis.<br/>
The grapher can detect syntax errors.<br/>
The parser works like this: The Tokenizer class turns input (String) into a bunch of tokens, or meaningful units of information such as a Plus symbol or a number.<br/>
The Parser class turns these tokens into a syntax tree: A tree that allows the Evaluator class to evaluate the input.<br/>
The Evaluator class evaluates the syntax tree (These classes can be found in the tokenizer folder, parser folder, and the evaluator folder, respectively).<br/>

Example functions you can input:<br/>
`coscoscos55x`<br/>
`(4+x(5+x)+5)`<br/>
`PIePIePIePIe //which will be interpreted as PI*e*PI*e*PI*e*PI*e` <br/>
`x^3loglnPI`<br/>


(W15) TBD: Good project but it needs more issues for students to work on. But most of the issues I can think of (such as adding parenthesis evaluation for arbitrary expressions) are difficult beyond the scope of a CS 56 project. (David Coffill)


project history
===============
```
 W14 | bronhuston 5pm | j-so | (bkiefer13) A 2D graphing GUI
```
