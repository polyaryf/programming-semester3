package dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import dao.UsersRepository;
import model.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UsersRepositoryFileImpl implements UsersRepository {
    private static List<User> users = new ArrayList<>();
    private static final String USERS_REP_CSV_FILE_PATH =
            "/Users/polinom/Repositories/programming-semester3/FormUsersServerPart1/src/main/java/dao/impl/users-repository.csv";
    private static final File csvFile = new File(USERS_REP_CSV_FILE_PATH);
    private boolean flagOfPageRefreshing = true;

    @Override
    public void save(User user) {
        int id = users.size() + 1;
        user.setId(id);
        if (users.contains(user)) {
            System.out.println("User already exist");
        } else {
            System.out.println(Arrays.toString(new User[]{users.get(0)}));
            users.add(user);
            writeNewUser(user);
        }
    }

    @Override
    public List<User> getAll() {
        if (flagOfPageRefreshing) {
            flagOfPageRefreshing = false;
            return new ArrayList<>(bildUsersList(readCSVFile()));
        } else {
            System.out.println(users + " else in getAll()");
            return new ArrayList<>(users);
        }
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    private List<String[]> readCSVFile() {
        List<String[]> usersFromCSV = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(USERS_REP_CSV_FILE_PATH))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                usersFromCSV.add(values);
                System.out.println(Arrays.toString(values) + "23");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(usersFromCSV);
        return usersFromCSV;
    }

    private List<User> bildUsersList(List<String[]> usersFromCSV) {
        List<User> userList = new ArrayList<>();
        for (String[] row : usersFromCSV) {
            User user = User.builder()
                    .id(Integer.valueOf(row[0]))
                    .firstName(row[1])
                    .lastName(row[2])
                    .courseName(row[3])
                    .age(Integer.valueOf(row[4]))
                    .build();
            userList.add(user);
        }
        System.out.println(userList);
        users = userList;
        return userList;
    }

    public void writeNewUser(User user) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile))) {
            List<String[]> usersToWrite = new ArrayList<>();
            for (User curUser: users) {
                String[] userArray = {
                        String.valueOf(curUser.getId()),
                        curUser.getFirstName(),curUser.getLastName(),
                        curUser.getCourseName(), String.valueOf(curUser.getAge()),
                };
                usersToWrite.add(userArray);
            }
            System.out.println(usersToWrite + " writeNewUser()");
            csvWriter.writeAll(usersToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
