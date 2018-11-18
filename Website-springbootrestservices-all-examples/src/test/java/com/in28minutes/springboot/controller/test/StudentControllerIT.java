package com.in28minutes.springboot.controller.test;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.StudentApplication;
import com.in28minutes.springboot.model.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Before
	public void before() {
		this.headers.add("Authorization", createHttpAuthenticationHeaderValue(
				"user1", "secret1"));
		this.headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testRetrieveStudentCourse() throws JSONException {

		final HttpEntity<String> entity = new HttpEntity<String>(null, this.headers);

		final ResponseEntity<String> response = this.restTemplate.exchange(
				createURLWithPort("/students/Student1/courses/Course1"),
				HttpMethod.GET, entity, String.class);

		final String expected = "{id:Course1,name:Spring,description:10 Steps}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void addCourse() {

		final Course course = new Course("Course1", "Spring", "10 Steps", Arrays
				.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));

		final HttpEntity<Course> entity = new HttpEntity<Course>(course, this.headers);

		final ResponseEntity<String> response = this.restTemplate.exchange(
				createURLWithPort("/students/Student1/courses"),
				HttpMethod.POST, entity, String.class);

		final String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/students/Student1/courses/"));

	}

	private String createURLWithPort(final String uri) {
		return "http://localhost:" + this.port + uri;
	}

	private String createHttpAuthenticationHeaderValue(final String userId,
			final String password) {

		final String auth = userId + ":" + password;

		final byte[] encodedAuth = Base64.encode(auth.getBytes(Charset
				.forName("US-ASCII")));

		final String headerValue = "Basic " + new String(encodedAuth);

		return headerValue;
	}

}
