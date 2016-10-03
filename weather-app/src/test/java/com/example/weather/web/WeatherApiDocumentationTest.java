package com.example.weather.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.weather.integration.ows.Weather;
import com.example.weather.integration.ows.WeatherForecast;
import com.example.weather.integration.ows.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@TestPropertySource(properties="spring.jackson.serialization.indent-output:true")
public class WeatherApiDocumentationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	@Autowired
	private ObjectMapper objectMapper;

	private final FieldDescriptor[] weatherFields = new FieldDescriptor[] {
			fieldWithPath("weatherId").description("ID of the weather conditions"),
			fieldWithPath("weatherIcon").description("ID of the weather icon"),
			fieldWithPath("temperature").description("Temperature in Kelvin"),
			fieldWithPath("timestamp").description("ISO-8601 timestamp of the weather data")};

	@Test
	public void weather() throws Exception {
		Weather weather = this.objectMapper.readValue(
				new ClassPathResource("/com/example/weather/integration/ows/weather-barcelona.json").getURL(),
				Weather.class);
		given(this.weatherService.getWeather("spring", "barcelona")).willReturn(weather);
		given(this.weatherService.getWeather("spain", "barcelona")).willReturn(weather);
		this.mockMvc.perform(get("/api/weather/now/{country}/{city}", "spain", "barcelona"))
				.andExpect(status().isOk())
				.andDo(document("weather",
						pathParameters(
								parameterWithName("country").description("Name of the country"),
								parameterWithName("city").description("Name of the city")),
						responseFields(
								fieldWithPath("name").description("Name of the weather data"))
								.and(this.weatherFields)));
	}

	@Test
	public void weatherForecast() throws Exception {
		WeatherForecast weatherForecast = this.objectMapper.readValue(
				new ClassPathResource("/com/example/weather/integration/ows/forecast-barcelona.json").getURL(),
				WeatherForecast.class);
		given(this.weatherService.getWeatherForecast("spain", "barcelona")).willReturn(weatherForecast);
		given(this.weatherService.getWeatherForecast("spain", "barcelona")).willReturn(weatherForecast);
		this.mockMvc.perform(get("/api/weather/weekly/{country}/{city}", "spain", "barcelona"))
				.andExpect(status().isOk())
				.andDo(document("weather-forecast",
						pathParameters(
								parameterWithName("country").description("Name of the country"),
								parameterWithName("city").description("Name of the city")),
						responseFields(
								fieldWithPath("name").description("Name of the weather forecast"),
								fieldWithPath("entries").description("Array of weather data"))
								.andWithPrefix("entries[].", this.weatherFields)));
	}

}
