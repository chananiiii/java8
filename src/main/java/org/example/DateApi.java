package org.example;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateApi {
    public static void main(String[] args) throws Exception{
        // 얘네들을 사용하는데 있어서, 불편했다.
        // 이유는 많은데,
        // 이름이 작명이 되어 있지 않다.
        Date date = new Date();
        long time = date.getTime(); // date 인데 시간을 가져온다고?
        System.out.println(date);
        System.out.println(time);

        // 객체의 상태를 바꿀 수 있다.
        // mutable 한 객체라고 한다.
        // 멀티쓰레드 환경에서 안전하게 사용하기가 힘들다.
        // thread safe 하지 않다고 한다.
        // 버그 발생할 여지가 많다.
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);

        // month 는 0부터 시작이다, 7월이면 6월이다. 그래서 실수할 여지가 있기 때문에
        // 저렇게 사용해야한다.
        // type safe 하지 않다. 위에서 본 thread safe 한 것과는 다르다.
        // type 안정성이 없다라고 말한다.
        // 아까 Date time 하는데 시간이 나오고, 왜 calendar 에 getTime하면 또 날짜가 나오냐.. 이런것이 문제다
        Calendar chanwanBirthDay = new GregorianCalendar(1994, Calendar.DECEMBER, 15);
        System.out.println(chanwanBirthDay.getTime());

        System.out.println();

        // 사람용 API
        // 기계용 API
        // 두개로 나눌 수 있다.
        Date date2 = new Date();
        long time2 = date2.getTime();
        System.out.println(time2); // 이건 기계용 시간이다


        // 기계용 API , 메서드 시간을 비교하거나 , 시간을 재거나 등등
        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC, GMT

        // 내 로컬 기준으로 보고 싶다면?
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        // 사람용
        // 서버에 배포가 되면, 서버에 시스템 기본 Zone 정보를 참고해서 사용하게 된다.
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime birthDay = LocalDateTime.of(1982, Month.APRIL, 15, 0, 0, 0);

        ZonedDateTime nowInKorea = zonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        // 서로 왔다갔다 할 수 있다.
        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1);

        LocalDate  today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.DECEMBER, 10);

        // 11월 10일까지 얼마나 남았는지
        // 사람 시간용 계산
        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        // 기계용 계산
        Instant nowTime = Instant.now();
        Instant plus = nowTime.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(nowTime, plus);
        System.out.println(between.getSeconds());

        // 사람이 읽기엔 불편하지 않는 형식으로 표현해주긴 하지만..
        LocalDateTime now2 = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(MMddyyyy));

        // 파싱
        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println(parse);


        // date -> instant 로 변환할 수 있다.
        Date date3 = new Date();
        Instant instan3 = date3.toInstant();
        // instant -> date 도 가능하다
        Date newDate = Date.from(instan3);

        // 예전 API 에서 최근 API 로 바꿀 수 있다.
        // 레거시 API 에서 다시 최근 API 로 갈 수 있다.
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);

    }
}
