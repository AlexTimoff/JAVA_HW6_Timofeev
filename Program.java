import java.util.*;

public class Program {
    public static void main(String[] args) {
        addContact("Александр", "+12347");
        addContact("Иван", "+33120");
        addContact("Максим", "+22348");
        addContact("Александр", "+47801");
        addContact("Пётр", "+55207");
        addContact("Александр", "+33047");
        addContact("Александр", "+50501");
        addContact("Максим", "+12350");
        addContact("Максим", "+22391");
        addContact("Иван", "+81501");
        addContact("Михаил", "+83503");
        addContact("Иван", "+31501");
        addContact("Пётр", "+23207");
        addContact("Артем", "+43007");
        addContact("Вадим", "+43007");// Специально добавил номер, который уже присовен Артему.

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println("Телефонная книга без сортировки: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        getPhoneBook();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println("Телефонная книга c сортировкий по убыванию количества номеров абонента: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        sortedPhoneBook();

    }

    /*
    Создаем коллекцию HashMap. Ключом будет являться имя абонента,значением - коллекция номеров типа Set,
    исключающая дублирование одинаковых номеров у абонента.
    */
    static HashMap<String, Set<String>> phoneBook = new HashMap<>();

    // Создаем метод, позволяющий добавлять очередной контакт в телефонную книгу.
    public static void addContact(String name, String phoneNum) {

        /*
        Проверяем наличие вводимого телефонного номера в телефонной книге.
        Если добавляемый номер уже присутсвует у кого-то из абонентов, предупреждаем, что номер введен неверно.
        */
        if (findPhoneNum(phoneNum)==true) {
            System.out.println("Номер "+phoneNum+ " уже существует.Проверьте правильность введенного номера.");
        }

        /*
        Если добавляемый номер отсутствует в телефонной книге, осуществляем слеждующие действия.
        */
        else{

            // В случае, если уже имеется ключ, то в лист значений добавляется еще один номер.
            if (phoneBook.containsKey(name)) {
                phoneBook.get(name).add(phoneNum);
                System.out.println("Номер "+phoneNum+ " успешно добавлен к контакту "+name+"." );
            }
            // В противном случае, добавляется новый ключ и создается коллекция Set, в которую добавляется номер телефона.
            else {
                Set<String> list = new HashSet<>();
                list.add(phoneNum);
                phoneBook.put(name, list);
                System.out.println("Абонент "+name+ " с номером "+phoneNum+ " успешно добавлен в телефонную книгу.");
            }
        }
    }

    //Создаем метод, позволяющий проверить наличие добавляемого номера в телефонной книге.
    public static boolean findPhoneNum(String phoneNum){
        for (Map.Entry<String, Set<String>> contact : phoneBook.entrySet()) {
            if (contact.getValue().contains(phoneNum)) {
                return true;
            }
        }
        return false;
    }

    /*
    Создаем метод, позволяющий вывести всю телефонную книгу.
    Все пары "ключ-значение" хранятся во внутреннем интерфейсе Map.Entry. Чтобы их получить вызываем метод
    entrySet(), который возвращает множество Set-пар, которые можно перебрать циклом.
     */
    public static void getPhoneBook() {
        for (Map.Entry<String, Set<String>> contact : phoneBook.entrySet()) {
            System.out.println(contact.getKey() + " : " + contact.getValue());
        }
    }

    /*
    Создаем метод, позволяющий сделать сортировку телефонной книги в зависимости от количества номеров у абонента.
    В рамках данного метода создаем ArrayList, в который передаем все пары "ключ-значение" из коллекции phoneBook.
    При помощи компаратора осуществляем сортировку созданного ArrayList.
    Посредством цикла for-each осуществляем перебор сортированного листа контактов.
    */
    public static void sortedPhoneBook() {
        ArrayList<Map.Entry<String, Set<String>>> listContacts = new ArrayList<>(phoneBook.entrySet());
        listContacts.sort(new PhoneBookComparator());
        for (Map.Entry<String, Set<String>> contact : listContacts) {
            System.out.println(contact.getKey() + ": " + contact.getValue());
        }

    }
}
