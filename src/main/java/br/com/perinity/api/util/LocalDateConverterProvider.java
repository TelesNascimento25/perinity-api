package br.com.perinity.api.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LocalDateConverterProvider implements ParamConverterProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.equals(LocalDate.class)) {
			return (ParamConverter<T>) new ParamConverter<LocalDate>() {
				@Override
				public LocalDate fromString(String value) {
					return LocalDate.parse(value);
				}

				@Override
				public String toString(LocalDate value) {
					return value.toString();
				}
			};
		}
		return null;
	}
}
