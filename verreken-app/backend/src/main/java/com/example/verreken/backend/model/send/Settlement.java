package com.example.verreken.backend.model.send;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("owes")
    private List<Owe> owes;

    @Expose
    @SerializedName("date")
    private LocalDate date;

}
