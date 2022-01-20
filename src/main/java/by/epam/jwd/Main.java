package by.epam.jwd;

import by.epam.jwd.entity.Role;

public class Main {
    public static void main(String[] args) {
        Role role = Role.valueOf("ADMIN");
        System.out.println(role);
    }
}
