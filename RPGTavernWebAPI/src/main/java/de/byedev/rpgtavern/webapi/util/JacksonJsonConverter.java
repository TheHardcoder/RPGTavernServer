package de.byedev.rpgtavern.webapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsExtension;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsMediaType;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;
import org.osgi.util.converter.Converter;
import org.osgi.util.converter.Converters;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.osgi.service.component.annotations.ServiceScope.PROTOTYPE;
import static org.osgi.util.converter.ConverterFunction.CANNOT_HANDLE;

//@Component(scope = PROTOTYPE)
//@JaxrsExtension
//@JaxrsMediaType(APPLICATION_JSON)
public class JacksonJsonConverter<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {

    @Reference(service= LoggerFactory.class)
    private Logger logger;

    private final Converter converter = Converters.newConverterBuilder()
            .rule(String.class, this::toJson)
            .rule(this::toObject)
            .build();

    private ObjectMapper mapper = new ObjectMapper();

    private String toJson(Object value, Type targetType) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error("error on JSON creation", e);
            return e.getLocalizedMessage();
        }
    }

    private Object toObject(Object o, Type t) {
        try {
            if (List.class.getName().equals(t.getTypeName())) {
                return this.mapper.readValue((String) o, List.class);
            }
            return this.mapper.readValue((String) o, String.class);
        } catch (IOException e) {
            logger.error("error on JSON parsing", e);
        }
        return CANNOT_HANDLE;
    }

    @Override
    public boolean isWriteable(
            Class<?> c, Type t, Annotation[] a, MediaType mediaType) {

        return APPLICATION_JSON_TYPE.isCompatible(mediaType)
                || mediaType.getSubtype().endsWith("+json");
    }

    @Override
    public boolean isReadable(
            Class<?> c, Type t, Annotation[] a, MediaType mediaType) {

        return APPLICATION_JSON_TYPE.isCompatible(mediaType)
                || mediaType.getSubtype().endsWith("+json");
    }

    @Override
    public void writeTo(
            T o, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
            MultivaluedMap<String, Object> arg5, OutputStream out)
            throws IOException, WebApplicationException {

        String json = converter.convert(o).to(String.class);
        out.write(json.getBytes());
    }

    @SuppressWarnings("unchecked")
    @Override
    public T readFrom(
            Class<T> arg0, Type arg1, Annotation[] arg2, MediaType arg3,
            MultivaluedMap<String, String> arg4, InputStream in)
            throws IOException, WebApplicationException {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in));
        return (T) converter.convert(reader.readLine()).to(arg1);
    }
}