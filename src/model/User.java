package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {
	
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty street;
    private IntegerProperty postalCode;
    private StringProperty city;
    private ObjectProperty<LocalDate> birthday;

    public User(){
    	this(null,null);
    }
    
    public User(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

	public User(String firstName2, String lastName2, String street2, String city2, int postalcode2, String birthday2) {
		this.firstName = new SimpleStringProperty(firstName2);
        this.lastName = new SimpleStringProperty(lastName2); 
        this.street = new SimpleStringProperty(street2);
        this.city = new SimpleStringProperty(city2);
        this.postalCode = new SimpleIntegerProperty(postalcode2);
        this.birthday = new SimpleObjectProperty<LocalDate>();
	}

	public User(String firstName2, String lastName2, String street2, String city2, int postalcode2) {
		this.firstName = new SimpleStringProperty(firstName2);
        this.lastName = new SimpleStringProperty(lastName2); 
        this.street = new SimpleStringProperty(street2);
        this.city = new SimpleStringProperty(city2);
        this.postalCode = new SimpleIntegerProperty(postalcode2);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public String getLastName() {
		return lastName.get();
	}

	public String getStreet() {
		return street.get();
	}

	public int getPostalCode() {
		return postalCode.get();
	}

	public String getCity() {
		return city.get();
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setFirstName(StringProperty firstName) {
		this.firstName = firstName;
	}

	public void setLastName(StringProperty lastName) {
		this.lastName = lastName;
	}

	public void setStreet(StringProperty street) {
		this.street = street;
	}

	public void setPostalCode(IntegerProperty postalCode) {
		this.postalCode = postalCode;
	}

	public void setCity(StringProperty city) {
		this.city = city;
	}

	public void setBirthday(ObjectProperty<LocalDate> birthday) {
		this.birthday = birthday;
	}

	public StringProperty firstNameProperty() {
        return firstName;
    }
	public StringProperty lastNameProperty() {
        return firstName;
    }
}
