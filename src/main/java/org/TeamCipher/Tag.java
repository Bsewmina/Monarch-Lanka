package org.TeamCipher;



public class Tag {
    private int id;
    private String tagName;
    private String tagCode;
    private String relTag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public String getRelTag() {
        return relTag;
    }

    public void setRelTag(String relTag) {
        this.relTag = relTag;
    }

    public Tag(int id, String tagName, String tagCode, String relTag) {
        this.id = id;
        this.tagName = tagName;
        this.tagCode = tagCode;
        this.relTag = relTag;
    }
}
