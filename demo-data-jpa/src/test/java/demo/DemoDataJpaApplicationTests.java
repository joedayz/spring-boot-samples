package demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoDataJpaApplication.class)
@WebAppConfiguration
@ActiveProfiles("qa")
public class DemoDataJpaApplicationTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void inicio(){
		this.mvc = MockMvcBuilders.
				webAppContextSetup(context).build();
	}
	
	@Test
	public void testHome() throws Exception {
	
		this.mvc.perform(get("/")).andExpect(status().isOk())
		.andExpect(content().string("Bath"));
	
	}

}
