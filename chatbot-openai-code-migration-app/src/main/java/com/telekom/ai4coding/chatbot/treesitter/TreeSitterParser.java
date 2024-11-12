package com.telekom.ai4coding.chatbot.treesitter;

import lombok.extern.slf4j.Slf4j;
import org.treesitter.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

@Slf4j
public class TreeSitterParser {
  private TSParser parser;

  // The mapping between the file type and the corresponding parser.
  // It is the list of language that TreeSitterParser supports.
  // New languages can be extended by adding a new row in this mapping.
  // Please add a new test sample to check if the parser actually works.
  private static final Map<FileType, TSLanguage> languages = Map.ofEntries(
      Map.entry(FileType.BASH, new TreeSitterBash()),
      Map.entry(FileType.C, new TreeSitterC()),
      Map.entry(FileType.COMMONLISP, new TreeSitterCommonlisp()),
      Map.entry(FileType.CSHARP, new TreeSitterCSharp()),
      Map.entry(FileType.DOCKERFILE, new TreeSitterDockerfile()),
      Map.entry(FileType.FORTRAN, new TreeSitterFortran()),
      Map.entry(FileType.GO, new TreeSitterGo()),
      Map.entry(FileType.HTML, new TreeSitterHtml()),
      Map.entry(FileType.JAVA, new TreeSitterJava()),
      Map.entry(FileType.JAVASCRIPT, new TreeSitterJavascript()),
      Map.entry(FileType.MAKE, new TreeSitterMake()),
      Map.entry(FileType.MARKDOWN, new TreeSitterMarkdown()),
      Map.entry(FileType.PERL, new TreeSitterPerl()),
      Map.entry(FileType.PHP, new TreeSitterPhp()),
      Map.entry(FileType.PYTHON, new TreeSitterPython()),
      Map.entry(FileType.R, new TreeSitterR()),
      Map.entry(FileType.RUST, new TreeSitterRust()),
      Map.entry(FileType.SQL, new TreeSitterSql()),
      Map.entry(FileType.SWIFT, new TreeSitterSwift()),
      Map.entry(FileType.TYPESCRIPT, new TreeSitterTypescript()),
      Map.entry(FileType.YAML, new TreeSitterYaml()),
      Map.entry(FileType.JSON, new TreeSitterJson())
  );

  public TreeSitterParser() {
    this.parser = new TSParser();
  }

  /**
   * Check if the file type is supported by the TreeSitterParser.
   * <p>
   * This method should be called to check if the file type is supported by TreeSitterParser.
   * 
   * @param  type The file type as {@code FileType}.
   * @return      If the file type is supported by TreeSitterParser.
   */
  public static boolean supportsFileType(FileType type) {
    return languages.containsKey(type);
  }

  /**
   * Check if the file is supported by the TreeSitterParser.
   * <p>
   * This method should be called to check if the file is supported by TreeSitterParser.
   * 
   * @param  file The file type as {@code File}.
   * @return      If the file is supported by TreeSitterParser.
   */
  public static boolean supportsFile(File file) {
    FileType type = FileType.fromFileName(file.getName());
    return supportsFileType(type);
  }

  private void setLanguage(FileType type) {
    TSLanguage language = languages.getOrDefault(type, null);
    if(language == null) {
      return;
    }
    parser.setLanguage(language);
  }

  /**
   * Parse the code given the file type.
   * 
   * @param  code The code as {@code String}.
   * @param  type The file type as {@code FileType}.
   * @return      The AST as {@code TSTree}, can be null if the file type is not supported.
   */
  public TSTree parse(String code, FileType type) {
    if(!supportsFileType(type)) {
      return null;
    }
    setLanguage(type);

    parser.reset();
    return parser.parseString(null, code);
  }

  /**
   * Parse the code given the filename.
   * 
   * @param  code     The code as {@code String}.
   * @param  filename The base filename as {@code String}. This is not supposed to handle file paths.
   *                  Behaviour is unknown when given a path instead of the base filename.
   * @return          The AST as {@code TSTree}, can be null if the file type extracted
   *                  from the filename is not supported.
   */
  public TSTree parse(String code, String filename) {
    FileType type = FileType.fromFileName(filename);
    return parse(code, type);
  }

  /**
   * tries to parse the code given as a file to utf-8, if it fails another try using ISO-8859-1 otherwise throws an error.
   *
   * @param  file     The {@code File} to parse.
   * @return          The AST as {@code TSTree}, can be null if the file type extracted
   *                  from the filename is not supported.
   */
  public TSTree parse(File file) {
    String code;
    try {
      // First, try to read the file as UTF-8
      code = Files.readString(file.toPath(), StandardCharsets.UTF_8);
    } catch (IOException utf8Exception) {
      try {
        // If UTF-8 parsing fails, try reading the file as ISO-8859-1
        code = Files.readString(file.toPath(), StandardCharsets.ISO_8859_1);
      } catch (IOException isoException) {
        // If both attempts fail, log the error and handle it
        log.error("Error reading file: {}. Either the type of the file you provided is not supported, or the file is not formatted in UTF-8 or ISO-8859-1.", file.getName(), isoException);
        return null;
      }
    }
    return parse(code, file.getName());
  }
}
