package com.example.day3;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Person")
public class Person extends Model {

	@Column(name = "Name")
	public String name;

}
