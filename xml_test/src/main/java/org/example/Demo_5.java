package org.example;

import com.thoughtworks.xstream.XStream;

import java.util.Objects;

public class Demo_5 {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("张三");
        p.setAge(18);

//        XStream使用
//        创建XStream对象
        XStream x = new XStream();
//        修改某个类型生成的节点（可选的，默认为包名。类名）
//        吧Person类型更改为Student结点
        x.alias("Student",Person.class);
//        传入对象，开始生成
        String xml = x.toXML(p);
        System.out.println(xml);

    }
    static class Person{
        private  String name ;
        private  int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Person() {
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}