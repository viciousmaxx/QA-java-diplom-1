package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class ParametrizedIngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private final IngredientType exceptedType;
    private final String expectedName;
    private final float expectedPrice;

    public ParametrizedIngredientTest(IngredientType type, String name, float price, IngredientType expectedType, String expectedName, float expectedPrice) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.exceptedType = expectedType;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные: Тип: {0}, имя: {1}, цена: {2}")
    public static Object[][] getSumData() {
        return new Object[][]{
                {SAUCE, "Some Hot Souse", 100, SAUCE, "Some Hot Souse", 100},
                {FILLING, "Some cool meat", 500, FILLING, "Some cool meat", 500},
                {SAUCE, "", 100, SAUCE, "", 100},
                {SAUCE, "Some Hot Souse", 0, SAUCE, "Some Hot Souse", 0},
                {SAUCE, "Some Hot Souse", 1_000.00F, SAUCE, "Some Hot Souse", 1_000.00F},
                {SAUCE, null, 0, SAUCE, null, 0},};
    }

    @Test
    public void ingredientsCreatingTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(exceptedType, ingredient.getType());
        Assert.assertEquals(expectedName, ingredient.getName());
        Assert.assertEquals(expectedPrice, ingredient.getPrice(), 0.0);
    }
}

