package api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBookingById {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Bookingdates bookingdates;
    private boolean depositpaid;
    private String additionalneeds;
}
