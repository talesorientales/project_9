package org.example.Data;

import com.opencsv.bean.CsvBindByPosition;

public class Forbes {
    private Integer id;

    @CsvBindByPosition(position = 0)
    private Integer rank;
    @CsvBindByPosition(position = 1)
    private String name_forbes;
    @CsvBindByPosition(position = 2)
    private Double networth;
    @CsvBindByPosition(position = 3)
    private Integer age;
    @CsvBindByPosition(position = 4)
    private String country;
    @CsvBindByPosition(position = 5)
    private String source_forbes;
    @CsvBindByPosition(position = 6)
    private String indusrty;

    @Override
    public String toString() {
        return "Forbes { " +
                "id=" + id +
                ", rank=" + rank +
                ", name_forbes='" + name_forbes + '\'' +
                ", networth=" + networth +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", source_forbes='" + source_forbes + '\'' +
                ", indusrty='" + indusrty + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_forbes() {
        return name_forbes;
    }

    public void setName_forbes(String name_forbes) {
        this.name_forbes = name_forbes;
    }

    public Double getNetworth() {
        return networth;
    }

    public void setNetworth(Double networth) {
        this.networth = networth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSource_forbes() {
        return source_forbes;
    }

    public void setSource_forbes(String source_forbes) {
        this.source_forbes = source_forbes;
    }

    public String getIndusrty() {
        return indusrty;
    }

    public void setIndusrty(String indusrty) {
        this.indusrty = indusrty;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
