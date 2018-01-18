package com.qa.team2.business.repository;

public interface ScheduleService {
		String findSchedule(Long id);

		String getAllSchedules();

		String createScheduleFromString(String schedule);

		String deleteSchedule(Long id);

		String updateSchedule(Long id, String schedule);
	}
