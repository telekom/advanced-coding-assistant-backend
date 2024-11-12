package com.telekom.ai4coding.chatbot.graph;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class KnowledgeGraphSetup {
    public static final String PYTHON_FILE_CONTENT = """
        print("Hello world")
        """;

    public static final String JAVA_FILE_CONTENT = """
        public class test {
          public static void main(String[] args) {
            System.out.println("Hello world");
          }
        }
        """;

    public static final String C_FILE_CONTENT = """
        #include <stdio.h>
        int main() {
          printf("Hello world");
          return 0;
        }
        """;

    public static final String TXT_FILE_CONTENT = """
        Hello world from txt file.
    """;

    // PDF writer does not handle newline characters, so no
    // newlines here.
    public static final String PDF_FILE_CONTENT = """
        Hello world from PDF file.""";


    public static KnowledgeGraph setup(KnowledgeGraphBuilder knowledgeGraphBuilder, File tempDir) throws IOException {
        /*
        These directory and files are structured in the following way:

        {tempDir}
        ├── foo
        │   ├── bar
        │   │   ├── test.c
        │   │   ├── test.java
        │   │   ├── test.pdf
        │   │   └── test.txt
        │   └── baz
        └── test.py
        */
        File fooDir = new File(tempDir, "foo");
        fooDir.mkdir();

        File pythonFile = new File(tempDir, "test.py");
        pythonFile.createNewFile();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pythonFile), "utf-8"))) {
            writer.write(PYTHON_FILE_CONTENT);
        }

        File barDir = new File(fooDir, "bar");
        barDir.mkdir();
        File javaFile = new File(barDir, "test.java");
        javaFile.createNewFile();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(javaFile), "utf-8"))) {
            writer.write(JAVA_FILE_CONTENT);
        }
        File cFile = new File(barDir, "test.c");
        cFile.createNewFile();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cFile), "utf-8"))) {
            writer.write(C_FILE_CONTENT);
        }
        File txtFile = new File(barDir, "test.txt");
        txtFile.createNewFile();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "utf-8"))) {
            writer.write(TXT_FILE_CONTENT);
        }
        File pdfFile = new File(barDir, "test.pdf");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        contentStream.showText(PDF_FILE_CONTENT);
        contentStream.endText();
        contentStream.close();
        document.save(pdfFile);
        document.close();

        File bazDir = new File(fooDir, "baz");
        bazDir.mkdir();

        return knowledgeGraphBuilder.buildGraphFromDir(tempDir);
    }
}
