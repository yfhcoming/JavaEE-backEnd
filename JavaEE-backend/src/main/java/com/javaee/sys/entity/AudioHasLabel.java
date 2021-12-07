package com.javaee.sys.entity;

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
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AudioHasLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer audioId;

    private Integer labelId;


}
