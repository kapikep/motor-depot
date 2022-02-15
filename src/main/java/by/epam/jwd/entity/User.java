package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String phoneNumber;
	private String photo;
	private Status status;
	private String eMail;
	private String additionalInfo;
	private Role role;

	public User() {
	}

	public User(int id, String name, String surname, String login, String password,
				String phoneNumber, String photo, Status status, String eMail, String additionalInfo, Role role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
		this.status = status;
		this.eMail = eMail;
		this.additionalInfo = additionalInfo;
		this.role = role;
	}

	public User(String name, String surname, String login, String password, String phoneNumber, String photo,
				Status status, String eMail, String additionalInfo, Role role) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
		this.status = status;
		this.eMail = eMail;
		this.additionalInfo = additionalInfo;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(login, user.login) && Objects.equals(password, user.password) &&
				Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(photo, user.photo) && status == user.status && Objects.equals(eMail, user.eMail) &&
				Objects.equals(additionalInfo, user.additionalInfo) && role == user.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, login, password, phoneNumber, photo, status, eMail, additionalInfo, role);
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
