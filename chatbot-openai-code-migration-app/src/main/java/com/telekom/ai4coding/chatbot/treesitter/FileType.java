package com.telekom.ai4coding.chatbot.treesitter;

import java.nio.file.Paths;

public enum FileType {
    OTHER,
    BASH(".sh"),
    C(".c"),
    COMMONLISP(".lisp", ".lsp", ".l", ".cl"),
    CSHARP(".cs"),
    DOCKERFILE("Dockerfile", ".dockerfile"),
    FORTRAN(".f"),
    GO(".go"),
    HTML(".html"),
    JAVA(".java"),
    JAVASCRIPT(".js", ".jsx"),
    MAKE("Makefile"),
    MARKDOWN(".md"),
    PERL(".pl"),
    PHP(".php"),
    PYTHON(".py"),
    R(".r"),
    RUST(".rs"),
    SQL(".sql"),
    SWIFT(".swift"),
    TYPESCRIPT(".ts",".tsx"),
    VISUALBASIC(".vb"),
    VBSCRIPT(".vbs"),
    YAML(".yaml", ".yml"),
    JSON(".json", ".map", ".topojson", ".geojson");

    private final String[] extensions;

    FileType(String... extensions) {
      this.extensions = extensions;
    }

    public static FileType fromFileName(String filename) {
        if(filename == null){
            return OTHER;
        }

        //Making sure we only use the base fileName even if the method is called wrongly with a file path instead of only the base filename
        String baseFilename = Paths.get(filename).getFileName().toString();
        System.out.println(baseFilename);
        // Special cases for Dockerfile and Makefile: must match exactly "Dockerfile" or "Makefile" with case sensitivity
        if (baseFilename.equals("Dockerfile")) {
            return DOCKERFILE;
        } else if (baseFilename.equals("Makefile")) {
            return MAKE;
        }

        // For all other file types, check case-insensitively
        String lowerCaseFilename = baseFilename.toLowerCase();
        for (FileType fileType : FileType.values()) {
            if (fileType == MAKE) {
                continue;
            }
            for (String extension : fileType.extensions) {
                if (lowerCaseFilename.endsWith(extension)) {
                    return fileType;
                }
            }
        }
        return OTHER;
    }
}