
/*
    File handling tool to make handling files easier

    Made by Reuben Prabaswara Windukusuma aka Kiwi
*/

package kiwi.reuben.files;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileHandler {

    // Methods to check for files (returns a boolean value)
    public boolean checkForFile(String path) {
        File file = new File(path);

        return checkForFile(file);
    }

    public boolean checkForFile(File file) {
        return file.exists();
    }

    public boolean checkForFile(String path, boolean createNewFile) {
        File file = new File(path);

        return checkForFile(file, createNewFile);
    }

    public boolean checkForFile(File file, boolean createNewFile) {
        if(file.exists()) {
            return true;
        } else {
            if(createNewFile) {
                try {
                    boolean createdNewFile = file.createNewFile();

                    return file.exists();
                } catch (IOException e) {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    // Methods to create new files (returns a boolean value)
    public boolean newFile(File file) {
        try {
            boolean createdNewFile = file.createNewFile();

            return file.exists();
        } catch (IOException e) {
            return false;
        }
    }

    public boolean newFile(String path) {
        File file = new File(path);

        return newFile(file);
    }

    // Methods to delete files (returns a boolean value)

    public boolean deleteFile(File file) throws FileHandlerException {

        if(!file.exists()) {
            throw new FileHandlerException("ERROR: file "+file.getPath()+" not found");
        }

        boolean deletedFile = file.delete();
        return !file.exists();
    }

    public boolean deleteFile(String path) throws FileHandlerException {
        File file = new File(path);

        return deleteFile(file);
    }

    // Methods to read one specific line from a file (returns a string value)
    // If user inputs -1 for the "line" parameter, it will return the last line of the file
    public String readLine(String path, int line) throws IOException, FileHandlerException {
        File file = new File(path);

       return readLine(file, line);
    }

    public String readLine(File file, int line) throws IOException, FileHandlerException {

        if(!file.exists()) throw new FileHandlerException("ERROR: file "+file.getPath()+" not found");

        Scanner fileScanner = new Scanner(file);
        if(line == -1) {
            String last = "";
            while(fileScanner.hasNextLine()) {
                last = fileScanner.nextLine();
            }
            return last;
        }

        try {
            return Files.readAllLines(Paths.get(file.getPath())).get(line - 1);
        } catch(java.lang.IndexOutOfBoundsException e) {
            return "ERROR: line "+line+" not found";
        }
    }

    // Methods to read multiple lines from a file (returns a string value)

    /*

        Note that if user inputs -1 for the "beginAtLine" parameter, it will return the last line of the
        file and if the user inputs -1 for the "stopAtLine" parameter, it will return the lines from
        the beginAtLine line until the last line of the file

     */
    public String readLine(String path, int beginAtLine, int stopAtLine) throws FileNotFoundException, FileHandlerException {
        File file = new File(path);

        return readLine(file, beginAtLine, stopAtLine);
    }

    public String readLine(File file, int beginAtLine, int stopAtLine) throws FileNotFoundException, FileHandlerException {

        if(!file.exists()) throw new FileHandlerException("ERROR: file "+file.getPath()+" not found");

        Scanner fileScanner = new Scanner(file);
        String returns = "";

        if(beginAtLine < 1 && beginAtLine != -1) {
            return "ERROR: line "+beginAtLine+" not found";
        } else if(beginAtLine == -1) {
            String last = "";
            while(fileScanner.hasNextLine()) {
                last = fileScanner.nextLine();
            }
            return last;
        }

        if(stopAtLine == -1) {
            int counter = 1;
            while(fileScanner.hasNextLine()) {
                if(counter >= beginAtLine) {
                    returns = returns.concat(fileScanner.nextLine() + "\n");
                } else {
                    fileScanner.nextLine();
                }
                counter += 1;
            }
        }

        for(int i = 1; i <= stopAtLine; i++) {
            try {
                if (i >= beginAtLine) {
                    returns = returns.concat(fileScanner.nextLine() + "\n");
                } else {
                    fileScanner.nextLine();
                }
            } catch(java.util.NoSuchElementException e) {
                return "ERROR: line "+stopAtLine+" not found";
            }
        }

        return returns;
    }

    // Methods to return all lines of a specified file
    public String readAllLines(String path) throws FileNotFoundException, FileHandlerException {
        File file = new File(path);

        return readAllLines(file);
    }

    public String readAllLines(File file) throws FileNotFoundException, FileHandlerException {

        if(!file.exists()) throw new FileHandlerException("ERROR: file "+file.getPath()+" not found");

        Scanner fileScanner = new Scanner(file);
        String returns = "";

        while(fileScanner.hasNextLine()) {
            returns = returns.concat(fileScanner.nextLine() + "\n");
        }

        return returns;
    }

    // Methods to write some text to a file (overwrites existing file)
    public boolean write(File file, String text) throws IOException, FileHandlerException {

        if(!file.exists()) throw new FileHandlerException("ERROR: file "+file.getPath()+" not found");

        FileWriter fileWriter = new FileWriter(file);

        try {
            fileWriter.write(text);
            fileWriter.close();
            return true;
        } catch(IOException e) {
            return false;
        }
    }

    public boolean write(String path, String text) throws IOException, FileHandlerException {
        File file = new File(path);

        return write(file, text);
    }

    // Methods to append some text to a file
    public boolean append(File file, String text) throws IOException, FileHandlerException {

        if(!file.exists()) throw new FileHandlerException("ERROR: file "+file.getPath()+" not found");

        String newText = "";
        Scanner fileScanner = new Scanner(file);

        try {
            while (fileScanner.hasNextLine()) {
                newText = newText.concat(fileScanner.nextLine() + "\n");
            }
            newText = newText.concat(text);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(newText);
            fileWriter.close();

            return true;
        } catch(IOException e) {
            return false;
        }
    }

    public boolean append(String path, String text) throws IOException, FileHandlerException {
        File file = new File(path);

        return append(file, text);
    }

}
