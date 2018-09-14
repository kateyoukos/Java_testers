package addresbook.model;

import java.util.Objects;

public class GroupData {

    private int id = Integer.MAX_VALUE;
    private String name;
    private String header;

    private String footer;

    public GroupData setId(int id) {
        this.id = id;
        return this;
    }

    public GroupData setName(String name) {
        this.name = name;
        return this;
    }

    public GroupData setHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData setFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public int getId() {
        return id;
    }

    /*public GroupData(String name, String header, String footer) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupData(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id &&
                Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
