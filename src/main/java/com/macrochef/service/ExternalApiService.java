package com.macrochef.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macrochef.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ingredient fillNutrition(Ingredient ingredient) {

        try {
            String url =
                    "https://world.openfoodfacts.org/cgi/search.pl?search_terms="
                            + ingredient.getName()
                            + "&search_simple=1&action=process&json=1";

            String response = rest.getForObject(url, String.class);

            JsonNode root = mapper.readTree(response);
            JsonNode products = root.get("products");

            if (products == null || products.isEmpty()) {
                System.out.println("No OFF product found for: " + ingredient.getName());
                return ingredient;
            }

            JsonNode product = products.get(0);
            JsonNode nutriments = product.get("nutriments");

            if (nutriments == null) return ingredient;

            ingredient.setCalories100g(getDouble(nutriments, "energy-kcal_100g"));
            ingredient.setProtein100g(getDouble(nutriments, "proteins_100g"));
            ingredient.setCarbs100g(getDouble(nutriments, "carbohydrates_100g"));
            ingredient.setFat100g(getDouble(nutriments, "fat_100g"));

            // API ID olarak product ID kullanabiliriz
            if (product.has("id"))
                ingredient.setApiId(product.get("id").asText());

            ingredient.setApiSource("OpenFoodFacts");

        } catch (Exception e) {
            System.out.println("OFF API ERROR: " + e.getMessage());
        }

        return ingredient;
    }

    private double getDouble(JsonNode node, String key) {
        return node.has(key) ? node.get(key).asDouble() : 0.0;
    }
}
