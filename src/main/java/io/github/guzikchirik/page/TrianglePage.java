package io.github.guzikchirik.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class TrianglePage {
    private String sideA;
    private String sideB;
    private String sideC;
}
