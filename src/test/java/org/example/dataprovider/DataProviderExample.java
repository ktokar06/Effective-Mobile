package org.example.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Класс, предоставляющий данные для тестов.
 */
public class DataProviderExample {

    /**
     * Метод, создающий набор данных для тестов.
     *
     * @return Массив строковых массивов, содержащих наборы данных для тестов.
     */
    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {"angular", "password", false},
                {"Max", "123", false},
                {"Jane", "123", false},
                {"default", "1q2w3e", true}    // valid credentials
        };
    }
}