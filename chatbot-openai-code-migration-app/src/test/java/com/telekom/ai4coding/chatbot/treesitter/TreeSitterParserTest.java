package com.telekom.ai4coding.chatbot.treesitter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.treesitter.TSNode;
import org.treesitter.TSTree;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.Assert.*;

class TreeSitterParserTest {

  private static Stream<Arguments> testData() {
    return Stream.of(
      Arguments.of(
        FileType.BASH,
        new String("""
        #!/bin/bash

        for i in {1..3}; do
            echo "Content of file $i" > "/tmp/test_file_$i.txt"
        done

        for i in {1..3}; do
            cat "/tmp/test_file_$i.txt"
        done

        rm /tmp/test_file_{1..3}.txt
        """)
      ),
      Arguments.of(
        FileType.C,
        new String("""
        #include <stdio.h>

        void printMessage(int count) {
            for (int i = 0; i < count; i++) {
                printf("This is message number %d\n", i + 1);
            }
        }

        int main() {
            int times = 5;
            printf("Testing the parser with C code.\n");
            if (times > 0) {
                printMessage(times);
            } else {
                printf("No messages to print.\n");
            }
            return 0;
        }
        """)
      ),
      Arguments.of(
        FileType.COMMONLISP,
        new String("""
        (defun greet (name)
          "Prints a greeting message to the user."
          (format t "Hello, ~a!~%" name))

        (defun test-parser ()
          "Function to test various Lisp constructs."
          (let ((names '("Alice" "Bob" "Charlie")))
            (dolist (name names)
              (greet name))
            (if (null names)
                (format t "No names to greet.~%")
                (format t "Finished greeting.~%"))))

        (test-parser)
        """)
      ),
      Arguments.of(
        FileType.CSHARP,
        new String("""
        using System;

        class Program
        {
            static void PrintEven(int number)
            {
                if (number % 2 == 0)
                {
                    Console.WriteLine("Even: " + number);
                }
            }

            static void Main()
            {
                for (int i = 1; i <= 5; i++)
                {
                    PrintEven(i);
                }
            }
        }
        """)
      ),
      Arguments.of(
        FileType.DOCKERFILE,
        new String("""
        FROM python:3.8-slim

        WORKDIR /usr/src/app

        COPY . .

        RUN pip install --no-cache-dir -r requirements.txt

        EXPOSE 80

        ENV NAME World

        CMD ["python", "./app.py"]
        """)
      ),
      Arguments.of(
        FileType.FORTRAN,
        new String("""
        program TestParser
            implicit none
            integer :: i, sum

            sum = 0
            do i = 1, 5
                sum = sum + i
                print *, 'Adding', i, 'Total so far:', sum
            end do

            print *, 'Final sum:', sum
        end program TestParser
        """)
      ),
      Arguments.of(
        FileType.GO,
        new String("""
        package main

        import "fmt"

        func printNumbers(limit int) {
          for i := 1; i <= limit; i++ {
            fmt.Printf("Number: %d\\n", i)
          }
        }

        func main() {
          fmt.Println("Testing the parser with Go code.")
          printNumbers(3)
        }
        """)
      ),
      Arguments.of(
        FileType.HTML,
        new String("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Test Page</title>
        </head>
        <body>
            <h1>Title</h1>
            <p>This is a paragraph.</p>
            <ul>
                <li>Item 1</li>
                <li>Item 2</li>
            </ul>
            <table>
                <tr>
                    <td>1</td>
                    <td>Alice</td>
                </tr>
            </table>
            <a href="#">Link</a>
        </body>
        </html>
        """)
      ),
      Arguments.of(
        FileType.JAVA,
        new String("""
        public class ParserTest {

            public static void printNumbers(int limit) {
                for (int i = 1; i <= limit; i++) {
                    System.out.println("Number: " + i);
                }
            }

            public static void main(String[] args) {
                System.out.println("Testing the parser with Java code.");
                printNumbers(3);
            }
        }
        """)
      ),
      Arguments.of(
        FileType.JAVASCRIPT,
        new String("""
        function printNumbers(limit) {
            for (let i = 1; i <= limit; i++) {
                if (i % 2 === 0) {
                    console.log(`Number ${i} is even.`);
                } else {
                    console.log(`Number ${i} is odd.`);
                }
            }
        }

        console.log('Testing the parser with JavaScript code.');
        printNumbers(5);
        """)
      ),
      Arguments.of(
        FileType.MAKE,
        new String("""
        CC=gcc
        CFLAGS=-I.

        all: hello

        hello: hello.o
          $(CC) -o hello hello.o

        hello.o: hello.c
          $(CC) -c hello.c $(CFLAGS)

        clean:
          rm -f *.o hello

        .PHONY: all clean
        """)
      ),
      Arguments.of(
        FileType.PERL,
        new String("""
        #!/usr/bin/perl

        use strict;
        use warnings;

        print "Hello, Perl Parser Test!\n";

        my $limit = 5;
        for my $i (1..$limit) {
            print "$i\n";  # Print each number

            if ($i % 2 == 0) {
                print "$i is even.\n";
            } else {
                print "$i is odd.\n";
            }
        }
        """)
      ),
      Arguments.of(
        FileType.PHP,
        new String("""
        <?php

        echo "Testing the parser with PHP code.\n";

        $limit = 3;

        for ($i = 1; $i <= $limit; $i++) {
            if ($i % 2 == 0) {
                echo $i . " is even.\n";
            } else {
                echo $i . " is odd.\n";
            }
        }
        ?>
        """)
      ),
      Arguments.of(
        FileType.PYTHON,
        new String("""
        import math
        
        class MathOperations:
          def add_numbers(self, num1, num2):
            return num1 + num2

          def calculate_circle_area(self, radius):
            return math.pi * radius ** 2

        math_ops = MathOperations()
        result = math_ops.add_numbers(5, 3)
        circle_area = math_ops.calculate_circle_area(10)
        print(f"Result: {result}, Circle Area: {circle_area}")
        """)
      ),
      Arguments.of(
        FileType.R,
        new String("""
        print("Testing the parser with R code")

        numbers <- 1:5

        is_even <- function(x) {
          return(x %% 2 == 0)
        }

        for (number in numbers) {
          if (is_even(number)) {
            print(paste(number, "is even"))
          } else {
            print(paste(number, "is odd"))
          }
        }
        """)
      ),
      Arguments.of(
        FileType.RUST,
        new String("""
        fn main() {
            println!("Testing the parser with Rust code.");

            let limit = 3;
            for i in 1..=limit {
                print_number(i);
            }
        }

        fn print_number(n: i32) {
            if n % 2 == 0 {
                println!("{} is even.", n);
            } else {
                println!("{} is odd.", n);
            }
        }
        """)
      ),
      Arguments.of(
        FileType.SQL,
        new String("""
        CREATE TABLE People (
            ID INT PRIMARY KEY,
            Name VARCHAR(100),
            Age INT
        );

        INSERT INTO People (ID, Name, Age) VALUES (1, 'Alice', 30);
        INSERT INTO People (ID, Name, Age) VALUES (2, 'Bob', 25);
        INSERT INTO People (ID, Name, Age) VALUES (3, 'Charlie', 35);

        SELECT * FROM People;

        UPDATE People SET Age = Age + 1 WHERE Name = 'Alice';

        DELETE FROM People WHERE Name = 'Bob';
        """)
      ),
      Arguments.of(
        FileType.SWIFT,
        new String("""
        import Foundation

        func printNumbers(upTo limit: Int) {
            for i in 1...limit {
                if i % 2 == 0 {
                    print("\\(i) is even.")
                } else {
                    print("\\(i) is odd.")
                }
            }
        }

        print("Testing the parser with Swift code.")
        printNumbers(upTo: 5)
        """)
      ),
      Arguments.of(
        FileType.TYPESCRIPT,
        new String("""
        interface Person {
            name: string;
            age: number;
        }

        class Greeter {
            greet(person: Person): void {
                console.log(`Hello, ${person.name}, who is ${person.age} years old.`);
            }
        }

        const greeter = new Greeter();
        const people: Person[] = [
            { name: "Alice", age: 30 },
            { name: "Bob", age: 25 }
        ];

        for (const person of people) {
            greeter.greet(person);
        }
        """)
      ),
      Arguments.of(
        FileType.YAML,
        new String("""
        appConfig:
          name: SampleApp
          version: 1.2.3
          enabled: true

        services:
          - name: database
            type: postgresql
            port: 5432
          - name: cache
            type: redis
            port: 6379

        environments:
          development:
            debug: true
            logLevel: debug
          production:
            debug: false
            logLevel: error
        """)
      )
    );
  }

  @Test
  public void testSupportsFileTypes() {
    FileType[] supportedFileTypes = {
        FileType.BASH, FileType.C, FileType.COMMONLISP, FileType.CSHARP, FileType.DOCKERFILE,
        FileType.FORTRAN, FileType.GO, FileType.HTML, FileType.JAVA, FileType.MAKE, FileType.JAVASCRIPT,
        FileType.PERL, FileType.PHP, FileType.PYTHON, FileType.R, FileType.RUST,
        FileType.SQL, FileType.SWIFT, FileType.TYPESCRIPT, FileType.YAML
    };
    for(FileType type : supportedFileTypes){
      assertTrue(TreeSitterParser.supportsFileType(type));
    }

    FileType[] unsupportedFileTypes = {
        FileType.VISUALBASIC, FileType.VBSCRIPT
    };
    for(FileType type : unsupportedFileTypes){
      assertFalse(TreeSitterParser.supportsFileType(type));
    }
  }

  @Test
  public void testSupportsFiles(@TempDir File tempDir) throws IOException {
    String[] supportedExtensions = {
        ".sh", ".c", ".lisp", ".cs", ".f", ".go", ".html", ".java", ".pl", //".js",
        ".php", ".py", ".r", ".rs", ".sql", ".swift", ".yaml", ".json", ".geojson", ".topojson" , ".map", ".dockerfile"
    };
    File tmpFile;
    for(String extension : supportedExtensions){
      tmpFile = new File(tempDir, "foo"+extension);
      tmpFile.createNewFile();
      System.out.println("extension: " + extension);
      assertTrue(TreeSitterParser.supportsFile(tmpFile));
    }

    String [] supportedFilenames = {
        "Makefile", "Dockerfile"
    };
    for(String filename : supportedFilenames) {
      tmpFile = new File(tempDir, filename);
      tmpFile.createNewFile();
      assertTrue(TreeSitterParser.supportsFile(tmpFile));
    }

    String[] unsupportedExtensions = {
        ".vb", ".vbs"
    };
    for(String extension : unsupportedExtensions){
      tmpFile = new File(tempDir, "foo"+extension);
      tmpFile.createNewFile();
      assertFalse(TreeSitterParser.supportsFile(tmpFile));
    }
  }

  @ParameterizedTest
  @MethodSource("testData")
  public void testParse(FileType fileType, String text) {
    TreeSitterParser parser = new TreeSitterParser();
    TSTree tree = parser.parse(text, fileType);
    assertNotNull(tree);

    TSNode rootNode = tree.getRootNode();
    assertFalse(rootNode.isMissing());
    assertFalse(rootNode.hasError());
    assertNotEquals(rootNode.getChildCount(), 0);
  }
}
