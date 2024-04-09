import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String surname = input("фамилию");
        String name = input("имя");
        String patronymic = input("отчество");
        String birthDateStr = input("дату рождения в формате дд.мм.гггг");

        LocalDate birthDate = null;
        do {
            try {
                birthDate = LocalDate.parse(birthDateStr, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Вы ввели дату рождения в неверном формате");
                birthDateStr = input("дату рождения в формате дд.мм.гггг");
            }
        } while (birthDate == null);

        int age = calculateAge(birthDate);

        String initials = surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".";

        String gender = getGender(patronymic, name);

        String ageYearsStr = "";
        if (age < 0) {
            ageYearsStr = "Родится через ";
            age *= -1;
        }
        if (age % 10 == 1 && age != 11) {
            ageYearsStr += age + " год";
        } else if (age % 10 >= 2 && age % 10 <= 4 && (age < 10 || age > 20)) {
            ageYearsStr += age + " года";
        } else {
            ageYearsStr += age + " лет";
        }

        System.out.printf("%s   %s   %s", initials, gender, ageYearsStr);
    }

    public static String input(String object) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введите %s: ", object);
        String objectValue = scanner.nextLine();
        while (objectValue == "") {
            System.out.printf("Вы ничего не ввели. Введите %s: ", object);
            objectValue = scanner.nextLine();
        }
        return objectValue;
    }

    public static String getGender(String patronymic, String name) {
        String lastChar = patronymic.substring(patronymic.length() - 1);
        if (lastChar.equals("ч")) {
            return "М";
        } else {
            return "Ж";
        }
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);
        return period.getYears();
    }

}
