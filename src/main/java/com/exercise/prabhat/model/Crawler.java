package com.exercise.prabhat.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crawler {

	private Set<String> crawledLinks;
	private List<String> linksToCrawl;
	protected String domainUrl;

	public Crawler() {
		crawledLinks = new HashSet<String>();
		linksToCrawl = new ArrayList<String>();
	}

	public void addUrl(String url) {
		if (null != url)
			this.linksToCrawl.add(url);
	}

	// Getters and setters
	public Set<String> getCrawledLinks() {
		return crawledLinks;
	}

	public void setCrawledLinks(Set<String> crawledLinks) {
		this.crawledLinks = crawledLinks;
	}

	public List<String> getLinksToCrawl() {
		return linksToCrawl;
	}

	public void setLinksToCrawl(List<String> linksToCrawl) {
		this.linksToCrawl = linksToCrawl;
	}

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}

}
