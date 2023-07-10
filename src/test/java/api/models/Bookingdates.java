package api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookingdates {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkout;
}
