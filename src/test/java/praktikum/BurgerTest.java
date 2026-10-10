
package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @Test
    public void setBunsSetsBun() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientMovesIngredient() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(100f);
        when(firstIngredient.getPrice()).thenReturn(50f);
        when(secondIngredient.getPrice()).thenReturn(70f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float actualPrice = burger.getPrice();

        assertEquals(320f, actualPrice, 0);
    }

    @Test
    public void getReceiptReturnsCorrectReceipt() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("hot sauce");
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String actualReceipt = burger.getReceipt();

        String expectedReceipt = String.format("(==== black bun ====)%n" +
                "= sauce hot sauce =%n" +
                "(==== black bun ====)%n" +
                "%nPrice: %f%n", 250f);

        assertEquals(expectedReceipt, actualReceipt);
    }
}
