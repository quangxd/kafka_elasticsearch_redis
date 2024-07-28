package superapp.portal.web.model.dto;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EsQueryDTO {

    private UUID id;

    private UUID userId;

    private String name;

    private Integer age;

    private String address;

    private String actionType;


}
