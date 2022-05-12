package operations;

import data_structures.Block3x3;
import data_structures.BoardFromString;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BoardIO
{
    private static String loadBoard(File file)
    {
        StringBuilder board = new StringBuilder();
        try
        {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine())
            {
                board.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return board.toString();
    }

    public static Block3x3 loadBoard()
    {
        JFileChooser jFileChooser = new JFileChooser();
        File file = jFileChooser.getSelectedFile();
        String board = loadBoard(file);
        Block3x3 block3x3 = BoardFromString.createBoard(board);
        return block3x3;
    }


    public static Block3x3 loadBoardFromResources(String fileName)
    {
        String board = loadResourceFile(fileName);
        Block3x3 block3x3 = BoardFromString.createBoard(board);
        return block3x3;
    }

    private static String loadResourceFile(String fileName)
    {
        StringBuilder result = new StringBuilder();

        ClassLoader classLoader = BoardIO.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                result.append(line).append("\n");
            }

        } catch (IOException e)
        {
            System.out.println("File read error: " + e.getMessage());
        }
        return result.toString();
    }

    public static void saveBoard(Block3x3 block3x3, String filePath)
    {

        try
        {
            File myObj = new File(filePath);
            boolean newFile = myObj.createNewFile();

            System.out.println("\n" + "File created: " + myObj.getName());

            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(block3x3.reportBlock3x3() + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + filePath);
        } catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Block3x3 loadBoardFromFile(String filePath)
    {
        String results = "";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                results += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Block3x3 block3x3 = BoardFromString.createBoard(results);
        return block3x3;
    }
}
