package com.telekom.ai4coding.chatbot.treesitter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTypeTest {

    @Test
    void testFromFileName_withValidExtensions() {
        assertEquals(FileType.JAVA, FileType.fromFileName("MyClass.java"));
        assertEquals(FileType.PYTHON, FileType.fromFileName("script.py"));
        assertEquals(FileType.JAVASCRIPT, FileType.fromFileName("app.js"));
        assertEquals(FileType.CSHARP, FileType.fromFileName("program.cs"));
        assertEquals(FileType.MARKDOWN, FileType.fromFileName("README.md"));
        assertEquals(FileType.TYPESCRIPT, FileType.fromFileName("component.ts"));
        assertEquals(FileType.TYPESCRIPT, FileType.fromFileName("component.tsx"));
        assertEquals(FileType.HTML, FileType.fromFileName("index.html"));
        assertEquals(FileType.BASH, FileType.fromFileName("script.sh"));
        assertEquals(FileType.C, FileType.fromFileName("source.c"));
        assertEquals(FileType.SQL, FileType.fromFileName("query.sql"));
        assertEquals(FileType.YAML, FileType.fromFileName("config.yaml"));
        assertEquals(FileType.YAML, FileType.fromFileName("config.yml"));
        assertEquals(FileType.COMMONLISP, FileType.fromFileName("code.lisp"));
        assertEquals(FileType.COMMONLISP, FileType.fromFileName("code.lsp"));
        assertEquals(FileType.COMMONLISP, FileType.fromFileName("code.l"));
        assertEquals(FileType.COMMONLISP, FileType.fromFileName("code.cl"));
        assertEquals(FileType.JSON, FileType.fromFileName("config.json"));
        assertEquals(FileType.JSON, FileType.fromFileName("config.map"));
        assertEquals(FileType.JSON, FileType.fromFileName("config.topojson"));
        assertEquals(FileType.JSON, FileType.fromFileName("config.geojson"));
        assertEquals(FileType.DOCKERFILE, FileType.fromFileName("docker.dockerfile"));
    }

    @Test
    void testFromFileName_withoutStandardExtensions() {
        // special files that do not have an extension
        assertEquals(FileType.DOCKERFILE, FileType.fromFileName("Dockerfile"));
        assertEquals(FileType.MAKE, FileType.fromFileName("Makefile"));
    }

    @Test
    void testFromFileName_withNull() {
        assertEquals(FileType.OTHER, FileType.fromFileName(null));
    }

    @Test
    void testFromFileName_withNoExtension() {
        assertEquals(FileType.OTHER, FileType.fromFileName("fileWithoutExtension"));
    }

    @Test
    void testFromFileName_withCaseSensitivity() {
        // file systems are typically case-insensitive, so making sure the comparison is as well, except "Dockerfile" and "Makefile" file types
        assertEquals(FileType.DOCKERFILE, FileType.fromFileName("Dockerfile"));
        assertNotEquals(FileType.DOCKERFILE, FileType.fromFileName("dockerfile"));
        assertNotEquals(FileType.DOCKERFILE, FileType.fromFileName("DOCKERFILE"));
        assertEquals(FileType.MAKE, FileType.fromFileName("Makefile"));
        assertNotEquals(FileType.MAKE, FileType.fromFileName("makefile"));
        assertNotEquals(FileType.MAKE, FileType.fromFileName("MAKEFILE"));
        assertEquals(FileType.PYTHON, FileType.fromFileName("SCRIPT.PY"));
        assertEquals(FileType.JAVA, FileType.fromFileName("MyClass.JAVA"));
    }
}

