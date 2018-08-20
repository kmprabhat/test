/**
 * 
 */
package com.exercise.prabhat.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.prabhat.service.CrawlerService;

/**
 * @author prabhatkumar.mishra
 *
 */

@Path("/crawl")
@RestController
@Produces(MediaType.APPLICATION_JSON)
public class CrawlerApi {

	@Autowired
	private CrawlerService crawlerService; 
	
	@GET
	@Path("/")
	public Response crawl(@QueryParam("url") String url) {
		if(null == url) {
			url = "https://wiprodigital.com";
		}
		return Response.ok().entity(crawlerService.crawl(url)).build();
	}
}