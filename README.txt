Data Intensive Computing - CSE587 - Lab4

README

This file contains the instructions on how to run the MapReduce jobs with the given JAR files and Input.
The Input files are contained in /Input, and the sub-folders are divided according to the activities performed.
The JAR files are contained in /JARs and divided into sub-folders by activity.
The /Output folder contains sample outputs for each of the activity, again branched using folders
The source code files are contained in /SourceFiles.
Plots for performance and scalability of MapReduce on Latin Texts are in the file LatinTextPlots.pdf

Following are the commands required to perform the respective activities

Commands for

Part 1 - WordCount

Create a new directory for input files with "hdfs dfs –mkdir –p ~/input/"
Copy the directory /Input/Part1 using the hdfs dfs -put command
From the folder containing the JAR file (/JARs/Part1) , run the command

hadoop jar wc.jar WordCount ~/yourinputfolder/Part1  ~/youroutputfolder


Note: The Tag Cloud for this activity is in the output folder, and the code for creating the tag cloud is in the folder 'Code for TweetWordCount.
To create the tag cloud, I have used the output from Hadoop and copied it to a CSV file 'EconomyWC.csv', and used it in an R iPython Notebook.



Part 2 - Pairs
Create a new directory for input files with "hdfs dfs –mkdir –p ~/input/"
Copy the directory /Input/Part2 using the hdfs dfs -put command
From the folder containing the JAR file (/JARs/Part2/Pairs) run the command

hadoop jar pairs.jar WordCountPairs ~/yourinputfolder/Part2  ~/youroutputfolder


Part 2 - Stripes

Create a new directory for input files with "hdfs dfs –mkdir –p ~/input/"
Copy the directory /Input/Part2 using the hdfs dfs -put command
From the folder containing the JAR file (/JARs/Part2/Stripes) run the command

hadoop jar stripes.jar WordCountStripes ~/yourinputfolder/Part2  ~/youroutputfolder



Part 3 - Latin Text WordCount

Create a new directory for input files with "hdfs dfs –mkdir –p ~/input/"

Note: For this activity, you may want to run the program with multiple corpus sizes. 
Hence I have included multiple folders in the input folder, with various number of files in each (mentioned in the name of the folder).

Copy the directory /Input/Part3/2 files/ using the hdfs dfs -put command
From the folder containing the JAR file (/JARs/Part3) run the command

hadoop jar latcount.jar WordCountLatin ~/yourinputfolder/Part3/2 files  ~/youroutputfolder

Note: Please make sure the new_lemmatizer.csv file is present together along with the JAR file. 
I have added it along with the JAR file in the submission, but if you copy the JAR to some other location,
please copy the new_lemmatizer.csv file with the JAR file too.



Part 4 - Latin Text Co-Occurence

The output for these 2 parts is as follows:

2Gram
For 2 words W1 and W2, having Lemmas Lem1W1,Lem2W1, Lem1W2 and Lem2W2, the output is Lem1W1,Lem1W2  <location1><location2>....<location n>

Lem1W1,Lem1W2  <location1><location2>....<location n>
Lem1W1,Lem2W2  <location1><location2>....<location n>
Lem2W1,Lem1W2  <location1><location2>....<location n>
Lem2W1,Lem2W2  <location1><location2>....<location n>

This pattern continues for 3Grams as well, like
Lem1W1,Lem1W2,Lem1W3  <location1><location2>....<location n>
Lem1W1,Lem1W2,Lem2W3  <location1><location2>....<location n>
and so on

a) 2gram

Create a new directory for input files with "hdfs dfs –mkdir –p ~/input/"
Copy the directory /Input/Part4/ using the hdfs dfs -put command
From the folder containing the JAR file (/JARs/Part4/2Gram) run the command

hadoop jar latcooc.jar WordCoocLatin ~/yourinputfolder/Part4/2Gram  ~/youroutputfolder

Note: Please make sure the new_lemmatizer.csv file is present together along with the JAR file. 
I have added it along with the JAR file in the submission, but if you copy the JAR to some other location,
please copy the new_lemmatizer.csv file with the JAR file too.


b) 3gram

Create a new directory for input files with "hdfs dfs –mkdir –p ~/input/"
Copy the directory /Input/Part4/ using the hdfs dfs -put command
From the folder containing the JAR file (/JARs/Part4/3Gram) run the command

hadoop jar latcooc.jar WordCooc3Gram ~/yourinputfolder/Part4/3Gram  ~/youroutputfolder

Note: Please make sure the new_lemmatizer.csv file is present together along with the JAR file. 
I have added it along with the JAR file in the submission, but if you copy the JAR to some other location,
please copy the new_lemmatizer.csv file with the JAR file too.






