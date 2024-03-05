package com.bookstore.bookstore.payload.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.micrometer.common.lang.Nullable;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomResponse<T> {

    public CustomResponse(@Nullable T response, @Nonnull HttpStatus status) {
        this.response = response;
        this.httpStatus = status;
        this.isSuccess = status.is2xxSuccessful();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    private Boolean isSuccess;

    private HttpStatus httpStatus;

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    public static final CustomResponse<Void> SUCCESS = CustomResponse.<Void>builder()
            .isSuccess(true)
            .httpStatus(HttpStatus.OK)
            .build();

    public static <E> CustomResponse<E> ok(E response) {
        return CustomResponse.<E>builder()
                .response(response)
                .isSuccess(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    public static <E> CustomResponse<E> created(E response) {
        return CustomResponse.<E>builder()
                .response(response)
                .isSuccess(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }
}
