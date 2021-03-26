package com.example.verreken.backend.model.send;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owe {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("from")
    private String from;

    @Expose
    @SerializedName("to")
    private String to;

    @Expose
    @SerializedName("amount")
    private double amount;

}
