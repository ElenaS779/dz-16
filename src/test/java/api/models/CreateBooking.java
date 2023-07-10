package api.models;

import api.models.Bookingdates;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBooking {
        private String firstname;
        private String lastname;
        private Integer totalprice;
        private Bookingdates bookingdates;
        private boolean depositpaid;
        private String additionalneeds;

}
