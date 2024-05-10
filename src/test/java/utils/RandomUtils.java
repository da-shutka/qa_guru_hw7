package utils;

import com.google.common.collect.Lists;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static List<String> getRandomDate(Date date) {
        List<String> dayMonthYear = new ArrayList<String>();

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String day = String.valueOf(localDate.getDayOfMonth());
        
        Format f = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        String month = f.format(date);

        f = new SimpleDateFormat("yyyy");
        String year = f.format(date);

        dayMonthYear.add(day);
        dayMonthYear.add(month);
        dayMonthYear.add(year);

        return dayMonthYear;
    }
    public static List<String> getRandomSubjects() {
        List<String> subjects = Lists.newArrayList("Accounting",
                "Arts",
                "Biology",
                "Chemistry",
                "Civics",
                "Computer Science",
                "Commerce",
                "Economics",
                "English",
                "Hindi",
                "History",
                "Maths",
                "Physics",
                "Social Studies");
        return getRandomItemsFromArray(subjects);
    }

    public static List<String> getRandomHobbies() {
        List<String> hobbies = Lists.newArrayList("Sports",
                "Reading",
                "Music");
        return getRandomItemsFromArray(hobbies);
    }

    public static String getRandomCityBasedOnState(String state) {
        List<String> citiesNCR = Lists.newArrayList("Delhi", "Gurgaon", "Noida");
        List<String> citiesUttar = Lists.newArrayList("Agra", "Lucknow", "Merrut");
        List<String> citiesHaryana = Lists.newArrayList("Karnal", "Panipat");
        List<String> citiesRajasthan = Lists.newArrayList("Jaipur", "Jaiselmer");
        List<String> target = new ArrayList<>();

        switch(state){
            case "NCR":
                target.addAll(citiesNCR);
                break;
            case "Uttar Pradesh":
                target.addAll(citiesUttar);
                break;
            case "Haryana":
                target.addAll(citiesHaryana);
                break;
            case "Rajasthan":
                target.addAll(citiesRajasthan);
                break;
        }
        return getRandomItemFromList(target);
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomItemFromList(List<String> array) {
        int index = getRandomInt(0, array.size() - 1);
        return array.get(index);
    }
    public static List<String> getRandomItemsFromArray(List<String> array) {
        int numberOfElements = getRandomInt(1, array.size());
        List<String> resList = new ArrayList<>();

        for(int i=0; i < numberOfElements; i++) {
            int randomIndex = getRandomInt(0, array.size() - 1);
            String randomElement = array.get(randomIndex);
            array.remove(randomIndex);
            resList.add(i,randomElement);
        }
        return resList;
    }
}
