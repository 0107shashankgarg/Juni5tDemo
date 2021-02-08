package demo;

import demo.domain.Precondition;
import demo.domain.User;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.opentest4j.AssertionFailedError;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

import static java.nio.charset.Charset.defaultCharset;

public class MockDataManager {

    private static final Gson GSON = new Gson();

    public static List<User> getById(final int id) {
        return readAll().stream()
                .filter(precondition -> precondition.getTestCaseId() == id)
                .findFirst()
                .orElseThrow(() -> new AssertionFailedError("Preconditions not found for TestCaseID <" + id + ">"))
                .getUsers();
    }

    public static List<Precondition> readAll() {
        URL url = Resources.getResource("preconditions.json");
        String json = null;
        try {
            json = Resources.toString(url, defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type listType = new TypeToken<List<Precondition>>() {
        }.getType();
        return GSON.fromJson(json, listType);
    }

}
