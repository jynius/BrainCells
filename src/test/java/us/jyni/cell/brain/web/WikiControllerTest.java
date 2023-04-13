/**
 * 
 */
package us.jyni.cell.brain.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

/**
 * @author jynius
 *
 */
@ExtendWith(SpringExtension.class)
class WikiControllerTest {

	private static final String LOAD_WIKI_URL = "http://localhost:8080/wiki/load1";
	
    private RestTemplate restTemplate = new RestTemplate();
    
	/**
	 * Test method for {@link us.jyni.cell.brain.web.WikiController#load()}.
	 */
	@Test
	void testLoad() {
		ResponseEntity<String> response = restTemplate.getForEntity(LOAD_WIKI_URL, String.class);
		assertEquals("complete!", response.getBody());
	}
}
