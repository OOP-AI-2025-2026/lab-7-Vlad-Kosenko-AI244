package ua.opnu;

import ua.opnu.Student;
import ua.opnu.ArrayOps;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isPrime = Filters.isPrime();
        System.out.println("7 просте? " + isPrime.test(7));
        System.out.println("8 просте? " + isPrime.test(8));

        Student[] students = new Student[] {
                new Student("Тарас", "Шевченко", "АІ-244", new int[]{95, 88, 74}),
                new Student("Леся", "Українка", "АІ-244", new int[]{60, 61, 59}),
                new Student("Іван", "Франко", "АІ-245", new int[]{100, 90, 100}),
                new Student("Ліна", "Костенко", "АІ-245", new int[]{59, 59, 59})
        };

        Predicate<Student> hasDebt = s -> Arrays.stream(s.getMarks()).anyMatch(m -> m < 60);
        List<Student> filtered = Filters.filter(students, hasDebt.negate());
        System.out.println("Студенти без боргів: " + filtered);

        Predicate<Student> groupIsIP12 = s -> "АІ-244".equals(s.getGroup());
        List<Student> goodAndGroup = Filters.filter(students, hasDebt.negate(), groupIsIP12);
        System.out.println("Без боргів і з групи АІ-244: " + goodAndGroup);

        Consumer<Student> printSurnameName = s ->
                System.out.println(s.getLastName().toUpperCase() + " " + s.getFirstName().toUpperCase());
        ArrayOps.forEach(students, printSurnameName);

        Integer[] ints = {0,1,2,3,4,5,6,7,8,9};
        Predicate<Integer> even = i -> i % 2 == 0;
        Consumer<Integer> printer = i -> System.out.print(i + " ");
        System.out.print("Парні: ");
        ArrayOps.doIf(ints, even, printer);
        System.out.println();

        Function<Integer, Integer> pow2 = ArrayOps.pow2();
        System.out.println("2^10 = " + pow2.apply(10));

        int[] zeroToNine = {0,1,2,3,4,5,6,7,8,9};
        Function<Integer, String> numToUa = ArrayOps.numToUa();
        String[] words = ArrayOps.stringify(zeroToNine, numToUa);
        System.out.println("Слова: " + Arrays.toString(words));
    }
}
