package com.springboot.cmp.util;

import com.springboot.cmp.entity.Gender;

import jakarta.persistence.AttributeConverter;

public class GenderAttributeConverter implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn(Gender attribute) {
		if (attribute == null)
			return null;
		switch (attribute) {
		case MALE:
			return "M";
		case FEMALE:
			return "F";
		default:
			throw new IllegalArgumentException(attribute + " is not a valid gender");
		}

	}

	@Override
	public Gender convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		switch (dbData) {
		case "M":
			return Gender.MALE;
		case "F":
			return Gender.FEMALE;
		default:
			throw new IllegalArgumentException(dbData + " is not a valid gender");
		}
	}

}
