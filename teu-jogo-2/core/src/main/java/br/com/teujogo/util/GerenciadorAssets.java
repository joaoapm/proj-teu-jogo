package br.com.teujogo.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.teujogo.ed.Asset;

public class GerenciadorAssets {

	public static List<Asset> lerAssets() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(GerenciadorAssets.class.getClassLoader().getResource("data/assets.json"),
					objectMapper.getTypeFactory().constructCollectionType(List.class, Asset.class));
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
