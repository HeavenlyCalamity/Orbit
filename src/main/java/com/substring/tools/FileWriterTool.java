package com.substring.tools;

import dev.langchain4j.agent.tool.Tool;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterTool {
    @Tool(name = "writeToFile")
    public void writeToFile(String content, String fileName) throws IOException {
        IO.println("writing to file");
        Path path = Path.of("files", fileName);
        Files.createDirectories(path.getParent());
        FileWriter fileWriter = new FileWriter( "files/" + fileName);
        try {

            fileWriter.write(content);

            fileWriter.close();
        } catch (Exception e) {
            IO.println("Error writing to file: " + e.getMessage());
        }
    }
}
