package geektime.spring.springbucks.model;

import java.io.Serializable;
import java.util.Date;
import org.joda.money.Money;

public class Coffee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String name;
    private Money price;


    public Long getId() {
        return id;
    }
    public Coffee withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public Coffee withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public Coffee withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getName() {
        return name;
    }


    public Coffee withName(String name) {
        this.setName(name);
        return this;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public Money getPrice() {
        return price;
    }


    public Coffee withPrice(Money price) {
        this.setPrice(price);
        return this;
    }


    public void setPrice(Money price) {
        this.price = price;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append("]");
        return sb.toString();
    }
}