package by.epam.jwd.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private double phoneNumber;
	private String photo;
	private Status status;
	private String eMail;
	private Role role;

	public User() {
	}

	public User(int id, String name, String surname, String login, String password,
				double phoneNumber, String photo, Status status, String eMail, Role role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
		this.status = status;
		this.eMail = eMail;
		this.role = role;
	}

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
