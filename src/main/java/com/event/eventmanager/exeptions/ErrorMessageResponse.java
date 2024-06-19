package com.event.eventmanager.exeptions;

import java.time.LocalDateTime;

public record ErrorMessageResponse(
        String message,
        String detailedMessage,
        LocalDateTime dateTime
) {
}
