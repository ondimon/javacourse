import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private final Map<String, List<String>> book;

    public PhoneBook() {
        book = new HashMap<>();
    }

    public void add(String surname, String phone) {
        List<String> listPone = get(surname);
        listPone.add(phone);
        book.put(surname, listPone);
    }

    public List<String> get(String surname) {
        List<String> listPone = book.get(surname);
        if (listPone == null) {
            listPone = new ArrayList<>();
        }
        return listPone;
    }

}
