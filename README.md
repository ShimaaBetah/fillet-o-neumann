# fillet-o-neumann
![example workflow](https://github.com/ShimaaBetah/fillet-o-neumann/actions/workflows/maven.yml/badge.svg)

A simulation for a Von Nuemann based Computer Architecture using Java

## Description

## Project Structure
<details>

 <summary> project structure </summary>
 
 ```
 .
 ├── main
 │  └── java
 │     └── fillet
 │        ├── App.java
 │        ├── exceptions
 │        │  ├── AddressOutOfRangeException.java
 │        │  ├── InvalidInstructionException.java
 │        │  ├── InvalidRegisterException.java
 │        │  └── InvalidRegisterNumberException.java
 │        ├── instructions
 │        │  ├── HaltInstruction.java
 │        │  ├── ImmediateInstruction.java
 │        │  ├── Instruction.java
 │        │  ├── InstructionFactory.java
 │        │  ├── InstructionType.java
 │        │  ├── JumpInstruction.java
 │        │  └── RegisterInstruction.java
 │        ├── logger
 │        │  ├── destinations
 │        │  │  ├── ConsoleLogger.java
 │        │  │  ├── FileLogger.java
 │        │  │  └── LogObserver.java
 │        │  ├── Logger.java
 │        │  ├── LogSubject.java
 │        │  ├── outputs
 │        │  │  ├── run-02-06-2022-15-04-37.log
 │        │  │  ├── run-03-06-2022-22-35-00.log
 │        │  │  ├── run-03-06-2022-22-58-43.log
 │        │  │  ├── run-04-06-2022-00-05-09.log
 │        │  │  └── run-04-06-2022-00-06-07.log
 │        │  └── services
 │        │     ├── ColorStringService.java
 │        │     ├── CreateLogFileService.java
 │        │     ├── GenerateTableService.java
 │        │     ├── InitLoggerService.java
 │        │     ├── LogEntityService.java
 │        │     └── SegmentType.java
 │        ├── memory
 │        │  ├── MainMemory.java
 │        │  └── RegisterFile.java
 │        ├── operations
 │        │  ├── haltoperations
 │        │  │  ├── Halt.java
 │        │  │  ├── HaltOperation.java
 │        │  │  └── HaltOperationFactory.java
 │        │  ├── immediateoperations
 │        │  │  ├── MoveToMemory.java
 │        │  │  ├── MoveToRegister.java
 │        │  │  └── XORImmediate.java
 │        │  ├── jumpoperations
 │        │  │  ├── Jump.java
 │        │  │  ├── JumpOperation.java
 │        │  │  └── JumpOperationFactory.java
 │        │  ├── Operation.java
 │        │  ├── OperationFactory.java
 │        │  ├── OperationType.java
 │        │  └── registeroperations
 │        │     ├── And.java
 │        │     ├── LogicalShiftRight.java
 │        │     ├── Multiply.java
 │        │     ├── RegisterOperationFactory.java
 │        │     └── Sub.java
 │        ├── programs
 │        │  ├── caProgram.txt
 │        │  ├── empty-file.txt
 │        │  ├── final-isA.txt
 │        │  ├── spicy-jprogram.txt
 │        ├── signals
 │        │  └── Signals.java
 │        └── utils
 │           ├── Binary.java
 │           ├── Decoder.java
 │           ├── Parser.java
 │           └── Program.java
 └── test
    └── java
       └── tests
          ├── DecoderTest.java
          ├── ImmediateInstructionTest.java
          ├── InstructionFactoryTest.java
          ├── JumpInstructionTest.java
          ├── MainMemoryTest.java
          ├── ParserTest.java
          ├── RegisterFileTest.java
          └── RegisterInstructionTest.java

```
  
</details>

## Features
- Parser handles labels.
- Parser handles comments
- A high-quality logger (GUI alternative), not only outputs to the console but also outputs a log file named after the current time stamp.
- The code is thoroughly tested by JUnit 5.
- Used CI in the whole process. 
- We used a containerized environment using Docker. 
- We used Maven as a build system and dependency manager.
- We applied some spectacular  design patterns such as: 
	- Singleton 
	- Observer
	- Command 
	- Factory

## Technologies

| Technology | Description |
| ---------  | ---------   |
| Java       | A hated programming langauge |
| Maven      | A build tool and dependancy manager|
| Junit 5    | Java testing framework|
| Docker     | Container | 


## How to test?

```bash
> make test

> mvn test
```


## Test Using Docker 
 ```bash
 > docker build -t fillet . 
 > docker run fillet                                                                                                                                       
 ```



## How to run the project 

```
mvn compile exec:java -Dexec.mainClass=fillet.App
```
