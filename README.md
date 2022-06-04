# fillet-o-neumann
![example workflow](https://github.com/ShimaaBetah/fillet-o-neumann/actions/workflows/maven.yml/badge.svg)


## Description
A simulation for a Von Neumann based Computer Architecture using Java.
### Main Memory Architecture 
A von neumann Architecture in which program data and instruction data are stored in the same memory<br>
<b>Main Memory Size</b> 2048 * 32 `from 0 to 1023 for instructions and from 1024 to 2047 for data` 
### Registers 
- 31 General purpose Registers from R1 to R31
- 1 Zero Registers named R0
- 1 program counter PC
<b> Registers Size </b> 32 bits

### Instruction Architecture
<details>
	<summary> Instruction Set supported </summary>

|Name | Format| Operation|
|-----|-----|-----|
|ADD| ADD R1 R2 R3|R1 = R2 + R3|
|SUB|SUB R1 R2 R3| R1 = R2 - R3|
|MUL|MUL R1 R2 R3|R1 = R2 * R3|
|MOVI| MOVI R1 IMM|R1 = IMM|
|JEQ|JEQ R1 R2 IMM\label|IF(R1 == R2) {PC = PC+1+IMM\ PC = label }|
|AND|AND R1 R2 R3|R1 = R2 & R3|
|XORI|XORI R1 R2 IMM|R1 = R2 ⊕ IMM|
|JMP|JMP ADDRESS|PC = PC[31:28] || ADDRESS|
|LSL|LSL R1 R2 SHAMT|R1 = R2 << SHAMT|
|LSR|LSR R1 R2 SHAMT|R1 = R2 >>> SHAMT|
|MOVR|MOVR R1 R2 IMM|R1 = MEM[R2 + IMM]|
|MOVM|MOVM R1 R2 IMM|MEM[R2 + IMM] = R1|
	
</details>

### Datapath

<b> Stages: </b> 5
 - Instruction Fetch (IF)
 - Instruction Decode (ID)
 - Execute (EX)
 - Memory (MEM)
 - Write Back (WB)
<br>
<b> Pipeline: </b> maximum 4 instructions running in parallel (IF) and (MEM) cannot be done in parallel.

## Project Structure
<details>

 <summary> project structure </summary>
 
 ```
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
- We applied some spectacular design patterns such as: 
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

Docker will guarantee more consistency and make sure you are in a isolated enviroment and not affected by your local machine

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

- [Ibrahim Abou Elenein](https://github.com/aboueleyes)
- [Mohammad Omar](https://github.com/MohammadOTaha)
- [Ahmed Nasser](https://github.com/AhmedNasserG)
- [Abdulaziz Hassan](https://github.com/Abdulaziz-Hassan)
- [Elshimaa Betah](https://github.com/ShimaaBetah)

