import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        int lineLength = exp.split(" ").length;
        if (lineLength != 3)
            throw new Exception();
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) { //проверка наличия допустимых знаков выражения
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new Exception();
        }

        exp = exp.replace(" ", "");
        String[] data = exp.split(regexActions[actionIndex]);
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) { // проверка, на числа в одной системе счисленеия
            int a, b;
            boolean isRoman = converter.isRoman(data[0]); //проверка, римские числа? если да то конвер. в арабские
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else { //если арабские то переводим в строки
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            //15->XV
            if (isRoman) {
                if (result < 1) {
                    throw new Exception();
                }
                System.out.println(converter.intToRoman(result)); //возврат в римском числе
            } else {
                System.out.println(result); // выводим в арабском числе
            }
        } else {
            throw new Exception();
        }


    }
}