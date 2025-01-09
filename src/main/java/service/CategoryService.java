package service;

import model.Category;

import java.util.HashMap;
import java.util.Map;

public class CategoryService {
    private final Map<String, Category> categories = new HashMap<>();

    public void addCategory(String name, boolean isIncome) {
        if (categories.containsKey(name)) {
            System.out.println("Данная категория '" + name + "' уже существует.");
        } else {
            categories.put(name, new Category(name, isIncome));
            System.out.println("Категория '" + name + "' добавлена в список.");
        }
    }

    public Category getCategory(String name) {
        return categories.get(name);
    }

    public void listCategories() {
        System.out.println("Список категорий:");
        for (Category category : categories.values()) {
            System.out.println("- " + category.getName() + " (" + (category.isIncome() ? "Доход" : "Расход") + ")");
        }
    }
}