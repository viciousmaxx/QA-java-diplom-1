package praktikum;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParametrizedBunTest {
    private final String name;
    private final float price;
    private final String expectedName;
    private final float expectedPrice;

    public ParametrizedBunTest(String name, float price, String expectedName, float expectedPrice) {
        this.name = name;
        this.price = price;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters (name = "Тестовые данные: Имя: {0}, цена: {1}")
    public static Object[][] getSumData() {
        return new Object[][]{
                {"SomeBun", 1000, "SomeBun", 1000},
                {"Булка", 0, "Булка", 0},
                {"", 1000.00F, "", 1000.00F},
                {null , 1000, null, 1000},
        };
    }

    @Test
    public void bunCreatingTest() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(expectedName, bun.getName());
        Assert.assertEquals(expectedPrice, bun.getPrice(), 0.0);
    }
}
