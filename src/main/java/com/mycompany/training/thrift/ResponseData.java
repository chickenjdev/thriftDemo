/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.mycompany.training.thrift;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2022-05-24")
public class ResponseData implements org.apache.thrift.TBase<ResponseData, ResponseData._Fields>, java.io.Serializable, Cloneable, Comparable<ResponseData> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ResponseData");

  private static final org.apache.thrift.protocol.TField ERROR_FIELD_DESC = new org.apache.thrift.protocol.TField("error", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField ERROR_DESC_FIELD_DESC = new org.apache.thrift.protocol.TField("errorDesc", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField USER_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("userInfo", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ResponseDataStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ResponseDataTupleSchemeFactory();

  public int error; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String errorDesc; // required
  public @org.apache.thrift.annotation.Nullable UserInfo userInfo; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR((short)1, "error"),
    ERROR_DESC((short)2, "errorDesc"),
    USER_INFO((short)3, "userInfo");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ERROR
          return ERROR;
        case 2: // ERROR_DESC
          return ERROR_DESC;
        case 3: // USER_INFO
          return USER_INFO;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ERROR_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR, new org.apache.thrift.meta_data.FieldMetaData("error", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ERROR_DESC, new org.apache.thrift.meta_data.FieldMetaData("errorDesc", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.USER_INFO, new org.apache.thrift.meta_data.FieldMetaData("userInfo", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, UserInfo.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ResponseData.class, metaDataMap);
  }

  public ResponseData() {
  }

  public ResponseData(
    int error,
    java.lang.String errorDesc,
    UserInfo userInfo)
  {
    this();
    this.error = error;
    setErrorIsSet(true);
    this.errorDesc = errorDesc;
    this.userInfo = userInfo;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ResponseData(ResponseData other) {
    __isset_bitfield = other.__isset_bitfield;
    this.error = other.error;
    if (other.isSetErrorDesc()) {
      this.errorDesc = other.errorDesc;
    }
    if (other.isSetUserInfo()) {
      this.userInfo = new UserInfo(other.userInfo);
    }
  }

  public ResponseData deepCopy() {
    return new ResponseData(this);
  }

  @Override
  public void clear() {
    setErrorIsSet(false);
    this.error = 0;
    this.errorDesc = null;
    this.userInfo = null;
  }

  public int getError() {
    return this.error;
  }

  public ResponseData setError(int error) {
    this.error = error;
    setErrorIsSet(true);
    return this;
  }

  public void unsetError() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ERROR_ISSET_ID);
  }

  /** Returns true if field error is set (has been assigned a value) and false otherwise */
  public boolean isSetError() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ERROR_ISSET_ID);
  }

  public void setErrorIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ERROR_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getErrorDesc() {
    return this.errorDesc;
  }

  public ResponseData setErrorDesc(@org.apache.thrift.annotation.Nullable java.lang.String errorDesc) {
    this.errorDesc = errorDesc;
    return this;
  }

  public void unsetErrorDesc() {
    this.errorDesc = null;
  }

  /** Returns true if field errorDesc is set (has been assigned a value) and false otherwise */
  public boolean isSetErrorDesc() {
    return this.errorDesc != null;
  }

  public void setErrorDescIsSet(boolean value) {
    if (!value) {
      this.errorDesc = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public UserInfo getUserInfo() {
    return this.userInfo;
  }

  public ResponseData setUserInfo(@org.apache.thrift.annotation.Nullable UserInfo userInfo) {
    this.userInfo = userInfo;
    return this;
  }

  public void unsetUserInfo() {
    this.userInfo = null;
  }

  /** Returns true if field userInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetUserInfo() {
    return this.userInfo != null;
  }

  public void setUserInfoIsSet(boolean value) {
    if (!value) {
      this.userInfo = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ERROR:
      if (value == null) {
        unsetError();
      } else {
        setError((java.lang.Integer)value);
      }
      break;

    case ERROR_DESC:
      if (value == null) {
        unsetErrorDesc();
      } else {
        setErrorDesc((java.lang.String)value);
      }
      break;

    case USER_INFO:
      if (value == null) {
        unsetUserInfo();
      } else {
        setUserInfo((UserInfo)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR:
      return getError();

    case ERROR_DESC:
      return getErrorDesc();

    case USER_INFO:
      return getUserInfo();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ERROR:
      return isSetError();
    case ERROR_DESC:
      return isSetErrorDesc();
    case USER_INFO:
      return isSetUserInfo();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ResponseData)
      return this.equals((ResponseData)that);
    return false;
  }

  public boolean equals(ResponseData that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_error = true;
    boolean that_present_error = true;
    if (this_present_error || that_present_error) {
      if (!(this_present_error && that_present_error))
        return false;
      if (this.error != that.error)
        return false;
    }

    boolean this_present_errorDesc = true && this.isSetErrorDesc();
    boolean that_present_errorDesc = true && that.isSetErrorDesc();
    if (this_present_errorDesc || that_present_errorDesc) {
      if (!(this_present_errorDesc && that_present_errorDesc))
        return false;
      if (!this.errorDesc.equals(that.errorDesc))
        return false;
    }

    boolean this_present_userInfo = true && this.isSetUserInfo();
    boolean that_present_userInfo = true && that.isSetUserInfo();
    if (this_present_userInfo || that_present_userInfo) {
      if (!(this_present_userInfo && that_present_userInfo))
        return false;
      if (!this.userInfo.equals(that.userInfo))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + error;

    hashCode = hashCode * 8191 + ((isSetErrorDesc()) ? 131071 : 524287);
    if (isSetErrorDesc())
      hashCode = hashCode * 8191 + errorDesc.hashCode();

    hashCode = hashCode * 8191 + ((isSetUserInfo()) ? 131071 : 524287);
    if (isSetUserInfo())
      hashCode = hashCode * 8191 + userInfo.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ResponseData other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetError()).compareTo(other.isSetError());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetError()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.error, other.error);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetErrorDesc()).compareTo(other.isSetErrorDesc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrorDesc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errorDesc, other.errorDesc);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUserInfo()).compareTo(other.isSetUserInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userInfo, other.userInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ResponseData(");
    boolean first = true;

    sb.append("error:");
    sb.append(this.error);
    first = false;
    if (!first) sb.append(", ");
    sb.append("errorDesc:");
    if (this.errorDesc == null) {
      sb.append("null");
    } else {
      sb.append(this.errorDesc);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userInfo:");
    if (this.userInfo == null) {
      sb.append("null");
    } else {
      sb.append(this.userInfo);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (userInfo != null) {
      userInfo.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ResponseDataStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ResponseDataStandardScheme getScheme() {
      return new ResponseDataStandardScheme();
    }
  }

  private static class ResponseDataStandardScheme extends org.apache.thrift.scheme.StandardScheme<ResponseData> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ResponseData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERROR
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.error = iprot.readI32();
              struct.setErrorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ERROR_DESC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.errorDesc = iprot.readString();
              struct.setErrorDescIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // USER_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.userInfo = new UserInfo();
              struct.userInfo.read(iprot);
              struct.setUserInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ResponseData struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ERROR_FIELD_DESC);
      oprot.writeI32(struct.error);
      oprot.writeFieldEnd();
      if (struct.errorDesc != null) {
        oprot.writeFieldBegin(ERROR_DESC_FIELD_DESC);
        oprot.writeString(struct.errorDesc);
        oprot.writeFieldEnd();
      }
      if (struct.userInfo != null) {
        oprot.writeFieldBegin(USER_INFO_FIELD_DESC);
        struct.userInfo.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ResponseDataTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ResponseDataTupleScheme getScheme() {
      return new ResponseDataTupleScheme();
    }
  }

  private static class ResponseDataTupleScheme extends org.apache.thrift.scheme.TupleScheme<ResponseData> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ResponseData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetError()) {
        optionals.set(0);
      }
      if (struct.isSetErrorDesc()) {
        optionals.set(1);
      }
      if (struct.isSetUserInfo()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetError()) {
        oprot.writeI32(struct.error);
      }
      if (struct.isSetErrorDesc()) {
        oprot.writeString(struct.errorDesc);
      }
      if (struct.isSetUserInfo()) {
        struct.userInfo.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ResponseData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.error = iprot.readI32();
        struct.setErrorIsSet(true);
      }
      if (incoming.get(1)) {
        struct.errorDesc = iprot.readString();
        struct.setErrorDescIsSet(true);
      }
      if (incoming.get(2)) {
        struct.userInfo = new UserInfo();
        struct.userInfo.read(iprot);
        struct.setUserInfoIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

