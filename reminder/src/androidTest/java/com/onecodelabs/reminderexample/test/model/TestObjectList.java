package com.onecodelabs.reminderexample.test.model;

import java.util.LinkedList;
import java.util.List;

public class TestObjectList {

    private List<TestObject> objectList;

    public TestObjectList() {
        objectList = new LinkedList<>();
    }

    public void addObject(TestObject testObject) {
        objectList.add(testObject);
    }

    public int size() {
        return objectList == null ? 0 : objectList.size();
    }

}
