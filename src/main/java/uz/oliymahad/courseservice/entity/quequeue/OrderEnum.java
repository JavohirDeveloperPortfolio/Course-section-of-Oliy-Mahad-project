package uz.oliymahad.courseservice.entity.quequeue;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Getter
@NoArgsConstructor
public enum OrderEnum {

    PENDING,
    ACCEPT,
    REJECT,
    CANCEL,
    COMPLETED;

}
