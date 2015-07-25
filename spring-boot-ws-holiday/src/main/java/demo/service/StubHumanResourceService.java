package demo.service;

import java.util.Date;
//slf4j es el default
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StubHumanResourceService implements HumanResourceService {

	private final Logger logger = LoggerFactory.getLogger(StubHumanResourceService.class);

	@Override
	public void bookHoliday(Date startDate, Date endDate, String name) {
		this.logger.info("Booking holiday for [{} - {}] for [{}] ", startDate, endDate,
				name);
	}

}
