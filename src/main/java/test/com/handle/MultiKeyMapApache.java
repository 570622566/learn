package test.com.handle;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections.map.MultiKeyMap;

/**
 * Created by Administrator on 2017/4/20.
 */
public class MultiKeyMapApache {

    public static void main(String[] args) {
        MultiKeyMap map = MultiKeyMap.decorate(new LinkedMap());
        MultiKeyMapApache.Student student1 = new MultiKeyMapApache().new Student("guoshuai", 22);
        MultiKeyMapApache.Student student2 = new MultiKeyMapApache().new Student("limin", 23);

        map.put("01","female","china","bachelor",student1);
        map.put("02","male","china","master",student2);

        //最多可以定义5个主键
        System.out.println(map.get("01","female","china","bachelor"));
        System.out.println(map.get("02","male","china","master"));
        System.out.println(map.get("02","male","china"));

        MultiKeyMapApache.Student student3 = new MultiKeyMapApache().new Student();


        /**
         * 属性copy但是暂时不好用...
         */
        try {
           BeanUtils.copyProperties(student3,student2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(student3);

    }


    class Student{
        private String name;
        private int age;

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

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Student() {
        }
    }

}
