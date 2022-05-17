package operations;

import com.google.gson.Gson;
import data_structures.ExpandedGrid;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExpandedGridIO
{
    public static ExpandedGrid loadBoardFromResources(String fileName)
    {
        String expandedGridJson = loadResourceFile(fileName);
        ExpandedGrid expandedGrid = new Gson().fromJson(expandedGridJson, ExpandedGrid.class);

        return expandedGrid;
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

    public static ExpandedGrid loadExpandedGrid()
    {
        File file = pickFile();
        ExpandedGrid expandedGrid = loadJsonFile(file);
        return expandedGrid;
    }

    private static ExpandedGrid loadJsonFile(File file)
    {
        String board = loadBoard(file);
        return new Gson().fromJson(board, ExpandedGrid.class);
    }

    private static File pickFile()
    {
        JFileChooser jFileChooser = new JFileChooser();
        int resFile = jFileChooser.showOpenDialog(null);
        File file = jFileChooser.getSelectedFile();
        return file;
    }

    private static String loadBoard(File file)
    {
        String result = "";
        try
        {
            result = Files.readString(Path.of(file.getPath()));

        } catch (Exception ex)
        {
            System.out.println("File read exception: " + file.getName() + "\n" + ex.getMessage());
        }
        return result;
    }


}
