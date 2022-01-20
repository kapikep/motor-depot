package by.epam.jwd.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private String password;
	private double phoneNumber;
	private Status status;
	private String eMail;
	private Role role;



	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", password='" + password + '\'' +
				", phoneNumber=" + phoneNumber +
				", status=" + status +
				", eMail='" + eMail + '\'' +
				", role=" + role +
				'}';
	}
}
