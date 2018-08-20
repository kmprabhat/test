# web-crawler-service

This project implements a simple synchronous web crawler as a spring-boot application. 

It uses jsoup library to fetch read and query on the webpages. 

* Implementation details: 
	
	Enabled to follow only the pages of same domain
	
	CrawlerApi exposes an api '/crawler' with an optional query param 'url'

* Build instructions: 
	
	Make sure your eclipse has Spring-Tool-Suite installed.
	
	Import the project as existing maven project in your eclipse and let the dependencies get installed
	
	Run it as Spring-Boot Application, and wait for the message: 'Started CrawlerApplication' on the console
	
	Alternatively, you can directly deploy the given war file on your tomcat server.
	
	Go to your browser and hit: http://localhost:8080/crawl (By default it will crawl for https://wiprodigital.com and give you the result as string array)
	
	Alternatively, you can hit with query parameter url: http://localhost:8080/crawl?url=https://blankslate.io (Don't forget to include protocol in the url)

* Further: 
	
	As I've developed this application within a few hour, it is in very basic, single-threaded (to keep it simple) and synchronous form. With more time it can be converted into multi-threaded model and if possible in async way. 
	Also the format of the response could be made as tree with different levels.
	
	Test cases are pretty basic and can be made extensive.
	  
	