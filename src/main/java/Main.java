import controller.AppController;
import model.User;
import service.CategoryService;
import service.UserService;
import service.WalletService;
import storage.FileStorage;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Type userType = new TypeToken<HashMap<String, User>>() {}.getType();

        FileStorage<String, User> userStorage = new FileStorage<>("users.json", userType);

        UserService userService = new UserService(userStorage);
        WalletService walletService = new WalletService();
        CategoryService categoryService = new CategoryService();

        AppController appController = new AppController(userService, walletService, categoryService);


        appController.start();
    }
}
