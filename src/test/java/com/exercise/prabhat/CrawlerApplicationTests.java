package com.exercise.prabhat;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.ws.rs.core.Response;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exercise.prabhat.api.CrawlerApi;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrawlerApplicationTests {

	private final Logger			log	= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CrawlerApi api;

	@Test(expected = IllegalArgumentException.class)
	public void testApi_Fail_01() throws Exception {
		api.crawl("invalid");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testApi_Fail_02() throws Exception {
		Response response = api.crawl("http://invalid");
		assertTrue(response.getStatus() == 200);
		Set<String> result = (Set<String>) response.getEntity();
		log.info(result.toString());
		assertTrue(result != null);
		assertTrue(result.size() == 0);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testApi_Fail_03() throws Exception {
		Response response = api.crawl("http://invalid/");
		assertTrue(response.getStatus() == 200);
		Set<String> result = (Set<String>) response.getEntity();
		log.info(result.toString());
		assertTrue(result != null);
		assertTrue(result.size() == 0);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testApi_Success_01() throws Exception {
		Response response = api.crawl("http://www.blankwebsite.com/");
		assertTrue(response.getStatus() == 200);
		Set<String> result = (Set<String>) response.getEntity();
		log.info(result.toString());
		assertTrue(result != null);
		assertTrue(result.size() == 1);
		assertTrue(result.contains("http://www.blankwebsite.com"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testApi_Success_02() throws Exception {
		Response response = api.crawl("http://www.blankwebsite.com");
		assertTrue(response.getStatus() == 200);
		Set<String> result = (Set<String>) response.getEntity();
		log.info(result.toString());
		assertTrue(result != null);
		assertTrue(result.size() == 1);
		assertTrue(result.contains("http://www.blankwebsite.com"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testApi_Success_03() throws Exception {
		Response response = api.crawl("http://adrianboutel.com/");
		assertTrue(response.getStatus() == 200);
		Set<String> result = (Set<String>) response.getEntity();
		log.info(result.toString());
		assertTrue(result != null);
		assertTrue(result.size() == 9);
		assertTrue(result.contains("http://adrianboutel.com"));
		assertTrue(result.contains("http://adrianboutel.com/reading-diary/what-is-social-construction.html"));

	}

}
