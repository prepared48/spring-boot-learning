package com.prepared.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author prepared
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tuser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idCard;

    private String name;

    private Integer age;

    private Boolean ismale;


}
