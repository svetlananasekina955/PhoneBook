mport java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phone_book = new HashMap<>();
    private int size = 0;

    int size(){
        return size;
    }

    boolean empty(){
        return size == 0;
    }

    void put(String key, String number) {
        if (phone_book.containsKey(key)) {
            phone_book.get(key).add(number);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(number);
            phone_book.put(key, list);
            this.size++;
        }
    }

    void delContact (String name) {
        phone_book.remove(name);
        this.size--;
    }

    void clear() {
        phone_book.clear();
        this.size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) return "The phone book is empty";
        StringBuilder sb = new StringBuilder();
        Set<String> keys = phone_book.keySet();
        int seporator = 2;
        int maxName = 0;
        for (String key : keys) {
            if (key.length() > maxName) maxName = key.length();
        }

        ArrayList<Map.Entry<String, ArrayList<String>>> list = new ArrayList<>(phone_book.entrySet());
        list.sort(Comparator.comparingInt(entry -> -entry.getValue().size()));

        for (Entry<String, ArrayList<String>> pair : list) {

            sb.append(pair.getKey()).append(":").repeat(" ", seporator + maxName - pair.getKey().length())
                    .append(pair.getValue().toString().replace("[", "")
                            .replace("]", "")).append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    HashMap<String, ArrayList<String>> getAll() {
        return phone_book;
    }
}
