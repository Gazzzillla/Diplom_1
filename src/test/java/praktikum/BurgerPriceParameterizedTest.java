
package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{index}: bun={0}, ingredient={1}, total={2}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},
                {80f, 0f, 160f},
                {125.5f, 19.5f, 270.5f}
        });
    }

    @Test
    public void getPriceReturnsExpectedPriceForDifferentPrices() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}
