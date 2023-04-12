package Homework;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Homework {
    public static void main(String[] args) throws IOException {
//1. Есть  текстовый файл.  Написать метод,  читающий файл и
//возвращающий строку, состоящую из строчек этого файла,
//разделенных пробелом.
        File file = new File("text.txt");
        file.createNewFile();

        System.out.println(text(file));     //to be or not to be


//2. Есть  файл, состоящий из строчек текста.  Написать метод,
//читающий файл и находящий самую длинную строчку в этом файле.
//Метод возвращает эту строчку

        System.out.println(longestString(file));        //not

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

        System.out.println(adult(fileNameAge));     //[name = Jill, age = 19, name = Jack, age = 23, name = Ann, age = 54]


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
    public static String text(File file){
        String text="";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                text=text+" "+line;
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return text.trim();
    }

   //2)
    public static String longestString(File file){
        String longestText="";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String text;
            while ((text = br.readLine()) != null) {
                if (text.length()>longestText.length()){
                    longestText=text;
                }
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return longestText;
    }

    //3)
    public static List<Person> adult(File file){
        List<String> persons=new ArrayList<>();
        List<Person> adult= new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String text;
            while ((text = br.readLine()) != null) {
                persons.add(text);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
        for(String s: persons){
            String [] parts= s.split(",");
            String parts1= parts[1].trim();
            int age= Integer.parseInt(parts1);
            if(age>=18) {
                adult.add(new Person(parts[0].trim(),age));

            }
            Collections.sort(adult);
        }
        return adult;
    }

    //4)
    public static void sum(File input, File output) {
        try(BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw = new BufferedWriter(new FileWriter(output,false))){

            String text;
            Map<String,Integer> result= new HashMap<>();
            while ((text = br.readLine()) != null) {
                String []parts = text.split(",");
                String name= parts[0].trim().concat(" ").concat(parts[1].trim());
                String priceInt = parts[3].trim();
                int price =Integer.parseInt(priceInt);
                if(result.containsKey(name)){
                    int oldPrice=result.get(name);//po kljutschu name berjom value, toest ego starij price.
                    result.put(name,price+oldPrice);
                } else {
                    result.put(name,price);
                }
            }
            for (Map.Entry<String,Integer> me: result.entrySet()) {//idem srasu po key i value
                bw.write(me.getKey()+" "+me.getValue());
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}