package com.bakalauras.backend.payload.mapper;

import com.bakalauras.backend.models.lists.Port;
import com.bakalauras.backend.models.lists.ReturnReason;
import com.bakalauras.backend.models.records.ReturnToPortRecord;
import com.bakalauras.backend.payload.request.ReturnToPortRecordDto;
import com.bakalauras.backend.repository.lists.PortRepository;
import com.bakalauras.backend.repository.lists.ReturnReasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReturnToPortRecordMapper {

    private final PortRepository portRepository;
    private final ReturnReasonRepository returnReasonRepository;

    public ReturnToPortRecordDto convertToDto(ReturnToPortRecord returnToPortRecord) {
        return ReturnToPortRecordDto.builder()
                .identifier(returnToPortRecord.getIdentifier())
                .logbookIdentifier(returnToPortRecord.getLogbookIdentifier())
                .dateTime(returnToPortRecord.getDateTime())
                .coordinates(returnToPortRecord.getCoordinates())
                .portCode(getPortCode(returnToPortRecord.getPort()))
                .returnReasonCode(getReturnReasonCode(returnToPortRecord.getReturnReason()))
                .build();
    }

    public ReturnToPortRecord convertToEntity(ReturnToPortRecordDto returnToPortRecord) {
        if (returnToPortRecord.getIdentifier() != null) {
            return ReturnToPortRecord.builder()
                    .identifier(returnToPortRecord.getIdentifier())
                    .logbookIdentifier(returnToPortRecord.getLogbookIdentifier())
                    .dateTime(returnToPortRecord.getDateTime())
                    .coordinates(returnToPortRecord.getCoordinates())
                    .port(getPort(returnToPortRecord.getPortCode()))
                    .returnReason(getReturnReason(returnToPortRecord.getReturnReasonCode()))
                    .build();
        }
        return null;
    }

    private ReturnReason getReturnReason(String code) {
        return returnReasonRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Return reason not found %s", code)));
    }

    private Port getPort(String code) {
        if (code != null) {
            return portRepository.findByCode(code)
                    .orElseThrow(() -> new RuntimeException(String.format("Port not found %s", code)));
        }
        return null;
    }

    private String getReturnReasonCode(ReturnReason returnReason) {
        if (returnReason != null) {
            return returnReason.getCode();
        }
        return null;
    }

    private String getPortCode(Port port) {
        if (port != null) {
            return port.getCode();
        }
        return null;
    }
}
