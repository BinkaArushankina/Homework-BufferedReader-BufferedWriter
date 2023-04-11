package Homework;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Homework {
    public static void main(String[] args) throws IOException {
//1. Есть  текстовый файл.  Написать метод,  читающий файл и
//возвращающий строку, состоящую из строчек этого файла,
//разделенных пробелом.
        File file = new File("text.txt");
        file.createNewFile();

        text(file);     //to be or not to be


//2. Есть  файл, состоящий из строчек текста.  Написать метод,
//читающий файл и находящий самую длинную строчку в этом файле.
//Метод возвращает эту строчку

        longestString(file);        //not

//3. *(со звездочкой)
//Есть  не пустой текстовый файл, состоящий из строчек, содержащих
//имя и возраст такого вида:
//John, 3
//Jack, 23
//Jill, 19
//Bill, 15
//Ann, 54
//Написать метод, читающий файл и возвращающий список из людей
//старше 18 лет отсортированный по возрасту.
        File fileNameAge= new File("nameAge.txt");
        fileNameAge.createNewFile();

        System.out.println(adult(fileNameAge));     //[Ann, 54, Jack, 23, Jill, 19]


//4. *(со звездочкой)
// Есть текстовый файл, содержащий информацию о клиенте, название
//устройства и цену.   Имена клиентов могут повторяться.
//Файл имеет такой вид:
//John, Smith, Notebook, 500
//Jill, White, Mac,  800
//John, Smith, Keyboard,25
//Ann, Green, Mousepad, 5
//Jill, White, Apple,60
//Написать метод, читающий информацию из файла, суммирующий
//цену для каждого покупателя  и записывающий результат в выходной
//файл. Этот файл будет иметь такой вид:
//Ann Green 5
//John Smith 525
//Jill White 860
        File inputInfo= new File("inputInfo.txt");
        inputInfo.createNewFile();
        File output=new File("outputSum.txt");
        output.createNewFile();


        sum(inputInfo,output);

//Для решения задач вам могут понадобиться также методы класса
//String  trim()  и split().  Найти о них информацию в интернете



    }
  //1)
    public static void text(File file){
        String text;

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((text = br.readLine()) != null) {
                System.out.print(text);
                System.out.print(" ");
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

   //2)
    public static void longestString(File file){
        String text;
        String longestText="";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((text = br.readLine()) != null) {
                if (text.length()>longestText.length()){
                    longestText=text;
                    System.out.println(text);
                }
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    //3)
    public static List<String> adult(File file){
        String text;
        List<String> adult= new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((text = br.readLine()) != null) {
                String [] parts= text.split(",");
                String parts1= parts[1].trim();
                int age= Integer.parseInt(parts1);
                if(age>=18) {
                    adult.add(text);
                    Collections.sort(adult);
                }
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return adult;
    }

    //4)
    public static void sum(File input, File output) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(input));
        String text;
        BufferedWriter bw = new BufferedWriter(new FileWriter(output));
        int sum=0;

        while ((text = br.readLine()) != null) {
            String []parts = text.split(",");
            String name= parts[0].trim();
            String surname = parts[1].trim();
            String priceInt = parts[3].trim();
            int price =Integer.parseInt(priceInt);

            if(output.equals(name) && output.equals(surname)){
                    sum+=price;
                    bw.write(" "+name+" "+surname+" "+sum);
                    bw.newLine();
            } else {
                    bw.write(" "+name+" "+surname+" "+price);
                    bw.newLine();
            }
        }
        bw.close();
    }
}