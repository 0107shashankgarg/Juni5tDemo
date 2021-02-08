package demo.domain;

import com.univocity.parsers.annotations.Parsed;

import java.util.HashMap;
import java.util.Map;

public class CsvInputSourceRow {

    @Parsed(field = "name")
    private String name;
    @Parsed(field = "number")
    private String number;
    @Parsed(field = "country")
    private String country;

    @Parsed(field = "age")
    private String age;


    @Override
    public String toString() {
        return "CsvInputSourceRow{" +
                "fromPhoneNumber='" + name + '\'' +
                ", fromImsi='" + number + '\'' +
                ", fromImei='" + country + '\'' +
                ", toPhoneNumber='" + age + '\'' +
                '}';
    }

    public static class ConditionField {
        public static final Map<String, String> LOOKUP_MAP = new HashMap<String, String>() {
            {
                put("name", "name");
                put("number", "number");
                put("country", "country");

                put("age", "age");
            }
        };
    }
}
