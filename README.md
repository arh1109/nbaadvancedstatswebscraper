# Web Scraper

This app scrapes www.nba.com's advanced statistics provided by Second Spectrum. Many categories of Player Tracking stats can be found at www.nba.com. To learn more about how Nba Player Tracking works you can visit https://en.wikipedia.org/wiki/Player_tracking_(National_Basketball_Association). This data is used for non-commercial purposes.

Right now the application only collects Catch and Shoot data from the 2021-2022 Regular Season. 

The output of this program is a text file containing a table of statistics for all applicable players related to that category.
The file uses the following date format -  SimpleDateFormat("yyyy-MM-dd HH:mm:ss") to name files according to the time the script was ran. 
