package entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class ProductBuilder {
    private String name;
    private String category;
    private int index;
}
