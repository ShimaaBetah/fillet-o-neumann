# fillet-o-neumann
![example workflow](https://github.com/ShimaaBetah/fillet-o-neumann/actions/workflows/maven.yml/badge.svg)


## Description
A simulation for a Von Nuemann based Computer Architecture using Java

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
=======
  .
 ├── Dockerfile
 ├── Makefile
 ├── pom.xml
 ├── README.md
 ├── run.sh
 ├── src
 │  ├── main
 │  │  ├── java
 │  │  │  └── fillet
 │  │  │     ├── App.java
 │  │  │     ├── exceptions
 │  │  │     │  ├── AddressOutOfRangeException.java
 │  │  │     │  ├── InvalidInstructionException.java
 │  │  │     │  ├── InvalidRegisterException.java
 │  │  │     │  └── InvalidRegisterNumberException.java
 │  │  │     ├── instructions
 │  │  │     │  ├── HaltInstruction.java
 │  │  │     │  ├── ImmediateInstruction.java
 │  │  │     │  ├── Instruction.java
 │  │  │     │  ├── InstructionFactory.java
 │  │  │     │  ├── InstructionType.java
 │  │  │     │  ├── JumpInstruction.java
 │  │  │     │  └── RegisterInstruction.java
 │  │  │     ├── logger
 │  │  │     │  ├── destinations
 │  │  │     │  │  ├── ConsoleLogger.java
 │  │  │     │  │  ├── FileLogger.java
 │  │  │     │  │  └── LogObserver.java
 │  │  │     │  ├── Logger.java
 │  │  │     │  ├── LogSubject.java
 │  │  │     │  ├── outputs
 │  │  │     │  │  ├── run-02-06-2022-15-04-37.log
 │  │  │     │  │  ├── run-02-06-2022-23-11-07.log
 │  │  │     │  │  ├── run-02-06-2022-23-11-36.log
 │  │  │     │  │  ├── run-02-06-2022-23-53-15.log
 │  │  │     │  │  ├── run-03-06-2022-00-13-46.log
 │  │  │     │  │  ├── run-03-06-2022-00-14-21.log
 │  │  │     │  │  ├── run-03-06-2022-00-29-18.log
 │  │  │     │  │  ├── run-03-06-2022-00-30-49.log
 │  │  │     │  │  ├── run-03-06-2022-00-54-02.log
 │  │  │     │  │  ├── run-03-06-2022-00-54-25.log
 │  │  │     │  │  ├── run-03-06-2022-00-58-52.log
 │  │  │     │  │  ├── run-03-06-2022-00-59-23.log
 │  │  │     │  │  ├── run-03-06-2022-13-52-00.log
 │  │  │     │  │  ├── run-03-06-2022-13-52-06.log
 │  │  │     │  │  ├── run-03-06-2022-13-52-40.log
 │  │  │     │  │  ├── run-03-06-2022-14-24-44.log
 │  │  │     │  │  ├── run-03-06-2022-14-27-28.log
 │  │  │     │  │  ├── run-04-06-2022-00-10-30.log
 │  │  │     │  │  ├── run-04-06-2022-00-11-11.log
 │  │  │     │  │  ├── run-04-06-2022-00-14-15.log
 │  │  │     │  │  ├── run-04-06-2022-00-16-58.log
 │  │  │     │  │  ├── run-04-06-2022-00-20-20.log
 │  │  │     │  │  ├── run-04-06-2022-00-21-14.log
 │  │  │     │  │  ├── run-04-06-2022-00-21-34.log
 │  │  │     │  │  ├── run-04-06-2022-00-21-42.log
 │  │  │     │  │  ├── run-04-06-2022-11-43-43.log
 │  │  │     │  │  └── run-04-06-2022-11-47-52.log
 │  │  │     │  └── services
 │  │  │     │     ├── ColorStringService.java
 │  │  │     │     ├── CreateLogFileService.java
 │  │  │     │     ├── GenerateTableService.java
 │  │  │     │     ├── InitLoggerService.java
 │  │  │     │     ├── LogEntityService.java
 │  │  │     │     └── SegmentType.java
 │  │  │     ├── memory
 │  │  │     │  ├── MainMemory.java
 │  │  │     │  └── RegisterFile.java
 │  │  │     ├── operations
 │  │  │     │  ├── haltoperations
 │  │  │     │  │  ├── Halt.java
 │  │  │     │  │  ├── HaltOperation.java
 │  │  │     │  │  └── HaltOperationFactory.java
 │  │  │     │  ├── immediateoperations
 │  │  │     │  │  ├── ImmediateOperation.java
 │  │  │     │  │  ├── ImmediateOperationFactory.java
 │  │  │     │  │  ├── JumpIfEqual.java
 │  │  │     │  │  ├── MoveImmediate.java
 │  │  │     │  │  ├── MoveToMemory.java
 │  │  │     │  │  ├── MoveToRegister.java
 │  │  │     │  │  └── XORImmediate.java
 │  │  │     │  ├── jumpoperations
 │  │  │     │  │  ├── Jump.java
 │  │  │     │  │  ├── JumpOperation.java
 │  │  │     │  │  └── JumpOperationFactory.java
 │  │  │     │  ├── Operation.java
 │  │  │     │  ├── OperationFactory.java
 │  │  │     │  ├── OperationType.java
 │  │  │     │  └── registeroperations
 │  │  │     │     ├── Add.java
 │  │  │     │     ├── And.java
 │  │  │     │     ├── LogicalShiftLeft.java
 │  │  │     │     ├── LogicalShiftRight.java
 │  │  │     │     ├── Multiply.java
 │  │  │     │     ├── RegisterOperation.java
 │  │  │     │     ├── RegisterOperationFactory.java
 │  │  │     │     └── Sub.java
 │  │  │     ├── programs
 │  │  │     │  ├── caProgram.txt
 │  │  │     │  ├── empty-file.txt
 │  │  │     │  ├── final-isA.txt
 │  │  │     │  ├── negative-jump.txt
 │  │  │     │  ├── program1.txt
 │  │  │     │  ├── spicy-iprogram.txt
 │  │  │     │  ├── spicy-jprogram.txt
 │  │  │     │  ├── spicy-rprogram.txt
 │  │  │     │  └── test-sum.txt
 │  │  │     ├── signals
 │  │  │     │  └── Signals.java
 │  │  │     └── utils
 │  │  │        ├── Binary.java
 │  │  │        ├── Decoder.java
 │  │  │        ├── Parser.java
 │  │  │        ├── Path.java
 │  │  │        └── Program.java
 │  │  └── resources
 │  └── test
 │     └── java
 │        └── tests
 │           ├── DecoderTest.java
 │           ├── ImmediateInstructionTest.java
 │           ├── InstructionFactoryTest.java
 │           ├── JumpInstructionTest.java
 │           ├── MainMemoryTest.java
 │           ├── ParserTest.java
 │           ├── RegisterFileTest.java
 │           └── RegisterInstructionTest.java
 └── target
```
  
</details>

## Features
- Parser handles labels. :boom:
- Parser handles comments.  ######
- A high-quality logger (GUI alternative), not only outputs to the console but also outputs a log file named after the current time stamp.
- The code is thoroughly tested by JUnit 5.
- Used CI in the whole process. ![example workflow](https://github.com/ShimaaBetah/fillet-o-neumann/actions/workflows/maven.yml/badge.svg)
- We used a containerized environment using Docker :whale:. 
- We used Maven as a build system and dependency manager.  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser)
- We applied some spectacular  design patterns such as: 
	- Singleton 
	- Observer
	- Command 
	- Factory

## Technologies

| Technology | Description |
| ---------  | ---------   |
| Java       | A hated programming langauge :thumbsdown:|
| Maven      | A build tool and dependancy manager|
| Junit 5    | Java testing framework|
| Docker :whale:    | Container | 


## How to test?

```bash
> make test

> mvn test
```


## Test Using Docker 

Docker will gurantee more consistency and make sure you are in a isolated enviroment and not affected by your local machine

 ```bash
 > docker build -t fillet . 
 > docker run fillet                                                                                                                                       
 ```

or even

```bash
> make docker-test
```


## How to run the project 

```
> mvn compile exec:java -Dexec.mainClass=fillet.App
```

## Team behind this piece of art

- [Ibrahim Abou Elenein](www.github.com/aboueleyes)
- [Mohammad Omar](https://github.com/MohammadOTaha)
- [Ahmed Nasser](https://github.com/AhmedNasserG)
- [Abdulaziz Hassan](https://github.com/Abdulaziz-Hassan)
- [Elshimaa Betah](https://github.com/ShimaaBetah)

