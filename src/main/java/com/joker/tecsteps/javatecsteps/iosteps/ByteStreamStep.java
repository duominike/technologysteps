package com.joker.tecsteps.javatecsteps.iosteps;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class ByteStreamStep {
    public void testFileStream() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int numRead = 0;
        try {
            inputStream = new FileInputStream(new File("~/bin/java_tec_test/char_stream.png"));
            outputStream = new FileOutputStream(new File("~/bin/java_tec_test/char_stream_copy.png"));
            byte[] buffer = new byte[1024];
            while ((numRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, numRead);
            }
        } catch (IOException e) {

        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void testObjectStream() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(
                    new File("~/bin/java_tec_test/persons.txt")));
            objectOutputStream.writeObject(new Person("jimmy", 25));
            objectOutputStream.writeObject(new Person("tonny", 8));
            objectInputStream = new ObjectInputStream(new FileInputStream("~/bin/java_tec_test/persons.txt"));
            for (int i = 0; i < 2; i++) {
                System.out.println(objectInputStream.readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void testDataStream() {
        Person[] persons = {new Person("joker", 30),
                new Person("jimmy", 24),
                new Person("tony", 50)};
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(new FileOutputStream("~/bin/java_tec_test/datastream.txt"));
            for (Person person : persons) {
                outputStream.writeUTF(person.nickname);
                outputStream.writeInt(person.age);
            }
            outputStream.flush();
            outputStream.close();
            inputStream = new DataInputStream(new FileInputStream("~/bin/java_tec_test/datastream.txt"));
            for (int i = 0; i < persons.length; i++) {
                String name = inputStream.readUTF();
                int age = inputStream.readInt();
                persons[i] = new Person(name, age);
            }
            for (Person person : persons) {
                System.out.printf("%s\t%d%n", person.nickname, person.age);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {


        }
    }

    public void testPushBackInputStream() {

        String str = "hello,rollenholt";
        PushbackInputStream push = null; // 声明回退流对象
        ByteArrayInputStream bat = null; // 声明字节数组流对象
        try {
            bat = new ByteArrayInputStream(str.getBytes());
            push = new PushbackInputStream(bat); // 创建回退流对象，将拆解的字节数组流传入
            int temp = 0;
            while ((temp = push.read()) != -1) { // push.read()逐字节读取存放在temp中，如果读取完成返回-1
                if (temp == ',') { // 判断读取的是否是逗号
                    push.unread(temp); //回到temp的位置
                    temp = push.read(); //接着读取字节
                    System.out.print("(回退" + (char) temp + ") "); // 输出回退的字符
                } else {
                    System.out.print((char) temp); // 否则输出字符
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                push.close();
                bat.close();
            } catch (Exception e) {

            }
        }
    }

    public void testSequenceStream() {

        // 创建一个合并流的对象
        SequenceInputStream sis = null;
        // 创建输出流。
        BufferedOutputStream bos = null;
        try {
            // 构建流集合。
            Vector<InputStream> vector = new Vector<InputStream>();
            vector.addElement(new FileInputStream("~/bin/java_tec_test/test1.txt"));
            vector.addElement(new FileInputStream("~/bin/java_tec_test/test2.txt"));
            vector.addElement(new FileInputStream("~/bin/java_tec_test/test3.txt"));
            Enumeration<InputStream> e = vector.elements();

            sis = new SequenceInputStream(e);

            bos = new BufferedOutputStream(new FileOutputStream("~/bin/java_tec_test/test4.txt"));
            // 读写数据
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = sis.read(buf)) != -1) {
                bos.write(buf, 0, len);
                bos.flush();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (sis != null)
                    sis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    class Person implements Serializable {
        public String nickname;
        public int age;

        public Person(String nickname, int age) {
            super();
            this.nickname = nickname;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person [name = " + nickname + ",age = " + age + "]";
        }
    }

}