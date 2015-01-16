package vn.itplus.dungpt;

import android.graphics.drawable.Drawable;

public class PersonModel {

	String name;
	String phone;
	Drawable img;

	public PersonModel(String name, String phone, Drawable img) {
		this.name = name;
		this.phone = phone;
		this.img = img;
	}

	public Drawable getImg() {
		return img;
	}

	public void setImg(Drawable img) {
		this.img = img;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
