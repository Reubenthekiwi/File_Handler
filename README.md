# File_Handler
A simple Java file handling tool to make handling files easier

Made by Reuben Prabaswara Windukusuma aka Kiwi

Methods:
  - Boolean values:
    
    // Methods to check for files (returns a boolean value) 
    > checkForFile(String path)
    > checkForFile(File file)
    > checkForFile(String path, boolean createNewFile)
    > checkForFile(File file, boolean createNewFile)
    
    // Methods to create new files (returns a boolean value)
    > newFile(String path)
    > newFile(File file)
    
    // Methods to delete files (returns a boolean value)
    > deleteFile(String path)
    > deleteFile(File file)
    
    // Methods to write some text to a file (overwrites existing file)
    > write(String path, String text)
    > write(File file, String text)
    
    // Methods to append some text to a file
    > append(String path, String text)
    > append(File file, String text)
    
  - String values:
    
    // Methods to read one specific line from a file (returns a string value), if user inputs -1 for the "line" parameter, it will return the last line of the file
    > readLine(String path, int line)
    > readLine(File file, int line)
    
    // Methods to read multiple lines from a file (returns a string value). Note that if user inputs -1 for the "beginAtLine" parameter, it will return the last line of the file and if the user inputs -1 for the "stopAtLine" parameter, it will return the lines fromthe beginAtLine line until the last line of the file
    > readLine(String path, int beginAtLine, int stopAtLine)
    > readLine(File file, int beginAtLine, int stopAtLine)
    
    // Methods to return all lines of a specified file
    > readAllLines(String path)
    > readAllLines(File file)
