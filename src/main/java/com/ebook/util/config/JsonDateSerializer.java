package com.ebook.util.config;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by donh on 2018/7/20.
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
    private static final SimpleDateFormat dateFormat;
    private static String DATE_DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    static {
        DATE_DEFAULT_PATTERN = StringUtils.isEmpty(DATE_DEFAULT_PATTERN) ? "yyyy-MM-dd HH:mm:ss" : DATE_DEFAULT_PATTERN;
        dateFormat = new SimpleDateFormat(DATE_DEFAULT_PATTERN);
    }

    public JsonDateSerializer() {
    }

    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }
}
