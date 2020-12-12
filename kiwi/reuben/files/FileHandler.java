
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

    // Object variables
    private File file = null;

    // Constructors
    public FileHandler() {}

    public FileHandler(String path) {
        this.file = new File(path);
    }

    public FileHandler(File file) {
        this.file = file;
    }

    // Method to change file directory
    public void changeDirectory(String path) {
        this.file = new File(path);

        System.out.println(this.file.getAbsolutePath());
    }

    public void changeDirectory(File file) {
        this.file = file;
    }

    // Method to return file directory
    public String getDirectory() throws FileHandlerException {
        if(file != null && file.exists()) {
            return file.getPath();
        } else {
            throw new FileHandlerException("File doesn't exist!");
        }
    }

    public String getAbsoluteDirectory() throws FileHandlerException {
        if(file != null && file.exists()) {
            return file.getAbsolutePath();
        } else {
            throw new FileHandlerException("File doesn't exist!");
        }
    }

    // Method to check for files (returns a boolean value)
    public boolean checkForFile() throws FileHandlerException {
        if(file != null) {
            return file.exists();
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    public boolean checkForFile(boolean createNewFile) throws FileHandlerException {
        if(file != null) {
            if (file.exists()) {
                return true;
            } else {
                if (createNewFile) {
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
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    // Method to create new files (returns a boolean value)
    public boolean newFile() throws FileHandlerException {
        if(file != null) {
            try {
                boolean createdNewFile = file.createNewFile();

                return file.exists();
            } catch (IOException e) {
                return false;
            }
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    // Method to delete files (returns a boolean value)

    public boolean deleteFile() throws FileHandlerException {
        if(file != null) {
            if (!file.exists()) {
                throw new FileHandlerException("ERROR: file " + file.getPath() + " not found");
            }

            boolean deletedFile = file.delete();
            return !file.exists();
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    // Method to read one specific line from a file (returns a string value)
    // If user inputs -1 for the "line" parameter, it will return the last line of the file

    public String readLine(int line) throws IOException, FileHandlerException {
        if(file != null) {
            if (!file.exists()) throw new FileHandlerException("ERROR: file " + file.getPath() + " not found");

            Scanner fileScanner = new Scanner(file);
            if (line == -1) {
                String last = "";
                while (fileScanner.hasNextLine()) {
                    last = fileScanner.nextLine();
                }
                return last;
            }

            try {
                return Files.readAllLines(Paths.get(file.getPath())).get(line - 1);
            } catch (IndexOutOfBoundsException e) {
                return "ERROR: line " + line + " not found";
            }
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    // Method to read multiple lines from a file (returns a string value)

    /*

        Note that if user inputs -1 for the "beginAtLine" parameter, it will return the last line of the
        file and if the user inputs -1 for the "stopAtLine" parameter, it will return the lines from
        the beginAtLine line until the last line of the file

     */

    public String readLine(int beginAtLine, int stopAtLine) throws FileNotFoundException, FileHandlerException {
        if(file != null) {
            if (!file.exists()) throw new FileHandlerException("ERROR: file " + file.getPath() + " not found");

            Scanner fileScanner = new Scanner(file);
            String returns = "";

            if (beginAtLine < 1 && beginAtLine != -1) {
                return "ERROR: line " + beginAtLine + " not found";
            } else if (beginAtLine == -1) {
                String last = "";
                while (fileScanner.hasNextLine()) {
                    last = fileScanner.nextLine();
                }
                return last;
            }

            if (stopAtLine == -1) {
                int counter = 1;
                while (fileScanner.hasNextLine()) {
                    if (counter >= beginAtLine) {
                        returns = returns.concat(fileScanner.nextLine() + "\n");
                    } else {
                        fileScanner.nextLine();
                    }
                    counter += 1;
                }
            }

            for (int i = 1; i <= stopAtLine; i++) {
                try {
                    if (i >= beginAtLine) {
                        returns = returns.concat(fileScanner.nextLine() + "\n");
                    } else {
                        fileScanner.nextLine();
                    }
                } catch (java.util.NoSuchElementException e) {
                    return "ERROR: line " + stopAtLine + " not found";
                }
            }

            return returns;
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    // Method to return all lines of a specified file

    public String readAllLines() throws FileNotFoundException, FileHandlerException {
        if(file != null) {
            if (!file.exists()) throw new FileHandlerException("ERROR: file " + file.getPath() + " not found");

            Scanner fileScanner = new Scanner(file);
            String returns = "";

            while (fileScanner.hasNextLine()) {
                returns = returns.concat(fileScanner.nextLine() + "\n");
            }

            return returns;
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    // Method to write some text to a file (overwrites existing file)
    public boolean write(String text) throws IOException, FileHandlerException {
        if(file != null) {
            if (!file.exists()) throw new FileHandlerException("ERROR: file " + file.getPath() + " not found");

            FileWriter fileWriter = new FileWriter(file);

            try {
                fileWriter.write(text);
                fileWriter.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }

    // Method to append some text to a file
    public boolean append(String text) throws IOException, FileHandlerException {
        if(file != null) {
            if (!file.exists()) throw new FileHandlerException("ERROR: file " + file.getPath() + " not found");

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
            } catch (IOException e) {
                return false;
            }
        } else {
            throw new FileHandlerException("File doesn't exist");
        }
    }
}
