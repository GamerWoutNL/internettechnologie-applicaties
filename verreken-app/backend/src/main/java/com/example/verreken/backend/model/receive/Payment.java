package com.example.verreken.backend.model.receive;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements JsonDeserializer<Payment> {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("amount")
    private double amount;

    @Expose
    @SerializedName("description")
    private String description;

    @Override
    public Payment deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject root = element.getAsJsonObject();

        return Payment.builder()
                .name(root.get("name").getAsString())
                .amount(Double.parseDouble(root.get("amount").getAsString()))
                .description(root.get("description").getAsString())
                .build();
    }
}
