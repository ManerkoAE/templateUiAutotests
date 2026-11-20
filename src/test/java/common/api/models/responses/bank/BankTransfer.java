package common.api.models.responses.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankTransfer {

    private Boolean success;
    private String id;
    private String userId;
    private Double amount;
    private String type;
    private String date;
    private String description;
    private String referenceId;
    private Double fee;
    private String senderName;
    private String receiverName;
    private Double senderBalance;
    private Double receiverBalance;
}
