package com.guodong.core.pojo;

import java.io.Serializable;

public class Owner implements Serializable {
    private Long id;

    private String name;

    private String phone;

    private String bkphone;

    private Long houseid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBkphone() {
        return bkphone;
    }

    public void setBkphone(String bkphone) {
        this.bkphone = bkphone == null ? null : bkphone.trim();
    }

    public Long getHouseid() {
        return houseid;
    }

    public void setHouseid(Long houseid) {
        this.houseid = houseid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", bkphone=").append(bkphone);
        sb.append(", houseid=").append(houseid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Owner other = (Owner) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getBkphone() == null ? other.getBkphone() == null : this.getBkphone().equals(other.getBkphone()))
            && (this.getHouseid() == null ? other.getHouseid() == null : this.getHouseid().equals(other.getHouseid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getBkphone() == null) ? 0 : getBkphone().hashCode());
        result = prime * result + ((getHouseid() == null) ? 0 : getHouseid().hashCode());
        return result;
    }
}