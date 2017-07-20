config file - must be in working directory
	SWING_SUPPORTED flag - popup warning messages
	IMAGE_CHOOSER_FLAG - popup image selection window
	PICTURE_FILE_FLAG and FILE_NAME - give us a default file with maze and its type - image or text
		if IMAGE_CHOOSER_FLAG is set to 1 , they are both ignored
	MONITOR_SIMULATION_FLAG - the animated simulation at the end

input file example:
	justAPath/justAMaze.txt or justAPath/justAMaze.jpg
output files:
	justAPath/justAMaze_parsed.txt - text implementation of the maze
	justAPath/justAMaze_solved.txt - same as the previous one but this time with marked elements
	justAPath/justAMaze_commands.txt - if path is found it contains directions and stepCounts
	justAPath/justAMaze_statistics.txt - some timings and maze parameters