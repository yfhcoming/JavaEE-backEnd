package com.javaee.sys.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author javaee
 * @since 2021-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;


}
