import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    public LocalTime targetTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> cart = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.targetTime = LocalTime.now();
    }

    public boolean isRestaurantOpen() {
        if (targetTime.isAfter(openingTime) && targetTime.isBefore(closingTime)){
            return true;
        }
        return false;
    }

    public void setTargetTime(LocalTime targetTime) {
        this.targetTime = targetTime;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    private Item findCartItemByName(String itemName){
        for(Item item: cart) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public void addItemsToCart(String... values) {
        List<String> names = new ArrayList<>();
        for(String c : values){
            names.add(c);
        }
        for (int i = 0; i < menu.size(); i++) {
            for (int j = 0; j < names.size(); j++) {
                if (menu.get(i).getName() == names.get(j)) {
                    Item newItem = new Item(menu.get(i).getName(),menu.get(i).getPrice());
                    cart.add(newItem);
                }
            }
        }
    }

    public void removeItemFromCart(String name) {
        Item itemToBeRemoved = findCartItemByName(name);
        cart.remove(itemToBeRemoved);
    }

    public int getTotalOrderCartValue() {
        int totalValue = 0;
        for (int i = 0; i < cart.size(); i++) {
            totalValue += cart.get(i).getPrice();
        }
        return totalValue;
    }

    public int getTotalOrderCartItems() {
        return cart.size();
    }
}
