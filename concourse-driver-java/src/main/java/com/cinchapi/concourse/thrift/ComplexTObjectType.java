/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.cinchapi.concourse.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * The possible types for a {@link ComplexTObject}.
 */
public enum ComplexTObjectType implements org.apache.thrift.TEnum {
  SCALAR(1),
  MAP(2),
  LIST(3),
  SET(4),
  TOBJECT(5),
  TCRITERIA(6);

  private final int value;

  private ComplexTObjectType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ComplexTObjectType findByValue(int value) { 
    switch (value) {
      case 1:
        return SCALAR;
      case 2:
        return MAP;
      case 3:
        return LIST;
      case 4:
        return SET;
      case 5:
        return TOBJECT;
      case 6:
        return TCRITERIA;
      default:
        return null;
    }
  }
}
