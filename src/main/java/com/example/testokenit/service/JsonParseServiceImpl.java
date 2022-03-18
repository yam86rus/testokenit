package com.example.testokenit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonParseServiceImpl implements JsonParseService {
    @Override
    public List<?> convertObjectToList(Object object) {
        List<?> list = new ArrayList<>();
        if (object.getClass().isArray()) {
            list = Arrays.asList((Object[]) object);
        } else if (object instanceof Collection) {
            list = new ArrayList<>((Collection<?>) object);
        }
        return list;
    }
}
