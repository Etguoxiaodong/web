package com.guodong.core.pojo;

import java.io.Serializable;

public class House implements Serializable {
    private Long id;

    private String building;

    private String unit;

    private String door;

    private Double area;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door == null ? null : door.trim();
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", building=").append(building);
        sb.append(", unit=").append(unit);
        sb.append(", door=").append(door);
        sb.append(", area=").append(area);
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
        House other = (House) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBuilding() == null ? other.getBuilding() == null : this.getBuilding().equals(other.getBuilding()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getDoor() == null ? other.getDoor() == null : this.getDoor().equals(other.getDoor()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBuilding() == null) ? 0 : getBuilding().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getDoor() == null) ? 0 : getDoor().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        return result;
    }
}