package com.exercise.prabhat.service;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exercise.prabhat.model.Crawler;
import com.exercise.prabhat.util.Constants;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	private final Logger			log	= LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Set<String> crawl(String url) {

		long startTime = new Date().getTime();
		url = extractCoreUrl(url);
		// Set params
		Crawler crawler = new Crawler();
		crawler.setDomainUrl(url);
		crawler.addUrl(url);

		while (crawler.getLinksToCrawl().size() > 0) {
			String nextUrl = crawler.getLinksToCrawl().remove(0); // Get first link and crawl
			nextUrl = extractCoreUrl(nextUrl);
			if (!shouldCrawl(crawler, nextUrl))
				continue; // Check if it should be crawled
			try {
				// System.out.println(nextUrl);
				Document document = Jsoup.connect(nextUrl).get(); // Get the content
				crawler.getCrawledLinks().add(nextUrl); // add to the crawled list				
				Elements linksOnPage = document.select("a[href]"); // look for links
				for (Element page : linksOnPage)
					crawler.addUrl(page.attr("abs:href")); // add all links to links to crawl
			} catch (IOException e) {
				log.error("For '" + nextUrl + "': " + e.getMessage());
			}

		}
		log.info("Completed crawling for "+ crawler.getDomainUrl() +" in " + (new Date().getTime() - startTime) + "ms.");
		return crawler.getCrawledLinks();
	}

	private boolean shouldCrawl(Crawler crawler, String url) {

		// Check for the same domain
		if (null != crawler.getDomainUrl() && !url.startsWith(crawler.getDomainUrl()))
			return false;

		// Check if already crawled
		if (crawler.getCrawledLinks().contains(url))
			return false;

		// Check for pdf/image links
		for (String ext : Constants.FILTER_EXTNS)
			if (url.endsWith(ext))
				return false;

		return true;
	}

	private String extractCoreUrl(String url) {

		if (null != url) {

			// Remove query params
			if (url.contains("?")) {
				url = url.split("\\?")[0];
			}

			// Remove element-ids
			if (url.contains("#")) {
				url = url.split("\\#")[0];
			}

			// Remove ending slash
			if (url.endsWith("/")) {
				url = url.substring(0, url.length() - 1);
			}
		}
		return url;
	}

}
