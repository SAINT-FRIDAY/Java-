import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Collection;
import java.util.List;

public class ClassProductToJSON implements Serializaer<Product> {
	@Override
	public void serialize(Product object , File output) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(output, object);
	}
	@Override
	public Product deserialize(File input) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		return mapper.readValue(input,Product.class);
	}
	@Override
	public void serializeCollection(List<Product> objects, File output) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(output, objects);
	}
	@Override
	public List<Product> deserializeCollection(File input) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
		return mapper.readValue(input,  mapper.getTypeFactory().constructCollectionType(List.class,Product.class));
	}
	
	
	
	
	
	
	
	
	
	
	
	/*public void serialize(Product product, File output) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(output, product);
		}
	public Product deserialize(File input) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		return mapper.readValue(input,Product.class);
	}
	public void serializecollection(List<Product> products,File input) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(input, products);
	}
	public List<Product> deserializeCollection(File output) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
		return mapper.readValue(output,  mapper.getTypeFactory().constructCollectionType(List.class,Product.class));
	}*/
}
