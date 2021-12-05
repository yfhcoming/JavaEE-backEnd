package com.javaee.collection.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;


}
