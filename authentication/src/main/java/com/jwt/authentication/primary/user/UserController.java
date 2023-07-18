package com.jwt.authentication.primary.user;

import com.jwt.authentication.primary.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/save-user")
    public ResponseEntity<User> saveUserDetails(@RequestBody User user) throws UserExistWithSameEmail {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/get-all/users")
    public ResponseEntity<List<User>> getAllUsers() throws NoUserInTheDatabaseException {
        List<User> userList = userService.getAllUsersList();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @GetMapping("/find-user/{userId}")
    public ResponseEntity<User> findUserBYId(@PathVariable int userId) throws UserNotFoundWithIdException {
        User existingUser = userService.findById(userId);
        return new ResponseEntity<>(existingUser,HttpStatus.FOUND);
    }
    @GetMapping("/find/user/books/{userId}")
    public ResponseEntity<?> findListOfBooksWithUserId(@PathVariable int userId) throws UserNotFoundWithIdException {
        List<Book> userBooks = userService.findUserBooks(userId);
        if(userBooks==null || userBooks.isEmpty()){
            return new ResponseEntity<>("no books for this user",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userBooks,HttpStatus.OK);
    }
}