package by.epam.jwd.entity;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMIN(1), CUSTOMER(2), DRIVER(3);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getRole(int id) {
        for (Role role : Role.values()) {
            if (role.id == id) {
                return role;
            }
        }
        return null;
    }
}
