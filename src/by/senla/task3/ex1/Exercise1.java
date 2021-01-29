package by.senla.task3.ex1;

/*
Написать программу выводящую на экран 3 случайно сгенерированных трёхзначных числа и разницу междучислом
получившимся методом последовательной записи 1-го и 2-го числа и третьим число
*/

import java.util.Random;

public class Exercise1 {
    public static void main(String[] args) {
        int[] numbers = new int[3];
        int num1and2;

        generate(numbers);
        num1and2 = union(numbers);
        show(numbers);
        System.out.println(num1and2 + " - " + numbers[2] + " = " + (num1and2 - numbers[2]));
    }

    private static int union(int[] numbers) {
        return numbers[0] * 1000 + numbers[1];
    }

    private static void show(int[] numbers) {
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void generate(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int num = 0;
            while (num < 100) {
                num = new Random().nextInt(1000);
            }
            array[i] = num;
        }
    }

}
