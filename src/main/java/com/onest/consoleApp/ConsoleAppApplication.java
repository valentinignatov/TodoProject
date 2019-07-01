package com.onest.consoleApp;

import com.onest.consoleApp.models.Tag;
import com.onest.consoleApp.models.Todo;
import com.onest.consoleApp.models.User;
import com.onest.consoleApp.services.FileReaderService;
import com.onest.consoleApp.services.TagService;
import com.onest.consoleApp.services.TodoService;
import com.onest.consoleApp.services.UserService;
import com.onest.consoleApp.services.impl.FileReaderServiceImpl;
import com.onest.consoleApp.services.impl.TagServiceImpl;
import com.onest.consoleApp.services.impl.TodoServiceImpl;
import com.onest.consoleApp.services.impl.UserServiceImpl;
import jline.console.ConsoleReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class ConsoleAppApplication {

    public static void main(String[] args) throws IOException {

        initDatabase();

        Scanner in = new Scanner(System.in);
        int choice = 0;

        char quit = 'n';

        BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));

        UserService userService = new UserServiceImpl();

        String input;

        ConsoleReader r = new ConsoleReader();

        User loggedUser;

        while (quit != 'y') {
            r.println("\t ---MENU---");
            r.println("\t 1. Register");
            r.println("\t 2. Login");
            r.println("\t 3. Exit");
            r.flush();
            choice = in.nextInt();
            r.clearScreen();
            switch (choice) {
                case 1:
                    System.out.println("");
                    System.out.println("Introduce Username");
                    String username = ob.readLine();
                    User user = new User(username);

                    Optional<User> existUser = userService.findByUsername(username);

                    if(existUser.isPresent()){
                        System.out.println("");
                        System.out.println("Username allready exists");
                    } else {
                        System.out.println("");
                        System.out.println("New user saved");
                        userService.save(user);
                    }
                    break;

                case 2:
                    System.out.println("");
                    System.out.println("Introduce Username");
                    String usernameToCheck = ob.readLine();

                    Optional<User> foundUser = userService.findByUsername(usernameToCheck);

                    if (foundUser.isPresent()) {

                        // 1. print user logged successfuly
                        System.out.println("");
                        System.out.println("Logged succesyfuly");

                        // 2. set logged user
                        loggedUser = foundUser.get();

                        // 3. enter submenu
                        menuLoged(loggedUser);

                    } else {
                        System.out.println("");
                        System.out.println("This username does not exist");
                    }
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("");
                    System.out.println("This is not a choice");
            }
//			for(int i = 0; i<50; i++){
//				System.out.println();
//			}
            System.out.println("");
            System.out.println("Would you like to quit y/n");
            input = in.next().toLowerCase();
            quit = input.charAt(0);
        }
    }

    private static void menuLoged(User user) throws IOException {

        TodoService todoService = new TodoServiceImpl();

        TagService tagService = new TagServiceImpl();

        ArrayList<Tag> tagList = new ArrayList<Tag>();

        Scanner in = new Scanner(System.in);
        int choice = 0;
        String input;
        char quit = 'a';

        BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
        while (quit != 'y') {
            System.out.println("");
            System.out.println("\t--- logged as: " + user.getUsername().toUpperCase() + "---");
            System.out.println("\t1. See all your todos");
            System.out.println("\t2. Add todo");
            System.out.println("\t3. Find by tags");
            System.out.println("\t4. Search todo by text");

            choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("");
                    todoService.findAllByUsername(user.getUsername()).forEach(todo -> {
                        System.out.println(todo.getId()+"\t|"+todo.getText()+"\t|"+todo.getTags());

                    });
                    break;

                case 2:
                    System.out.println("");
                    System.out.println("Introduce Todo");
                    String todoToBeSaved = ob.readLine();

                    // 1. check if exists
                    Optional<Todo> foundTodo = todoService.findByName(todoToBeSaved);

                    if (foundTodo.isPresent()) {
                        System.out.println("");
                        System.out.println("Todo already exist");

                    } else {

                        Todo todo = new Todo();
                        todo.setUserId(user.getId());
                        todo.setText(todoToBeSaved);
                        todo.setCreatedOn(new Date());

                        System.out.println(todo.getUserId()+todo.getText());

                        todoService.save(todo);

                        System.out.println("todo.getUserId():"+todo.getUserId());

                        todoService.findByUserId(todo.getUserId()).forEach(foundTodoByUserId -> {
                            if (foundTodoByUserId.getText().equals(todo.getText())){
                                System.out.println("id:"+foundTodoByUserId.getId()+" user_id:"+foundTodoByUserId.getUserId()+" text:"+foundTodoByUserId.getText());
                                todo.setId(foundTodoByUserId.getId());
                            }
                        });

                        tagList = tagService.findAll();
                        System.out.println("");
                        System.out.println("Chose a tag of importance for introduced todo: \n ");
                        tagList.forEach(tag -> {
                            System.out.println("tag id:"+tag.getId()+" tag name:"+tag.getName());
                        });

                        int tagId = in.nextInt();
                        tagService.addTagByTodoId(todo.getId(),tagId);
                        System.out.println("");
                        System.out.println("Todo was saved");

                    }
                    break;

                case 3:
                    tagList = tagService.findAll();
                    System.out.println("");
                    System.out.println("Chose a tag to find by: \n ");
                    tagList.forEach(tag -> {
                        System.out.println("tag id: "+tag.getId()+" tag name: "+tag.getName());
                    });

                    Long tagId = in.nextLong();

                    todoService.findByTagId(user.getId(), tagId).forEach(todo -> {
                        System.out.println(todo.getText());
                    });

                    break;

                case 4:
                    // search by text
                    System.out.println("");
                    System.out.println("Introduce text");
                    String textToFindTodo = ob.readLine();

                    System.out.println("");
                    todoService.findByIntroducedText(textToFindTodo).forEach(todo -> {
                        System.out.println("Found todo: "+todo.getText());
                    });

                    break;
                default:
            }
            System.out.println("");
            System.out.println("Would you like to quit to main menu y/n");
            input = in.next().toLowerCase();
            quit = input.charAt(0);
        }
    }

    private static void initDatabase(){
     Connection connection;

     FileReaderService fileReaderService = new FileReaderServiceImpl();
            try {
               connection = DriverManager.getConnection(
                        "jdbc:postgresql://127.0.0.1:5432/todos",
                        "postgres",
                        "558226");
                PreparedStatement statement = connection.prepareStatement(fileReaderService.read());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();

        }
    }
}
