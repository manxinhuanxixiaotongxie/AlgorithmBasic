package book_01stackandlist;

/**
 * @Description 猫狗队列
 * @Author Scurry
 * @Date 2022-10-18 19:55
 */

import java.sql.BatchUpdateException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 要求：
 * 1.队列先进先出
 * 2.弹出所有的狗（队列）
 * 3.弹出所有的猫（队列）
 * 4.是否有狗
 * 5.是否有猫
 * 不能改变原有的类结构
 */
public class CatAndDogList {

    /**
     * 实现思路
     * 封装一个带有count的类，里面有类型
     * 在队列里进行维护
     * 两个队列  一个猫队列 一个狗队列
     */

    class Pet {
        private String petType;

        Pet(String petType) {
            this.petType = petType;
        }

        public String getPetType() {
            return petType;
        }
    }


    class Dog extends Pet {
        Dog() {
            super("dog");
        }
    }

    class Cat extends Pet {
        Cat() {
            super("cat");
        }
    }

    class PetEnterQueue {
        private Pet pet;
        private long count;

        PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }
    }

    // 猫狗两个队列
    private Queue<PetEnterQueue> dogList = new LinkedList<>();
    private Queue<PetEnterQueue> catList = new LinkedList<>();
    private long count;

    CatAndDogList() {
        count = 0;
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            dogList.add(new PetEnterQueue(pet, count++));
        } else if (pet.getPetType().equals("cat")) {
            catList.add(new PetEnterQueue(pet, count++));
        } else {
            throw new RuntimeException("fail");
        }
    }

    public Pet poll() {
        if (!dogList.isEmpty() && !catList.isEmpty()) {
            if (dogList.peek().getCount() < catList.peek().count) {
                return catList.poll().getPet();
            } else {
                return dogList.poll().getPet();
            }
        } else if (dogList.isEmpty()) {
            return catList.poll().getPet();
        } else if (catList.isEmpty()) {
            return dogList.poll().getPet();
        } else {
            throw new RuntimeException("fail");
        }

    }

    /**
     * 按照队列的顺序清空队列
     */
    public void pollAll() {
        while (!dogList.isEmpty() && !catList.isEmpty()) {
            if (dogList.peek().getCount() < catList.peek().count) {
                catList.poll();
            } else {
                dogList.poll();
            }
        }

        while (dogList.isEmpty() && !catList.isEmpty()) {
            catList.poll();
        }

        while (!dogList.isEmpty() && catList.isEmpty()) {
            dogList.poll();
        }

        while (dogList.isEmpty() && catList.isEmpty()) {
            throw new RuntimeException("fail");
        }
    }


}
