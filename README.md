<!-- TODO(timur): someone should expand this-->
# algebud
An abstract algebra service

# Building

This is the build instructions for ubuntu/debian/`apt` systems. For other platforms you may have to follow different steps.

## Install Dependencies

 - Install bazel: https://bazel.build/install/ubuntu
 - Install java: `apt-get install openjdk-9-jdk`

Any version of Java 9 or higher should work.

## Building Source

You can build the source into a deployable jar using

```sh
bazel build //core/java/algebud:cli_deploy.jar
```

and run it with 

```sh
java -jar //bazel-bin/core/java/algebud/cli_deploy.jar
```

## Usage

Algebud's CLI tool evaluates an expression in infix notation:

```
java -Dalgebud.variable.A=1 -Dalgebud.variable.B=1 \\
  -jar //bazel-bin/core/java/algebud/cli_deploy.jar "A + B"
A + B where [A=1, B=1] = 2
```

The tool supports [arithmetic operations](https://en.wikipedia.org/wiki/Arithmetic#Arithmetic_operations), parentheses, floating point numbers, compile-time defined named constants, and user-defined variables.

Named constants are any string sequence that matches the regex `-?@[A-Za-z]+(_[A-Za-z0-9]+)*`. The evaluator currently supports [Euler's Number](https://en.wikipedia.org/wiki/E_(mathematical_constant)) (`"@e"`) and [π](https://en.wikipedia.org/wiki/Pi) (`"@pi"`).

Variables are any string sequence that matches the regex `-?[A-Za-z]+(_[A-Za-z0-9]+)*`. Variables can be defined as java properties as single values (`-Dalgebud.variable.A=1`) or as a collection of values (`-Dalgebud.variables=B:1,C:1`). For example, we could compute the quadratic formula for `x^2 + 2*x + 1` as:

```
java -Dalgebud.variable.A=1 -Dalgebud.variable.B=2 -Dalgebud.variable.C=1 \\
  -jar //bazel-bin/core/java/algebud/cli_deploy.jar "(-B + (B^2 + 4*A*C)) / (2*C)"
(-B + (B^2 + 4*A*C)) / (2*C) where [A=1, B=2, C=1] = -1
```

```
java -Dalgebud.variables=A:1,B:2,C:1 \\
  -jar //bazel-bin/core/java/algebud/cli_deploy.jar "(-B + (B^2 + 4*A*C)) / (2*C)"
(-B + (B^2 + 4*A*C)) / (2*C) where [A=1, B=2, C=1] = -1
```

```
java -Dalgebud.variable.A=1 -Dalgebud.variables=B:2,C:1 \\
  -jar //bazel-bin/core/java/algebud/cli_deploy.jar "(-B + (B^2 + 4*A*C)) / (2*C)"
(-B + (B^2 + 4*A*C)) / (2*C) where [A=1, B=2, C=1] = -1
```

<!-- Install protoc: https://grpc.io/docs/protoc-installation -->
<!-- https://github.com/bazelbuild/rules_go/blob/0.19.0/go/workspace.rst#proto-dependencies -->