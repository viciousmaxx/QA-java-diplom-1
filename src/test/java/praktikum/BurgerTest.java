package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    List<Ingredient> ingredients;
    @Mock
    List<Bun> buns;
    @Mock
    Burger burger;

    @Test
    public void addIngredientsSuccessfulTest() {
        Burger burger = new Burger();

        Mockito.when(ingredients.get(0)).
                thenReturn(new Ingredient(SAUCE, "sour cream", 200.00F));
        burger.addIngredient(ingredients.get(0));

        Assert.assertFalse("Перед добавлением ингредиентов - список ингредиентов д.б. пуст", burger.ingredients.isEmpty());
        Assert.assertTrue("Тип добавляемого ингредиента д.б. SAUCE", burger.ingredients.get(0).getType().equals(SAUCE));
        Assert.assertTrue("Имя добавляемого ингредиента д.б. 'sour cream'", burger.ingredients.get(0).getName().equals("sour cream"));
        Assert.assertTrue("Цена добавляемого ингредиента д.б. 200.00", burger.ingredients.get(0).getPrice() == 200.00F);
    }

    @Test
    public void setBunTest() {
        Burger burger = new Burger();

        Mockito.when(buns.get(0))
                .thenReturn(new Bun("SomeBun", 100_500.01F));
        burger.setBuns(buns.get(0));

        Assert.assertEquals("Имя добавляемой булки д.б. 'SomeBun'", "SomeBun", burger.bun.getName());
        Assert.assertTrue("Цена добавляемой булки д.б. '100500.01'", burger.bun.price == 100_500.01F);
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();

        Mockito.when(ingredients.get(0)).
                thenReturn(new Ingredient(FILLING, "dinosaur", 200));
        burger.addIngredient(ingredients.get(0));
        burger.removeIngredient(0);
        Assert.assertTrue("После удаления единственного элемента - список ингредиентов д.б. пуст", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();

        Mockito.when(ingredients.get(0)).
                thenReturn(new Ingredient(FILLING, "dinosaur", 200));
        Mockito.when(ingredients.get(1)).
                thenReturn(new Ingredient(FILLING, "sausage", 300));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.moveIngredient(0, 1);
        Assert.assertTrue("После перемещения - первый по индексу элемент должен стать последним", burger.ingredients.get(1).getName().equals("dinosaur"));
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();

        Mockito.when(buns.get(0))
                .thenReturn(new Bun("SomeBun", 500.50F));
        burger.setBuns(buns.get(0));

        Mockito.when(ingredients.get(0)).
                thenReturn(new Ingredient(FILLING, "dinosaur", 300));
        burger.addIngredient(ingredients.get(0));

        Mockito.when(ingredients.get(1)).
                thenReturn(new Ingredient(SAUCE, "sour cream", 150.00F));
        burger.addIngredient(ingredients.get(1));

        Assert.assertTrue("Проверка должна возвращать цену 1450", 1451 == burger.getPrice());
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();

        Mockito.when(buns.get(0)).thenReturn(new Bun("SomeBun", 500.00F));
        burger.setBuns(buns.get(0));

        Mockito.when(ingredients.get(0)).
                thenReturn(new Ingredient(FILLING, "dinosaur", 300));
        burger.addIngredient(ingredients.get(0));

        Mockito.when(ingredients.get(1)).
                thenReturn(new Ingredient(SAUCE, "sour cream", 150.00F));
        burger.addIngredient(ingredients.get(1));


        String expected = "(==== SomeBun ====)\r\n" +
                "= filling dinosaur =\r\n" +
                "= sauce sour cream =\r\n" +
                "(==== SomeBun ====)\r\n" +
                "\r\n" +
                "Price: 1450.000000\r\n";
        Assert.assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void getReceiptVerifyTest() {

        Mockito.when(buns.get(0)).thenReturn(new Bun("SomeBun", 500.00F));
        burger.setBuns(buns.get(0));

        Mockito.when(ingredients.get(0)).
                thenReturn(new Ingredient(FILLING, "dinosaur", 300));
        burger.addIngredient(ingredients.get(0));

        Mockito.when(ingredients.get(1)).
                thenReturn(new Ingredient(SAUCE, "sour cream", 150.00F));
        burger.addIngredient(ingredients.get(1));
        burger.getReceipt();

        Mockito.verify(burger).getReceipt();
        Mockito.verify(burger).setBuns(buns.get(0));
        Mockito.verify(burger).addIngredient(ingredients.get(0));
    }
}
