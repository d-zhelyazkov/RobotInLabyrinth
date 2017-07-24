This project demonstrates a simple path planner in a maze.
The planning algorithm is named Gasolization:
   - based on BFS
   - for variable-sized robot
   - research paper: http://www.wseas.us/e-library/conferences/2015/Konya/ACS/ACS-04.pdf
   
The program accepts image of a robot in a maze and produces path for the robot to follow toward its goal.
It also graphically simulates the robot motion. 

Project requirements:
    - JDK - recommended version 1.7
    - maven build tool
 
Project input:
    - single image file in one of these formats:
        * JPEG, GIF, PNG - image representation of the robot in a maze and its goal
            - see ./example_mazes directory for examples
        * text file - a parsed maze from an image of the upper formats in a specific format

Project output:
    - a parsed maze in a text file (with '_parsed' suffix) - text implementation of the maze in a specific format
    - a solved maze in a text file ('_solved' suffix) - as above but with marked path if available
    - robot commands in a text file ('_commands' suffix) - a path if available represented by directions and step counts
    - some statistics in a text file ('_statistics' suffix) - some timings and maze parameters
    - graphic simulation of the robot motion

Project configuration:
    - config.properties file with the following properties:
        * IMAGE_CHOOSER_FLAG - enables popup image selection window for image files
        * MONITOR_SIMULATION_FLAG - enables the animated simulation
        * SWING_SUPPORTED flag - enables popup warning messages; auto-enabled if one of the above flags is set
        * FILE_NAME - input file to process if IMAGE_CHOOSER_FLAG is not set
        * PICTURE_FILE_FLAG - tells the type of the file pointed by FILE_NAME - 1 for image, 0 for text
	
Project build:
    - mvn clean install
    
Project run:
    1. cd target/classes
    2. java Main/RobotInLabyrinthByPicture