package com.pnm.kube.kube;

import java.io.StringReader;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;

@RestController
public class HelloKube {
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/welcome-vets")
	public String welcome() {
		String message = "response from customer-service";
		try {
			System.err.println("1Hello in welcome");
			message = restTemplate.getForObject("http://vets-service:8888/hello", String.class)
					+ "vets-service response";
			System.err.println("2Hello in welcome");

		} catch (Exception e) {
			try {

				System.out.println("1 Exception " + e.getMessage());

				message = restTemplate.getForObject("http://localhost:8888/hello", String.class) + "localhost response";
				System.out.println("3 Exception " + e.getMessage());

			} catch (Exception e2) {
				System.out.println("2 Exception " + e2.getMessage());
			}
		}
		System.out.println("5 Exception ");

		return message;
	}

	@RequestMapping("/dear")
	@ResponseBody
	public String welcome1() {
		return "dear.....";
	}

	@RequestMapping("/")
	@ResponseBody
	public String welcome2() {
		return "2dear.....";
	}

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	@RequestMapping("/welcome-visits")
	public String welcomeVisits() {
		String message = "response from visits-service";
		try {
			System.err.println("11Hello in welcome");
			message = restTemplate.getForObject("http://visits-service:9999/hellovisit", String.class)
					+ "visits-service response";
			System.err.println("22Hello in welcome");

		} catch (Exception e) {
			try {

				System.out.println("11 Exception " + e.getMessage());

				message = restTemplate.getForObject("http://localhost:9999/hellovisit", String.class)
						+ "localhost response";
				System.out.println("32 Exception " + e.getMessage());

			} catch (Exception e2) {
				System.out.println("22 Exception " + e2.getMessage());
			}
		}
		System.out.println("55 Exception ");

		return message;
	}

	@CrossOrigin
	@PostMapping(path = "/esp-mock", produces = "application/xml")
	public ResponseEntity<ModifyExecutableOrderResponse> get(@RequestBody String requestPayload,
			@RequestHeader Map<String, String> requestHeaders) {

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(requestPayload));

		String msg = "";
		String msg1 = "";

		msg1 = StringUtils.substringBetween(requestPayload, "<header:MsgReference>", "</header:MsgReference>");

		try {
			// msg = (String) xpath.evaluate("//MsgBody", source,XPathConstants.STRING);
			// msg1 = (String) xpath.evaluate("/resp/msg", source,XPathConstants.STRING);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Message=" + msg);
		System.out.println("Message=" + msg1);

		ModifyExecutableOrderResponse model = new ModifyExecutableOrderResponse();
		SuccessResponse response = new SuccessResponse();
		response.setMessageReference(msg1);
		response.setReturnCode("200");
		response.setServiceName("ModifyExecutableOrderRequest");
		model.setSuccessResponse(response);

		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<ModifyExecutableOrderResponse> entityModel = new ResponseEntity<>(model, headers,
				HttpStatus.CREATED);

		return entityModel;
	}

}
