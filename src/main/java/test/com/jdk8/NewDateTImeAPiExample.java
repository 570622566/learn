package test.com.jdk8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class NewDateTImeAPiExample {

	public static void main(String[] args) {
		/**
		 * 传统的java.util.date是非线程安全的,开发人员使用的时候,必须使用并发处理问题
		 */
		
		/**
		 * Difficult time zone handling − Developers had to write a lot of code to deal with timezone issues. 
		 * The new API has been developed keeping domain-specific design in mind.
		 * 开发人员必须编写大量的代码来处理时间问题
		 * 
		 * Java 8 introduces a new date-time API under the package java.time. Following are some of the important classes introduced in java.time package 
		 * java.time
		 */
		
	      LocalDateTime currentTime = LocalDateTime.now();
	      System.out.println("Current DateTime: " + currentTime); //默认貌似是UTC时间
	      
	      
	      LocalDate date1 = currentTime.toLocalDate();
	      System.out.println(date1);

	      Month month = currentTime.getMonth();
	      int day = currentTime.getDayOfMonth();
	      int seconds = currentTime.getSecond();
			
	      System.out.println("Month: " + month +",day: " + day +",seconds: " + seconds);
	      
	      LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
	      System.out.println("date2: " + date2);
	      
	      //12 december 2014
	      LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
	      System.out.println("date3: " + date3);
	      
	      //22 hour 15 minutes
	      LocalTime date4 = LocalTime.of(22, 15);
	      System.out.println("date4: " + date4);
	      
	      //parse a string
	      LocalTime date5 = LocalTime.parse("20:15:30");
	      System.out.println("date5: " + date5);
	      
	      /**
	       * 带有区时的API
	       */
	      ZonedDateTime dat11 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
	      System.out.println("date1: " + dat11);
	      
	      ZoneId id = ZoneId.of("Europe/Paris");
	      System.out.println("ZoneId: " + id);
	      
	      ZoneId currentZone = ZoneId.systemDefault();
	      System.out.println("CurrentZone: " + currentZone);
	      
	      
	      
	      //Get the current date
	      LocalDate today = LocalDate.now();
	      System.out.println("Current date: " + today);
			
	      //add 1 week to the current date
	      LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
	      System.out.println("Next week: " + nextWeek);
			
	      //add 1 month to the current date
	      LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
	      System.out.println("Next month: " + nextMonth);
			
	      //add 1 year to the current date
	      LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
	      System.out.println("Next year: " + nextYear);
			
	      //add 10 years to the current date
	      LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
	      System.out.println("Date after ten year: " + nextDecade);
	      
	      
	      
	      //Get the current date
	      LocalDate date22 = LocalDate.now();
	      System.out.println("Current date: " + date22);
			
	      //get the next tuesday
	      LocalDate nextTuesday = date1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
	      System.out.println("Next Tuesday on : " + nextTuesday);
			
	      //get the second saturday of next month
	      LocalDate firstInYear = LocalDate.of(date1.getYear(),date1.getMonth(), 1);
	      LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
	      System.out.println("Second Saturday on : " + secondSaturday);
	}
	
	
}
