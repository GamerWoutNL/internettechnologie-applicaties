package com.example.verreken.backend.model.send;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Document(collection = "settlement")
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {

    @Id
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("owes")
    private List<Owe> owes;

    @Expose
    @SerializedName("date")
    private LocalDate date;

}
