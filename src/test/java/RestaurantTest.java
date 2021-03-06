import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    public RestaurantTest() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime targetTime = LocalTime.parse("13:30:00");
        restaurant.setTargetTime(targetTime);

        Boolean isRestaurantOpen = restaurant.isRestaurantOpen();
        assertEquals(true,isRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime targetTime = LocalTime.parse("23:30:00");
        restaurant.setTargetTime(targetTime);

        Boolean isRestaurantNotOpen = restaurant.isRestaurantOpen();
        assertEquals(false,restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>TOTAL ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_cart_should_increase_the_total_order_value_by_item_price() {
        int cartOrderValue = restaurant.getTotalOrderCartValue();
        restaurant.addItemsToCart("Sweet corn soup");
        assertEquals(cartOrderValue+119,restaurant.getTotalOrderCartValue());
    }

    @Test
    public void removing_item_from_cart_should_decrease_the_total_order_value_by_item_price() {
        restaurant.addToMenu("Sizzling brownie",319);
        restaurant.addItemsToCart("Sweet corn soup", "Vegetable lasagne", "Sizzling brownie");
        int cartOrderValue = restaurant.getTotalOrderCartValue();
        restaurant.removeItemFromCart("Sizzling brownie");
        assertEquals(cartOrderValue-319,restaurant.getTotalOrderCartValue());
    }

    @Test
    public void adding_item_to_cart_should_increase_the_cart_size_by_1() {
        int cartOrderItems = restaurant.getTotalOrderCartItems();
        restaurant.addItemsToCart("Sweet corn soup");
        assertEquals(cartOrderItems+1,restaurant.getTotalOrderCartItems());
    }

    @Test
    public void removing_item_from_cart_should_decrease_the_cart_size_by_1() {
        restaurant.addToMenu("Sizzling brownie",319);
        restaurant.addItemsToCart("Sweet corn soup", "Vegetable lasagne", "Sizzling brownie");
        int cartOrderValue = restaurant.getTotalOrderCartItems();
        restaurant.removeItemFromCart("Sizzling brownie");
        assertEquals(cartOrderValue-1,restaurant.getTotalOrderCartItems());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<TOTAL ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}