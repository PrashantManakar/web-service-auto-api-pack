package com.example.api.utils;

import com.example.api.model.MobilePhoneItem;

import java.util.ArrayList;
import java.util.List;

public class TestContext {
    private MobilePhoneItem currentObject;
    private final List<String> createdObjectIds = new ArrayList<>();

    public MobilePhoneItem getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(MobilePhoneItem currentObject) {
        this.currentObject = currentObject;
    }

    public void addCreatedObjectId(String id) {
        createdObjectIds.add(id);
    }

    public List<String> getCreatedObjectIds() {
        return createdObjectIds;
    }

    public void clear() {
        currentObject = null;
        createdObjectIds.clear();
    }
}
