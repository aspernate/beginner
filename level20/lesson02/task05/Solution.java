package com.javarush.test.level20.lesson02.task05;

import java.io.*;

/* И еще раз о синхронизации
Разберитесь почему не работает метод main()
Реализуйте логику записи в файл и чтения из файла для класса Object
Метод load должен инициализировать объект данными из файла
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(java.lang.String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("Y://your_file_name.txt", null);
            OutputStream outputStream = new FileOutputStream(new File("D://temp.txt"));
            InputStream inputStream = new FileInputStream("D://temp.txt");

            Object object = new Object();
            object.string1 = new String();   //string #1
            object.string2 = new String();   //string #2
            object.save(outputStream);
            outputStream.flush();

            Object loadedObject = new Object();
            loadedObject.string1 = new String(); //string #3
            loadedObject.string2 = new String(); //string #4


            loadedObject.load(inputStream);
            object.string1.print();
            object.string2.print();
            System.out.println("============");
            loadedObject.string1.print();
            loadedObject.string2.print();

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Object {
        public String string1;
        public String string2;

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(this.string1 != null ? this.string1.number - 1 : "NO");
            printWriter.println(this.string2 != null ? this.string2.number - 1 : "NO");
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            java.lang.String s;
            if (!(s = bufferedReader.readLine()).equals("NO"))
            {
                int old_count = countStrings;
                countStrings = Integer.parseInt(s);
                this.string1 = new String();
                countStrings = old_count;
            } else
            {
                this.string1 = null;
            }
            if (!(s = bufferedReader.readLine()).equals("NO"))
            {
                int old_count = countStrings;
                countStrings = Integer.parseInt(s);
                this.string2 = new String();
                countStrings = old_count;
            } else
            {
                this.string2 = null;
            }
            bufferedReader.close();
        }
    }

    public static int countStrings;

    public static class String {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }
}
