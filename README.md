# File_Handler
A simple Java file handling tool to make handling files easier

Made by Reuben Prabaswara Windukusuma aka Kiwi

Variables:
  - File file

Constructors:
  - FileHandler() - creates an empty FileHandler object with a null file variable
  - FileHandler(File file) - creates a new FileHandler object and assign the file variable with the file parameter
  - FileHandler(String path) - creates a new FileHandler object and assign the file variable with a new file based on the path parameter

Methods:
  - Void methods:
    - changeDirectory(String path)
    - changeDirectory(File file)
  
  - Boolean values:
    
    // Methods to check for files (returns a boolean value) 
    - checkForFile()
    - checkForFile(boolean createNewFile)
    
    // Method to create new files (returns a boolean value)
    - newFile()
    
    // Method to delete files (returns a boolean value)
    - deleteFile()
    
    // Method to write some text to a file (overwrites existing file)
    - write(String text)
    
    // Method to append some text to a file
    - append(String text)
    
  - String values:
  
    // Method that returns the file path (throws an error if file is null)
    - getDirectory()
    - getAbsoluteDirectory()
    
    // Method to read one specific line from a file (returns a string value), if user inputs -1 for the "line" parameter, it will return the last line of the file
    - readLine(int line)
    
    // Method to read multiple lines from a file (returns a string value). Note that if user inputs -1 for the "beginAtLine" parameter, it will return the last line of the file and if the user inputs -1 for the "stopAtLine" parameter, it will return the lines fromthe beginAtLine line until the last line of the file
    - readLine(int beginAtLine, int stopAtLine)
    
    // Method to return all lines of a specified file
    - readAllLines()
