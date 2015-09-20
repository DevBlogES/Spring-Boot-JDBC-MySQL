package es.devblog.test.Model.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Warren on 20/09/2015.
 */
public class CustomDateTimeDeserializer extends JsonDeserializer<LocalDate> {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		JsonToken jsonToken = jsonParser.getCurrentToken();

		if (jsonToken == JsonToken.VALUE_STRING) {
			return LocalDate.parse(jsonParser.getText().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}

		if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
			Date timestamp = new Date(jsonParser.getLongValue());
			return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		throw deserializationContext.mappingException(handledType());
	}
}
